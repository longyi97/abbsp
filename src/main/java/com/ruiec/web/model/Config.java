/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */
package com.ruiec.web.model;

/**
 * 系统配置模型
 * 
 * @author bingo<br>
 * @date 2017年11月28日 下午5:57:07
 */
public class Config {
	/** 键 */
	private String key;

	/** 值 */
	private String value;

	/** 键 */
	public String getKey() {
		return key;
	}

	/** 键 */
	public void setKey(String key) {
		this.key = key;
	}

	/** 值 */
	public String getValue() {
		return value;
	}

	/** 值 */
	public void setValue(String value) {
		this.value = value;
	}
}