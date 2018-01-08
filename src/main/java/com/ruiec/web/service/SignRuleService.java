/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */
package com.ruiec.web.service;

import com.ruiec.web.model.SignRule;

/**
 * 连续签到规则接口
 * 
 * @author 钟国城<br>
 * @date 2017年11月28日 下午4:01:44
 */
public interface SignRuleService extends BaseService<SignRule> {

	/**
	 * 根据连续天数查询
	 * 
	 * @author 钟国城<br>
	 * @date 2017年11月30日 上午9:20:38
	 */
	public SignRule selectByContinuousDays(Integer continuousDays);

}