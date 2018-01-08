<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form action="/home/article/list.shtml" id="form_global_top" method="post">
		<div class="header">
			<div class="logo fl">
				<a href="/home/index.shtml"><img src="/resources/home/images/logo.png" alt=""></a>
			</div>
			<div class="header-nav fr">
				<div class="header-nav1 fl">
					<input type="hidden" name="globalFlag" value="true" />
					<label>
						<input name="title" type="text" placeholder="请输入搜索关键词" class="headerinput" datatype="*" />
						<a href="javascript:void(0);" onclick="sub()"><i class="icon iconfont">&#xe68d;</i></a>
					</label>
				</div>
				<div class="header-nav2 fl">
					<span class="fl aim">
						<c:if test="${not empty sessionScope.session_user.username}">
							<a href="${path}/user/index.shtml?"><em><img src="${sessionScope.session_user.headImage==null?'/resources/home/images/images6.png':sessionScope.session_user.headImage}"></em>${sessionScope.session_user.username}</a>
							<div class="memberCor">
								<a href="${path}/user/index.shtml" class="curr">个人中心</a>
								<a href="/home/logout.shtml">退出论坛</a>
							</div>
						</c:if>
						<c:if test="${empty sessionScope.session_user.username}">
							<a href="/home/view.shtml">登录</a>
							<a href="/home/register.shtml">注册</a>
						</c:if>
					</span>
				</div>
			</div>
		</div>
</form>
<script type="text/javascript" src="/resources/admin/script/jquery/Validform_v5.3.2_min.js"></script>
<script type="text/javascript">
	function sub () {
		if($(".header-nav1 .headerinput").val().trim()!=""){
			$('#form_global_top').submit();
		}else{
			layer.alert("输入不能为空");
		}
	}
</script>
