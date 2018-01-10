package com.ruiec.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ruiec.web.model.Module;

/**
 * 版块DAO接口
 * 
 * @author bingo<br>
 * @date 2017年10月24日 下午2:35:06
 */
public interface ModuleMapper extends BaseMapper<Module> {

	/**
	 * 获取直接下级
	 * 
	 * @author bingo<br>
	 * @date 2017年10月25日 下午8:14:59
	 */
	public List<Module> selectByParentId(@Param("parentId") Integer parentId);
	
	/**
	 * 根据当前登录用户->部门->版块
	 * 
	 * @author bingo<br>
	 * @date 2017年11月16日 下午4:37:50
	 */
	public List<Module> selectByUserDepartmentModule(@Param("userId") Integer userId);
	
	/**
	 * 根据当前登录用户->部门->版块（包含完整父级）
	 * 
	 * @author bingo<br>
	 * @date 2017年11月16日 下午4:37:50
	 */
	public List<Module> selectByUserDepartmentModuleAll(@Param("userId") Integer userId);
	
	/**
	 * 通过指定的版块ID获取直接下级版块ID
	 * 
	 * @author bingo<br>
	 * @date 2017年12月31日 下午4:13:50
	 */
	public List<Integer> selectSubModuleIds(@Param("parentId") Integer parentId);
}