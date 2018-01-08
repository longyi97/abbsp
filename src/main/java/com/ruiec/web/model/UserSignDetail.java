package com.ruiec.web.model;

import java.util.Date;

/**
 * 签到明细模型
 * 
 * @author 钟国城<br>
 * @date 2017年11月28日 下午3:56:58
 */
public class UserSignDetail extends BaseModel {

	/** 序列化 */
	private static final long serialVersionUID = 4149094314032988651L;

	/** 用户ID */
	private Integer userId;

	/** 用户最后签到时间 */
	private Date lastSignTime;

	/** 用户签到总次数 */
	private Integer signCount;

	/** 用户连续签到次数 */
	private Integer signContinuousCount;

	/** 用户名 */
	private String username;

	/** 排序条件 */
	private Integer conditionsSort;

	/** 用户ID */
	public Integer getUserId() {
		return userId;
	}

	/** 用户ID */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/** 用户最后签到时间 */
	public Date getLastSignTime() {
		return lastSignTime;
	}

	/** 用户最后签到时间 */
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

	/** 用户连续签到次数 */
	public Integer getSignContinuousCount() {
		return signContinuousCount;
	}

	/** 用户连续签到次数 */
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