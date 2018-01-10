
package com.ruiec.server.core.enumeration;

/**
 * 产品行情平台方枚举
 * 
 * Version 1.0<br>
 * Date: 2016-08-03
 */
public enum GoodsPricePlatform {
	
	sinaGoodsPriceProcessor("新浪财经"),
	juheGoodsPriceProcessor("聚合数据"),
	okCoin("ok硬币"),
	fireData("火币数据"),
	chbtc("中国比特币"),
	wstockGoodsPriceProcessor("微盛数海");
	
	private String name;

	private GoodsPricePlatform(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
