package com.lynch.cms.common.time;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Date常用工具类
 * 
 * @author Lynch Yao
 * @version 1.0
 */
public class DateUtil2 {

	/** 格式化形式 */
	public static final String DATE_PATTERN_YYYYMMDD1 = "yyyy-MM-dd";
	public static final String DATE_PATTERN_YYYYMMDD2 = "yyyy/MM/dd";
	public static final String DATE_PATTERN_YYYYMMDD3 = "yyyy年MM月dd日";
	public static final String DATE_PATTERN_MD = "M月d号";
	public static final String DATE_PATTERN_YYYYMMDD = "yyyyMMdd";
	public static final String DATE_PATTERN_YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_PATTERN_YYYYMMDDHHMM = "yyyy-MM-dd HH:mm";

	/**
	 * 获取当前字符串类型时间
	 * 
	 * @param pattern 时间格式
	 * @return String 时间
	 */
	public static String nowStringDate(String pattern) {
		return dateToString(new Date(), pattern);
	}

	/**
	 * 获取当前日期格式的时间
	 * 
	 * @param pattern 时间格式 
	 * @return Date 时间
	 */
	public static Date nowDate(String pattern) {
		String nowStringDate = nowStringDate(pattern);
		return stringToDate(nowStringDate, pattern);
	}

