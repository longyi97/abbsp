package com.ruiec.web.service.impl;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ruiec.web.dao.DepartmentUserMapper;
import com.ruiec.web.model.DepartmentUser;
import com.ruiec.web.service.DepartmentUserService;

/**
 * 用户服务实现类
 * @author Jerry<br>
 * @date 2017年10月24日 下午2:13:46
 */
@Service("departmentUserServiceImpl")
public class DepartmentUserServiceImpl extends BaseServiceImpl<DepartmentUser> implements DepartmentUserService{
	
	private DepartmentUserMapper departmentUserMapper;
	
	@Resource
	public void setDepartmentUserMapper(DepartmentUserMapper departmentUserMapper) {
		this.baseMapper=this.departmentUserMapper = departmentUserMapper;
	}
	
	/**
	 * 按用户ID查询部门ID
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年10月24日 下午3:32:39
	 */
	@Override
	public DepartmentUser selectByDepartmentId(Integer userId) {
		return departmentUserMapper.selectByDepartmentId(userId);
	}
}
