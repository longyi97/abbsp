package com.ruiec.web.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruiec.framework.web.support.controller.BaseAdminController;
import com.ruiec.web.common.CommonParam;
import com.ruiec.web.common.Message;
import com.ruiec.web.model.Admin;
import com.ruiec.web.service.AdminService;

/**
 * 后台登录控制器
 * 
 * @author 钟国城<br>
 * @date 2017年11月8日 上午9:29:37
 */
@Controller
@RequestMapping("/admin/login")
public class AdminLoginController extends BaseAdminController {

	@Resource
	private AdminService adminService;

	/**
	 * 登录页面
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年11月8日 上午9:29:53
	 */
	@RequestMapping("/view")
	public String view() {
		return "/admin/login/view";
	}

	/**
	 * 后台登录
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年11月8日 上午9:30:06
	 */
	@RequestMapping(value = "/action", method = RequestMethod.POST)
	@ResponseBody
	public Message action(String username, String password, HttpSession session, HttpServletRequest request) {
		// 任意值为空
		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			return Message.error("登录信息不允许为空");
		}
		// 根据用户名获取管理员对象
		Admin admin = adminService.selectByUsername(username);
		// 用户名找不到
		if (admin == null) {
			return Message.error("用户名或密码错误");
		} else {
			// 密码错误
			if (!admin.getPassword().equals(DigestUtils.md5Hex(password))) {
				return Message.error("用户名或密码错误");
			}
		}
		// 存入session
		session.setAttribute(CommonParam.SESSION_ADMIN_USER, admin);
		return Message.info("登录成功", "/admin/common/main.shtml");
	}

	/**
	 * 退出登录
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年11月8日 上午9:30:26
	 */
	@RequestMapping("/out")
	public String out(HttpSession session) {
		// 清除登录缓存
		session.removeAttribute(CommonParam.SESSION_ADMIN_USER);
		// 跳转登录页面
		return "redirect:view.shtml";
	}
}
