package com.ruiec.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ruiec.web.dao.UserSignDetailMapper;
import com.ruiec.web.model.UserSignDetail;
import com.ruiec.web.service.UserSignDetailService;

/**
 * 签到明细接口
 * 
 * @author 钟国城<br>
 * @date 2017年11月28日 下午4:01:44
 */
@Service("userSignDetailServiceImpl")
public class UserSignDetailServiceImpl extends BaseServiceImpl<UserSignDetail> implements UserSignDetailService {

	private UserSignDetailMapper userSignDetailMapper;

	@Resource
	public void setUserSignDetailMapper(UserSignDetailMapper userSignDetailMapper) {
		this.baseMapper = this.userSignDetailMapper = userSignDetailMapper;
	}

	/**
	 * 可根据用户名搜索
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年11月29日 上午9:13:51
	 */
	public List<UserSignDetail> selectByUserSignDetail(UserSignDetail userSignDetail) {
		return userSignDetailMapper.selectByUserSignDetail(userSignDetail);
	}

	/**
	 * 通过用户ID查询该用户的所有签到明细
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年12月2日 下午2:39:18
	 */
	public List<UserSignDetail> selectByUserId(Integer userId) {
		return userSignDetailMapper.selectByUserId(userId);
	}
	
	/**
	 * 传入用户id，传入一周的数量，获取到一周签到数量
	 * 
	 * @author 王伟<br>
	 * @date 2017年12月20日 下午2:39:18
	 */
	@Override
	public List<UserSignDetail> selectByUserIdOne(Integer userId, String st) {
		return userSignDetailMapper.selectByUserIdOne(userId,st);
	}
}
