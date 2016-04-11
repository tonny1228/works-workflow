/**
 * <p>
 * <p/>
 * </p>
 * <p>
 * Copyright: Copyright (c) tonny 2015
 * </p>
 *
 * @date 2015-3-25 下午2:59:36
 * @author tonny
 */
package works.tonny.apps.workflow.web;

import java.util.List;

import org.llama.library.utils.CalendarUtil;

import works.tonny.apps.core.Setting;
import works.tonny.apps.core.SettingService;
import works.tonny.apps.user.AuthedAction;
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
public class WorkingDayAction extends AuthedAction {
    private WorkingDayService workingDayService;

    private int year;

    private Holiday holiday;

    private String id;

    private String workingDayPattern;

    public String list() {
        if (year == 0) {
            year = new CalendarUtil().getYear();
        }
        final List<Holiday> holidays = workingDayService.holidays(year);
        request.setAttribute("list", holidays);
        request.setAttribute("setting", workingDayService.getWorkingdayPatternSetting());
        return "list";
    }

    public String save() {
        workingDayService.saveHoliday(holiday);
        return "listredirect";
    }

    public String updateWorkingDayPattern() {
        workingDayService.setWorkingdayPatternSetting(workingDayPattern);
        return "listredirect";
    }

    public String delete() {
        final String[] split = id.split(", ");
        workingDayService.deleteHoliday(split);
        return "listredirect";
    }

    /**
     * @return the workingDayService
     */
    public WorkingDayService getWorkingDayService() {
        return workingDayService;
    }

    /**
     * @param workingDayService the workingDayService to set
     */
    public void setWorkingDayService(WorkingDayService workingDayService) {
        this.workingDayService = workingDayService;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return the holiday
     */
    public Holiday getHoliday() {
        return holiday;
    }

    /**
     * @param holiday the holiday to set
     */
    public void setHoliday(Holiday holiday) {
        this.holiday = holiday;
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
    }


}