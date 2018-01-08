package com.ruiec.server.core.bean;

import java.io.Serializable;

/**
 * 个人统计条件bean
 * @author 刘立雯
 * Date：2017年07月12日
 */
public class PersonalStatisticConditionBean implements Serializable {

	private static final long serialVersionUID = -3585324761624951454L;

	/** 用户名 */
	private String username;
	
	/** 部门ID */
	private String projectDepartmentId;
	
	/** 项目开始时间 */
	private String startDate;
	
	/** 项目结束时间 */
	private String endDate;
	
	public PersonalStatisticConditionBean(String username,
			String projectDepartmentId, String startDate, String endDate) {
		super();
		this.username = username;
		this.projectDepartmentId = projectDepartmentId;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public PersonalStatisticConditionBean() {
		super();
	}
	
	/** 用户名 */
	public String getUsername() {
		return username;
	}
	
	/** 用户名 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/** 部门ID */
	public String getProjectDepartmentId() {
		return projectDepartmentId;
	}
	
	/** 部门ID */
	public void setProjectDepartmentId(String projectDepartmentId) {
		this.projectDepartmentId = projectDepartmentId;
	}

	/** 项目开始时间 */
	public String getStartDate() {
		return startDate;
	}
	
	/** 项目开始时间 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	/** 项目结束时间 */
	public String getEndDate() {
		return endDate;
	}
	
	/** 项目结束时间 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}
