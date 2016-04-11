/**
 * <p>
 * 
 * </p>
 * <p>
 * Copyright: Copyright (c) tonny 2015
 * </p>
 * @date 2015-1-29 上午10:01:06
 * @author tonny
 */
package works.tonny.apps.workflow.manage.impl;

import java.util.List;

import works.tonny.apps.EntityDAO;
import works.tonny.apps.support.ListSupport;
import works.tonny.apps.workflow.manage.Holiday;

/**
 * <p>
 * 
 * </p>
 * 
 * @author tonny
 * @version 1.0.0
 */
public interface HolidayDAO extends EntityDAO<Holiday> {
	/**
	 * 查询所有的假期配置
	 * 
	 * @return
	 * @author tonny
	 */
	@ListSupport()
	List<Holiday> list();

	/**
	 * 查询所有的假期配置
	 * 
	 * @return
	 * @author tonny
	 */
	@ListSupport(field = "year(t.date)", order = "date")
	List<Holiday> list(int year);
}
