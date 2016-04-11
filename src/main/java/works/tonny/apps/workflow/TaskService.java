/**
 *
 */
package works.tonny.apps.workflow;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.llama.library.utils.PagedList;
import org.springframework.scheduling.config.Task;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 任务服务
 *
 * @author 祥栋
 */
public interface TaskService {
    TaskDesc getTask(String taskId);

    /**
     * 获取用户流程中用户参与的最后任务
     *
     * @param processInstanceId
     * @return
     */
    TaskDesc getUserTask(String userId, String processInstanceId);

    /**
     * The list of subtasks for this parent task
     */
    List<TaskDesc> getSubTasks(String parentTaskId);

    /**
     * 获取流程
     *
     * @param processInstanceId
     * @return
     */
    List<TaskDesc> listTasks(String processInstanceId);

    /**
     * 查询用户的待办任务
     *
     * @return
     */
    PagedList<TaskDesc> listQueued(String userId, String processDefinitionId, int offset, int limit);

    /**
     * 查询用户的待办任务
     *
     * @return
     */
    PagedList<TaskDesc> listQueuedByKey(String userId, String processDefinitionKey, int offset, int limit);

    /**
     * 查询用户的待办任务
     *
     * @return
     */
    PagedList<TaskDesc> listQueued(String userId, int offset, int limit);

    /**
     * 查询用户的待办任务
     *
     * @return
     */
    PagedList<TaskDesc> listAssingned(String userId, String processDefinitionId, int offset, int limit);

    /**
     * 查询用户的待办任务
     *
     * @return
     */
    PagedList<TaskDesc> listAssingnedByKey(String userId, String processDefinitionKey, int offset, int limit);

    /**
     * 查询用户的待办任务
     *
     * @return
     */
    PagedList<TaskDesc> listAssingned(String userId, int offset, int limit);

    /**
     * Deletes the given task.
     *
     * @param taskId  The id of the task that will be deleted, cannot be null. If no task exists with
     *                the given taskId, the operation is ignored.
     * @param cascade If cascade is true, also the historic information related to this task is
     *                deleted.
     * @throws ActivitiObjectNotFoundException when the task with given id does not exist.
     * @throws ActivitiException               when an error occurs while deleting the task or in case the task is
     *                                         part of a running process.
     */
    void deleteTask(String taskId, boolean cascade);

    /**
     * Claim responsibility for a task: the given user is made assignee for the task. The difference
     * with {@link #setAssignee(String, String)} is that here a check is done if the task already
     * has a user assigned to it. No check is done whether the user is known by the identity
     * component.
     *
     * @param taskId task to claim, cannot be null.
     * @param userId user that claims the task. When userId is null the task is unclaimed, assigned
     *               to no one.
     * @throws ActivitiObjectNotFoundException     when the task doesn't exist.
     * @throws ActivitiTaskAlreadyClaimedException when the task is already claimed by another user.
     */
    void claim(String taskId, String userId);

    /**
     * A shortcut to {@link #claim} with null user in order to unclaim the task
     *
     * @param taskId task to unclaim, cannot be null.
     * @throws ActivitiObjectNotFoundException when the task doesn't exist.
     */
    void unclaim(String taskId);

    /**
     * Called when the task is successfully executed.
     *
     * @param taskId the id of the task to complete, cannot be null.
     * @throws ActivitiObjectNotFoundException when no task exists with the given id.
     * @throws ActivitiException               when this task is {@link DelegationState#PENDING} delegation.
     */
    void complete(String userId, String taskId);

    /**
     * Delegates the task to another user. This means that the assignee is set and the delegation
     * state is set to {@link DelegationState#PENDING}. If no owner is set on the task, the owner is
     * set to the current assignee of the task.
     *
     * @param taskId The id of the task that will be delegated.
     * @param userId The id of the user that will be set as assignee.
     * @throws ActivitiObjectNotFoundException when no task exists with the given id.
     */
    void delegateTask(String taskId, String userId);

    /**
     * Marks that the assignee is done with this task and that it can be send back to the owner. Can
     * only be called when this task is {@link DelegationState#PENDING} delegation. After this
     * method returns, the {@link Task#getDelegationState() delegationState} is set to
     * {@link DelegationState#RESOLVED}.
     *
     * @param taskId the id of the task to resolve, cannot be null.
     * @throws ActivitiObjectNotFoundException when no task exists with the given id.
     */
    void resolveTask(String userId, String taskId, Object... variables);

