/**
 * 
 */
package works.tonny.apps.workflow.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.llama.library.utils.ClassUtils;

import works.tonny.apps.user.AuthedAction;
import works.tonny.apps.workflow.DeploymentDesc;
import works.tonny.apps.workflow.ProcessDefinitionDesc;
import works.tonny.apps.workflow.ProcessService;
import works.tonny.apps.workflow.WorkflowEntity;
import works.tonny.apps.workflow.manage.WorkflowActivityConfig;
import works.tonny.apps.workflow.manage.WorkflowActivityConfigService;

/**
 * @author 祥栋
 */
public class ManagementAction extends AuthedAction {
	protected ProcessService processService;
	protected WorkflowActivityConfigService workflowActivityConfigService;

	protected String deploymentId;
	protected String processDefinitionId;
	protected ProcessDefinitionDesc processDefinition;
	protected File file;
	protected String filename;
	protected File[] resources;
	protected String[] resourcesNames;
	protected Map<String, String> prop;
	protected String[] variableName;
	protected String[] variableType;
	protected String[] variableValue;

	/**
	 * 部署列表
	 * 
	 * @return
	 */
	public String deployList() {
		List<DeploymentDesc> list = processService.listDeployments();
		request.setAttribute("list", list);
		return SUCCESS;
	}

	/**
	 * 部署
	 * 
	 * @return
	 */
	public String deploy() {
		if (!ClassUtils.isClassExist(prop.get("entityClass"))) {
			throw new IllegalArgumentException(prop.get("entityClass") + "不存在");
		}

		try {
			if (!ClassUtils.isAssignable(ClassUtils.getClass(prop.get("entityClass")), WorkflowEntity.class)) {
				throw new IllegalArgumentException(prop.get("entityClass") + "不是流程类");
			}
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException(prop.get("entityClass") + "不是流程类");
		}

		if (StringUtils.isNotEmpty(prop.get("startFormHandeler"))
				&& !ClassUtils.isClassExist(prop.get("startFormHandeler"))) {
			throw new IllegalArgumentException(prop.get("startFormHandeler") + "不存在");
		}

		try {
			if (StringUtils.isNotEmpty(prop.get("startFormHandeler"))
					&& !ClassUtils.isAssignable(ClassUtils.getClass(prop.get("startFormHandeler")),
							WorkflowEntity.class)) {
				throw new IllegalArgumentException(prop.get("startFormHandeler") + "不是表单初始化类");
			}
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException(prop.get("entityClass") + "不是表单初始化类");
		}

		Map<String, File> map = new HashMap<String, File>();
		if (resources != null) {
			for (int i = 0; i < resources.length; i++) {
				map.put(resourcesNames[i], resources[i]);
			}
		}

		if (prop == null) {
			prop = new HashMap<String, String>();
		}

		if (variableName != null) {
			for (int i = 0; i < variableName.length; i++) {
				if (StringUtils.isNotEmpty(variableName[i])) {
					prop.put("init_variable_" + variableName[i], variableType[i] + ":" + variableValue[i]);
				}
			}
		}
		DeploymentDesc deploy = processService.deploy(getParameter("name"), getParameter("category"),
				getParameter("tenantId"), filename, file, prop, map);
		FileUtils.deleteQuietly(file);
		request.setAttribute("deploy", deploy);
		return SUCCESS;
	}

	/**
	 * 删除部署
	 * 
	 * @return
	 */
	public String deleteDeployment() {
		String[] ids = deploymentId.split(", ");
		boolean cascade = "true".equals(getParameter("cascade"));
		for (String id : ids) {
			processService.deleteDeployment(id, cascade);
		}
		return SUCCESS;
	}

	/**
	 * 部署的流程
	 * 
	 * @return
	 */
	public String processList() {
		List<ProcessDefinitionDesc> processDefinitions = null;
		if (StringUtils.isNotEmpty(deploymentId)) {
			processDefinitions = processService.listProcessDefinition(deploymentId);
		} else {
			processDefinitions = processService.listProcessDefinitions();
		}
		request.setAttribute("list", processDefinitions);
		return SUCCESS;
	}

