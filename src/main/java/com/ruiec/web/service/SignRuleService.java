package com.ruiec.web.service;

import com.ruiec.web.model.SignRule;

/**
 * 连续签到规则接口
 * 
 * @date 2017年11月28日 下午4:01:44
 */
public interface SignRuleService extends BaseService<SignRule> {

	/**
	 * 根据连续天数查询
	 * 
	 * @date 2017年11月30日 上午9:20:38
	 */
	public SignRule selectByContinuousDays(Integer continuousDays);

}