    /**
     * Called when the task is successfully executed, and the required task parameters are given by
     * the end-user.
     *
     * @param taskId    the id of the task to complete, cannot be null.
     * @param variables task parameters. May be null or empty.
     * @throws ActivitiObjectNotFoundException when no task exists with the given id.
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    void complete(WorkflowExecutor user, String taskId, Object... variables);

    /**
     * Called when the task is successfully executed, and the required task paramaters are given by
     * the end-user.
     *
     * @param taskId     the id of the task to complete, cannot be null.
     * @param variables  task parameters. May be null or empty.
     * @param localScope If true, the provided variables will be stored task-local, instead of
     *                   process instance wide (which is the default for {@link #complete(String, Map)}).
     * @throws ActivitiObjectNotFoundException when no task exists with the given id.
     */
    @Transactional(rollbackFor = Exception.class)
    void complete(WorkflowExecutor user, String taskId, boolean localScope, Object... variables);

    /**
     * Changes the assignee of the given task to the given userId. No check is done whether the user
     * is known by the identity component.
     *
     * @param taskId id of the task, cannot be null.
     * @param userId id of the user to use as assignee.
     * @throws ActivitiObjectNotFoundException when the task or user doesn't exist.
     */
    void setAssignee(String taskId, String userId);

    /**
     * Transfers ownership of this task to another user. No check is done whether the user is known
     * by the identity component.
     *
     * @param taskId id of the task, cannot be null.
     * @param userId of the person that is receiving ownership.
     * @throws ActivitiObjectNotFoundException when the task or user doesn't exist.
     */
    void setOwner(String taskId, String userId);

    /**
     * Changes the priority of the task. Authorization: actual owner / business admin
     *
     * @param taskId   id of the task, cannot be null.
     * @param priority the new priority for the task.
     * @throws ActivitiObjectNotFoundException when the task doesn't exist.
     */
    void setPriority(String taskId, int priority);

    /**
     * Changes the due date of the task
     *
     * @param taskId  id of the task, cannot be null.
     * @param dueDate the new due date for the task
     * @throws ActivitiException when the task doesn't exist.
     */
    void setDueDate(String taskId, Date dueDate);

    /**
     * set variable on a task. If the variable is not already existing, it will be created in the
     * most outer scope. This means the process instance in case this task is related to an
     * execution.
     */
    void setVariable(String taskId, String variableName, Object value);

    /**
     * set variable on a task. If the variable is not already existing, it will be created in the
     * task.
     */
    void setVariableLocal(String taskId, String variableName, Object value);

    /**
     * get a variables and search in the task scope and if available also the execution scopes.
     */
    Object getVariable(String taskId, String variableName);

    /**
     * checks whether or not the task has a variable defined with the given name.
     */
    Object getVariableLocal(String taskId, String variableName);

    /**
     * get all variables and search in the task scope and if available also the execution scopes. If
     * you have many variables and you only need a few, consider using
     * {@link #getVariables(String, Collection)} for better performance.
     */
    Map<String, Object> getVariables(String taskId);

    /**
     * get all variables and search only in the task scope. If you have many task local variables
     * and you only need a few, consider using {@link #getVariablesLocal(String, Collection)} for
     * better performance.
     */
    Map<String, Object> getVariablesLocal(String taskId);

    /**
     * Removes the variable from the task. When the variable does not exist, nothing happens.
     */
    void removeVariable(String taskId, String variableName);

    /**
     * Removes the variable from the task (not considering parent scopes). When the variable does
     * not exist, nothing happens.
     */
    void removeVariableLocal(String taskId, String variableName);

    /**
     * Add a comment to a task and/or process instance.
     */
    CommentDesc addComment(String taskId, String processInstanceId, String message);

    /**
     * Returns an individual comment with the given id. Returns null if no comment exists with the
     * given id.
     */
    CommentDesc getComment(String commentId);

    /**
     * Removes all comments from the provided task and/or process instance
     */
    void deleteComments(String taskId, String processInstanceId);

    /**
     * Removes an individual comment with the given id.
     *
     * @throws ActivitiObjectNotFoundException when no comment exists with the given id.
     */
    void deleteComment(String commentId);

    /**
     * The comments related to the given task.
     */
    List<CommentDesc> getTaskComments(String taskId);

}
