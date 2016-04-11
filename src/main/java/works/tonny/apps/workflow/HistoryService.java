/**
 * 
 */
package works.tonny.apps.workflow;

import java.util.List;

/**
 * @author 祥栋
 */
public interface HistoryService {
	ProcessInstanceDesc getProcessInstance(String processInstanceId);

	/**
	 * 获取流程
	 * 
	 * @param processInstanceId
	 * @return
	 */
	List<TaskDesc> listTasks(String processInstanceId);
	
	
	ActivityDesc getLastActivity(String processInstanceId);
}
