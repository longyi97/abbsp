
package com.ruiec.server.core.enumeration;

/**
 * 日志对象类型枚举
 * 注意要点：定义枚举值的时候，名字必须是实体的名字且首个字母是小写。如实体的名字是User，枚举的名字只能是use或者实体的名字是QQCustomer，枚举的名字只能是qQCustomer。
 * 因为记录后台操作日志是使用AOP处理的，在设定日志对象类型枚举值的时候，是按照实体的名字且首个字母转成小写给定枚举值的。
 * 
 * Version 1.0<br>
 * Date: 2016年05月18日
 */
public enum TargetType {
	
	user("用户"),
	userGroup("用户组"),
	userInfo("用户信息（资料）"),
	statistics("账户"),
	lockInfo("用户相关数据（锁定操作）"),
	goods("产品"),
	goodsPriceAPI("产品行情接口"),
	goodsType("产品分类"),
	goodsTime("产品到期时间"),
	bankDeposit("开户行"),
	userSafetyQuestion("用户安全问题"),
	pluginConfig("支付插件"),
	ad("广告"),
	adPosition("广告位"),
	notice("网站公告"),
	mediaReport("行业要闻"),
	point("平台观点"),
	aboutUs("关于我们"),
	friendLink("友情链接"),
	companion("合作伙伴"),
	qQCustomer("QQ客服"),
	phoneCustomer("电话客服"),
	helpType("帮助类型"),
	subHelpType("子帮助类型"),
	helpOption("帮助选项"),
	footerDataType("底部数据类型"),
	subFooterDataType("子底部数据类型"),
	footerDataOption("底部数据选项"),
	admin("管理员"),
	optionType("玩法"),
	role("角色"),
	currency("币种"),
	platformReceivableAccount("平台收款账户"),
	groupConstantConfig("综合会员配置"),
	wxPayParameters("微信支付参数"),
	sysConfig("系统配置"),
	protocol("协议"),
	itemType("栏目类型"),
	itemOption("栏目选项"),
	withdrawRecord("提现记录"),
	summaryStatistics("概要统计"),
	subItemType("子栏目类型"),
	department("部门管理"),
	projectType("项目类型管理"),
	product("产品管理"),
	project("项目管理"),
	projectDefer("项目延期表"),
	projectRequirementChange("项目需求更改"),
	projectQuestionReport("项目问题报备"),
	projectPause("项目暂停"),
	projectRestart("项目启动"),
	projectRelease("项目发布"),
	projectInternalDelivery("项目内部交付")
	;
	
	
	private String name;

	private TargetType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
