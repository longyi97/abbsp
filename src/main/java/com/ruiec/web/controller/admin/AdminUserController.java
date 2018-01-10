package com.ruiec.web.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.web.support.controller.BaseAdminController;
import com.ruiec.web.common.Message;
import com.ruiec.web.dto.UserDTO;
import com.ruiec.web.model.DepartmentUser;
import com.ruiec.web.model.User;
import com.ruiec.web.service.DepartmentService;
import com.ruiec.web.service.DepartmentUserService;
import com.ruiec.web.service.UserService;
import com.ruiec.web.util.RuiecStringUtil;

/**
 * 后台用户管理控制器
 * 
 * @date 2017年10月17日 下午2:11:01
 */
@Controller
@RequestMapping("/admin/user")
public class AdminUserController extends BaseAdminController {

	@Resource
	private UserService userService;
	@Resource
	private DepartmentService departmentService;
	@Resource
	private DepartmentUserService departmentUserService;

	/**
	 * 用户列表
	 * 
	 * @date 2017年10月20日 下午2:11:01
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/list")
	public String list(Page page, Model model, UserDTO userDTO) {
		PageHelper.startPage(page.getPageNumber(), page.getPageSize());
		List<User> userList = userService.selectByDepartmentAllUser(userDTO);
		PageInfo pageInfo = new PageInfo(userList);
		page.setTotalCount(pageInfo.getTotal());
		page.setList(userList);
		model.addAttribute("page", page);
		model.addAttribute("user", userDTO);
		List<String> departmentNamelist = new ArrayList<>();
		// 根据用户id来获取部门名字
		for (int i = 0; i < userList.size(); i++) {
			departmentNamelist.add(userService.selectdepartment(userList.get(i).getId()));
		}
		model.addAttribute("departmentNamelist", departmentNamelist);
		model.addAttribute("allDepartment", departmentService.getDepartmentsSort("-"));
		return "/admin/user/list";
	}

	/**
	 * 跳转到新增用户页面
	 * 
	 * @date 2017年10月20日 下午2:11:01
	 */
	@RequestMapping("/add")
	public String add(Model model) {
		model.addAttribute("departments", departmentService.getDepartmentsSort("-"));
		return "/admin/user/add";
	}

	/**
	 * 新增用户
	 * 
	 * @date 2017年10月20日 下午2:11:01
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Map<String, String> save(HttpServletRequest request, HttpServletResponse response, User user, Model model,
			Integer departmentId) {
		Map<String, String> jsonmap = new HashMap<String, String>();
		// 创建返回标识，1为成功，0为失败
		String flag = "1";
		String msg = "添加成功!";
		// 验证用户名长度
		if (user.getUsername().length() < 2 && user.getUsername().length() > 10) {
			flag = "0";
			msg = "用户名小于2位或超过10位！";
		} else if (null != userService.selectByUsername(user.getUsername())) {
			flag = "0";
			msg = "用户名重复！";
		} else if (user.getPassword().length() < 6 || user.getPassword().length() > 16) {
			flag = "0";
			msg = "密码必须6-16位！";
		} else if (user.getEmail() != null && !RuiecStringUtil.isEmail(user.getEmail())) {
			flag = "0";
			msg = "邮箱格式不对！";
		} else if (user.getMobile() != null && !RuiecStringUtil.isMobileNO(user.getMobile())) {
			flag = "0";
			msg = "手机格式不对！";
		} else if (user.getSign() != null && user.getSign().length() > 50) {
			flag = "0";
			msg = "个性签名的长度不能超过50！";
		} else {
			user.setPassword(DigestUtils.md5Hex(user.getPassword()));
			if (null != departmentId) {
				userService.insertUserAndDepartmentUser(user, departmentId);
			}
		}
		jsonmap.put("flag", flag);
		jsonmap.put("msg", msg);
		return jsonmap;
	}

	/**
	 * 验证用户名
	 * 
	 * @date 2017年10月17日 下午2:11:01
	 */
	@ResponseBody
	@RequestMapping("isUserName")
	public String isUserName(HttpServletRequest request, User user) {
		String flag = "1";
		User u = userService.selectByUsername(user.getUsername());
		if (null != u) {
			flag = "0";
		}
		return flag;
	}

