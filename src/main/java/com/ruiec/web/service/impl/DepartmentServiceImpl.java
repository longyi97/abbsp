package com.ruiec.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ruiec.web.dao.DepartmentMapper;
import com.ruiec.web.model.Department;
import com.ruiec.web.service.DepartmentService;

/**
 * 部门服务
 * 
 * @date 2017年10月20日 上午10:09:49
 */
@Service
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements DepartmentService {

	/** 部门DAO */
	private DepartmentMapper departmentMapper;

	/** 注入部门DAO */
	@Resource
	public void setDepartmentMapper(DepartmentMapper departmentMapper) {
		this.baseMapper = this.departmentMapper = departmentMapper;
	}

	/**
	 * 获取直接子类列表
	 * 
	 * @date 2017年10月26日 下午7:20:15
	 */
	@Override
	public List<Department> getSubList(Integer parentId) {
		return departmentMapper.selectByParentId(parentId);
	}

	/**
	 * 判断是否有下级部门
	 * 
	 * @date 2017年11月15日 下午2:55:28
	 */
	public Boolean hasSubDepartments(List<Department> all, Integer parentId) {
		for (Department item : all) {
			if (item.getParentId() == parentId) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 按层级组装部门(子集set入对象中)
	 * 
	 * @date 2017年11月15日 下午2:30:00
	 */
	public void getSubsDepartments(List<Department> all, Department department) {
		List<Department> sub = new ArrayList<Department>();
		for (Department item : all) {
			if (item.getParentId() == department.getId()) {
				sub.add(item);
				if (hasSubDepartments(all, item.getId())) {
					getSubsDepartments(all, item);
				}
			}
		}
		department.setSubDepartments(sub);
	}

	/**
	 * 按层级排序部门
	 * 
	 * @date 2017年12月8日 下午3:50:25
	 */
	public void subList(List<Department> all, List<Department> contentList, Integer parentId) {
		for (Department item : all) {
			if (parentId == item.getId()) {
				contentList.add(item);
				if (hasSubDepartments(all, item.getId())) {
					subList(all, contentList, item.getId());
				}
			}
		}
	}

	/**
	 * 组装部门（按部门层级排序，通过名称前面加符号）
	 * 
	 * @date 2017年11月17日 下午3:11:39
	 */
	public void subListAddStr(List<Department> all, List<Department> contentList,Integer parentId, String strTab) {
		for (Department item : all) {
			if (parentId == item.getParentId()) {
				if (null != item.getParentId()) {
					item.setName(strTab + item.getName());
				}
				contentList.add(item);
				if (hasSubDepartments(all, item.getId())) {
					subListAddStr(all, contentList, item.getId(), "　" + strTab);
				}
			}
		}
	}
	
	/**
	 * 获取所有部门（按部门层级排序，通过名称前面加符号）
	 * 
	 * @param 层级符号
	 * @date 2017年11月17日 下午3:11:39
	 */
	public List<Department> getDepartmentsSort(String strTab) {
		List<Department> all = selectAll();
		List<Department> contentList = new ArrayList<Department>();
		subListAddStr(all, contentList, null, strTab);
		return contentList;
	}
}
