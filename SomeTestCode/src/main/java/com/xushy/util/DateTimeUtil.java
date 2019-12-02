package com.xushy.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTimeUtil {
    public static final String DATE_WITHOUT_DASH = "yyyyMMdd";
    public static final String TIME_WITHOUT_DASH = "HHmmss";
    public static final String DATE_WITH_DASH = "yyyy-MM-dd";
    public static final String DATETIME_WITH_MILLISECOND_STRING = "yyyyMMddHHmmssSSS";
    public static final String YEAR = "yyyy";
    public static final String MONTH_DAY = "MMdd";
    public static final String DATETIME_WITH_DASH = "yyyy-MM-dd HH:mm:ss";
    public static final String MONTH = "MM";
    public static final String YEAR_START_DAY = "0101";
    public static final String YEAR_END_DAY = "1231";

    /**
     * @param date
     * @param 需要加上的天数
     *            ,可为负数
     * @return
     */
    public static Date addDay(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return new Date(calendar.getTimeInMillis());
    }

    /**
     * @param dateStr
     *            :形如20181227格式的日期
     * @return 2018-12-27
     */
    public static Date covertStr2Date(String dateStr) {
        // if (StringUtils.isBlank(dateStr)) {
        // return null;
        // }
        StringBuffer dateStringTemp = new StringBuffer(dateStr);
        dateStringTemp.insert(4, '-').insert(7, '-');
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_WITH_DASH);
        Date sqlDate = null;
        try {
            java.util.Date date = sdf.parse(dateStringTemp.toString());
            sqlDate = new Date(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sqlDate;
    }

    /**
     * @return 得到当前时间yyyyMMdd形式的字符串
     */
    public static String getDateStr() {
        return getDateTimeStr(DATE_WITHOUT_DASH);
    }

    /**
     * @return 得到当前时间HHmmss形式的字符串
     */
    public static String getTimeStr() {
        return getDateTimeStr(TIME_WITHOUT_DASH);
    }

    /**
     * @param year
     * @param month
     * @return 得到某年某月的月最大天数
     */
    public static int getDaysOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * @param year
     * @return 得到某年的天数
     */
    public static int getDaysOfYear(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        return calendar.getActualMaximum(Calendar.YEAR);
    }

    /**
     * @param date
     * @return 得到某年的天数
     */
    public static int getDaysOfYear(java.util.Date date) {
        int year = getYear(date);
        return getDaysOfYear(year);
    }

    /**
     * @param formatter
     * @return 当前时间的转换成指定格式
     */
    public static String getDateTimeStr(String formatter) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatter);
        return sdf.format(new java.util.Date());
    }

    /**
     * @param date
     * @param formatter
     * @return 当前传入时间的转换成指定格式
     */
    public static String getDateTimeStr(java.util.Date date, String formatter) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatter);
        return sdf.format(date);
    }

    /**
     * @param date
     * @return 得到年份
     */
    private static int getYear(java.util.Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * @param year
     * @return 得到一年的周数，不满一周按一周计算，只有当闰年(366天)且当年第一天为周日时才有可能为54周，其余均为53周
     */
    public static int getWeeksOfYear(int year) {
        Calendar calendar = Calendar.getInstance();
        int days = getDaysOfYear(year);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        int dayOfWeek = getDayOfWeek(calendar.getTime());
        if (days == 366 && dayOfWeek == 7) {
            return 54;
        }
        return 53;
    }

    /**
     * @description Calendar 日历类中星期的枚举，周日=1，周一=2，以此类推
     * @param date
     * @return calendar返回的星期枚举做减1处理，周日特殊处理
     */
    public static int getDayOfWeek(java.util.Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int ret = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return ret == 0 ? 7 : ret;
    }

    /**
     * @param date
     * @return 得到日期所在的季度
     */
    public static int getQuarter(java.util.Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(MONTH_DAY);
        String monthDayStr = sdf.format(date);
        Integer monthDayInt;
        if (monthDayStr.startsWith("0")) {
            monthDayInt = Integer.parseInt(monthDayStr.substring(1));
        } else {
            monthDayInt = Integer.parseInt(monthDayStr);
        }
        if (101 <= monthDayInt && monthDayInt <= 331) {
            return 1;
        } else if (401 <= monthDayInt && monthDayInt <= 630) {
            return 2;
        } else if (701 <= monthDayInt && monthDayInt <= 930) {
            return 3;
        } else if (1001 <= monthDayInt && monthDayInt <= 1231) {
            return 4;
        } else {
            throw new IllegalArgumentException("日期不合法,日期:" + date);
        }
    }

    /**
     * @param dateStart
     * @param dateEnd
     * @return 日期之间相差的天数
     */
    public static int getDaysBetweenDate(java.util.Date dateStart, java.util.Date dateEnd) {
        long time = Math.abs(dateEnd.getTime() - dateStart.getTime());
        return (int)(time / (1000 * 3600 * 24L));
    }

    /**
     * @param year
     * @param week
     * @return 得到某一周的日期
     */
    public static String getDateByWeek(int year, int week) {
        int max = getWeeksOfYear(year);
        if (week > max || week <= 0) {
            throw new IllegalArgumentException("周数超出当年最大值或小于1");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.WEEK_OF_YEAR, week);
        String start = "";
        String end = "";
        if (week == 1) {
            start = year + YEAR_START_DAY;
            calendar.set(Calendar.MONTH, 0);
            calendar.set(Calendar.DATE, 1);
            while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                calendar.add(Calendar.DATE, 1);
            }
            end = getDateTimeStr(calendar.getTime(), DATE_WITHOUT_DASH);
        }
        if (week != 1 && week != max) {
            calendar.setFirstDayOfWeek(Calendar.MONDAY);
            calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
            start = getDateTimeStr(calendar.getTime(), DATE_WITHOUT_DASH);
            calendar.add(Calendar.DAY_OF_WEEK, 6);
            end = getDateTimeStr(calendar.getTime(), DATE_WITHOUT_DASH);
        }
        if (week == max) {
            calendar.setFirstDayOfWeek(Calendar.MONDAY);
            calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
            start = getDateTimeStr(calendar.getTime(), DATE_WITHOUT_DASH);
            end = year + YEAR_END_DAY;
        }
        return start + "@" + end;
    }
}
