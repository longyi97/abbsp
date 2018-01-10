package com.ruiec.web.service;

import com.ruiec.web.model.DepartmentUser;

/**
 * 用户部门表访问数据接口
 * 
 * @date 2017年10月24日 下午2:12:35
 */
public interface DepartmentUserService extends BaseService<DepartmentUser> {

	/**
	 * 按用户ID查询部门ID
	 * 
	 * @date 2017年10月24日 下午3:31:43
	 */
	public DepartmentUser selectByDepartmentId(Integer userId);
}
