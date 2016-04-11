/**
 * <p>
 * 
 * </p>
 * <p>
 * Copyright: Copyright (c) tonny 2015
 * </p>
 * @date 2015-1-20 下午3:27:38
 * @author tonny
 */
package works.tonny.apps.workflow;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 * 
 * @author tonny
 * @version 1.0.0
 */
public class Activiti implements Serializable {
	private String id;
	
	private String name;
	
	private String type;
	
	private String desc;
	
	

	/**
	 * @param id
	 * @param name
	 * @param type
	 * @param desc
	 */
	public Activiti(String id, String name, String type, String desc) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.desc = desc;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
