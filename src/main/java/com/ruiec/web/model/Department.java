package com.ruiec.web.model;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

/**
 * 部门模型
 * 
 * @author bingo<br>
 * @date 2017年10月20日 上午9:52:51
 */
public class Department extends BaseModel {

	/** serialVersionUID */
	private static final long serialVersionUID = 6170895037878938104L;

	/** 部门名称 */
	@NotEmpty
//	@Pattern(regexp = "\\w{1,25}", message = "用户名长度1-25")
	@Length(min=1, max=25, message="用户名长度必须在1-25之间") 
	private String name;

	/** 描述 */
//	@Pattern(regexp = "\\w{0,255}", message = "长度不超过255")
	@Length(min=0, max=255, message="长度不超过255") 
	private String description;

	/** 部门管理员，默认为超级管理员1 */
	private Integer adminUserId;

	/** 排序 */
	@Max(value = 99, message = "长度不超过2位的数字")
	private Integer sort;

	/** 父级ID */
	private Integer parentId;

	/** 直接子部门 */
	private List<Department> subDepartments;

	/** 获取 subDepartments */
	public List<Department> getSubDepartments() {
		return subDepartments;
	}

	/** 设置subDepartments */
	public void setSubDepartments(List<Department> subDepartments) {
		this.subDepartments = subDepartments;
	}

	/**
	 * 用户对象
	 */
	private List<User> userList;

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	/**
	 * 部门名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 部门名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 描述
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 部门管理员，默认为超级管理员1
	 */
	public Integer getAdminUserId() {
		return adminUserId;
	}

	/**
	 * 部门管理员，默认为超级管理员1
	 */
	public void setAdminUserId(Integer adminUserId) {
		this.adminUserId = adminUserId;
	}

	/**
	 * 排序
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * 排序
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * 父级ID
	 */
	public Integer getParentId() {
		return parentId;
	}

	/**
	 * 父级ID
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

}