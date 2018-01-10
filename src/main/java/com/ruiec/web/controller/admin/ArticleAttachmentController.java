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
import com.ruiec.web.model.ArticleAttachment;
import com.ruiec.web.service.ArticleAttachmentService;

/**
 * 帖子附件控制器
 * 
 * @author bingo<br>
 * @date 2017年12月8日 下午6:50:41
 */
@Controller
@RequestMapping("/admin/articleAttachment")
public class ArticleAttachmentController extends BaseAdminController {
	
	@Resource
	private ArticleAttachmentService articleAttachmentService;
	
	/**
	 * 列表页面
	 * 
	 * @author bingo<br>
	 * @date 2017年12月8日 下午7:01:25
	 */
	@RequestMapping("/list")
	public String list(Page page, ArticleAttachment articleAttachment, Model model) {
		PageHelper.startPage(page.getPageNumber(), page.getPageSize());
		List<ArticleAttachment> list = articleAttachmentService.selectByArticleAttachment(articleAttachment);
		page.setList(list);
		page.setTotalCount(new PageInfo<ArticleAttachment>(list).getTotal());
		model.addAttribute("page", page);
		return "/admin/articleAttachment/list";
	}

}
