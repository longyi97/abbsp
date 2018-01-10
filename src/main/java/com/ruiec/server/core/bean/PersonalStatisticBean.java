package com.ruiec.server.core.bean;

import java.io.Serializable;

/**
 * 个人统计bean
 * 
 */
public class PersonalStatisticBean implements Serializable {

	private static final long serialVersionUID = 6474476430841556088L;
	/** 用户名 */
	private String username;
	/** 参与项目数量 */
	private int projectCount;
	/** 完成项目数量 */
	private int completeTheProjectCount;
	/** 延期数量 */
	private int extensionOfTheProjectCount;
	/** 废弃数量 */
	private int discardedCount;
	/** 报备数量 */
	private int reportTheBillCount;
	/** 所属部门 */
	private String departmentName;
	
	public PersonalStatisticBean() {
		super();
	}

	public PersonalStatisticBean(String username, int projectCount,
			int completeTheProjectCount, int extensionOfTheProjectCount,
			int discardedCount, int reportTheBillCount, String departmentName) {
		super();
		this.username = username;
		this.projectCount = projectCount;
		this.completeTheProjectCount = completeTheProjectCount;
		this.extensionOfTheProjectCount = extensionOfTheProjectCount;
		this.discardedCount = discardedCount;
		this.reportTheBillCount = reportTheBillCount;
		this.departmentName = departmentName;
	}

	/** 用户名 */
	public String getUsername() {
		return username;
	}

	/** 用户名 */
	public void setUsername(String username) {
		this.username = username;
	}

	/** 参与项目数量 */
	public int getProjectCount() {
		return projectCount;
	}

	/** 参与项目数量 */
	public void setProjectCount(int projectCount) {
		this.projectCount = projectCount;
	}

	/** 完成项目数量 */
	public int getCompleteTheProjectCount() {
		return completeTheProjectCount;
	}

	/** 完成项目数量 */
	public void setCompleteTheProjectCount(int completeTheProjectCount) {
		this.completeTheProjectCount = completeTheProjectCount;
	}

	/** 延期数量 */
	public int getExtensionOfTheProjectCount() {
		return extensionOfTheProjectCount;
	}

	/** 延期数量 */
	public void setExtensionOfTheProjectCount(int extensionOfTheProjectCount) {
		this.extensionOfTheProjectCount = extensionOfTheProjectCount;
	}

	/** 废弃数量 */
	public int getDiscardedCount() {
		return discardedCount;
	}

	/** 废弃数量 */
	public void setDiscardedCount(int discardedCount) {
		this.discardedCount = discardedCount;
	}

	/** 报备数量 */
	public int getReportTheBillCount() {
		return reportTheBillCount;
	}

	/** 报备数量 */
	public void setReportTheBillCount(int reportTheBillCount) {
		this.reportTheBillCount = reportTheBillCount;
	}

	/** 所属部门 */
	public String getDepartmentName() {
		return departmentName;
	}

	/** 所属部门 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

}
