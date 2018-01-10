package com.ruiec.web.dao;

import org.apache.ibatis.annotations.Param;

import com.ruiec.web.model.DepartmentUser;

public interface DepartmentUserMapper extends BaseMapper<DepartmentUser>{
	
	/**
	 * 按用户ID查询部门ID
	 * @date 2017年10月24日 下午3:35:37
	 */
	DepartmentUser selectByDepartmentId(@Param(value="userId")Integer userId);
}