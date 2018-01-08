/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */
package com.ruiec.web.template.jsp.directive;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.ruiec.web.model.Module;
import com.ruiec.web.service.ModuleService;
import com.ruiec.web.util.SpringUtils;

/**
 * 版块标签
 * 
 * @author bingo<br>
 * @date 2017年10月26日 下午1:39:18
 */
public class ModuleTag extends SimpleTagSupport {

	/** 父级ID */
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
	 * 根据父级ID，返回版块模型
	 * 
	 * @author bingo<br>
	 * @date 2017年10月26日 下午1:41:45
	 */
	@Override
	public void doTag() throws JspException, IOException {
		ModuleService moduleService = SpringUtils.getBean("moduleServiceImpl", ModuleService.class);
		Module module = moduleService.selectByPrimaryKey(parentId);
		if (module != null) {
			// 输出版块名称
			getJspContext().getOut().write(module.getName());
		} else {
			// 输出标签体内容
			if (getJspBody() != null) {
				getJspBody().invoke(null);
			}
		}
	}

}
