
package com.ruiec.server.core.enumeration;

/**
 * 验证码的操作类型(哪些操作需要获取验证码)
 * Version: 1.0<br>
 * Date: 2016年04月27日
 */
public enum CaptchaOperationType {
	
	addBankCard("添加银行卡"),
	editUserInfo("编辑个人资料"),
	withdraw("出金"),
	transfer("我要转账"),
	mutualTransfer("资金互转"),
	register("注册"),
	findPassword("找回密码"),
	binding("绑定"),
	updateBinding("修改绑定"),
	updatePassword("修改密码"),
	updatePayPassword("修改支付密码");
	
	private String name;

	private CaptchaOperationType(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
