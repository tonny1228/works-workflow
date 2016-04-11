/**
 * <p>
 * <p/>
 * </p>
 * <p>
 * Copyright: Copyright (c) tonny 2015
 * </p>
 *
 * @date 2015-1-26 下午4:25:58
 * @author tonny
 */
package works.tonny.apps.workflow.manage.impl;

import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TimerTask;
import java.util.TreeSet;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.llama.library.utils.Assert;
import org.llama.library.utils.CalendarUtil;
import org.llama.library.utils.DateUtils;
import org.llama.library.utils.TimerUtils;

import works.tonny.apps.core.Setting;
import works.tonny.apps.core.SettingService;
import works.tonny.apps.workflow.manage.Holiday;
import works.tonny.apps.workflow.manage.WorkflowSetting;
import works.tonny.apps.workflow.manage.WorkingDayService;

/**
 * <p>
 * <p/>
 * </p>
 *
 * @author tonny
 * @version 1.0.0
 */
public class WorkingDayServiceImpl implements WorkingDayService {
    /**
     *
     */
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    /**
     *
     */
    private static final String TIME_FORMAT = "HH:mm";

    /**
     *
     */
    private static final String TO = "-";

    /**
     *
     */
    private static final String ANY = "*";

    public static final String PATTERN = "8:30-12:00,13:00-17:30 * * 1-5";

    /**
     * 时分 日 月 星期(日一二三四五六0123456)
     */
    protected String workingDayPattern = PATTERN;// "8:30-12:00,13:00-17:30 1-5"

    protected TreeSet<String> includes = new TreeSet<String>();

    protected TreeSet<String> excludes = new TreeSet<String>();

    private WorkflowSetting workflowSetting;

    protected static final TreeSet<Integer> minuteSet = new TreeSet<Integer>();
    protected static final TreeSet<Integer> daySet = new TreeSet<Integer>();
    protected static final TreeSet<Integer> monthSet = new TreeSet<Integer>();
    protected static final TreeSet<Integer> weekdaySet = new TreeSet<Integer>();

    private HolidayDAO holidayDAO;

    private SettingService settingService;

    /**
     *
     */
    public WorkingDayServiceImpl() {
    }

    /**
     * 根据规则创建工作日信息
     *
     * @author tonny
     */
    public void buildCommonWorkingday() {
        Setting setting = getSettingService().getSetting(WorkflowSetting.class, WorkflowSetting.WORKING_DAY_PATTERN);
        if (setting != null) {
            workingDayPattern = setting.getStringValue();
        }
        final List<Holiday> list = holidayDAO.list();
        for (Holiday holiday : list) {
            if (holiday.getType() == 0) {
                excludes.add(holiday.getId());
            } else {
                includes.add(holiday.getId());
            }
        }

        minuteSet.clear();
        daySet.clear();
        monthSet.clear();
        weekdaySet.clear();

        StringTokenizer tokenizer1 = new StringTokenizer(workingDayPattern, " \t", false);
        int type = -1;
        while (tokenizer1.hasMoreTokens()) {
            type++;
            String expr = tokenizer1.nextToken().trim();
            StringTokenizer tokenizer2 = new StringTokenizer(expr, ",", false);
            while (tokenizer2.hasMoreTokens()) {
                String expr2 = tokenizer2.nextToken().trim();
                addData(type, expr2);
            }
        }
        CalendarUtil util = new CalendarUtil();
        util.addDays(1);
        util.setHour(0);
        util.setMinute(15);
        util.setSecond(0);
        TimerUtils.schedule(new TimerTask() {
            @Override
            public void run() {
                WorkingDayServiceImpl.this.buildCommonWorkingday();
            }
        }, util.dateTime());
    }

    /**
     * 创建规则时，添加工作日信息到各字段
     *
     * @param type  类型：0分钟 1 天 2 月 3 星期
     * @param expr2
     * @author tonny
     */
    private void addData(int type, String expr2) {
        if (type == 0) {
            addMinute(expr2);
        }
        if (type == 1) {
            addDay(expr2);
        }
        if (type == 2) {
            addMonth(expr2);
        }
        if (type == 3) {
            addWeekDay(expr2);
        }

    }

