package com.ruiec.web.service;

import java.util.List;

import com.ruiec.web.dto.UserDTO;
import com.ruiec.web.model.DepartmentUser;
import com.ruiec.web.model.User;

/**
 * 用户服务接口
 * 
 * @author Jerry<br>
 * @date 2017年10月24日 下午2:12:35
 */
public interface UserService extends BaseService<User> {

	/**
	 * 根据用户名查询用户信息
	 * 
	 * @author Jerry<br>
	 * @date 2017年10月24日 下午3:31:43
	 */
	public User selectByUsername(String username);

	/**
	 * 新增用户信息服务接口
	 * 
	 * @author Jerry<br>
	 * @date 2017年10月24日 下午5:59:20
	 */
	public int insertUser(User user);

	/**
	 * 新增用户(含部门)
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年10月24日 下午5:59:20
	 */
	public void insertUserAndDepartmentUser(User user, Integer departmentId);

	/**
	 * 修改用户(含部门)
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年10月24日 下午5:59:20
	 */
	public void updateUserAndDepartmentUser(User user, DepartmentUser departmentUser);

	/**
	 * 修改用户(无部门)
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年10月24日 下午5:59:20
	 */
	public void updateUserAndInsertDepartmentUser(User user, DepartmentUser departmentUser);

	/**
	 * 删除用户(含部门)
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年10月24日 下午5:59:20
	 */
	public void deletesUserAndDepartmentUser(Integer[] userId);

	/**
	 * 按用户id查询部门名字
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年10月24日 下午3:35:37
	 */
	public String selectdepartment(Integer id);

	/**
	 * 获取所有用户数量
	 * 
	 * @author bingo<br>
	 * @date 2017年10月31日 下午3:56:43
	 */
	public Integer countAll();

	/**
	 * 按用户id查询部门id
	 * 
	 * @author 王伟<br>
	 * @date 2017年11月11日 下午3:35:37
	 */
	public Integer selectDepartmentId(Integer id);

	/**
	 * 根据用户名，部门，是否锁定搜索
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年11月14日 下午4:27:12
	 */
	public List<User> selectByDepartmentAllUser(UserDTO userDTO);

	/**
	 * 通过主键更新（可允许birthday，mobile，email，sign字段为空）
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年11月15日 下午4:11:06
	 */
	public int updateByPrimaryKeyPart(User user);

}
