package com.ruiec.server.core.enumeration;
/**
 * 
 * 
 * 交易类型
 * Version: 1.0<br>
 * Date: 2016年3月21日
 */
public enum TransactionType {
	
	buyPosition("购买持仓"),//购买持仓需要的交易类型
//	positionPrincipalLiq("持仓本金结算"),//由于持仓本金结算会出现三种可能，持仓盈利本金结算，持仓持平本金结算、持仓亏损本金结算，所以把该类型分为三种
	positionPrincipalProfitLiq("持仓盈利本金结算"),//购买持仓盈利本金结算需要的交易类型
	positionPrincipalLossLiq("持仓亏损本金结算"),//该条记录主要是告诉用户持仓亏损和统计亏损金额
	positionPrincipalDogfalltLiq("持仓持平本金结算"),//购买持仓持平本金结算需要的交易类型
	positionProfitLiq("持仓盈利结算"),//购买持仓盈利需要的交易类型
	positionLossLiq("持仓亏损结算"),//暂时觉得不需要该条交易类型，购买持仓亏损需要的交易类型
	finalPositionLiq("最终结算结果"),//显示到个人中心的最终结算结果
	selfRebate("自身返点"),//返点需要的交易类型
	proxyRebate("代理返点"),//计算上级代理返点需要的交易类型
	proxyBonus("代理分红"),//代理分红发放需要的交易类型、（正负数决定收入还是支出）
	charge("入金"),//充值需要的交易类型
	withdraw("出金"),//提现需要的交易类型
	withdrawReturn("提现退回"),//提现失败或者取消提现需要的交易类型
	//withdrawVirtualCoin("提币"),//提虚拟币需要的交易类型
	//withdrawVirtualCoinReturn("提币退回"),//提虚拟币失败或者取消提虚拟币需要的交易类型
	transferIn("转入"),//接收由上级代理或者下级代理的转账的时候需要用到
	transferOut("转出"),//向上级代理或者下级代理转账的时候需要用到
	
//	mutualTransfer("资金互转"); // 该类型已被拆分成: trasferBetweenAmountBonusAndAmountBalance和givingBonus
	// 分红金额转到可用金额记为正值, 可用金额转到分红金额记为负值
	trasferBetweenAmountBonusAndAmountBalance("分红金额和可用金额相互转账"),//amount为正数代表分红转到余额，为负数则代表余额转到分红
	givingOrReceivingBonus("发放或者接收分红"),// 接收分红记为正值, 发放分红记为负值
	administratorAdd("管理员增加用户金额"),//管理员在后台直接给用户充值
	administratorSubtract("管理员减少用户金额");//管理员在后台直接减少用户的金额
	private String name;

	private TransactionType(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
