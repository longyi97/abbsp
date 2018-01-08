/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */
package com.ruiec.web.controller.home;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.ruiec.web.model.ArticleReply;
import com.ruiec.web.service.ArticleReplyService;
import com.ruiec.web.service.ModuleService;

/**
 * 帖子回复控制器
 * 
 * @author Jerry<br>
 * @date 2017年10月27日 下午2:43:20
 */
@Controller
@RequestMapping("/home/articleReply")
public class ArticleReplayController extends BaseAdminController {

	@Resource
	private ArticleReplyService articleReplyService;
	@Autowired
	private ModuleService moduleService;

	/**
	 * 帖子回复列表
	 * 
	 * @author Jerry<br>
	 * @date 2017年10月27日 下午4:13:40
	 */
	@RequestMapping(value = "/list")
	public String list(ArticleReply articleReply, Model model, Page page) {
		PageHelper.startPage(page.getPageNumber(), page.getPageSize());
		List<ArticleReply> articleReplys = articleReplyService
				.selectByPage(articleReply);
		PageInfo pageInfo = new PageInfo(articleReplys);
		page.setTotalCount(pageInfo.getTotal());
		page.setList(articleReplys);
		model.addAttribute("page", page);
		return "/admin/articleReply/list";
	}

	/**
	 * 添加回复初始页
	 * 
	 * @author Jerry<br>
	 * @date 2017年10月27日 下午4:14:23
	 */
	@RequestMapping(value = "/add")
	public String add(Model model,HttpSession session) {
		// 导航栏一级版块
		session.setAttribute("modules", moduleService.getSubList(null));
		return "/admin/articleReply/add";
	}

	/**
	 * 保存回复信息
	 * 
	 * @author Jerry<br>
	 * @date 2017年10月27日 下午4:58:04
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(ArticleReply articleReply) {
		if (!validate(articleReply))
			return ERROR;
		articleReplyService.insertSelective(articleReply);
		return "redirect:list.shtml";
	}

	/**
	 * 更新回复初始页
	 * 
	 * @author Jerry<br>
	 * @date 2017年10月27日 下午5:32:02
	 */
	@RequestMapping(value = "/edit")
	public String edit(Integer id, Model model,HttpSession session) {
		// 导航栏一级版块
		session.setAttribute("modules", moduleService.getSubList(null));
		ArticleReply articleReply = null;
		if (null != id) {
			articleReply = articleReplyService.selectByPrimaryKey(id);
		}
		model.addAttribute("articleReply", articleReply);
		return "/admin/articleReply/edit";
	}

	/**
	 * 更新回复信息
	 * 
	 * @author Jerry<br>
	 * @date 2017年10月27日 下午5:36:24
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(ArticleReply articleReply,HttpSession session) {
		// 导航栏一级版块
		session.setAttribute("modules", moduleService.getSubList(null));
		if (!validate(articleReply))
			return ERROR;
		articleReplyService.updateByPrimaryKeySelective(articleReply);
		return "redirect:list.shtml";
	}

	/**
	 * 删除回复信息
	 * 
	 * @author Jerry<br>
	 * @date 2017年10月27日 下午5:36:43
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Message delete(Integer[] ids) {
		try {
			articleReplyService.deleteByPrimaryKeys(ids, ArticleReply.class);
		} catch (Exception e) {
			return Message.error("删除失败!该条数据被其他地方所引用!");
		}
		return Message.info("删除成功!");
	}

}
