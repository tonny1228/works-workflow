/**
 * <p>
 * 
 * </p>
 * <p>
 * Copyright: Copyright (c) tonny 2015
 * </p>
 * @date 2015-1-20 下午4:35:24
 * @author tonny
 */
package works.tonny.apps.workflow.manage;

/**
 * <p>
 * 流程执行人配置接口
 * </p>
 * 
 * @author tonny
 * @version 1.0.0
 */
public interface WorkflowActivityConfigManageService extends WorkflowActivityConfigService {

	/**
	 * 更新流程执行人信息
	 * 
	 * @param actor
	 * @author tonny
	 */
	void update(WorkflowActivityConfig config);

	/**
	 * 删除流程中所有的执行人配置信息
	 * 
	 * @param processDefinitionId 流程id
	 * @author tonny
	 */
	void delete(String processDefinitionId);
}