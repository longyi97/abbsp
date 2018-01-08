package com.ruiec.web.model;

/**
 * 帖子回复模型
 * 
 * @author Jerry<br>
 * @date 2017年10月27日 下午1:57:26
 */
public class ArticleReply extends BaseModel {

	private static final long serialVersionUID = -8821801661108722074L;

	/** 用户ID */
	private Integer userId;

	/** 帖子ID */
	private Integer articleId;

	/** 引用回复ID */
	private Integer replyArticleId;

	/** 内容 */
	private String content;

	/** 关联用户表 */
	private User user;

	/**
	 * 关联用户表
	 */
	public User getUser() {
		return user;
	}

	/**
	 * 关联用户表
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/** 用户ID */
	public Integer getUserId() {
		return userId;
	}

	/** 用户ID */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/** 帖子ID */
	public Integer getArticleId() {
		return articleId;
	}

	/** 帖子ID */
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	/** 引用回复ID */
	public Integer getReplyArticleId() {
		return replyArticleId;
	}

	/** 引用回复ID */
	public void setReplyArticleId(Integer replyArticleId) {
		this.replyArticleId = replyArticleId;
	}

	/** 内容 */
	public String getContent() {
		return content;
	}

	/** 内容 */
	public void setContent(String content) {
		this.content = content;
	}
}