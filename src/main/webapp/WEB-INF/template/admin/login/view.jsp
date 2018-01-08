<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0,user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <base href="${base}" />
    <title>管理员登录</title>
    <link media="all" type="text/css" rel="stylesheet" href="/resources/admin/skin/style.css">
    <link media="all" type="text/css" rel="stylesheet" href="/resources/admin/script/artdialog/ui-dialog.css">
    <script type="text/javascript" src="/resources/admin/script/jquery/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="/resources/admin/script/artdialog/dialog-min.js"></script>
    <script type="text/javascript" src="/resources/admin/script/common.js"></script>
    <script type="text/javascript" src="/resources/admin/script/layer/layer.js"></script>

</head>
<body class="loginbody">
<div class="login-wrap">
    <form method="POST" action="/admin/login/action.shtml" accept-charset="UTF-8" class="login-form"><input name="_token" type="hidden" value="lFmCm7zSLNecUUkCBq3OrlKHsdDZAxu7LkoPqwpK">
        <div class="col">
            <input class="login-input" placeholder="管理员名称" id="username" name="username" type="text">
        </div>
        <div class="col">
            <input class="login-input" placeholder="管理员密码" id="password" name="password" type="password">
        </div>
        <div class="col">
            <input class="login-btn" type="button" value="登 录">
        </div>
    </form>
</div>
<div class="copy-right">
    <p>版权所有  Copyright ? 2012 - 2017 192.168.0.7:8021</p>
</div>

<script type="text/javascript">
    function submitFun(){
    	var username=$("#username").val();
		var password=$("#password").val();
		
		if(username==""||password==""){
			layer.alert("登录信息不允许为空");
		}else{
			$.ajax({
				url:"action.shtml",
				type:"POST",
				data:{"username":username,"password":password},
				dataType:"json",
				async:false,
				success:function(data){
					if(data.type=="info"){
						//登录成功，跳转后台管理首页
						window.location=data.url;
					} else{
						layer.alert(data.message, {closeBtn: 0}, function(){
  							//刷新頁面
  							location.reload();
						});
					}
	
				}
			});
		}
    }
    
    $(function(){
		$(".login-btn").click(function(){
			submitFun();
		})
		$(document).keyup(function(event){
			if(event.keyCode ==13){
				submitFun();
			}
		});
	})
</script>
</body>
</html>
