
package com.ruiec.server.core.enumeration;

/**
 * 充值状态
 * @author 熊华松<br>
 * Version: 1.0<br>
 * Date: 2016年03月31日
 */
public enum ChargeStatus {
	
	charging("支付中"),
	chargeSuccessfully("支付成功"),
	chargeClose("订单关闭");
	
	private String name;

	private ChargeStatus(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
