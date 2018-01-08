<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>Ruiec BBS</title>
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1,maximum-scale=1, user-scalable=no">
		<link rel="stylesheet" href="/resources/home/css/common.css" />
		<link rel="stylesheet" href="/resources/home/css/member.css" />
		<link rel="stylesheet" href="/resources/home/css/style.css" />
		<link rel="stylesheet" href="/resources/home/iconfont/iconfont.css" />
		<link rel="stylesheet" href="/resources/home/css/bootstrap.css" />
		<script type="text/javascript" src="/resources/home/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="/resources/home/js/layer.js" ></script>
		<script type="text/javascript" src="/resources/home/js/index.js"></script>
	</head>
	<body class="body">
		<!--头部-->
			<%@ include file="common/index-top.jsp"%>
		<!--头部-->
		<!--中间-->
		<div class="content">
			<!--右边-->
			<%@ include file="common/index-leftMenu.jsp"%>
			<!--右边-->
			<div class="contentRigth">
				<div class="w1200">
					<div class="sectionOne">
						<div class="newPic">
							<h4>最新图片</h4>
							<div class="lunbo">
				                       <ul class="imges" id="imgChange">
				                       <c:forEach var="list" items="${shufflingList}" varStatus="stop">
				                       		<c:if test="${stop.index<3}">
					                        	<li class="show">
					                        	<a href="/home/articleDetails/view.shtml?id=${list.id}" onclick="aclick();"><img src="${img.get(stop.index)==''||img.get(stop.index)==null?'/resources/home/images/noImage.jpg':img.get(stop.index)}" alt="" width="100%" height="100%"></a>
					                        	<p><a href="/home/articleDetails/view.shtml?id=${list.id}" onclick="aclick();">${list.title}</a></p></li>
				                       		</c:if>
				                       </c:forEach>
				                       </ul>
								<ul class="circles" id="circles">
								 	<c:forEach var="list" items="${shufflingList}" varStatus="stop">
				                    <c:if test="${stop.index<3}">
									<li></li>
									</c:if>
				                    </c:forEach>
								</ul>
							</div>
						</div>
						<div class="cardNews">
							<table>
								<thead>
								<tr>
									<th><i>NEW</i><span>最新帖子</span></th>
									<th><i>HOT</i><span>最热帖子</span></th>
								</tr>
								</thead>
								<tbody>
								<tr>
									<td>
										<ul>
											<c:forEach var="item" items="${articleNew }">
												<li>
													<a href="/home/articleDetails/view.shtml?id=${item.id }" onclick="aclick();">
														${item.title.length() le 15 ? item.title : item.title.substring(0, 15).concat('...') }
													</a>
												</li>
									</c:forEach>
										</ul>
									</td>
									<td>
										<ul>
											<c:forEach var="item" items="${articleHot }" begin="1" end="${articleHot.size() }" >
												<li>
													<a href="/home/articleDetails/view.shtml?id=${item.id }" onclick="aclick();">
														${item.title.length() le 15 ? item.title : item.title.substring(0, 15).concat('...') }
													</a>
												</li>
											</c:forEach>
										</ul>
									</td>
								</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div class="sectionTwo">
						<ul>
							<li>
								<img src="/resources/home/images/brow.png" alt="">
								<span>今日帖数：<em>${articleCountToday }</em></span>
							</li>
							<li>
								<img src="/resources/home/images/brow.png" alt="">
								<span>昨日帖数：<em>${articleCountYesterday }</em></span>
							</li>
							<li>
								<img src="/resources/home/images/brow.png" alt="">
								<span>帖子数量：<em>${articleCount }</em></span>
							</li>
							<li>
								<a href="/home/article/add.shtml" onclick="aclick();">发表新帖</a>
							</li>
						</ul>
					</div>
					<div class="sectionThree">
						<div class="threeLeft">
							<div class="mouldOne">
								<div class="mouldTitle">
									<span>${moduleName==null?nolModules:moduleName}</span>
									<img src="/resources/home/images/no.gif" alt="" class="expant">
								</div>
								<ul>
									<c:forEach var="item" items="${subModules }" varStatus="i">
										<li>
											<a href="/home/subIndex.shtml?moduleId=${item.id }">
												<div class="mouldPic">
													<img src="${item.logoImage }" alt="">
												</div>
												<div class="moduldMain">
													<h4><a href="/home/subIndex.shtml?moduleId=${item.id }" onclick="aclick();">${item.name }<i></i></a></h4>
													<span>主题：${subMudulesArticleCategoryCounts.get(i.index) }</span>
													<span>帖子：${subMudulesArticleCounts.get(i.index) }</span>
												</div>
											</a>
										</li>
									</c:forEach>
								</ul>
							</div> 
						</div>
						<div class="threeRight">
							<div class="threeSign">
								<c:if test="${TodayIsSignIn eq true}">
									<img src="/resources/home/images/sign.png">
								</c:if>
								<c:if test="${TodayIsSignIn eq false}">
									<img src="/resources/home/images/noSign.png">
								</c:if>
								<p>已累计签到 <i>${userSignCount==null?0:userSignCount}</i>天</p>
								<a href="/user/sign.shtml">我要签到</a>
							</div>
							<div class="threeRank">
								<div class="threeRankT">
									<img src="/resources/home/images/threerank.png" alt="">
									<span>热帖排行</span>
								</div>
								<ul>
									<c:forEach var="item" items="${articleHot }" begin="0" end="${articleHot.size() gt 9 ? 9 : articleHot.size() }" varStatus="i" >
										<li>
											<a href="/home/articleDetails/view.shtml?id=${item.id }" onclick="aclick();">
												<i class="uier emer${i.index + 1 }">${i.index + 1 }</i>
												<span>${item.title.length() le 15 ? item.title : item.title.substring(0, 15).concat('...') }</span>
											</a>
										</li>
									</c:forEach>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!--右边-->
		</div>
		<!--中间-->
		<script>
		//图片轮播的js函数
		$(function() {
			var lis = $("#imgChange").find("li");
		    var circles = $("#circles").find("li");
		    var count = 0;
		    var timer1 = null;
		    function showImages(index) {
		    	if(lis.length > 1){
		    		for (var i = 0; i < lis.length; i++) {
		                lis[i].className = "hide";
		            }
		            lis[index].className = "show";
		    	}
		    	if(circles.length>0){
		    		for (var k = 0; k < circles.length; k++) {
		        		circles[k].className = "";
		            }
		            circles[index].className = "active";
		    	}
		    }
		    showImages(0);
		    function autoplay() {
		        count == lis.length&&(count=0);
		        showImages(count);
		        count++;
		    }
		    timer1 = setInterval(autoplay,3000);
		    for(var j = 0;j < circles.length;j++){
		        circles[j].index = j;
		        circles[j].onclick = function () {
		            clearInterval(timer1);
		            showImages(this.index);
		            count = this.index;
		            timer1 = setInterval(autoplay,3000);
		        }
		    }
		}); 
			$(".panel_left dt").click(function () {
                if($(this).hasClass("curr")){
                   /*  $(this).siblings("dd").stop(true,true).slideUp(); */
                    $(this).removeClass("curr");
                }else{
                   /*  $(this).siblings("dd").stop(true,true).slideDown(); */
                    $(this).addClass("curr");
                }
            });
			$(".mouldTitle img").click(function () {
				if($(this).hasClass("expant")){
				    $(this).parents(".mouldOne").find("ul").stop(true,true).slideUp();
				    $(this).attr("src","/resources/home/images/yes.gif");
                    $(this).removeClass("expant");
				}else{
                    $(this).parents(".mouldOne").find("ul").stop(true,true).slideDown();
                    $(this).attr("src","/resources/home/images/no.gif");
                    $(this).addClass("expant");
				}
            })
		</script>
	</body>
</html>