package com.ruiec.server.core.enumeration;

/**
 * 
 * 
 * 提现状态
 * Version: 1.0<br>
 * Date: 2016年3月21日
 */
public enum WithdrawStatus {
	
	withdrawing("处理中"),
	withdrawSuccessfully("提现成功"),
	withdrawFailed("提现失败");
	
	private String name;

	private WithdrawStatus(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
