/**
 *
 */
package works.tonny.apps.workflow;

import java.io.InputStream;
import java.util.Map;

import org.llama.library.utils.PagedList;

/**
 * 工作流程实例服务
 *
 * @author 祥栋
 */
public interface ProcessInstanceService {
    /**
     * 获取流程信息
     *
     * @param processDefinitionId
     * @return
     */
    ProcessInstanceDesc getProcessInstance(String processInstanceId);

    /**
     * 用户发起的流程
     *
     * @return
     */
    PagedList<ProcessInstanceDesc> userStarted(String user, int offset, int limit);

    /**
     * 用户参与的并结束的流程
     *
     * @return
     */
    PagedList<ProcessInstanceDesc> userFinished(String user, String processDefinitionKey, int offset, int limit);


    /**
     * 用户发起的流程
     *
     * @return
     */
    PagedList<ProcessInstanceDesc> userStarted(String user, String processDefinitionId, int offset, int limit);

    /**
     * 用户发起的流程
     *
     * @return
     */
    PagedList<ProcessInstanceDesc> userStartedByKey(String user, String processDefinitionKey, int offset, int limit);

    /**
     * 用户参与的流程
     *
     * @return
     */
    PagedList<ProcessInstanceDesc> userInvolved(String user, int offset, int limit);

    /**
     * 用户参与的流程
     *
     * @return
     */
    PagedList<ProcessInstanceDesc> userInvolved(String user, String processDefinitionId, int offset, int limit);

    /**
     * 用户参与的流程
     *
     * @return
     */
    PagedList<ProcessInstanceDesc> userInvolvedByKey(String user, String processDefinitionKey, int offset, int limit);

    /**
     * 所有的流程
     *
     * @return
     */
    PagedList<ProcessInstanceDesc> list(int offset, int limit);

    /**
     * 所有的流程
     *
     * @return
     */
    PagedList<ProcessInstanceDesc> list(String processDefinitionId, int offset, int limit);

    /**
     * 所有的流程
     *
     * @return
     */
    PagedList<ProcessInstanceDesc> listByKey(String processDefinitionKey, int offset, int limit);

    /**
     * 用最新版本的流程定义开始一个流程实例
     *
     * @param user                 user 标识
     * @param processDefinitionKey 流程定义的key
     * @param variables            一个类Map的数组，成对出现:String1,Object1,String2,Object2...
     * @throws ActivitiObjectNotFoundException when no process definition is deployed with the given
     *                                         key.
     */
    ProcessInstanceDesc startProcessInstanceByKey(WorkflowExecutor user, String processDefinitionKey, Object... variables);

    /**
     * 用指定的流程定义开始一个流程实例
     *
     * @param user                用户标识
     * @param processDefinitionId 流程定义id
     * @param variables           一个类Map的数组，成对出现:String1,Object1,String2,Object2...
     * @throws ActivitiObjectNotFoundException when no process definition is deployed with the given
     *                                         key.
     */
    ProcessInstanceDesc startProcessInstanceById(WorkflowExecutor user, String processDefinitionId, Object... variables);

    /**
     * 删除流程实例
     *
     * @param user              用户标识
     * @param processInstanceId id of process instance to delete, cannot be null.
     * @param deleteReason      reason for deleting, can be null.
     * @throws ActivitiObjectNotFoundException when no process instance is found with the given id.
     */
    void deleteProcessInstance(String user, String processInstanceId, String deleteReason);

    /**
     * 所有的变量
     *
     * @param executionId id of execution, cannot be null.
     * @return the variables or an empty map if no such variables are found.
     * @throws ActivitiObjectNotFoundException when no execution is found for the given executionId.
     */
    Map<String, Object> getVariables(String processInstanceId);

    /**
     * 获取单个变量
     *
     * @param executionId  id of execution, cannot be null.
     * @param variableName name of variable, cannot be null.
     * @return the variable value or null if the variable is undefined or the value of the variable
     * is null.
     * @throws ActivitiObjectNotFoundException when no execution is found for the given executionId.
     */
    Object getVariable(String processInstanceId, String variableName);

    /**
     * 设置变量
     *
     * @param executionId  id of execution to set variable in, cannot be null.
     * @param variableName name of variable to set, cannot be null.
     * @param value        value to set. When null is passed, the variable is not removed, only it's value
     *                     will be set to null.
     * @throws ActivitiObjectNotFoundException when no execution is found for the given executionId.
     * @see VariableScope#setVariable(String, Object)
     * {@link VariableScope#setVariable(String, Object)}
     */
    void setVariables(String processInstanceId, Object... variables);

    /**
     * Removes a variable for an execution.
     *
     * @param executionId  id of execution to remove variable in.
     * @param variableName name of variable to remove.
     */
    void removeVariables(String processInstanceId, String... variableName);

    /**
     * Suspends the process instance with the given id. If a process instance is in state suspended,
     * activiti will not execute jobs (timers, messages) associated with this instance. If you have
     * a process instance hierarchy, suspending one process instance form the hierarchy will not
     * suspend other process instances form that hierarchy.
     *
     * @throws ActivitiObjectNotFoundException if no such ProcessInstanceDesc can be found.
     * @throws ActivitiException               the process instance is already in state suspended.
     */
    void suspendProcessInstanceById(String processInstanceId);

    /**
     * Activates the process instance with the given id. If you have a process instance hierarchy,
     * suspending one process instance form the hierarchy will not suspend other process instances
     * form that hierarchy.
     *
     * @throws ActivitiObjectNotFoundException if no such ProcessInstanceDesc can be found.
     * @throws ActivitiException               if the process instance is already in state active.
     */
    void activateProcessInstanceById(String processInstanceId);

    /**
     * Sends an external trigger to an activity instance that is waiting inside the given execution.
     *
     * @param executionId id of execution to signal, cannot be null.
     * @throws ActivitiObjectNotFoundException when no execution is found for the given executionId.
     */
    void signal(String processInstanceId, Object... processVariables);

    /**
     * Notifies the process engine that a signal event of name 'signalName' has been received. This
     * method delivers the signal to all executions waiting on the signal.
     * <p/>
     * <strong>NOTE:</strong> The waiting executions are notified synchronously.
     *
     * @param signalName the name of the signal event
     */
    void signalEventReceived(String signalName, Object... processVariables);

    /**
     * Notifies the process engine that a signal event of name 'signalName' has been received. This
     * method delivers the signal to a single execution, being the execution referenced by
     * 'executionId'. The waiting execution is notified synchronously.
     *
     * @param signalName  the name of the signal event
     * @param executionId the id of the execution to deliver the signal to
     * @throws ActivitiObjectNotFoundException if no such execution exists.
     * @throws ActivitiException               if the execution has not subscribed to the signal.
     */
    void signalEventReceived(String signalName, String processInstanceId, Object... processVariables);

    /**
     * @param processInstanceId
     * @return
     */
    InputStream getProcessDiagram(String processInstanceId);


    InstanceQuery createQuery();


}
