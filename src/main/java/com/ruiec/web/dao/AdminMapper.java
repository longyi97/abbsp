package com.ruiec.web.dao;

import com.ruiec.web.model.Admin;

/**
 * 后台管理员接口
 * 
 * @author 钟国城<br>
 * @date 2017年11月8日 上午9:31:40
 */
public interface AdminMapper extends BaseMapper<Admin> {

	/**
	 * 根据用户名查询管理员对象
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年11月8日 上午9:31:10
	 */
	public Admin selectByUsername(String username);

}