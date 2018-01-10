package com.ruiec.web.dto;

import java.util.Date;

/**
 * 帖子回复DTO
 * 
 * @date 2017年11月8日 上午9:35:00
 */
public class ArticleReplyDTO {

	/** 主键 */
	private Integer id;

	/** 用户ID */
	private Integer userId;

	/** 创建时间 */
	private Date createTime;

	/** 帖子ID */
	private Integer articleId;

	/** 引用回复ID */
	private Integer replyArticleId;

	/** 内容 */
	private String content;

	/** 用户名 */
	private String username;

	/** 标题 */
	private String title;

	/**
	 * 部门名称
	 */
	private String name;

	/** 主键 */
	public Integer getId() {
		return id;
	}

	/** 主键 */
	public void setId(Integer id) {
		this.id = id;
	}

	/** 用户ID */
	public Integer getUserId() {
		return userId;
	}

	/** 用户ID */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/** 创建时间 */
	public Date getCreateTime() {
		return createTime;
	}

	/** 创建时间 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	/** 用户名 */
	public String getUsername() {
		return username;
	}

	/** 用户名 */
	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	/** 标题 */
	public String getTitle() {
		return title;
	}

	/** 标题 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 部门名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 部门名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/** 默认构造器 */
	public ArticleReplyDTO() {
		super();
	}

	/**
	 * 自定义构造器
	 * 
	 * @param userId
	 * @param createTime
	 * @param articleId
	 */
	public ArticleReplyDTO(Integer userId, Date createTime, Integer articleId) {
		super();
		this.userId = userId;
		this.createTime = createTime;
		this.articleId = articleId;
	}

}