/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */
package com.ruiec.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruiec.web.dao.DepartmentUserMapper;
import com.ruiec.web.dao.UserMapper;
import com.ruiec.web.dao.UserPointsMapper;
import com.ruiec.web.dao.UserSignMapper;
import com.ruiec.web.dto.UserDTO;
import com.ruiec.web.model.DepartmentUser;
import com.ruiec.web.model.User;
import com.ruiec.web.model.UserPoints;
import com.ruiec.web.service.UserService;

/**
 * 用户服务实现类
 * 
 * @author 钟国城<br>
 * @date 2017年12月1日 下午3:58:11
 */
@Service("userServiceImpl")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	private UserMapper userMapper;
	@Resource
	private DepartmentUserMapper departmentUserMapper;
	@Resource
	private UserPointsMapper userPointsMapper;
	@Resource
	private UserSignMapper userSignMapper;

	@Resource
	public void setUserMapper(UserMapper userMapper) {
		this.baseMapper = this.userMapper = userMapper;
	}

	/**
	 * 根据用户名查询用户信息
	 * 
	 * @author Jerry<br>
	 * @date 2017年10月24日 下午3:32:39
	 */
	@Override
	public User selectByUsername(String username) {
		return userMapper.selectByUsername(username);
	}

	/**
	 * 新增用户信息服务接口实现
	 * 
	 * @author Jerry<br>
	 * @date 2017年10月24日 下午5:59:56
	 */
	@Override
	@Transactional
	public int insertUser(User user) {
		User u = userMapper.selectByUsername(user.getUsername());
		if (u != null)
			return 0;
		userMapper.insertSelective(user);
		// 新增一条记录到用户积分表中
		UserPoints newUserPoints = new UserPoints();
		newUserPoints.setUserId(user.getId());
		userPointsMapper.insertSelective(newUserPoints);
		return 1;
	}

	/**
	 * 新增用户(含部门)
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年10月24日 下午5:59:20
	 */
	@Transactional
	public void insertUserAndDepartmentUser(User user, Integer departmentId) {
		try {
			// 插入用户信息到用户表
			userMapper.insertSelective(user);
			// 插入用户ID和部门ID到用户部门联表
			DepartmentUser departmentUser = new DepartmentUser();
			departmentUser.setDepartmentId(departmentId);
			departmentUser.setUserId(user.getId());
			departmentUserMapper.insert(departmentUser);
			// 新增一条记录到用户积分表中
			UserPoints newUserPoints = new UserPoints();
			newUserPoints.setUserId(user.getId());
			userPointsMapper.insertSelective(newUserPoints);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * 修改用户(含部门)
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年10月24日 下午5:59:20
	 */
	@Transactional
	public void updateUserAndDepartmentUser(User user, DepartmentUser departmentUser) {
		try {
			// 修改用户信息到用户表
			userMapper.updateByPrimaryKeyPart(user);
			// 修改部门用户信息到部门用户表
			departmentUserMapper.updateByPrimaryKey(departmentUser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * 修改用户(无部门)
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年10月24日 下午5:59:20
	 */
	@Transactional
	public void updateUserAndInsertDepartmentUser(User user, DepartmentUser departmentUser) {
		try {
			// 修改用户信息到用户表
			userMapper.updateByPrimaryKeyPart(user);
			// 插入部门用户信息到部门用户表
			departmentUserMapper.insert(departmentUser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * 删除用户(含部门)
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年10月24日 下午5:59:20
	 */
	@Transactional
	public void deletesUserAndDepartmentUser(Integer[] userId) {
		try {
			departmentUserMapper.deleteByPrimaryKeys(userId);
			userMapper.deleteByPrimaryKeys(userId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
	 * 按用户id查询部门名字
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年10月24日 下午3:35:37
	 */
	@Override
	public String selectdepartment(Integer id) {
		return userMapper.selectdepartment(id);
	}

	/**
	 * 获取所有用户数量
	 * 
	 * @author bingo<br>
	 * @date 2017年10月31日 下午3:56:43
	 */
	@Override
	public Integer countAll() {
		return userMapper.countAll();
	}

	/**
	 * 按用户id查询部门id
	 * 
	 * @author 王伟<br>
	 * @date 2017年11月11日 下午3:56:43
	 */
	@Override
	public Integer selectDepartmentId(Integer id) {
		return userMapper.selectDepartmentId(id);
	}

	/**
	 * 根据用户名，部门，是否锁定搜索
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年11月14日 下午4:27:12
	 */
	public List<User> selectByDepartmentAllUser(UserDTO userDTO) {
		return userMapper.selectByDepartmentAllUser(userDTO);
	}

	/**
	 * 通过主键更新（可允许birthday，mobile，email，sign字段为空）
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年11月15日 下午4:11:06
	 */
	public int updateByPrimaryKeyPart(User user) {
		return userMapper.updateByPrimaryKeyPart(user);
	}
}
