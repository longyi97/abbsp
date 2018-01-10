package com.ruiec.server.core.enumeration;
/**
 * 
 * 
 * 消息类型类型
 * Version: 1.0<br>
 * Date: 2016年3月21日
 */
public enum MessageType {
	
	system("系统消息"),
	user("用户消息");
	
	private String name;

	private MessageType(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
