package com.ruiec.web.template.jsp.directive;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.ruiec.web.model.Department;
import com.ruiec.web.service.DepartmentService;
import com.ruiec.web.util.SpringUtils;

/**
 * 部门标签
 * 
 * @date 2017年10月26日 下午3:11:08
 */
public class DepartmentTag extends SimpleTagSupport {

	/** ID */
	private Integer parentId;
	/** 存值变量 */
	private String var;

	/** 获取 parentId */
	public Integer getParentId() {
		return parentId;
	}

	/** 设置parentId */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	/** 获取 var */
	public String getVar() {
		return var;
	}

	/** 设置var */
	public void setVar(String var) {
		this.var = var;
	}

	/**
	 * 根据父级ID，返回模型
	 * 
	 * @date 2017年10月26日 下午5:19:56
	 */
	@Override
	public void doTag() throws JspException, IOException {
		DepartmentService departmentService = SpringUtils.getBean("departmentServiceImpl", DepartmentService.class);
		Department department = departmentService.selectByPrimaryKey(parentId);
		if (department != null) {
			// 输出版块名称
			getJspContext().getOut().write(department.getName());
		} else {
			// 输出标签体内容
			if (getJspBody() != null) {
				getJspBody().invoke(null);
			}
		}
	}

}