    /**
     * 解析星期，*代表任意周几，1-5代表星期1 2 3 4 5
     *
     * @param expr2
     * @author tonny
     */
    private void addWeekDay(String expr2) {
        if (ANY.equals(expr2)) {
            for (int i = 0; i < 7; i++) {
                weekdaySet.add(i);
            }
            return;
        }
        if (expr2.indexOf(TO) > 0) {
            int begin = NumberUtils.toInt(StringUtils.substringBefore(expr2, TO));
            int end = NumberUtils.toInt(StringUtils.substringAfter(expr2, TO));
            for (int i = begin; i < end + 1; i++) {
                weekdaySet.add(i);
            }
        } else {
            weekdaySet.add(NumberUtils.toInt(expr2));
        }
    }

    /**
     * 解析月，*代表任意月，1-5代表1 2 3 4 5月
     *
     * @param expr2
     * @author tonny
     */
    private void addMonth(String expr2) {
        if (ANY.equals(expr2)) {
            for (int i = 1; i < 13; i++) {
                monthSet.add(i);
            }
            return;
        }
        if (expr2.indexOf('-') > 0) {
            int begin = NumberUtils.toInt(StringUtils.substringBefore(expr2, TO));
            int end = NumberUtils.toInt(StringUtils.substringAfter(expr2, TO));
            for (int i = begin; i < end + 1; i++) {
                monthSet.add(i);
            }
        } else {
            monthSet.add(NumberUtils.toInt(expr2));
        }
    }

    /**
     * 解析天，*代表任意1-31天，1-5代表1 2 3 4 5号
     *
     * @param expr2
     * @author tonny
     */
    private void addDay(String expr2) {
        if (ANY.equals(expr2)) {
            for (int i = 1; i < 32; i++) {
                daySet.add(i);
            }
            return;
        }
        if (expr2.indexOf(TO) > 0) {
            int begin = NumberUtils.toInt(StringUtils.substringBefore(expr2, TO));
            int end = NumberUtils.toInt(StringUtils.substringAfter(expr2, TO));
            for (int i = begin; i < end + 1; i++) {
                daySet.add(i);
            }
        } else {
            daySet.add(NumberUtils.toInt(expr2));
        }
    }

    /**
     * 解析分钟，HH:mm格式，范围段，不包含下边界
     *
     * @param expr2
     * @author tonny
     */
    private void addMinute(String expr2) {
        String begin = StringUtils.substringBefore(expr2, TO);
        String end = StringUtils.substringAfter(expr2, TO);
        Date beginDate = DateUtils.toDate(begin, TIME_FORMAT);
        Date endDate = DateUtils.toDate(end, TIME_FORMAT);
        Date compareDate = DateUtils.toDate("00:00", TIME_FORMAT);
        if (endDate.after(beginDate)) {
            for (long i = (beginDate.getTime() - compareDate.getTime()) / 1000 / 60; i < (endDate.getTime() - compareDate
                    .getTime()) / 1000 / 60; i++) {
                minuteSet.add((int) i);
            }
        }
        if (endDate.before(beginDate)) {
            endDate.setTime(endDate.getTime() + 24 * 3600000);
            for (long i = (beginDate.getTime() - compareDate.getTime()) / 1000 / 60; i < (endDate.getTime() - compareDate
                    .getTime()) / 1000 / 60; i++) {
                minuteSet.add((int) i);
            }
        }
    }

    /**
     * 在当前时间下增加value个工作日
     *
     * @see works.tonny.apps.workflow.manage.WorkingDayService#addDays(java.util.Date, int)
     */
    @Override
    public Date addDays(Date date, int value) {
        CalendarUtil util = new CalendarUtil(date);
        Assert.isTrue(value >= 0, "只能往后查");

        if (!isWorkingTime(date)) {
            // 当前时间不是工作时间，获得开始时间为下一个工作时间
            date = nextWorkingTime(date);
            util.set(date);
        }

        int i = 0;
        while (i < value) {
            util.addDays(1);
            if (isWorkingDay(util.dateTime())) {
                i++;
            }
        }
        return util.dateTime();
    }

    /**
     * @see works.tonny.apps.workflow.manage.WorkingDayService#addHours(java.util.Date, int)
     */
    @Override
    public Date addHours(Date date, int hours) {
        CalendarUtil util = new CalendarUtil(date);
        Assert.isTrue(hours >= 0, "只能往后查");

        if (!isWorkingTime(date)) {
            // 当前时间不是工作时间，获得开始时间为工作时间
            date = nextWorkingTime(date);
            util.set(date);
        }

        int passedMinutes = 0;
        int begin = util.getHour() * 60 + util.getMinute();
        while (passedMinutes <= hours * 60) {
            util.addHours(1);
            if (isWorkingTime(util.dateTime())) {
                passedMinutes += 60;
                begin = util.getHour() * 60 + util.getMinute();
            } else {
                Integer lower = minuteSet.lower(util.getHour() * 60 + util.getMinute());
                if (lower == null) {
                    lower = minuteSet.lower(util.getHour() * 60 + util.getMinute() + 24 * 60) - 24 * 60;
                }
                passedMinutes += lower - begin + 1;
                date = nextWorkingTime(util.dateTime());
                util.set(date);
                begin = util.getHour() * 60 + util.getMinute();
            }
        }
        if (passedMinutes > hours * 60) {
            util.addMinutes(hours * 60 - passedMinutes);
        }
        return util.dateTime();
    }

