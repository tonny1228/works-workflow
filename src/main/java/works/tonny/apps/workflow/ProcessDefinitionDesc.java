/**
 * 
 */
package works.tonny.apps.workflow;

import java.util.List;
import java.util.Map;

import works.tonny.apps.Entity;

/**
 * @author 祥栋
 */
public class ProcessDefinitionDesc extends Entity {
	protected String name;
	protected String key;
	protected String version;
	protected String category;
	protected String deploymentId;
	protected String resourceName;
	protected String tenantId;
	protected String diagramResourceName;
	protected String state;
	protected String candidateUserIds;
	protected String candidateGroupIds;
	protected String entityClass;
	protected String entityIdGenerator;
	protected List<Activiti> activities;

	/**
	 * @return the entityIdGenerator
	 */
	public String getEntityIdGenerator() {
		return entityIdGenerator;
	}

	/**
	 * @param entityIdGenerator the entityIdGenerator to set
	 */
	public void setEntityIdGenerator(String entityIdGenerator) {
		this.entityIdGenerator = entityIdGenerator;
	}

	protected String viewForm;
	protected Map<String, Object> initVariable;
	protected String startFormHandeler;

	/**
	 * 
	 */
	public ProcessDefinitionDesc() {
	}

	/**
	 * @param key
	 * @param version
	 * @param category
	 * @param deploymentId
	 * @param resourceName
	 * @param tenantId
	 * @param diagramResourceName
	 * @param state
	 * @param candidateUserIds
	 * @param candidateGroupIds
	 */
	public ProcessDefinitionDesc(String id, String name, String key, String version, String category,
			String deploymentId, String resourceName, String tenantId, String diagramResourceName, String state) {
		this.id = id;
		this.name = name;
		this.key = key;
		this.version = version;
		this.category = category;
		this.deploymentId = deploymentId;
		this.resourceName = resourceName;
		this.tenantId = tenantId;
		this.diagramResourceName = diagramResourceName;
		this.state = state;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
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
	 * @return the resourceName
	 */
	public String getResourceName() {
		return resourceName;
	}

	/**
	 * @param resourceName the resourceName to set
	 */
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	/**
	 * @return the tenantId
	 */
	public String getTenantId() {
		return tenantId;
	}

	/**
	 * @param tenantId the tenantId to set
	 */
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	/**
	 * @return the diagramResourceName
	 */
	public String getDiagramResourceName() {
		return diagramResourceName;
	}

	/**
	 * @param diagramResourceName the diagramResourceName to set
	 */
	public void setDiagramResourceName(String diagramResourceName) {
		this.diagramResourceName = diagramResourceName;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the candidateUserIds
	 */
	public String getCandidateUserIds() {
		return candidateUserIds;
	}

	/**
	 * @param candidateUserIds the candidateUserIds to set
	 */
	public void setCandidateUserIds(String candidateUserIds) {
		this.candidateUserIds = candidateUserIds;
	}

	/**
	 * @return the candidateGroupIds
	 */
	public String getCandidateGroupIds() {
		return candidateGroupIds;
	}

	/**
	 * @param candidateGroupIds the candidateGroupIds to set
	 */
	public void setCandidateGroupIds(String candidateGroupIds) {
		this.candidateGroupIds = candidateGroupIds;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the entityClass
	 */
	public String getEntityClass() {
		return entityClass;
	}

	/**
	 * @param entityClass the entityClass to set
	 */
	public void setEntityClass(String entityClass) {
		this.entityClass = entityClass;
	}

	/**
	 * @return the viewForm
	 */
	public String getViewForm() {
		return viewForm;
	}

	/**
	 * @param viewForm the viewForm to set
	 */
	public void setViewForm(String viewForm) {
		this.viewForm = viewForm;
	}

	/**
	 * @return the initVariable
	 */
	public Map<String, Object> getInitVariable() {
		return initVariable;
	}

	/**
	 * @param initVariable the initVariable to set
	 */
	public void setInitVariable(Map<String, Object> initVariable) {
		this.initVariable = initVariable;
	}

	/**
	 * @return the startFormHandeler
	 */
	public String getStartFormHandeler() {
		return startFormHandeler;
	}

	/**
	 * @param startFormHandeler the startFormHandeler to set
	 */
	public void setStartFormHandeler(String startFormHandeler) {
		this.startFormHandeler = startFormHandeler;
	}

	/**
	 * @return the activities
	 */
	public List<Activiti> getActivities() {
		return activities;
	}

	/**
	 * @param activities the activities to set
	 */
	public void setActivities(List<Activiti> activities) {
		this.activities = activities;
	}

}
