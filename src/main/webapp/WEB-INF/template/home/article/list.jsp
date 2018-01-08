<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="/ruiec-tags" prefix="ruiec"%>
<!DOCTYPE html>
<html>
	<head>
		<title>论坛列表</title>
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1,maximum-scale=1, user-scalable=no">
		<link rel="stylesheet" href="/resources/home/css/common.css" />
		<link rel="stylesheet" href="/resources/home/css/member.css" />
		<link rel="stylesheet" href="/resources/home/css/style.css" />
		<link rel="stylesheet" href="/resources/home/iconfont/iconfont.css" />
		<link rel="stylesheet" href="/resources/home/css/bootstrap.css" />
		<link rel="stylesheet" href="/resources/home/css/swiper.css" />
		<script type="text/javascript" src="/resources/home/js/jquery-1.9.1.min.js" ></script>

		<script type="text/javascript" src="/resources/home/js/swiper.js" ></script>
		<script type="text/javascript" src="/resources/home/js/Tlist_cookie.js" ></script>
	</head>
	<body class="body">
		<!--头部-->
		<%@ include file="../common/index-top.jsp" %>
		<!--头部-->
		<!--中间-->
		<div class="tipContent">
			<%@ include file="../common/index-leftMenu.jsp"%>
		</div>
			<!--右边-->
			<div class="content-rigth fl col-md-12">
				<div class="row">
				<form action="list.shtml" id="list_form" method="get">
				 <input type="hidden" name="moduleId" value="${articelSelectDTO.moduleId}">
				 <input type="hidden" id="articleCategoryId" name="articleCategoryId" value="${articelSelectDTO.articleCategoryId}">
				 
					<div class="col-md-12">
						<div class="content-1">
							<div class="active1">
								<div class="choose">
									<!-- 筛选条件 -->
									<a href="javascript:void(0);" id="articleselectItem" class="selectItem curr" >所有帖子</a>
									<a href="javascript:void(0);" class="selectItem" value="isHot">最新火帖 <input type="hidden" name="isHot" value="${articelSelectDTO.isHot}" /> </a>
									<a href="javascript:void(0);" class="selectItem" value="isGood">版本精华 <input type="hidden" name="isGood" value="${articelSelectDTO.isGood}" /> </a>
									<a href="javascript:void(0);" class="selectItem" value="isRecommend">版本推荐 <input type="hidden" name="isRecommend" value="${articelSelectDTO.isRecommend}" /> </a>
									<a href="javascript:void(0);" class="selectItem" value="isTop">版本置顶 <input type="hidden" name="isTop" value="${articelSelectDTO.isTop}" /> </a>
								</div>
								<a href="/home/article/add.shtml" class="fr">我要发帖</a>
								<c:if test="${globalFlag }">
								<input type="hidden" name="globalFlag" value="true"/>
								</c:if>
								<lable class="fr"><input type="text" name="title" placeholder="搜索帖子" value="${articelSelectDTO.title}"/><button id="lbtnSearch" class="button" href="javascript:__doPostBack('lbtnSearch','')" onclick="document.getElementById('list_form').submit();">搜 索</button></lable>
							</div>
							<c:if test="${!globalFlag }">
							<div class="chooseThemes">
								<a href="javascript:void(0);" id="allThemes">全部主题<input type="hidden" name="moduleId" value="${articelSelectDTO.moduleId}"></a>
								<c:forEach items="${articleCategory}" var="articleCategory" varStatus="ix">
								<a href="javascript:void(0);" class="articleCategoryId" value="${articleCategory.id}" >${articleCategory.name}</a>
                              </c:forEach>
							</div>
							</c:if>
							<div class="list-ui">
								<table width="100%">
									<colgroup>
										<col width="10%" />
										<col width="30%" />
										<col width="15%" />
										<col width="15%" />
										<col width="15%" />
										<col width="15%" />
									</colgroup>
									<thead>
									<tr>
										<th>
											<a id="clickReply" class="replyNum">
												<span class="fl">回复量</span>
										         <input type="hidden" name="replyType" value="${articelSelectDTO.replyType}" id="replyType">
												<div class="sortBtn"><i class="arrow_up"></i><i class="arrow_down curr"></i></div>
											</a>
										</th>
										<th style="text-align: left">标题</th>
										<th>发帖人</th>
										<th>主题</th>
										<th>
											<a id="clickNum" class="clickNum">
												<span class="fl">点击量</span>
											 	<input type="hidden" name="hitType" value="${articelSelectDTO.hitType}" id="hitType">
												<div class="sortBtn"><i id="replyType" value="1" class="arrow_up"></i><i id="replyType" value="1" class="arrow_down curr"></i></div>
											</a>
										</th>
										<th>发帖时间</th>
									</tr>
									</thead>
									<tbody>
										<c:forEach items="${page.list}" var="article" varStatus="i">
			               	   	  			<tr>
			               	   	  			<td><i>${ArticleReplyCount.get(i.index)}</i></td>
			               	   	  			
											<td><a href="/home/articleDetails/view.shtml?id=${article.id}">
											<c:if test="${article.isTop==true}"><img src="/resources/home/images/stick.jpg"/></c:if>
											<c:if test="${article.isGood==true}"><img src="/resources/home/images/essence.jpg"/></c:if>
											<c:if test="${article.isRecommend==true}"><img src="/resources/home/images/recommend.jpg"/></c:if>
											<c:if test="${article.isHot==true}"><img src="/resources/home/images/hot.png"/></c:if>
											${article.title}</a></td>		
			               	   	  		    <td> <ruiec:user var="r" userId="${article.userId}">${r.username}</ruiec:user></td> 
			               	   	  			<td>${category.get(i.index).name}</td>
			               	   	  			<td>${article.hit}</td>
			               	   	  			<td><fmt:formatDate value="${article.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				               	   	  		</tr>
			               	   	  		</c:forEach>    
									</tbody>
								</table>
								 <%@ include file="../common/page.jsp" %>
							</div>
						</div>
					</div>
					</form>
				</div>
			</div>
			<!--右边-->
		</div>
		<!--中间-->
	</body>
	<script type="text/javascript">
		 $("#articleselectItem").click(function(){
			 $(".choose a input").val("");
			 clearSelectItems();
			 $("#list_form").submit();
	            $(".choose a").removeClass("selectItem curr");
			 	$(this).addClass("selectItem curr");
		 })
		 
		 $(".choose a").click(function() {
			$(".choose a input").val("");
			clearSelectItems();
			$(this).find("input").val(true);
			$("#list_form").submit();
		})
		 
		 $("#clickReply").click(function() {
			var replyType = $("#replyType").val();
			if(replyType == 1) {
				$("#replyType").val(2);
			} else if(replyType == 2){
				$("#replyType").val(1);
			} else {
				$("#replyType").val(1);
			}
			$("#selectItem").attr("name", "");
			$("#articleCategoryId").val("");
			$("#hitType").val(0);
			$("#list_form").submit();
		})
		 
		 $("#clickNum").click(function() {
			var hitType = $("#hitType").val();
			if(hitType == 4) {
				$("#hitType").val(3);
			} else if(hitType == 3){
				$("#hitType").val(4);
			} else {
				$("#hitType").val(3);
			}
			$("#selectItem").attr("name", "");
			$("#articleCategoryId").val("");
			$("#replyType").val(0);
			$("#list_form").submit();
		})
		
		 var up_down=${articelSelectDTO.replyType};
		 var up_do=${articelSelectDTO.hitType};
		 $(".chooseThemes a").click(function(){
			clearSelectItems();
		    $(".choose input").val("false");
            var articleCategoryId = $(this).attr("value");
            $("#articleCategoryId").val(articleCategoryId);
            $("#list_form").submit();
            $(".chooseThemes a").removeClass("curr");
		 	$(this).addClass("curr");
         })
		 $("#allThemes").click(function(){
			clearSelectItems();
            $("#list_form").submit();
            $(".chooseThemes a").removeClass("curr");
		 	$(this).addClass("curr");
         })
         
         function clearSelectItems() {
			 $("#articleCategoryId").val("");
	         $("#hitType").val(0);
	         $("#replyType").val(0);
		}
	</script>
	
</html>