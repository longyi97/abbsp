package com.ruiec.server.core.bean;

import java.io.Serializable;

/**
 * 部门任务统计bean
 * @author 刘立雯
 * Date：2017年07月13日
 */
public class DepartmentalTasksStatisticBean implements Serializable {

	private static final long serialVersionUID = 5389682993548949150L;
	
	/** 部门名称 */
	private String departmentName;
	/** 任务量 */
	private int taskCount;
	/** 部门logo */
	private String logo;
	
	/** 部门名称 */
	public String getDepartmentName() {
		return departmentName;
	}
	/** 部门名称 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	/** 任务量 */
	public int getTaskCount() {
		return taskCount;
	}
	/** 任务量 */
	public void setTaskCount(int taskCount) {
		this.taskCount = taskCount;
	}
	
	/** 部门logo */
	public String getLogo() {
		return logo;
	}
	/** 部门logo */
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	@Override
	public String toString() {
		return "DepartmentalTasksStatisticBean [departmentName="
				+ departmentName + ", taskCount=" + taskCount + ", logo="
				+ logo + "]";
	}
	
}
