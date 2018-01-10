package com.ruiec.server.core.enumeration;

/**
 * 
 * 
 * 资金互转状态
 * Version: 1.0<br>
 * Date: 2016年3月21日
 */
public enum MutualTransferType {
	
	receipts("分红资金转可用资金"),
	expenditure("可用资金转分红资金");
	
	private String name;

	private MutualTransferType(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
