package com.ruiec.server.core.enumeration;

/**
 * 
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 * 
 * 提现状态
 * @author 刘立雯<br>
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
