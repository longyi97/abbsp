package com.ruiec.web.controller.admin;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.ruiec.web.dto.ArticleReplyDTO;
import com.ruiec.web.model.Article;
import com.ruiec.web.model.ArticleReply;
import com.ruiec.web.service.ArticleReplyService;
import com.ruiec.web.service.ArticleService;
import com.ruiec.web.service.UserService;
import com.ruiec.web.util.RuiecRemoveHTML;

/**
 * 后台帖子控制器
 * @author 王伟
 * date:2017年11月15日09:49:37
 */
@Controller
@RequestMapping("/admin/articleReplay")
public class AdminArticleReplayController extends BaseAdminController{
	
	@Autowired
	private ArticleReplyService articleReplyService;
	@Resource
	private UserService userService;
	@Resource
	private ArticleService articleService;
	/**
	 * 后台帖子回复列表
	 * 
	 * @author zhongguocheng<br>
	 * @date 2017年11月17日 下午2:14:30
	 */
	@RequestMapping(value="/list")
	public String list( Page page,ArticleReplyDTO articleReplyDTO , Model model,Integer selectWho,String selectContent) {
		PageHelper.startPage(page.getPageNumber(), page.getPageSize());
		if (selectWho!=null) {
			switch (selectWho) {
			case 1:
				articleReplyDTO.setTitle(selectContent);
				break;
			case 2:
				articleReplyDTO.setContent(selectContent);
				break;
			case 3:
				articleReplyDTO.setName(selectContent);
				break;
			case 4:
				articleReplyDTO.setUsername(selectContent);
				break;	
			default:
				break;
			}
		}
		List<ArticleReplyDTO> reply = articleReplyService.selectByArticleReplyAll(articleReplyDTO);
		if (reply!=null) {
			for (int i = 0; i < reply.size(); i++) {
				reply.get(i).setContent(RuiecRemoveHTML.delHTMLTag(reply.get(i).getContent()));
			}
		}
		List<Integer> ArticleIdArticleReply = new ArrayList<>();
		for (ArticleReplyDTO ar : reply) {
		    //获取到帖子id 获取到发帖人的id，去判断有不有下级，如果有下级说明存在，存在判断有子类，
		    ArticleIdArticleReply.add(articleReplyService.selectArticleIdArticleReplyId(ar.getId()));
		}
		page.setTotalCount(((com.github.pagehelper.Page<ArticleReplyDTO>)reply).getTotal());
		page.setList(reply);
		model.addAttribute("selectWho",selectWho);
		model.addAttribute("selectContent",selectContent);
		model.addAttribute("ArticleIdArticleReply", ArticleIdArticleReply);
		model.addAttribute("page",page);
		return "/admin/articleReplay/list";
	}
	/**
	 * 删除回复评论
	 * @author 王伟<br>
	 * @date 2017年11月15日 下午2:31:01
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Message delete(Integer[] ids) {
		try {
			articleReplyService.deleteByPrimaryKeys(ids, ArticleReply.class);
		} catch (Exception e) {
			e.printStackTrace();
			return Message.error("删除失败!该条数据被其他地方所引用!");
		}
		return Message.info("删除成功!");
	}
	
	/**
	 * 编辑评论回复初始页面
	 * @author 王伟
	 * @date:2017年11月15日10:54:58
	 */
	@RequestMapping(value = "/edit")
	public String edit(Integer id, Model model){
		ArticleReply articleReply = null;
		Article article =null;
		if (null != id) {
			articleReply = articleReplyService.selectByPrimaryKey(id);
			article = articleService.selectByPrimaryKey(articleReply.getArticleId());
			
		}
		model.addAttribute("articleReply", articleReply);
		model.addAttribute("article", article);
		return "/admin/articleReplay/edit";
	}
	
	
	/**
	 * 编辑评论回复
	 * @author 王伟
	 * date:2017年11月15日10:56:18
	 */

	@RequestMapping(value = "/update",method=RequestMethod.POST)
	@ResponseBody
	public Message update(ArticleReply articleReply) {
		try {
			if(articleReply.getContent()==null ||articleReply.getContent()==""){
				return Message.info("1", "null");
			}
			articleReplyService.updateByPrimaryKeySelective(articleReply);
			return Message.info("0", "redirect:list.shtml");
		} catch (Exception e) {
			e.printStackTrace();
			return Message.info("5", "null");
		}		
	}
	
	/**
	 * 查询子类
	 * @author 王伟
	 * date：2017年11月15日10:02:36
	 */
	@RequestMapping(value="/list2")
	public String list2(HttpServletRequest request, HttpServletResponse response, Page page,ArticleReply articleReply , Model model,String createTime,String username,Integer id) {
			PageHelper.startPage(page.getPageNumber(), page.getPageSize());
			//获取到回复的所有数据
			List<ArticleReply> reply = articleReplyService.selectArticleidArticleReply(id);
			PageInfo pageInfo = new PageInfo(reply);
			page.setTotalCount(pageInfo.getTotal());
			//获取到回复一条数据的部门信息
			List<String> departmentNamelist = new ArrayList<>();
			//获取到帖子名称
			List<String> articletitl = new ArrayList<>();
			List<String> articletitl3 = new ArrayList<>();
			List<Integer> ArticleIdArticleReply = new ArrayList<>();
			for (ArticleReply articleReply2 : reply) {
				departmentNamelist.add(userService.selectdepartment(articleReply2.getUserId()));
			    articletitl.add(articleService.selectArticleTitl(articleReply2.getArticleId()));
			    //获取到帖子id 获取到发帖人的id，去判断有不有下级，如果有下级说明存在，存在判断有子类，
			    ArticleIdArticleReply.add(articleReplyService.selectArticleIdArticleReplyId(articleReply2.getId()));
			}
			//得到用户的部门属性
			model.addAttribute("departmentNamelist", departmentNamelist);
			//得到帖子标题
			model.addAttribute("articletitl", articletitl);
			//获得是否有子类
			model.addAttribute("articletitl3", articletitl3);
			  //获取到帖子id 获取到发帖人的id，去判断有不有下级，如果有下级说明存在，存在判断有子类，
			model.addAttribute("ArticleIdArticleReply", ArticleIdArticleReply);
			page.setList(reply);
			return "/admin/articleReplay/list";
	}
}
