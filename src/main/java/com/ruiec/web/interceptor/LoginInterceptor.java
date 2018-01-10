
package com.ruiec.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ruiec.web.common.CommonParam;
import com.ruiec.web.model.User;

/**
 * 登录拦截器, 此拦截器将可以避免未经登录之间访问登录后才能跳转的页面
 * @author Jerry<br>
 * Version: 1.0<br>
 * Date: 2017年11月00日
 */
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(CommonParam.SESSION_USER);
		if (user == null) {
			// 记录进入前页面URL
			request.setAttribute("comingUrl", request.getHeader("Referer"));
			// 记录url
			String toUrl = request.getRequestURI();
			String queryString = request.getQueryString();
			toUrl = queryString == null ? toUrl : toUrl + "?" + queryString;
			request.setAttribute("toUrl", toUrl);
			request.getRequestDispatcher("/home/view.shtml").forward(request, response);
			return false;
		}
		//返回true代表继续往下执行
		return true;
	}

}