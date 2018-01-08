package com.ruiec.web.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 获取图片路径
 * 
 * @author 钟国城<br>
 * @date 2017年12月11日 下午10:03:51
 */
public class RuiecGetImage {
	/**
	 * 获取图片路径
	 * 
	 * 从HTML源码中提取图片路径，最后以一个 String 类型的 List 返回，如果不包含任何图片，则返回一个 size=0 的List
	 * 需要注意的是，此方法只会提取以下格式的图片：.jpg|.bmp|.eps|.gif|.mif|.miff|.png|.tif|.tiff|.svg|.wmf|.jpe|.jpeg|.dib|.ico|.tga|.cut|.pic
	 * 
	 * @param htmlCode
	 *            HTML源码
	 * @author zhongguocheng<br>
	 * @date 2017年12月11日 下午10:03:02
	 */
	public static String getImageSrc(String htmlCode) {
		List<String> imageSrcList = new ArrayList<String>();
		Pattern p = Pattern.compile(
				"<img\\b[^>]*\\bsrc\\b\\s*=\\s*('|\")?([^'\"\n\r\f>]+(\\.jpg|\\.bmp|\\.eps|\\.gif|\\.mif|\\.miff|\\.png|\\.tif|\\.tiff|\\.svg|\\.wmf|\\.jpe|\\.jpeg|\\.dib|\\.ico|\\.tga|\\.cut|\\.pic)\\b)[^>]*>",
				Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(htmlCode);
		String quote = null;
		String src = null;
		while (m.find()) {
			quote = m.group(1);
			src = (quote == null || quote.trim().length() == 0) ? m.group(2).split("\\s+")[0] : m.group(2);
			imageSrcList.add(src);
		}
		String image = imageSrcList.toString();
		image = image.replace("[", "");
		image = image.replace("]", "");
		return image;
	}
}
