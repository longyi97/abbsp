package com.ruiec.web.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ruiec.web.common.CommonParam;
import com.ruiec.web.model.Department;
import com.ruiec.web.model.Module;
import com.ruiec.web.model.User;
import com.ruiec.web.service.DepartmentUserService;
import com.ruiec.web.service.ModuleService;
import com.ruiec.web.util.SpringUtils;

/**
 * 版块权限拦截器
 * 
 * @date 2017年11月10日 下午9:58:17
 */
public class AuthorityInterceptor implements HandlerInterceptor {

	protected static final Logger LOGGER = LoggerFactory.getLogger(AuthorityInterceptor.class);
	
	private DepartmentUserService departmentUserService;
	private ModuleService moduleService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 获取当前登录用户
		HttpSession session = request.getSession();
		User currentUser = (User) session.getAttribute(CommonParam.SESSION_USER);
		
		// 获取当前登录用户所在部门
		departmentUserService = SpringUtils.getBean("departmentUserServiceImpl", DepartmentUserService.class);
		Integer userDepartmentId = departmentUserService.selectByDepartmentId(currentUser.getId()).getDepartmentId();
		
		// 获取版块
		Integer moduleId = Integer.valueOf(request.getParameter("moduleId"));
		moduleService = SpringUtils.getBean("moduleServiceImpl", ModuleService.class);
		Module module = moduleService.selectByPrimaryKey(moduleId);
		// 获取版块所属部门
		List<Department> departments = module.getDepartments();
		for (Department department : departments) {
			if (department.getId() == userDepartmentId) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		
	}

}
