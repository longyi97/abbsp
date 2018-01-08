package com.ruiec.web.model;

/**
 * 附件模型类
 * 
 * @author Jerry<br>
 * @date 2017年10月30日 下午4:02:00
 */
public class ArticleAttachment extends BaseModel {

	private static final long serialVersionUID = 520553561533623618L;

	/** 帖子ID */
	private Integer articleId;

	/** 附件名称 */
	private String name;

	/** 附件路径 */
	private String url;

	/** 描述 */
	private String description;
	
	 /**标题*/
    private String title;

	/** 帖子ID */
	public Integer getArticleId() {
		return articleId;
	}

	/** 帖子ID */
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	/** 附件名称 */
	public String getName() {
		return name;
	}

	/** 附件名称 */
	public void setName(String name) {
		this.name = name;
	}

	/** 附件路径 */
	public String getUrl() {
		return url;
	}

	/** 附件路径 */
	public void setUrl(String url) {
		this.url = url;
	}

	/** 描述 */
	public String getDescription() {
		return description;
	}

	/** 描述 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	 /**标题*/
    public String getTitle() {
        return title;
    }

    /**标题*/
    public void setTitle(String title) {
        this.title = title;
    }
}