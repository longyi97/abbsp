<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- 分页样式 -->
<style type="text/css">
	#page{float:left;margin-top:5px;width:100%;}
	#page p{float:left;line-height:30px;font-size:14px;border: 1px solid #ddd;padding:0 10px;color:#000;}
	#page li{float:left;overflow:hidden;}
	#page li a{width: 30px;height: 30px;line-height: 30px;text-align: center;color:#000;display: inline-block;border: 1px solid #ddd;font-size: 14px;border-left:0}
	#page li a:hover{text-decoration:none;background-color:#2A72C5;color:#fff;border-color:#2A72C5}
	#page li a.curr{background-color:#2A72C5;color:#fff;border-color:#2A72C5}
	#page li>span{font-weight: 600;width: 30px;height: 30px;line-height: 30px;text-align: center;color:#000;display: inline-block;border: 1px solid #ddd;font-size: 14px;border-left:0}
</style>
<!-- 分页js -->
<script type="text/javascript" src="/resources/admin/js/page.js"></script>  

<input type="hidden" id="sortProperty" name="sortProperty" value="${page.sortProperty }">
<input type="hidden" id="sortType" name="sortType" value="${page.sortType }">
<input type="hidden" id="pageNumber" name="pageNumber" value="${page.pageNumber }">
<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }">
<input type="hidden" id="totalCount" name="totalCount" value="${page.totalCount }">

 <div id="page" class="fr listfo page1">
 	<p>共${page.totalCount }条记录</p>
    <c:if test="${page ne null }">
	<ul>
		
		<c:if test="${page.pageCount gt 0 and page.pageNumber gt 1}">
			<li><a href="javascript:$.pageTo(${page.pageNumber-1 });" style="font-weight: 600;"> &laquo; </a></li>
		</c:if>
		<c:if test="${page.pageCount gt 0 and page.pageNumber eq 1}">
			<li><span style="color: gray;"> &laquo; </span></li>
		</c:if>
					
		<!-- 总页数小于导航数 -->
		<!-- 总页数大于导航数 -->
		<c:choose>
			<c:when test="${page.pageCount le 5 }">
				<c:forEach var="num" begin="1" step="1" end="${page.pageCount }">
					<c:if test="${num eq page.pageNumber }">
						<li><a href="javascript:void(0)" class="curr">${num }</a></li>
					</c:if>
					<c:if test="${num ne page.pageNumber }">
						<li><a href="javascript:$.pageTo(${num })">${num }</a></li>
					</c:if>
				</c:forEach>				
			</c:when>
			
			<c:otherwise>
				<c:if test="${page.pageNumber > 3 }">
					<li><span>...</span></li>
				</c:if>
				<c:set var="center">
					<c:if test="${page.pageNumber lt 3 }">3</c:if>
					<c:if test="${page.pageNumber gt page.pageCount - 2 }">${page.pageCount - 2 }</c:if>
					<c:if test="${page.pageNumber ge 3 and page.pageNumber le page.pageCount - 2 }">${page.pageNumber }</c:if>
				</c:set>
				<c:forEach var="num" begin="${center-2 }" step="1" end="${center+2 }">
					<c:if test="${num eq page.pageNumber }">
						<li><a href="javascript:void(0)" class="curr">${num }</a></li>
					</c:if>
					<c:if test="${num ne page.pageNumber }">
						<li><a href="javascript:$.pageTo(${num })">${num }</a></li>
					</c:if>
				</c:forEach>
				<c:if test="${page.pageNumber < page.pageCount - 2 }">
					<li><span>...</span></li>
				</c:if>
			</c:otherwise>
		</c:choose>
		
		<c:if test="${page.pageCount gt 0 and page.pageNumber lt  page.pageCount }">
			<li><a href="javascript:$.pageTo(${page.pageNumber+1 });" style="font-weight: 600;"> &raquo; </a></li>
		</c:if>
		<c:if test="${page.pageCount gt 0 and page.pageNumber eq  page.pageCount }">
			<li><span style="color: gray;"> &raquo; </span></li>
		</c:if>
	
	</ul>
    </c:if>
</div>
