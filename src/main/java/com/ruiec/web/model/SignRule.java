package com.ruiec.web.model;

/**
 * 用户连续签到规则模型
 * 
 * @author 钟国城<br>
 * @date 2017年11月29日 下午2:00:55
 */
public class SignRule extends BaseModel {

	/** 序列化 */
	private static final long serialVersionUID = -4425050811423911326L;

	/** 连续签到x天 */
	private Integer continuousDays;

	/** 奖励分值 */
	private Float rewardPoints;

	/** 备注 */
	private String note;

	/** 连续签到x天 */
	public Integer getContinuousDays() {
		return continuousDays;
	}

	/** 连续签到x天 */
	public void setContinuousDays(Integer continuousDays) {
		this.continuousDays = continuousDays;
	}

	/** 奖励分值 */
	public Float getRewardPoints() {
		return rewardPoints;
	}

	/** 奖励分值 */
	public void setRewardPoints(Float rewardPoints) {
		this.rewardPoints = rewardPoints;
	}

	/** 备注 */
	public String getNote() {
		return note;
	}

	/** 备注 */
	public void setNote(String note) {
		this.note = note == null ? null : note.trim();
	}
}