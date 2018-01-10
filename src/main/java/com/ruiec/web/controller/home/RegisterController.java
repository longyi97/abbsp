package com.ruiec.web.controller.home;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruiec.web.common.CommonParam;
import com.ruiec.web.common.Message;
import com.ruiec.web.model.User;
import com.ruiec.web.service.UserService;
import com.ruiec.web.util.AdminControllerAspect;

/**
 * 前台注册控制器
 * 
 * @date 2017年10月24日 下午4:53:52
 */
@Controller
@RequestMapping("/home")
public class RegisterController extends AdminControllerAspect {

	@Resource
	private UserService userService;

	/**
	 * 用户注册初始页
	 * 
	 * @date 2017年10月26日 下午8:11:53
	 */
	@RequestMapping("/register")
	public String register() {
		return "/home/register";
	}

	/**
	 * 保存用户注册信息
	 * 
	 * @date 2017年10月24日 下午5:39:23
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Message save(User user, Model model, HttpSession session,
			String password, String password1) {
		if (StringUtils.isBlank(user.getUsername())
				|| StringUtils.isBlank(password)) {
			return Message.error("用户名密码不能为空");
		}
		if (user.getUsername().length() < 2 || user.getUsername().length() > 10) {
			return Message.error("用户名必须为2-10位");
		}
		if (user.getPassword().length() < 6 || user.getPassword().length() > 16) {
			return Message.error("密码必须为6-16位");
		}
		if (password.matches("^\\s|\\s$|^(.)\\1*$")) {
			return Message.error("密码不能是相同的数字、字母或符号并且密码首尾不能是空格");
		}
		if (user.getPassword() == user.getUsername()) {
			return Message.error("用户名和密码不能相同");
		}
		if (!password.equals(password1))
			return Message.error("两次密码输入不一致");
		user.setUsername(user.getUsername());
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		if (userService.insertUser(user) == 0) {
			model.addAttribute("msg", "用户名已存在！");
			return Message.info("用户名已存在", "register.shtml");
		}
		// 存入session
		session.setAttribute(CommonParam.SESSION_USER, user);
		return Message.info("注册成功！", "index.shtml");
	}
}
