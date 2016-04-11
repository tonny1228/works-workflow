/**
 * <p>
 * 
 * </p>
 * <p>
 * Copyright: Copyright (c) tonny 2015
 * </p>
 * @date 2015-1-26 下午3:35:24
 * @author tonny
 */
package works.tonny.apps.workflow;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Assert;
import org.junit.Test;
import org.llama.library.utils.CalendarUtil;

import works.tonny.apps.workflow.manage.impl.WorkingDayServiceImpl;

/**
 * <p>
 * 
 * </p>
 * 
 * @author tonny
 * @version 1.0.0
 */
public class WorkingDay {

	@Test
	public void test() {
		CalendarUtil util = new CalendarUtil(2015, 1, 1);
		WorkingDayServiceImpl service = new WorkingDayServiceImpl();
		for (int i = 0; i < 31; i++) {
			System.out.println(util.getDate() + ":" + service.isWorkingDay(util.dateTime()));
			util.addDays(1);
		}
	}

	@Test
	public void testTime() {
		WorkingDayServiceImpl service = new WorkingDayServiceImpl();
		CalendarUtil util = new CalendarUtil();
		util.reset(2015, 1, 4, 9, 0, 0);
		service.isWorkingTime(util.dateTime());
		util.reset(2015, 1, 2, 8, 0, 0);
		service.isWorkingTime(util.dateTime());
		for (int i = 0; i < 24; i++) {
			System.out.println(util.getDate() + " " + util.getTime() + ":" + service.isWorkingTime(util.dateTime()));
			util.addHours(3);
		}
	}

	@Test
	public void testAddDay() {
		WorkingDayServiceImpl service = new WorkingDayServiceImpl();
		service.getIncludes().add("2015-01-04");
		service.getExcludes().add("2015-01-01");
		service.getExcludes().add("2015-01-02");
		service.buildCommonWorkingday();
		CalendarUtil util = new CalendarUtil();
		util.reset(2015, 1, 1, 9, 0, 0);
		System.out.println(service.addDays(util.dateTime(), 5));
		Assert.assertEquals(DateFormatUtils.format(service.addDays(util.dateTime(), 5), "yyyy-MM-dd HH:mm:ss"),
				"2015-01-09 08:30:00");
		Assert.assertEquals(DateFormatUtils.format(service.addDays(util.dateTime(), 6), "yyyy-MM-dd HH:mm:ss"),
				"2015-01-12 08:30:00");
		util.reset(2015, 1, 4, 9, 0, 0);
		Assert.assertEquals(DateFormatUtils.format(service.addDays(util.dateTime(), 6), "yyyy-MM-dd HH:mm:ss"),
				"2015-01-12 09:00:00");
		Assert.assertEquals(DateFormatUtils.format(service.addDays(util.dateTime(), 11), "yyyy-MM-dd HH:mm:ss"),
				"2015-01-19 09:00:00");
		util.reset(2015, 1, 8, 18, 0, 0);
		Assert.assertEquals(DateFormatUtils.format(service.addDays(util.dateTime(), 5), "yyyy-MM-dd HH:mm:ss"),
				"2015-01-15 22:00:00");
		Assert.assertEquals(DateFormatUtils.format(service.addDays(util.dateTime(), 12), "yyyy-MM-dd HH:mm:ss"),
				"2015-01-26 22:00:00");
		util.reset(2015, 1, 10, 6, 0, 0);
		Assert.assertEquals(DateFormatUtils.format(service.addDays(util.dateTime(), 3), "yyyy-MM-dd HH:mm:ss"),
				"2015-01-15 08:30:00");

	}

	@Test
	public void testAddHour() {
		WorkingDayServiceImpl service = new WorkingDayServiceImpl();
		service.getIncludes().add("2015-01-04");
		service.getExcludes().add("2015-01-01");
		service.getExcludes().add("2015-01-02");
		service.buildCommonWorkingday();
		CalendarUtil util = new CalendarUtil();
		util.reset(2015, 1, 3, 9, 0, 0);
		Assert.assertEquals(DateFormatUtils.format(service.addHours(util.dateTime(), 6), "yyyy-MM-dd HH:mm:ss"),
				"2015-01-04 15:30:00");
		Assert.assertEquals(DateFormatUtils.format(service.addHours(util.dateTime(), 9), "yyyy-MM-dd HH:mm:ss"),
				"2015-01-04 23:00:00");
		Assert.assertEquals(DateFormatUtils.format(service.addHours(util.dateTime(), 15), "yyyy-MM-dd HH:mm:ss"),
				"2015-01-05 09:30:00");

		util.reset(2015, 1, 9, 11, 0, 0);
		Assert.assertEquals(DateFormatUtils.format(service.addHours(util.dateTime(), 6), "yyyy-MM-dd HH:mm:ss"),
				"2015-01-09 22:30:00");
		Assert.assertEquals(DateFormatUtils.format(service.addHours(util.dateTime(), 12), "yyyy-MM-dd HH:mm:ss"),
				"2015-01-12 09:00:00");
		Assert.assertEquals(DateFormatUtils.format(service.addHours(util.dateTime(), 28), "yyyy-MM-dd HH:mm:ss"),
				"2015-01-13 11:00:00");
	}
}
