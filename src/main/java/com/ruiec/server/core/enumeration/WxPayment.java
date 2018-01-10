package com.ruiec.server.core.enumeration;

/**
 * 微信支付方式
 * 2016年10月31日
 */
public enum WxPayment {
	
	JSAPI("公众号支付"),
	NATIVE("原生扫码支付"),
	APP("app支付");
	
	private String name;

	private WxPayment(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
