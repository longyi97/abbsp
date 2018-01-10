package com.ruiec.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruiec.web.dao.ModuleDepartmentMapper;
import com.ruiec.web.dao.ModuleMapper;
import com.ruiec.web.model.Module;
import com.ruiec.web.model.ModuleDepartment;
import com.ruiec.web.service.ModuleService;

/**
 * 版块服务实现
 * 
 * @author bingo<br>
 * @date 2017年10月24日 下午4:43:49
 */
@Service
public class ModuleServiceImpl extends BaseServiceImpl<Module> implements ModuleService {

	private ModuleMapper moduleMapper;
	@Resource
	private ModuleDepartmentMapper moduleDepartmentMapper;

	@Resource
	public void setModuleMapper(ModuleMapper moduleMapper) {
		this.baseMapper = this.moduleMapper = moduleMapper;
	}

	/**
	 * 获取直接子级
	 * 
	 * @author bingo<br>
	 * @date 2017年10月25日 下午8:05:50
	 */
	@Override
	public List<Module> getSubList(Integer parentId) {
		// 一、获取所有记录，筛选出父级ID下直接子类

		// 二、sql语句筛选匹配父级ID记录
		return moduleMapper.selectByParentId(parentId);
	}

	/**
	 * 保存版块及版块所属部门
	 * 
	 * @author bingo<br>
	 * @date 2017年11月3日 下午5:12:27
	 */
	@Override
	@Transactional
	public int insertWithModuleDepartment(Module module, Integer[] departmentIds) {
		int result = 0;
		// 保存版块记录，获取生成的版块ID
		result = moduleMapper.insertSelective(module);
		// 版块所属部门为多个部门
		// 部门ID数组为空则抛异常
		if (departmentIds == null) {
			throw new RuntimeException("所属部门不能为空");
		}
		// 根据部门ID数组封装到版块-部门模型并保存
		for (int i = 0; i < departmentIds.length; i++) {
			ModuleDepartment moduleDepartment = new ModuleDepartment();
			moduleDepartment.setModuleId(module.getId());
			moduleDepartment.setDepartmentId(departmentIds[i]);
			result = moduleDepartmentMapper.insert(moduleDepartment);
		}
		return result;
	}

	/**
	 * 更新版块及版块所属部门
	 * 
	 * @author bingo<br>
	 * @date 2017年11月3日 下午5:16:25
	 */
	@Override
	@Transactional
	public int updateWithModuleDepartment(Module module, Integer[] departmentIds) {
		int result = 0;
		// 更新版块记录
		result = moduleMapper.updateByPrimaryKeySelective(module);
		// 版块所属部门为多个部门
		// 部门ID数组为空则抛异常
		if (departmentIds == null) {
			throw new RuntimeException("所属部门不能为空");
		}
		// 删除现有记录
		moduleDepartmentMapper.deleteByModuleId(module.getId());
		// 根据部门ID数组封装到版块-部门模型并保存
		for (int i = 0; i < departmentIds.length; i++) {
			ModuleDepartment moduleDepartment = new ModuleDepartment();
			moduleDepartment.setModuleId(module.getId());
			moduleDepartment.setDepartmentId(departmentIds[i]);
			result = moduleDepartmentMapper.insert(moduleDepartment);
		}
		return result;
	}

	/**
	 * 删除版块
	 * 
	 * @author bingo<br>
	 * @date 2017年11月6日 下午3:14:08
	 */
	@Override
	@Transactional
	public int deleteByPrimaryKeys(Integer[] ids, Class<Module> t) {
		moduleDepartmentMapper.deleteByPrimaryKeys(ids);
		return moduleMapper.deleteByPrimaryKeys(ids);
	}

	/**
	 * 根据当前登录用户->部门->版块
	 * 
	 * @author bingo<br>
	 * @date 2017年11月16日 下午4:37:50
	 */
	@Override
	public List<Module> selectByUserDepartmentModule(Integer userId){
		return moduleMapper.selectByUserDepartmentModule(userId);
	}
	
	/**
	 * 根据当前登录用户->部门->版块（包含完整父级）
	 * 
	 * @author bingo<br>
	 * @date 2017年11月16日 下午4:37:50
	 */
	@Override
	public List<Module> selectByUserDepartmentModuleAll(Integer userId){
		return moduleMapper.selectByUserDepartmentModuleAll(userId);
	}

	/**
	 * 迭代获取所有父级
	 * 
	 * @author bingo<br>
	 * @date 2017年11月16日 下午10:24:17
	 */
	@Override
	public void getParent(List<Module> contentList, Module module) {
		if (module.getParentId() != null) {
			Module parentModule = selectByPrimaryKey(module.getParentId());
			if (parentModule != null) {
				contentList.add(parentModule);
				getParent(contentList, parentModule);
			}
		}
	}
	
	/**
	 * 获取按层级排序后的所有版块（从module开始所有子级）
	 * 
	 * @param dataList:数据列表（所有版块） module 顶级版块
	 * @author bingo<br>
	 * @date 2017年11月15日 下午2:30:00
	 */
	@Override
	public void getSortModules(List<Module> dataList, List<Module> contentList, Module module) {
		if (module == null) {
			return;
		}
		for (Module item : dataList) {
			if (item.getParentId() == module.getId()) {
				contentList.add(item);
				if (hasSubModules(dataList, item.getId())) {
					getSortModules(dataList, contentList, item);
				}
			}
		}
	}

	/**
	 * 按层级组装
	 * 
	 * @param dataList:数据列表（所有版块） module 顶级版块
	 * @author bingo<br>
	 * @date 2017年11月15日 下午2:30:00
	 */
	@Override
	public void getSubsModules(List<Module> dataList, Module module) {
		if (module == null) {
			return;
		}
		List<Module> sub = new ArrayList<Module>();
		for (Module item : dataList) {
			if (item.getParentId() == module.getId()) {
				sub.add(item);
				if (hasSubModules(dataList, item.getId())) {
					getSubsModules(dataList, item);
				}
			}
		}
		module.setSubModules(sub);
	}

	/**
	 * 判断是否有下级
	 * 
	 * @author bingo<br>
	 * @date 2017年11月15日 下午2:55:28
	 */
	@Override
	public boolean hasSubModules(List<Module> dataList, Integer parentId) {
		for (Module item : dataList) {
			if (item.getParentId() == parentId) {
				return true;
			}
		}
		return false;
	}

	
	/**
	 * 通过指定的版块ID获取所有直接下级版块ID
	 * 
	 * @author bingo<br>
	 * @date 2017年12月31日 下午4:13:50
	 */
	@Override
	public List<Integer> selectSubModuleIds(Integer parentId) {
		List<Integer> list = new ArrayList<>();
		list.add(parentId);
		getSubModuleIds(list, parentId);
		return list;
	}
	
	/**
	 * 递归获取子级ID
	 * 
	 * @author bingo<br>
	 * @date 2017年12月31日 下午4:35:35
	 */
	public void getSubModuleIds(List<Integer> contentList, Integer parentId) {
		List<Integer> list = moduleMapper.selectSubModuleIds(parentId);
		if (null != list && list.size() > 0) {
			contentList.addAll(list);
			for (Integer item : list) {
				getSubModuleIds(contentList, item);
			}
		}
	}
}
