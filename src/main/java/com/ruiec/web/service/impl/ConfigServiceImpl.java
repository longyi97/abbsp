package com.ruiec.web.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ruiec.web.dao.ConfigMapper;
import com.ruiec.web.model.Config;
import com.ruiec.web.service.ConfigService;

/**
 * 系统配置服务实现类
 * 
 * @date 2017年11月29日 上午9:03:00
 */
@Service
public class ConfigServiceImpl extends BaseServiceImpl<Config> implements ConfigService {
	
	protected static final Logger LOGGER = LoggerFactory.getLogger(ConfigServiceImpl.class);
	
	private ConfigMapper configMapper;

	/** 设置configMapper */
	@Resource
	public void setConfigMapper(ConfigMapper configMapper) {
		this.baseMapper = this.configMapper = configMapper;
	}

	/**
	 * 通过key获取系统配置
	 * 
	 * @date 2017年11月29日 下午4:43:53
	 */
	@Override
	public Config selectByKey(String key) {
		return configMapper.selectByKey(key);
	}

	/**
	 * 通过key更新系统配置
	 * 
	 * @date 2017年11月29日 下午4:47:16
	 */
	@Override
	public Integer updateByKey(Config config) {
		return configMapper.updateByKey(config);
	}
	
}
