package com.ruiec.web.dao;

import java.util.List;

import com.ruiec.web.model.Navigation;

/**
 * 导航数据访问接口
 * Date：2017年07月05日
 */
public interface NavigationMapper extends BaseMapper<Navigation>{
	
	/**
	 * 根据层级查询导航数据
	 * Date：2017年07月06日
	 */
	public List<Navigation> selectByLevel(int level);
	
}