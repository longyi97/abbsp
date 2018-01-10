package com.ruiec.server.core.bean;

import java.io.Serializable;

/**
 * 统计图数据bean
 * 
 */
public class StatisticImageBean implements Serializable {

	private static final long serialVersionUID = -4985855725727103001L;

	/** 报备单数量 */
	private int reportTheBillCount;

	/** 延期函数量 */
	private int extensionLetterCount;

	/** 需求变更数量 */
	private int changeInDemandCount;

	/** 缺少延期函数量 */
	private int lackOfExtensionLetterCount;

	/** 完成项目数量 */ 
	private int completeTheProjectCount;

	/** 测试项目数量 */
	private int testItemsCount;

	/** 延期项目数量 */
	private int extensionOfTheProjectCount;
	
	/** 暂停项目数量 */
	private int suspendProjectCount;
	
	/** 正常项目数量 */
	private int normalProjectCount;
	
	/** 项目总量 */
	private int projectCount;
	
	/** 下单总量 */
	private int orderCount;
	
	/** 暂停函数量 */
	private int suspendCount;
	
	/** 发布函数量 */
	private int releaseCount;
	
	/** 启动数量 */
	private int startUpCount;
	
	/** 废弃数量 */
	private int scrapCount;
	
	/** 报备单数量 */
	public int getReportTheBillCount() {
		return reportTheBillCount;
	}

	/** 报备单数量 */
	public void setReportTheBillCount(int reportTheBillCount) {
		this.reportTheBillCount = reportTheBillCount;
	}

	/** 延期函数量 */
	public int getExtensionLetterCount() {
		return extensionLetterCount;
	}

	/** 延期函数量 */
	public void setExtensionLetterCount(int extensionLetterCount) {
		this.extensionLetterCount = extensionLetterCount;
	}

	/** 需求变更数量 */
	public int getChangeInDemandCount() {
		return changeInDemandCount;
	}

	/** 需求变更数量 */
	public void setChangeInDemandCount(int changeInDemandCount) {
		this.changeInDemandCount = changeInDemandCount;
	}

	/** 缺少延期函数量 */
	public int getLackOfExtensionLetterCount() {
		return lackOfExtensionLetterCount;
	}

	/** 缺少延期函数量 */
	public void setLackOfExtensionLetterCount(int lackOfExtensionLetterCount) {
		this.lackOfExtensionLetterCount = lackOfExtensionLetterCount;
	}

	/** 完成项目数量 */
	public int getCompleteTheProjectCount() {
		return completeTheProjectCount;
	}

	/** 完成项目数量 */
	public void setCompleteTheProjectCount(int completeTheProjectCount) {
		this.completeTheProjectCount = completeTheProjectCount;
	}

	/** 测试项目数量 */
	public int getTestItemsCount() {
		return testItemsCount;
	}

	/** 测试项目数量 */
	public void setTestItemsCount(int testItemsCount) {
		this.testItemsCount = testItemsCount;
	}

	/** 延期项目数量 */
	public int getExtensionOfTheProjectCount() {
		return extensionOfTheProjectCount;
	}

	/** 延期项目数量 */
	public void setExtensionOfTheProjectCount(int extensionOfTheProjectCount) {
		this.extensionOfTheProjectCount = extensionOfTheProjectCount;
	}
	
	/** 暂停项目数量 */
	public int getSuspendProjectCount() {
		return suspendProjectCount;
	}

	/** 暂停项目数量 */
	public void setSuspendProjectCount(int suspendProjectCount) {
		this.suspendProjectCount = suspendProjectCount;
	}

	/** 正常项目数量 */
	public int getNormalProjectCount() {
		return normalProjectCount;
	}

	/** 正常项目数量 */
	public void setNormalProjectCount(int normalProjectCount) {
		this.normalProjectCount = normalProjectCount;
	}
	
	/** 项目数量 */
	public int getProjectCount() {
		return projectCount;
	}

	/** 项目数量 */
	public void setProjectCount(int projectCount) {
		this.projectCount = projectCount;
	}

	/** 下单总量 */
	public int getOrderCount() {
		return orderCount;
	}
	
	/** 下单总量 */
	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}
	
	/** 暂停函数量 */
	public int getSuspendCount() {
		return suspendCount;
	}
	
	/** 暂停函数量 */
	public void setSuspendCount(int suspendCount) {
		this.suspendCount = suspendCount;
	}

	/** 发布函数量 */
	public int getReleaseCount() {
		return releaseCount;
	}

	/** 发布函数量 */
	public void setReleaseCount(int releaseCount) {
		this.releaseCount = releaseCount;
	}

	/** 启动数量 */
	public int getStartUpCount() {
		return startUpCount;
	}

	/** 启动数量 */
	public void setStartUpCount(int startUpCount) {
		this.startUpCount = startUpCount;
	}

	/** 废弃数量 */
	public int getScrapCount() {
		return scrapCount;
	}

	/** 废弃数量 */
	public void setScrapCount(int scrapCount) {
		this.scrapCount = scrapCount;
	}

	@Override
	public String toString() {
		return "StatisticImageBean [reportTheBillCount=" + reportTheBillCount
				+ ", extensionLetterCount=" + extensionLetterCount
				+ ", changeInDemandCount=" + changeInDemandCount
				+ ", lackOfExtensionLetterCount=" + lackOfExtensionLetterCount
				+ ", completeTheProjectCount=" + completeTheProjectCount
				+ ", testItemsCount=" + testItemsCount
				+ ", extensionOfTheProjectCount=" + extensionOfTheProjectCount
				+ ", suspendProjectCount=" + suspendProjectCount
				+ ", normalProjectCount=" + normalProjectCount
				+ ", projectCount=" + projectCount + ", orderCount="
				+ orderCount + ", suspendCount=" + suspendCount
				+ ", releaseCount=" + releaseCount + ", startUpCount="
				+ startUpCount + ", scrapCount=" + scrapCount + "]";
	}
	
}
