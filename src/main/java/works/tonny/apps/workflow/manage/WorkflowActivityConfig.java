/**
 * <p>
 * 
 * </p>
 * <p>
 * Copyright: Copyright (c) tonny 2015
 * </p>
 * @date 2015-1-29 上午11:28:38
 * @author tonny
 */
package works.tonny.apps.workflow.manage;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import works.tonny.apps.Entity;

/**
 * <p>
 * 流程用户任务配置
 * </p>
 * 
 * @author tonny
 * @version 1.0.0
 */
@javax.persistence.Entity
@Table(name = "wf_cfg_activity")
public class WorkflowActivityConfig extends Entity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String processDefinitionId;

	private String activitiId;

	private int dueTime;

	private int dueType;

	private String balancerPolicy;

	private Set<WorkflowActor> actors;

	/**
	 * @see works.tonny.apps.Entity#getId()
	 */
	@Override
	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@Column(length = 50)
	public String getId() {
		return super.getId();
	}

	/**
	 * @return the processDefinitionId
	 */
	@Column(name = "process_definition_id", length = 50)
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
	 * @return the activitiId
	 */
	@Column(name = "activiti_id", length = 50)
	public String getActivitiId() {
		return activitiId;
	}

	/**
	 * @param activitiId the activitiId to set
	 */
	public void setActivitiId(String activitiId) {
		this.activitiId = activitiId;
	}

	/**
	 * @return the dueTime
	 */
	@Column(name = "due_time")
	public int getDueTime() {
		return dueTime;
	}

	/**
	 * @param dueTime the dueTime to set
	 */
	public void setDueTime(int dueTime) {
		this.dueTime = dueTime;
	}

	/**
	 * @return the dueType
	 */
	@Column(name = "due_type")
	public int getDueType() {
		return dueType;
	}

	/**
	 * @param dueType the dueType to set
	 */
	public void setDueType(int dueType) {
		this.dueType = dueType;
	}

	/**
	 * @return the balancerPolicy
	 */
	@Column(name = "balancer_policy", length = 20)
	public String getBalancerPolicy() {
		return balancerPolicy;
	}

	/**
	 * @param balancerPolicy the balancerPolicy to set
	 */
	public void setBalancerPolicy(String balancerPolicy) {
		this.balancerPolicy = balancerPolicy;
	}

	/**
	 * @return the actors
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "activitiConfig")
	public Set<WorkflowActor> getActors() {
		return actors;
	}

	/**
	 * @param actors the actors to set
	 */
	public void setActors(Set<WorkflowActor> actors) {
		this.actors = actors;
	}

}