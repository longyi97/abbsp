package com.ruiec.web.model;

import java.util.List;

public class Module extends BaseModel {

    /** serialVersionUID */
	private static final long serialVersionUID = -8018160743904704594L;

	/**板块名称*/
    private String name;

    /**板块图标*/
    private String logoImage;

    /**描述*/
    private String description;

    /**父级ID*/
    private Integer parentId;

    /**排序*/
    private Integer sort;
    
    /**子类*/
    private List<Module> subModules;
    
    /** 部门 */
    private List<Department> departments;

	/** 获取 departments */
	public List<Department> getDepartments() {
		return departments;
	}

	/** 设置departments */
	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	/** 子类 */
	public List<Module> getSubModules() {
		return subModules;
	}

	/** 子类 */
	public void setSubModules(List<Module> subModules) {
		this.subModules = subModules;
	}

	/**板块名称*/
    public String getName() {
        return name;
    }

    /**板块名称*/
    public void setName(String name) {
        this.name = name;
    }

    /**板块图标*/
    public String getLogoImage() {
        return logoImage;
    }

    /**板块图标*/
    public void setLogoImage(String logoImage) {
        this.logoImage = logoImage;
    }

    /**描述*/
    public String getDescription() {
        return description;
    }

    /**描述*/
    public void setDescription(String description) {
        this.description = description;
    }

    /**父级ID*/
    public Integer getParentId() {
        return parentId;
    }

    /**父级ID*/
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**排序*/
    public Integer getSort() {
        return sort;
    }

    /**排序*/
    public void setSort(Integer sort) {
        this.sort = sort;
    }
}