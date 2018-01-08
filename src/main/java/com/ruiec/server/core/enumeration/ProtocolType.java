/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.server.core.enumeration;

/**
 * 协议类型枚举
 * 
 * @author 熊华松<br>
 * Version: 1.0<br>
 * Date: 2016年09月28日
 */
public enum ProtocolType {

	register("注册"),
	charge("充值"),
	withdraw("提现");
	
	private String name;

	private ProtocolType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
