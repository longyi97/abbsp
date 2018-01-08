package com.ruiec.web.dao;

import com.ruiec.web.model.SignRule;

/**
 * 签到规则接口
 * 
 * @author 钟国城<br>
 * @date 2017年11月30日 上午9:21:27
 */
public interface SignRuleMapper extends BaseMapper<SignRule> {

	/**
	 * 根据连续天数查询
	 * 
	 * @author 钟国城<br>
	 * @date 2017年11月30日 上午9:20:38
	 */
	public SignRule selectByContinuousDays(Integer continuousDays);

}