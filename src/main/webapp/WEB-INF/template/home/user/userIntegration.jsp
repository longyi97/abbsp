<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
				<div class="tabSecond">
					<div class="tabHead">
						<a href="javascript:void(0);" class="butt curr">积分列表</a> <a
							href="javascript:void(0);" class="butt">积分规则</a>
					</div>
					<div class="integrationList">
						<form action="signList.shtml">
							<table>
								<colgroup>
									<col width="23%">
									<col width="22%">
									<col width="24%">
									<col width="31%">
								</colgroup>
								<thead>
									<tr>
										<th>积分时间</th>
										<th>积分加减</th>
										<th>积分总额</th>
										<th>积分来源</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="list" items="${page.list}" >
			                           <tr>
			                              <td><fmt:formatDate value="${list.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			                              <td class="judge">${list.changePoints}</td>
			                              <td>${list.currentPoints}</td>
			                              <td>${list.item}</td>
			                           </tr>
		                 		    </c:forEach>
								</tbody>
							</table>
							  <c:if test="${nolist==0}">
										<div class="nolist">
										<img src="/resources/home/images/nullNews.png" alt="">
										<p>暂无记录！</p>
										</div>
									</c:if>
							<div class="paging">
								 <%@ include file="../common/page.jsp" %>	
							</div>
						</form>
					</div>
					<div class="integrationMain" style="display: none;">
						<%-- <img src="/resources/home/images/integrationHead.jpg" alt=""
							class="integrationImg">
						<dl>
							<dd><i>1</i><span>每天签到赠送${oneSign}积分</span></dd>
		                    <c:forEach var="list" items="${signRule}" varStatus="stop">
		                     <dd><i>${stop.index+2}</i><span>连续${list.continuousDays}天签到额外赠送${list.rewardPoints}积分</span></dd>
		                    </c:forEach>
						</dl> --%>
						 <p>进行以下时间动作，会得到积分奖励或惩罚。不过，在一个周期内，你最多得到的奖励次数有限制，每人每日最高获得${configs['points_pre_user_pre_day_max'] }积分(一个帖子最多可得${configs['points_pre_article_max'] }积分)。</p>
	                    <table>
	                        <colgroup>
	                            <col width="26%">
	                            <col width="26%">
	                            <col width="26%">
	                            <col width="22%">
	                        </colgroup>
	                        <thead>
	                        <tr>
	                            <th>获取积分操作名称</th>
	                            <th>周期范围</th>
	                            <th>周期内最多奖励次数</th>
	                            <th>积分</th>
	                        </tr>
	                        </thead>
	                        <tbody>
	                        <tr>
	                            <td>签到</td>
	                            <td>每天</td>
	                            <td>1</td>
	                            <td>${oneSign}</td>
	                        </tr>
	                         <c:forEach var="list" items="${signRule}" varStatus="stop">
	                          <tr>
	                            <td>连续签到</td>
	                            <td>${list.continuousDays}天</td>
	                            <td>1</td>
	                            <td>${list.rewardPoints}</td>
	                        </tr>
		                    </c:forEach>
	                        <tr>
	                            <td>发帖</td>
	                            <td>1</td>
	                            <td>${configs['points_post_times'] }</td>
	                            <td class="integrationNum">${configs['points_post'] }</td>
	                        </tr>
	                        <tr>
	                            <td>回复</td>
	                            <td>1000</td>
	                            <td>${configs['points_post_times'] }</td>
	                            <td>${configs['points_reply'] }</td>
	                        </tr>
	                        <tr>
	                            <td>帖子被回复</td>
	                            <td>1000</td>
	                            <td>${configs['points_post_times'] }</td>
	                            <td>${configs['points_post_replied'] }</td>
	                        </tr>
	                        <tr>
	                            <td>水贴</td>
	                            <td>不限周期</td>
	                            <td>不限次数</td>
	                            <td>${configs['points_bad_article'] }</td>
	                        </tr>
	                        </tbody>
	                    </table>
					</div>
				</div>
			</div>
		</div>
		<!--1200内容-->
	</div>
	<!--中间-->
	<script>

	$(document).ready(function () {
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
		 //tabSecond页切换函数
        $(".tabSecond .tabHead a").click(function () {
            $(".tabSecond .tabHead a").removeClass("curr") && $(this).addClass("curr");
            if($(this).index()==0){
                $(".integrationList").css("display","block");
                $(".integrationMain").css("display","none");
            }
            if($(this).index()==1){
                $(".integrationList").css("display","none");
                $(".integrationMain").css("display","block");
            }
        });
		
       
       
        
      	//判断积分列表第二列数字正负并改变其颜色
        if($(".integrationList tbody").size()>0){
            var checkNum =  /^-\s?[0-9]*[1-9][0-9]*/;
            $(".integrationList .judge").each(function () {
                if(checkNum.test($(this).text())){
                    $(this).addClass("c_red");
                }else {
                    $(this).addClass("c_green");
                }
            })
        }
      	
      //判断积分规则最后列数字正负并改变其内容
        if($(".integrationMain tbody").size()>0){
            $(".integrationMain tbody tr td:last-child").each(function () {
                if($(this).text()>0){
                   $(this).prepend("+");
                }
            })
        }
    });
</script>
</body>
</html>
