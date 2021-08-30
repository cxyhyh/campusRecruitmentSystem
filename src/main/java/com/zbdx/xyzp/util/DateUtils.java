/**
 * @Copyright: 2010 杭州海康威视系统技术有限公司
 * @address: http://www.hikvision.com
 * @ProjectName: CMS基线平台
 * @Description: 公司内部的基础平台
 */
package com.zbdx.xyzp.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 时间，日期处理工具类
 *
 * @author zmh 2019年2月28日
 *
 */
@Slf4j
public class DateUtils {

	/**
	 * 获取当前时间yyyy-MM-dd HH:mm:ss
	 *
	 * @return String yyyy-MM-dd HH:mm:ss
	 */
	public static String getDateString() {
		// 设置日期格式
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = df.format(new Date());
		return date;
	}

	/**
	 * 根据String型时间，获取long型时间，单位毫秒
	 *
	 * @param inVal
	 *            时间字符串
	 * @return long型毫秒时间
	 */
	public static long fromDateStringToLong(String inVal) {
		Date date = null;
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");
		long time=0L;
		try {
			date = inputFormat.parse(inVal);
			if(date!=null){
				time=date.getTime();
			}
		} catch (Exception e) {
			return time;
		}
		return time;
	}

	/**
	 * 字符串转换为Date类型，参数格式yyyy-MM-dd HH:mm:ss
	 * @throws ParseException
	 * @param data
	 *            时间字符串
	 * @return Date型时间
	 */
	public static Date StringToDate(String data) throws ParseException {
		if("".equals(data)||data==null){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = sdf.parse(data);
		return date;
	}

	/**
	 * 获取今天日期
	 * @param format 日期格式
	 * @return
	 * @throws ParseException
	 */
	public static String getToday(String format){
            //获取当前日期
            Date date = new Date();
            SimpleDateFormat sf = new SimpleDateFormat(format);
            String nowDate = sf.format(date);
            return nowDate;

    }

	/**
	 * 获取明天日期
	 * @param format 日期格式
	 * @return
	 * @throws ParseException
	 */
	public static String getTomorrow(String format) throws ParseException{
			//获取当前日期
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat(format);
			String nowDate = sf.format(date);
			//通过日历获取下一天日期
			Calendar cal = Calendar.getInstance();
			cal.setTime(sf.parse(nowDate));
			cal.add(Calendar.DAY_OF_YEAR, +1);
			String nextDate_1 = sf.format(cal.getTime());
		return nextDate_1;
	}

	/**
	 * 获取昨天日期
	 * @param format 日期格式
	 * @return
	 * @throws ParseException
	 */
	public static String getYesterday(String format) throws ParseException{
			//获取当前日期
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat(format);
			String nowDate = sf.format(date);

			System.out.println(nowDate);
			//通过日历获取下一天日期
			Calendar cal = Calendar.getInstance();
			cal.setTime(sf.parse(nowDate));
			cal.add(Calendar.DAY_OF_YEAR, -1);
			String nextDate_1 = sf.format(cal.getTime());
		return nextDate_1;
	}

	/**
	 * 获取单独日期
	 * @return int[0] : 当前年，int[1] : 当前月，int[2] : 当前日
	 */
	public static int[] getdate() throws ParseException{
		Calendar calendar = Calendar.getInstance();
		int year=calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH )+1;
		int day=calendar.get(Calendar.DATE);
		int[] dateYMD=new int[]{year,month,day};
		return dateYMD;
	}

	/**
	 * 获取10分钟前的时间格式
	 * @return Date
	 */
	public static Date get10mmDate() {
		Date date = new Date();
		Date afterDate = new Date(date .getTime() - 1800000);
		return afterDate;
	}

