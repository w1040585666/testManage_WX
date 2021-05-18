package com.hw.example.utils.util;

import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 时间操作定义类
 * @author wang xin.
 * @version 1.0
 * @date 2019/7/13 15:23.
 * @Copyright：2018 汉王智远科技有限公司 All rights reserved.
 */
public class DateUtils extends PropertyEditorSupport {
    
    public static final String DATE_FORMAT_HMS = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_ZZZ = "yyyy-MM-dd 00:00:00";
    public static final String DATE_FORMAT_LAST_SECOND = "yyyy-MM-dd 23:59:59";
    public static final String DATE_FORMAT_HM  = "yyyy-MM-dd HH:mm";
    public static final String DATE_FORMAT_YMD = "yyyy-MM-dd";
    public static final String DATE_FORMAT_YMD_STRING = "yyyyMMdd";
    public static final String DATE_FORMAT_YYMD = "yy-MM-dd";
    public static final String DATE_FORMAT_YM = "yyyy-MM";
    public static final String DATE_FORMAT_Y = "yyyy";
    public static final String DATE_FORMAT_TIME_HMS = "HH:mm:ss";
    public static final String DATE_FORMAT_TIME_HM = "HH:mm";
	
    /**传递时间的字符串长度为10*/
    private static final int NUMBER_TEN = 10;
    /**传递时间的字符串长度为19*/
    private static final int NUMBER_NINTEEN = 19;
	
    /**指定模式的时间格式*/
    public static SimpleDateFormat getFormat(String format) {
        return new SimpleDateFormat(format);
    }

    /**
     * 当前日期
     * @return 系统当前时间
     */
    public static Date getDate() {
        /**设置为东八区*/
        TimeZone time = TimeZone.getTimeZone("GMT+8");
        /**设置时区*/
        TimeZone.setDefault(time);
        /**获取实例*/
        Calendar calendar = Calendar.getInstance(time);
        /**获取Date对象*/
        Date date = calendar.getTime();
        return date;
    }

    /**
     * 将日期转换为一定格式的字符串（参数date为null时，默认当前时间）
     * @param date 日期
     * @param format 日期格式
     * @return 字符串
     */
    public static String date2Str(Date date, String format) {
        if (null == date) {
        	date = getDate();
        }
        return getFormat(format).format(date);
    }

    /**
     * 将日期字符串转换为一定格式的字符串
     * @param dateString 日期字符串
     * @param format 日期格式
     * @return 字符串
     */
    public static String str2Str(String dateString, String format) {
        Date date = null;
        SimpleDateFormat sdf = getFormat(format);
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sdf.format(date);
    }

    /**
     * 将时间戳转换为一定格式的字符串（参数time为null时，默认当前时间）
     * @param time 时间戳
     * @return 字符串
     */
    public static String timestamp2Str(Timestamp time, String format) {
        Date date = null;
        if (null != time) {
            date = getDate(time.getTime());
        }
        return date2Str(date, format);
    }

    /**
     * 将指定日历时间转换为特定格式的字符串（参数cal为null时，默认当前时间）
     * @param cal 指定的日期
     * @return 字符串
     */
    public static String cal2Str(Calendar cal, String format) {
        return getFormat(format).format(cal.getTime());
    }

    /**
     * 指定毫秒数转换为特定格式的字符串（参数cal为null时，默认当前时间）
     * @param millis 指定的毫秒数
     * @return 字符串
     */
    public static String long2Str(long millis, String format) {
        return getFormat(format).format(new Date(millis));
    }
    /* 返回类型为String的方法--end-- */

    /* 返回类型为Date的方法--start-- */

    /**
     * 指定毫秒数表示的日期
     * @param millis 毫秒数
     * @return 指定毫秒数表示的日期
     */
    public static Date getDate(long millis) {
        return new Date(millis);
    }

