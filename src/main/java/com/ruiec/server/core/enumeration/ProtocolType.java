
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
