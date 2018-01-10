package com.ruiec.web.controller.home;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
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
import com.ruiec.web.common.CommonParam;
import com.ruiec.web.model.Article;
import com.ruiec.web.model.Config;
import com.ruiec.web.model.SignRule;
import com.ruiec.web.model.User;
import com.ruiec.web.model.UserPointsDetail;
import com.ruiec.web.model.UserSign;
import com.ruiec.web.model.UserSignDetail;
import com.ruiec.web.service.ArticleReplyService;
import com.ruiec.web.service.ArticleService;
import com.ruiec.web.service.ConfigService;
import com.ruiec.web.service.ModuleService;
import com.ruiec.web.service.SignRuleService;
import com.ruiec.web.service.UserPointsDetailService;
import com.ruiec.web.service.UserPointsService;
import com.ruiec.web.service.UserService;
import com.ruiec.web.service.UserSignDetailService;
import com.ruiec.web.service.UserSignService;
import com.ruiec.web.util.RuiecDateUtils;
import com.ruiec.web.util.RuiecGetImage;
import com.ruiec.web.util.RuiecRemoveHTML;

/**
 * 用户操作页面控制器
 * 
 * @date 2017年10月17日 下午2:11:01
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseAdminController {

	@Autowired
	UserService userService;
	@Autowired
	ArticleService articleService;
	@Autowired
	private ArticleReplyService articleReplyService;
	@Resource
	private SignRuleService signRuleService;
	@Resource
	private UserSignService userSignService;
	@Resource
	private ConfigService configService;
	@Resource
	private UserPointsDetailService userPointsDetailService;
	@Resource
	private UserSignDetailService userSignDetailService;
	@Resource
	private UserPointsService userPointsService;
	@Autowired
	private ModuleService moduleService;

	/**
	 * 跳转个人中心首页
	 * 
	 * @date 2017年10月17日 下午2:11:01
	 */
	@RequestMapping("index")
	public String index(Model model, Page page, User user, HttpSession session) {
		Integer sessionId = ((User) session.getAttribute(CommonParam.SESSION_USER)).getId();
		userInformation(model, sessionId, user, session);
		if (null != sessionId) {
			PageHelper.startPage(page.getPageNumber(), page.getPageSize());
			List<Article> articleList = articleService.selectByUserAllArticle(sessionId);
			if (articleList.size() != 0) {
				// 创建数组存放帖子图片的路径
				String[] img = new String[articleList.size()];
				// 创建数组存放纯文本帖子内容
				String[] content = new String[articleList.size()];
				for (int i = 0; i < articleList.size(); i++) {
					String context = articleList.get(i).getContent();
					content[i] = articleList.get(i).getContent();
					// 去除内容中的所有标签
					// content[i] = content[i].replaceAll("</?[a-zA-Z]+[^><]*>",
					// ""); 第一版去除，暂时保留
					content[i] = RuiecRemoveHTML.delHTMLTag(content[i]);
					if (!RuiecGetImage.getImageSrc(context).equals("")) {
						// 后期优化
						// 将图片路径从context中取出来
						/*
						 * img[i] = context.substring(context.indexOf("src=\"")
						 * + 5, context.indexOf("\"", context.indexOf("src=\"")
						 * + 5));
						 */
						String image = RuiecGetImage.getImageSrc(context);
						if (image.indexOf(",") != -1) {
							image = image.substring(0, image.indexOf(","));
						}
						img[i] = image;
					}
				}
				PageInfo<Article> pageInfo = new PageInfo<Article>(articleList);
				page.setTotalCount(pageInfo.getTotal());
				page.setList(articleList);
				model.addAttribute("page", page);
				model.addAttribute("img", img);
				model.addAttribute("content", content);
				// 帖子的评论数
				List<Integer> ArticleReplyCount = new ArrayList<>();
				for (Article article2 : articleList) {
					ArticleReplyCount.add(articleReplyService.selecctArticleIdCount(article2.getId()));
				}
				model.addAttribute("ArticleReplyCount", ArticleReplyCount);
			} else {
				model.addAttribute("isNull", "0");
			}
		}
		model.addAttribute("controlColor", "1");
		return "/home/user/userIndex";
	}

	/**
	 * 用户基本信息
	 * 
	 * @date 2017年12月6日 下午4:24:43
	 */
	public void userInformation(Model model, Integer sessionId, User user, HttpSession session) {
		if (null != sessionId) {
			user = userService.selectByPrimaryKey(sessionId);
			// 用户积分
			if (userPointsService.selectByUserId(sessionId) != null) {
				model.addAttribute("userPoints", userPointsService.selectByUserId(sessionId).getPoints());
			}
			// 用户部门
			model.addAttribute("departmentName", userService.selectdepartment(sessionId));
			// 用户发帖数
			model.addAttribute("articleCount", articleService.selectByUserAllArticleCount(sessionId));
			// 用户精华数
			model.addAttribute("articleHitCount", articleService.selectByUserAllArticleHitCount(sessionId));
		}
		// 导航栏一级版块
		session.setAttribute("modules", moduleService.getSubList(null));
		model.addAttribute("user", user);
		model.addAttribute("id", sessionId);
	}

	/**
	 * 跳转设置个人资料页面
	 * 
	 * @date 2017年10月17日 下午2:11:01
	 */
	@RequestMapping("userData")
	public String userData(Model model, Page page, HttpSession session, User user) {
		Integer sessionId = ((User) session.getAttribute(CommonParam.SESSION_USER)).getId();
		userInformation(model, sessionId, user, session);
		model.addAttribute("controlColor", "2");
		return "/home/user/userSetting";
	}

	/**
	 * 修改用户资料
	 * 
	 * @date 2017年10月17日 下午2:11:01
	 */
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(User user, HttpServletRequest req, HttpSession session, Model model, Page page) {
		// 导航栏一级版块
		session.setAttribute("modules", moduleService.getSubList(null));
		if (user.getSign() != null) {
			if (user.getSign().length() > 50) {
				return "0";
			}
		} else {
			user.setSign("");
		}
		userService.updateByPrimaryKeySelective(user);
		((User) session.getAttribute(CommonParam.SESSION_USER)).setHeadImage(user.getHeadImage());
		((User) session.getAttribute(CommonParam.SESSION_USER)).setSign(user.getSign());
		((User) session.getAttribute(CommonParam.SESSION_USER)).setBirthday(user.getBirthday());
		((User) session.getAttribute(CommonParam.SESSION_USER)).setSex(user.getSex());
		return "1";
	}

	/**
	 * 跳转修改密码
	 * 
	 * @date 2017年10月17日 下午2:11:01
	 */
	/*
	 * @RequestMapping("updatePwd") public String updatePwd(Model model, Page
	 * page, HttpSession session, User user) { Integer sessionId = ((User)
	 * session.getAttribute(CommonParam.SESSION_USER)).getId();
	 * userInformation(model, sessionId, user); return "/home/user/userSetting";
	 * }
	 */

	/**
	 * 修改密码
	 * 
	 * @date 2017年10月17日 下午2:11:01
	 */
	@ResponseBody
	@RequestMapping("savaPwd")
	public String savaPwd(HttpServletRequest request, String pwd, String newpwd, HttpSession session) {
		User user = (User) session.getAttribute(CommonParam.SESSION_USER);
		// 导航栏一级版块
		session.setAttribute("modules", moduleService.getSubList(null));
		if (user.getPassword().equals(DigestUtils.md5Hex(pwd))) {
			if (user.getUsername().equals(newpwd)) {
				return "3";
			}
			if (newpwd.matches("^\\s|\\s$|^(.)\\1*$")) {
				return "2";
			}
			if (newpwd.equals(pwd)) {
				return "4";
			}
			user.setPassword(DigestUtils.md5Hex(newpwd));
			userService.updateByPrimaryKeySelective(user);
			session.removeAttribute(CommonParam.SESSION_USER);
			return "1";
		} else {
			return "0";
		}
	}
	// 后期优化
	/*
	 * public static boolean isOrderNumeric(String numOrStr) { boolean flag =
	 * true;// 如果全是连续数字返回true boolean isNumeric = true;// 如果全是数字返回true for (int
	 * i = 0; i < numOrStr.length(); i++) { if
	 * (!Character.isDigit(numOrStr.charAt(i))) { isNumeric = false; break; } }
	 * if (isNumeric) {// 如果全是数字则执行是否连续数字判断 for (int i = 0; i <
	 * numOrStr.length(); i++) { if (i > 0) {// 判断如123456 int num =
	 * Integer.parseInt(numOrStr.charAt(i) + ""); int num_ =
	 * Integer.parseInt(numOrStr.charAt(i - 1) + "") + 1; if (num != num_) {
	 * flag = false; break; } } } } else { flag = false; } return flag; }
	 * 
	 * //保留
	 *//**
		 * 不能是连续的数字--递减（如：987654、876543）
		 * 
		 * @param numOrStr
		 * @return 连续数字返回true
		 *//*
		 * public static boolean isOrderNumeric_(String numOrStr) { boolean flag
		 * = true;// 如果全是连续数字返回true boolean isNumeric = true;// 如果全是数字返回true for
		 * (int i = 0; i < numOrStr.length(); i++) { if
		 * (!Character.isDigit(numOrStr.charAt(i))) { isNumeric = false; break;
		 * } } if (isNumeric) {// 如果全是数字则执行是否连续数字判断 for (int i = 0; i <
		 * numOrStr.length(); i++) { if (i > 0) {// 判断如654321 int num =
		 * Integer.parseInt(numOrStr.charAt(i) + ""); int num_ =
		 * Integer.parseInt(numOrStr.charAt(i - 1) + "") - 1; if (num != num_) {
		 * flag = false; break; } } } } else { flag = false; } return flag; }
		 */

	/**
	 * 跳转积分列表页面
	 * 
	 * @date 2017年10月17日 下午2:11:01
	 */
	@RequestMapping("signList")
	public String signList(Model model, HttpSession session, Page page, User user) {
		user = (User) session.getAttribute(CommonParam.SESSION_USER);
		userInformation(model, user.getId(), user, session);
		PageHelper.startPage(page.getPageNumber(), page.getPageSize());
		List<UserPointsDetail> userPointsDetail = userPointsDetailService.selectByUserId(user.getId());
		if (userPointsDetail.size() == 0) {
			model.addAttribute("nolist", "0");
		}
		PageInfo<UserPointsDetail> pageInfo = new PageInfo<UserPointsDetail>(userPointsDetail);
		page.setTotalCount(pageInfo.getTotal());
		page.setList(userPointsDetail);
		// 控制点击的颜色
		model.addAttribute("controlColor", "3");
		// 每次签到得到的正常积分
		Float oneSign = Float.valueOf(configService.selectByKey("points_sign").getValue());
		Map<String, String> map = new HashMap<String, String>();
		for (Config config : configService.selectAll()) {
			map.put(config.getKey(), config.getValue());
		}
		model.addAttribute("configs", map);
		model.addAttribute("oneSign", oneSign);
		// 查出所有签到规则
		model.addAttribute("signRule", signRuleService.selectAll());
		return "/home/user/userIntegration";
	}

	/**
	 * 跳转签到页面
	 * 
	 * @date 2017年11月30日 上午10:42:41
	 */
	@RequestMapping("sign")
	public String sign(Model model, HttpSession session) {
		// 导航栏一级版块
		session.setAttribute("modules", moduleService.getSubList(null));
		User user = (User) session.getAttribute(CommonParam.SESSION_USER);

		// 用戶通过签到获取的总积分
		Float aFloat = userPointsDetailService.selectByAllSignPoints(user.getId());
		if (aFloat != null && aFloat.intValue() == aFloat) {
			model.addAttribute("userRulePoints", aFloat.intValue());
		} else {
			model.addAttribute("userRulePoints", aFloat);
		}

		// 用戶签到的总天数
		if (userSignService.selectByUserName(user.getId()) != null) {
			model.addAttribute("userSignCount", userSignService.selectByUserName(user.getId()).getSignCount());
		}

		// 查出所有签到规则
		List<SignRule> list = signRuleService.selectAll();
		if (list.size() > 0) {
			// 存放分值的数组
			String rewardPoints[] = new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				// 强转为int
				Integer a = list.get(i).getRewardPoints().intValue();
				if (list.get(i).getRewardPoints() - a == 0) {
					rewardPoints[i] = a.toString();
				} else {
					rewardPoints[i] = list.get(i).getRewardPoints().toString();
				}
			}
			model.addAttribute("rewardPoints", rewardPoints);
		}
		model.addAttribute("signRule", signRuleService.selectAll());
		model.addAttribute("signRuleSize", signRuleService.selectAll().size());

		// 查出所有按总签到次倒序排序
		UserSign uSign = new UserSign();
		uSign.setConditionsSort(7);
		List<UserSign> uSignList = userSignService.selectByUserSign(uSign);
		model.addAttribute("uSign", uSignList);

		// 查出所有按连续签到次数倒序排序
		UserSign sSign = new UserSign();
		sSign.setConditionsSort(8);
		List<UserSign> sSignList = userSignService.selectByUserSign(sSign);
		model.addAttribute("sSign", sSignList);

		// 每次签到得到的正常积分
		Float oneSign = Float.valueOf(configService.selectByKey("points_sign").getValue());
		if (oneSign.intValue() == oneSign) {
			model.addAttribute("oneSign", oneSign.intValue());
		} else {
			model.addAttribute("oneSign", oneSign);
		}
		return "/home/user/sign/sign";
	}

	/**
	 * 完成签到
	 * 
	 * @throws ParseException
	 * @date 2017年11月30日 上午11:18:58
	 */
	@RequestMapping("succeedSign")
	@ResponseBody
	public Map<String, Object> succeedSign(HttpSession session) throws ParseException {
		User user = (User) session.getAttribute(CommonParam.SESSION_USER);
		// 签到是否成功的标识 false：失败，true：成功
		Boolean noSucceed = false;
		if (user.getId() != null) {
			noSucceed = userSignService.isSign(user.getId());
		}
		// 成功签到，返回历史签到
		if (noSucceed) {
			return userSignDate(session);
			// 签到失败，返回失败标识
		} else {
			Map<String, Object> jsonmap = new HashMap<String, Object>();
			jsonmap.put("noSucceed", noSucceed);
			return jsonmap;
		}
	}

	/**
	 * 获取用户的所有签到日期
	 * 
	 * @date 2017年12月5日 上午11:25:32
	 */
	@RequestMapping("userSignDate")
	@ResponseBody
	public Map<String, Object> userSignDate(HttpSession session) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> jsonmap = new HashMap<String, Object>();
		// 获取session
		User user = (User) session.getAttribute(CommonParam.SESSION_USER);
		Date today = new Date();
		// 判断今天是否签到标识，false：未签到，true：签到
		Boolean TodayIsSignIn = false;
		// 判断今天是否已签到
		if (userSignService.selectByUserName(user.getId()) != null) {
			Date lastSignTime = userSignService.selectByUserName(user.getId()).getLastSignTime();
			Integer signMinus = (int) RuiecDateUtils.getTimePositiveDifference(today, lastSignTime, 3);
			if (signMinus == 1) {
				TodayIsSignIn = true;
			}
		}
		// 返回签到标识
		jsonmap.put("TodayIsSignIn", TodayIsSignIn);
		// 判断是否签到成功标识 false：失败，true：成功
		Boolean noSucceed = false;
		if (user.getId() != null) {
			List<UserSignDetail> userSignDetailList = userSignDetailService.selectByUserId(user.getId());
			// 获取历史签到记录
			if (userSignDetailList != null) {
				String signDate[] = new String[userSignDetailList.size()];
				for (int i = 0; i < signDate.length; i++) {
					signDate[i] = RuiecDateUtils.format_dd(userSignDetailList.get(i).getLastSignTime());
				}
				noSucceed = true;
				// 返回历史签到
				jsonmap.put("signDate", signDate);
				jsonmap.put("noSucceed", noSucceed);
			} else {
				jsonmap.put("noSucceed", noSucceed);
			}
		}
		// 进度条
		UserSign userSign = userSignService.selectByUserName(user.getId());
		List<SignRule> signRuleList = signRuleService.selectAll();
		// 初始进度条
		String progress = "0%";
		if (signRuleList != null) {
			if (userSign != null) {
				Float aFloat = ((float) userSign.getSignContinuousCount()
						/ (float) signRuleList.get(signRuleList.size() - 1).getContinuousDays());
				// 若用户连续签到天数大于所定规则最多连续天数，则进度为100%
				if (aFloat > 1) {
					aFloat = (float) 1;
				}
				// 返回当前缺省语言环境的百分比格式
				progress = NumberFormat.getPercentInstance().format(aFloat);
			}
			// 各规则所占百分比
			String[] rules = new String[signRuleList.size()];
			for (int i = 0; i < signRuleList.size(); i++) {
				Float aFloat = ((float) signRuleList.get(i).getContinuousDays()
						/ (float) signRuleList.get(signRuleList.size() - 1).getContinuousDays());
				// 应前端需要，计算出各规则所在比例后减百分4
				aFloat = (float) (aFloat - 0.04);
				rules[i] = NumberFormat.getPercentInstance().format(aFloat);
			}
			for (int i = 0; i < rules.length; i++) {
				Map<String, Object> jsonmap1 = new HashMap<String, Object>();
				jsonmap1.put("left", rules[i]);
				jsonmap1.put("Day", signRuleList.get(i).getContinuousDays());
				jsonmap1.put("Number", signRuleList.get(i).getRewardPoints());
				list.add(jsonmap1);
			}
		}
		jsonmap.put("list", list);
		// 返回进度条
		jsonmap.put("progress", progress);
		return jsonmap;
	}

	/**
	 * 签到后按周签到展示
	 * 
	 * @date 2017年12月19日 下午10:42:41
	 */
	@RequestMapping("/weekDignIn")
	@ResponseBody
	public Map<String, Object> WeekDignIn(HttpSession session, Model model, Integer sessionId) {
		// 导航栏一级版块
		session.setAttribute("modules", moduleService.getSubList(null));
		Map<String, Object> jsonmap = new HashMap<String, Object>();
		// 获取到登录的用户
		User user = (User) session.getAttribute(CommonParam.SESSION_USER);
		userInformation(model, sessionId, user, session);// ?
		// 判断用户是否存在签到记录
		List<Integer> weeks = new ArrayList<Integer>();
		if (userSignService.selectByUserName(user.getId()) != null) {
			// 获取到用户最后的签到时间
			Date lastSignTime = userSignService.selectByUserName(user.getId()).getLastSignTime();
			// 获取到最后一次签到的时间中周一的的日期
			String st = RuiecDateUtils.getWeekDateOne(lastSignTime);
			// 传入st 这个时间，去数据库中判断2017-12-18后面有3条数据
			List<UserSignDetail> userSignDetailList = userSignDetailService.selectByUserIdOne(user.getId(), st);
			for (UserSignDetail ul : userSignDetailList) {
				// 已经获取到周一后面的三条数据
				String str = RuiecDateUtils.getWeekOfDate(ul.getLastSignTime());
				weeks.add(Integer.valueOf(str));
			}
			jsonmap.put("weeks", weeks);
		}
		return jsonmap;
	}

}
