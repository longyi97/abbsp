package com.ruiec.web.controller.home;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.web.support.controller.BaseAdminController;
import com.ruiec.web.common.CommonParam;
import com.ruiec.web.common.Message;
import com.ruiec.web.dto.ArticelSelectDTO;
import com.ruiec.web.model.Article;
import com.ruiec.web.model.ArticleCategory;
import com.ruiec.web.model.Module;
import com.ruiec.web.model.User;
import com.ruiec.web.service.ArticleCategoryService;
import com.ruiec.web.service.ArticleReplyService;
import com.ruiec.web.service.ArticleService;
import com.ruiec.web.service.ModuleService;

/**
 * 帖子列表
 * 
 * @date 2017年12月16日 下午7:34:14
 */
@Controller
@RequestMapping("/home/article")
public class ArticleListContrller extends BaseAdminController {
	
	protected static final Logger LOGGER = LoggerFactory.getLogger(ArticleListContrller.class);

	@Resource
	private ArticleService articleService;
	@Autowired
	private ModuleService moduleService;
	@Resource
	private ArticleCategoryService articleCategoryService;
	@Autowired
	private ArticleReplyService articleReplyService;

	/**
	 * 根据版块ID查询 articelSelectDTO（接收参数类）
	 * 
	 * @date 2017年11月1日 下午3:55:16
	 */
	@RequestMapping(value = "/list")
	public String list(ArticelSelectDTO articelSelectDTO, Page page, Model model, Boolean globalFlag,HttpSession session) {
		// 导航栏一级版块
		session.setAttribute("modules", moduleService.getSubList(null));
		PageHelper.startPage(page.getPageNumber(), page.getPageSize());
		List<Article> list = articleService.selectByPageType(articelSelectDTO);
		List<Integer> ArticleReplyCount = new ArrayList<>();
		List<ArticleCategory> category = new ArrayList<>();
		for (Article article2 : list) {
			ArticleReplyCount.add(articleReplyService.selecctArticleIdCount(article2.getId()));
			category.add(articleCategoryService.selecctCategoryId(article2.getArticleCategoryId()));
		}
		page.setTotalCount(((com.github.pagehelper.Page<Article>) list).getTotal());
		// 获取到所有主题
		model.addAttribute("articleCategory", articleCategoryService.selectmoduleId(articelSelectDTO.getModuleId()));
		model.addAttribute("ArticleReplyCount", ArticleReplyCount);
		model.addAttribute("category", category);
		page.setList(list);
		model.addAttribute("page", page);
		model.addAttribute("articelSelectDTO", articelSelectDTO);
		model.addAttribute("globalFlag", globalFlag);
		return "/home/article/list";
	}

	/**
	 * 初始化增加帖子页面
	 * 
	 * @date 2017年12月16日 下午7:34:14
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(HttpServletRequest request, Model model,HttpSession session) {
		// 导航栏一级版块
		session.setAttribute("modules", moduleService.getSubList(null));
		model.addAttribute("modules", moduleService.getSubList(null));
		return "/home/article/articleAdd";
	}

	/**
	 * 发帖
	 * 
	 * @date 2017年12月16日 下午7:34:14
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public Message save(HttpServletRequest request, Model model, Article article, String[] attachments) {
		try {
			// 获取当前登录用户（避免session过时）
			User currentUser = (User) request.getSession().getAttribute(CommonParam.SESSION_USER);
			if (null != currentUser) {
				article.setUserId(currentUser.getId());
				
				// 数据校验
				if (null != article.getContent() || !article.getContent().isEmpty()) {
					if (article.getContent().trim().replaceAll("&nbsp;|\\s|　|<\\/?((?!img).)*?\\/?>", "").isEmpty()) {
						return Message.info("内容不能为空!");
					}
				} else {
					return Message.info("内容不能为空!");
				}
				
				// 是否上传附件
				if (null != attachments && attachments.length > 0) {
					articleService.insertSelective(article, attachments);
				} else {
					articleService.insertSelective(article);
				}
				
				return Message.info("success", "/home/articleDetails/view.shtml?id=" + article.getId());
			}
			return Message.info("发帖出错了，请刷新后重试");
		} catch (Exception e) {
			LOGGER.error("保存帖子异常", e);
			return Message.info("发帖出错了，请刷新后重试");
		}
	}

	/**
	 * 获取子版块
	 * 
	 * @date 2017年11月7日 下午3:35:34
	 */
	@ResponseBody
	@RequestMapping("/getSubModule")
	public Message getSubModule(Integer parentId) {
		List<Module> subModules = new ArrayList<Module>();
		subModules = moduleService.getSubList(parentId);
		if (subModules.size() <= 0) {
			subModules.add(moduleService.selectByPrimaryKey(parentId));
		}
		return Message.info("succeed", subModules);
	}

