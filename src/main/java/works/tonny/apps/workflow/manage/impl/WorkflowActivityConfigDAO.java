/**
 * <p>
 * 
 * </p>
 * <p>
 * Copyright: Copyright (c) tonny 2015
 * </p>
 * @date 2015-1-20 下午4:40:11
 * @author tonny
 */
package works.tonny.apps.workflow.manage.impl;

import java.util.List;

import works.tonny.apps.EntityDAO;
import works.tonny.apps.support.ListSupport;
import works.tonny.apps.workflow.manage.WorkflowActivityConfig;

/**
 * <p>
 * 
 * </p>
 * 
 * @author tonny
 * @version 1.0.0
 */
public interface WorkflowActivityConfigDAO extends EntityDAO<WorkflowActivityConfig> {

	/**
	 * 查询流程中所有的流程执行人配置
	 * 
	 * @param processDefinitionId 流程id
	 * @returnworkflowActivityConfigDAO
	 * @author tonny
	 */
	@ListSupport(field = "processDefinitionId")
	List<WorkflowActivityConfig> list(String processDefinitionId);

	/**
	 * 查询流程中指定任务的流程执行人配置
	 * 
	 * @param processDefinitionId 流程id
	 * @param activitiId 任务id
	 * @return
	 * @author tonny
	 */
	@ListSupport(field = { "processDefinitionId", "activitiId" })
	List<WorkflowActivityConfig> list(String processDefinitionId, String activitiId);

}
