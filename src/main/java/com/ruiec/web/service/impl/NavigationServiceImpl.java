package com.ruiec.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ruiec.web.dao.NavigationMapper;
import com.ruiec.web.model.Navigation;
import com.ruiec.web.service.NavigationService;

/**
 * 导航服务实现类
 * Date：2017年07月06日
 */
@Service("navigationServiceImpl")
public class NavigationServiceImpl extends BaseServiceImpl<Navigation> implements NavigationService {

	private NavigationMapper navigationMapper;
	
	@Resource
	public void setNavigationMapper(NavigationMapper navigationMapper) {
		this.baseMapper = this.navigationMapper = navigationMapper;
	}
	
	/**
	 * 根据层级查询导航数据
	 * Date：2017年07月06日
	 */
	public List<Navigation> selectByLevel(int level){
		return navigationMapper.selectByLevel(level);
	}
	
}
