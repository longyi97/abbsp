/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */
package com.ruiec.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ruiec.web.dao.SignRuleMapper;
import com.ruiec.web.model.SignRule;
import com.ruiec.web.service.SignRuleService;

/**
 * 连续签到规则接口实现
 * 
 * @author 钟国城<br>
 * @date 2017年11月29日 下午2:11:33
 */
@Service("signRuleServiceImpl")
public class SignRuleServiceImpl extends BaseServiceImpl<SignRule> implements SignRuleService {

	private SignRuleMapper signRuleMapper;

	@Resource
	public void setSignRuleMapper(SignRuleMapper signRuleMapper) {
		this.baseMapper = this.signRuleMapper = signRuleMapper;
	}

	/**
	 * 根据连续天数查询
	 * 
	 * @author 钟国城<br>
	 * @date 2017年11月30日 上午9:20:38
	 */
	public SignRule selectByContinuousDays(Integer continuousDays) {
		return signRuleMapper.selectByContinuousDays(continuousDays);
	}
}
