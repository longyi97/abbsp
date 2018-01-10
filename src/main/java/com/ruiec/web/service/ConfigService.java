package com.ruiec.web.service;

import com.ruiec.web.model.Config;

/**
 * 系统配置服务接口
 * 
 * @author bingo<br>
 * @date 2017年11月29日 上午9:01:44
 */
public interface ConfigService extends BaseService<Config> {
	
	/**
	 * 通过key获取系统配置
	 * 
	 * @author bingo<br>
	 * @date 2017年11月29日 下午4:43:53
	 */
	Config selectByKey(String key);
	
	/**
	 * 通过key更新系统配置
	 * 
	 * @author bingo<br>
	 * @date 2017年11月29日 下午4:47:16
	 */
	Integer updateByKey(Config config);

}
