<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>注册</title>
		<link rel="stylesheet" href="/resources/home/css/common.css" />
		<link rel="stylesheet" href="/resources/home/css/style.css" />
		<link rel="stylesheet" href="/resources/home/css/member.css" />
		<link rel="stylesheet" href="/resources/home/iconfont/iconfont.css" />
		<link rel="stylesheet" href="/resources/home/css/layui.css" />
		<script type="text/javascript" src="/resources/home/js/jquery-1.9.1.min.js" ></script>
		<script type="text/javascript" src="/resources/home/js/ruiec_validate/jquery.form.min.js" ></script>
		<script src="/resources/home/js/ruiec_validate/jquery.ruiValidate.js"></script> 
		<script type="text/javascript" src="/resources/home/js/help.js" ></script>
		<script type="text/javascript" src="/resources/home/js/layer.js" ></script>
	</head>
	<body class="bg_log">
		<div class="Xtop">
			<div class="fl">
				<a href="/home/index.shtml"><img src="/resources/home/images/logo.png"/></a>
			</div>
			<div class="header-nav1 fr">
					<label class="log">
						<a href="/home/view.shtml" >登录</a>
						<a href="/home/register.shtml">注册</a>
					</label>
				</div>
		</div>
		<div class="user_log w1200">
			<form method="post" id="ruivalidate_form_class" class="ruivalidate_form_class" name="ruivalidate_form_class">
				<dl>
					<dt>&nbsp;</dt>
					<dd><h2>用户注册</h2></dd>
				</dl>
				<dl>
					<dt>用户名</dt>
					<dd><input type="text" name="username" id="username" isnot="true" msg="请输入用户名" placeholder="请输入2-10位的用户名"/> </dd>
				</dl>
				<dl>
					<dt>设置密码</dt>
					<dd><input type="password" name="password" id="password" isnot="true" msg="请输入密码" placeholder="请输入6-16位的密码"/> </dd>
				</dl>
				<dl>
					<dt>确认密码</dt>
					<dd><input type="password" name="password1" id="password1" isnot="true" msg="请输入密码" placeholder="请再次确认密码"/> </dd>
				</dl>					
				<dl>
					<dt>&nbsp;</dt>
					<dd>
						<input type="button" id="sub_btn" value="立即注册" class="xbtn" name="button"/>
					</dd>
				</dl>
				<dl>
					<dt>&nbsp;</dt>
					<dd>
						<span>已有账号？<a href="/home/view.shtml" >点击登录</a></span>
					</dd>
				</dl>
			</form>
		</div>
		
		<!-- ajax登录 -->
		<script type="text/javascript">
		    function submitFun(){
		    	var username=$("#username").val();
				var password=$("#password").val();
				var password1=$("#password1").val();
				if(username==""||password==""||password1==""){
					layer.alert("注册信息不允许为空")
				}else if (password!=password1){
					layer.alert("两次密码输入不一致")
				}else if (password.length<6 || password.length>16){
					layer.alert("密码必须为6-16位")
				}else if (password.match(/^(.)\1*$/) != null)
				    {
					layer.alert("密码不能是连续相同的数字、字母或符号")
				}else if (password.match(/^\s|\s$/) != null)
				    {
					layer.alert("密码首尾不能是空格")
				}else if (username.length<2 ||username.length>10){
					layer.alert("用户名必须为2-10位")
				}else if (password==username){
					layer.alert("用户名和密码不能相同")
				}else{
					$.ajax({
						url:"save.shtml",
						type:"POST",
						data:{"username":username,"password":password,"password1":password1},
						dataType:"json",
						async:false,
						success:function(data){
							layer.msg(data.message, {icon: 6}); 
							if(data.type=="info"){
		   						 //登录成功，跳转前台首页
		   						 setTimeout(function(){
		   							window.location=data.url;
								},3000)
		   						
		   					}else{
								//刷新頁面
								/*  location.reload(); */
		   					}
						}
					});
				}
		    }
		    
		    $(function(){
				$("#sub_btn").click(function(){
					submitFun();
				})
				 $(document).keydown(function(event){
					if(event.keyCode ==13){
						submitFun();
					}
				}); 
			})
		</script>

	</body>
</html>