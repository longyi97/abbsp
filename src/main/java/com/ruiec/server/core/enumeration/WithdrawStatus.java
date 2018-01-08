package com.ruiec.server.core.enumeration;

/**
 * 
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 * 
 * 提现状态
 * @author 熊华松<br>
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
