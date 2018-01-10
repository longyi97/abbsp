package com.ruiec.web.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruiec.web.dao.ConfigMapper;
import com.ruiec.web.dao.UserSignDetailMapper;
import com.ruiec.web.dao.UserSignMapper;
import com.ruiec.web.model.SignRule;
import com.ruiec.web.model.UserPointsDetail;
import com.ruiec.web.model.UserSign;
import com.ruiec.web.model.UserSignDetail;
import com.ruiec.web.service.SignRuleService;
import com.ruiec.web.service.UserPointsService;
import com.ruiec.web.service.UserSignService;
import com.ruiec.web.util.RuiecDateUtils;

/**
 * 用户签到实现
 * 
 * @date 2017年11月28日 下午4:01:44
 */
@Service("userSignServiceImpl")
public class UserSignServiceImpl extends BaseServiceImpl<UserSign> implements UserSignService {

	private UserSignMapper userSignMapper;
	@Resource
	private UserSignDetailMapper userSignDetailMapper;
	@Resource
	private UserPointsService userPointsService;
	@Resource
	private ConfigMapper configMapper;
	@Resource
	private SignRuleService signRuleService;

	@Resource
	public void setUserSignMapper(UserSignMapper userSignMapper) {
		this.baseMapper = this.userSignMapper = userSignMapper;
	}

	/**
	 * 可根据用户名搜索
	 * 
	 * @date 2017年11月29日 上午9:13:51
	 */
	public List<UserSign> selectByUserSign(UserSign userSign) {
		return userSignMapper.selectByUserSign(userSign);
	}

	/**
	 * 通过用户ID查找该用户的用户签到记录
	 * 
	 * @date 2017年12月1日 下午1:57:00
	 */
	public UserSign selectByUserName(Integer userId) {
		return userSignMapper.selectByUserName(userId);
	}

	/**
	 * 用户签到
	 * 
	 * @throws ParseException
	 * @date 2017年12月5日 上午10:25:52
	 */
	@Transactional
	public Boolean isSign(Integer userId) throws ParseException {
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateNowStr = sdf.format(today);
		today = sdf.parse(dateNowStr);
		UserSign userSign = new UserSign();
		UserPointsDetail userPointsDetail = new UserPointsDetail();
		// 判断用户签到表是否存在该用户记录
		if (userSignMapper.selectByUserName(userId) != null) {
			Date lastSignTime = userSignMapper.selectByUserName(userId).getLastSignTime();
			Integer signMinus = (int) RuiecDateUtils.getTimePositiveDifference(lastSignTime, today, 3);
			if (signMinus == 0) {
				return false;
			}
			userSign = userSignMapper.selectByUserName(userId);
			// 修改用户签到表中的最后签到时间 ,总签到天数
			userSign.setLastSignTime(today);
			userSign.setSignCount(userSign.getSignCount() + 1);
			if (signMinus == 2) {
				// 修改用户签到表中的连续签到天数+1
				userSign.setSignContinuousCount(userSign.getSignContinuousCount() + 1);

			} else {
				// 连续签到天数初始化为1
				userSign.setSignContinuousCount(1);
			}
			// 更新用户签到表
			userSignMapper.updateByPrimaryKeySelective(userSign);
		} else {
			userSign.setUserId(userId);
			userSign.setLastSignTime(today);
			userSign.setSignContinuousCount(1);
			userSign.setSignCount(1);
			userSignMapper.insertSelective(userSign);
		}
		// 新增签到明细记录
		UserSignDetail userSignDetail = new UserSignDetail();
		userSignDetail.setLastSignTime(today);
		userSignDetail.setSignContinuousCount(userSign.getSignContinuousCount());
		userSignDetail.setSignCount(userSign.getSignCount());
		userSignDetail.setUserId(userId);
		userSignDetailMapper.insertSelective(userSignDetail);
		// 签到积分
		Float signPoints = Float.valueOf(configMapper.selectByKey("points_sign").getValue());
		// 若符合连续签到规则则额外加分
		List<SignRule> signRuleList = signRuleService.selectAll();
		for (int i = 0; i < signRuleList.size(); i++) {
			if (signRuleList.get(i).getContinuousDays() == userSign.getSignContinuousCount()) {
				signPoints = signPoints + signRuleList.get(i).getRewardPoints();
			}
		}
		// 签到增加积分
		userPointsDetail.setUserId(userId);
		userPointsDetail.setItem("签到");
		userPointsDetail.setChangePoints(signPoints);
		userPointsService.insertOrUpdateByPoints(userPointsDetail);
		return true;
	}

	/**
	 * 查询今日签到数
	 * 
	 * @date 2017年12月6日 下午8:30:57
	 */
	public Integer selectTodaySignCount(Date date) {
		return userSignMapper.selectTodaySignCount(date);
	}
}
