package com.ruiec.web.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.web.support.controller.BaseAdminController;
import com.ruiec.web.common.Message;
import com.ruiec.web.model.SignRule;
import com.ruiec.web.service.SignRuleService;

/**
 * 后台用户连续签到管理控制器
 * 
 * @author 钟国城<br>
 * @date 2017年11月29日 下午2:14:40
 */
@Controller
@RequestMapping("/admin/signRule")
public class SignRuleController extends BaseAdminController {

	@Resource
	private SignRuleService signRuleService;

	/***
	 * 签到规则列表
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年11月29日 下午4:58:56
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping("/list")
	public String list(Page page, Model model, SignRule signRule) {
		PageHelper.startPage(page.getPageNumber(), page.getPageSize());
		List<SignRule> signRuleList = signRuleService.selectByPage(signRule);
		PageInfo pageInfo = new PageInfo(signRuleList);
		page.setTotalCount(pageInfo.getTotal());
		page.setList(signRuleList);
		return "/admin/signRule/list";
	}

	/**
	 * 跳转修改签到规则页面
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年11月29日 下午5:18:23
	 */
	@RequestMapping("/edit")
	public String edit(Integer id, Model model, SignRule signRule) {
		if (id != null) {
			signRule = signRuleService.selectByPrimaryKey(id);
		}
		model.addAttribute("signRule", signRule);
		return "/admin/signRule/edit";
	}

	/**
	 * 修改签到规则
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年11月29日 下午5:01:26
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(SignRule signRule) {
			if (signRule.getId() == null) {
				signRuleService.insertSelective(signRule);
			} else {
				signRuleService.updateByPrimaryKeySelective(signRule);
			}
		return "redirect:list.shtml";
	}

	/**
	 * 删除
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年11月30日 上午9:06:55
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Message delete(Integer[] ids) {
		try {
			signRuleService.deleteByPrimaryKeys(ids, SignRule.class);
		} catch (Exception e) {
			return Message.error("删除失败!该条数据被其他地方所引用!");
		}
		return Message.info("删除成功!");
	}

	/**
	 * 验证是否存在相同连续天数
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年11月30日 上午9:25:07
	 */
	@ResponseBody
	@RequestMapping("isContinuousDays")
	public String isLastDays(HttpServletRequest request, Integer continuousDays) {
		String flag = "1";
		SignRule signRule = signRuleService.selectByContinuousDays(continuousDays);
		if (null != signRule) {
			flag = "0";
		}
		return flag;
	}
}
