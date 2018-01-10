
package com.ruiec.server.autocreatetable.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.ruiec.framework.server.support.entity.BaseEntity;
import com.ruiec.framework.server.support.hibernate.EntityInterceptor;
import com.ruiec.framework.server.support.hibernate.TableNameInfo;

/**
 * 切换表名工具类(根据年份切换表名)
 * 
 * @author 杨龙香<br>
 * Version 1.0<br>
 * Date: 2016年06月22日
 */
public class SwitchTableNameUtil {
	
	/**
	 * 将表名切换成指定年份
	 * 
	 * @author 杨龙香<br>
	 * Version 1.0<br>
	 * Date: 2016年06月22日
	 */
	public static void switchTableName(Class<? extends BaseEntity> c, String year) {
		String prefix = c.getAnnotation(javax.persistence.Table.class).name();
		EntityInterceptor.addTableNameInfo(new TableNameInfo(prefix, prefix + "_" + year));
	}
	
	/**
	 * 将表名切换成当前年份
	 * 
	 * @author 杨龙香<br>
	 * Version 1.0<br>
	 * Date: 2016年06月22日
	 */
	public static String switchTableName(Class<? extends BaseEntity> c) {
		String prefix = c.getAnnotation(javax.persistence.Table.class).name();
		Date now = new Date();
		SimpleDateFormat fm = new SimpleDateFormat("yyyy");
		String year = fm.format(now);
		EntityInterceptor.addTableNameInfo(new TableNameInfo(prefix, prefix + "_" + year));
		return year;
	}
	
	/**
	 * 将表名切换成指后缀
	 * 
	 * @author 杨龙香<br>
	 * Version 1.0<br>
	 * Date: 2016年06月22日
	 */
	public static void switchTableNameWithSuffix(Class<? extends BaseEntity> c, String suffix) {
		String prefix = c.getAnnotation(javax.persistence.Table.class).name();
		EntityInterceptor.addTableNameInfo(new TableNameInfo(prefix, prefix + "_" + suffix));
	}
}
