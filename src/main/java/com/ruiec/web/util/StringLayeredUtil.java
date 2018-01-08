/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */
package com.ruiec.web.util;

import java.util.List;

/**
 * 字符串分层
 * 
 * @author 张威斌<br>
 * @date 2017年9月28日 下午2:42:22
 */
public class StringLayeredUtil<T> {

	/**
	 * 获取直接子类
	 * 
	 * @author 张威斌<br>
	 * @date 2017年9月28日 下午2:42:46
	 */
	public static List<String> getSub(List<String> list, List<String> sons, String parentId, Integer offset) {
		String parentIdPre = eraseEndZero(parentId);
		for (String item : list) {
			if (!item.equals(parentId) && item.startsWith(parentIdPre)) {
				if (eraseEndZero(item).length() - parentIdPre.length() == offset) {
					sons.add(item);
					getSub(list, sons, item, offset);
				}
			}
		}
		return sons;
	}


	/**
	 * 擦除字符串末位00
	 * 
	 * @author 张威斌<br>
	 * @date 2017年9月28日 下午2:43:30
	 */
	public static String eraseEndZero(String numString) {
		// 判断是否为空
		if (numString == null || numString.isEmpty()) {
			return "";
		}
		try {
			int length = numString.length() / 2;
			for (int i = 0; i < length; i++) {
				numString = numString.endsWith("00") ? numString.substring(0, numString.lastIndexOf("00")) : numString;
			}
		} catch (Exception e) {
			System.out.println("字符串格式错误，请检查");
			e.printStackTrace();
		}
		return numString;
	}

}
