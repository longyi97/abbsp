package com.ruiec.web.controller.admin;

import java.awt.image.BufferedImage;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.octo.captcha.service.CaptchaService;
import com.ruiec.framework.web.support.controller.BaseAdminController;
import com.ruiec.web.service.NavigationService;

/**
 * 
 * 公共控制器
 * 
 * @modify bingo<br>
 * @date 2017年10月25日 上午10:43:55
 */
@Controller
@RequestMapping("/admin/common")
public class CommonController extends BaseAdminController {

	@Resource
	private CaptchaService captchaService;
	@Resource
	private NavigationService navigationService;

	/**
	 * 输出图片验证码
	 * 
	 * @modify bingo<br>
	 * @date 2017年12月7日 上午11:11:23
	 */
	@RequestMapping(value = "/captcha", method = RequestMethod.GET)
	public void captcha(String captcha, HttpServletRequest request, HttpServletResponse response) {
		String sessionId = request.getSession(true).getId();
		BufferedImage localBufferedImage = (BufferedImage) captchaService.getChallengeForID(sessionId);
		imageOut(localBufferedImage, response);
	}

	/**
	 * 后台主界面
	 * 
	 * @modify bingo<br>
	 * @date 2017年12月7日 上午11:11:46
	 */
	@RequestMapping(value = "/main")
	public String main() {
		return "/admin/common/main";
	}

	/**
	 * 后台菜单
	 * 
	 * @modify bingo<br>
	 * @date 2017年12月7日 上午11:12:00
	 */
	@RequestMapping(value = "/nav")
	public String nav(Model model) {
		model.addAttribute("navs1", navigationService.selectByLevel(1));
		model.addAttribute("navs2", navigationService.selectByLevel(2));
		model.addAttribute("navs3", navigationService.selectByLevel(3));
		return "/admin/common/nav";
	}

	/**
	 * 后台主界面中间
	 * 
	 * @modify bingo<br>
	 * @date 2017年12月7日 上午11:12:29
	 */
	@RequestMapping(value = "/center")
	public String center() {
		return "/admin/common/center";
	}

}
