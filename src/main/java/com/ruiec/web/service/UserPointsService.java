/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */
package com.ruiec.web.service;

import com.ruiec.web.model.UserPoints;
import com.ruiec.web.model.UserPointsDetail;

/**
 * 积分记录服务接口
 * 
 * @author Jerry<br>
 * @date 2017年11月28日 下午4:31:43
 */
public interface UserPointsService extends BaseService<UserPoints> {

	/**
	 * 根据用户ID查询用户积分
	 * 
	 * @author Jerry<br>
	 * @date 2017年11月29日 下午5:49:32
	 */
	UserPoints selectByUserId(Integer userId);

	/**
	 * 根据用户名查询是否存在
	 * 
	 * @author Jerry<br>
	 * @date 2017年11月30日 上午11:16:41
	 */
	public UserPoints selectByUsername(String username);

	/**
	 * 用户积分表和积分明细表更新或插入积分记录
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年11月30日 下午4:09:21
	 */
	public void insertOrUpdateByPoints(UserPointsDetail userPointsDetail);

	/**
	 * 积分变动业务
	 * 
	 * @author bingo<br>
	 * @date 2017年12月1日 下午3:53:59
	 */
	Integer changePointsBusiness(Integer userId, String item, Float changePoints);
	
	/**
	 * 积分变动业务(判断上限)
	 * 
	 * @return 末达上限返回正常分值；临界上限返回上限值减当前分值；超过上限返回0.00
	 * @author bingo<br>
	 * @date 2017年12月1日 下午3:53:59
	 */
	Float changePointsLimitMax(Integer userId, String item, Float changePoints, Float max, Float current);

}
