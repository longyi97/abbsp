<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- 分页样式 -->
<style>
	#page{float:right;margin:20px 0 ;}
	#page li{float:left;overflow:hidden;margin-right:5px;border:0;}
	#page li span{width: 30px;height: 30px;line-height:30px;text-align: center;display: inline-block;font-size: 14px;border:1px solid #ddd;}
	#page ul{overflow:hidden;}
	#page li>a{width: 30px;height: 30px;line-height:30px;text-align: center;background-color:#fff;color:#666;display:inline-block;border:1px solid #ddd;font-size:14px;}
	#page li a.curr{background-color: #da251d;color:#fff;}
	
</style>
<!-- 分页js -->
<script type="text/javascript" src="/resources/home/js/page.js"></script>  

<input type="hidden" id="sortProperty" name="sortProperty" value="${page.sortProperty }">
<input type="hidden" id="sortType" name="sortType" value="${page.sortType }">
<input type="hidden" id="pageNumber" name="pageNumber" value="${page.pageNumber }">
<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }">
<input type="hidden" id="totalCount" name="totalCount" value="${page.totalCount }">

 <div id="page" class="fr listfo page1 paging">
	<c:if test="${page ne null }">
	<ul>
		<c:if test="${page.pageCount gt 5 and page.pageNumber ne 1 }">	
			<li><a href="javascript:$.pageTo(1);"> |< </a></li>
		</c:if>
		<c:if test="${page.pageCount gt 5 and page.pageNumber eq 1 }">	
			<li><a href="javascript:void(0);"> |< </a></li>
		</c:if>
		
		<c:if test="${page.pageCount gt 0 and page.pageNumber gt 1}">
			<li><a href="javascript:$.pageTo(${page.pageNumber-1 });"> < </a></li>
		</c:if>
		<c:if test="${page.pageCount gt 0 and page.pageNumber eq 1}">
			<li><a href="javascript:void(0);"> < </a></li>
		</c:if>
					
		<!-- 总页数小于导航数 -->
		<!-- 总页数大于导航数 -->
		<c:choose>
			<c:when test="${page.pageCount le 5 }">
				<c:forEach var="num" begin="1" step="1" end="${page.pageCount }">
					<c:if test="${num eq page.pageNumber }">
						<li><a href="javascript:void(0);" class="curr">${num }</a></li>
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
						<li><a href="javascript:void(0);" class="curr">${num }</a></li>
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
			<li><a href="javascript:$.pageTo(${page.pageNumber+1 });"> > </a></li>
		</c:if>
		<c:if test="${page.pageCount gt 0 and page.pageNumber eq  page.pageCount }">
			<li><a href="javascript:void(0);"> > </a></li>
		</c:if>
	
		<c:if test="${page.pageCount gt 5 and page.pageNumber ne page.pageCount }">
			<li><a href="javascript:$.pageTo(${page.pageCount });"> >| </a></li>
		</c:if>
		<c:if test="${page.pageCount gt 5 and page.pageNumber eq page.pageCount }">
			<li><a href="javascript:void(0);"> >| </a></li>
		</c:if>
	</ul>
    </c:if>
</div>
