/**
 * 
 */
package works.tonny.apps.workflow;

import java.util.Date;
import java.util.Map;

import works.tonny.apps.Entity;

/**
 * @author 祥栋
 */
public class DeploymentDesc extends Entity {
	protected String name;
	protected String category;
	protected String tenantId;
	protected Date deploymentTime;
	protected Map<String, String> properties;

	/**
	 * 
	 */
	public DeploymentDesc() {
	}

	/**
	 * @param name
	 * @param category
	 * @param tenantId
	 * @param deploymentTime
	 */
	public DeploymentDesc(String id, String name, String category, String tenantId, Date deploymentTime) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.tenantId = tenantId;
		this.deploymentTime = deploymentTime;
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
	 * @return the deploymentTime
	 */
	public Date getDeploymentTime() {
		return deploymentTime;
	}

	/**
	 * @param deploymentTime the deploymentTime to set
	 */
	public void setDeploymentTime(Date deploymentTime) {
		this.deploymentTime = deploymentTime;
	}

	/**
	 * @return the properties
	 */
	public Map<String, String> getProperties() {
		return properties;
	}

	/**
	 * @param properties the properties to set
	 */
	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}
}