	/**
	 * 跳转到管理用户页面
	 * 
	 * @date 2017年10月20日 下午2:31:01
	 */
	@RequestMapping(value = "/edit")
	public String edit(Integer id, Model model) {
		if (null != id) {
			model.addAttribute("id", id);
			model.addAttribute("user", userService.selectByPrimaryKey(id));
		}
		model.addAttribute("departmentID", userService.selectDepartmentId(id));
		model.addAttribute("departmentname", userService.selectdepartment(id));
		model.addAttribute("departments", departmentService.getDepartmentsSort("-"));
		return "/admin/user/edit";
	}

	/**
	 * 管理用户
	 * 
	 * @date 2017年10月20日 下午2:31:01
	 */
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Map<String, String> update(HttpServletRequest request, HttpServletResponse response, User user,
			String oldname, Integer departmentId) {
		Map<String, String> jsonmap = new HashMap<String, String>();
		String flag = "1";
		String msg = "编辑成功!";
		DepartmentUser departmentUser = new DepartmentUser();
		departmentUser.setDepartmentId(departmentId);
		departmentUser.setUserId(user.getId());
		// 如果有邮箱，则校验邮箱格式
		if (user.getEmail() != null && !RuiecStringUtil.isEmail(user.getEmail())) {
			flag = "0";
			msg = "邮箱格式不对！";
		}
		// 如果有手机，则校验手机格式
		else if (user.getMobile() != null && !RuiecStringUtil.isMobileNO(user.getMobile())) {
			flag = "0";
			msg = "手机格式不对！";
		} // 验证用户名长度
		else if (user.getSign() != null && user.getSign().length() > 50) {
			flag = "0";
			msg = "个性签名的长度不能超过50！";
		} // 验证个性签名长度
		else if (user.getUsername().length() < 2 || user.getUsername().length() > 10) {
			flag = "0";
			msg = "用户名小于2位或超过10位！";
			// 验证用户名是否重复
		} else if (!oldname.equals(user.getUsername()) && null != userService.selectByUsername(user.getUsername())) {
			flag = "0";
			msg = "用户名重复！";
			// 判断是否有部门
		} else if (null != userService.selectdepartment(departmentUser.getUserId())) {
			userService.updateUserAndDepartmentUser(user, departmentUser);
		} else {
			userService.updateUserAndInsertDepartmentUser(user, departmentUser);
		}
		jsonmap.put("flag", flag);
		jsonmap.put("msg", msg);
		return jsonmap;
	}

	/**
	 * 跳转修改密码
	 * 
	 * @date 2017年10月17日 下午2:11:01
	 */
	@RequestMapping("updatePwd")
	public String updatePwd(Integer id, Model model) {
		model.addAttribute("id", id);
		return "/admin/user/editUpw";
	}

	/**
	 * 修改密码
	 * 
	 * @date 2017年10月17日 下午2:11:01
	 */
	@ResponseBody
	@RequestMapping("savaPwd")
	public String savaPwd(User user) {
		if (null != user && !(user.getPassword().length() < 6 || user.getPassword().length() > 16)) {
			user.setPassword(DigestUtils.md5Hex(user.getPassword()));
			userService.updateByPrimaryKeySelective(user);
			return "1";
		} else {
			return "0";
		}
	}

	/**
	 * 删除用户
	 * 
	 * @date 2017年10月20日 下午2:31:01
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Message delete(Integer[] ids) {
		try {
			userService.deletesUserAndDepartmentUser(ids);
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error("删除失败!该条数据被其他地方所引用!");
		}
		return Message.info("删除成功!");
	}
}
