/**
 * <p>
 * 
 * </p>
 * <p>
 * Copyright: Copyright (c) tonny 2015
 * </p>
 * @date 2015-1-20 下午4:26:42
 * @author tonny
 */
package works.tonny.apps.workflow.manage;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import works.tonny.apps.Entity;

/**
 * <p>
 * 
 * </p>
 * 
 * @author tonny
 * @version 1.0.0
 */
@javax.persistence.Entity
@Table(name = "wf_cfg_actor")
public class WorkflowActor extends Entity {
	private String departmentId;

	private String departmentName;

	private String positionId;

	private String positionName;

	private String roleId;

	private String roleName;

	private String username;

	private String name;

	private WorkflowActivityConfig activitiConfig;

	/**
	 * @see works.tonny.apps.Entity#getId()
	 */
	@Override
	@Id
	@GeneratedValue(generator = "idGenerator")
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@Column(length = 50)
	public String getId() {
		return super.getId();
	}

	/**
	 * @return the departmentId
	 */
	@Column(name = "department_id", length = 50)
	public String getDepartmentId() {
		return departmentId;
	}

	/**
	 * @param departmentId the departmentId to set
	 */
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * @return the departmentName
	 */
	@Column(name = "department_name", length = 50)
	public String getDepartmentName() {
		return departmentName;
	}

	/**
	 * @param departmentName the departmentName to set
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	/**
	 * @return the positionId
	 */
	@Column(name = "position_id", length = 50)
	public String getPositionId() {
		return positionId;
	}

	/**
	 * @param positionId the positionId to set
	 */
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	/**
	 * @return the positionName
	 */
	@Column(name = "position_name", length = 50)
	public String getPositionName() {
		return positionName;
	}

	/**
	 * @param positionName the positionName to set
	 */
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	/**
	 * @return the roleId
	 */
	@Column(name = "role_id", length = 50)
	public String getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the roleName
	 */
	@Column(name = "role_name", length = 50)
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * @return the username
	 */
	@Column(name = "user_loginname", length = 500)
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the name
	 */
	@Column(name = "user_name", length = 500)
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
	 * @return the activitiConfig
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "config_id")
	public WorkflowActivityConfig getActivitiConfig() {
		return activitiConfig;
	}

	/**
	 * @param activitiConfig the activitiConfig to set
	 */
	public void setActivitiConfig(WorkflowActivityConfig activitiConfig) {
		this.activitiConfig = activitiConfig;
	}

}