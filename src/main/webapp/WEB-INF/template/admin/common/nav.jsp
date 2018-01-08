<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:forEach items="${navs1 }" var="n1">
	<div class="list-group">
		<h1 title="${n1.name }"></h1>
		<div class="list-wrap">
			<h2>${n1.name }<i></i></h2>
			<ul>
			<c:forEach items="${navs2 }" var="n2">
				<c:if test="${n2.parentId eq n1.id }">
					<li>
					<a href="${empty n2.link ? 'javascript:void(0)' : n2.link }" navid="${n2.id }" target="mainframe"><span>${n2.name }</span></a> 
					<c:forEach items="${navs3 }" var="n3">
						<c:if test="${n3.parentId eq n2.id }">
							<ul>
								<li>
								<a href="${empty n3.link ? 'javascript:void(0)' : n3.link }" navid="${n3.id }" target="mainframe"><span>${n3.name }</span></a>
								</li>
							</ul>
						</c:if>
					</c:forEach>
					</li>
				</c:if>
			</c:forEach>
			</ul>
		</div>
	</div>
</c:forEach>