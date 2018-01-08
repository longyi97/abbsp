package com.ruiec.web.model;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 前台用户模型
 * 
 * @author 钟国城<br>
 * @date 2017年11月8日 上午9:35:00
 */
public class User extends BaseModel {

	/** serialVersionUID */
	private static final long serialVersionUID = 7592783025953509051L;

	/** 用户名 */
	@NotBlank
	private String username;

	/** 密码 */
	@NotBlank
	private String password;

	/** 性别 */
	private Boolean sex;

	/** 生日 */
	private Date birthday;

	/** 手机号码 */
	private String mobile;

	/** 邮箱 */
	private String email;

	/** 签名 */
	private String sign;

	/** 头像 */
	private String headImage;

	/** 是否锁定 */
	private Boolean isLocked;

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

	/** 性别 */
	public Boolean getSex() {
		return sex;
	}

	/** 性别 */
	public void setSex(Boolean sex) {
		this.sex = sex;
	}

	/** 生日 */
	public Date getBirthday() {
		return birthday;
	}

	/** 生日 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/** 手机号码 */
	public String getMobile() {
		return mobile;
	}

	/** 手机号码 */
	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}

	/** 邮箱 */
	public String getEmail() {
		return email;
	}

	/** 邮箱 */
	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	/** 签名 */
	public String getSign() {
		return sign;
	}

	/** 签名 */
	public void setSign(String sign) {
		this.sign = sign == null ? null : sign.trim();
	}

	/** 头像 */
	public String getHeadImage() {
		return headImage;
	}

	/** 头像 */
	public void setHeadImage(String headImage) {
		this.headImage = headImage == null ? null : headImage.trim();
	}

	/** 是否锁定 */
	public Boolean getIsLocked() {
		return isLocked;
	}

	/** 是否锁定 */
	public void setIsLocked(Boolean isLocked) {
		this.isLocked = isLocked;
	}
}