/**
 * <p>
 * 
 * </p>
 * <p>
 * Copyright: Copyright (c) tonny 2015
 * </p>
 * @date 2015-1-20 下午4:32:42
 * @author tonny
 */
package works.tonny.apps.workflow.manage;

import java.util.List;

/**
 * <p>
 * 查询流程任务的执行人
 * </p>
 * 
 * @author tonny
 * @version 1.0.0
 */
public interface WorkflowActivityConfigService {
	/**
	 * 查询执行人配置
	 * 
	 * @param processDefinitionId 流程id
	 * @param activitiId 任务id
	 * @return 执行人信息
	 * @author tonny
	 */
	WorkflowActivityConfig get(String processDefinitionId, String activitiId);

	/**
	 * 查询流程中定义的执行人
	 * 
	 * @param processDefinitionId
	 * @return
	 * @author tonny
	 */
	List<WorkflowActivityConfig> list(String processDefinitionId);
}