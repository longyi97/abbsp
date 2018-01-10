package com.ruiec.server.core.enumeration;

/**
 * 
 * 
 * 提现状态
 * Version: 1.0<br>
 * Date: 2016年9月30日
 */
public enum WithdrawVirtualCoinStatus {
	
	withdrawVirtualCoining("处理中"),
	withdrawVirtualCoinSuccessfully("提币成功"),
	withdrawVirtualCoinFailed("提币失败");
	
	private String name;

	private WithdrawVirtualCoinStatus(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
