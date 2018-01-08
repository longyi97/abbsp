/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */
package com.ruiec.web.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.ruiec.web.model.UserSign;

/**
 * 用户签到接口
 * 
 * @author 钟国城<br>
 * @date 2017年11月28日 下午4:01:44
 */
public interface UserSignService extends BaseService<UserSign> {

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
	 * 用户签到
	 * 
	 * @author zhongguocheng<br>
	 * @throws ParseException
	 * @date 2017年12月5日 上午10:25:52
	 */
	Boolean isSign(Integer userId) throws ParseException;

	/**
	 * 查询今日签到数
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年12月6日 下午8:30:57
	 */
	Integer selectTodaySignCount(Date date);
}
