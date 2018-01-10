package com.ruiec.server.core.enumeration;
/**
 * 
 * 
 * 结算时间类型
 * Version: 1.0<br>
 * Date: 2016年3月21日
 */
public enum CycleType {
	
	shortTerm("短期"),
	longTerm("长期"),
	is60Seconds("60秒");
	
	private String name;

	private CycleType(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
