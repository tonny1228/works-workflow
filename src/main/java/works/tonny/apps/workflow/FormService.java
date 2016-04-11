/**
 * 
 */
package works.tonny.apps.workflow;

import works.tonny.apps.core.Form;

/**
 * @author 祥栋
 */
public interface FormService {
	/**
	 * return a process start form
	 * 
	 * @param processDefinitionId
	 * @return
	 */
	Form getStartForm(ProcessDefinitionDesc definition);

	/**
	 * return a process start form
	 * 
	 * @param processDefinitionId
	 * @return
	 */
	String getViewForm(ProcessInstanceDesc instance);

	/**
	 * return form resource
	 * 
	 * @param definition
	 * @return
	 */
	String getStartFormKey(ProcessDefinitionDesc definition);

	/**
	 * return a task form
	 * 
	 * @param taskId
	 * @return
	 */
	Form getTaskForm(TaskDesc taskDesc);

	/**
	 * return task form resource
	 * 
	 * @param taskDesc
	 * @return
	 */
	String getTaskFormKey(TaskDesc taskDesc);

	/**
	 * return form resource
	 * 
	 * @param definition
	 * @return
	 */
	String getRenderedStartForm(ProcessDefinitionDesc definition);

	/**
	 * return task form resource
	 * 
	 * @param taskDesc
	 * @return
	 */
	String getRenderedTaskForm(TaskDesc taskDesc);
}
