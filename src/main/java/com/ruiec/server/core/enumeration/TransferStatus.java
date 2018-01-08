package com.ruiec.server.core.enumeration;

/**
 * 
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 * 
 * 交易类型
 * @author 肖学良<br>
 * Version: 1.0<br>
 * Date: 2016年3月21日
 */
public enum TransferStatus {
	
	buyPosition("购买持仓"),
	positionPrincipalLiq("持仓本金结算"),
	positionProfitLiq("持仓盈利结算"),
	selfRebate("自身返点"),
	proxyRebate("代理返点"),
	proxyBonus("代理分红"),
	charge("充值"),
	withdraw("提现"),
	withdrawReturn("提现退回"),
	transferIn("转入"),
	transferOut("转出");
	
	private String name;

	private TransferStatus(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
