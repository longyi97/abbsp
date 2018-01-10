package com.ruiec.web.dao;

import org.apache.ibatis.annotations.Param;

import com.ruiec.web.model.UserPoints;

/**
 * 积分记录数据访问接口
 * 
 * @date 2017年11月28日 下午4:30:28
 */
public interface UserPointsMapper extends BaseMapper<UserPoints> {

	/**
	 * 通过用户ID更新用户积分记录
	 * 
	 * @date 2017年11月29日 下午5:18:10
	 */
	Integer updateByUserId(@Param("userId") Integer userId);

	/**
	 * 根据用户ID查询用户积分
	 * 
	 * @date 2017年11月29日 下午5:49:32
	 */
	UserPoints selectByUserId(@Param("userId") Integer userId);

	/**
	 * 根据用户名查询是否存在
	 * 
	 * @date 2017年11月30日 上午11:16:41
	 */
	public UserPoints selectByUsername(String username);
}