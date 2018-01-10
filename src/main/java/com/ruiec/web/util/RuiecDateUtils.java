
package com.ruiec.web.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.time.DateUtils;

/**
 * 日期工具类
 * 
 * Version 1.0<br>
 * Date: 2015年12月31日
 */
public class RuiecDateUtils {
	
	/**
	 * 获取当前月的第一秒
	 * @return
	 */
	public static Date getMinForMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		String year = format_yyyy(date);
		String month = format_MM(date);
		cal.set(Calendar.YEAR, Integer.parseInt(year));
		cal.set(Calendar.MONTH, Integer.parseInt(month));
		StringBuilder min = new StringBuilder(year + "-" + month); 
		min.append("-" + cal.getMinimum(Calendar.DAY_OF_MONTH));
		min.append(" " + cal.getMinimum(Calendar.HOUR_OF_DAY));
		min.append(":" + cal.getMinimum(Calendar.MINUTE));
		min.append(":" + cal.getMinimum(Calendar.SECOND));
		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(min.toString());
		} catch (ParseException e) {
			return null;
		}
	}
	
	/**
	 * 获取昨天的第一秒
	 * 
	 * Date:2016年07月07日
	 */
	public static Date getFirstSecondOfYesterday(){
		try {
			Date now=new Date();
			String year=RuiecDateUtils.format_yyyy(now);
			String month=RuiecDateUtils.format_MM(now);
			String day=RuiecDateUtils.format_dd(now);
			StringBuilder stringBuilder=new StringBuilder();
			stringBuilder.append(year);
			stringBuilder.append("-");
			stringBuilder.append(month);
			stringBuilder.append("-");
			stringBuilder.append(day);
			stringBuilder.append(" 00:00:00");
			return DateUtils.addDays(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(stringBuilder.toString()),-1);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 获取昨天的最后一秒
	 * 
	 * Date:2016年07月07日
	 */
	public static Date getLastSecondOfYesterday(){
		try {
			Date now=new Date();
			String year=RuiecDateUtils.format_yyyy(now);
			String month=RuiecDateUtils.format_MM(now);
			String day=RuiecDateUtils.format_dd(now);
			StringBuilder stringBuilder=new StringBuilder();
			stringBuilder.append(year);
			stringBuilder.append("-");
			stringBuilder.append(month);
			stringBuilder.append("-");
			stringBuilder.append(day);
			stringBuilder.append(" 23:59:59");
			return DateUtils.addDays(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(stringBuilder.toString()),-1);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * yyyyMM格式转换为日期
	 */
	public static Date parse_yyyyMM(String yyyyMMString){
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyyMM").parse(yyyyMMString);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return date;
	}
	
	/**
	 * yyyy-MM格式转换为日期
	 */
	public static Date parse_yyyy_MM(String yyyyMMString){
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM").parse(yyyyMMString);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return date;
	}
	
	/**
	 * yyyy格式转换为日期
	 */
	public static Date parse_yyyy(String yyyyStr){
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy").parse(yyyyStr);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return date;
	}
	
	/**
	 * yyyyMMdd格式转换为日期
	 */
	public static Date parse_yyyyMMdd(String yyyyMMddString){
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyyMMdd").parse(yyyyMMddString);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return date;
	}
	
	/**
	 * 将yyyy-MM-dd的字符串转换为日期
	 * Date：2016年09月06日
	 */
	public static Date parse_yyyy_MM_dd(String yyyyMMddString){
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(yyyyMMddString);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return date;
	}
	
	/**
	 * yyyyMMddHHmm格式转换为日期
	 * @param yyyyMMddHHmmString
	 * @return
	 * Date: 2016-06-14
	 */
	public static Date parse_yyyyMMddHHmm(String yyyyMMddHHmmString) {
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(yyyyMMddHHmmString);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return date;
	}
	
	/**
	 * yyyyMMddHHmmss格式转换为日期
	 * @param yyyyMMddHHmmssString
	 * @return
	 * Date: 2016-06-14
	 */
	public static Date parse_yyyyMMddHHmmss(String yyyyMMddHHmmssString) {
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(yyyyMMddHHmmssString);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return date;
	}
	
	/**
	 * ××时××分格式转换为××年××月××日 ××时××分
	 * @param time
	 * @return
	 * Date: 2016-06-14
	 */
	public static Date parse_timeToyyyyMMddHHmm(String time) {
		Date date = new Date();
		String yyyyMMddHHmmString = RuiecDateUtils.format_yyyy_MM_dd(date) + " " + time;
		return parse_yyyyMMddHHmm(yyyyMMddHHmmString);
	}
	
	/**
	 * 日期转换为yyyyMM格式
	 */
	public static String format_yyyyMM(Date date){
		return new SimpleDateFormat("yyyyMM").format(date);
	}
	
	/**
	 * 日期转换为yyyy-MM格式
	 */
	public static String format_yyyy_MM(Date date){
		return new SimpleDateFormat("yyyy-MM").format(date);
	}
	
	/**
	 * 日期转换为yyyy年MM月格式
	 */
	public static String format_yyyynMMy(Date date){
		return new SimpleDateFormat("yyyy年MM月").format(date);
	}
	
	/**
	 * 日期转换为yyyy-MM-dd HH:mm:ss格式
	 */
	public static String format_yyyy_MM_dd_HH_mm_ss(Date date){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}
	
	/**
	 * 日期转换为yyyy-MM-dd HH:mm格式
	 */
	public static String format_yyyy_MM_dd_HH_mm(Date date){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
	}
	
	/**
	 * 日期转换为HH:mm格式
	 */
	public static String format_HH_mm(Date date){
		return new SimpleDateFormat("HH:mm").format(date);
	}
	
	/**
	 * 日期转换为dd格式
	 */
	public static String format_dd(Date date){
		return new SimpleDateFormat("dd").format(date);
	}
	
	/**
	 * 日期转换为HH格式
	 */
	public static String format_HH(Date date){
		return new SimpleDateFormat("HH").format(date);
	}
	
	/**
	 * 日期转换为MM格式
	 */
	public static String format_MM(Date date){
		return new SimpleDateFormat("MM").format(date);
	}
	
	/**
	 * 日期转换为yyyy格式
	 */
	public static String format_yyyy(Date date){
		return new SimpleDateFormat("yyyy").format(date);
	}
	/**
	 * 日期转换为yyyyMMdd格式
	 */
	public static String format_yyyyMMdd(Date date){
		return new SimpleDateFormat("yyyyMMdd").format(date);
	}
	
	/**
	 * 日期转换为yyyyMMddHH格式
	 */
	public static String format_yyyyMMddHH(Date date){
		return new SimpleDateFormat("yyyyMMddHH").format(date);
	}
	
	/**
	 * 日期转换为yyyy-MM-dd格式
	 */
	public static String format_yyyy_MM_dd(Date date){
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
	
	/**
	 * 日期转换为yyMMddHHmm格式
	 */
	public static String format_yyMMddHHmm(Date date){
		return new SimpleDateFormat("yyMMddHHmm").format(date);
	}
	
	/**
	 * 获取昨天的日期
	 */
	public static String getYesterday() {
		Calendar cal=Calendar.getInstance();//获取当前时间
		cal.add(Calendar.DATE, -1);
		return RuiecDateUtils.format_yyyy_MM_dd(cal.getTime());
	}
	
	/**
	 * 获取本周一的日期
	 */
	public static String getThisMonday() {
		Calendar cal=Calendar.getInstance();//获取当前时间
		cal.set(Calendar.DAY_OF_WEEK, 2);//设置为本周的第二天（星期一）
		return RuiecDateUtils.format_yyyy_MM_dd(cal.getTime());
	}
	
	/**
	 * 获取本周日的日期
	 */
	public static String getThisSunday() {
		Calendar cal=Calendar.getInstance();//获取当前时间
		cal.add(Calendar.WEEK_OF_MONTH, 1);//下个星期
		cal.set(Calendar.DAY_OF_WEEK, 1);//设置为下个星期的第一天（星期天）
		return RuiecDateUtils.format_yyyy_MM_dd(cal.getTime());
	}
	
	/**
	 * 获取上周一的日期
	 */
	public static String getLastMonday() {
		Calendar cal=Calendar.getInstance();//获取当前时间
		cal.add(Calendar.WEEK_OF_MONTH, -1);//上周
		cal.set(Calendar.DAY_OF_WEEK, 2);
		return RuiecDateUtils.format_yyyy_MM_dd(cal.getTime());
	}
	
	/**
	 * 获取上周日的日期
	 */
	public static String getLastSunday() {
		Calendar cal=Calendar.getInstance();//获取当前时间
		cal.set(Calendar.DAY_OF_WEEK, 1);
		return RuiecDateUtils.format_yyyy_MM_dd(cal.getTime());
	}
	
	/**
	 * 获取本月第一天的日期
	 */
	public static String getThisMonthOneDay() {
		Calendar cal=Calendar.getInstance();//获取当前时间
		cal.set(Calendar.DAY_OF_MONTH, 1);//把日期设为1号
		return RuiecDateUtils.format_yyyy_MM_dd(cal.getTime());
	}
	
	/**
	 * 获取本月最后一天的日期
	 */
	public static String getThisMonthLastDay() {
		Calendar cal=Calendar.getInstance();//获取当前时间
		cal.add(Calendar.MONTH, 1);//增加一个月
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);//日期倒退一天
		return RuiecDateUtils.format_yyyy_MM_dd(cal.getTime());
	}
	
	/**
	 * 获取上月第一天的日期
	 */
	public static String getLastMonthOneDay() {
		Calendar cal=Calendar.getInstance();//获取当前时间
		cal.add(Calendar.MONTH, -1);//倒退一个月
		cal.set(Calendar.DAY_OF_MONTH, 1);//把日期设为1号
		return RuiecDateUtils.format_yyyy_MM_dd(cal.getTime());
	}
	
	/**
	 * 获取上月最后一天的日期
	 */
	public static String getLastMonthLastDay() {
		Calendar cal=Calendar.getInstance();//获取当前时间
		cal.set(Calendar.DAY_OF_MONTH, 1);//设置为当前月的第一天
		cal.add(Calendar.DAY_OF_MONTH, -1);//日期倒退一天即为上个月最后一天
		return RuiecDateUtils.format_yyyy_MM_dd(cal.getTime());
	}
	
	/**
	 * 获取某月第一天的日期
	 * @param month 月份(yyyy-MM)
	 */
	public static String getMonthOneDay(String month){
		Calendar cal=Calendar.getInstance();
		cal.setTime(RuiecDateUtils.parse_yyyy_MM(month));
		cal.set(Calendar.DAY_OF_MONTH, 1);//把日期设为1号
		return RuiecDateUtils.format_yyyy_MM_dd(cal.getTime());
	}
	
	/**
	 * 获取某月最后一天的日期
	 * @param month 月份(yyyy-MM)
	 */
	public static String getMonthLastDay(String month){
		Calendar cal=Calendar.getInstance();
		cal.setTime(RuiecDateUtils.parse_yyyy_MM(month));
		cal.add(Calendar.MONTH, 1);//增加一个月
		cal.set(Calendar.DAY_OF_MONTH, 1);//设置为当前月的第一天
		cal.add(Calendar.DAY_OF_MONTH, -1);//日期倒退一天
		return RuiecDateUtils.format_yyyy_MM_dd(cal.getTime());
	}
	
	/**
	 * 数字转换文字月份
	 */
	public static String getMonth(int i) {
		String lastmonth="";		
		switch (i) {
		case 1:
			lastmonth="一月";
			break;
		case 2:
			lastmonth="二月";
			break;
		case 3:
			lastmonth="三月";
			break;
		case 4:
			lastmonth="四月";
			break;
		case 5:
			lastmonth="五月";
			break;
		case 6:
			lastmonth="六月";
			break;
		case 7:
			lastmonth="七月";
			break;
		case 8:
			lastmonth="八月";
			break;
		case 9:
			lastmonth="九月";
			break;
		case 10:
			lastmonth="十月";
			break;
		case 11:
			lastmonth="十一月";
			break;
		case 12:
			lastmonth="十二月";
			break;
		}
		return lastmonth;
	}
	
	/**
	 * 获取当前月的最后一秒
	 * @return
	 */
	public static Date getMaxForMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		String year = format_yyyy(date);
		String month = format_MM(date);
		cal.set(Calendar.YEAR, Integer.parseInt(year));
		cal.set(Calendar.MONTH, Integer.parseInt(month));
		StringBuilder max = new StringBuilder(year + "-" + month); 
		max.append("-" + cal.getMaximum(Calendar.DAY_OF_MONTH));
		max.append(" " + cal.getMaximum(Calendar.HOUR_OF_DAY));
		max.append(":" + cal.getMaximum(Calendar.MINUTE));
		max.append(":" + cal.getMaximum(Calendar.SECOND));
		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(max.toString());
		} catch (ParseException e) {
			return null;
		}
	}
	
	
	public static Date transfer2Date(String year, String month, String day)
			throws ParseException {
		StringBuilder sb = new StringBuilder();
		sb.append(year).append("-").append(month).append("-").append(day);
		return new SimpleDateFormat("yyyy-MM-dd").parse(sb.toString());
	}
	/**
	 * yyyy-MM-dd
	 * Date: 2015年8月28日
	 */
	public static String formatYyyy_MM_dd(Date date){
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
	
	/**
	 * yyyy-MM-dd
	 * Date: 2015年8月28日
	 */
	public static String transferDate2String(Date date) {
		if (date == null) {
			return null;
		}
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
	public static String transferDate2String(Date date, String pattern) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat dateFormate = new SimpleDateFormat(pattern);
		return dateFormate.format(date);
	}


	/**
	 * 验证日期字符串是否是YYYY-MM-DD格式
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDataFormat(String str) {
		boolean flag = false;
		// String
		// regxStr="[1-9][0-9]{3}-[0-1][0-2]-((0[1-9])|([12][0-9])|(3[01]))";
		String regxStr = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$";
		Pattern pattern1 = Pattern.compile(regxStr);
		Matcher isNo = pattern1.matcher(str);
		if (isNo.matches()) {
			flag = true;
		}
		return flag;
	}
	
	/**
     * 获取凌晨时间
     * @param date
     * @flag 0 返回yyyy-MM-dd 00:00:00日期<br>
     *       1 返回yyyy-MM-dd 23:59:59日期
     * @return
     */
    public static Date getWeeHours(Date date, int flag) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        //时分秒（毫秒数）
        long millisecond = hour*60*60*1000 + minute*60*1000 + second*1000;
        //凌晨00:00:00
        cal.setTimeInMillis(cal.getTimeInMillis()-millisecond);
         
        if (flag == 0) {
            return cal.getTime();
        } else if (flag == 1) {
            //凌晨23:59:59
            cal.setTimeInMillis(cal.getTimeInMillis()+23*60*60*1000 + 59*60*1000 + 59*1000);
        }
        return cal.getTime();
    }
	
    /**
     * 获取n天后的时间
     * 
     * @param date 开始时间
     * @param n 几天
     * 
     * Date：2016年11月04日
     */
    public static Date getNDaysTime(Date date,int n) {
    	Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, n);
        date = calendar.getTime();
        return date;
	}
    
    /**
     * 求两个时间之间的时间差（不满1秒按1秒算，分、时、天同）
     * 
     * @param startTime 开始时间（减数）
     * @param endTime 结束时间（被减数）
     * @param unit 单位   0:秒；  1:分；  2:时；  3:天；<br>
     * 
     * Date：2016年11月04日
     */
    public static long getTimeDifference (Date startTime,Date endTime,int unit) {
    	long timeDifference=0;
    	long time=endTime.getTime()-startTime.getTime();
		switch (unit) {
		case 0:
			timeDifference=time%1000==0?time/1000:time/1000+1;//秒
			break;
		case 1:
			timeDifference=time%(1000*60)==0?time/(1000*60):time/(1000*60)+1;//分
			break;
		case 2:
			timeDifference=time%(1000*60*60)==0?time/(1000*60*60):time/(1000*60*60)+1;//时
			break;
		case 3:
			timeDifference=time%(1000*60*60*24)==0?time/(1000*60*60*24):time/(1000*60*60*24)+1;//天
			break;
		}
		return timeDifference;
	}
    
    /**
     * 求两个时间之间的时间差(负数返回0，正数正常返回)
     * 例如：
     * date1=2017-07-21;
     * date2=2017-07-25; 
     * date2-date1返回5天
     * date1-date2则返回0；
     * 
     * @param startTime 开始时间（减数）
     * @param endTime 结束时间（被减数）
     * @param unit 单位   0:秒；  1:分；  2:时；  3:天；<br>
     * 
     * Date：2016年11月04日
     */
    public static long getTimePositiveDifference (Date startTime,Date endTime,int unit) {
    	long timeDifference=0;
    	long time=endTime.getTime()-startTime.getTime();
		switch (unit) {
		case 0:
			timeDifference=time/1000;//秒
			break;
		case 1:
			timeDifference=time/(1000*60);//分
			break;
		case 2:
			timeDifference=time/(1000*60*60);//时
			break;
		case 3:
			timeDifference=time/(1000*60*60*24);//天
			break;
		}
		if(timeDifference<0){
			return 0;
		}
		return timeDifference+1;
	}
    
    /**
     * 根据年月获取当月有几周
     * @param str 2017-07
     * Date：2017年07月12日
     */
    public static int getWeekCount(String str) {
    	String[] strs=str.split("-");
    	int year=Integer.valueOf(strs[0]);
    	int month=Integer.valueOf(strs[1]);
    	Calendar c = Calendar.getInstance();
    	//设置年份
    	c.set(Calendar.YEAR, year);
    	//设置月份
    	c.set(Calendar.MONTH, month);
    	c.setFirstDayOfWeek(Calendar.MONDAY);
    	return c.getActualMaximum(Calendar.WEEK_OF_MONTH);
	}
    
    /**  
     * 根据日期字符串判断当月第几周  
     * @param startDate  
     * Date：2017年07月12日
     */  
    public static int getWeek(String startDate) throws Exception{  
        Date date =RuiecDateUtils.parse_yyyy_MM_dd(startDate);
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);  
        //第几周  
        return calendar.get(Calendar.WEEK_OF_MONTH);  
    }  
    
    /**
     * 根据某年或某月第几周获取对应周第一天和最后一天日期
     * @param year 2017
     * @param month 7
     * @param week 3
     * Date：2017年07月12日
     */
    public static Map<String, String> getDateByWeek(Integer year, Integer month, Integer week) {
    	Map<String, String> map=new HashMap<String, String>();
    	Calendar c = Calendar.getInstance();
    	if (year!=null) {
    		//如果年份不为空，设置年份
        	c.set(Calendar.YEAR,year);
		}
    	if (month!=null) {
    		//如果月份不为空，设置月份
        	c.set(Calendar.MONTH,month);
		}
    	if (month!=null&&week!=null) {
    		//如果月周都不为空，根据月设置第几周
        	c.set(Calendar.WEEK_OF_MONTH,week);
		}
    	if (year!=null&&week!=null&&month==null) {
    		//如果年周都不为空且月份为空，根据年设置第几周
        	c.set(Calendar.WEEK_OF_YEAR,week);
		}
    	//本周第一天
    	c.set(Calendar.DAY_OF_WEEK, 1);
    	map.put("startDate", RuiecDateUtils.format_yyyy_MM_dd(c.getTime()));
    	//本周 最后一天
    	c.set(Calendar.DAY_OF_WEEK, 7);
    	map.put("endDate", RuiecDateUtils.format_yyyy_MM_dd(c.getTime()));
    	return map;
	}
    
    /**
     * 将日期调整到当天最后一秒
     * Date：2017年07月25日
     */
    public static Date getDateLastSecond(Date date) {
    	//取当前时间的日期字符串+23:59:59
		String dateStr=RuiecDateUtils.format_yyyy_MM_dd(date)+" 23:59:59";
		//转回时间格式
		return RuiecDateUtils.parse_yyyyMMddHHmmss(dateStr);
	}
    
    /**
     * 获取某月所有周的周一和周日日期（按周六算：周六在哪个月所在周就算是那月的周）
     * @param month 月份（yyyy-MM）
     * Date：2017年08月05日
     * @throws ParseException 
     */
    public static List<String> getMonthOfTheWeeks(String month) throws ParseException {
    	List<String> strings=new ArrayList<String>();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        sdf1.setLenient(false);
        SimpleDateFormat sdf2 = new SimpleDateFormat("EEE");
        int yyyy=Integer.valueOf(month.split("-")[0]);
        int mm=Integer.valueOf(month.split("-")[1]);
        
        //每月的天数，默认31
        int d=31;
        
        //根据不同月份来分别计算所含周数
        if(mm==2){
        	//如果当前为2月，计算当前是平年还是闰年
        	if((yyyy%4==0&&yyyy%100!=0)||yyyy%400==0){
        		//闰年
        		d=29;
        	}else {
				//平年
        		d=28;
			}
        	
        }else if(mm==4||mm==6||mm==9||mm==11){
        	//当前月份为小月
        	d=30;
        }
        
        //某月所含周数
        for(int i = 1; i <= d; i++){
            Date date = sdf1.parse(month + "-" + i);
            if(sdf2.format(date).equals("星期六")){
            	//保存所在周的周一和周日
            	String s1=RuiecDateUtils.format_yyyy_MM_dd(DateUtils.addDays(date, -5));
            	String s2=RuiecDateUtils.format_yyyy_MM_dd(DateUtils.addDays(date, 1));
            	//拼接周一和周日的日期，并添加至list
            	strings.add(s1+","+s2);
            }
        }
        return strings;
	}
    
    /**
     * 根据日期获取所在周的周一和周日日期
     * Date：2017年8月5日
     */
    public static String getWeekDate(Date date) {
    	 Calendar cal = Calendar.getInstance();
         cal.setTime(date);
         int d = 0;
         if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
             d = -6;
         } else {
             d = 2 - cal.get(Calendar.DAY_OF_WEEK);
         }
         cal.add(Calendar.DAY_OF_WEEK, d);
         // 所在周开始日期
         String data1 = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
         cal.add(Calendar.DAY_OF_WEEK, 6);
         // 所在周结束日期
         String data2 = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
         return data1+","+data2;
	}
    
    
    /**
     * 根据日期获取所在周的周一的日期
     * Date：2017年12月20日
     */
    public static String getWeekDateOne(Date date) {
    	 Calendar cal = Calendar.getInstance();
         cal.setTime(date);
         int d = 0;
         if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
             d = -6;
         } else {
             d = 2 - cal.get(Calendar.DAY_OF_WEEK);
         }
         cal.add(Calendar.DAY_OF_WEEK, d);
         // 所在周开始日期
         String data1 = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
         cal.add(Calendar.DAY_OF_WEEK, 6);
         return data1;
	}
    /**
     * 获取到对应的date类型，查询出这个日期是周几，
     * Date：2017年12月20日
     */
    public static String getWeekOfDate(Date dt) {
    	/*String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};*/
    	String[] weekDays = {"7", "1", "2", "3", "4", "5", "6"};
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(dt);
    	int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
    	if (w < 0)
    	w = 0;
    	return weekDays[w];
    	}
}