    /**
     * @param date
     * @return
     * @author tonny
     */
    private Date nextWorkingTime(Date date) {
        CalendarUtil calendarUtil = new CalendarUtil(date);
        if (isWorkingDay(date)) {
            // 当前时间是工作日
            int passedMinutes = calendarUtil.getHour() * 60 + calendarUtil.getMinute();
            Integer workMinute = minuteSet.higher(passedMinutes);
            if (workMinute != null) {
                // 今天工作时间还没结束，设置时间为下一工作时间，并返回
                calendarUtil.setHour(workMinute / 60);
                calendarUtil.setMinute(workMinute % 60);
                calendarUtil.setSecond(0);
                return calendarUtil.dateTime();
            }
        }
        // 今天不是工作日，返回下个工作日的上班开始时间；今天工作时间结束了，返回下个工作日的工作开始时间
        Date next = nextWorkingDay(date);
        return next;
    }

    /**
     * 返回下一个工作日，时间是工作开始时间
     *
     * @param date
     * @return
     * @author tonny
     */
    private Date nextWorkingDay(Date date) {
        CalendarUtil calendarUtil = new CalendarUtil(date);
        int weekday = calendarUtil.getDayOfWeek() - 1;

        // 获取日期之后的第一个特殊工作日
        final String nextInclude = includes.higher(DateFormatUtils.format(date, DATE_FORMAT));
        Date include = null;
        if (nextInclude != null) {
            include = DateUtils.toDate(nextInclude, DATE_FORMAT);
        }

        // 获取日志之后的第一个特殊非工作日
        final String nextExclude = excludes.higher(DateFormatUtils.format(date, DATE_FORMAT));

        // 获取下一个上班的周几
        Integer nextWeekDay = weekdaySet.higher(weekday);
        if (nextWeekDay != null) {
            calendarUtil.addDays(nextWeekDay - weekday);
        } else {
            nextWeekDay = weekdaySet.first();
            calendarUtil.addDays(7 - weekday + nextWeekDay);
        }

        // 设定时间为当前工作开始时间
        if (calendarUtil.getHour() * 60 + calendarUtil.getMinute() != minuteSet.first()) {
            calendarUtil.setHour(minuteSet.first() / 60);
            calendarUtil.setMinute(minuteSet.first() % 60);
        }

        if (daySet.contains(calendarUtil.getDayOfMonth())
                && monthSet.contains(calendarUtil.getMonthInt())
                && ((nextExclude != null && !nextExclude.equals(DateFormatUtils.format(calendarUtil.dateTime(),
                DATE_FORMAT))) || nextExclude == null)) {
            // 是正常工作日，并且不是节假日
            if (include != null && include.before(calendarUtil.dateTime())) {
                // 非正常工作日更近，返回非正常工作日
                include.setTime(include.getTime() + minuteSet.first() * 60000);
                return include;
            }
            // 返回正常工作日
            return calendarUtil.dateTime();
        } else {
            // 继续查找下一个工作日
            return nextWorkingDay(calendarUtil.dateTime());
        }
    }

    /**
     * 当前上班，就是工作时间，上班到第二天凌晨的，第二天正常不上班的，第二天不是工作日。
     *
     * @param date
     * @return
     * @author tonny
     */
    public boolean isWorkingDay(Date date) {
        CalendarUtil calendarUtil = new CalendarUtil(date);
        final String format = DateFormatUtils.format(date, DATE_FORMAT);
        if (excludes.contains(format)) {
            return false;
        }
        if (monthSet.contains(calendarUtil.getMonthInt()) && daySet.contains(calendarUtil.getDayOfMonth())
                && weekdaySet.contains(calendarUtil.getDayOfWeek() - 1)) {
            return true;
        }
        if (includes.contains(format)) {
            return true;
        }
        return false;
    }

