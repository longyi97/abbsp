package com.ruiec.server.core.bean;

import java.io.Serializable;

/**
 * 首页项目列表Bean
 * 
 * @author 刘立雯 Date：2017年07月15日
 */
public class IndexProjectBean implements Serializable {

	private static final long serialVersionUID = -1759381767795088171L;

	/** 项目ID */
	private String id;
	/** 项目名称 */
	private String name;
	/** 计划交付时间 */
	private String planDeliveryDate;
	/** 开始维护日期 */
	private String startMaintainDate;
	/** 结束维护日期 */
	private String endMaintainDate;
	/** 报备问题数 */
	private int reportQuestionAmount;
	/** 延期天数 */
	private int deferDayAmount;
	/** 变更需求数 */
	private int changeRequirementAmount;
	/** 共使用天数 */
	private int daysCount;
	/** BUG数 */
	private int bugAmount;
	/** 参与人数 */
	private int participantsCount;
	/** 状态 */
	private String status;

	/** 项目ID */
	public String getId() {
		return id;
	}

	/** 项目ID */
	public void setId(String id) {
		this.id = id;
	}

	/** 项目名称 */
	public String getName() {
		return name;
	}

	/** 项目名称 */
	public void setName(String name) {
		this.name = name;
	}

	/** 计划交付时间 */
	public String getPlanDeliveryDate() {
		return planDeliveryDate;
	}

	/** 计划交付时间 */
	public void setPlanDeliveryDate(String planDeliveryDate) {
		this.planDeliveryDate = planDeliveryDate;
	}

	/** 开始维护日期 */
	public String getStartMaintainDate() {
		return startMaintainDate;
	}

	/** 开始维护日期 */
	public void setStartMaintainDate(String startMaintainDate) {
		this.startMaintainDate = startMaintainDate;
	}

	/** 结束维护日期 */
	public String getEndMaintainDate() {
		return endMaintainDate;
	}

	/** 结束维护日期 */
	public void setEndMaintainDate(String endMaintainDate) {
		this.endMaintainDate = endMaintainDate;
	}

	/** 报备问题数 */
	public int getReportQuestionAmount() {
		return reportQuestionAmount;
	}

	/** 报备问题数 */
	public void setReportQuestionAmount(int reportQuestionAmount) {
		this.reportQuestionAmount = reportQuestionAmount;
	}

	/** 延期天数 */
	public int getDeferDayAmount() {
		return deferDayAmount;
	}

	/** 延期天数 */
	public void setDeferDayAmount(int deferDayAmount) {
		this.deferDayAmount = deferDayAmount;
	}

	/** 变更需求数 */
	public int getChangeRequirementAmount() {
		return changeRequirementAmount;
	}

	/** 变更需求数 */
	public void setChangeRequirementAmount(int changeRequirementAmount) {
		this.changeRequirementAmount = changeRequirementAmount;
	}

	/** 共使用天数 */
	public int getDaysCount() {
		return daysCount;
	}

	/** 共使用天数 */
	public void setDaysCount(int daysCount) {
		this.daysCount = daysCount;
	}

	/** BUG数 */
	public int getBugAmount() {
		return bugAmount;
	}

	/** BUG数 */
	public void setBugAmount(int bugAmount) {
		this.bugAmount = bugAmount;
	}

	/** 参与人数 */
	public int getParticipantsCount() {
		return participantsCount;
	}

	/** 参与人数 */
	public void setParticipantsCount(int participantsCount) {
		this.participantsCount = participantsCount;
	}

	/** 状态 */
	public String getStatus() {
		return status;
	}

	/** 状态 */
	public void setStatus(String status) {
		this.status = status;
	}

	public IndexProjectBean(String id, String name, String planDeliveryDate,
			String startMaintainDate, String endMaintainDate,
			int reportQuestionAmount, int deferDayAmount,
			int changeRequirementAmount, int daysCount, int bugAmount,
			int participantsCount, String status) {
		super();
		this.id = id;
		this.name = name;
		this.planDeliveryDate = planDeliveryDate;
		this.startMaintainDate = startMaintainDate;
		this.endMaintainDate = endMaintainDate;
		this.reportQuestionAmount = reportQuestionAmount;
		this.deferDayAmount = deferDayAmount;
		this.changeRequirementAmount = changeRequirementAmount;
		this.daysCount = daysCount;
		this.bugAmount = bugAmount;
		this.participantsCount = participantsCount;
		this.status = status;
	}

	public IndexProjectBean() {
		super();
	}
	
}
