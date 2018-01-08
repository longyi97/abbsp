/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.server.core.enumeration;

/**
 * 登录类型枚举:前台登录、后台登录
 * 
 * @author 熊华松<br>
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
