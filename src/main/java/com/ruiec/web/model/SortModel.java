
package com.ruiec.web.model;

import java.io.Serializable;

/**
 * 排序基类
 * @author 熊华松<br>
 * Version 1.0<br>
 * Date: 2017年01月05日
 */
public class SortModel extends BaseModel implements Serializable {

	private static final long serialVersionUID = 8530733163219989566L;
	
	/** 排序 */
	private Integer sort;

	/**
	 * 排序
	 * @return
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * 排序
	 * @param sort
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SortModel [sort=");
		builder.append(sort);
		builder.append("]");
		return builder.toString();
	}
	
}