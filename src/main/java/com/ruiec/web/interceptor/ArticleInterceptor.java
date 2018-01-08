package com.ruiec.web.interceptor;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ruiec.web.common.CommonParam;
import com.ruiec.web.dao.ModuleDepartmentMapper;
import com.ruiec.web.model.Article;
import com.ruiec.web.model.Department;
import com.ruiec.web.model.Module;
import com.ruiec.web.model.User;
import com.ruiec.web.service.ArticleService;
import com.ruiec.web.service.ModuleService;
import com.ruiec.web.service.UserService;

/***
 * 进入帖子拦截器，如果是用户所在的板块有这个帖子就进入否则就没有权限。
 * 
 * @author 王伟
 * @date:2017年11月10日15:39:46
 */
public class ArticleInterceptor implements HandlerInterceptor {
	@Autowired
	private UserService userService;
	@Resource
	private ArticleService articleService;
	@Resource
	private ModuleDepartmentMapper moduleDepartmentMapper;

	@Resource
	private ModuleService moduleService;

	protected static final Logger LOGGER = LoggerFactory.getLogger(PrintUrlInterceptor.class);

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 获取到当前登录的用户
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(CommonParam.SESSION_USER);
		// 通过用户获取当前的部门
		Integer selectDepartmentId = userService.selectDepartmentId(user.getId());

		// 在页面上获取到点击的帖子id.
		String article = request.getParameter("id");
		Integer ID = Integer.valueOf(article);
		// 获取到板块
		Article arl = articleService.selectByPrimaryKey(ID);
		if (arl != null) {
			// 通过板块id获取到
			Module module = moduleService.selectByPrimaryKey(arl.getModuleId());
			List<Department> departments = module.getDepartments();
			for (Department department : departments) {
				if (selectDepartmentId == department.getId()) {
					if (arl.getIsDelete() == false) {
						return true;
					} else {
						if (arl.getUserId() == (int) user.getId()) {
							return true;
						} else {
							return false;
						}
					}
				}
			}
			// 总经办放过
			if (selectDepartmentId != null && selectDepartmentId == 1) {
				return true;
			}

			// 记录进入前页面URL
			String comingUrl = request.getParameter("comingUrl");
			comingUrl = comingUrl == null ? request.getHeader("Referer") : comingUrl;
			if (comingUrl == null) {
				comingUrl = "javascript:history.back(-1);";
			} else {
				comingUrl = comingUrl.contains(request.getRequestURI()) ? "javascript:history.back(-1);" : comingUrl;
			}
			request.setAttribute("comingUrl", comingUrl);

			// 权限不足
			session.setAttribute("errorMessag", "抱歉，您没有这个帖子的权限！");
			request.getRequestDispatcher("/home/common/error.shtml").forward(request, response);

			return false;
		}
		return false;
	}
}