package com.hydee.ydky.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.http.util.TextUtils;

public class DateUtils {
	private static final SimpleDateFormat SMDF_NORMAL = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat SMDF_DATE = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 获取当前日期时间对象
	 */
	public static Date now() {
		return new Date();
	}
	
	/**
	 * 比较时间戳与当前时间间隔
	 * @param timstamp
	 * @param string
	 * @param enablesec
	 * @return
	 */
	public static Long compareSeconds(String timstamp, String fmt) {
		long nowTime = new Date().getTime();
		try {
			if(StringUtils.isAnyEmpty(timstamp, fmt)) return nowTime / 1000;
			return (nowTime - new SimpleDateFormat(fmt).parse(timstamp).getTime()) / 1000;
		} catch (Exception e) {
			return nowTime / 1000;
		}
	}
	
	/**
	 * 按指定格式格式化当前日期
	 * @param fmt
	 * @return
	 */
	public static String formatNow(String fmt) {
		return new SimpleDateFormat(fmt).format(new Date());
	}
	
	/**
	 * 格式化时间
	 * @param date
	 * @return
	 */
	public static String format(Date date) {
		if(date == null) return null;
		return SMDF_NORMAL.format(date);
	}
	
	/**
	 * 格式化日期
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date) {
		if(date == null) return null;
		return SMDF_DATE.format(date);
	}
	
	/**
     * Java将Unix时间戳转换成指定格式日期字符串
     * @param timestampString 时间戳 如："1473048265";
     * @param formats 要格式化的格式 默认："yyyy-MM-dd HH:mm:ss";
     *
     * @return 返回结果 如："2016-09-05 16:06:42";
     */
    public static String TimeStamp2Date(String timestampString, String formats) {
        if (TextUtils.isEmpty(formats))
            formats = "yyyy-MM-dd HH:mm:ss";
        DecimalFormat decimalFormat = new DecimalFormat("###################.###########");
        Long timestamp = Long.parseLong(decimalFormat.format(Double.valueOf(timestampString))) * 1000;
        return new SimpleDateFormat(formats, Locale.CHINA).format(new Date(timestamp));
    }
    
    /**
     * 功能描述：时间相减得到天数
     * @param beginDate
     * @param endDate
     * @return
     * long 
     * @author Administrator
     */
    public static long getDaySub(Date beginDate,Date endDate){
        return (endDate.getTime()-beginDate.getTime()) / (24*60*60*1000);
    }
    
    /**
     * 获取当前时间前后间隔后的时间
     * 正式为后,负数为减
     * @param filed		:时、分、秒类型,例如Calendar.MINUTE
     * @param amount	:间隔时间
     * @return
     */
    public static Date getDateAmount(int filed, int amount) {
    	Calendar cal = Calendar.getInstance();
    	cal.add(filed, amount);
    	return  cal.getTime();
	}

    /**
     * 获取今日日期
     * @return
     */
	public static String today() {
		return SMDF_DATE.format(new Date());
	}
	
	/**
	 * 将按年的自然周换算成按月的自然周
	 * @param week
	 * @return
	 */
	public static int getWeekIndex(int week) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.WEEK_OF_YEAR, week);
		return calendar.get(Calendar.WEEK_OF_MONTH);
	}
	
	/**
	 * 获取指定起止日期中的所有符合日期类型条件的数值列表
	 * @param fieldNumber
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	private static final SimpleDateFormat SMDF_DATE2 = new SimpleDateFormat("yyyy-MM");
	public static List<String> listAllDateByConditions(int fieldNumber, Date startTime, Date endTime) {
		if(startTime == null || endTime == null) return null;
		List<String> fieldNumbers = new ArrayList<>();
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(startTime);
		// 如果是按年,则排除截止日期滞后的年份
		if(Calendar.YEAR == fieldNumber) {
			cal.set(Calendar.MONTH, 11);
			cal.set(Calendar.DATE, 31);
		}
		// 循环遍历出指定日期类型值(加上最大限制365天数据,避免无限循环浪费资源)
		for(int i=0;i<365;i++) {
			if(Calendar.MONTH == fieldNumber) {
				// 按月计数+1,ORACEL从1月计数非0计数
				fieldNumbers.add(SMDF_DATE2.format(cal.getTime()));
			} else if(Calendar.DATE == fieldNumber) {
				// 按日计算则统计为yyyyMMdd
				fieldNumbers.add(SMDF_DATE.format(cal.getTime()));
			} else {
				fieldNumbers.add(cal.get(fieldNumber)+"");
			}
			// 比较当前日期是否超过截止日期
			if(cal.getTimeInMillis() >= endTime.getTime()) break;
			// 追加一个日期单位
			cal.add(fieldNumber, 1);
		}
		return fieldNumbers;
	}
	
}
