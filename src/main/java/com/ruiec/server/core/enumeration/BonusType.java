
package com.ruiec.server.core.enumeration;

/**
 * 分红类型枚举
 * 
 * Version 1.0<br>
 * Date: 2016-06-28
 */
public enum BonusType {
	
	leastBonus("保底分红"),
	ruleBonus("规则分红");
	
	private String name;

	private BonusType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
