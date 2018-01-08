/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */
package com.ruiec.web.service;

import com.ruiec.web.model.DepartmentUser;

/**
 * 用户部门表访问数据接口
 * 
 * @author zhongguocheng<br>
 * @date 2017年10月24日 下午2:12:35
 */
public interface DepartmentUserService extends BaseService<DepartmentUser> {

	/**
	 * 按用户ID查询部门ID
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年10月24日 下午3:31:43
	 */
	public DepartmentUser selectByDepartmentId(Integer userId);
}
