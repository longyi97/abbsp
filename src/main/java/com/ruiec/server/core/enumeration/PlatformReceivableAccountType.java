package com.ruiec.server.core.enumeration;
/**
 * 平台收款账户类型
 * Date：2016年09月22日
 */
public enum PlatformReceivableAccountType {
	
	thirdPartyPayment("第三方支付"),
	bankCardPayment("银行卡支付");
	
	private String name;

	private PlatformReceivableAccountType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
