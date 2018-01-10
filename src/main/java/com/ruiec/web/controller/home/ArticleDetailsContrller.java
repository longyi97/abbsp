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
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.web.support.controller.BaseAdminController;
import com.ruiec.web.common.CommonParam;
import com.ruiec.web.common.Message;
import com.ruiec.web.model.Article;
import com.ruiec.web.model.ArticleReply;
import com.ruiec.web.model.User;
import com.ruiec.web.model.UserPoints;
import com.ruiec.web.service.ArticleAttachmentService;
import com.ruiec.web.service.ArticleCategoryService;
import com.ruiec.web.service.ArticleReplyService;
import com.ruiec.web.service.ArticleService;
import com.ruiec.web.service.ModuleService;
import com.ruiec.web.service.UserPointsService;
import com.ruiec.web.service.UserService;

/**
 * 帖子详情控制器
 * 
 * @date 2017年12月16日 下午7:56:55
 */
@Controller
@RequestMapping("/home/articleDetails")
public class ArticleDetailsContrller extends BaseAdminController {
	
	protected static final Logger LOGGER = LoggerFactory.getLogger(ArticleDetailsContrller.class);
	
	@Resource
	private ArticleService articleService;
	@Resource
	private ArticleAttachmentService articleAttachmentService;
	@Resource
	private UserService userService;
	@Resource
	private UserPointsService userPointsService;
	@Autowired
	private ArticleReplyService articleReplyService;
	@Autowired
	private ArticleCategoryService articleCategoryService;
	@Autowired
	private ModuleService moduleService;

	/**
	 * 保存回复信息
	 * 
	 * @date 2017年10月30日 下午4:58:04
	 */
	@RequestMapping(value = "/addsave")
	public String addsave(ArticleReply articleReply, HttpServletRequest request) {
		if (StringUtil.isEmpty(articleReply.getContent()))
			return ERROR;
		/* 获取到登录的id */
		articleReply.setUserId(((User) request.getSession().getAttribute(CommonParam.SESSION_USER)).getId());
		articleReplyService.insertSelective(articleReply);
		return "redirect:view.shtml?id=" + articleReply.getArticleId();
	}

	/***
	 * 通过id删除回复信息
	 * 
	 * @date 2017年12月16日 下午7:57:13
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Message delete(Integer id) {
		try {
			articleService.deleteArticleId(id);
		} catch (Exception e) {
			return Message.error("删除失败!该条数据被其他地方所引用!");
		}
		return Message.error("删除成功");
	}

	/**
	 * 删除回复信息，（修改回复信息）
	 * 
	 * @date 2017年10月27日 下午5:36:24
	 */
	@RequestMapping(value = "/updatedelete")
	public String updatedelete(ArticleReply articleReply) {
		if (articleReply.getId() != null) {
			articleReply.setContent("内容违规，已经被管理员屏蔽。");
			articleReplyService.updateByPrimaryKeySelective(articleReply);
			return "redirect:view.shtml?id=" + articleReply.getArticleId();
		} else {
			return "回复失败";
		}
	}

	/**
	 * 跳转到回复初始化页面
	 * 
	 * @date 2017年12月16日 下午7:57:13
	 */
	@RequestMapping(value = "/articleReplyInfo")
	public String ArticleReplyInfo(ArticleReply articleReply, Model model, Integer id, Integer articleId) {
		model.addAttribute("id", id);
		model.addAttribute("articleId", articleId);
		return "/home/article/articleReply";
	}

