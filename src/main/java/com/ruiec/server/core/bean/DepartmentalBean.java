package com.ruiec.server.core.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 部门Bean
 * Date：2017年08月15日
 */
public class DepartmentalBean implements Serializable {

	private static final long serialVersionUID = -1348547042055071545L;

	/** 部门名称 */
	private String departmentalName="";
	/** 部门成员 */
	private List<MemberBean> memberBeans;
	
	public DepartmentalBean() {
		super();
	}
	
	public DepartmentalBean(String departmentalName,
			List<MemberBean> memberBeans) {
		super();
		this.departmentalName = departmentalName;
		this.memberBeans = memberBeans;
	}
	
	/** 部门名称 */
	public String getDepartmentalName() {
		return departmentalName;
	}
	/** 部门名称 */
	public void setDepartmentalName(String departmentalName) {
		this.departmentalName = departmentalName;
	}
	
	/** 部门成员 */
	public List<MemberBean> getMemberBeans() {
		return memberBeans;
	}
	/** 部门成员 */
	public void setMemberBeans(List<MemberBean> memberBeans) {
		this.memberBeans = memberBeans;
	}

	@Override
	public String toString() {
		return "DepartmentalBean [departmentalName=" + departmentalName
				+ ", memberBeans=" + memberBeans + "]";
	}
	
}
