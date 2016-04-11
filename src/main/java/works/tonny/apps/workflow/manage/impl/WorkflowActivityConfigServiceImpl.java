/**
 * <p>
 * 
 * </p>
 * <p>
 * Copyright: Copyright (c) tonny 2015
 * </p>
 * @date 2015-1-20 下午4:39:31
 * @author tonny
 */
package works.tonny.apps.workflow.manage.impl;

import java.util.List;

import org.llama.library.utils.PropertiesUtils;
import org.springframework.transaction.annotation.Transactional;

import works.tonny.apps.user.AuthedAbstractService;
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
public class WorkflowActivityConfigServiceImpl extends AuthedAbstractService implements
		WorkflowActivityConfigManageService {

	private WorkflowActivityConfigDAO workflowActivityConfigDAO;

	/**
	 * @see works.tonny.apps.workflow.WorkflowActorService#get(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public WorkflowActivityConfig get(String processDefinitionId, String activitiId) {
		final List<WorkflowActivityConfig> list = workflowActivityConfigDAO.list(processDefinitionId, activitiId);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	/**
	 * @see works.tonny.apps.workflow.WorkflowActorService#list(java.lang.String)
	 */
	@Override
	public List<WorkflowActivityConfig> list(String processDefinitionId) {
		return workflowActivityConfigDAO.list(processDefinitionId);
	}

	/**
	 * @see works.tonny.apps.workflow.manage.WorkflowActorConfigService#update(works.tonny.apps.workflow.manage.WorkflowActor)
	 */
	@Override
	public void update(WorkflowActivityConfig config) {
		for (WorkflowActor actor : config.getActors()) {
			actor.setActivitiConfig(config);
		}
		final WorkflowActivityConfig workflowActivityConfig = get(config.getProcessDefinitionId(),
				config.getActivitiId());
		if (workflowActivityConfig != null) {
			workflowActivityConfig.getActors().clear();
			PropertiesUtils.copyProperties(workflowActivityConfig, config, "processDefinitionId", "activitiId",
					"dueTime", "dueType", "balancerPolicy");
			workflowActivityConfig.getActors().addAll(config.getActors());
			workflowActivityConfigDAO.update(workflowActivityConfig);
		} else {
			workflowActivityConfigDAO.save(config);
		}
	}

	/**
	 * @see works.tonny.apps.workflow.manage.WorkflowActorConfigService#delete(java.lang.String)
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(String processDefinitionId) {
		final List<WorkflowActivityConfig> list = list(processDefinitionId);
		for (WorkflowActivityConfig workflowActor : list) {
			workflowActivityConfigDAO.delete(workflowActor);
		}
	}

	/**
	 * @return the workflowActivityConfigDAO
	 */
	public WorkflowActivityConfigDAO getWorkflowActivityConfigDAO() {
		return workflowActivityConfigDAO;
	}

	/**
	 * @param workflowActivityConfigDAO the workflowActivityConfigDAO to set
	 */
	public void setWorkflowActivityConfigDAO(WorkflowActivityConfigDAO workflowActivityConfigDAO) {
		this.workflowActivityConfigDAO = workflowActivityConfigDAO;
	}

}
