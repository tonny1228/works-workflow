/**
 *
 */
package works.tonny.apps.workflow.web;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.llama.library.utils.HttpRequestUtils;
import org.llama.library.utils.PagedList;
import org.llama.library.utils.PropertiesUtils;
import works.tonny.apps.core.Form;
import works.tonny.apps.core.IDGeneratorService;
import works.tonny.apps.support.EntityLazyProxy;
import works.tonny.apps.support.ServiceManager;
import works.tonny.apps.user.AuthedAction;
import works.tonny.apps.user.User;
import works.tonny.apps.user.UserQuery;
import works.tonny.apps.user.UserService;
import works.tonny.apps.user.model.UserInfo;
import works.tonny.apps.workflow.*;
import works.tonny.apps.workflow.manage.WorkflowActivityConfig;
import works.tonny.apps.workflow.manage.WorkflowActivityConfigService;
import works.tonny.apps.workflow.manage.WorkflowActor;
import works.tonny.apps.workflow.util.VariableUtils;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.Map.Entry;

/**
 * 流程实例操作
 *
 * @author 祥栋
 */
public class ProcessAction extends AuthedAction {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    protected ProcessService processService;
    protected ProcessInstanceService instanceService;
    protected FormService formService;
    protected TaskService taskService;
    protected HistoryService historyService;
    protected String processDefinitionId;
    protected IDGeneratorService idGeneratorService;
    protected WorkflowActivityConfigService workflowActivityConfigService;
    protected UserService userService;

    protected String processDefinitionKey;
    protected String processInstanceId;
    protected String taskId;
    protected String userId;
    protected String redirect;
    protected String actionRedirect;