	/**
	 * 获取主题
	 * 
	 * @date 2017年11月7日 下午3:36:03
	 */
	@ResponseBody
	@RequestMapping("/getArticleCategories")
	public Message getArticleCategories(ArticleCategory articleCategory) {
		List<ArticleCategory> list = new ArrayList<ArticleCategory>();
		if (articleCategory.getModuleId() != null) {
			list = articleCategoryService.selectByPage(articleCategory);
		}
		return Message.info("succeedd", list);
	}

	/**
	 * 获取所有版块（按层级）
	 * 
	 * @date 2017年11月15日 下午2:28:59
	 */
	@ResponseBody
	@RequestMapping("/getAllModulesLayer")
	public List<Module> getAllModulesLayer(HttpSession session) {
		User currentUser = (User) session.getAttribute(CommonParam.SESSION_USER);
		if (currentUser.getId() != null) {
			// 所有拥有权限的版块
			List<Module> all = new ArrayList<Module>();
			// 获取用户->部门->版块（最低层版块）
			List<Module> lowestModules = moduleService.selectByUserDepartmentModule(currentUser.getId());
			// 根据低层版块迭代获取所有父级版块
			for (Module module : lowestModules) {
				getParent(all, module);
			}
			// 添加低层版块
			all.addAll(lowestModules);
			// 所有拥有权限的一级版块
			List<Module> top = new ArrayList<Module>();
			// 获取一级版块
			for (Module module : all) {
				// 去除重复（）
				if (!contains(top, module)) {
					if (module.getParentId() == null) {
						top.add(module);
					}
				}
			}
			// 按层级组装版块
			for (Module item : top) {
				if (item.getParentId() == null) {
					getSubsModules(all, item);
				}
			}
			for (int i = 0; i < top.size(); i++) {
				if (top.get(i).getSubModules().size() == 0) {
					top.get(i).setSubModules(null);
				}
			}
			return top;
		} else {
			return null;
		}
	}

	/**
	 * List contains method
	 * 
	 * @date 2017年11月17日 下午4:48:11
	 */
	public boolean contains(List<Module> list, Module item) {
		if (list == null || list.size() == 0) {
			return false;
		}
		if (item == null || null == item.getId()) {
			return false;
		}
		for (Module module : list) {
			if (module.getId() == item.getId()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 迭代获取所有父级
	 * 
	 * @date 2017年11月16日 下午10:24:17
	 */
	public void getParent(List<Module> contentList, Module module) {
		if (module.getParentId() != null) {
			Module parentModule = moduleService.selectByPrimaryKey(module.getParentId());
			if (parentModule != null) {
				contentList.add(parentModule);
				getParent(contentList, parentModule);
			}
		}
	}

	/**
	 * 按层级组装
	 * 
	 * @date 2017年11月15日 下午2:30:00
	 */
	public void getSubsModules(List<Module> all, Module module) {
		List<Module> sub = new ArrayList<Module>();
		for (Module item : all) {
			if (item.getParentId() == module.getId()) {
				if (!contains(sub, item)) {
					sub.add(item);
				}
				if (hasSubModules(all, item.getId())) {
					getSubsModules(all, item);
				}
			}
		}
		module.setSubModules(sub);
	}

	/**
	 * 判断是否有下级
	 * 
	 * @date 2017年11月15日 下午2:55:28
	 */
	public boolean hasSubModules(List<Module> all, Integer parentId) {
		for (Module item : all) {
			if (item.getParentId() == parentId) {
				return true;
			}
		}
		return false;
	}

}
