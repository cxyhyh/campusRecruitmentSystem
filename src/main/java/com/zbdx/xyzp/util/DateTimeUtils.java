package com.zbdx.xyzp.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @类说明 [时间类工具]
 * @author fhz
 * 
 **/
@Slf4j
public class DateTimeUtils {

	private static final ThreadLocal<DateFormat> SHORT_SDF = ThreadLocal.withInitial(()
			-> new SimpleDateFormat("yyyy-MM-dd"));

	private static final ThreadLocal<DateFormat> LONG_SDF = ThreadLocal.withInitial(()
			-> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"));
	/**
	 * @方法说明 把符串转成日期
	 **/
	public static Date StringToDate(String srcDate) {
		Date date = null;
		try {
			if (!StringUtils.isEmpty(srcDate)) {
				date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(srcDate);
			}
		} catch (ParseException e) {
			log.error("日期转换时发生ParseException异常", e);
		}
		return date;
	}

	/**
	 * @方法说明 把日期转时间串
	 **/
	public static String DateToString(Date date) {
		if (date != null) {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		}
		return null;
	}
	
	public static String DateToStr(Date date) {
		if (date != null) {
			return new SimpleDateFormat("yyyy-MM-dd").format(date);
		}
		return null;
	}
	public static String stamp(Date date) {
		if (date != null) {
			return new SimpleDateFormat("yyyyMMddHHmmss").format(date);
		}
		return null;
	}

	public static String stampDay(Date date) {
		if (date != null) {
			return new SimpleDateFormat("yyyyMMdd").format(date);
		}
		return null;
	}
	
	public static String stampMillisecond(Date date) {
		if (date != null) {
			return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(date);
		}
		return null;
	}
	

	/**
	 * @方法说明 将时间戳转换为时间
	 **/
	public static String stampToDate(String str) {
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long lt = new Long(str);
		Date date = new Date(lt);
		res = simpleDateFormat.format(date);
		return res;
	}

	/**
	 * @方法说明 将时间转换为时间戳
	 **/
	public static String dateToStamp(String str) throws ParseException {
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = simpleDateFormat.parse(str);
		long ts = date.getTime();
		res = String.valueOf(ts);
		return res;
	}

	/**
	 * @方法说明 将"yyyy-mm-dd"转换为date
	 **/
	public static Date StringToDateTime(String strDate) {
		Date date = null;
		try {
			if (!StringUtils.isEmpty(strDate)) {
				date = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
			}
		} catch (ParseException e) {
			log.error("日期转换时发生ParseException异常", e);
		}
		return date;
	}

	/**
	 * @ 获取指定时间的那天 00:00:00 的时间
	 * 
	 * @param date
	 * @return
	 */
	public static String getDayBeginTime(String strDate) {
		Date date = null;
		try {
			if (!StringUtils.isEmpty(strDate)) {
				date = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
			}
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
		} catch (ParseException e) {
			log.error("日期转换时发生ParseException异常", e);
		}
		return null;
	}

	/**
	 * @获取指定时间的那天 23:59:59 的时间
	 * 
	 * @return
	 */
	public static String getDayEndTime(String strDate) {
		Date date = null;
		try {
			if (!StringUtils.isEmpty(strDate)) {
				date = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
			}
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
		} catch (ParseException e) {
			log.error("日期转换时发生ParseException异常", e);
		}
		return null;
	}
	
	/**
	 * @方法说明 把long符串转成日期
	 **/
	public static String longStrToString(String longStr) {
		try {
			long parseLong = Long.parseLong(longStr);
			Date date = new Date(parseLong);
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		} catch (Exception e) {
			return null;
		}
	}


	/**
	 * 获得本月的开始时间
	 *
	 * @return
	 */
	public static Date getMonthStartTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		Date dt = null;
		try {
			calendar.set(Calendar.DATE, 1);
			dt = SHORT_SDF.get().parse(SHORT_SDF.get().format(calendar.getTime()));
		} catch (Exception e) {
            log.error("日期转换时发生ParseException异常", e);
		}
		return dt;
	}

