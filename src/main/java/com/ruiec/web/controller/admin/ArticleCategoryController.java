package com.ruiec.web.controller.admin;

import java.util.List;
import javax.annotation.Resource;
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
import com.ruiec.web.model.ArticleCategory;
import com.ruiec.web.service.ArticleCategoryService;
import com.ruiec.web.service.ModuleService;

/**
 * 主题控制器
 * 
 * @date 2017年10月25日 下午8:26:20
 */
@Controller
@RequestMapping("/admin/articleCategory")
public class ArticleCategoryController extends BaseAdminController {

	/** 帖子分类服务 */
	@Resource
	private ArticleCategoryService articleCategoryService;
	/** 版块服务 */
	@Resource
	private ModuleService ModuleService;

	/**
	 * 主题列表
	 * 
	 * @date 2017年10月25日 下午8:26:20
	 */
	@RequestMapping(value = "/list")
	public String list(ArticleCategory articleCategory, Model model, Page page) {
		PageHelper.startPage(page.getPageNumber(), page.getPageSize());
		List<ArticleCategory> articleCategories = articleCategoryService.selectByPage(articleCategory);
		PageInfo<ArticleCategory> pageInfo = new PageInfo<ArticleCategory>(articleCategories);
		page.setTotalCount(pageInfo.getTotal());
		page.setList(articleCategories);
		model.addAttribute("page", page);
		model.addAttribute("moduleId", articleCategory.getModuleId());
		return "/admin/articleCategory/list";
	}

	/**
	 * 添加主题初始页
	 * 
	 * @date 2017年10月25日 下午8:26:20
	 */
	@RequestMapping(value = "/add")
	public String add(Model model) {
		return "/admin/articleCategory/add";
	}

	/**
	 * 添加主题信息
	 * 
	 * @date 2017年10月25日 下午8:26:20
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(ArticleCategory articleCategory, Model model) {
		articleCategoryService.insertSelective(articleCategory);
		return list(articleCategory, model, new Page());
	}

	/**
	 * 更新主题初始页
	 * 
	 * @date 2017年10月25日 下午8:26:20
	 */
	@RequestMapping(value = "/edit")
	public String edit(Integer id, Integer moduleId, Model model) {
		if (null != id) {
			ArticleCategory articleCategory = articleCategoryService.selectByPrimaryKey(id);
			model.addAttribute("articleCategory", articleCategory);
		}
		model.addAttribute("moduleId", moduleId);
		return "/admin/articleCategory/edit";
	}

	/**
	 * 更新主题信息
	 * 
	 * @date 2017年10月25日 下午8:26:20
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(ArticleCategory articleCategory, Model model) {
		if (articleCategory.getId() != null) {
			articleCategoryService.updateByPrimaryKeySelective(articleCategory);
		} else {
			articleCategoryService.insertSelective(articleCategory);
		}
		return list(articleCategory, model, new Page());
	}

	/**
	 * 删除主题信息
	 * 
	 * @date 2017年10月25日 下午8:26:20
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Message delete(Integer[] ids) {
		try {
			articleCategoryService.deleteByPrimaryKeys(ids, ArticleCategory.class);
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error("删除失败!该条数据被其他地方所引用!");
		}
		return Message.info("删除成功!");
	}
}