	/**
	 * 根据年月获取新的时间.返回时间格式
	 * @return Date
	 */
	public static Date getNewDateByYearMonth(Date date,String yearMonth) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//时间类型转为字符串处理
		String createDate =  sdf.format(date);
		String newCreateDate = yearMonth+ createDate.substring(10, createDate.length());
		return sdf.parse(newCreateDate);
	}

	/**
	 * 根据年月获取新的时间.返回时间格式
	 * @return Date
	 */
	public static Date getNewDateByYearMonth2(Date date,String yearMonth) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Integer rr =(int)(Math.random()*(600000-180000+1)+180000);
		Long timestamp = date.getTime();
		Long date2 =timestamp+rr;
		String datetime = sdf.format(date2);  // 获取年月日时分秒
		String newCreateDate = yearMonth+ datetime.substring(10, datetime.length());
		return sdf.parse(newCreateDate);
	}

	/**
	 * 根据年月获取新的时间.返回字符串格式
	 * @return Date
	 */
	public static String getNewDateByYearMonthString(String date,String yearMonth) {
		return yearMonth + date.substring(10, date.length());
	}

	/**
	 * 根据年月获取新的时间.返回字符串格式时间上下浮动2000时间戳半个小时左右
	 * @return Date
	 */
	public static String getNewDateByYearMonthStrings(String date,String yearMonth) throws ParseException {
		Integer rr =(int)(Math.random()*(600000-180000+1)+180000);
		Long timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date).getTime();
		Long date2 =timestamp+rr;
		String datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date2);  // 获取年月日时分秒
		return yearMonth + datetime.substring(10, datetime.length());
	}

	/**
	 * 得到几天后的时间
	 *
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date getDateAfter(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return now.getTime();
	}

	/**
	 * 得到当前时间的前N小时
	 *
	 * @return
	 */
	public static Date getBeforeByHourTime(int ihour){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + ihour);
		return calendar.getTime();
	}

	/**
	 * @方法说明 把日期转成符串
	 **/
	public static String DateToString(Date date) {
		if (date != null) {
			return new SimpleDateFormat("yyyy-MM-dd").format(date);
		}
		return null;
	}

	/**
	 * @方法说明 把日期转成符串
	 **/
	public static String DateToStr(Date date) {
		if (date != null) {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		}
		return null;
	}
	/**
	 * @方法说明 把符串转成日期
	 **/
	public static Date strToDate(String srcDate) {
		Date date = null;
		try {
			if (!StringUtils.isEmpty(srcDate)) {
				date = new SimpleDateFormat("yyyy-MM-dd").parse(srcDate);
			}
		} catch (ParseException e) {
			log.error("日期转换时发生ParseException异常", e);
		}
		return date;
	}

	/**
	 * @方法说明 获取某一日期的后一天
	 **/
	public static Date getAfterOneDay(Date date) {
		  Calendar c = Calendar.getInstance();
	        c.setTime(date);
	        int day = c.get(Calendar.DATE);
	        c.set(Calendar.DATE, day + 1);
		return c.getTime();
	}

	/**
	 * @方法说明 获取某一日期是周几
	 **/
	public static int dayForWeek(Date date){
		  Calendar c = Calendar.getInstance();
		  c.setTime(date);
		  int dayForWeek = 0;
		  if(c.get(Calendar.DAY_OF_WEEK) == 1){
		   dayForWeek = 7;
		  }else{
		   dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		  }
		  return dayForWeek;
		 }

	/**
	 * 判断日期格式是否正确
	 * @param sDate 日期
	 * @param dateType 日期格式 如："yyyy-MM-dd"
	 * @return
	 */
	public static boolean isLegalDate(String sDate,String dateType) {
		int legalLen = 10;
		if ((sDate == null) || (sDate.length() != legalLen)) {
			return false;
		}

		DateFormat formatter = new SimpleDateFormat(dateType);
		try {
			Date date = formatter.parse(sDate);
			return sDate.equals(formatter.format(date));
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * @方法说明 把符串转成日期
	 **/
	public static String stToDate(String strDate) {
	     SimpleDateFormat sf1 = new SimpleDateFormat("EEE MMM dd hh:mm:ss z yyyy", Locale.ENGLISH);
	     Date date = null;
		try {
			date = sf1.parse(strDate);
		} catch (ParseException e) {
			log.error("日期转换时发生ParseException异常", e);
		}
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

	public static String getTimeStamp() {
		Date currentDate = new Date();
		Long currentDateLong = currentDate.getTime();

		return currentDateLong.toString();
	}

}