    /**
     * 按照一定的格式将字符串转换时间（Date）类型
     * @param str 字符串
     * @return 特定时间格式的时间
     */
    public static Date str2Date(String str, String format) {
        if (null == str || "".equals(str)) {
            return null;
        }
        Date date = null;
        try {
            date = getFormat(format).parse(str);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    /* 返回类型为Date的方法--end-- */

    /* 返回类型为Timestamp的方法--start-- */
    /**
     * 指定日期的时间戳（参数date为null时，默认当前时间）
     * @param date 指定日期
     * @return 指定日期的时间戳
     */
    public static Timestamp date2Timestamp(Date date) {
    	if (null == date) {
        	date = getDate();
        }
        return new Timestamp(date.getTime());
    }

    /**
     * 根据指定的格式将字符串转换成时间戳  如输入：2003-11-19 11:20:20将按照这个转成时间
     * @param dateString 将要转换的原始字符窜
     * @param format 转换的匹配格式
     * @return 转换后的时间戳或null
     */
    public static Timestamp str2Timestamp(String dateString, String format) {
        Date date = str2Date(dateString, format);
        if (date != null) {
        	return getTimestamp(date.getTime());
		} else {
			return null;
		}
    }

    /**
     * 以数字字符形式表示的时间转换成时间戳
     * @param time 毫秒数
     * @return 时间戳
     */
    public static Timestamp getTimestamp(String time) {
        return new Timestamp(Long.parseLong(time));
    }

    /**
     * 指定毫秒数的时间戳
     * @param millis 毫秒数
     * @return 指定毫秒数的时间戳
     */
    public static Timestamp getTimestamp(long millis) {
        return new Timestamp(millis);
    }

    /**
     * 指定日历的时间戳
     * @param cal 指定日历
     * @return 指定日历的时间戳
     */
    public static Timestamp getCalendarTimestamp(Calendar cal) {
        return new Timestamp(cal.getTime().getTime());
    }
    /* 返回类型为Timestamp的方法--end-- */

    /* 返回类型为long的方法--start-- */
    /**
     * 系统时间的毫秒数
     * @return 系统时间的毫秒数
     */
    public static long getMillis() {
        return getDate().getTime();
    }

    /**
     * 指定日历的毫秒数
     * @param cal 指定日历
     * @return 指定日历的毫秒数
     */
    public static long getMillis(Calendar cal) {
        return cal.getTime().getTime();
    }

    /**
     * 指定日期的毫秒数
     * @param date 指定日期
     * @return 指定日期的毫秒数
     */
    public static long getMillis(Date date) {
        return date.getTime();
    }

    /**
     * 指定时间戳的毫秒数
     * @param ts 指定时间戳
     * @return 指定时间戳的毫秒数
     */
    public static long getMillis(Timestamp ts) {
        return ts.getTime();
    }
    /* 返回类型为long的方法--end-- */

    /* 返回类型为Calendar的方法--start-- */
    /**
     * 当前日历，这里用中国时间表示
     * @return 以当地时区表示的系统当前日历
     */
    public static Calendar getCalendar() {
        TimeZone time = TimeZone.getTimeZone("GMT+8"); //设置为东八区
        TimeZone.setDefault(time);
        return Calendar.getInstance(time);
    }

    /**
     * 指定毫秒数表示的日历
     * @param millis 毫秒数
     * @return 指定毫秒数表示的日历
     */
    public static Calendar getCalendar(long millis) {
        Calendar cal = getCalendar();
        cal.setTime(getDate(millis));
        return cal;
    }

    /**
     * 根据指定的格式将字符串转换成Calendar
     * @param dateString 将要转换的原始字符串
     * @param format 转换的匹配格式
     * @return 如果转换成功则返回转换后的日期
     */
    public static Calendar toCalendar(String dateString, String format){
        Date date = str2Date(dateString, format);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }
    /* 返回类型为Calendar的方法--end-- */

    /**
     * @Description 将日期字符串加上特定天数，并转换成特点格式
     * @param dateString 日期字符串
     * @param format 日期格式
     * @param amount 天数
     * @return
     */
    public static String formatAddDate(String dateString, String format, int amount){
        Calendar cal;
        cal = toCalendar(dateString, format);
        cal.add(Calendar.DATE, amount);
        return cal2Str(cal, format);
    }

    /**
     * @Description 获取当前时间<br>
     * @return 时间格式：如20150730
     * @author tanghan barbadosliy@163.com
     * @date 2015年7月30日
     * @see
     */
    public static String getCurrentDate() {
        Date dt = new Date();
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        String nowTime = df.format(dt);
        return nowTime;
    }

    /**
     * 日期转时间
     * @param str 时间字符串
     * @return
     */
    public static Date strToDate(String str) {
    	if(StringUtils.isEmpty(str)){
    		return null;
    	}

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 日期转时间
     * @param str 时间字符串yyyyMMdd
     * @return
     */
    public static Date strToDateNo_(String str) {
        if(StringUtils.isEmpty(str)){
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 日期转时间,只有年的时候
     * @param str 时间字符串
     * @return
     */
    public static Date StrToDateYear(String str) {
    	if(StringUtils.isEmpty(str)){
    		return null;
    	}

        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 时间区间是否包含当前时间
     * @Method: isBetween
     * @param date 当前时间
     * @param start 开始时间
     * @param end 结果时间
     * @author hubing
     * @date 2017年7月12日
     */
    public static boolean isBetween(Date date, Date start, Date end) {
        String dateStr = DateUtils.date2Str(date, DATE_FORMAT_TIME_HMS);
        String startStr = DateUtils.date2Str(start, DATE_FORMAT_TIME_HMS);
        String endStr = DateUtils.date2Str(end, DATE_FORMAT_TIME_HMS);

        date = DateUtils.str2Date(dateStr, DATE_FORMAT_TIME_HMS);
        start = DateUtils.str2Date(startStr, DATE_FORMAT_TIME_HMS);
        end = DateUtils.str2Date(endStr, DATE_FORMAT_TIME_HMS);

        return start.before(date) && end.after(date);
    }

    /**
     * 时间区间是否不包含当前时间
     * @Method: isBetween
     * @param date 当前时间
     * @param start 开始时间  HH:mm:ss格式
     * @param end 结果时间  HH:mm:ss格式
     * @author hubing
     * @date 2017年7月12日
     */
    public static boolean isNotBetween(Date date, Date start, Date end) {
        String dateStr = DateUtils.date2Str(date, DATE_FORMAT_TIME_HMS);
        String startStr = DateUtils.date2Str(start, DATE_FORMAT_TIME_HMS);
        String endStr = DateUtils.date2Str(end, DATE_FORMAT_TIME_HMS);

        Date dateTemp = DateUtils.str2Date(dateStr, DATE_FORMAT_TIME_HMS);
        Date startTemp = DateUtils.str2Date(startStr, DATE_FORMAT_TIME_HMS);
        Date endTemp = DateUtils.str2Date(endStr, DATE_FORMAT_TIME_HMS);

        return !(startTemp.before(dateTemp) && endTemp.after(dateTemp));
    }

    /**
     * 将HH:mm:ss格式日期转换为当前日期
     * @Method: parseHHMMSSToDate
     * @param date HH:mm:ss格式日期
     * @author hubing
     * @date 2017年7月13日
     */
    public static Date parseHHMMSSToDate(Date date){
        Calendar dateCal = Calendar.getInstance();
        dateCal.setTime(date);

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY, dateCal.get(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, dateCal.get(Calendar.MINUTE));
        cal.set(Calendar.SECOND, dateCal.get(Calendar.SECOND));

        return cal.getTime();
    }

    /**
     * 比较HH:mm:ss格式日期大小
     * @Method: compareHHMMSSDate
     * @return boolean true
     * @author hubing
     * @date 2017年7月13日
     */
    public static boolean compareHHMMSSDate(Date start, Date end) {
        String startStr = DateUtils.date2Str(start, DATE_FORMAT_TIME_HMS);
        String endStr = DateUtils.date2Str(end, DATE_FORMAT_TIME_HMS);

        Date startTemp = DateUtils.str2Date(startStr, DATE_FORMAT_TIME_HMS);
        Date endTemp = DateUtils.str2Date(endStr, DATE_FORMAT_TIME_HMS);

        return startTemp.after(endTemp);
    }

    /**
     * 日期否在昨天、今天、明天之间
     * @Method: beforeAndAfter
     * @param date 时间
     * @author hubing
     * @date 2017年7月15日
     */
    public static boolean isReportValidTime(Date date) {
        String dateStr = DateUtils.date2Str(date, DATE_FORMAT_YMD);
        Date afterDate = getAfterDate(new Date(), -2);

        String beforeStr = DateUtils.date2Str(afterDate, DATE_FORMAT_YMD);
        String afterStr = DateUtils.date2Str(new Date(), DATE_FORMAT_YMD);

        return dateStr.compareTo(beforeStr) >= 0 && dateStr.compareTo(afterStr) <= 0;
    }

    /**
     * 获取几天之后的日期
     * @Method: getAfterDate
     * @param date 日期
     * @param days 天数
     * @author hubing
     * @date 2017年7月15日
     */
    public static Date getAfterDate(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, days);

        return cal.getTime();
    }

    public static  List<String> getBetweenDays(String stime,String etime,String format){
        SimpleDateFormat df=new SimpleDateFormat(format);
        Date sdate=null;
        Date eDate=null;
        try {
            sdate=df.parse(stime);
            eDate=df.parse(etime);
        } catch (Exception e) {
            e.printStackTrace();
        }

        long betweendays=(long) ((eDate.getTime()-sdate.getTime())/(1000 * 60 * 60 *24)+0.5);//天数间隔
        Calendar c = Calendar.getInstance();
        List<String> list=new ArrayList<String>();
        while (sdate.getTime()<=eDate.getTime()) {
            list.add(df.format(sdate));
            c.setTime(sdate);
            c.add(Calendar.DATE, 1); // 日期加1天
            sdate = c.getTime();
        }
        return list;
    }

	/**
	 * @Title: getFmtDate
	 * @Description: TODO 获取当前时间的String类型，具体到秒
	 * @param @return
	 * @return String 当前时间
	 */
	public static String getFmtDate() {
		Date dt = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = df.format(dt);
		return nowTime;
	}

    /**
     * 指定日期的默认显示，具体格式：年-月-日
     * @param cal 日历
     */
    public static String formatDate(Calendar cal) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_YMD);
        return format.format(cal.getTime());
    }

    /**
     * 指定日期的默认显示，具体格式：年-月-日
     * @param date 日期
     */
    public static String formatDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_YMD);
        return format.format(date);
    }

    public static String formatDateToString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_YMD_STRING);
        return format.format(date);
    }

    public static String formatDateToStringHMS(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_HMS);
        return format.format(date);
    }

    /**
     * 指定日期的默认显示，具体格式：年-月-日
     * @param date 日期
     */
    public static Date formatDateToDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_YMD);
        return strToDate(format.format(date));
    }

    /**
     * 指定日期的默认显示
     * @param date 日期
     */
    public static Date formatDateToDate(Date date, String parttern) {
        SimpleDateFormat format = new SimpleDateFormat(parttern);
        return  str2Date(format.format(date), parttern);
    }

    public static String formatStrToStr(String date, String parttern) {
        Date formatDate = str2Date(date, DATE_FORMAT_HMS);
        return  date2Str(formatDate, parttern);
    }

    /**
     * 字符串格式日期转换成Date类型
     * @param date
     * @return
     */
    public static Date formatStrToDate(String date) {
        Date formatDate = str2Date(date, DATE_FORMAT_HMS);
        return  formatDate;
    }

    /**
     * 比较日期大小
     * @param startDate 开始时间
     * @param endDate 结束时间
     */
    public static boolean before(Date startDate, Date endDate){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        startDate = str2Date(sdf.format(startDate), "yyyy-MM-dd");
        endDate = str2Date(sdf.format(endDate), "yyyy-MM-dd");

        return startDate.before(endDate);
    }

    /**
     * 获取两个时间的中单时间
     * @param startDate 开始时间
     * @param endDate 结束时间
     */
    public static Date getCenterDate(Date startDate, Date endDate) {
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);
        long start = startCal.getTimeInMillis();

        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);
        long end = endCal.getTimeInMillis();

        long center = start + (end - start)/2;

        Calendar centerCal = Calendar.getInstance();
        centerCal.setTimeInMillis(center);

        return formatDateToDate(centerCal.getTime(), DATE_FORMAT_HM);
    }

    public static Integer getAge(String birthday) {
        Date birth = str2Date(birthday, DATE_FORMAT_YMD);
        Calendar cal = Calendar.getInstance();
        if (cal.before(birth)) {
            throw new IllegalArgumentException("出生日期大于当前时间!");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birth);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
        int age = yearNow - year;
        if (monthNow <= month) {
            if (monthNow == month) {
                if (dayOfMonthNow < dayOfMonth) {
                    age--;
                }
            } else {
                age --;
            }
        }
        return age;
    }

    /**
     * 王新添加
     * 获得当前时间前几天的时间数据
     * @param total    特定天数参数
     * @return
     */
    public static Date getNowDateBeforeDate(int total){
        Date date = getDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        /**获得前两天的时间*/
        calendar.add(Calendar.DAY_OF_MONTH, -total);
        date = calendar.getTime();
        return date;
    }

    /**
     * 返回日期，具体格式：年-月-日
     * @author wang xin.
     * @date 2018/12/7 17:37.
     * @return java.lang.String.
     */
    public static Date getDateYMD(){
        TimeZone time = TimeZone.getTimeZone("GMT+8"); //设置为东八区
        TimeZone.setDefault(time);// 设置时区
        Calendar calendar = Calendar.getInstance(time);// 获取实例
        Date date = calendar.getTime(); //获取Date对象
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_YMD);
        try {
            date = format.parse(format.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 返回日期，当前日期的后一年
     * @author wang xin.
     * @date 2018/12/7 17:47.
     * @return java.lang.String.
     */
    public static Date getDateYMDAfter(){
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(System.currentTimeMillis());
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, 1);
        date = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_YMD);
        try {
            date = format.parse(format.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取本月第一天
     * @return
     */
    public static Date getMinDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));

        return calendar.getTime();
    }

    /**
     * 获取本月最后一天
     * @return
     */
    public static Date getMaxDate(){
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(new Date());
        calendar2.set(Calendar.DAY_OF_MONTH, calendar2.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar2.getTime();
    }

    public static void main(String[] args) {
    }
}