	/**
	 * 保存回复信息
	 * 
	 * @date 2017年10月30日 下午4:58:04
	 */
	@RequestMapping(value = "/addsave1")
	@ResponseBody
	public String addsave1(ArticleReply articleReply, HttpServletRequest request) {
		try {//返回0判断传入的值是否为空
			String str = articleReply.getContent().trim();
			str = str.replaceAll(" ", "");
			str = str.replaceAll("<\\/?((?!img).)*?\\/?>", "");
			if (str.equals("<p></p>") || str.equals("") || str.equals("<p><br></p>")) {
				return "0";
			}
			if (articleReply.getContent() == null || articleReply.getContent() == "") {
				return "0";
			} else {
				User user = (User) request.getSession().getAttribute(CommonParam.SESSION_USER);
				if (user != null) {
					/** 获取到登录的id ***/
					articleReply.setUserId(user.getId());
					articleReplyService.insertSelective(articleReply);
				} else {
					return "3";
				}
				return "1";
			}
		} catch (Exception e) {
			LOGGER.error("保存回复信息异常", e);
			return "0";//错误提示如果返回0就是错误哦
		}
	}

	/**
	 * 分页后的详情
	 * 
	 * @date 2017年12月14日 下午7:21:03
	 */
	@RequestMapping(value = "/view")
	public String view(Page page, Model model, Integer id,HttpSession session) {
		// 导航栏一级版块
		session.setAttribute("modules", moduleService.getSubList(null));
		try {
			// 如果当前页为第一页，显示帖子内容，否则只显示回复
			// 获取帖子以及发帖用户信息
			Article article = articleService.selectByPrimaryKey(id);
			if (null != article) {
				model.addAttribute("article", article);
				// 通过id查询所有部门
				model.addAttribute("articleUser", userService.selectByPrimaryKey(article.getUserId()));
				// 通过用户查询部门名称
				model.addAttribute("dep", userService.selectdepartment(article.getUserId()));
				// 查询管理员发帖的信息
				model.addAttribute("articleCount", articleService.selectByUserAllArticleCount(article.getUserId()));
				// 通过帖子id获取的头像
				model.addAttribute("userImg", userService.selectByPrimaryKey(article.getUserId()));
				// 通过主题id获取到主题
				model.addAttribute("articleCategory", articleCategoryService.selectByPrimaryKey(article.getArticleCategoryId()));
				// 查询帖主的积分多少
				model.addAttribute("userPoints", userPointsService.selectByUserId(article.getUserId()));

				// 点击增加点击量,如果当前点击量超过九亿就不会执行这个方法
				if(article.getHit()<999999999){
					articleService.updateAddHit(id);
				}
			}

			PageHelper.startPage(page.getPageNumber(), page.getPageSize());
			// 通过帖子，通过帖子id查询回复-----（连用户表查询）
			List<ArticleReply> articleReply = articleReplyService.selecctArticleId(id);
			PageInfo<ArticleReply> pageInfo = new PageInfo<ArticleReply>(articleReply);
			page.setTotalCount(pageInfo.getTotal());
			page.setList(articleReply);
			// 获取所有回复用户
			List<User> userList = new ArrayList<User>();
			// 获取所有回复用户的发帖数量
			List<Integer> articleCountList = new ArrayList<Integer>();
			// 获取所有回复用户的积分
			List<UserPoints> points = new ArrayList<UserPoints>();
			for (ArticleReply articleReply2 : articleReply) {
				userList.add(userService.selectByPrimaryKey(articleReply2.getUserId()));
				articleCountList.add(articleService.selectByUserAllArticleCount(articleReply2.getUserId()));
				points.add(userPointsService.selectByUserId(articleReply2.getUserId()));
			}

			// 获取所有回复用户的部门名称
			List<String> departmentNamelist = new ArrayList<>();
			// 根据用户id来获取部门
			for (int i = 0; i < userList.size(); i++) {
				if (null == userService.selectdepartment(userList.get(i).getId())) {
					departmentNamelist.add(null);
				} else {
					departmentNamelist.add(userService.selectdepartment(userList.get(i).getId()));
				}
			}

			model.addAttribute("userList", userList);
			model.addAttribute("points", points);
			model.addAttribute("departmentNamelist", departmentNamelist);
			model.addAttribute("articleCountList", articleCountList);
			model.addAttribute("articleReplySize", pageInfo.getTotal());
			model.addAttribute("page", page);
			return "/home/article/articleDetails";
		} catch (Exception e) {
			LOGGER.error("获取数据失败",e);
			return "redirect:/home/view.shtml";
		}

	}

}
