package com.ruiec.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruiec.web.model.Department;

/**
 * 部门DAO接口
 * 
 * @author bingo<br>
 * @date 2017年10月20日 上午9:55:51
 */
public interface DepartmentMapper extends BaseMapper<Department> {

	/**
	 * 获取直接下级
	 * 
	 * @author bingo<br>
	 * @date 2017年10月26日 下午7:18:53
	 */
	public List<Department> selectByParentId(@Param("parentId") Integer parentId);
}