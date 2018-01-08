/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.server.core.enumeration;

/**
 * 充值方式
 * @author 熊华松<br>
 * Version: 1.0<br>
 * Date: 2016年03月31日
 */
public enum ChargeType {
	
	wx("微信"),
	pay("支付宝"),
	unionpay("银联");
	
	private String name;

	private ChargeType(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
