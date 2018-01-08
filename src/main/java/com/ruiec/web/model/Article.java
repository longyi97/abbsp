package com.ruiec.web.model;

import java.util.Date;
/***
 * 帖子实体
 * @author 王伟
 * date:2017年10月24日
 */
public class Article extends BaseModel{
    /**
	 * 序列化接口
	 */
	private static final long serialVersionUID = 1L;

	/**主键*/
    private Integer id;

    /**创建时间*/
    private Date createTime;

    /**修改时间*/
    private Date modifyTime;

    /**标题*/
    private String title;

    /**用户ID*/
    private Integer userId;

    /**版块ID*/
    private Integer moduleId;

    /**主题分类ID*/
    private Integer articleCategoryId;

    /**是否置顶*/
    private Boolean isTop;
    /**是否精华**/
    private Boolean isGood;
    /**是否推荐*/
    private Boolean isRecommend;
    /**是否火帖**/
    private Boolean isHot;
    /**是否删除**/
    private Boolean  isDelete;
    
    /**点击数*/
    private Integer hit;
    
    /** 帖子所获得积分 */
    private Float points;
    
    /** 帖子所获得积分最大值 */
    private Float pointsMax;
    
    /**回复数量**/
    private Integer reply;
    
    /**内容*/
    private String content;
    
    /**关联帖子附件表*/
    private ArticleAttachment attachment;
    
    
    
    /**
	 * 是否删除
	 */
	public Boolean getIsDelete() {
		return isDelete;
	}

	/**
	 *是否删除
	 */
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	/** 帖子所获得积分 */
	public Float getPoints() {
		return points;
	}

    /** 帖子所获得积分 */
	public void setPoints(Float points) {
		this.points = points;
	}
	
    /** 帖子所获得积分最大值 */
	public Float getPoints_max() {
		return pointsMax;
	}

    /** 帖子所获得积分最大值 */
	public void setPoints_max(Float pointsMax) {
		this.pointsMax = pointsMax;
	}

	/**
	 *帖子附件表
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

	/**
	 * 回复数量
	 */
	public Integer getReply() {
		return reply;
	}

	/**
	 * 回复数量
	 */
	public void setReply(Integer reply) {
		this.reply = reply;
	}

	/**
	 *是否精华
	 */
	public Boolean getIsGood() {
		return isGood;
	}
	/**
	 *是否精华
	 */
	public void setIsGood(Boolean isGood) {
		this.isGood = isGood;
	}
	/**
	 * 是否推荐
	 */
	public Boolean getIsRecommend() {
		return isRecommend;
	}
	/**
	 * 是否推荐
	 */
	public void setIsRecommend(Boolean isRecommend) {
		this.isRecommend = isRecommend;
	}
	/**
	 * 是否火帖
	 */
	public Boolean getIsHot() {
		return isHot;
	}
	/**
	 * 是否火帖
	 */
	public void setIsHot(Boolean isHot) {
		this.isHot = isHot;
	}
	/**主键*/
    public Integer getId() {
        return id;
    }

    /**主键*/
    public void setId(Integer id) {
        this.id = id;
    }

    /**创建时间*/
    public Date getCreateTime() {
        return createTime;
    }

    /**创建时间*/
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**修改时间*/
    public Date getModifyTime() {
        return modifyTime;
    }

    /**修改时间*/
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**标题*/
    public String getTitle() {
        return title;
    }

    /**标题*/
    public void setTitle(String title) {
        this.title = title;
    }

    /**用户ID*/
    public Integer getUserId() {
        return userId;
    }

    /**用户ID*/
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**版块ID*/
    public Integer getModuleId() {
        return moduleId;
    }

    /**版块ID*/
    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    /**主题分类ID*/
    public Integer getArticleCategoryId() {
        return articleCategoryId;
    }

    /**主题分类ID*/
    public void setArticleCategoryId(Integer articleCategoryId) {
        this.articleCategoryId = articleCategoryId;
    }

    /**是否置顶*/
    public Boolean getIsTop() {
        return isTop;
    }

    /**是否置顶*/
    public void setIsTop(Boolean isTop) {
        this.isTop = isTop;
    }

    /**点击数*/
    public Integer getHit() {
        return hit;
    }

    /**点击数*/
    public void setHit(Integer hit) {
        this.hit = hit;
    }

    /**内容*/
    public String getContent() {
        return content;
    }

    /**内容*/
    public void setContent(String content) {
        this.content = content;
    }
}