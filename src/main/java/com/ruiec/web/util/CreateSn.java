/**
 */

package com.ruiec.web.util;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生成唯一序列号工具类
 * Version: 1.0<br>
 * Date: 2016年6月12日
 */
public class CreateSn {
	//订单号后缀
	private static AtomicInteger suffix=new AtomicInteger(0);
	
	/**
	 * 生成唯一序列号(一个的情况)
	 * Date: 2016年6月12日
	 */
	//因为key的位数不是固定的，按sn排序的时候结果会出现不按预料的情况，例如"9">"10"，也就是订单号在后的排序出现在前（正序）
	public static String getNextSn() {
//			KeyService keyService = (KeyService) SpringUtils.getBean("keyServiceImpl");
//			String key = keyService.getNextKey();
//			return RuiecDateUtils.transferDate2String(new Date(), "yyyyMMddHHmm") + key;
		//因为suffix.addAndGet后下面又有get操作的话是两个以上的动作，所以改为使用临时变量保存
		int tmp = suffix.addAndGet(1);
		return RuiecDateUtils.transferDate2String(new Date(), "MMddHHmmssSSS")+String.format("%010d", tmp);
	}
	
	/**
	 * 订单号后缀
	 */
	public static AtomicInteger getSuffix() {
		return suffix;
	}
	
	/**
	 * 订单号后缀，不需要参数，初始化订单号后缀
	 */
	public static void setSuffix() {
		suffix.set(0);
	}
	
}
