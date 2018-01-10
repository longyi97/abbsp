package com.ruiec.web.service;

import java.util.List;

import com.ruiec.web.model.UserPointsDetail;

/**
 * 积分记录详情服务接口
 * @date 2017年11月28日 下午4:32:53
 */
public interface UserPointsDetailService extends BaseService<UserPointsDetail>{

	/**
	 * 通过用户ID查询该用户的所有积分明细
	 * 
	 * @date 2017年12月2日 下午2:39:18
	 */
	List<UserPointsDetail> selectByUserId(Integer userId);
	
	/**
	 * 通过用户ID查询该用户通过签到所获得的积分之和
	 * 
	 * @return 
	 * @date 2017年12月2日 下午4:23:05
	 */
	Float selectByAllSignPoints(Integer userId);
	
	/**
	 * 计算用户今日获得积分数
	 * 
	 * @date 2017年12月4日 下午8:50:35
	 */
	Float countUserPointsToday(Integer userId);
}
