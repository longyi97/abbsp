package com.ruiec.web.model;

import java.io.Serializable;

/**
 * 导航实体
 * @author 刘立雯
 * Date：2017年07月06日
 */
public class Navigation implements Serializable {

	private static final long serialVersionUID = -2564557031801790316L;

	/** 主键 */
	private Integer id;
	/** 名称 */
    private String name;
    /** 父级导航编号 */
    private Integer parentId;
    /** 层级 */
    private Integer level;
    /** 排序 */
    private Integer sort;
    /** 是否显示 */
    private boolean isShow;
    /** 链接 */
    private String link;
    /** 图标*/
    private String image;
	/** 图标*/
	public String getImage() {
		return image;
	}
	/** 图标*/
	public void setImage(String image) {
		this.image = image;
	}
	
    /** 获取 id */
	public Integer getId() {
		return id;
	}
	/** 设置id */
	public void setId(Integer id) {
		this.id = id;
	}
	/** 名称 */
    public String getName() {
        return name;
    }
    /** 名称 */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /** 父级导航编号 */
    public Integer getParentId() {
        return parentId;
    }
    /** 父级导航编号 */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /** 层级 */
    public Integer getLevel() {
		return level;
	}
    /** 层级 */
	public void setLevel(Integer level) {
		this.level = level;
	}
	
	/** 排序 */
    public Integer getSort() {
        return sort;
    }
    /** 排序 */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /** 是否显示 */
	public boolean getIsShow() {
		return isShow;
	}
    /** 是否显示 */
	public void setIsShow(boolean isShow) {
		this.isShow = isShow;
	}

    /** 链接 */
	public String getLink() {
		return link;
	}
    /** 链接 */
	public void setLink(String link) {
		this.link = link;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Navigation [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", parentId=");
		builder.append(parentId);
		builder.append(", level=");
		builder.append(level);
		builder.append(", sort=");
		builder.append(sort);
		builder.append(", isShow=");
		builder.append(isShow);
		builder.append(", link=");
		builder.append(link);
		builder.append("]");
		return builder.toString();
	}
	
}