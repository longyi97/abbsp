package com.ruiec.web.dto;

import java.sql.Timestamp;
import java.util.Date;

import com.ruiec.web.model.ArticleAttachment;

/***
 * 帖子实体
 * 
 * @author 王伟 date:2017年10月24日
 */
public class ArticleDTO {
	/**
	 * 序列化接口
	 */
	private static final long serialVersionUID = 1L;

	/** 主键 */
	private Integer id;

	/** 创建时间 */
	private Date createTime;

	/** 修改时间 */
	private Date modifyTime;

	/** 标题 */
	private String title;

	/** 用户ID */
	private Integer userId;

	/** 版块ID */
	private Integer moduleId;

	/** 主题分类ID */
	private Integer articleCategoryId;

	/** 是否置顶 */
	private Boolean isTop;

	/** 点击数 */
	private Integer hit;

	/** 内容 */
	private String content;

	/** 关联帖子附件表 */
	private ArticleAttachment attachment;

	/** 用户名 */
	private String username;

	/** 板块名称 */
	private String name;

	/** 查询日期 */
	private Timestamp queryDate;

	/** 查询日期 */
	public Timestamp getQueryDate() {
		return queryDate;
	}

	/** 查询日期 */
	public void setQueryDate(Timestamp queryDate) {
		this.queryDate = queryDate;
	}

	/**
	 * 帖子附件表
	 */
	public ArticleAttachment getAttachment() {
		return attachment;
	}

	/**
	 * 帖子附件表
	 */
	public void setAttachment(ArticleAttachment attachment) {
		this.attachment = attachment;
	}

	/** 主键 */
	public Integer getId() {
		return id;
	}

	/** 主键 */
	public void setId(Integer id) {
		this.id = id;
	}

	/** 创建时间 */
	public Date getCreateTime() {
		return createTime;
	}

	/** 创建时间 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/** 修改时间 */
	public Date getModifyTime() {
		return modifyTime;
	}

	/** 修改时间 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	/** 标题 */
	public String getTitle() {
		return title;
	}

	/** 标题 */
	public void setTitle(String title) {
		this.title = title;
	}

	/** 用户ID */
	public Integer getUserId() {
		return userId;
	}

	/** 用户ID */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/** 版块ID */
	public Integer getModuleId() {
		return moduleId;
	}

	/** 版块ID */
	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	/** 主题分类ID */
	public Integer getArticleCategoryId() {
		return articleCategoryId;
	}

	/** 主题分类ID */
	public void setArticleCategoryId(Integer articleCategoryId) {
		this.articleCategoryId = articleCategoryId;
	}

	/** 是否置顶 */
	public Boolean getIsTop() {
		return isTop;
	}

	/** 是否置顶 */
	public void setIsTop(Boolean isTop) {
		this.isTop = isTop;
	}

	/** 点击数 */
	public Integer getHit() {
		return hit;
	}

	/** 点击数 */
	public void setHit(Integer hit) {
		this.hit = hit;
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

	/** 板块名称 */
	public String getName() {
		return name;
	}

	/** 板块名称 */
	public void setName(String name) {
		this.name = name;
	}
}