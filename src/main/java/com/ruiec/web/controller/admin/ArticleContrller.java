package com.ruiec.web.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.ruiec.framework.server.support.query.Page;
import com.ruiec.framework.web.support.controller.BaseAdminController;
import com.ruiec.web.common.Message;
import com.ruiec.web.dto.ArticleDTO;
import com.ruiec.web.model.Article;
import com.ruiec.web.model.Module;
import com.ruiec.web.service.ArticleService;
import com.ruiec.web.service.ConfigService;
import com.ruiec.web.service.ModuleService;
import com.ruiec.web.service.UserPointsService;
import com.ruiec.web.util.RuiecRemoveHTML;

/**
 * 后台帖子控制器
 * 
 * @author Jerry
 * @date 2017年11月15日 下午8:26:20
 */
@Controller
@RequestMapping("/admin/article")
public class ArticleContrller extends BaseAdminController {

	@Resource
	private ArticleService articleService;
	@Autowired
	private ModuleService moduleService;
	@Resource
	private UserPointsService userPointsService;
	@Resource
	private ConfigService configService;

	/**
	 * 分页查询帖子信息
	 * 
	 * @author Jerry
	 * @date 2017年11月15日 下午8:26:20
	 */
	@RequestMapping(value = "/list")
	public String index(Page page, Model model, String startTime, String endTime, Integer parentId,
			ArticleDTO articleDTO, Integer selectWho, String selectContent, String selectModule) {
		PageHelper.startPage(page.getPageNumber(), page.getPageSize());
		articleDTO.setName(selectModule);
		if (selectWho != null) {
			switch (selectWho) {
			case 1:
				articleDTO.setTitle(selectContent);
				break;
			case 2:
				articleDTO.setContent(selectContent);
				break;
			case 4:
				articleDTO.setUsername(selectContent);
				break;
			default:
				break;
			}
		}
		List<Article> article = articleService.selectByArticleAllUser(articleDTO);
		model.addAttribute("selectWho", selectWho);
		model.addAttribute("selectContent", selectContent);
		model.addAttribute("selectModule", selectModule);
		model.addAttribute("articleDTO", articleDTO);
		// 存放帖子图片的路径
		String[] img = new String[article.size()];
		// 存放纯文本帖子内容
		String[] content = new String[article.size()];
		for (int i = 0; i < article.size(); i++) {
			String context = article.get(i).getContent();
			content[i] = article.get(i).getContent();
			// 去除内容中的所有标签
			//content[i] = content[i].replaceAll("</?[a-zA-Z]+[^><]*>", "");
			content[i] = RuiecRemoveHTML.delHTMLTag(content[i] );
			if (context.indexOf("src=\"") != -1) {
				// 将图片路径从context中取出来
				img[i] = context.substring(context.indexOf("src=\"") + 5,
						context.indexOf("\"", context.indexOf("src=\"") + 5));
			}
		}
		model.addAttribute("content", content);
		model.addAttribute("img", img);
		page.setTotalCount(((com.github.pagehelper.Page<Article>) article).getTotal());
		page.setList(article);
		List<Module> list = moduleService.selectAll();
		model.addAttribute("moduleList", list);
		return "/admin/article/list";
	}

	/***
	 * 通过id删除帖子
	 * 
	 * @author Jerry
	 * @date 2017年11月15日 下午8:26:20
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Message delete(Integer[] ids, HttpServletRequest req) {
		try {
			articleService.deleteByPrimaryKeys(ids, Article.class);
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error("删除失败!该条数据被其他地方所引用!");
		}
		return Message.info("删除成功!");
	}

	/**
	 * 初始化增加帖子页面
	 * 
	 * @author Jerry
	 * @date 2017年11月15日 下午8:26:20
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(HttpServletRequest request, Model model) {
		return "/home/article/add";
	}

	/***
	 * 发帖
	 * 
	 * @author 王伟
	 * @date:2017年10月25日20:01:38
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public String save(HttpServletRequest request, Model model, Article article) {
		try {
			article.setArticleCategoryId(1);
			article.setHit(1);
			article.setTitle("哈哈哈哈");
			article.setIsTop(true);
			article.setModuleId(1);
			article.setUserId(1);
			articleService.insertSelective(article);
			return "发帖成功";
		} catch (Exception e) {
			e.printStackTrace();
			return "发帖失败";
		}
	}

	/**
	 * 通过子类板块查询所有的帖子信息
	 * 
	 * @author 王伟 date:2017年10月26日19:54:44
	 */
	@RequestMapping(value = "moduleArticle", method = RequestMethod.GET)
	public String moduleArticle(Model model, Article article, Integer id) {
		// 通过所属板块查询下面有多少 帖子，然后再通过展示名称， 通过id查询出详细信息。
		model.addAttribute("Article", articleService.selectByPrimaryKey(id));
		return "/index/moduleSubclass";
	}

	/**
	 * 更新帖子初始页
	 * 
	 * @author Jerry<br>
	 * @date 2017年11月15日 下午8:26:20
	 */
	@RequestMapping(value = "/edit")
	public String edit(Integer id, Model model) {
		Article article = null;
		if (null != id) {
			article = articleService.selectByPrimaryKey(id);
		}
		model.addAttribute("article", article);
		return "/admin/article/edit";
	}

	/**
	 * 更新帖子信息(重构使用ajax提交)
	 * 
	 * @author Jerry<br>
	 * @date 2017年11月15日 下午8:26:20
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Message update(Article article) {
		try {
			if (article.getTitle() == null) {
				return Message.info("1", "null");
			} else if (article.getContent() == null || article.getContent() == "") {
				return Message.info("2", "null");
			} else if (article.getHit() == 0) {
				return Message.info("3", "null");
			}
			articleService.updateByPrimaryKeySelective(article);
			return Message.info("0", "redirect:list.shtml");
		} catch (Exception e) {
			e.printStackTrace();
			return Message.info("5", "null");
		}
	}

	@RequestMapping(value = "/update2", method = RequestMethod.GET)
	public String update2(Article article) {
		try {
			if (article.getId() == null) {
				return "1";
			}
			articleService.updateByPrimaryKeySelective(article);
			return "redirect:list.shtml";
		} catch (Exception e) {
			e.printStackTrace();
			return "2";
		}
	}
	
	/**
	 * 帖子假删除（水贴）
	 * 
	 * @author bingo<br>
	 * @date 2017年12月8日 下午4:49:04
	 */
	@RequestMapping("/fakeDelete")
	public String fakeDelete(Article article) {
		if (article.getId() != null) {
			articleService.fakeDelete(article);
			return LIST;
		}
		return ERROR;
	}
}
