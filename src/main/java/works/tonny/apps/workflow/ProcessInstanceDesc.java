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
public class ProcessInstanceDesc extends Entity {
	protected String title;

	protected String processDefinitionId;
	
	protected String processDefinitionName;

	protected String tenantId;

	protected String state;

	protected String startUserId;

	protected Date startTime;

	protected Date endTime;

	protected String startActivityId;

	protected String startActivityName;

	protected String endActivityId;

	protected String endActivityName;

	protected String currentActivityId;
	
	protected Date currentActivityTime;

	protected String currentActivityName;

	protected Map<String, Object> variables;

	protected Long durationInMillis;

	/**
	 * 
	 */
	public ProcessInstanceDesc() {
	}

	/**
	 * @param title
	 * @param processDefinitionId
	 * @param tenantId
	 * @param state
	 * @param startUserId
	 * @param startTime
	 * @param endTime
	 * @param startActivityId
	 * @param endActivityId
	 * @param variables
	 * @param durationInMillis
	 */
	public ProcessInstanceDesc(String id, String title, String processDefinitionId, String tenantId, String state,
								String startUserId, Date startTime, Date endTime, String startActivityId,
								String endActivityId, Map<String, Object> variables, Long durationInMillis) {
		this.id = id;
		this.title = title;
		this.processDefinitionId = processDefinitionId;
		this.tenantId = tenantId;
		this.state = state;
		this.startUserId = startUserId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.startActivityId = startActivityId;
		this.endActivityId = endActivityId;
		this.variables = variables;
		this.durationInMillis = durationInMillis;
	}

	/**
	 * @param id
	 * @param processDefinitionId2
	 * @param tenantId2
	 * @param processVariables
	 */
	public ProcessInstanceDesc(String id, String processDefinitionId, String tenantId,
								Map<String, Object> processVariables) {
		this.id = id;
		this.processDefinitionId = processDefinitionId;
		this.tenantId = tenantId;
		this.variables = processVariables;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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
	 * @return the startUserId
	 */
	public String getStartUserId() {
		return startUserId;
	}

	/**
	 * @param startUserId the startUserId to set
	 */
	public void setStartUserId(String startUserId) {
		this.startUserId = startUserId;
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
	 * @return the startActivityId
	 */
	public String getStartActivityId() {
		return startActivityId;
	}

	/**
	 * @param startActivityId the startActivityId to set
	 */
	public void setStartActivityId(String startActivityId) {
		this.startActivityId = startActivityId;
	}

	/**
	 * @return the endActivityId
	 */
	public String getEndActivityId() {
		return endActivityId;
	}

	/**
	 * @param endActivityId the endActivityId to set
	 */
	public void setEndActivityId(String endActivityId) {
		this.endActivityId = endActivityId;
	}

	/**
	 * @return the variables
	 */
	public Map<String, Object> getVariables() {
		return variables;
	}

	/**
	 * @param variables the variables to set
	 */
	public void setVariables(Map<String, Object> variables) {
		this.variables = variables;
	}

	/**
	 * @return the processDefinitionName
	 */
	public String getProcessDefinitionName() {
		return processDefinitionName;
	}

	/**
	 * @param processDefinitionName the processDefinitionName to set
	 */
	public void setProcessDefinitionName(String processDefinitionName) {
		this.processDefinitionName = processDefinitionName;
	}

	/**
	 * @return the startActivityName
	 */
	public String getStartActivityName() {
		return startActivityName;
	}

	/**
	 * @param startActivityName the startActivityName to set
	 */
	public void setStartActivityName(String startActivityName) {
		this.startActivityName = startActivityName;
	}

	/**
	 * @return the endActivityName
	 */
	public String getEndActivityName() {
		return endActivityName;
	}

	/**
	 * @param endActivityName the endActivityName to set
	 */
	public void setEndActivityName(String endActivityName) {
		this.endActivityName = endActivityName;
	}

	/**
	 * @return the currentActivityId
	 */
	public String getCurrentActivityId() {
		return currentActivityId;
	}

	/**
	 * @param currentActivityId the currentActivityId to set
	 */
	public void setCurrentActivityId(String currentActivityId) {
		this.currentActivityId = currentActivityId;
	}

	/**
	 * @return the currentActivityName
	 */
	public String getCurrentActivityName() {
		return currentActivityName;
	}

	/**
	 * @param currentActivityName the currentActivityName to set
	 */
	public void setCurrentActivityName(String currentActivityName) {
		this.currentActivityName = currentActivityName;
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
	 * @return the currentActivityTime
	 */
	public Date getCurrentActivityTime() {
		return currentActivityTime;
	}

	/**
	 * @param currentActivityTime the currentActivityTime to set
	 */
	public void setCurrentActivityTime(Date currentActivityTime) {
		this.currentActivityTime = currentActivityTime;
	}

}
