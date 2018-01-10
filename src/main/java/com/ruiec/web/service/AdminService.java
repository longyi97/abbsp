package com.ruiec.web.service;

import com.ruiec.web.model.Admin;

/**
 * 管理员服务接口
 * 
 * @date 2017年11月8日 上午9:35:45
 */
public interface AdminService extends BaseService<Admin> {

	/**
	 * 登录用户名密码验证查询
	 * 
	 * @date 2017年11月8日 上午9:35:29
	 */
	public Admin selectByUsername(String username);

}
