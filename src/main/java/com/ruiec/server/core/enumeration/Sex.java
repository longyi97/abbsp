package com.ruiec.server.core.enumeration;
/**
 * 
 * 
 * 性别类型
 * Version: 1.0<br>
 * Date: 2016年3月21日
 */
public enum Sex {
	
	male("男"), 
	female("女"),
	unknown("未知");
	
	private String name;
	
	
	private Sex(String name){
		this.name = name;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
