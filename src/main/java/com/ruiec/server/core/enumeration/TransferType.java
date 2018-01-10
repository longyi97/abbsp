package com.ruiec.server.core.enumeration;

/**
 * 
 * 
 * 转账状态
 * Version: 1.0<br>
 * Date: 2016年3月21日
 */
public enum TransferType {
	
	transferIn("转入"),
	transferOut("转出");
	
	private String name;

	private TransferType(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
