package com.ruiec.web.template.jsp.directive;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.ruiec.web.common.DictionaryUtil;
import com.ruiec.web.model.Module;
import com.ruiec.web.service.ModuleService;
import com.ruiec.web.util.SpringUtils;

/**
 * 前台菜单标签
 * @date 2017年12月27日 上午10:07:55
 */
public class HomeMenuTag  extends SimpleTagSupport{
	
private String moduleId;
	
	/** 对象的自定义名称 */
	private String var;
	
	@Override
	public void doTag() throws JspException, IOException {
		if (moduleId!=null && !"".equals(moduleId)) {
			Module module=DictionaryUtil.getMap().get(moduleId.trim());
			if(module==null){
				ModuleService moduleService=(ModuleService) SpringUtils.getBean("moduleServiceImpl");
				module=((HashMap<String,Module>) moduleService).get(Integer.valueOf(moduleId));
			}
			PageContext pageContext = (PageContext) getJspContext();
		    pageContext.setAttribute(var, module);
		}
	    getJspBody().invoke(null);
	}

	public String getmoduleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	/** 对象的自定义名称 */
	public String getVar() {
		return var;
	}
	
	/** 对象的自定义名称 */
	public void setVar(String var) {
		this.var = var;
	}
}
