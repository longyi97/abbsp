package com.ruiec.web.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruiec.web.dao.UserPointsDetailMapper;
import com.ruiec.web.dao.UserPointsMapper;
import com.ruiec.web.model.UserPoints;
import com.ruiec.web.model.UserPointsDetail;
import com.ruiec.web.service.UserPointsService;

/**
 * 积分记录服务实现类
 * 
 * @date 2017年11月28日 下午4:34:52
 */
@Service("userPointsServiceImpl")
public class UserPointsServiceImpl extends BaseServiceImpl<UserPoints> implements UserPointsService {

	protected static final Logger LOGGER = LoggerFactory.getLogger(UserPointsDetailServiceImpl.class);

	private UserPointsMapper userPointsMapper;
	@Resource
	private UserPointsDetailMapper userPointsDetailMapper;

	@Resource
	public void setUserPointsMapper(UserPointsMapper userPointsMapper) {
		this.baseMapper = this.userPointsMapper = userPointsMapper;
	}

	/**
	 * 根据用户ID查询用户积分
	 * 
	 * @date 2017年11月29日 下午5:49:32
	 */
	@Override
	public UserPoints selectByUserId(Integer userId) {
		return userPointsMapper.selectByUserId(userId);
	}

	/**
	 * 根据用户名查询是否存在
	 * 
	 * @date 2017年11月30日 上午11:16:41
	 */
	public UserPoints selectByUsername(String username) {
		return userPointsMapper.selectByUsername(username);
	}

	/**
	 * 用户积分表和积分明细表更新或插入积分记录
	 * 
	 * @date 2017年11月30日 下午4:09:21
	 */
	@Transactional
	public void insertOrUpdateByPoints(UserPointsDetail userPointsDetail) {
		// 根据用户ID查询用户积分表，若存在该用户记录，则更新该记录，若无，则插入新记录
		UserPoints userPoints = userPointsMapper.selectByUserId(userPointsDetail.getUserId());
		// 更新用户积分记录
		if (userPoints != null) {
			// 先更新积分明细表中的当前积分
			userPointsDetail.setCurrentPoints(userPoints.getPoints() + userPointsDetail.getChangePoints());
			// 后更新用户积分表中的当前积分
			userPoints.setPoints(userPoints.getPoints() + userPointsDetail.getChangePoints());
			// 加分则更新用户积分表中的历史积分
			if (userPointsDetail.getChangePoints() > 0) {
				userPoints.setHistoryPoints(userPoints.getHistoryPoints() + userPointsDetail.getChangePoints());
			}
			// 更新用户积分表
			userPointsMapper.updateByPrimaryKeySelective(userPoints);
			// 新增记录到积分明细表中
			userPointsDetailMapper.insertSelective(userPointsDetail);
			// 插入用户积分记录
		} else {
			// 用户积分不存在记录则增加一条记录
			UserPoints newUserPoints = new UserPoints();
			newUserPoints.setPoints(userPointsDetail.getChangePoints());
			newUserPoints.setUserId(userPointsDetail.getUserId());
			if (userPointsDetail.getChangePoints() > 0) {
				newUserPoints.setHistoryPoints(userPointsDetail.getChangePoints());
			}
			userPointsMapper.insertSelective(newUserPoints);
			// 新增记录到积分明细表中
			userPointsDetail.setCurrentPoints(userPointsDetail.getChangePoints());
			userPointsDetailMapper.insertSelective(userPointsDetail);
		}
	}

	/**
	 * 积分变动业务
	 * 
	 * @date 2017年12月1日 下午3:53:59
	 */
	@Override
	public Integer changePointsBusiness(Integer userId, String item, Float changePoints) {
		try {
			UserPointsDetail userPointsDetail = new UserPointsDetail();
			userPointsDetail.setItem(item);
			userPointsDetail.setUserId(userId);
			userPointsDetail.setChangePoints(changePoints);
			insertOrUpdateByPoints(userPointsDetail);
			LOGGER.info("积分修改成功");
			return 1;
		} catch (Exception e) {
			LOGGER.error("积分修改失败", e);
			return 0;
		}
	}

	/**
	 * 积分变动业务(判断上限)
	 * 
	 * @return 末达上限返回正常分值；临界上限返回上限值减当前分值；超过上限返回0.00
	 * @date 2017年12月1日 下午3:53:59
	 */
	@Override
	public Float changePointsLimitMax(Integer userId, String item, Float changePoints, Float max, Float current) {
		if (max >= current + changePoints) {
			changePointsBusiness(userId, item, changePoints);
			return changePoints;
		} else if (max > current && max < current + changePoints) {
			changePointsBusiness(userId, item + "(积分达上限)", max - current);
			return max - current;
		}
		return 0f;
	}

}
