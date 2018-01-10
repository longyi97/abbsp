package com.ruiec.web.service;

import java.util.List;

import com.ruiec.web.model.UserSignDetail;

/**
 * 签到明细接口
 * 
 * @date 2017年11月28日 下午4:01:44
 */
public interface UserSignDetailService extends BaseService<UserSignDetail> {
	/**
	 * 可根据用户名搜索
	 * 
	 * @date 2017年11月29日 上午9:13:51
	 */
	List<UserSignDetail> selectByUserSignDetail(UserSignDetail userSignDetail);

	/**
	 * 通过用户ID查询该用户的所有签到明细
	 * 
	 * @date 2017年12月2日 下午2:39:18
	 */
	List<UserSignDetail> selectByUserId(Integer userId);
	
	/**
	 * 传入用户id，传入一周的数量，获取到一周签到数量
	 * 
	 * @date 2017年12月20日 下午2:39:18
	 */
	List<UserSignDetail> selectByUserIdOne(Integer userId,String st);
}