	/**
	 * 格式化时间为字符串
	 * 
	 * @param date 时间
	 * @param pattern 时间格式        
	 * @param locale 地理时区       
	 * @return String 时间
	 */
	public static String dateToString(Date date, String pattern, Locale locale) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern, locale);
			return sdf.format(date);
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 格式化时间为字符串
	 * 
	 * @param date 时间
	 * @param pattern 时间格式        
	 * @return String 时间
	 */
	public static String dateToString(Date date, String pattern) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.format(date);
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 格式化字符型时间为长整型
	 * 
	 * @param strDate 时间
	 * @param pattern 时间格式
	 * @param locale 地理时区
	 * @return long 时间
	 * @throws ParseException
	 */
	public static long stringToLong(String strDate, String pattern,
			Locale locale) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern, locale);
		Date date = sdf.parse(strDate);
		return date.getTime();
	}

	/**
	 * 格式化字符型时间为长整型
	 * 
	 * @param strDate 时间
	 * @param pattern 时间格式
	 * @return long 时间
	 * @throws ParseException
	 */
	public static long stringToLong(String strDate, String pattern)
			throws ParseException {
		Locale locale = Locale.CHINESE;
		return stringToLong(strDate, pattern, locale);
	}

	/**
	 * 格式化字符型为Date型
	 * 
	 * @param strDate 时间
	 * @param pattern 时间格式
	 * @return Date 时间
	 */
	public static Date stringToDate(String strDate, String pattern) {
		try {
			long ltime = stringToLong(strDate, pattern);
			return new Date(ltime);
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 格式化字符型为Date型
	 * 
	 * @param strDate 时间
	 * @param pattern 时间格式
	 * @param otherPattern 另外的时间格式
	 * @return Date 时间
	 */
	public static Date stringToDate(String strDate, String pattern,
			String otherPattern) {
		try {
			long ltime = stringToLong(strDate, pattern);
			return new Date(ltime);
		} catch (Exception ex) {
			try {
				long ltime = stringToLong(strDate, otherPattern);
				return new Date(ltime);
			} catch (Exception e) {
				return null;
			}
		}
	}

	/**
	 * 格式化时间
	 * 
	 * @param date 时间
	 * @param pattern 时间格式
	 * @return Date 时间
	 */
	public static Date formateDate(Date date, String pattern) {
		String s = dateToString(date, pattern);
		return stringToDate(s, pattern);
	}
	
	/**
	 * 将给定的日期转换为指定格式的字符串,如果输入时间为null,那么返回""
	 * 
	 * @param date 时间
	 * @param pattern 时间格式
	 * @return String 时间 
	 */
	public static String getDateToString(Date date,String pattern) {
		String sDate = "";
		if (date != null) {
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			sDate = format.format(date);
		}
		return sDate;
	}

	/**
	 * 星期几的取得
	 * 
	 * @param date 时间
	 * @return int 星期几(0-6)
	 */
	public static int getWeekInt(Date date) {

		Calendar date1 = Calendar.getInstance();
		date1.setTime(date);                     			// 设置日期
		int week = date1.get(Calendar.DAY_OF_WEEK) - 1;		// 取星期几
		return week;
	}

	/**
	 * 获取某个时间的具体的某一天
	 * 
	 * @param date 时间
	 * @return int date今天日期
	 */
	public static int getEmbodyDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int embodyDay = calendar.get(Calendar.DAY_OF_MONTH);
		return embodyDay;

	}

	/**
	 * 获取某个时间的具体的某一月
	 * 
	 * @param date 时间
	 * @return int date今天月份
	 */
	public static int getEmbodyMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int embodyMonth = calendar.get(Calendar.MONTH) + 1;
		return embodyMonth;

	}

	/**
	 * 获取某个时间的具体的某一年
	 * 
	 * @param date 时间
	 * @return int date今天年份
	 */
	public static int getEmbodyYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int embodyYear = calendar.get(Calendar.YEAR);
		return embodyYear;

	}

	/**
	 * 日期変換<BR>
	 * 
	 * @param _strDate
	 *            日付変換対象文字列
	 * @param _strDateFormat
	 * @return Date 変換後日付
	 */
	public static Date toDateType(String _strDate, String _strDateFormat) {

		Date dteRet_ = null;
		SimpleDateFormat sdfObject_ = null;

		try {

			// if (CommonFunction.isNull(_strDate)) {
			// return null;
			// }

			sdfObject_ = new SimpleDateFormat(_strDateFormat);

			dteRet_ = sdfObject_.parse(_strDate);

		} catch (Exception e) {
			dteRet_ = null;
		}

		return dteRet_;
	}

	/**
	 * 开始日星期日取得
	 * 
	 * @param beginDate 日期
	 * @param pattern 时间格式
	 * @return String 开始日星期日
	 */
	public static String getSunday(String beginDate, String pattern) {

		if (getWeekInt(toDateType(beginDate, pattern)) != 0) {

			GregorianCalendar Calendar = new GregorianCalendar();
			Calendar.setTime(toDateType(beginDate, pattern));
			switch (getWeekInt(toDateType(beginDate, pattern))) {
			case 1:
				Calendar.add(GregorianCalendar.DATE, -1);
				break;
			case 2:
				Calendar.add(GregorianCalendar.DATE, -2);
				break;
			case 3:
				Calendar.add(GregorianCalendar.DATE, -3);
				break;
			case 4:
				Calendar.add(GregorianCalendar.DATE, -4);
				break;
			case 5:
				Calendar.add(GregorianCalendar.DATE, -5);
				break;
			case 6:
				Calendar.add(GregorianCalendar.DATE, -6);
				break;
			default:
				break;
			}
			// 创建SimpleDateFormat
			SimpleDateFormat DateFormat = new SimpleDateFormat(pattern);
			// 获得开始日
			beginDate = DateFormat.format(Calendar.getTime());
		}

		return beginDate;
	}

	/**
	 * 开始日星期一取得
	 * 
	 * @param beginDate 日期
	 * @param pattern 时间格式
	 * @return String 开始日星期一
	 */
	public static String getPrevMondy(String beginDate, String pattern) {

		if (getWeekInt(toDateType(beginDate, pattern)) != 0) {

			GregorianCalendar Calendar = new GregorianCalendar();
			Calendar.setTime(toDateType(beginDate, pattern));
			switch (getWeekInt(toDateType(beginDate, pattern))) {
			case 1:
				Calendar.add(GregorianCalendar.DATE, -7);
				break;
			case 2:
				Calendar.add(GregorianCalendar.DATE, -1);
				break;
			case 3:
				Calendar.add(GregorianCalendar.DATE, -2);
				break;
			case 4:
				Calendar.add(GregorianCalendar.DATE, -3);
				break;
			case 5:
				Calendar.add(GregorianCalendar.DATE, -4);
				break;
			case 6:
				Calendar.add(GregorianCalendar.DATE, -5);
				break;
			case 7:
				Calendar.add(GregorianCalendar.DATE, -6);
				break;
			default:
				break;
			}
			// 创建SimpleDateFormat
			SimpleDateFormat DateFormat = new SimpleDateFormat(pattern);
			// 获得开始日
			beginDate = DateFormat.format(Calendar.getTime());
		}

		return beginDate;
	}

	/**
	 * 前一个星期一取得
	 * 
	 * @param beginDate 日期
	 * @return Date 前一个星期一
	 */
	public static Date getPrevMondy(Date beginDate) {

		GregorianCalendar Calendar = new GregorianCalendar();
		Calendar.setTime(beginDate);
		switch (getWeekInt(beginDate)) {
		case 1:
			Calendar.add(GregorianCalendar.DATE, -7);
			break;
		case 2:
			Calendar.add(GregorianCalendar.DATE, -1);
			break;
		case 3:
			Calendar.add(GregorianCalendar.DATE, -2);
			break;
		case 4:
			Calendar.add(GregorianCalendar.DATE, -3);
			break;
		case 5:
			Calendar.add(GregorianCalendar.DATE, -4);
			break;
		case 6:
			Calendar.add(GregorianCalendar.DATE, -5);
			break;
		case 0:
			Calendar.add(GregorianCalendar.DATE, -6);
			break;
		default:
			break;
		}
		beginDate = Calendar.getTime();
		return beginDate;
	}

	/**
	 * 下一个星期一取得
	 * 
	 * @param beginDate 日期
	 * @param pattern 时间格式
	 * @return String 下一个星期一
	 */
	public static String getNextMondy(String beginDate, String pattern) {

		if (getWeekInt(toDateType(beginDate, pattern)) != 0) {

			GregorianCalendar Calendar = new GregorianCalendar();
			Calendar.setTime(toDateType(beginDate, pattern));
			switch (getWeekInt(toDateType(beginDate, pattern))) {
			case 2:
				Calendar.add(GregorianCalendar.DATE, 6);
				break;
			case 3:
				Calendar.add(GregorianCalendar.DATE, 5);
				break;
			case 4:
				Calendar.add(GregorianCalendar.DATE, 4);
				break;
			case 5:
				Calendar.add(GregorianCalendar.DATE, 3);
				break;
			case 6:
				Calendar.add(GregorianCalendar.DATE, 2);
				break;
			case 7:
				Calendar.add(GregorianCalendar.DATE, 1);
				break;
			default:
				break;
			}
			// 创建SimpleDateFormat
			SimpleDateFormat DateFormat = new SimpleDateFormat(pattern);
			// 获得开始日
			beginDate = DateFormat.format(Calendar.getTime());
		}

		return beginDate;
	}

	/**
	 * 下一个星期天取得
	 * 
	 * @param beginDate 日期
	 * @param pattern 时间格式
	 * @return String 下一个星期天
	 */
	public static String getNextSunday(String beginDate, String pattern) {

		if (getWeekInt(toDateType(beginDate, pattern)) != 0) {

			GregorianCalendar Calendar = new GregorianCalendar();
			Calendar.setTime(toDateType(beginDate, pattern));
			switch (getWeekInt(toDateType(beginDate, pattern))) {
			case 1:
				Calendar.add(GregorianCalendar.DATE, 6);
				break;
			case 2:
				Calendar.add(GregorianCalendar.DATE, 5);
				break;
			case 3:
				Calendar.add(GregorianCalendar.DATE, 4);
				break;
			case 4:
				Calendar.add(GregorianCalendar.DATE, 3);
				break;
			case 5:
				Calendar.add(GregorianCalendar.DATE, 2);
				break;
			case 6:
				Calendar.add(GregorianCalendar.DATE, 1);
				break;
			default:
				break;
			}
			// 创建SimpleDateFormat
			SimpleDateFormat DateFormat = new SimpleDateFormat(pattern);
			// 获得开始日
			beginDate = DateFormat.format(Calendar.getTime());
		}

		return beginDate;
	}

	/**
	 * 下一个星期天取得
	 * 
	 * @param beginDate 日期
	 * @return Date 下一个星期天
	 */
	public static Date getNextSunday(Date beginDate) {

		if (getWeekInt(beginDate) != 0) {

			GregorianCalendar Calendar = new GregorianCalendar();
			Calendar.setTime(beginDate);
			switch (getWeekInt(beginDate)) {
			case 1:
				Calendar.add(GregorianCalendar.DATE, 6);
				break;
			case 2:
				Calendar.add(GregorianCalendar.DATE, 5);
				break;
			case 3:
				Calendar.add(GregorianCalendar.DATE, 4);
				break;
			case 4:
				Calendar.add(GregorianCalendar.DATE, 3);
				break;
			case 5:
				Calendar.add(GregorianCalendar.DATE, 2);
				break;
			case 6:
				Calendar.add(GregorianCalendar.DATE, 1);
				break;
			default:
				break;
			}
			// 获得开始日
			beginDate = Calendar.getTime();
		}

		return beginDate;
	}

	/**
	 * 取得指定月份的第一天
	 * 
	 * @param strdate 日期
	 * @return String 指定月份的第一天
	 */
	public static String getMonthBegin(String strdate, String pattern) {
		Date date = stringToDate(strdate, pattern);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		date = calendar.getTime();

		return format(date, pattern);
	}

	/**
	 * 取得指定月份的最后一天
	 * 
	 * @param strdate 日期
	 * @return String 指定月份的最后一天
	 */
	public static String getMonthEnd(String strdate, String pattern) {
		Date date = stringToDate(strdate, pattern);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		date = calendar.getTime();
		date = addMonth(date, 1);
		date = addDays(date, -1);
		return format(date, pattern);
	}

	/**
	 * 取得指定月份的最大天数
	 * 
	 * @param year 年
	 * @param month 月
	 * @return int 返回指定月份的最大天数
	 */
	public static int getMonthDay(String year, String month) {
		String strDate = getYYYYMMDD(year, month, "1",
				DateUtil2.DATE_PATTERN_YYYYMMDD);
		Date date = stringToDate(strDate, DateUtil2.DATE_PATTERN_YYYYMMDD);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		date = calendar.getTime();
		date = addMonth(date, 1);
		date = addDays(date, -1);
		return getDayInt(date);
	}

	/**
	 * 月份中文名称转换函数
	 * 
	 * @param months 月份名称
	 * @return String 月份名称
	 */
	public static String getMonthChineseName(String months) {
		String monthsChinese = "";
		String[] newMonths = months.split(",");
		for (int i = 0; i < newMonths.length; i++) {
			if ("01".equals(newMonths[i]) || "1".equals(newMonths[i])) {
				newMonths[i] = "1月";
			}
			if ("02".equals(newMonths[i]) || "2".equals(newMonths[i])) {
				newMonths[i] = "2月";
			}
			if ("03".equals(newMonths[i]) || "3".equals(newMonths[i])) {
				newMonths[i] = "3月";
			}
			if ("04".equals(newMonths[i]) || "4".equals(newMonths[i])) {
				newMonths[i] = "4月";
			}
			if ("05".equals(newMonths[i]) || "5".equals(newMonths[i])) {
				newMonths[i] = "5月";
			}
			if ("06".equals(newMonths[i]) || "6".equals(newMonths[i])) {
				newMonths[i] = "6月";
			}
			if ("07".equals(newMonths[i]) || "7".equals(newMonths[i])) {
				newMonths[i] = "7月";
			}
			if ("08".equals(newMonths[i]) || "8".equals(newMonths[i])) {
				newMonths[i] = "8月";
			}
			if ("09".equals(newMonths[i]) || "9".equals(newMonths[i])) {
				newMonths[i] = "9月";
			}
			if ("10".equals(newMonths[i])) {
				newMonths[i] = "10月";
			}
			if ("11".equals(newMonths[i])) {
				newMonths[i] = "11月";
			}
			if ("12".equals(newMonths[i])) {
				newMonths[i] = "12月";
			}
		}
		for (int i = 0; i < newMonths.length; i++) {
			if (i == 0) {
				monthsChinese = newMonths[i];
			} else {
				monthsChinese = monthsChinese + "," + newMonths[i];
			}
		}
		return monthsChinese;
	}

	/**
	 * 日的取得（Int）
	 * 
	 * @param date 日期
	 * @return int 日期
	 */
	public static int getDayInt(Date date) {

		Calendar date1 = Calendar.getInstance();

		// 设置日期
		date1.setTime(date);

		// 取号数
		int day = date1.get(Calendar.DAY_OF_MONTH);

		return day;
	}

	/**
	 * 日期月份加减计算方法
	 * 
	 * @param _date 日期
	 * @param month 加减月份
	 * @return Date 计算结果
	 */
	public static Date addMonth(Date _date, int month) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(_date);
		cal.add(Calendar.MONTH, month);
		return cal.getTime();
	}

	/**
	 * 日期日的加减计算方法
	 * 
	 * @param _date 日期
	 * @param day 加减天数
	 * @return Date 计算结果
	 */
	public static Date addDays(Date _date, int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(_date);
		cal.add(Calendar.DATE, day);
		return cal.getTime();
	}

	/**
	 * 返回YYYYMMDD格式的日期字符串
	 * 
	 * @param _yaer 年
	 * @param _months 月
	 * @param _day 日
	 * @return String 日期字符串
	 */
	public static String getYYYYMMDD(String _yaer, String _months, String _day,
			String _pattern) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.parseInt(_yaer));
		cal.set(Calendar.MONTH, Integer.parseInt(_months) - 1);
		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(_day));
		Date date = cal.getTime();

		return format(date, _pattern);
	}

	/**
	 * 日期格式化方法
	 * 
	 * @param _dte 日期
	 * @param _pattern 格式化形式
	 * @return String 格式化后的日期字符串
	 */
	public static String format(Date _dte, String _pattern) {
		String result = "";
		try {
			if (_dte != null) {
				DateFormat df = new SimpleDateFormat(_pattern);
				result = df.format(_dte);
			}
		} catch (Exception e) {
		}
		return result;
	}

	/**
	 * 判断时间date1是否在时间date2之前 
	 * 时间格式 2005-4-21 16:16:34
	 * 
	 * @param date1 日期
	 * @param date2 日期
	 * @return boolean 是否在之前
	 */
	public static boolean isDateBefore(String date1, String date2) {
		try {
			DateFormat df = DateFormat.getDateTimeInstance();
			return df.parse(date1).before(df.parse(date2));
		} catch (ParseException e) {
			System.out.print("[SYS] " + e.getMessage());
			return false;
		}
	}

	/**
	 * 判断当前时间是否在时间date2之前 
	 * 时间格式 2005-4-21 16:16:34
	 * 
	 * @param date2 日期
	 * @return boolean 是否在之前
	 */
	public static boolean isDateBefore(String date2) {
		try {
			Date date1 = new Date();
			DateFormat df = DateFormat.getDateTimeInstance();
			return date1.before(df.parse(date2));
		} catch (ParseException e) {
			System.out.print("[SYS] " + e.getMessage());
			return false;
		}
	}

	/**  
     * 返回两个日期相差的天数
     * 
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return long 相差日期
     * @throws ParseException  
     */  
    public static long getDistDates(Date startDate, Date endDate) {   
        long totalDate = 0;   
        Calendar calendar = Calendar.getInstance();   
        calendar.setTime(startDate);   
        long timestart = calendar.getTimeInMillis();   
        calendar.setTime(endDate);   
        long timeend = calendar.getTimeInMillis();   
        totalDate = Math.abs((timeend - timestart)) / (1000 * 60 * 60 * 24);   
        return totalDate;   
    } 
    
    
    
    /** 
     * 返回给定时间的下一天
     * 
     * @param nowDate 日期
     * @return Date 给定时间的下一天
     */
	public static Date TomorrowToDate(Date nowDate) {
		
		Calendar c = Calendar.getInstance();
		c.setTime(nowDate);
		c.add(Calendar.DAY_OF_YEAR, 1);
		Date tomorrow = c.getTime();
		return tomorrow;
	}
	
}

