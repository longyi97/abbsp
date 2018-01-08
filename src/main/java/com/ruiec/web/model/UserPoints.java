package com.ruiec.web.model;

/**
 * 积分记录模型类
 * 
 * @author Jerry<br>
 * @date 2017年11月28日 下午4:25:42
 */
public class UserPoints extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 用户ID */
	private Integer userId;

	/** 积分分数 */
	private Float points;

	/** 历史积分分数 */
	private Float historyPoints;

	/** 用户名 */
	private String username;

	/** 排序条件 */
	private Integer conditionsSort;

	/** 排序条件 */
	public Integer getConditionsSort() {
		return conditionsSort;
	}

	/** 排序条件 */
	public void setConditionsSort(Integer conditionsSort) {
		this.conditionsSort = conditionsSort;
	}

	/** 用户ID */
	public Integer getUserId() {
		return userId;
	}

	/** 用户ID */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/** 积分分数 */
	public Float getPoints() {
		return points;
	}

	/** 积分分数 */
	public void setPoints(Float points) {
		this.points = points;
	}

	/** 历史积分分数 */
	public Float getHistoryPoints() {
		return historyPoints;
	}

	/** 历史积分分数 */
	public void setHistoryPoints(Float historyPoints) {
		this.historyPoints = historyPoints;
	}

	/** 用户名 */
	public String getUsername() {
		return username;
	}

	/** 用户名 */
	public void setUsername(String username) {
		this.username = username;
	}
}