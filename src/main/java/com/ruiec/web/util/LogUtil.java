///*
// * 版权所有：深圳源中瑞科技有限公司<br>
// * 网 址：www.ruiec.com<br>
// * 电 话：0755-33581131<br><br>
// */
//
//package com.ruiec.web.util;
//
//
//import com.ruiec.web.model.OperationLog;
//import com.ruiec.web.service.OperationLogService;
//
///**
// * 日志服务接口实现类
// * 
// * @author 熊华松<br>
// * Version 1.0<br>
// * Date: 2016年07月13日
// */
//public class LogUtil {
//	
//	/**
//	 * 保存日志
//	 * 
//	 * @author 熊华松
//	 * @date 2016-07-13
//	 */
//	public static void saveLog(OperationLog log) {
//		OperationLogService logService = (OperationLogService) SpringUtils.getBean("operationLogServiceImpl");
//		logService.insert(log);
//	}
//	
//	/**
//	 * 截取字符串(从起始位置到倒数第一的位置)
//	 * 
//	 * @author 熊华松
//	 * @date 2016-07-13
//	 */
//	public static String substring(String str) {
//		return str.substring(0, str.length() - 1);
//	}
//	
//	/**
//	 * 截取字符串(从起始位置到倒数第一的位置)
//	 * 
//	 * @author 熊华松
//	 * @date 2016-07-13
//	 */
//	public static String substring(StringBuffer str) {
//		if (str.length() == 0) {
//			return "";
//		}
//		return str.toString().substring(0, str.toString().length() - 1);
//	}
//	
//}