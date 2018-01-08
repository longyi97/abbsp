/*
 * 版权所有：深圳源中瑞科技有限公司<br>
 * 网 址：www.ruiec.com<br>
 * 电 话：0755-33581131<br><br>
 */

package com.ruiec.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ruiec.web.common.CommonParam;
import com.ruiec.web.model.Admin;

/**
 * 后台登录拦截器, 此拦截器将可以避免未经登录之间访问登录后才能跳转的页面
 * 
 * @author 钟国城<br>
 *         Version: 1.0<br>
 *         Date: 2017年11月00日
 */
public class AdminLoginInterceptor implements HandlerInterceptor {

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
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Admin adminUser = (Admin) session.getAttribute(CommonParam.SESSION_ADMIN_USER);
		if (adminUser == null) {
			java.io.PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<script>");
			out.println("window.open ('/admin/login/view.shtml','_top')");
			out.println("</script>");
			out.println("</html>");
			// response.sendRedirect("/admin/login/view.shtml");
			return false;
		}
		// 返回true代表继续往下执行
		return true;
	}
}