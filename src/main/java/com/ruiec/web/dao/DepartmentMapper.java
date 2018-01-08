/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */
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