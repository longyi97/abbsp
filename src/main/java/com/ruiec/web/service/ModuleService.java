/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */
package com.ruiec.web.service;

import java.util.List;

import com.ruiec.web.model.Module;

/**
 * 版块服务接口
 * 
 * @author bingo<br>
 * @date 2017年10月24日 下午4:41:50
 */
public interface ModuleService extends BaseService<Module> {

	/**
	 * 获取直接子级
	 * 
	 * @author bingo<br>
	 * @date 2017年10月25日 下午8:05:50
	 */
	List<Module> getSubList(Integer parentId);
	
	/**
	 * 保存版块及版块所属部门
	 * 
	 * @author bingo<br>
	 * @date 2017年11月3日 下午5:12:27
	 */
	public int insertWithModuleDepartment(Module record, Integer[] departmentIds);
	
	/**
	 * 更新版块及版块所属部门
	 * 
	 * @author bingo<br>
	 * @date 2017年11月3日 下午5:12:27
	 */
	public int updateWithModuleDepartment(Module record, Integer[] departmentIds);
	
	/**
	 * 根据当前登录用户->部门->版块
	 * 
	 * @author bingo<br>
	 * @date 2017年11月16日 下午4:37:50
	 */
	public List<Module> selectByUserDepartmentModule(Integer userId);
	
	/**
	 * 根据当前登录用户->部门->版块（包含完整父级）
	 * 
	 * @author bingo<br>
	 * @date 2017年11月16日 下午4:37:50
	 */
	public List<Module> selectByUserDepartmentModuleAll(Integer userId);
	

	/**
	 * 迭代获取所有父级
	 * 
	 * @author bingo<br>
	 * @date 2017年11月16日 下午10:24:17
	 */
	public void getParent(List<Module> contentList, Module module);
	
	/**
	 * 获取按层级排序后的所有版块（从module开始所有子级）
	 * 
	 * @param dataList:数据列表（所有版块） module 顶级版块
	 * @author bingo<br>
	 * @date 2017年11月15日 下午2:30:00
	 */
	public void getSortModules(List<Module> dataList, List<Module> contentList, Module module);

	/**
	 * 按层级组装
	 * 
	 * @param dataList:数据列表（所有版块） module 顶级版块
	 * @author bingo<br>
	 * @date 2017年11月15日 下午2:30:00
	 */
	public void getSubsModules(List<Module> dataList, Module module);

	/**
	 * 判断是否有下级
	 * 
	 * @author bingo<br>
	 * @date 2017年11月15日 下午2:55:28
	 */
	public boolean hasSubModules(List<Module> dataList, Integer parentId);

	/**
	 * 通过指定的版块ID获取所有下级版块ID
	 * 
	 * @author bingo<br>
	 * @date 2017年12月31日 下午4:13:50
	 */
	public List<Integer> selectSubModuleIds(Integer parentId);
}
