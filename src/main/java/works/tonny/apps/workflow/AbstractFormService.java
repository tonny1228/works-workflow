/**
 * 
 */
package works.tonny.apps.workflow;

import org.apache.commons.lang3.StringUtils;
import org.llama.library.utils.ClassUtils;

import works.tonny.apps.core.Form;

/**
 * @author 祥栋
 */
public abstract class AbstractFormService implements FormService {

	/**
	 * {@inheritDoc}
	 * 
	 * @see works.tonny.apps.workflow.FormService#getStartForm(java.lang.String)
	 */
	public Form getStartForm(ProcessDefinitionDesc definition) {
		Form form = doGetStartForm(definition);
		String startFormHandeler = definition.getStartFormHandeler();
		// FormElement formElement = form.getElements().get("formHandler");
		if (StringUtils.isNotEmpty(startFormHandeler)) {
			FormHandler formHandler = ClassUtils.newInstance(startFormHandeler);
			if (formHandler != null)
				formHandler.initForm(form);
		}
		return form;
	}

	/**
	 * @param definition
	 * @return
	 */
	protected abstract Form doGetStartForm(ProcessDefinitionDesc definition);

	/**
	 * {@inheritDoc}
	 * 
	 * @see works.tonny.apps.workflow.FormService#getTaskForm(java.lang.String)
	 */
	public Form getTaskForm(TaskDesc taskDesc) {
		Form form = doGetTaskForm(taskDesc);
		// FormElement formElement = form.getElements().get("系统参数");
		// if (formElement != null) {
		// FormHandler formHandler = ClassUtils.newInstance(formElement.getId());
		// if (formHandler != null)
		// formHandler.initForm(form);
		// }
		return form;
	}

	/**
	 * @param taskDesc
	 * @return
	 */
	protected abstract Form doGetTaskForm(TaskDesc taskDesc);

}
