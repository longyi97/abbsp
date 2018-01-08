/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ruiec.web.dao.SysConfigMapper;
import com.ruiec.web.model.SysConfig;
import com.ruiec.web.service.SysConfigService;

/**
 * 系统服务实现类
 * @author 杨龙香<br>
 * Version 1.0<br>
 * Date: 2017年01月05日
 */
@Service("sysConfigServiceImpl")
public class SysConfigServiceImpl extends BaseServiceImpl<SysConfig> implements SysConfigService {
	
	private SysConfigMapper sysConfigMapper;
	
	@Resource
	public void setQqCustomerMapper(SysConfigMapper sysConfigMapper) {
		this.baseMapper = this.sysConfigMapper = sysConfigMapper;
	}
	
}
