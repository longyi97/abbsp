package com.ruiec.web.model;

/**
 * 主题模型类
 * 
 * @author Jerry<br>
 * @date 2017年10月25日 下午8:10:24
 */
public class ArticleCategory extends BaseModel {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6204845090842719645L;

	/** 板块 */
	private Module module;

	/** 板块 */
	public Module getModule() {
		return module;
	}

	/** 板块 */
	public void setModule(Module module) {
		this.module = module;
	}

	/** 分类名称 */
	private String name;

	/** 版块ID */
	private Integer moduleId;

	/** 父级ID */
	private Integer parentId;

	/** 排序 */
	private Integer sort;

	/** 分类名称 */
	public String getName() {
		return name;
	}

	/** 分类名称 */
	public void setName(String name) {
		this.name = name;
	}

	/** 版块ID */
	public Integer getModuleId() {
		return moduleId;
	}

	/** 版块ID */
	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	/** 父级ID */
	public Integer getParentId() {
		return parentId;
	}

	/** 父级ID */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	/** 排序 */
	public Integer getSort() {
		return sort;
	}

	/** 排序 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
}