	/**
	 * 查看流程
	 * 
	 * @return
	 */
	public String viewProcess() {
		processDefinition = processService.getProcessDefinition(processDefinitionId);
		final List<WorkflowActivityConfig> list = workflowActivityConfigService.list(processDefinitionId);
		Map<String, WorkflowActivityConfig> actors = new HashMap<String, WorkflowActivityConfig>();
		for (WorkflowActivityConfig workflowActor : list) {
			actors.put(workflowActor.getActivitiId(), workflowActor);
		}
		request.setAttribute("actors", actors);
		return SUCCESS;
	}

	/**
	 * @return
	 */
	public String viewImage() {
		InputStream processDiagram = processService.getProcessDiagram(processDefinitionId);
		processDefinition = processService.getProcessDefinition(processDefinitionId);
		response.setContentType("image/png");
		// response.setHeader("Content-Disposition", "attachment;filename=" +
		// processDefinition.getDiagramResourceName());
		ServletOutputStream out = null;
		try {
			out = response.getOutputStream();
			IOUtils.copy(processDiagram, out);
		} catch (IOException e) {
			log.error(e);
		} finally {
			IOUtils.closeQuietly(processDiagram);
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				log.error(e);
			}
		}
		return null;
	}

	/**
	 * @return
	 */
	public String activateProcessDefinition() {
		processService.activateProcessDefinitionById(processDefinitionId);
		return SUCCESS;
	}

	/**
	 * @return
	 */
	public String suspendProcessDefinition() {
		processService.suspendProcessDefinitionById(processDefinitionId);
		return SUCCESS;
	}

	/**
	 * @return the processService
	 */
	public ProcessService getProcessService() {
		return processService;
	}

	/**
	 * @param processService the processService to set
	 */
	public void setProcessService(ProcessService processService) {
		this.processService = processService;
	}

	/**
	 * @return the deploymentId
	 */
	public String getDeploymentId() {
		return deploymentId;
	}

	/**
	 * @param deploymentId the deploymentId to set
	 */
	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
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
	 * @return the processDefinition
	 */
	public ProcessDefinitionDesc getProcessDefinition() {
		return processDefinition;
	}

	/**
	 * @param processDefinition the processDefinition to set
	 */
	public void setProcessDefinition(ProcessDefinitionDesc processDefinition) {
		this.processDefinition = processDefinition;
	}

	/**
	 * @return the file
	 */
	public File getFile() {
		return file;
	}

	/**
	 * @param file the file to set
	 */
	public void setFile(File file) {
		this.file = file;
	}

	public void setFileFileName(String fileName) {
		this.filename = fileName;
	}

	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * @return the resources
	 */
	public File[] getResources() {
		return resources;
	}

	/**
	 * @param resources the resources to set
	 */
	public void setResources(File[] resources) {
		this.resources = resources;
	}

	/**
	 * @return the resourcesNames
	 */
	public String[] getResourcesNames() {
		return resourcesNames;
	}

	/**
	 * @param resourcesNames the resourcesNames to set
	 */
	public void setResourcesNames(String[] resourcesNames) {
		this.resourcesNames = resourcesNames;
	}

	public void setResourcesFileName(String[] fileName) {
		this.resourcesNames = fileName;
	}

	/**
	 * @return the prop
	 */
	public Map<String, String> getProp() {
		return prop;
	}

	/**
	 * @param prop the prop to set
	 */
	public void setProp(Map<String, String> prop) {
		this.prop = prop;
	}

	/**
	 * @return the variableName
	 */
	public String[] getVariableName() {
		return variableName;
	}

	/**
	 * @param variableName the variableName to set
	 */
	public void setVariableName(String[] variableName) {
		this.variableName = variableName;
	}

	/**
	 * @return the variableType
	 */
	public String[] getVariableType() {
		return variableType;
	}

	/**
	 * @param variableType the variableType to set
	 */
	public void setVariableType(String[] variableType) {
		this.variableType = variableType;
	}

	/**
	 * @return the variableValue
	 */
	public String[] getVariableValue() {
		return variableValue;
	}

	/**
	 * @param variableValue the variableValue to set
	 */
	public void setVariableValue(String[] variableValue) {
		this.variableValue = variableValue;
	}

	/**
	 * @return the workflowActivityConfigService
	 */
	public WorkflowActivityConfigService getWorkflowActivityConfigService() {
		return workflowActivityConfigService;
	}

	/**
	 * @param workflowActivityConfigService the workflowActivityConfigService to
	 *            set
	 */
	public void setWorkflowActivityConfigService(WorkflowActivityConfigService workflowActivityConfigService) {
		this.workflowActivityConfigService = workflowActivityConfigService;
	}

}
