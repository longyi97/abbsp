package com.ruiec.server.core.enumeration;

public enum TimeType {
	
	notime("没有结算时间"),
	shortAndLongTime("长短期类型"),
	seconds60("60秒类型");
	private String name;
	private TimeType(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
