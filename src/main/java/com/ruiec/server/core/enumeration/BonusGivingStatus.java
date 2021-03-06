
package com.ruiec.server.core.enumeration;

/**
 * 分红发放状态枚举
 * 
 * Version 1.0<br>
 * Date: 2016-07-08
 */
public enum BonusGivingStatus {
	
	hasGiven("已发放"),
	hasNotGiven("未发放");
	
	private String name;

	private BonusGivingStatus(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
