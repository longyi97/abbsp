/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */
package com.ruiec.web.dao;

import org.apache.ibatis.annotations.Param;

import com.ruiec.web.model.ModuleDepartment;

/**
 * 版块-部门DAO接口
 * 
 * @author bingo<br>
 * @date 2017年10月24日 下午2:37:43
 */
public interface ModuleDepartmentMapper extends BaseMapper<ModuleDepartment> {
	
	/**
	 * 根据版块ID删除记录
	 * 
	 * @author bingo<br>
	 * @date 2017年11月8日 下午6:20:37
	 */
	Integer deleteByModuleId(@Param(value = "moduleId") Integer moduleId);
	
}