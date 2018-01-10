
package com.ruiec.server.core.enumeration;

/**
 * 契约状态枚举
 * 
 * @author 熊华松<br>
 * Version 1.0<br>
 * Date: 2016-06-28
 */
public enum ContractStatus {
	
	toConfirm("待确认"),
	confirmed("已确认"),
	rejected("已拒绝"),
	toConfirmModify("待确认修改");
		
	private String name;

	private ContractStatus(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
