<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="userCon">
			<div class="userHead">
				<div class="w1200 UHCon">
					<div class="userPic">
						<c:if test="${user.headImage==null}">
							<img src="/resources/home/images/images6.png" alt="">
						</c:if>
						<c:if test="${user.headImage!=null}">
							<img src="${user.headImage}" alt="">
						</c:if>
					</div>
					<div class="UHindex">
						<div class="UHtop">
							<i>${user.username}<em>
							（<c:if test="${null==departmentName}">暂无部门</c:if><c:if test="${null!=departmentName}">${departmentName}</c:if>）
							</em></i> <span>帖子<em>（${articleCount }）</em></span> <span>精华<em>（${articleHitCount }）</em></span>
						</div>
						<div class="UHmiddle">
							<p>${user.sign} </p>
						</div>
						<div class="UHfoot">
							<ul>
								<li><a href="/user/index.shtml">首页</a></li>
								<li><a href="/user/signList.shtml">积分</a></li>
								<li><a href="/user/userData.shtml" class="curr">设置</a></li>
							</ul>
						</div>
					</div>
					<div class="UHright">
						<img src="/resources/home/images/signPic.png" alt="">
						<ul>
							<li>一</li>
							<li>二</li>
							<li>三</li>
							<li>四</li>
							<li>五</li>
							<li>六</li>
							<li>日</li>
						</ul>
						<a href="/user/sign.shtml">今日签到</a>
					</div>
				</div>
			</div>
			
			
<script>       
//页面加载   获取全部信息
$(function(){
    $.ajax({
        type: "POST",//请求方式
        url: "/user/weekDignIn.shtml",//地址，就是json文件的请求路径
        dataType: "json",//数据类型可以为 text xml json  script  jsonp
		 success: function(data){//返回的参数就是 action里面所有的有get和set方法的参数
			var dateList = data.weeks;
			if( dateList !="" && dateList != null && dateList !=undefined ){
				 for(var i = 0;i<dateList.length;i++){
			            $(".UHright li").eq(dateList[i]-1).empty().html('<i class="iconfont">&#xe603;<\/i>');
			        }
			}
	       
        }
    });
  
	
});

</script>