    public boolean isWorkingTime(Date dateTime) {
        CalendarUtil calendarUtil = new CalendarUtil(dateTime);
        String format = DateFormatUtils.format(dateTime, DATE_FORMAT);

        int passedMinutes = calendarUtil.getHour() * 60 + calendarUtil.getMinute();
        if (minuteSet.contains(passedMinutes)
                && ((monthSet.contains(calendarUtil.getMonthInt()) && daySet.contains(calendarUtil.getDayOfMonth()) && weekdaySet
                .contains(calendarUtil.getDayOfWeek() - 1)) || (includes.contains(format)))) {
            if (excludes.contains(format)) {
                return false;
            }
            return true;
        }
        calendarUtil.addDays(-1);
        passedMinutes += 24 * 60;
        format = DateFormatUtils.format(calendarUtil.dateTime(), DATE_FORMAT);
        if (minuteSet.contains(passedMinutes)
                && ((monthSet.contains(calendarUtil.getMonthInt()) && daySet.contains(calendarUtil.getDayOfMonth()) && weekdaySet
                .contains(calendarUtil.getDayOfWeek() - 1)) || (includes.contains(format)))) {
            if (excludes.contains(format)) {
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * @see works.tonny.apps.workflow.manage.WorkingDayService#holidays(int)
     */
    @Override
    public List<Holiday> holidays(int year) {
        return holidayDAO.list(year);
    }

    /**
     * @see works.tonny.apps.workflow.manage.WorkingDayService#saveHoliday(works.tonny.apps.workflow.manage.Holiday)
     */
    @Override
    public void saveHoliday(Holiday holiday) {
        holiday.setCreateDate(new Date());
        if (StringUtils.isEmpty(holiday.getId())) {
            holidayDAO.save(holiday);
        } else {
            holidayDAO.update(holiday);
        }
    }


    @Override
    public WorkflowSetting getWorkingdayPatternSetting() {
        if (workflowSetting == null) {
            final Setting setting = settingService.getSetting(WorkflowSetting.class,
                    WorkflowSetting.WORKING_DAY_PATTERN);
            if (setting == null) {
                workflowSetting = new WorkflowSetting(WorkflowSetting.WORKING_DAY_PATTERN, WorkflowSetting.WORKING_DAY_PATTERN, PATTERN);
            } else {
                workflowSetting = new WorkflowSetting(WorkflowSetting.WORKING_DAY_PATTERN, WorkflowSetting.WORKING_DAY_PATTERN, setting.getStringValue());
            }
        }

        return workflowSetting;
    }


    @Override
    public void setWorkingdayPatternSetting(String pattern) {
        this.workingDayPattern = pattern;
        getSettingService().setSetting(
                new WorkflowSetting(WorkflowSetting.WORKING_DAY_PATTERN, "工作日", pattern));
        this.workflowSetting.setStringValue(workingDayPattern);
    }

    /**
     * @see works.tonny.apps.workflow.manage.WorkingDayService#deleteHoliday(java.lang.String[])
     */
    @Override
    public void deleteHoliday(String... id) {
        holidayDAO.deleteAll(id);
    }

    /**
     * @return the workingDayPattern
     */
    public String getWorkingDayPattern() {
        return workingDayPattern;
    }

    /**
     * @param workingDayPattern the workingDayPattern to set
     */
    public void setWorkingDayPattern(String workingDayPattern) {
        this.workingDayPattern = workingDayPattern;
        if (workflowSetting != null) {
            workflowSetting.setValue(workingDayPattern);
        }
    }

    /**
     * @return the includes
     */
    public TreeSet<String> getIncludes() {
        return includes;
    }

    /**
     * @param includes the includes to set
     */
    public void setIncludes(TreeSet<String> includes) {
        this.includes = includes;
    }

    /**
     * @return the excludes
     */
    public TreeSet<String> getExcludes() {
        return excludes;
    }

    /**
     * @param excludes the excludes to set
     */
    public void setExcludes(TreeSet<String> excludes) {
        this.excludes = excludes;
    }

    /**
     * @return the holidayDAO
     */
    public HolidayDAO getHolidayDAO() {
        return holidayDAO;
    }

    /**
     * @param holidayDAO the holidayDAO to set
     */
    public void setHolidayDAO(HolidayDAO holidayDAO) {
        this.holidayDAO = holidayDAO;
    }

    /**
     * @return the settingService
     */
    public SettingService getSettingService() {
        return settingService;
    }

    /**
     * @param settingService the settingService to set
     */
    public void setSettingService(SettingService settingService) {
        this.settingService = settingService;
    }

}
