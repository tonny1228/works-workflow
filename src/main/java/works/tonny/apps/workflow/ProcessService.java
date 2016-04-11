/**
 * 
 */
package works.tonny.apps.workflow;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import works.tonny.apps.auth.AuthType;
import works.tonny.apps.auth.AuthVerify;

/**
 * 流程定义服务
 * 
 * @author 祥栋
 */
public interface ProcessService {

	/**
	 * deploy a bpmn resource as a process define
	 * 
	 * @param resource
	 * @return
	 */
	@AuthVerify(AuthType.CREATE)
	DeploymentDesc deploy(String name, String category, String tenantId, String resourceName, File file,
			Map<String, String> properties, Map<String, File> resources);

	/**
	 * Deletes the given deployment and cascade deletion to process instances,
	 * history process instances and jobs.
	 * 
	 * @param deploymentId id of the deployment, cannot be null.
	 */
	@AuthVerify(AuthType.DELETE)
	void deleteDeployment(String deploymentId, boolean cascade);

	/**
	 * Gives access to a deployed process diagram, e.g., a PNG image, through a
	 * stream of bytes.
	 * 
	 * @param processDefinitionId id of a {@link ProcessDefinition}, cannot be
	 *            null.
	 * @return null when the diagram resource name of a
	 *         {@link ProcessDefinition} is null.
	 * @throws ActivitiObjectNotFoundException when the process diagram doesn't
	 *             exist.
	 */
	InputStream getProcessDiagram(String processDefinitionId);

	/**
	 * Returns the {@link ProcessDefinition} including all BPMN information like
	 * additional Properties (e.g. documentation).
	 */
	ProcessDefinitionDesc getProcessDefinition(String processDefinitionId);

	/**
	 * Returns the {@link ProcessDefinition} including all BPMN information like
	 * additional Properties (e.g. documentation).
	 */
	ProcessDefinitionDesc getLastestProcessDefinition(String processDefinitionKey);

	/**
	 * Suspends the process definition with the given id. If a process
	 * definition is in state suspended, it will not be possible to start new
	 * process instances based on the process definition. <strong>Note: all the
	 * process instances of the process definition will still be active (ie. not
	 * suspended)!</strong>
	 * 
	 * @throws ActivitiObjectNotFoundException if no such processDefinition can
	 *             be found
	 * @throws ActivitiException if the process definition is already in state
	 *             suspended.
	 */
	@AuthVerify(AuthType.UPDATE)
	void suspendProcessDefinitionById(String processDefinitionId);

	/**
	 * Activates the process definition with the given id.
	 * 
	 * @throws ActivitiObjectNotFoundException if no such processDefinition can
	 *             be found or if the process definition is already in state
	 *             active.
	 */
	@AuthVerify(AuthType.UPDATE)
	void activateProcessDefinitionById(String processDefinitionId);

	/**
	 * all deployment deployed
	 * 
	 * @return
	 */
	List<DeploymentDesc> listDeployments();

	/**
	 * find ProcessDefinitions in deployment
	 * 
	 * @param deploymentId
	 * @return
	 */
	List<ProcessDefinitionDesc> listProcessDefinition(String deploymentId);

	/**
	 * all ProcessDefinitions deployed;
	 * 
	 * @return
	 */
	List<ProcessDefinitionDesc> listProcessDefinitions();
}
