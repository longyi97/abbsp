package com.ruiec.web.controller.admin;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.PageHelper;
import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.web.support.controller.BaseAdminController;
import com.ruiec.web.model.UserPoints;
import com.ruiec.web.model.UserPointsDetail;
import com.ruiec.web.service.UserPointsDetailService;
import com.ruiec.web.service.UserPointsService;
import com.ruiec.web.service.UserService;

/**
 * 用户积分管理控制器
 * @date 2017年11月29日 上午10:16:54
 */
@Controller
@RequestMapping("/admin/userPoints")
public class UserPointsController extends BaseAdminController{
	
	@Resource
	private UserPointsService userPointsService;
	@Resource
	private UserPointsDetailService userPointsDetailService;
	@Resource
	private UserService userService;
	
	/**
	 * 用户积分列表
	 * @date 2017年11月29日 上午10:24:20
	 */
	@RequestMapping(value = "/list")
	public String list(Page page, Model model,UserPoints userPoints,Integer selectWho,Integer selectSort){
		if (selectWho!=null&&selectSort!=null) {
			userPoints.setConditionsSort(selectSort+selectWho);	
		}
		PageHelper.startPage(page.getPageNumber(), page.getPageSize());
		List<UserPoints> list = userPointsService.selectByPage(userPoints);
		page.setList(list);
		page.setTotalCount(((com.github.pagehelper.Page<UserPoints>) list).getTotal());
		model.addAttribute("userPoints", userPoints);
		return "/admin/userPoints/list";
	}
	
	/**
	 * 显示积分操作初始页
	 * @date 2017年11月22日 下午5:23:14
	 */
	@RequestMapping("/add")
	public String add() {
		return "/admin/userPoints/add";
	}
	
	/**
	 * 增加积分操作
	 * @date 2017年11月22日 下午5:25:53
	 */
	@RequestMapping("/save")
	public String save(UserPointsDetail userPointsDetail, String username, String flag) {
		if (flag != null && !flag.isEmpty()) {
			// 拼接正负
			String points = flag + userPointsDetail.getChangePoints();
			userPointsDetail.setChangePoints(Float.valueOf(points));
			// 设置用户ID（根据用户名查找用户，进而获取用户ID）
			int userId = userService.selectByUsername(username).getId();
			userPointsDetail.setUserId(userId);
			userPointsService.insertOrUpdateByPoints(userPointsDetail);
			// 记录当前积分
			/*if (userPointsService.selectByUserId(userId)==null) {
				userPointsDetail.setCurrentPoints(Float.valueOf(points));
			}else{
				userPointsDetail.setCurrentPoints(userPointsService.selectByUserId(userId).getPoints()+Float.valueOf(points));
			}
			userPointsDetailService.insertSelective(userPointsDetail);
*/		}
		return "redirect:list.shtml";
	}
	
	/**
	 * 验证是否存在用户名
	 * @date 2017年11月30日 上午9:25:07
	 */
	@ResponseBody
	@RequestMapping("isUserName")
	public String isUsername(HttpServletRequest request, String username) {
		//1为失败状态
		String flag = "1";
		UserPoints userPoints = userPointsService.selectByUsername(username);
		if (null != userPoints) {
			//0为成功状态
			flag = "0";
		}
		return flag;
	}
}
