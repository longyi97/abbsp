package com.ruiec.web.service;

import java.util.ArrayList;
import java.util.List;

import com.ruiec.web.model.Department;

/**
 * 部门服务接口
 * 
 * @author bingo<br>
 * @date 2017年10月20日 上午10:03:36
 */
public interface DepartmentService extends BaseService<Department> {

	/**
	 * 获取直接子类列表
	 * 
	 * @author bingo<br>
	 * @date 2017年10月26日 下午7:20:15
	 */
	List<Department> getSubList(Integer parentId);

	/**
	 * 判断是否有下级部门
	 * 
	 * @author bingo<br>
	 * @date 2017年11月15日 下午2:55:28
	 */
	Boolean hasSubDepartments(List<Department> all, Integer parentId);

	/**
	 * 按层级组装部门(子集set入对象中)
	 * 
	 * @author bingo<br>
	 * @date 2017年11月15日 下午2:30:00
	 */
	void getSubsDepartments(List<Department> all, Department department);

	/**
	 * 按层级排序部门
	 * 
	 * @author bingo<br>
	 * @date 2017年12月8日 下午3:50:25
	 */
	void subList(List<Department> all, List<Department> contentList, Integer parentId);

	/**
	 * 组装部门（按部门层级排序，通过名称前面加符号）
	 * 
	 * @author bingo<br>
	 * @date 2017年11月17日 下午3:11:39
	 */
	void subListAddStr(List<Department> all, List<Department> contentList,Integer parentId, String strTab);
	
	/**
	 * 获取所有部门（按部门层级排序，通过名称前面加符号）
	 * 
	 * @param 层级符号
	 * @author bingo<br>
	 * @date 2017年11月17日 下午3:11:39
	 */
	List<Department> getDepartmentsSort(String strTab);
}
