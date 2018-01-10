package com.ruiec.web.controller.home;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.octo.captcha.service.CaptchaService;
import com.ruiec.framework.web.support.controller.BaseAdminController;
import com.ruiec.web.common.CommonParam;
import com.ruiec.web.common.Message;
import com.ruiec.web.model.User;
import com.ruiec.web.service.UserService;

/**
 * 前台登录控制器
 * 
 * @date 2017年10月24日 下午2:41:09
 */
@Controller
@RequestMapping("/home")
public class LoginController extends BaseAdminController {
	
	protected static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	@Resource
	private CaptchaService captchaService;
	@Resource
	private UserService userService;

	/**
	 * 跳转登录页面
	 * 
	 * @date 2017年10月24日 下午2:49:19
	 */
	@RequestMapping("/view")
	public String view(String requestUrl, Model model) {
		model.addAttribute("requestUrl", requestUrl);
		return "/home/login";
	}

	/**
	 * 登录
	 * 
	 * @date 2017年10月24日 下午3:04:57
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Message login(String username, String password, String captcha,
			String toUrl, String comingUrl, HttpSession session,
			HttpServletRequest request) {
		try {
			// 如果用户名或密码为空
			if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
				return Message.error("用户名密码不能为空");
			}
			User user = new User();
			// 根据用户名查询用户信息
			user = userService.selectByUsername(username);
			if (user == null) {
				return Message.error("用户名不存在！");
			}
			if (!DigestUtils.md5Hex(password).equals(user.getPassword())) {
				return Message.error("用户名或密码错误！");
			}
			if (!captchaService.validateResponseForID(session.getId(), captcha
					.trim().toUpperCase())) {
				return Message.error("验证码错误！");
			}
			if (user.getIsLocked() == true) {
				return Message.error("账户已锁定，请联系管理员！");
			}
			// 存入session
			session.setAttribute(CommonParam.SESSION_USER, user);
			if (toUrl != null && toUrl.length() > 0) {
				if (comingUrl != null && comingUrl.length() > 0) {
					// 记录进入前页面URL
					return Message.info("登录成功", toUrl
							+ (toUrl.contains("?") ? "&comingUrl="
									: "?comingUrl=") + comingUrl);
				}
				return Message.info("登录成功", toUrl);
			}
			return Message.info("登录成功", "index.shtml");
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error("验证码失效！");
		}
	}

	/**
	 * 退出登录
	 * 
	 * @date 2017年10月25日 上午11:08:52
	 */
	@RequestMapping("/logout")
	public String out(HttpSession session) {
		try {
			// 清除session_user
			session.removeAttribute(CommonParam.SESSION_USER);
			return "redirect:/home/view.shtml";
		} catch (Exception e) {
			LOGGER.error("error", e);
			return ERROR;
		}
	}
}
