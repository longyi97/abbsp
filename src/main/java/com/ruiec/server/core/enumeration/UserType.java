/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.server.core.enumeration;

/**
 * 用户类型枚举
 * @author 熊华松<br>
 * Version 1.0<br>
 * Date: 2016年11月21日
 */
public enum UserType {
	
	/**
	 * 综合会员
	 */
	comprehensiveMember("综合会员"),
	/**
	 * 代理
	 */
	agent("代理"),
	/**
	 * 会员
	 */
	member("会员");
	
	private String name;

	private UserType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