    /**
     * 本月的结束时间
     *
     * @return
     */
    public static Date getMonthEndTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Date dt = null;
        try {
            calendar.set(Calendar.DATE, 1);
            calendar.add(Calendar.MONTH, 1);
            calendar.add(Calendar.DATE, -1);
            dt = LONG_SDF.get().parse(SHORT_SDF.get().format(calendar.getTime()) + " 23:59:59.999");
        } catch (Exception e) {
            log.error("日期转换时发生ParseException异常", e);
        }
        return dt;
    }

    /**
     * 当前季度的开始时间
     *
     * @return
     */
    public static Date getQuarterStartTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        Date dt = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 3) {
                calendar.set(Calendar.MONTH, 0);
            }else if (currentMonth >= 4 && currentMonth <= 6) {
                calendar.set(Calendar.MONTH, 3);
            }else if (currentMonth >= 7 && currentMonth <= 9) {
                calendar.set(Calendar.MONTH, 6);
            }else if (currentMonth >= 10 && currentMonth <= 12) {
                calendar.set(Calendar.MONTH, 9);
            }
            calendar.set(Calendar.DATE, 1);
            dt = LONG_SDF.get().parse(SHORT_SDF.get().format(calendar.getTime()) + " 00:00:00.000");
        } catch (Exception e) {
            log.error("日期转换时发生ParseException异常", e);
        }
        return dt;
    }

    /**
     * 当前季度的结束时间
     *
     * @return
     */
    public static Date getQuarterEndTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        Date dt = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 3) {
                calendar.set(Calendar.MONTH, 2);
                calendar.set(Calendar.DATE, 31);
            } else if (currentMonth >= 4 && currentMonth <= 6) {
                calendar.set(Calendar.MONTH, 5);
                calendar.set(Calendar.DATE, 30);
            } else if (currentMonth >= 7 && currentMonth <= 9) {
                calendar.set(Calendar.MONTH, 8);
                calendar.set(Calendar.DATE, 30);
            } else if (currentMonth >= 10 && currentMonth <= 12) {
                calendar.set(Calendar.MONTH, 11);
                calendar.set(Calendar.DATE, 31);
            }
            dt = LONG_SDF.get().parse(SHORT_SDF.get().format(calendar.getTime()) + " 23:59:59.999");
        } catch (Exception e) {
            log.error("日期转换时发生ParseException异常", e);
        }
        return dt;
    }


    /**
     * 当前年的开始时间
     *
     * @return
     */
    public static Date getYearStartTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Date dt = null;
        try {
            calendar.set(Calendar.MONTH, 0);
            calendar.set(Calendar.DATE, 1);
            dt = SHORT_SDF.get().parse(SHORT_SDF.get().format(calendar.getTime()));
        } catch (Exception e) {
            log.error("日期转换时发生ParseException异常", e);
        }
        return dt;
    }

    /**
     * 当前年的结束时间
     *
     * @return
     */
    public static Date getYearEndTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Date dt = null;
        try {
            calendar.set(Calendar.MONTH, 11);
            calendar.set(Calendar.DATE, 31);
            dt = LONG_SDF.get().parse(SHORT_SDF.get().format(calendar.getTime()) + " 23:59:59.999");
        } catch (Exception e) {
            log.error("日期转换时发生ParseException异常", e);
        }
        return dt;
    }

	/**
	 * 获取date所属年的所有月列表及开始/结束时间 开始时间：date[0]，结束时间：date[1]
	 *
	 * @param date
	 * @return
	 */
	public static List<Date[]> yearMonthList(Date date) {
		List<Date[]> result = new ArrayList<>();
		Calendar calendar = Calendar.getInstance();
		Date starttm = getYearStartTime(date);
		Date endtm = getYearEndTime(date);
		calendar.setTime(starttm);
		while (calendar.getTime().before(endtm)) {
			Date tm = calendar.getTime();
			Date st = getMonthStartTime(tm);
			Date et = getMonthEndTime(tm);
			result.add(new Date[]{st, et});
			calendar.add(Calendar.MONTH, 1);
		}
		return result;
	}

    /**
     *
     * @param date
     * @return  yyyy-MM格式的字符串
     */
    public static List<String> getMonthList(Date date) {
        List<String> result = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        Date starttm = getYearStartTime(date);
        Date endtm = getYearEndTime(date);
        calendar.setTime(starttm);
        while (calendar.getTime().before(endtm)) {
            Date tm = calendar.getTime();
            Date st = getMonthStartTime(tm);
            String res = new SimpleDateFormat("yyyy-MM").format(st);
            result.add(res);
            calendar.add(Calendar.MONTH, 1);
        }
        return result;
    }


	/**
	 * 计算俩个日期的相差天数
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static long betweenDays(Date startDate,Date endDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//除以1000是为了转换成秒
		long between=(sdf.parse(sdf.format(endDate)).getTime()-sdf.parse(sdf.format(startDate)).getTime())/1000;

		return between/(24*3600) + 1;
	}

	/**
	 * 替换年份
	 * @param year
	 * @param date
	 * @return
	 */
	public static Date replaceYear(int year, Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.YEAR, year);
		return cal.getTime();
	}
	/**
	 * 获取系统当前年份
	 * @return
	 */
	public static String getCurrentYear(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        return sdf.format(date);
}

}
