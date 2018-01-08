<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<label><img src="${user.headImage}"  width="100px" height="100px"></label>
<p>${user.username}</p>
<span class="boyu"> <em>性别：<i style="color: #666;"><c:if
				test="${user.sex==true}">男</c:if> <c:if test="${user.sex==false}">女</c:if></i></em>
</span>
<span class="boyu">
	<em>发帖：<i style="color: #da251d;">${articleCount}</i></em>
</span>
<span class="boyu">
	<em>积分：<i style="color: #da251d;">${userPoints==null?0:userPoints}</i></em>
</span>
<span class="boyu"><em>部门：</em><i><c:if
   test="${null==departmentName}">暂无</c:if>
<c:if test="${null!=departmentName}">${departmentName}</c:if></i></span>
<span class="boyu"><em>生日：</em><i><fmt:formatDate
			value="${user.birthday}" pattern="yyyy-MM-dd" /></i></span>
