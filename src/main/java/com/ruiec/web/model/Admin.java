package com.ruiec.web.model;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 后台管理员模型
 * 
 * @date 2017年11月8日 上午9:34:29
 */
public class Admin extends BaseModel {
	
	/** 序列化 */
	private static final long serialVersionUID = -7178152297459611661L;

	/** 用户名 */
	@NotBlank
	private String username;

	/** 密码 */
	@NotBlank
	private String password;

	/** 姓名 */
	private String name;

	/** 手机号码 */
	@Pattern(regexp = "^1(3[0-9]|4[57]|5[0-35-9]|7[01678]|8[0-9])\\d{8}$", message = "the format of phone is error")
	private String mobile;

	/** 用户名 */
	public String getUsername() {
		return username;
	}

	/** 用户名 */
	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	/** 密码 */
	public String getPassword() {
		return password;
	}

	/** 密码 */
	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	/** 姓名 */
	public String getName() {
		return name;
	}

	/** 姓名 */
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/** 手机号码 */
	public String getMobile() {
		return mobile;
	}

	/** 手机号码 */
	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}
	
}