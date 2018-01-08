package com.ruiec.web.controller.admin;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ruiec.framework.web.support.controller.BaseAdminController;
import com.ruiec.web.model.Admin;
import com.ruiec.web.service.AdminService;

/**
 * 后台管理员控制器
 * 
 * @author 钟国城<br>
 * @date 2017年10月17日 下午2:11:01
 */
@Controller
@RequestMapping("/admin/adminUser")
public class AdminController extends BaseAdminController {

	@Resource
	private AdminService adminService;

	/**
	 * 更新管理员初始页
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年11月8日 上午9:28:22
	 */
	@RequestMapping(value = "/edit")
	public String edit(Integer id, Model model) {
		model.addAttribute("id", id);
		Admin admin = adminService.selectByPrimaryKey(id);
		model.addAttribute("admin", admin);
		return "/admin/administrator/edit";
	}

	/**
	 * 更新管理员
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年11月8日 上午9:28:02
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Admin admin) {
		if (!validate(admin))
			return ERROR;
		admin.setPassword(DigestUtils.md5Hex(admin.getPassword()));
		adminService.updateByPrimaryKeySelective(admin);
		return "redirect:/admin/common/main.shtml";
	}
}
