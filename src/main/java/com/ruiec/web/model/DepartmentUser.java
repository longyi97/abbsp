package com.ruiec.web.model;

import java.util.List;

/**
 * 部门用户中间表
 * @date 2017年11月10日 下午5:26:52
 */
public class DepartmentUser {
    /**部门ID*/
    private Integer departmentId;

    /**用户ID*/
    private Integer userId;
    
    /**用户对象*/
    private List<User> userList;
    
    /**用户对象*/
    public List<User> getUserList() {
		return userList;
	}
    
    /**用户对象*/
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	/**部门ID*/
    public Integer getDepartmentId() {
        return departmentId;
    }

    /**部门ID*/
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    /**用户ID*/
    public Integer getUserId() {
        return userId;
    }

    /**用户ID*/
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}