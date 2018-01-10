package com.ruiec.web.dto;
/**
 * 封装(列表页面参数-接收参数)
 * date：2017年12月2日15:07:48
 */
public class ArticelSelectDTO {
    /**版块ID*/
    private Integer moduleId;
    /**回复类型（）**/
    private Integer replyType=0;
    /**通过主题查询**/
    private Integer articleCategoryId;
	/**点击数类型**/
    private Integer hitType=0;
    /**是否置顶*/
    private Boolean isTop;
    /**是否精华**/
    private Boolean isGood;
    /**是否推荐*/
    private Boolean isRecommend;
    /**是否火帖**/
    private Boolean isHot;
    /**帖子标题**/
    private String title; 
    
    /**
	 * 帖子标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 帖子标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 是否置顶
	 */
	public Boolean getIsTop() {
		return isTop;
	}
	/**
	 * 是否置顶
	 */
	public void setIsTop(Boolean isTop) {
		this.isTop = isTop;
	}
	/**
	 * 是否精华
	 */
	public Boolean getIsGood() {
		return isGood;
	}

	/**
	 * 是否精华
	 */
	public void setIsGood(Boolean isGood) {
		this.isGood = isGood;
	}

	/**
	 *是否推荐
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

	/**
	 * 回复传值类型
	 */
	public Integer getReplyType() {
		return replyType;
	}

	/**
	 * 回复传值类型
	 */
	public void setReplyType(Integer replyType) {
		this.replyType = replyType;
	}

	/**
	 * 回复传值类型
	 */
	public Integer getHitType() {
		return hitType;
	}

	/**
	 * 回复传值类型
	 */
	public void setHitType(Integer hitType) {
		this.hitType = hitType;
	}


	/**
	 * 版块ID
	 */
	public Integer getModuleId() {
		return moduleId;
	}
	
	/**
	 * 通过主题
	 */
	public Integer getArticleCategoryId() {
		return articleCategoryId;
	}

	/**
	 *通过主题
	 */
	public void setArticleCategoryId(Integer articleCategoryId) {
		this.articleCategoryId = articleCategoryId;
	}

	/**
	 * *版块ID
	 */
	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}


    
}
