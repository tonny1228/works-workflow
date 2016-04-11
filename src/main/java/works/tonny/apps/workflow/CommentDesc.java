/**
 * 
 */
package works.tonny.apps.workflow;

import java.util.Date;

import works.tonny.apps.Entity;

/**
 * @author 祥栋
 */
public class CommentDesc extends Entity {
	protected String type;
	protected String userId;
	protected Date time;
	protected String taskId;
	protected String processInstanceId;
	protected String message;

	/**
	 * 
	 */
	public CommentDesc() {
	}

	/**
	 * @param type
	 * @param userId
	 * @param time
	 * @param taskId
	 * @param processInstanceId
	 * @param action
	 * @param message
	 */
	public CommentDesc(String id, String type, String userId, Date time, String taskId, String processInstanceId,
						String message) {
		this.id = id;
		this.type = type;
		this.userId = userId;
		this.time = time;
		this.taskId = taskId;
		this.processInstanceId = processInstanceId;
		this.message = message;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
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
	 * @return the time
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(Date time) {
		this.time = time;
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
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
