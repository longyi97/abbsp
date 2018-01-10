package com.ruiec.server.core.bean;

import java.io.Serializable;

/**
 * 统计日期bean
 * Date：2017年07月12日
 */
public class StatisticDateBean implements Serializable {

	private static final long serialVersionUID = -3585324761624951454L;

	/** 开始日期 */
	private String startDate;
	
	/** 结束日期 */
	private String endDate;
	
	public StatisticDateBean(String startDate, String endDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public StatisticDateBean() {
		super();
	}

	/** 开始日期 */
	public String getStartDate() {
		return startDate;
	}
	
	/** 开始日期 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	/** 结束日期 */
	public String getEndDate() {
		return endDate;
	}
	
	/** 结束日期 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}
