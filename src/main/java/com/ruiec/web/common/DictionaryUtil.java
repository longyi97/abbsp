package com.ruiec.web.common;

import java.util.Dictionary;
import java.util.HashMap;

import com.ruiec.web.model.Module;


/**
 * 字典数据工具类
 * Date：2017年09月29日
 */
public class DictionaryUtil {

	/** 字典全局静态变量 */
	private static HashMap<String, Module> map=new HashMap<String, Module>();
	
	/** 字典全局静态变量 */
	public static HashMap<String, Module> getMap() {
		return map;
	}
	
	/** 字典全局静态变量 */
	public static void setMap(HashMap<String, Module> map2) {
		DictionaryUtil.map = map2;
	}
	
}
