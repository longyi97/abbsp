<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>登录</title>
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
						<a href="/home/view.shtml">登录</a>
						<a href="/home/register.shtml">注册</a>
					</label>
				</div>
		</div>
		<div class="user_log w1200">
			<form method="post" id="ruivalidate_form_class" class="ruivalidate_form_class" name="ruivalidate_form_class">
				<input type="hidden" id="comingUrl" name="comingUrl" value="${comingUrl }" />
				<input type="hidden" id="toUrl" name="toUrl" value="${toUrl }" />
				<dl>
					<dt>&nbsp;</dt>
					<dd><h2>用户登录</h2></dd>
				</dl>
				<dl>
					<dt>账号</dt>
					<dd><input type="text" id="username" name="username" isnot="true" msg="请输入用户名" placeholder="请输入用户名"/> </dd>
				</dl>
				<dl>
					<dt>密码</dt>
					<dd><input type="password" id="password" name="password" isnot="true" msg="请输入密码" placeholder="请输入密码"/> </dd>
				</dl>
				<dl>
					<dt>验证码</dt>
					<dd>
						<div class="yzm-box">
							<input type="text" id="captcha" name="captcha" class="w_186" isnot="true" msg="请输入验证码" placeholder="请输入验证码"/>
						<a href="javascript:void(0)" class="get-code">
							<img src='/home/common/captcha.shtml' class='captcha' style='cursor: pointer' width='150' height='50' margin-left='10px'/></a> 
						</div>
					</dd>
				</dl>								
				<dl>
					<dt>&nbsp;</dt>
					<dd>
						<input type="button" value="立即登录" class="xbtn" id="sub_btn" name="button"/>
					</dd>
				</dl>
				<dl>
					<dt>&nbsp;</dt>
					<dd>
						<span>还没有账号？<a href="/home/register.shtml" >点击注册</a></span>
					</dd>
				</dl>
			</form>
		</div>
		
		<!-- ajax登录 -->
		<script type="text/javascript">
		 var url=$('.captcha').attr('src');
		    $(".captcha").click(function () {
		        url1 = url + "?tmp" + Math.random();
		        this.src=url1;
		    }); 
		
		   function submitFun(){
		   	var username=$("#username").val();
			var password=$("#password").val();
			var captcha=$("#captcha").val();
			var toUrl=$("#toUrl").val();
			var comingUrl=$("#comingUrl").val();
			
			if(username==""){
				layer.alert("请输入用户名")
			}else if(password==""){
				layer.alert("请输入密码")
			}else if(captcha==""){
				layer.alert("请输入验证码")
			}else if(!captcha.match(/[\w\d]{4}/)){
				layer.alert("验证码格式错误")
			}else{
				$.ajax({
					url:"/home/login.shtml",
					type:"POST",
					data:{"username":username,"password":password,"captcha":captcha,"toUrl":toUrl,"comingUrl":comingUrl},
					dataType:"json",
					async:false,
					success:function(data){
						if(data.type=="info"){
		  						//登录成功，跳转前台首页
		  						window.location=data.url;
	  					}else{
	  						layer.alert(data.message);
							//刷新验证码
	  						$(".captcha").attr('src', $('.captcha').attr('src') + "?tmp" + Math.random());
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