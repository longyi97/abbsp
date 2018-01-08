package com.ruiec.web.template.jsp.directive;

import java.io.IOException;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.ruiec.web.util.RuiecDateUtils;



/**
 * 用户标签
 * @author 刘立雯
 * Date：2017年08月15日
 */
public class DateTag extends SimpleTagSupport {
	
	/** 开始时间--减数 */
	private Date startDate;
	
	/** 结束时间--被减数 */
	private Date endDate;
	
	/** 单位   0:秒；  1:分；  2:时；  3:天 */
	private int type;
	
	/** 对象的自定义名称 */
	private String var;
	
	@Override
	public void doTag() throws JspException, IOException {
		long day=RuiecDateUtils.getTimeDifference(startDate, endDate, type);
		PageContext pageContext = (PageContext) getJspContext();
	    pageContext.setAttribute(var, day);
	    getJspBody().invoke(null);
	}
	
	/** 开始时间--减数 */
	public Date getStartDate() {
		return startDate;
	}

	/** 开始时间--减数 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/** 结束时间--被减数 */
	public Date getEndDate() {
		return endDate;
	}
	
	/** 结束时间--被减数 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/** 单位   0:秒；  1:分；  2:时；  3:天 */
	public int getType() {
		return type;
	}

	/** 单位   0:秒；  1:分；  2:时；  3:天 */
	public void setType(int type) {
		this.type = type;
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
