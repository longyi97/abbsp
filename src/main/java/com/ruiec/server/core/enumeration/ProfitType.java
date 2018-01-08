package com.ruiec.server.core.enumeration;
/**
 * 
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 * 
 * 收益类型
 * @author 肖学良<br>
 * Version: 1.0<br>
 * Date: 2016年3月21日
 */
public enum ProfitType {
	/**
	 * 盈利
	 */
	profit("盈利"),
	/**
	 * 亏损
	 */
	loss("亏损"),
	/**
	 * 持平
	 */
	dogfall("持平");
	
	private String name;

	private ProfitType(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
