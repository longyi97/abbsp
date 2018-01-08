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
import com.ruiec.web.model.UserSignDetail;
import com.ruiec.web.service.UserSignDetailService;

/**
 * 后台签到明细管理控制器
 * 
 * @author zhongguocheng<br>
 * @date 2017年11月28日 下午5:43:50
 */
@Controller
@RequestMapping("/admin/userSignDetail")
public class UserSignDetailController extends BaseAdminController {

	@Resource
	private UserSignDetailService userSignDetailService;

	/**
	 * 签到明细列表
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年11月28日 下午5:44:01
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/list")
	public String list(Page page, Model model, UserSignDetail userSignDetail, Integer selectWho, Integer selectSort) {
		PageHelper.startPage(page.getPageNumber(), page.getPageSize());
		if (selectWho != null && selectSort != null) {
			userSignDetail.setConditionsSort(selectSort + selectWho);
		}
		List<UserSignDetail> userSignDetailList = userSignDetailService.selectByUserSignDetail(userSignDetail);
		PageInfo pageInfo = new PageInfo(userSignDetailList);
		page.setTotalCount(pageInfo.getTotal());
		page.setList(userSignDetailList);
		model.addAttribute("selectWho", selectWho);
		model.addAttribute("selectSort", selectSort);
		model.addAttribute("userSignDetail", userSignDetail);
		return "/admin/userSignDetail/list";
	}
}
