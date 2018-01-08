package com.ruiec.server.core.enumeration;

/**
 * 
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 * 
 * 资金互转状态
 * @author 熊华松<br>
 * Version: 1.0<br>
 * Date: 2016年3月21日
 */
public enum MutualTransferType {
	
	receipts("分红资金转可用资金"),
	expenditure("可用资金转分红资金");
	
	private String name;

	private MutualTransferType(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
