
package com.ruiec.server.core.enumeration;

/**
 * 登录类型枚举:前台登录、后台登录
 * 
 * Version 1.0<br>
 * Date: 2016年05月18日
 */
public enum LoginType {
	
	/**
	 * 前台日志
	 */
	proscenium("前台登录"),
	/**
	 * 后台日志
	 */
	backstage("后台登录"),
	/**
	 * APP日志
	 */
	app("APP登录");
	
	private String name;

	private LoginType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
