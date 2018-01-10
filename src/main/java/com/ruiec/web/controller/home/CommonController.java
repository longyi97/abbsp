package com.ruiec.web.controller.home;

import java.awt.image.BufferedImage;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.octo.captcha.service.CaptchaService;
import com.ruiec.framework.web.support.controller.BaseAdminController;
/**
 * 
 * 公共控制器
 * 
 * @author 肖学良<br>
 * @modify bingo<br>
 * @date 2017年10月25日 上午10:43:55
 */
@Controller(value= "homeCommonController")
@RequestMapping("/home/common")
public class CommonController extends BaseAdminController {

	@Resource
	private CaptchaService captchaService;
	
	/**
	 * 输出图片验证码
	 * @author 肖学良<br>
	 * Date: 2015年12月22日
	 */
	@RequestMapping(value = "/captcha", method = RequestMethod.GET)
	public void captcha(String captcha, HttpServletRequest request, HttpServletResponse response) {
		String sessionId = request.getSession(true).getId();
		BufferedImage localBufferedImage = (BufferedImage) captchaService.getChallengeForID(sessionId);
		imageOut(localBufferedImage, response);
	}
	
	/**
	 * 错误页面
	 * 
	 * @author bingo<br>
	 * @date 2017年11月12日 下午5:38:46
	 */
	@RequestMapping("/error")
	public String error() {
		return "/home/error";
	}
}
