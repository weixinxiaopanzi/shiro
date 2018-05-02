package com.yunlianhui.shiro.util;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;


/**
 * @author Jary.ho
 * 
 */
public class DateUtil {
	private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);
	
	/**时间格式*/
	public static final String DATE_FORMAT_INTERFACE = "yyyy-MM-dd_HH:mm:ss";
	public static final String DATE_FOoRMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_SHORT_FORMAT = "yyyyMMdd";
	public static final String DATE_HORIZONTAL_FORMAT = "yyyy-MM-dd";
	public static final String DATE_LONG_FORMAT = "yyyyMMddHHmmss";
	public static final String TIME_SHORT_FORMAT = "HH:mm:ss";
	public static final String TIMEZONE = "GMT+08:00";
	
	/**
	 * 秒 转 00:00:00
	 * 
	 * @creator andy.fu
	 * @create-time 2013-7-9 上午11:40:38
	 * @param second
	 * @return
	 */
	public static String calTime(int second) {
		String h = "0";
		String d = "0";
		String s = "0";
		int temp = second % 3600;
		if (second > 3600) {
			h = String.valueOf(second / 3600);
			if (Integer.parseInt(h) < 10) {
				h = "0" + h;
			}
			if (temp != 0) {
				if (temp > 60) {
					d = String.valueOf(temp / 60);
					if (Integer.parseInt(d) < 10) {
						d = "0" + d;
					}
					if (temp % 60 != 0) {
						s = String.valueOf(temp % 60);
						if (Integer.parseInt(s) < 10) {
							s = "0" + s;
						}
					}
				} else {
					s = String.valueOf(temp);
					d = "00";
					if (Integer.parseInt(s) < 10) {
						s = "0" + s;
					}
				}
			} else {
				d = "00";
				s = "00";
			}
		} else {
			h = "00";
			d = String.valueOf(second / 60);
			if (Integer.parseInt(d) < 10) {
				d = "0" + d;
			}
			if (Integer.parseInt(d) % 60 == 0) {

				h = String.valueOf((Integer.parseInt(d) / 60));
				if (Integer.parseInt(h) < 10) {
					h = "0" + h;
				}
				d = "00";
			}
			s = "00";
			if (second % 60 != 0) {
				s = String.valueOf(second % 60);
				if (Integer.parseInt(s) < 10) {
					s = "0" + s;
				}
			}
		}

		return h + ":" + d + ":" + s;
	}

	/**
	 * 得到相关格式的[当前]日期字符串 tangzr
	 * 
	 * @param daynum
	 * @param dayFormat
	 *            "yyyy-MM-dd HH:mm:ss"
	 * @return String
	 */
	public static String getCalendar(String dayFormat, Integer dayNum) {
		Format f = new SimpleDateFormat(dayFormat);
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, dayNum);
		String date = f.format(c.getTime());
		return date;
	}
	
	
	/*public static void main(String[] args){
		boolean is_can_call = true;
		String call_window = "09:00-18:00";//默认的callwindow
		String time_begin = call_window.split("-")[0];
		String time_end = call_window.split("-")[1];
		String time_cur = DateUtil.getHour() + ":" + DateUtil.getTime();
		if(time_begin.compareTo(time_cur)<0 && time_cur.compareTo(time_end)<0){
			is_can_call = true;
		}else{
			is_can_call = false;
		}
		if(!is_can_call){
			System.out.println("不能拨号");
		}
		String str0="01:00";
		String str1="16:14";
		System.out.println(str0.compareTo(str1));
	}*/
	
	/**
	 * 得到相关格式的[当前]日期
	 * 
	 * @param daynum
	 * @param dayFormat
	 *            "yyyy-MM-dd HH:mm:ss"
	 * @return String
	 */
	public static Date getCalendarToDate(String dayFormat, Integer dayNum) {
		SimpleDateFormat f = new SimpleDateFormat(dayFormat);
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, dayNum);
		String date = f.format(c.getTime());
		
		try {
			return f.parse(date);
		} catch (ParseException e) {
		  logger.error("Failed", e);
		}
		
		return null;
	}

	/**
	 * 时间格式化 加多少天
	 * 
	 * @param 某一时间
	 * @param 天数
	 * @return
	 */
	public static String dateToFormat(String dayformat, Integer dayNum) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String day = "";
		try {
			Date dd = formatter.parse(dayformat);// 从给定字符串的开始解析文本，以生成一个日期。
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dd);
			calendar.add(Calendar.DAY_OF_MONTH, dayNum);
			day = formatter.format(calendar.getTime());
		} catch (Exception e) {
			logger.error("Failed", e);
		}
		return day;
	}
	
	
	public static String dateToFormat2(String dayformat, Integer dayNum) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String day = "";
		try {
			Date dd = formatter.parse(dayformat);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dd);
			calendar.add(Calendar.DAY_OF_MONTH, dayNum);
			day = formatter.format(calendar.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return day;
	}
	
	
	public static void main(String[] args)  throws Exception{
		System.out.println(getStringDateShort());
      System.out.println(dateToFormat2(getStringDateShort(),-9));
	}
	
	public static String dateToFormat(Date date, Integer dayNum) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		if (calendar == null) {
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!");
		}
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, dayNum);
		String day = formatter.format(calendar.getTime());
		return day;
	}
	/**
	 * 获取指定日期，加上几月份后的日期
	 * @param monthNum
	 * @return
	 */
	public static String monthAfterNum(Integer monthNum) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, monthNum);
		return formatter.format(calendar.getTime());
	}
	/**
	 * 获取指定日期，加上几年之后的日期
	 * @param monthNum
	 * @return
	 */
	public static String yearAfterNum(Integer yearNum) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.YEAR, yearNum);
		return formatter.format(calendar.getTime());
	}

	public static String dateShortToFormat(String dayformat, Integer dayNum) {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String day = "";
		try {
			Date dd = formatter.parse(dayformat);// 从给定字符串的开始解析文本，以生成一个日期。
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dd);
			calendar.add(Calendar.DAY_OF_MONTH, dayNum);
			day = formatter.format(calendar.getTime());
		} catch (Exception e) {
			logger.error("Failed", e);
		}
		return day;
	}
	
	/**
	 * 根据日期时间转换的格式和字符串，转换返回该日期的时间戳
	 * @param dayformat，默认yyyy-MM-dd
	 * @param dateStr
	 * @return
	 */
	public static long dateStr2Format(String dayformat,String dateStr) {
		DateFormat formatter ;
		if (StringUtils.isEmpty(dayformat))
			formatter = new SimpleDateFormat("yyyy-MM-dd"); 
		else{
			formatter = new SimpleDateFormat(dayformat);
		}
		try {
			if(StringUtils.isEmpty(dateStr)){
				return new Date().getTime();
			}
			Date tempDate = formatter.parse(dateStr);// 从给定字符串的开始解析文本，以生成一个日期。
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(tempDate);
			return calendar.getTimeInMillis();
		} catch (Exception e) {
			logger.error("Failed", e);
		}
		return 0;
	}

	/**
	 * 获取现在时间
	 * 
	 * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
	 */
	public static Date getNowDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		ParsePosition pos = new ParsePosition(8);
		Date currentTime_2 = formatter.parse(dateString, pos);
		return currentTime_2;

	}
	
	
	/**
	 * 获取现在时间
	 * 
	 * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
	 * @throws ParseException 
	 */
	public static Date getNowDate2() throws ParseException {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		Date parse = formatter.parse(dateString);
		return parse;
		
	}

	/**
	 * 获取现在时间
	 * 
	 * @return返回短时间格式 yyyy-MM-dd
	 */
	public static Date getNowDateShort() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		ParsePosition pos = new ParsePosition(8);
		Date currentTime_2 = formatter.parse(dateString, pos);
		return currentTime_2;

	}

	/**
	 * 获取现在时间
	 * 
	 * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
	 */
	public static String getStringDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString;

	}
	
	/**
	 * 获取现在时间
	 * 
	 * @return返回字符串格式 MM-dd HH:mm
	 */
	public static String getStringDate2() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd HH:mm");
		String dateString = formatter.format(currentTime);
		return dateString;

	}
	
	public static Date parseDate(String dateStr) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		return sdf.parse(dateStr);
	}
	
	/**
	 * 获取现在时间
	 * 
	 * @return 返回短时间字符串格式yyyy-MM-dd
	 */
	public static String getStringDateShort() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		return dateString;

	}

	/**
	 * 获取时间 小时:分;秒 HH:mm:ss
	 * 
	 * @return
	 */
	public static String getTimeShort() {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		Date currentTime = new Date();
		String dateString = formatter.format(currentTime);
		return dateString;

	}

	/**
	 * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date strToDateLong(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;

	}
	
	/**
	 * 将字符串时间转换成特定格式的Date
	 * @param strDate
	 * @param special
	 * @return
	 */
	public static Date strToSpecialDate(String strDate, String special){
		SimpleDateFormat formatter = new SimpleDateFormat(special);
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	/**
	 * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateDate
	 * @return
	 */
	public static String dateToStrLong(java.util.Date dateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(dateDate);
		return dateString;

	}

	/**
	 * 将短时间格式时间转换为字符串 yyyy-MM-dd
	 * 
	 * @param dateDate
	 * @param k
	 * @return
	 */
	public static String dateToStr(java.util.Date dateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(dateDate);
		return dateString;
	}
	/**
	 * 将短时间格式时间转换为字符串 yyyyMMdd
	 * 
	 * @param dateDate
	 * @param k
	 * @returnå
	 */
	public static String dateToyyyyMMddStr(java.util.Date dateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String dateString = formatter.format(dateDate);
		return dateString;
	}

	/**
	 * 将短时间格式字符串转换为时间 yyyy-MM-dd
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date strToDate(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	/**
	 * 得到现在时间
	 * 
	 * @return
	 */
	public static Date getNow() {
		Date currentTime = new Date();
		return currentTime;
	}

	/**
	 * 得到现在时间的天
	 * 
	 * @return 天
	 */
	public static int getDay() {
	
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取当前日期是星期几
	 * 
	 * 
	 * @param dt
	 * @return 当前日期是星期几
	 */
	public static String getWeekOfDate(Date dt) {
		if (dt == null)
			dt = new Date();
		String[] weekDays = { "Sunday", "Monday", "Tuesday", "Wednesday",
				"Thursday", "Friday", "Saturday" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		return weekDays[w];
	}

	/**
	 * 提取一个月中的最后一天
	 * 
	 * @param day
	 * @return
	 */
	public static Date getLastDate(long day) {
		Date date = new Date();
		long date_3_hm = date.getTime() - 3600000 * 34 * day;
		Date date_3_hm_date = new Date(date_3_hm);
		return date_3_hm_date;
	}

	/**
	 * 得到现在时间
	 * 
	 * @return 字符串 yyyyMMdd HHmmss
	 */
	public static String getStringToday() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HHmmss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 得到现在小时
	 */
	public static String getHour() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		String hour = dateString.substring(11, 13);
		return hour;
	}

	/**
	 * 得到现在分钟
	 * 
	 * @return
	 */
	public static String getTime() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		String min;
		min = dateString.substring(14, 16);
		return min;
	}

	/**
	 * 根据用户传入的时间表示格式，返回当前时间的格式 如果是yyyyMMdd，注意字母y不能大写。
	 * 
	 * @param sformat
	 *            yyyyMMddhhmmss
	 * @return
	 */
	public static String getUserDate(String sformat) {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(sformat);
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 二个小时时间间的差值,必须保证二个时间都是"HH:MM"的格式，返回字符型的分钟
	 */
	public static String getTwoHour(String st1, String st2) {
		String[] kk = null;
		String[] jj = null;
		kk = st1.split(":");
		jj = st2.split(":");
		if (Integer.parseInt(kk[0]) < Integer.parseInt(jj[0]))
			return "0";
		else {
			double y = Double.parseDouble(kk[0]) + Double.parseDouble(kk[1])
					/ 60;
			double u = Double.parseDouble(jj[0]) + Double.parseDouble(jj[1])
					/ 60;
			if ((y - u) > 0)
				return y - u + "";
			else
				return "0";
		}
	}

	/**
	 * 得到二个日期间的间隔天数
	 */
	public static String getTwoDay(String sj1, String sj2) {
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		long day = 0;
		try {
			java.util.Date date = myFormatter.parse(sj1);
			java.util.Date mydate = myFormatter.parse(sj2);
			day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		} catch (Exception e) {
			return "";
		}
		return day + "";
	}

	/**
	 * 时间前推或后推分钟,其中JJ表示分钟.
	 */
	public static String getPreTime(String sj1, String jj) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String mydate1 = "";
		try {
			Date date1 = format.parse(sj1);
			long Time = (date1.getTime() / 1000) + Integer.parseInt(jj) * 60;
			date1.setTime(Time * 1000);
			mydate1 = format.format(date1);
		} catch (Exception e) {
		}
		return mydate1;
	}
	
	/**
	 * 时间前推或后推分钟,其中JJ表示分钟.
	 * 
	 * @param dt1
	 * @param jj
	 * @return
	 */
	public static String getPreTime(Date dt1, String jj) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String mydate1 = "";
		try {
			Date date1 = dt1;
			long Time = (date1.getTime() / 1000) + Integer.parseInt(jj) * 60;
			date1.setTime(Time * 1000);
			mydate1 = format.format(date1);
		} catch (Exception e) {
		}
		return mydate1;
	}

	/**
	 * 时间前推或后推分钟,其中JJ表示分钟.
	 * 
	 * @param dt1
	 * @param jj
	 * @return
	 */
	public static Date getPreTimeDate(Date dt1, String jj) {
		try {
			Date date1 = dt1;
			long Time = (date1.getTime() / 1000) + Integer.parseInt(jj) * 60;
			date1.setTime(Time * 1000);
			return date1;
		} catch (Exception e) {
		}
		return null;
	}
	
	/**
	 * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数
	 */
	public static String getNextDay(String nowdate, String delay) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String mdate = "";
			Date d = strToDate(nowdate);
			long myTime = (d.getTime() / 1000) + Integer.parseInt(delay) * 24
					* 60 * 60;
			d.setTime(myTime * 1000);
			mdate = format.format(d);
			return mdate;
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 判断是否润年
	 * 
	 * @param ddate
	 * @return
	 */
	public static boolean isLeapYear(String ddate) {

		/**
		 * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
		 * 3.能被4整除同时能被100整除则不是闰年
		 */
		Date d = strToDate(ddate);
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(d);
		int year = gc.get(Calendar.YEAR);
		if ((year % 400) == 0)
			return true;
		else if ((year % 4) == 0) {
			if ((year % 100) == 0)
				return false;
			else
				return true;
		} else
			return false;
	}

	/**
	 * 返回美国时间格式 26 Apr 2006
	 * 
	 * @param str
	 * @return
	 */
	public static String getEDate(String str) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(str, pos);
		String j = strtodate.toString();
		String[] k = j.split(" ");
		return k[2] + k[1].toUpperCase() + k[5].substring(2, 4);
	}

	/**
	 * 获取一个月的最后一天
	 * 
	 * @param dat
	 * @return
	 */
	public static String getEndDateOfMonth(String dat) {// yyyy-MM-dd
		String str = dat.substring(0, 8);
		String month = dat.substring(5, 7);
		int mon = Integer.parseInt(month);
		if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8
				|| mon == 10 || mon == 12) {
			str += "31";
		} else if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {
			str += "30";
		} else {
			if (isLeapYear(dat)) {
				str += "29";
			} else {
				str += "28";
			}
		}
		return str;
	}

	/**
	 * 判断二个时间是否在同一个周
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameWeekDates(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(date1);
		cal2.setTime(date2);
		int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
		if (0 == subYear) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
					.get(Calendar.WEEK_OF_YEAR))
				return true;
		} else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
			// 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
					.get(Calendar.WEEK_OF_YEAR))
				return true;
		} else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
					.get(Calendar.WEEK_OF_YEAR))
				return true;
		}
		return false;
	}

	/**
	 * 产生周序列,即得到当前时间所在的年度是第几周
	 * 
	 * @return
	 */
	public static String getSeqWeek() {
		Calendar c = Calendar.getInstance(Locale.CHINA);
		String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
		if (week.length() == 1)
			week = "0" + week;
		String year = Integer.toString(c.get(Calendar.YEAR));
		return year + week;
	}

	/**
	 * 获得一个日期所在的周的星期几的日期，如要找出2002年2月3日所在周的星期一是几号
	 * 
	 * @param sdate
	 * @param num
	 * @return
	 */
	public static String getWeek(String sdate, String num) {
		// 再转换为时间
		Date dd = DateUtil.strToDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(dd);
		if (num.equals("1")) // 返回星期一所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		else if (num.equals("2")) // 返回星期二所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
		else if (num.equals("3")) // 返回星期三所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
		else if (num.equals("4")) // 返回星期四所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
		else if (num.equals("5")) // 返回星期五所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		else if (num.equals("6")) // 返回星期六所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		else if (num.equals("0")) // 返回星期日所在的日期
			c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	}

	/**
	 * 根据一个日期，返回是星期几的字符串
	 * 
	 * @param sdate
	 * @return
	 */
	public static String getWeek(String sdate) {
		// 再转换为时间
		Date date = DateUtil.strToDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// int hour=c.get(Calendar.DAY_OF_WEEK);
		// hour中存的就是星期几了，其范围 1~7
		// 1=星期日 7=星期六，其他类推
		return new SimpleDateFormat("EEEE").format(c.getTime());
	}

	public static String getWeekStr(String sdate) {
		String str = "";
		str = DateUtil.getWeek(sdate);
		if ("1".equals(str)) {
			str = "星期日";
		} else if ("2".equals(str)) {
			str = "星期一";
		} else if ("3".equals(str)) {
			str = "星期二";
		} else if ("4".equals(str)) {
			str = "星期三";
		} else if ("5".equals(str)) {
			str = "星期四";
		} else if ("6".equals(str)) {
			str = "星期五";
		} else if ("7".equals(str)) {
			str = "星期六";
		}
		return str;
	}

	/**
	 * 两个时间之间的天数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getDays(String date1, String date2) {
		if (date1 == null || date1.equals(""))
			return 0;
		if (date2 == null || date2.equals(""))
			return 0;
		// 转换为标准时间
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = null;
		java.util.Date mydate = null;
		try {
			date = myFormatter.parse(date1);
			mydate = myFormatter.parse(date2);
		} catch (Exception e) {
		}
		long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		return day;
	}

	/**
	 * 两个时间之间的秒数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getSeconds(String date1, String date2) {
		if (date1 == null || date1.equals(""))
			return 0;
		if (date2 == null || date2.equals(""))
			return 0;
		// 转换为标准时间
		SimpleDateFormat myFormatter = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		java.util.Date date = null;
		java.util.Date mydate = null;
		try {
			date = myFormatter.parse(date1);
			mydate = myFormatter.parse(date2);
		} catch (Exception e) {
		}
		long second = (date.getTime() - mydate.getTime()) / (1000);
		return second;
	}

	/**
	 * 形成如下的日历 ， 根据传入的一个时间返回一个结构 星期日 星期一 星期二 星期三 星期四 星期五 星期六 下面是当月的各个时间
	 * 此函数返回该日历第一行星期日所在的日期
	 * 
	 * @param sdate
	 * @return
	 */
	public static String getNowMonth(String sdate) {
		// 取该时间所在月的一号
		sdate = sdate.substring(0, 8) + "01";

		// 得到这个月的1号是星期几
		Date date = DateUtil.strToDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int u = c.get(Calendar.DAY_OF_WEEK);
		String newday = DateUtil.getNextDay(sdate, (1 - u) + "");
		return newday;
	}

	/**
	 * 取得数据库主键 生成格式为yyyymmddhhmmss+k位随机数
	 * 
	 * @param k
	 *            表示是取几位随机数，可以自己定
	 */

	public static String getNo(int k) {

		return getUserDate("yyyyMMddHHmmss") + getRandom(k);
	}

	/**
	 * 返回一个随机数
	 *
	 * @param i
	 * @return
	 */
	public static String getRandom(int i) {
		Random jjj = new Random();
		// int suiJiShu = jjj.nextInt(9);
		if (i == 0)
			return "";
		String jj = "";
		for (int k = 0; k < i; k++) {
			jj = jj + jjj.nextInt(9);
		}
		return jj;
	}

	/**
	 * 
	 * @param args
	 * @throws java.text.ParseException
	 * @throws java.text.ParseException
	 */
	public static boolean RightDate(String date)
			throws java.text.ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		if (date == null)
			return false;
		if (date.length() > 10) {
			sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		} else {
			sdf = new SimpleDateFormat("yyyy-MM-dd");
		}

		sdf.parse(date);
		return true;
	}

	/**
	 * 得到当前时间, 与JS的 Date.parse(new Date())的值相同,
	 * 
	 * @return 秒数
	 */
	public static String getTimeInMilli() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateTime = formatter.format(currentTime);

		Calendar c = Calendar.getInstance();

		try {
			c.setTime(new SimpleDateFormat("yyyyMMddHHmmss").parse(dateTime));
		} catch (java.text.ParseException e) {
			logger.error("Failed", e);
		}
		String strtime = String.valueOf(c.getTimeInMillis() / 1000);
		return strtime;

	}
	
	public static String convertDateFormat(String datestr) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		Date dateTime = null;
		try {
			dateTime = formatter.parse(datestr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		
		return dateToStrLong(dateTime);

	}
	
	/**  
	* 根据开始时间和结束时间返回时间段内的时间集合  
	* @param beginDate  开始时间
	* @param endDate	结束时间
	* @param dateType	时间段类型
	* @param length  	时间段长度
	* @return List  
	*/    
	public static List<Date> getDatesBetweenTwoDate(Date beginDate, Date endDate, Integer dateType, Integer length) {  
		List<Date> lDate = new ArrayList<Date>();
		
		lDate.add(beginDate);// 把开始时间加入集合
		Calendar cal = Calendar.getInstance();
		// 使用给定的 Date 设置此 Calendar 的时间
		cal.setTime(beginDate);
		boolean bContinue = true;
		while (bContinue) {
			// 根据日历的规则，为给定的日历字段添加或减去指定的时间量
			cal.add(dateType, length);
			// 测试此日期是否在指定日期之后
			if (endDate.after(cal.getTime())) {
				lDate.add(cal.getTime());
			} else {
				break;
			}
		}
		lDate.add(endDate);// 把结束时间加入集合
		return lDate;
	}  
	
	
	/**
	 * 获取多小天前，后的开始时间
	 * @param days 天
	 * @return
	 */
	public static Date getBeginDate(Integer days) {
		
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, days);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.HOUR_OF_DAY, 0);
		
		return c.getTime();

	}
	
	/**
	 * 获取多小天前，后的结束时间
	 * 
	 * @param days 天
	 * @return
	 */
	public static Date getEndDate(Integer days) {
		
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, days + 1);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.HOUR_OF_DAY, 0);
		
		return c.getTime();

	}
	
	/**
	 * 获取当前时间前、后n个月的时间值
	 * @param months
	 * @return
	 */
	public static Date getBeginDateForMonth(Integer months){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, months);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.HOUR_OF_DAY, 0);
		
		return c.getTime();
	}
	
	public static boolean isWorkDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int dayofweek = 0;
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			dayofweek = 7;
		} else {
			dayofweek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return dayofweek >= 1 && dayofweek <= 5;
	}
	
	public static Date addHour(Date date, int hour) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.HOUR_OF_DAY, hour);
		return c.getTime();
	}
	/**
	 * 例如：4.5小时，则*60*60 然后转为字符串去掉.后面的数，再转为整型
	 * @param date
	 * @param hour
	 * @return
	 */
	public static Date addSeconds(Date date,double hour) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		double dseconds = hour*60*60;
		String sseconds = Double.toString(dseconds);
		sseconds = sseconds.substring(0, sseconds.indexOf("."));
		int senconds = Integer.parseInt(sseconds);
		c.add(Calendar.SECOND,senconds);
		return c.getTime();
	}
	
	/**
	 * 返回增加后的时间格式
	 * @param date 时间
	 * @param type day;hour;minute;second;
	 * @param num 数量
	 * @return 时间
	 * @throws ParseException
	 * @author zhirong.tang
	 * @date 2014年7月9日
	 */
	public static String addDateByType(String date,String type,double num) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		Date d = sdf.parse(date);
		long time = d.getTime(); 
		if(type.equals("day")){
			num = num*24*60*60*1000; 
			time+=num; 
		}else if(type.equals("hour")){
			num = num*60*60*1000; 
			time+=num; 
		}else if(type.equals("minute")){
			num = num*60*1000; 
			time+=num; 
		}else if(type.equals("second")){
			time+=num*1000; 
		}

		Date d2=new Date(time);
		return sdf.format(d2);

	}

	/**
	 * 格式化成 当天 00:00:00 的一个timestamp时间类型
	 * @return
	 */
	public static String forMatStartDay(){
		Date curDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		return sdf.format(curDate);
	}
	/**
	 * 格式化成 指定日期 00:00:00 的一个timestamp时间类型
	 * @return
	 */
	public static Date forMatStartDate(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		return strToDateLong(sdf.format(date));
	}
	/**
	 * 格式化成 指定日期 23:59:059 的一个timestamp时间类型
	 * @return
	 */
	public static Date forMatEndDate(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		return strToDateLong(sdf.format(date));
	}

	/**
	 * 获取本周一 凌晨时间
	 * @return
	 */
	public static String getMondayOfWeek() {
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		int i = 2 - c.get(Calendar.DAY_OF_WEEK);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, i);
		return sdf.format(calendar.getTime());
	}

	/**
	 * 获取本月第一天 凌晨时间
	 * @return
	 */
	public static String getFirstDayOfMonth(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00");

		//获取前月的第一天
		Calendar   cal_1=Calendar.getInstance();//获取当前日期
		cal_1.add(Calendar.MONTH, 0);
		cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
		String firstDay = format.format(cal_1.getTime());
		return firstDay;
	}

	/**
	 * 根据年 月 获取对应的月份 天数
	 * */
	public static int getDaysByYearMonth(int year, int month) {

		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);
		a.roll(Calendar.DATE, -1);
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	/**
	 * 将毫秒值转为 天时分
	 * @param date
	 * @return
	 */
	public static String getMillisecondTurnDay(long date){
		String str = "";
		long day = date / 86400000;
		long hour = (date % 86400000) / 3600000;
		long minute = (date % 86400000 % 3600000) / 60000;
		if (day > 0) {
			str = String.valueOf(day) + "天";
		}
		if (hour > 0) {
			str += String.valueOf(hour) + "小时";
		}
		if (minute > 0) {
			str += String.valueOf(minute) + "分钟";
		}
		return str;
	}


	/**
	 * 将毫秒值转为 天时分秒
	 * @param date
	 * @return
	 */
	public static String getMillisecondTurnSecond(long date){
		String str = "";
		long day = date / 86400000;
		long hour = (date % 86400000) / 3600000;
		long minute = (date % 86400000 % 3600000) / 60000;
		long second = (date % 86400000 % 3600000 % 60000) / 1000;
		if (day > 0) {
			str = String.valueOf(day) + "天";
		}
		if (hour > 0) {
			str += String.valueOf(hour) + "小时";
		}
		if (minute > 0) {
			str += String.valueOf(minute) + "分钟";
		}
		if (second > 0) {
			str += String.valueOf(second) + "秒";
		}
		return str;
	}

}
