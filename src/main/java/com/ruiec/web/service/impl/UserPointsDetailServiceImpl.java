package com.ruiec.web.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruiec.web.dao.UserPointsDetailMapper;
import com.ruiec.web.dao.UserPointsMapper;
import com.ruiec.web.model.UserPointsDetail;
import com.ruiec.web.service.UserPointsDetailService;

/**
 * 积分记录详情实现类
 * 
 * @author Jerry<br>
 * @date 2017年11月28日 下午5:44:03
 */
@Service("userPointsDetailServiceImpl")
public class UserPointsDetailServiceImpl extends BaseServiceImpl<UserPointsDetail> implements UserPointsDetailService {

	private UserPointsDetailMapper userPointsDetailMapper;
	@Resource
	private UserPointsMapper userPointsMapper;

	@Resource
	public void setUserPointsDetailMapper(UserPointsDetailMapper userPointsDetailMapper) {
		this.baseMapper = this.userPointsDetailMapper = userPointsDetailMapper;
	}

	/**
	 * 插入用户积分明细记录，同时更新用户积分表中的积分
	 * 
	 * @author Jerry<br>
	 * @date 2017年11月29日 下午5:26:28
	 */
	@Override
	@Transactional
	public int insertSelective(UserPointsDetail t) {
		// 插入用户积分明细记录，同时更新用户积分表中的积分
		// 1. 插入用户积分明细记录
		userPointsDetailMapper.insertSelective(t);
		// 2. 更新用户积分表中的积分
		return userPointsMapper.updateByUserId(t.getUserId());
	}

	/**
	 * 通过用户ID查询该用户的所有积分明细
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年12月2日 下午2:39:18
	 */
	public List<UserPointsDetail> selectByUserId(Integer userId) {
		return userPointsDetailMapper.selectByUserId(userId);
	}

	/**
	 * 通过用户ID查询该用户通过签到所获得的积分之和
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年12月2日 下午4:23:05
	 */
	public Float selectByAllSignPoints(Integer userId) {
		return userPointsDetailMapper.selectByAllSignPoints(userId);
	}

	/**
	 * 计算用户今日获得积分数
	 * 
	 * @author bingo<br>
	 * @date 2017年12月4日 下午8:50:35
	 */
	@Override
	public synchronized Float countUserPointsToday(Integer userId) {
		// 获取用户今日的积分明细
		UserPointsDetail userPointsDetail = new UserPointsDetail();
		userPointsDetail.setUserId(userId);
		userPointsDetail.setCreateTime(new Date());
		List<UserPointsDetail> userPointsDetails = userPointsDetailMapper.selectByUserPointsDetail(userPointsDetail);
		float pointsSum = 0;
		for (UserPointsDetail item : userPointsDetails) {
			// 加分项
			if (Float.valueOf(item.getChangePoints()) > 0) {
				pointsSum += item.getChangePoints();
			}
		}
		return pointsSum;
	}
}
