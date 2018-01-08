package com.ruiec.web.dao;

import java.util.List;

import com.ruiec.web.model.UserPointsDetail;

/**
 * 积分记录详情数据访问接口
 * 
 * @author Jerry<br>
 * @date 2017年11月28日 下午4:29:32
 */
public interface UserPointsDetailMapper extends BaseMapper<UserPointsDetail> {
	/**
	 * 通过用户ID查询该用户的所有积分明细
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年12月2日 下午2:39:18
	 */
	List<UserPointsDetail> selectByUserId(Integer userId);

	/**
	 * 通过用户ID查询该用户通过签到所获得的积分之和
	 * 
	 * @author zhongguocheng<br>
	 * @return
	 * @date 2017年12月2日 下午4:23:05
	 */
	Float selectByAllSignPoints(Integer userId);

	/**
	 * 条件查询
	 * 
	 * @author bingo<br>
	 * @date 2017年12月4日 下午9:15:08
	 */
	List<UserPointsDetail> selectByUserPointsDetail(UserPointsDetail userPointsDetail);
}