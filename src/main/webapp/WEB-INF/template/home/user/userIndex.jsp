<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<title>用户中心设置</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1,maximum-scale=1, user-scalable=no">
<link rel="stylesheet" href="/resources/home/css/common.css" />
<link rel="stylesheet" href="/resources/home/css/member.css" />
<link rel="stylesheet" href="/resources/home/css/style.css" />
<link rel="stylesheet" href="/resources/home/iconfont/iconfont.css" />
<link rel="stylesheet" href="/resources/home/css/bootstrap.css" />

<script type="text/javascript" src="/resources/home/js/jquery-1.9.1.min.js"></script>

</head>
<body class="body">
	<!--头部-->
	<%@ include file="../common/index-top.jsp"%>
	<!--头部-->

	<!--中间-->
	<div class="content">
		<%@ include file="../common/index-leftMenu.jsp"%>
		<!--右边-->
		<!--1200内容-->
		<%@ include file="../user/userCenter-head.jsp"%>
			<div class="w1200 tabMain">
				 <div class="tabFirst">
	                <div class="tabHead">
	                    <a href="javascript:void(0);" class="butt curr">我发表的帖子</a>
	                </div>
	                <ul class="posting"> 
	                <c:forEach items="${page.list}" var="Article" varStatus="stop">
		                     <li>
		                     <a href="/home/articleDetails/view.shtml?id=${Article.id}" >
		                        <img src="${img[stop.index]==''||img[stop.index]==null?'/resources/home/images/noImage.jpg':img[stop.index]}" alt="">
		                     </a>
		                        <div class="tieziContent">
		                            <h4>
			                            <a href="/home/articleDetails/view.shtml?id=${Article.id}">${Article.title}</a>
			                             <c:if test="${Article.isTop==true}">
			                            <i>【置顶】</i>
			                            </c:if>
			                             <c:if test="${Article.isGood==true}">
										<i>【精华】</i>
										</c:if>
										 <c:if test="${Article.isRecommend==true}">
										<i>【推荐】</i>
										</c:if>
										 <c:if test="${Article.isHot==true}">
										<i>【火帖】</i>
										</c:if>
		                            </h4>
		                            <p>
		                             	<c:if test="${fn:length(content[stop.index])>90}">
											${fn:substring(content[stop.index],0,90)}...
										</c:if>
										<c:if test="${fn:length(content[stop.index])<91}">
											${content[stop.index]}											
										</c:if>  
		                                <a href="/home/articleDetails/view.shtml?id=${Article.id}">【详情】</a>
		                                <c:if test="${Article.isDelete==true}">
												<i class="yuioe">【抱歉，此贴已被管理员标记为水贴】</i>
										</c:if>
		                            </p>
		                            <span>发表于：<fmt:formatDate value="${Article.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /> 源中瑞内部论坛</span>
		                            <em class="fr"><i class="iconfont">&#xe640;</i>${ArticleReplyCount.get(stop.index)}</em>
		                            <em class="fr"><i class="iconfont">&#xe604;</i>${Article.hit}</em>
		                        </div>
		                    </li>
	                  </c:forEach>
	                <c:if test="${isNull==0}">
					<div class="nolist">
						<img src="/resources/home/images/nullNews.png" alt="">
						<p>暂无记录！</p>
					</div>
					</c:if>
	                </ul>
                    <form action="index.shtml" style="overflow: hidden">
						 <%@ include file="../common/page.jsp"%>	
					</form>	
            	</div>
			</div>
		</div>
		<!--1200内容-->
	</div>
	<!--中间-->
	<script>
	$(document).ready(function(){
		//获取地址栏参数进行判断头部菜单的选中状态
		if( window.location.pathname !="" && window.location.pathname != null && window.location.pathname !=undefined ){
			var addUrl =window.location.pathname;	
		}else{
			var _Url =window.location;
			var __UrlSplit = _Url.split("/user");
			var addUrl = "/user" + __UrlSplit[1]
		};
    		$(".UHfoot li").each(function(){
    			var _href=$(this).find("a").attr("href")
    			if(_href==addUrl){
    				$(".UHfoot li a").removeClass("curr");
    				$(this).find("a").addClass("curr");
    			}
    		}); 
			
		})
	</script>
</body>
</html>
