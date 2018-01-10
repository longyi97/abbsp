package com.ruiec.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruiec.web.dto.UserDTO;
import com.ruiec.web.model.User;

/**
 * 前台用户接口
 * 
 * @date 2017年11月10日 下午5:22:20
 */
public interface UserMapper extends BaseMapper<User> {

	/**
	 * 按用户名查询用户信息（验证数据库是否存在重复的用户名）
	 * 
	 * @date 2017年10月24日 下午3:35:37
	 */
	User selectByUsername(@Param(value = "username") String username);

	/**
	 * 按用户id查询部门名字
	 * 
	 * @date 2017年10月24日 下午3:35:37
	 */
	String selectdepartment(@Param(value = "id") Integer id);

	/**
	 * 获取所有用户数量
	 * 
	 * @date 2017年10月31日 下午3:56:43
	 */
	Integer countAll();

	/**
	 * 按用户id查询部门id
	 * 
	 * @date 2017年10月24日 下午3:35:37
	 */
	Integer selectDepartmentId(@Param(value = "id") Integer id);

	/**
	 * 根据用户名，部门，是否锁定搜索
	 * 
	 * @date 2017年11月14日 下午4:27:12
	 */
	List<User> selectByDepartmentAllUser(UserDTO userDTO);

	/**
	 * 通过主键更新（可允许birthday，mobile，email，sign字段为空）
	 * 
	 * @date 2017年11月15日 下午4:11:06
	 */
	public int updateByPrimaryKeyPart(User user);
}