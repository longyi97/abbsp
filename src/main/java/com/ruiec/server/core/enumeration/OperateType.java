/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.server.core.enumeration;

/**
 * 日志操作类型枚举
 * 
 * @author 熊华松<br>
 * Version 1.0<br>
 * Date: 2016年05月18日
 */
public enum OperateType {
	
	add("添加"),delete("删除"),update("修改");
	
	private String name;

	private OperateType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
