package com.ruiec.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruiec.web.model.UserSignDetail;

/**
 * 签到明细接口
 * 
 * @author 钟国城<br>
 * @date 2017年11月28日 下午3:53:36
 */
public interface UserSignDetailMapper extends BaseMapper<UserSignDetail> {
	/**
	 * 可根据用户名搜索
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年11月29日 上午9:13:51
	 */
	List<UserSignDetail> selectByUserSignDetail(UserSignDetail userSignDetail);

	/**
	 * 通过用户ID查询该用户的所有签到明细
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年12月2日 下午2:39:18
	 */
	List<UserSignDetail> selectByUserId(Integer userId);
	
	/** 
	 * 传入用户id，传入一周的数量，获取到一周签到数量
	 * 
	 * @author 王伟<br>
	 * @date 2017年12月20日 下午2:39:18
	 */
	List<UserSignDetail> selectByUserIdOne(@Param("userId")Integer userId, @Param("st")String st);
}