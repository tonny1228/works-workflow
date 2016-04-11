/**
 * <p>
 * <p/>
 * </p>
 * <p>
 * Copyright: Copyright (c) tonny 2015
 * </p>
 *
 * @date 2015-1-26 下午3:37:54
 * @author tonny
 */
package works.tonny.apps.workflow.manage;

import java.util.Date;
import java.util.List;


/**
 * <p>
 * 工作日服务类
 * </p>
 *
 * @author tonny
 * @version 1.0.0
 */
public interface WorkingDayService {
    /**
     * 在当前时间的基础上增加工作时间后的下一工作时间
     *
     * @param date
     * @param field
     * @param value
     * @return
     * @author tonny
     */
    Date addDays(Date date, int days);

    Date addHours(Date date, int hours);

    /**
     * 假日列表
     *
     * @param year 查询的年份
     * @return
     * @author tonny
     */
    List<Holiday> holidays(int year);

    void saveHoliday(Holiday holiday);

    void deleteHoliday(String... id);


    WorkflowSetting getWorkingdayPatternSetting();

    void setWorkingdayPatternSetting(String pattern);


}