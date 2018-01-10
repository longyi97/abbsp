package com.ruiec.web.model;

import java.util.Date;

/**
 * 用户签到模型
 * 
 * @date 2017年11月28日 下午3:56:00
 */
public class UserSign extends BaseModel {
	/** 序列化 */
	private static final long serialVersionUID = 306386141758299932L;

	/** 用户id */
	private Integer userId;

	/** 用户签到最后时间 */
	private Date lastSignTime;

	/** 用户签到总次数 */
	private Integer signCount;

	/** 用户签到连续次数 */
	private Integer signContinuousCount;

	/** 用户名 */
	private String username;

	/** 排序条件 */
	private Integer conditionsSort;

	/** 用户id */
	public Integer getUserId() {
		return userId;
	}

	/** 用户id */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/** 用户签到最后时间 */
	public Date getLastSignTime() {
		return lastSignTime;
	}

	/** 用户签到最后时间 */
	public void setLastSignTime(Date lastSignTime) {
		this.lastSignTime = lastSignTime;
	}

	/** 用户签到总次数 */
	public Integer getSignCount() {
		return signCount;
	}

	/** 用户签到总次数 */
	public void setSignCount(Integer signCount) {
		this.signCount = signCount;
	}

	/** 用户签到连续次数 */
	public Integer getSignContinuousCount() {
		return signContinuousCount;
	}

	/** 用户签到连续次数 */
	public void setSignContinuousCount(Integer signContinuousCount) {
		this.signContinuousCount = signContinuousCount;
	}

	/** 用户名 */
	public String getUsername() {
		return username;
	}

	/** 用户名 */
	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	/** 排序条件 */
	public Integer getConditionsSort() {
		return conditionsSort;
	}

	/** 排序条件 */
	public void setConditionsSort(Integer conditionsSort) {
		this.conditionsSort = conditionsSort;
	}
}