    /**
     * 查询定制化的路径，根据此路径查询定制化页面
     */
    protected void redirect() {
        if (StringUtils.isNotEmpty(redirect)) {
            return;
        }
        redirect = request.getRequestURI();
        int endIndex = 0;
        endIndex = StringUtils.lastIndexOf(redirect, "/");
        redirect = StringUtils.substring(redirect, request.getContextPath().length() + 8, endIndex);
        if (redirect.length() > 0) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < StringUtils.countMatches(redirect, "/") - 1; i++) {
                builder.append("../");
            }
            builder.append(redirect.substring(1)).append("/");
            redirect = builder.toString();
            actionRedirect = "../" + redirect;
        } else {
            actionRedirect = redirect;
        }
    }

    /**
     * 进入流程开始表单
     *
     * @return
     */
    public String start() {
        redirect();
        ProcessDefinitionDesc processDefinition = null;
        if (StringUtils.isNotEmpty(processDefinitionId)) {
            processDefinition = processService.getProcessDefinition(processDefinitionId);
        } else if (StringUtils.isNotEmpty(processDefinitionKey)) {
            processDefinition = processService.getLastestProcessDefinition(processDefinitionKey);
        } else {
            request.setAttribute(ERROR_MESSAGE, "没有指定启动流程");
            return ERROR;
        }
        String key = formService.getStartFormKey(processDefinition);
        if (key != null) {
            request.setAttribute("html", formService.getRenderedStartForm(processDefinition));
        } else {
            Form form = formService.getStartForm(processDefinition);
            request.setAttribute("form", form);
        }
        request.setAttribute("processDefinition", processDefinition);
        return "start";
    }

    /**
     * 启动一个流程，并初始化参数
     *
     * @return
     */
    public String save() {
        redirect();
        ProcessDefinitionDesc processDefinition = processService.getProcessDefinition(processDefinitionId);
        String entityClass = processDefinition.getEntityClass();
        WorkflowEntity entity = null;
        if (this instanceof WorkflowEntityAware) {
            entity = ((WorkflowEntityAware) this).getEntity();
        } else if (entityClass != null) {
            entity = VariableUtils.wrap(request, entityClass);
        }

        if (entity != null) {
            if (StringUtils.isNotEmpty(processDefinition.getEntityIdGenerator())) {
                Map<String, Object> describe = null;
                try {
                    describe = BeanUtils.describe(entity);
                } catch (Exception e) {
                }
                entity.setId(idGeneratorService.create(processDefinition.getEntityIdGenerator(), describe));
            }
        }
        Map<String, Object> map = new HashMap<String, Object>();
        if (processDefinition.getInitVariable() != null) {
            map.putAll(processDefinition.getInitVariable());
        }
        VariableUtils.fillVariables(map, request, entity, null);
        map.put(Variables.ENTITY, entity);
        map.put(Variables.VIEW_FORM, processDefinition.getViewForm());
        ProcessInstanceDesc p = instanceService.startProcessInstanceById(WorkflowExecutor.valueOf(loginedUser().getUser()), processDefinitionId, map);
        processInstanceId = p.getId();
        request.setAttribute("Instance", p);
        return "saved";
    }

    /**
     * 设定下一步执行人
     *
     * @return
     * @author tonny
     */
    public String selectActor() {
        redirect();
        ProcessInstanceDesc processInstance = historyService.getProcessInstance(processInstanceId);
        List<TaskDesc> listTasks = historyService.listTasks(processInstanceId);
        final TaskDesc leastTask = listTasks.get(listTasks.size() - 1);
        Map<WorkflowActor, List<User>> users = new HashMap<WorkflowActor, List<User>>();
        if (leastTask.getState().equals("进行中")
                && (listTasks.size() > 1
                && listTasks.get(listTasks.size() - 2).getAssignee()
                .equals(loginedUser().getUser().getUsername()) || listTasks.size() < 2)
                && "assign".equals(leastTask.getLocalVariables().get("localtask_BalancerPolicy"))) {

            WorkflowActivityConfig config = workflowActivityConfigService.get(processInstance.getProcessDefinitionId(),
                    leastTask.getBusinessKey());
            for (WorkflowActor workflowActor : config.getActors()) {
                final UserQuery userQuery = userService.createUserQuery();
                List<User> actors = new ArrayList<User>();
                boolean empty = true;
                if (StringUtils.isNotEmpty(workflowActor.getDepartmentId())) {
                    userQuery.departmentLike(workflowActor.getDepartmentId());
                    empty = false;
                }
                if (StringUtils.isNotEmpty(workflowActor.getPositionId())) {
                    userQuery.position(workflowActor.getPositionId());
                    empty = false;
                }
                if (StringUtils.isNotEmpty(workflowActor.getRoleId())) {
                    userQuery.role(workflowActor.getRoleId());
                    empty = false;
                }
                if (StringUtils.isNotEmpty(workflowActor.getUsername())) {
                    userQuery.username(workflowActor.getUsername());
                    empty = false;
                }
                if (empty) {
                    continue;
                }
                PagedList<User> list = userQuery.list(1, 50);
                while (list.size() > 0 && list.getOffset() + list.size() <= list.getTotal()) {
                    for (User user : list) {
                        EntityLazyProxy proxy = ServiceManager.lookup(EntityLazyProxy.class);
                        proxy.refresh(user, "roles");
                        if (user instanceof UserInfo) {
                            proxy.refresh(user, "position");
                            proxy.refresh(user, "position.department");
                        }
                        actors.add(user);
                    }
                    list = userQuery.list(list.getOffset() + 1, 50);
                }
                users.put(workflowActor, actors);
            }
            request.setAttribute("task", leastTask);
            request.setAttribute("users", users);
            return "selectActor";
        }

        return StringUtils.defaultString(getParameter("return"), "assigned");
    }

    /**
     * 保存数据
     *
     * @return
     */
    public String update() {
        redirect();
        if (StringUtils.isEmpty(taskId) && StringUtils.isNotEmpty(processDefinitionId)) {
            return save();
        }
        Enumeration<String> names = request.getAttributeNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            taskService.setVariableLocal(taskId, name, request.getParameter(name));
        }
        return "started";
    }

    /**
     * 查看流程的最新信息
     *
     * @return
     */
    public String view() {
        redirect();
        TaskDesc userTask = taskService.getUserTask(loginedUser().getUser().getUsername(), processInstanceId);
        request.setAttribute("task", userTask);
        return "view";
    }

    /**
     * 待接受任务
     *
     * @return
     */
    public String toCliam() {
        TaskDesc task = taskService.getTask(taskId);
        this.processInstanceId = task.getProcessInstanceId();
        viewInstanceHistory();
        return "cliam";
    }

    /**
     * 接受一个分配给自己的任务
     *
     * @return
     */
    public String cliam() {
        redirect();
        taskService.claim(taskId, loginedUser().getUser().getUsername());
        return "cliamed";
    }

    /**
     * 取消接受的任务
     *
     * @return
     */
    public String uncliam() {
        redirect();
        taskService.unclaim(taskId);
        return "queue";
    }

    /**
     * 委派任务给其它用户
     *
     * @return
     */
    public String delegateTask() {
        redirect();
        if ("delegate".equals(getParameter("type"))) {
            taskService.delegateTask(taskId, userId);
        } else {
            taskService.setAssignee(taskId, userId);
        }
        return StringUtils.isEmpty(getParameter("return")) ? "assigned" : getParameter("return");
    }

    /**
     * 简单的完成任务
     *
     * @return
     */
    public String complete() {
        redirect();
        taskService.complete(WorkflowExecutor.valueOf(loginedUser().getUser()), taskId, request.getParameterMap());
        return "assigned";
    }

    /**
     * 查看流程跟踪图
     *
     * @return
     */
    public String viewImage() {
        InputStream processDiagram = instanceService.getProcessDiagram(processInstanceId);
        response.setContentType("image/png");
        // response.setHeader("Content-Disposition", "attachment;filename=" +
        // processDefinition.getDiagramResourceName());
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            IOUtils.copy(processDiagram, out);
        } catch (IOException e) {
            log.error(e);
        } finally {
            IOUtils.closeQuietly(processDiagram);
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                log.error(e);
            }
        }
        return null;
    }

    /**
     * 查询所有待分配给自己的任务
     *
     * @return
     */
    public String queue() {
        redirect();
        List<TaskDesc> todo = null;
        if (StringUtils.isNotEmpty(processDefinitionId)) {
            todo = taskService.listQueued(processDefinitionId, getOffset(), getLimit());
        } else if (StringUtils.isNotEmpty(processDefinitionKey)) {
            todo = taskService.listQueuedByKey(loginedUser().getUser().getUsername(), processDefinitionKey, getOffset(), getLimit());
        } else {
            todo = taskService.listQueued(loginedUser().getUser().getUsername(), getOffset(), getLimit());
        }
        request.setAttribute("list", todo);
        return SUCCESS;
    }

    /**
     * 查询所有已分配给自己的任务
     *
     * @return
     */
    public String assigned() {
        redirect();
        List<TaskDesc> todo = null;
        if (StringUtils.isNotEmpty(processDefinitionId)) {
            todo = taskService.listAssingned(processDefinitionId, getOffset(), getLimit());
        } else if (StringUtils.isNotEmpty(processDefinitionKey)) {
            todo = taskService.listAssingnedByKey(loginedUser().getUser().getUsername(), processDefinitionKey, getOffset(), getLimit());
        } else {
            todo = taskService.listAssingned(loginedUser().getUser().getUsername(), getOffset(), getLimit());
        }
        request.setAttribute("list", todo);
        return SUCCESS;
    }

    /**
     * 查询所有自己启动的流程
     *
     * @return
     */
    public String started() {
        redirect();
        List<ProcessInstanceDesc> todo = null;
        if (StringUtils.isNotEmpty(processDefinitionId)) {
            todo = instanceService.userStarted(processDefinitionId, getOffset(), getLimit());
        } else if (StringUtils.isNotEmpty(processDefinitionKey)) {
            todo = instanceService.userStartedByKey(loginedUser().getUser().getUsername(), processDefinitionKey, getOffset(), getLimit());
            ProcessDefinitionDesc lastestProcessDefinition = processService
                    .getLastestProcessDefinition(processDefinitionKey);
            processDefinitionId = lastestProcessDefinition.getId();
        } else {
            todo = instanceService.userStarted(loginedUser().getUser().getUsername(), getOffset(), getLimit());
        }
        request.setAttribute("list", todo);
        return SUCCESS;
    }

    /**
     * 查询所有自己参与的流程
     *
     * @return
     */
    public String involved() {
        redirect();
        List<ProcessInstanceDesc> todo = null;
        if (StringUtils.isNotEmpty(processDefinitionId)) {
            todo = instanceService.userInvolved(processDefinitionId, getOffset(), getLimit());
        } else if (StringUtils.isNotEmpty(processDefinitionKey)) {
            todo = instanceService.userInvolvedByKey(loginedUser().getUser().getUsername(), processDefinitionKey, getOffset(), getLimit());
        } else {
            todo = instanceService.userInvolved(loginedUser().getUser().getUsername(), getOffset(), getLimit());
        }
        request.setAttribute("list", todo);
        return SUCCESS;
    }

    /**
     * 查询所有的流程
     *
     * @return
     */
    public String all() {
        redirect();
        List<ProcessInstanceDesc> todo = null;
        if (StringUtils.isNotEmpty(processDefinitionId)) {
            todo = instanceService.list(processDefinitionId, getOffset(), getLimit());
        } else if (StringUtils.isNotEmpty(processDefinitionKey)) {
            todo = instanceService.listByKey(processDefinitionKey, getOffset(), getLimit());
        } else {
            todo = instanceService.list(getOffset(), getLimit());
        }
        request.setAttribute("list", todo);
        return SUCCESS;
    }


    /**
     * 列出用户参与的并结束的流程
     *
     * @return
     */
    public String finished() {
        redirect();
        List<ProcessInstanceDesc> todo = null;
        if (StringUtils.isNotEmpty(processDefinitionId)) {
            todo = instanceService.userInvolved(processDefinitionId, getOffset(), getLimit());
        } else if (StringUtils.isNotEmpty(processDefinitionKey)) {
            todo = instanceService.userFinished(loginedUser().getUser().getUsername(), processDefinitionKey, getOffset(), getLimit());
        }
        request.setAttribute("list", todo);
        return SUCCESS;
    }

    /**
     * 查看流程信息
     *
     * @return
     */
    public String viewInstance() {
        redirect();
        ProcessInstanceDesc processInstance = instanceService.getProcessInstance(processInstanceId);
        List<TaskDesc> listTasks = taskService.listTasks(processInstanceId);
        request.setAttribute("process", processInstance);
        request.setAttribute("form", formService.getViewForm(processInstance));
        request.setAttribute("tasks", listTasks);
        return "view";
    }

    /**
     * 查看流程详细信息
     *
     * @return
     */
    public String viewInstanceHistory() {
        redirect();
        ProcessInstanceDesc processInstance = historyService.getProcessInstance(processInstanceId);
        List<TaskDesc> listTasks = historyService.listTasks(processInstanceId);
        request.setAttribute("process", processInstance);
        request.setAttribute("form", formService.getViewForm(processInstance));
        request.setAttribute("tasks", listTasks);
        return "view";
    }

    /**
     * 开始处理自己的待办任务
     *
     * @return
     */
    public String startTask() {
        redirect();
        TaskDesc task = taskService.getTask(taskId);
        String taskFormKey = formService.getTaskFormKey(task);
        if (StringUtils.isNotEmpty(taskFormKey)) {
            request.setAttribute("html", formService.getRenderedTaskForm(task));
        } else {
            request.setAttribute("form", formService.getTaskForm(task));
        }
        request.setAttribute("task", task);
        return "taskStart";
    }

    /**
     * 提交待办信息
     *
     * @return
     * @throws Exception
     */
    public String dealTask() throws Exception {
        redirect();
        String action = getParameter("proc_action");
        Enumeration<String> parameterNames = request.getParameterNames();
        if ("保存".equals(action)) {
            while (parameterNames.hasMoreElements()) {
                String name = parameterNames.nextElement();
                taskService.setVariableLocal(taskId, name, request.getParameter(name));
            }
        } else {
            ProcessInstanceDesc processInstance = instanceService.getProcessInstance(processInstanceId);
            WorkflowEntity entity = (WorkflowEntity) processInstance.getVariables().get(Variables.ENTITY);
            Map<String, Object> map = new HashMap<String, Object>();
            try {
                WorkflowEntity update = null;
                if (this instanceof WorkflowEntityAware) {
                    update = ((WorkflowEntityAware) this).getEntity();
                }

                if (entity != null && update == null) {
                    HttpRequestUtils.updateBean(request, entity);
                } else if (entity != null && update != null) {
                    PropertiesUtils.copyNotNullProperties(entity, update);
                } else {
                    ProcessDefinitionDesc processDefinition = processService.getProcessDefinition(processInstance
                            .getProcessDefinitionId());
                    if (update != null) {
                        entity = update;
                    } else {
                        entity = VariableUtils.wrap(request, processDefinition.getEntityClass());
                    }
                    entity.setStarter(loginedUser().getUser().getUsername());
                    if (StringUtils.isNotEmpty(processDefinition.getEntityIdGenerator())) {
                        Map<String, Object> describe = null;
                        try {
                            describe = BeanUtils.describe(entity);
                        } catch (Exception e) {
                        }
                        entity.setId(idGeneratorService.create(processDefinition.getEntityIdGenerator(), describe));
                    }
                    // VariableUtils.fillVariables(map, request, entity,
                    // processInstance.getVariables());
                }
            } catch (Exception e) {
                throw e;
            }
            VariableUtils.fillVariables(map, request, entity, processInstance.getVariables());
            final Set<Entry<String, Object>> entrySet = map.entrySet();
            for (Entry<String, Object> entry : entrySet) {
                taskService.setVariableLocal(taskId, entry.getKey(), entry.getValue());
            }
            map.put(Variables.ENTITY, entity);
            taskService.complete(WorkflowExecutor.valueOf(loginedUser().getUser()), taskId, map);
        }
        return "dealed";
    }

    /**
     * 删除运行中的流程
     *
     * @return
     */
    public String deleteProcessInstance() {
        instanceService.deleteProcessInstance(loginedUser().getUser().getUsername(), processInstanceId, "启动用户主动删除");
        return getParameter("action");
    }

    /**
     * 休眠流程
     *
     * @return
     */
    public String suspendProcessInstance() {
        instanceService.suspendProcessInstanceById(processInstanceId);
        return getParameter("action");
    }

    /**
     * 恢复流程
     *
     * @return
     */
    public String activateProcessInstance() {
        instanceService.activateProcessInstanceById(processInstanceId);
        return getParameter("action");
    }

    /**
     * @return the instanceService
     */
    public ProcessInstanceService getInstanceService() {
        return instanceService;
    }

    /**
     * @param instanceService the instanceService to set
     */
    public void setInstanceService(ProcessInstanceService instanceService) {
        this.instanceService = instanceService;
    }

    /**
     * @return the processDefinitionId
     */
    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    /**
     * @param processDefinitionId the processDefinitionId to set
     */
    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    /**
     * @return the processDefinitionKey
     */
    public String getProcessDefinitionKey() {
        return processDefinitionKey;
    }

    /**
     * @param processDefinitionKey the processDefinitionKey to set
     */
    public void setProcessDefinitionKey(String processDefinitionKey) {
        this.processDefinitionKey = processDefinitionKey;
    }

    /**
     * @return the formService
     */
    public FormService getFormService() {
        return formService;
    }

    /**
     * @param formService the formService to set
     */
    public void setFormService(FormService formService) {
        this.formService = formService;
    }

    /**
     * @return the taskService
     */
    public TaskService getTaskService() {
        return taskService;
    }

    /**
     * @param taskService the taskService to set
     */
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * @return the taskId
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * @param taskId the taskId to set
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return the processService
     */
    public ProcessService getProcessService() {
        return processService;
    }

    /**
     * @param processService the processService to set
     */
    public void setProcessService(ProcessService processService) {
        this.processService = processService;
    }

    /**
     * @return the processInstanceId
     */
    public String getProcessInstanceId() {
        return processInstanceId;
    }

    /**
     * @param processInstanceId the processInstanceId to set
     */
    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    /**
     * @return the historyService
     */
    public HistoryService getHistoryService() {
        return historyService;
    }

    /**
     * @param historyService the historyService to set
     */
    public void setHistoryService(HistoryService historyService) {
        this.historyService = historyService;
    }

    /**
     * @return the idGeneratorService
     */
    public IDGeneratorService getIdGeneratorService() {
        return idGeneratorService;
    }

    /**
     * @param idGeneratorService the idGeneratorService to set
     */
    public void setIdGeneratorService(IDGeneratorService idGeneratorService) {
        this.idGeneratorService = idGeneratorService;
    }

    /**
     * @return the redirect
     */
    public String getRedirect() {
        return redirect;
    }

    /**
     * @return the userService
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * @param userService the userService to set
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * @return the workflowActivityConfigService
     */
    public WorkflowActivityConfigService getWorkflowActivityConfigService() {
        return workflowActivityConfigService;
    }

    /**
     * @param workflowActivityConfigService the workflowActivityConfigService to
     *                                      set
     */
    public void setWorkflowActivityConfigService(WorkflowActivityConfigService workflowActivityConfigService) {
        this.workflowActivityConfigService = workflowActivityConfigService;
    }

    /**
     * @return the actionRedirect
     */
    public String getActionRedirect() {
        return actionRedirect;
    }

}
