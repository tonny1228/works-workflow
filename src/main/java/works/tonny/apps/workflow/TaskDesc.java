/**
 * 
 */
package works.tonny.apps.workflow;

import java.util.Date;
import java.util.Map;

import works.tonny.apps.Entity;

/**
 * @author 祥栋
 */
public class TaskDesc extends Entity {
	protected String processInstanceId;
	protected String businessKey;
	protected String name;
	protected String parentTaskId;
	protected String description;
	protected String initialAssignee;
	protected String assignee;
	protected int priority;
	protected String category;
	protected String tenantId;
	protected String state;
	protected Date dueDate;
	protected Date claimTime;
	protected Date startTime;
	protected Date endTime;
	protected Long durationInMillis;
	protected String candidateUserIds;
	protected String candidateGroupIds;
	protected Map<String, Object> localVariables;
	protected Map<String, Object> processVariables;
	protected ProcessDefinitionDesc processDefinition;
	protected ProcessInstanceDesc processInstance;

	/**
	 * 
	 */
	public TaskDesc() {
	}

	/**
	 * @param processInstanceId
	 * @param businessKey
	 * @param name
	 * @param parentTaskId
	 * @param description
	 * @param initialAssignee
	 * @param assignee
	 * @param priority
	 * @param category
	 * @param tenantId
	 * @param state
	 * @param dueDate
	 * @param claimTime
	 * @param createTime
	 * @param startTime
	 * @param endTime
	 * @param durationInMillis
	 * @param candidateUserIds
	 * @param candidateGroupIds
	 */
	public TaskDesc(String id, String processInstanceId, String businessKey, String name, String parentTaskId,
					String description, String initialAssignee, String assignee, int priority, String category,
					String tenantId, String state, Date dueDate, Date claimTime, Date startTime, Date endTime,
					Long durationInMillis) {
		super();
		this.id = id;
		this.processInstanceId = processInstanceId;
		this.businessKey = businessKey;
		this.name = name;
		this.parentTaskId = parentTaskId;
		this.description = description;
		this.initialAssignee = initialAssignee;
		this.assignee = assignee;
		this.priority = priority;
		this.category = category;
		this.tenantId = tenantId;
		this.state = state;
		this.dueDate = dueDate;
		this.claimTime = claimTime;
		this.startTime = startTime;
		this.endTime = endTime;
		this.durationInMillis = durationInMillis;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the parentTaskId
	 */
	public String getParentTaskId() {
		return parentTaskId;
	}

	/**
	 * @param parentTaskId the parentTaskId to set
	 */
	public void setParentTaskId(String parentTaskId) {
		this.parentTaskId = parentTaskId;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the initialAssignee
	 */
	public String getInitialAssignee() {
		return initialAssignee;
	}

	/**
	 * @param initialAssignee the initialAssignee to set
	 */
	public void setInitialAssignee(String initialAssignee) {
		this.initialAssignee = initialAssignee;
	}

	/**
	 * @return the assignee
	 */
	public String getAssignee() {
		return assignee;
	}

	/**
	 * @param assignee the assignee to set
	 */
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	/**
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the tenantId
	 */
	public String getTenantId() {
		return tenantId;
	}

	/**
	 * @param tenantId the tenantId to set
	 */
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the dueDate
	 */
	public Date getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * @return the claimTime
	 */
	public Date getClaimTime() {
		return claimTime;
	}

	/**
	 * @param claimTime the claimTime to set
	 */
	public void setClaimTime(Date claimTime) {
		this.claimTime = claimTime;
	}

	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the durationInMillis
	 */
	public Long getDurationInMillis() {
		return durationInMillis;
	}

	/**
	 * @param durationInMillis the durationInMillis to set
	 */
	public void setDurationInMillis(Long durationInMillis) {
		this.durationInMillis = durationInMillis;
	}

	/**
	 * @return the businessKey
	 */
	public String getBusinessKey() {
		return businessKey;
	}

	/**
	 * @param businessKey the businessKey to set
	 */
	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	/**
	 * @return the candidateUserIds
	 */
	public String getCandidateUserIds() {
		return candidateUserIds;
	}

	/**
	 * @param candidateUserIds the candidateUserIds to set
	 */
	public void setCandidateUserIds(String candidateUserIds) {
		this.candidateUserIds = candidateUserIds;
	}

	/**
	 * @return the candidateGroupIds
	 */
	public String getCandidateGroupIds() {
		return candidateGroupIds;
	}

	/**
	 * @param candidateGroupIds the candidateGroupIds to set
	 */
	public void setCandidateGroupIds(String candidateGroupIds) {
		this.candidateGroupIds = candidateGroupIds;
	}

	/**
	 * @return the localVariables
	 */
	public Map<String, Object> getLocalVariables() {
		return localVariables;
	}

	/**
	 * @param localVariables the localVariables to set
	 */
	public void setLocalVariables(Map<String, Object> localVariables) {
		this.localVariables = localVariables;
	}

	/**
	 * @return the processVariables
	 */
	public Map<String, Object> getProcessVariables() {
		return processVariables;
	}

	/**
	 * @param processVariables the processVariables to set
	 */
	public void setProcessVariables(Map<String, Object> processVariables) {
		this.processVariables = processVariables;
	}

	/**
	 * @return the processDefinition
	 */
	public ProcessDefinitionDesc getProcessDefinition() {
		return processDefinition;
	}

	/**
	 * @param processDefinition the processDefinition to set
	 */
	public void setProcessDefinition(ProcessDefinitionDesc processDefinition) {
		this.processDefinition = processDefinition;
	}

	/**
	 * @return the processInstance
	 */
	public ProcessInstanceDesc getProcessInstance() {
		return processInstance;
	}

	/**
	 * @param processInstance the processInstance to set
	 */
	public void setProcessInstance(ProcessInstanceDesc processInstance) {
		this.processInstance = processInstance;
	}

}