package com.ruiec.web.controller.admin;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.web.support.controller.BaseAdminController;
import com.ruiec.web.model.UserSign;
import com.ruiec.web.service.UserSignService;

/**
 * 后台用户签到管理控制器
 * 
 * @date 2017年10月17日 下午2:11:01
 */
@Controller
@RequestMapping("/admin/userSign")
public class UserSignController extends BaseAdminController {

	@Resource
	private UserSignService userSignService;

	/**
	 * 用户签到列表
	 * 
	 * @date 2017年11月28日 下午5:38:21
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/list")
	public String list(Page page, Model model, UserSign userSign, Integer selectWho, Integer selectSort) {
		PageHelper.startPage(page.getPageNumber(), page.getPageSize());
		//设置排序条件，ConditionsSort=4，按总次数正序，5按总次数倒序，7按连续次数正序，8按连续次数倒序
		if (selectWho != null && selectSort != null) {
			userSign.setConditionsSort(selectSort + selectWho);
		}
		List<UserSign> userSignList = userSignService.selectByUserSign(userSign);
		PageInfo pageInfo = new PageInfo(userSignList);
		page.setTotalCount(pageInfo.getTotal());
		page.setList(userSignList);
		model.addAttribute("selectWho", selectWho);
		model.addAttribute("selectSort", selectSort);
		model.addAttribute("userSign", userSign);
		return "/admin/userSign/list";
	}
}
