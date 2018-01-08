package com.ruiec.web.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * 
 * 跳转后台首页控制器
 * 王伟
 * 2017年10月20日09:52:15
 */
@Controller
@RequestMapping("/admin")
public class AdminlndexController {
	
	//后台页面查询
	@RequestMapping("index")
	public String findAllWhere(HttpServletRequest request, Model model) {
		return "redirect:admin/common/main.shtml";
	}
}
