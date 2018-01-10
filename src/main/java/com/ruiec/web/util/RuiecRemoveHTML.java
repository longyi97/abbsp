package com.ruiec.web.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 去除所有标签
 * @date 2017年12月11日 下午9:48:50
 */
public class RuiecRemoveHTML {
	private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
	private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
	private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
//	private static final String regEx_space = "\\s*|\t|\r|\n";// 定义空格回车换行符
	private static final String regEx_matcher = "^\\s*|\\s*$";// 删除行首行尾的空白字符(包括空格、制表符、换页符等等)

	/**
	 * 删除Html标签
	 * 
	 * @date 2017年12月11日 下午9:02:42
	 */
	public static String delHTMLTag(String htmlStr) {
		Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(htmlStr);
		htmlStr = m_script.replaceAll(""); // 过滤script标签
		Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll(""); // 过滤style标签
		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll(""); // 过滤html标签
		/*Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
		Matcher m_space = p_space.matcher(htmlStr);
		htmlStr = m_space.replaceAll(""); // 过滤空格回车标签
*/		Pattern p_matcher = Pattern.compile(regEx_matcher, Pattern.CASE_INSENSITIVE);
		Matcher m_matcher = p_matcher.matcher(htmlStr);
		htmlStr = m_matcher.replaceAll(""); // 过滤空格回车标签
		
		htmlStr = htmlStr.replace("&nbsp;", "");// 过滤&nbsp;
		return htmlStr.trim(); // 返回文本字符串
	}

	
}