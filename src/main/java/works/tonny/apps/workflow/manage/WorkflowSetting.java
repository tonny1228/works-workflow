/**
 * <p>
 * 
 * </p>
 * <p>
 * Copyright: Copyright (c) tonny 2015
 * </p>
 * @date 2015-3-26 下午4:20:51
 * @author tonny
 */
package works.tonny.apps.workflow.manage;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import works.tonny.apps.core.Setting;

/**
 * <p>
 * 
 * </p>
 * 
 * @author tonny
 * @version 1.0.0
 */
@Entity
@DiscriminatorValue("workflow")
public class WorkflowSetting extends Setting {

	/**
	 * 
	 */
	public static final String WORKING_DAY_PATTERN = "workingDayPattern";

	/**
	 * 
	 */
	public WorkflowSetting() {
	}

	/**
	 * @param key
	 * @param name
	 * @param objectValue
	 */
	public WorkflowSetting(String key, String name, Object objectValue) {
		super(key, name, objectValue);
	}

}
