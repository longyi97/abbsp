package com.ruiec.web.dao;

import org.apache.ibatis.annotations.Param;

import com.ruiec.web.model.ModuleDepartment;

/**
 * 版块-部门DAO接口
 * 
 * @date 2017年10月24日 下午2:37:43
 */
public interface ModuleDepartmentMapper extends BaseMapper<ModuleDepartment> {
	
	/**
	 * 根据版块ID删除记录
	 * 
	 * @date 2017年11月8日 下午6:20:37
	 */
	Integer deleteByModuleId(@Param(value = "moduleId") Integer moduleId);
	
}