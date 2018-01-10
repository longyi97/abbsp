package com.ruiec.web.controller.home;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ruiec.framework.web.support.controller.BaseAdminController;
import com.ruiec.web.common.CommonParam;
import com.ruiec.web.model.Article;
import com.ruiec.web.model.Module;
import com.ruiec.web.model.User;
import com.ruiec.web.service.ArticleCategoryService;
import com.ruiec.web.service.ArticleService;
import com.ruiec.web.service.ModuleService;
import com.ruiec.web.service.UserService;
import com.ruiec.web.service.UserSignService;
import com.ruiec.web.util.RuiecDateUtils;
import com.ruiec.web.util.RuiecGetImage;

/**
 * 前台首页控制器
 * 
 * @date 2017年10月24日 下午4:37:48
 */
@Controller
@RequestMapping("/home")
public class IndexController extends BaseAdminController {

	/** 日志 */
	protected static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

	@Resource
	private ArticleService articleService;
	/** 版块服务 */
	@Autowired
	private ModuleService moduleService;
	/** 用户服务 */
	@Resource
	private UserService userService;
	/** 主题分类 */
	@Resource
	private ArticleCategoryService articleCategoryService;
	@Resource
	private UserSignService userSignService;

	/**
	 * 跳转首页
	 * 
	 * @modify bingo、zhongguocheng<br>
	 * @date 2017年10月31日 下午4:13:59
	 */
	@RequestMapping(value = "index")
	public String index(Article article, Model model, HttpSession session) {
		User user = (User) session.getAttribute(CommonParam.SESSION_USER);
		// 今日签到数
		Date today = new Date();
		
		// 判断今天是否签到标识，false：未签到，true：签到
		Boolean TodayIsSignIn = false;
		if (user!=null) {
			// 判断今天是否已签到
			if (userSignService.selectByUserName(user.getId()) != null) {
				Date lastSignTime = userSignService.selectByUserName(user.getId()).getLastSignTime();
				Integer signMinus = (int) RuiecDateUtils.getTimePositiveDifference(today, lastSignTime, 3);
				if (signMinus == 1) {
					TodayIsSignIn = true;
				}
		}
		}
		model.addAttribute("TodayIsSignIn",TodayIsSignIn);
		String dateNowStr = RuiecDateUtils.format_yyyy_MM_dd(today);
		today = RuiecDateUtils.parse_yyyy_MM_dd(dateNowStr);
		model.addAttribute("todaySignCount", userSignService.selectTodaySignCount(today));
		// 最新帖子（9条）
		List<Article> articleNew = articleService.selectByTopModule(1, 9, "create_time desc", article.getModuleId());
		model.addAttribute("articleNew", articleNew);

		// 热帖（10条）
		List<Article> articleHot = articleService.selectByTopModule(1, 10, "hit desc, create_time desc", article.getModuleId());
		model.addAttribute("articleHot", articleHot);

		// 用戶签到的总天数
		if (user != null) {
			if (userSignService.selectByUserName(user.getId()) != null) {
				model.addAttribute("userSignCount", userSignService.selectByUserName(user.getId()).getSignCount());
			}
		}

		// 今日帖子数
		model.addAttribute("articleCountToday",
				articleService.countByDate(new Timestamp(new Date().getTime()), article.getModuleId()));

		// 昨日帖子数
		model.addAttribute("articleCountYesterday", articleService
				.countByDate(new Timestamp(DateUtils.addDays(new Date(), -1).getTime()), article.getModuleId()));

		// 帖子数
		model.addAttribute("articleCount", articleService.countByModuleId(article.getModuleId()));

		// 导航栏一级版块
		session.setAttribute("modules", moduleService.getSubList(null));

		// 版块
		List<Module> subModules = new ArrayList<Module>();
		if (article.getModuleId() != null) {
			subModules = moduleService.getSubList(article.getModuleId());
		} else {
			try {
				// 一级版块
				List<Module> modules = moduleService.getSubList(null);
				//默认板块的名字
				model.addAttribute("nolModules",modules.get(0).getName());
				// 第一个版块的子版块
				subModules = moduleService.getSubList(modules.get(0).getId());
				if (subModules.size() <= 0) {
					subModules = modules;
				}
			} catch (Exception e) {
				LOGGER.error("没有版块", e);
			}
		}
		// 版块主题数
		List<Integer> subMudulesArticleCategoryCounts = new ArrayList<Integer>();
		// 版块帖子数
		List<Integer> subMudulesArticleCounts = new ArrayList<Integer>();
		for (int i = 0; i < subModules.size(); i++) {
			subMudulesArticleCounts.add(articleService.countByModuleId(subModules.get(i).getId()));
			subMudulesArticleCategoryCounts.add(articleCategoryService.countByModuleId(subModules.get(i).getId()));
		}
		model.addAttribute("subModules", subModules);
		model.addAttribute("subMudulesArticleCounts", subMudulesArticleCounts);
		model.addAttribute("subMudulesArticleCategoryCounts", subMudulesArticleCategoryCounts);

		// 轮播保留处理方式（后期可优化）
		/*
		 * // 轮播的帖子 List<Article> shufflingList =
		 * articleService.selectShufflingArticle(article.getModuleId()); //
		 * 创建存放帖子图片的路径数组 String[] img = new String[3]; //帖子数组是否满足数量的标识 int j=-1;
		 * for (int i = 0; i < shufflingList.size() && j < 2; i++) { String
		 * context = shufflingList.get(i).getContent(); // 将图片路径从context中取出来 if
		 * (context.indexOf("src=\"") != -1) { img[++j] =
		 * context.substring(context.indexOf("src=\"") + 5,
		 * context.indexOf("\"", context.indexOf("src=\"") + 5)); } } //
		 * 如果该版块有图片的帖子不够3篇，则增加该板块无图的帖子 if (j < 2) { List<Article> articleList =
		 * articleService.selectByTopModule(article.getModuleId());
		 * shufflingList.addAll(articleList); // 去除重复，防止轮播帖子有重复 Set<Article> set
		 * = new HashSet<Article>(); List<Article> newArticleList = new
		 * ArrayList<Article>(); for (Article cd : shufflingList) { if
		 * (set.add(cd)) { newArticleList.add(cd); } } shufflingList.clear();
		 * shufflingList.addAll(newArticleList); } model.addAttribute("img",
		 * img); model.addAttribute("shufflingList", shufflingList);
		 */
		List<String> shufflingImg = new ArrayList<>();
		List<Article> shufflingList = new ArrayList<>();
		if (articleHot.size() > 0) {
			for (int i = 0; i < articleHot.size() && shufflingImg.size() < 4; i++) {
				String context = articleHot.get(i).getContent();
				// 后期优化
				/*
				 * if (context.indexOf("src=\"") != -1) {
				 * shufflingImg.add(context.substring(context.indexOf("src=\"")
				 * + 5, context.indexOf("\"", context.indexOf("src=\"") + 5)));
				 * shufflingList.add(articleHot.get(i)); }
				 */
				if (!RuiecGetImage.getImageSrc(context).equals("")) {
					String img = RuiecGetImage.getImageSrc(context);
					if (img.indexOf(",") != -1) {
						img = img.substring(0, img.indexOf(","));
					}
					shufflingImg.add(img);
					shufflingList.add(articleHot.get(i));
				}
			}
		}
		if (shufflingList.size() < 3) {
			for (int i = 0; i < 3; i++) {
				shufflingImg.add(null);
			}
			shufflingList.addAll(articleHot);
			Set<Article> set = new HashSet<Article>();
			List<Article> newArticleList = new ArrayList<Article>();
			for (Article cd : shufflingList) {
				if (set.add(cd)) {
					newArticleList.add(cd);
				}
			}
			shufflingList.clear();
			shufflingList.addAll(newArticleList);
		}
		model.addAttribute("img", shufflingImg);
		model.addAttribute("shufflingList", shufflingList);
		return "/home/index";
	}

	/**
	 * 版块有子版块? 首页展示子版块: 帖子列表
	 * 
	 * @date 2017年11月10日 下午5:05:07
	 */
	@RequestMapping("/subIndex")
	public String subIndex(Article article, Model model, HttpSession session, User user) {
		// 版块ID不为空
		if (article.getModuleId() != null) {
			List<Module> subModules = moduleService.getSubList(article.getModuleId());
			// 所选版块有子版块，进入首页
			// 所选版块没有子版块，进入帖子列表
			if (subModules == null || subModules.size() <= 0) {
				return "redirect:/home/article/list.shtml?moduleId=" + article.getModuleId();
			}
			model.addAttribute("moduleName", moduleService.selectByPrimaryKey(article.getModuleId()).getName());
		}
		return index(article, model, session);
	}
}
