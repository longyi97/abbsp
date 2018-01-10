package com.ruiec.server.core.bean;

import java.io.Serializable;

/**
 * 成员Bean
 * Date：2017年08月15日
 */
public class MemberBean implements Serializable {

	private static final long serialVersionUID = -870379484699463830L;
	
	/** 用户ID */
	private String userId="";
	/** 用户名 */
	private String username="";
	
	public MemberBean() {
		super();
	}
	public MemberBean(String userId, String username) {
		super();
		this.userId = userId;
		this.username = username;
	}
	
	/** 用户ID */
	public String getUserId() {
		return userId;
	}
	/** 用户ID */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	/** 用户名 */
	public String getUsername() {
		return username;
	}
	/** 用户名 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Override
	public String toString() {
		return "MemberBean [userId=" + userId + ", username=" + username + "]";
	}
	
}
