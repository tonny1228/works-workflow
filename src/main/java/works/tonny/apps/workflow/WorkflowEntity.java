/**
 * 
 */
package works.tonny.apps.workflow;

import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import works.tonny.apps.Entity;

/**
 * 流程实体类
 * 
 * @author 祥栋
 */
@MappedSuperclass
public abstract class WorkflowEntity extends Entity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String title;

	/**
	 * 开始人
	 */
	protected String starter;

	/**
	 * 开始时间
	 */
	protected Date processStartTime;

	/**
	 * 结束时间
	 */
	protected Date processEndTime;

	/**
	 * 状态
	 */
	protected WFState state;

	/**
	 * 关联的流程实例id
	 */
	protected String processInstanceId;

	/**
	 * @return the starter
	 */
	public String getStarter() {
		return starter;
	}

	/**
	 * @param starter the starter to set
	 */
	public void setStarter(String starter) {
		this.starter = starter;
	}

	/**
	 * @return the state
	 */
	@Enumerated(EnumType.STRING)
	public WFState getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(WFState state) {
		this.state = state;
	}

	/**
	 * @return the processStartTime
	 */
	public Date getProcessStartTime() {
		return processStartTime;
	}

	/**
	 * @param processStartTime the processStartTime to set
	 */
	public void setProcessStartTime(Date processStartTime) {
		this.processStartTime = processStartTime;
	}

	/**
	 * @return the processEndTime
	 */
	public Date getProcessEndTime() {
		return processEndTime;
	}

	/**
	 * @param processEndTime the processEndTime to set
	 */
	public void setProcessEndTime(Date processEndTime) {
		this.processEndTime = processEndTime;
	}

	/**
	 * @return the title
	 */
	@Transient
	public abstract String getTitle();

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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

}
