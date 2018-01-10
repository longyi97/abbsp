package com.ruiec.web.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.web.support.controller.BaseAdminController;
import com.ruiec.web.common.Message;
import com.ruiec.web.model.Department;
import com.ruiec.web.model.Module;
import com.ruiec.web.service.DepartmentService;
import com.ruiec.web.service.ModuleService;

/**
 * 版块控制器
 * 
 * @author bingo<br>
 * @date 2017年10月20日 上午10:28:44
 */
@Controller
@RequestMapping("/admin/module")
public class ModuleController extends BaseAdminController {

	/** 日志 */
	protected static final Logger LOGGER = LoggerFactory.getLogger(ModuleController.class);

	/** 版块服务 */
	@Autowired
	private ModuleService moduleService;
	/** 部门服务 */
	@Autowired
	private DepartmentService departmentService;

	/**
	 * 显示添加版块页面
	 * 
	 * @author bingo<br>
	 * @date 2017年10月20日 上午10:32:31
	 */
	@RequestMapping("/add")
	public String add() {
		return "/admin/module/add";
	}

	/**
	 * 添加版块
	 * 
	 * @author bingo<br>
	 * @date 2017年10月20日 上午10:34:50
	 */
	@RequestMapping("/save")
	public String save(Module module) {
		moduleService.insertSelective(module);
		return "redirect:list.shtml";
	}

	/**
	 * 显示编辑版块页面
	 * 
	 * @author bingo<br>
	 * @date 2017年10月20日 上午10:39:58
	 */
	@RequestMapping("/edit")
	public String edit(Integer id, Integer parentId, Model model) {
		if (id != null) {
			Module module = moduleService.selectByPrimaryKey(id);
			model.addAttribute("module", module);
		}
		model.addAttribute("parentId", parentId);
		model.addAttribute("departments",departmentService.getDepartmentsSort("-"));
		return "/admin/module/edit";
	}

	/**
	 * 编辑版块
	 * 
	 * @author bingo<br>
	 * @date 2017年11月9日 下午3:09:57
	 */
	@RequestMapping("/update")
	public String update(Module module, Integer[] departmentIds, Model model) {
		if (module.getId() != null) {
			moduleService.updateWithModuleDepartment(module, departmentIds);
		} else {
			moduleService.insertWithModuleDepartment(module, departmentIds);
		}
		return list(module.getParentId(), new Page(), model);
	}

	/**
	 * 删除版块
	 * 
	 * @author bingo<br>
	 * @date 2017年10月20日 上午10:43:48
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Message delete(Integer[] ids) {
		try {
			moduleService.deleteByPrimaryKeys(ids, Module.class);
		} catch (Exception e) {
			LOGGER.error("删除失败!", e);
			return Message.error("删除失败!该条数据被其他地方所引用!");
		}
		return Message.info("删除成功!");
	}

	/**
	 * 同级版块列表
	 * 
	 * @author bingo<br>
	 * @date 2017年10月20日 上午10:49:05
	 */
	@RequestMapping("/list")
	public String list(Integer parentId, Page page, Model model) {
		PageHelper.startPage(page.getPageNumber(), page.getPageSize());
		List<Module> list = moduleService.getSubList(parentId);
		page.setList(list);
		page.setTotalCount(((com.github.pagehelper.Page<Module>) list).getTotal());
		model.addAttribute("page", page);
		model.addAttribute("parentId", parentId);
		return "/admin/module/list";
	}
	
	/**
	 * AJAX获取同级部门
	 * 
	 * @author bingo<br>
	 * @date 2017年11月15日 上午11:10:25
	 */
	@ResponseBody
	@RequestMapping("/getSubDepartments")
	public Message getSubDepartments(Integer parentId) {
		return Message.info("succeed", departmentService.getSubList(parentId));
	}
	
	/**
	 * 获取所有部门（按层级）
	 * 
	 * @author bingo<br>
	 * @date 2017年11月15日 下午2:28:59
	 */
	@ResponseBody
	@RequestMapping("/getAllDepartmentsLayer")
	public List<Department> getAllDepartmentsLayer() {
		List<Department> all = departmentService.selectAll();
		List<Department> top = departmentService.getSubList(null);
		for (Department item : top) {
			if (item.getParentId() == null) {
				getSubsDepartments(all, item);
			}
		}
		return top;
	}
	
	/**
	 * 按层级组装部门
	 * 
	 * @author bingo<br>
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
	 * 判断是否有下级
	 * 
	 * @author bingo<br>
	 * @date 2017年11月15日 下午2:55:28
	 */
	public boolean hasSubDepartments(List<Department> all, Integer parentId) {
		for (Department item : all) {
			if (item.getParentId() == parentId) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * AJAX获取同级版块
	 * 
	 * @author bingo<br>
	 * @date 2017年11月15日 上午11:10:25
	 */
	@ResponseBody
	@RequestMapping("/getSubModules")
	public Message getSubModules(Integer parentId) {
		List<Module> modules = new ArrayList<Module>();
		modules = moduleService.getSubList(parentId);
		return Message.info("succeed", modules);
	}
	
	/**
	 * 获取所有版块（按层级）
	 * 
	 * @author bingo<br>
	 * @date 2017年11月15日 下午2:28:59
	 */
	@ResponseBody
	@RequestMapping("/getAllModulesLayer")
	public Message getAllModulesLayer() {
		List<Module> all = moduleService.selectAll();
		List<Module> top = moduleService.getSubList(null);
		for (Module item : top) {
			if (item.getParentId() == null) {
				getSubsModules(all, item);
			}
		}
		return Message.info("succeed", top);
	}
	
	/**
	 * 按层级组装
	 * 
	 * @author bingo<br>
	 * @date 2017年11月15日 下午2:30:00
	 */
	public void getSubsModules(List<Module> all, Module module) {
		List<Module> sub = new ArrayList<Module>();
		for (Module item : all) {
			if (item.getParentId() == module.getId()) {
				sub.add(item);
				if (hasSubModules(all, item.getId())) {
					getSubsModules(all, item);
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
	public boolean hasSubModules(List<Module> all, Integer parentId) {
		for (Module item : all) {
			if (item.getParentId() == parentId) {
				return true;
			}
		}
		return false;
	}

}
