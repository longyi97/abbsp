/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */
package com.ruiec.web.controller.admin;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.github.pagehelper.PageHelper;
import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.web.support.controller.BaseAdminController;
import com.ruiec.web.model.UserPointsDetail;
import com.ruiec.web.service.UserPointsDetailService;

/**
 * 用户积分明细控制器
 * @author Jerry<br>
 * @date 2017年11月29日 上午10:17:48
 */
@Controller
@RequestMapping("/admin/userPointsDetail")
public class UserPointsDetailController extends BaseAdminController{
	
	@Resource
	private UserPointsDetailService userPointsDetailService;
	
	/**
	 * 用户积分详情列表
	 * @author Jerry<br>
	 * @date 2017年11月29日 上午10:24:20
	 */
	@RequestMapping(value = "/list")
	public String list(Page page, Model model,UserPointsDetail userPointsDetail){
		PageHelper.startPage(page.getPageNumber(), page.getPageSize());
		List<UserPointsDetail> list = userPointsDetailService.selectByPage(userPointsDetail);
		page.setList(list);
		page.setTotalCount(((com.github.pagehelper.Page<UserPointsDetail>) list).getTotal());
		model.addAttribute("userPointsDetail", userPointsDetail);
		return "/admin/userPointsDetail/list"; 
	}
}
