package com.ruiec.web.dao;

import org.apache.ibatis.annotations.Param;

import com.ruiec.web.model.Config;

/**
 * 系统配置DAO接口
 * 
 * @date 2017年11月28日 下午5:56:21
 */
public interface ConfigMapper extends BaseMapper<Config> {
	
	/**
	 * 通过key获取系统配置
	 * 
	 * @date 2017年11月29日 下午4:43:53
	 */
	Config selectByKey(@Param("key") String key);
	
	/**
	 * 通过key更新系统配置
	 * 
	 * @date 2017年11月29日 下午4:47:16
	 */
	Integer updateByKey(Config config);
	
}