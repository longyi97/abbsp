package com.ruiec.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruiec.web.dao.AdminMapper;
import com.ruiec.web.model.Admin;
import com.ruiec.web.service.AdminService;

/**
 * 管理员服务实现类
 * 
 * @author 钟国城<br>
 * @date 2017年11月8日 上午9:36:23
 */
@Service("adminServiceImpl")
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService {

	private AdminMapper adminMapper;

	@Resource
	public void setAdminMapper(AdminMapper adminMapper) {
		this.baseMapper = this.adminMapper = adminMapper;
	}

	/**
	 * 登录用户名密码验证查询
	 * 
	 * @author 钟国城<br>
	 * @date 2017年11月8日 上午9:36:10
	 */
	@Override
	@Transactional
	public Admin selectByUsername(String username) {
		return adminMapper.selectByUsername(username);
	}

}
