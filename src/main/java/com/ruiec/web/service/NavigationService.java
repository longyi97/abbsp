package com.ruiec.web.service;

import java.util.List;

import com.ruiec.web.model.Navigation;

/**
 * 导航服务接口
 * @author 刘立雯
 * Date：2017年07月06日
 */
public interface NavigationService extends BaseService<Navigation> {

	/**
	 * 根据层级查询导航数据
	 * @author 刘立雯
	 * @Date：2017年07月06日
	 */
	public List<Navigation> selectByLevel(int level);
	
}
