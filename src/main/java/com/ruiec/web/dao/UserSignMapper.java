package com.ruiec.web.dao;

import java.util.Date;
import java.util.List;

import com.ruiec.web.model.UserSign;

/**
 * 用户签到接口
 * 
 * @author 钟国城<br>
 * @date 2017年11月28日 下午3:54:32
 */
public interface UserSignMapper extends BaseMapper<UserSign> {
	/**
	 * 可根据用户名搜索
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年11月29日 上午9:13:51
	 */
	List<UserSign> selectByUserSign(UserSign userSign);

	/**
	 * 通过用户ID查找该用户的用户签到记录
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年12月1日 下午1:57:00
	 */
	UserSign selectByUserName(Integer userId);

	/**
	 * 查询今日签到数
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年12月6日 下午8:30:57
	 */
	Integer selectTodaySignCount(Date lastSignTime);
}