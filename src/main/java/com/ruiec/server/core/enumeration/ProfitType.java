package com.ruiec.server.core.enumeration;
/**
 * 
 * 
 * 收益类型
 * Version: 1.0<br>
 * Date: 2016年3月21日
 */
public enum ProfitType {
	/**
	 * 盈利
	 */
	profit("盈利"),
	/**
	 * 亏损
	 */
	loss("亏损"),
	/**
	 * 持平
	 */
	dogfall("持平");
	
	private String name;

	private ProfitType(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
