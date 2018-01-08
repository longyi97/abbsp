/*版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.server.core.bean.searchcriteria;

import java.io.Serializable;
import java.util.Date;

/**
 * 管理员搜索条件
 * @author 刘立雯<br>
 * Version 1.0<br>
 * Date: 2016年12月08日
 */
public class AdminSearchCriteria implements Serializable {
	
	private static final long serialVersionUID = 5901833577746585017L;
	
	/** 用户名 */
	private String username;
	/** 真实姓名 */
	private String realName;
	/** 是否为系统内置管理员 */
	private String isSystem;
	/** 注册时间(开始) */
	private Date regStartDate;
	/** 注册时间(结束) */
	private Date regEndDate;
	/** 最后一次登录时间(开始) */
	private Date lastStartDate;
	/** 最后一次登录时间(结束) */
	private Date lastEndDate;
	
	/** 用户名 */
	public String getUsername() {
		return username;
	}
	/** 用户名 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/** 真实姓名 */
	public String getRealName() {
		return realName;
	}
	/** 真实姓名 */
	public void setRealName(String realName) {
		this.realName = realName;
	}

	/** 是否为系统内置管理员 */
	public String getIsSystem() {
		return isSystem;
	}
	/** 是否为系统内置管理员 */
	public void setIsSystem(String isSystem) {
		this.isSystem = isSystem;
	}

	/** 注册时间(开始) */
	public Date getRegStartDate() {
		return regStartDate;
	}
	/** 注册时间(开始) */
	public void setRegStartDate(Date regStartDate) {
		this.regStartDate = regStartDate;
	}

	/** 注册时间(结束) */
	public Date getRegEndDate() {
		return regEndDate;
	}
	/** 注册时间(结束) */
	public void setRegEndDate(Date regEndDate) {
		this.regEndDate = regEndDate;
	}

	/** 最后一次登录时间(开始) */
	public Date getLastStartDate() {
		return lastStartDate;
	}
	/** 最后一次登录时间(开始) */
	public void setLastStartDate(Date lastStartDate) {
		this.lastStartDate = lastStartDate;
	}

	/** 最后一次登录时间(结束) */
	public Date getLastEndDate() {
		return lastEndDate;
	}
	/** 最后一次登录时间(结束) */
	public void setLastEndDate(Date lastEndDate) {
		this.lastEndDate = lastEndDate;
	}
	
}
