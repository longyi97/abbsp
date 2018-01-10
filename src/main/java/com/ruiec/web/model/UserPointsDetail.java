package com.ruiec.web.model;

/**
 * 积分记录详情模型类
 * 
 * @date 2017年11月28日 下午4:26:30
 */
public class UserPointsDetail extends BaseModel {

	private static final long serialVersionUID = 1L;

	/** 用户ID */
	private Integer userId;

	/** 积分项目 */
	private String item;

	/** 分值 */
	private Float changePoints;

	/** 当前积分分数 */
	private Float currentPoints;

	/** 标识 */
	private Integer selectWho;

	/** 标识 */
	private String selectContent;

	/** 用户ID */
	public Integer getUserId() {
		return userId;
	}

	/** 用户ID */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/** 积分项目 */
	public String getItem() {
		return item;
	}

	/** 积分项目 */
	public void setItem(String item) {
		this.item = item;
	}

	/** 分值 */
	public Float getChangePoints() {
		return changePoints;
	}

	/** 分值 */
	public void setChangePoints(Float changePoints) {
		this.changePoints = changePoints;
	}

	/** 当前积分分数 */
	public Float getCurrentPoints() {
		return currentPoints;
	}

	/** 当前积分分数 */
	public void setCurrentPoints(Float currentPoints) {
		this.currentPoints = currentPoints;
	}

	/** 标识 */
	public Integer getSelectWho() {
		return selectWho;
	}

	/** 标识 */
	public void setSelectWho(Integer selectWho) {
		this.selectWho = selectWho;
	}

	/** 标识 */
	public String getSelectContent() {
		return selectContent;
	}

	/** 标识 */
	public void setSelectContent(String selectContent) {
		this.selectContent = selectContent;
	}
}