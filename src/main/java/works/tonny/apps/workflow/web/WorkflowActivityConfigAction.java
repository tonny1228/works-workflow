/**
 * <p>
 * 
 * </p>
 * <p>
 * Copyright: Copyright (c) tonny 2015
 * </p>
 * @date 2015-1-20 下午4:53:15
 * @author tonny
 */
package works.tonny.apps.workflow.web;

import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import works.tonny.apps.user.AuthedAction;
import works.tonny.apps.workflow.manage.WorkflowActivityConfig;
import works.tonny.apps.workflow.manage.WorkflowActivityConfigManageService;
import works.tonny.apps.workflow.manage.WorkflowActor;

/**
 * <p>
 * 
 * </p>
 * 
 * @author tonny
 * @version 1.0.0
 */
public class WorkflowActivityConfigAction extends AuthedAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private WorkflowActivityConfigManageService workflowActivityConfigService;

	private WorkflowActivityConfig config;
	private List<WorkflowActor> actors;

	private String processDefinitionId;

	private String activitiId;

	public String edit() {
		config = workflowActivityConfigService.get(processDefinitionId, activitiId);
		return SUCCESS;
	}

	public String update() {
		if (config.getActors() == null) {
			config.setActors(new HashSet<WorkflowActor>());
		}
		if (actors != null)
			for (WorkflowActor actor : actors) {
				if (StringUtils.isEmpty(actor.getDepartmentId()) && StringUtils.isEmpty(actor.getPositionId())
						&& StringUtils.isEmpty(actor.getRoleId()) && StringUtils.isEmpty(actor.getUsername())) {
					continue;
				}
				config.getActors().add(actor);
			}
		workflowActivityConfigService.update(config);
		return SUCCESS;
	}

	/**
	 * @return the workflowActivityConfigService
	 */
	public WorkflowActivityConfigManageService getWorkflowActivityConfigService() {
		return workflowActivityConfigService;
	}

	/**
	 * @param workflowActivityConfigService the workflowActivityConfigService to
	 *            set
	 */
	public void setWorkflowActivityConfigService(WorkflowActivityConfigManageService workflowActivityConfigService) {
		this.workflowActivityConfigService = workflowActivityConfigService;
	}

	/**
	 * @return the config
	 */
	public WorkflowActivityConfig getConfig() {
		return config;
	}

	/**
	 * @param config the config to set
	 */
	public void setConfig(WorkflowActivityConfig config) {
		this.config = config;
	}

	/**
	 * @return the actors
	 */
	public List<WorkflowActor> getActors() {
		return actors;
	}

	/**
	 * @param actors the actors to set
	 */
	public void setActors(List<WorkflowActor> actors) {
		this.actors = actors;
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
	 * @return the activitiId
	 */
	public String getActivitiId() {
		return activitiId;
	}

	/**
	 * @param activitiId the activitiId to set
	 */
	public void setActivitiId(String activitiId) {
		this.activitiId = activitiId;
	}

}