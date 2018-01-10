package com.ruiec.web.template.jsp.directive;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.ruiec.web.model.ArticleReply;
import com.ruiec.web.service.ArticleReplyService;
import com.ruiec.web.util.SpringUtils;
/**
 * 回复标签
 * 
 * @date 2017年10月26日 下午1:39:18
 */
public class ReplyArticleTag  extends SimpleTagSupport{
	
    /**引用回复ID*/
    private Integer replyArticleId;
	/** 存值变量 */
	private String var;
	/**
	 * 存值变量
	 */
	public String getVar() {
		return var;
	}
	/**
	 *引用回复ID
	 */
	public Integer getReplyArticleId() {
		return replyArticleId;
	}
	/**
	 * 引用回复ID
	 */
	public void setReplyArticleId(Integer replyArticleId) {
		this.replyArticleId = replyArticleId;
	}
	/**
	 * 存值变量
	 */
	public void setVar(String var) {
		this.var = var;
	}
	
	/**
	 * 根据父级ID，返回版块模型
	 * 
	 * @date 2017年10月26日 下午1:41:45
	 */
	
	@Override
	public void doTag() throws JspException, IOException {
		ArticleReplyService articleReplyService = SpringUtils.getBean("articleReplyServiceImpl", ArticleReplyService.class);
		ArticleReply articleReply = articleReplyService.selectByPrimaryKey(replyArticleId);
		getJspContext().setAttribute(var, articleReply);
		getJspBody().invoke(null);
	}
}
