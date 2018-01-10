
package com.ruiec.web.model;


/**
 * 系统配置项实体类
 * Version: 1.0<br>
 * Date: 2017年01月07日
 */
public class SysConfig extends BaseModel{

	private static final long serialVersionUID = -333728582005253694L;
	
	private String name;//名称
	private String value;//数值
	private String description;//描述
	
	
	/**
	 * 名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 数值
	 */
//	@Lob
	public String getValue() {
		return value;
	}
	/**
	 * 数值
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * 描述
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * 描述
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
}
