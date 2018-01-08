package com.ruiec.web.template.jsp.directive;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import com.ruiec.web.model.User;
import com.ruiec.web.service.UserService;
import com.ruiec.web.util.SpringUtils;
/**
 * 用户标签
 * 
 * @author bingo<br>
 * @date 2017年10月26日 下午1:39:18
 */
public class UserTag  extends SimpleTagSupport{
	
	/** 父级ID */
	private Integer userId;
	/** 存值变量 */
	private String var;
	/**
	 * 获取Userid
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * 获取Userid
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * 存值变量
	 */
	public String getVar() {
		return var;
	}
	/**
	 * 存值变量
	 */
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
		UserService userService = SpringUtils.getBean("userServiceImpl", UserService.class);
		User user = userService.selectByPrimaryKey(userId);
		getJspContext().setAttribute(var, user);
		getJspBody().invoke(null);
	}
}
