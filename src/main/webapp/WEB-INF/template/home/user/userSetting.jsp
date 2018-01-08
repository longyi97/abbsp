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
<link rel="stylesheet" href="/resources/home/css/jquery.datetimepicker.css" />
<script type="text/javascript" src="/resources/home/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/resources/home/js/layer.js" ></script>
<script type="text/javascript" src="/resources/admin/script/webuploader/webuploader.min.js"></script>
<script type="text/javascript" src="/resources/admin/js/uploader.js"></script>
<script type="text/javascript" src="/resources/home/js/jquery.datetimepicker.js"></script>
<script type="text/javascript">
function updatepwd(){
	var headImage=$("#fileUrl").val();
	var birthday=$("#datetimepicker9").val();
	var sex=$("input:radio:checked").val();
	var sign=$("#sign").val();
	var id=$("#id").val();
	var username=$("#username").val();
    var param={};
	 param.headImage=headImage;
	 param.birthday=birthday;
	 param.sex=sex;
	 param.sign=sign;
	 param.id=id;
	 param.username=username;
	 $.ajax({
		   type: "POST",
		   url: "${path}/user/update.shtml?id=${sessionScope.session_user.id}",
		   data: param,
		   async: false,
		   dataType: "json", 
		   success: function(data){
			   if(data==1){
				   layer.msg('资料修改成功！', {icon: 6}); 
				   $("#chk_oldpassword").html("");
				   $("#oldpassword").val("");
				   setTimeout(function(){
					   window.location.href='${path}/user/userData.shtml?id=${sessionScope.session_user.id}';
					},1000)
			   }else if(data==0){
				   $("#chk_oldpassword").html("修改资料错误");
			   }else{
				   layer.alert("系统错误");
			   }
		   }
	 });
		return true;
}


function ispw(){
	 var oldpassword=$.trim($("#oldpassword").val());
	 if(oldpassword==""){
		$("#chk_oldpassword").html("请输入原始密码");
		$("#chk_oldpassword").show();
		return false;
   }else{
	   $("#chk_oldpassword").html("");
	   $("#chk_newpassword").show();
	   return true;
	}
}

function ispwd(){
	var pwd=$("#newpassword").val();
	 if(pwd.length<6||pwd.length>16){
		 $("#chk_newpassword").html("密码长度必须是6-16位");
		 $("#chk_newpassword").show();
		 return false;
     }else if (pwd.match(/^(.)\1*$/) != null){
		 $("#chk_newpassword").html("密码不能是相同的数字、字母或符号");
		 $("#chk_newpassword").show();
		 return false;
	 }else if (pwd.match(/^\s|\s$/) != null){
		 $("#chk_newpassword").html("密码首尾不能是空格");
		 $("#chk_newpassword").show();
		 return false; 
	 }else{
		 $("#chk_newpassword").html("");
		 $("#chk_newpassword").show();
		 return true;
	 }
}
function isqpwd(){
	 var pwd=$("#newpassword").val();
	 var qpwd=$("#newpassword2").val();
	 if(pwd!=qpwd){
		 $("#chk_newpassword2").html("两次密码不一致");
		 $("#chk_newpassword2").show();
		 return false;
	 }else{
		 $("#chk_newpassword2").html("");
		 $("#chk_newpassword2").show();
		 return true;
	 }
}

function updatepwd1(){
	var oldpassword=$("#oldpassword").val();
	var newpassword=$("#newpassword").val();
	var newpassword2=$("#newpassword2").val();
	if(ispw()&&ispwd()&&isqpwd()){
		var param={};
		param.pwd=oldpassword;
		param.newpwd=newpassword2;
		$.ajax({
		  type: "POST",
		  url: "${path}/user/savaPwd.shtml?id=${sessionScope.session_user.id}",
		  data: param,
		  async: false,
		  dataType: "json", 
		  success: function(data){
			   if(data==1){
				   layer.alert("密码修改成功，请重新登录！");
				   $("#chk_newpassword2").html("");
				   $("#chk_newpassword").html("");
				   $("#chk_oldpassword").html("");
				   $("#oldpassword").val("");
				   $("#newpassword").val("");
				   $("#newpassword2").val("");
				   window.location.href='${path}/home/view.shtml';
			   }else if(data==0){
				   $("#chk_oldpassword").html("原密码错误！");
			   }else if(data==3){
				   $("#chk_newpassword").html("抱歉，密码不能与用户名相同！");
			   }else if(data==2){
				   $("#chk_newpassword").html("抱歉，设置的密码规则不符合！");
			   }else if(data==4){
				   $("#chk_newpassword").html("抱歉，新密码不能与旧密码一样！");
			   }else{
				   layer.alert("系统错误");
			   }
		  }
		});
	}
}
</script>
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
				<div class="tabThird">
					<div class="tabHead">
						<a href="javascript:void(0);" class="butt curr">我的资料</a> 
						<a href="javascript:void(0);" class="butt">密码设置</a>
					</div>
					<div class="myData">
						<form action="">
							<dl>
								<dt>我的头像：</dt>
								<dd>
									<img id="face_pic" src="${user.headImage==null?'/resources/admin/images/img3.png':user.headImage}" alt=""> 
									<span id="uploaderFace">点击上传</span> 
									<em class="c_9">（上传内容不能大于2M）</em>
									<input id="fileUrl" type="hidden" name="headImage" value="${user.headImage}">
								</dd>
							</dl>
							<dl>
								<dt>性别：</dt>
								<dd>
									<label for="sex-man" class="radio_label">
										<em <c:if test="${user.sex==true}">class="checked"</c:if>></em> 
										<input type="radio" value="true" id="sex-man" name="sex" style="display: none" <c:if test="${user.sex==true}">checked="checked"</c:if> />男
									</label> 
									<label for="sex-woman" class="radio_label checked"> 
										<em <c:if test="${user.sex==false}">class="checked"</c:if>></em> 
										<input type="radio" value="false"id="sex-woman" name="sex" style="display: none" <c:if test="${user.sex==false}">checked="checked"</c:if> />女
									</label>
								</dd>
							</dl>
							<dl>
								<dt>生日：</dt>
								<dd>
									<div class="date-input">
										<i class="iconfont">&#xe618;</i> 
										<input type="text" readonly="readonly" id="datetimepicker9" name="birthday" class="dateTime" value="<fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd"/>">
									</div>
								</dd>
							</dl>
							<dl>
								<dt>个人签名：</dt>
								<dd>
									<textarea placeholder="请用汉字介绍下自己吧，限50字以内。" type="text"  name="sign" id="sign" maxlength="50" onchange="this.value=this.value.substring(0, 50)" onkeydown="this.value=this.value.substring(0, 50)" onkeyup="this.value=this.value.substring(0, 50)" >${user.sign}</textarea>
								</dd>
							</dl>
							<dl>
								<dt></dt>
								<dd>
									<input type="hidden" id="id" name="id" value="${user.id }" />
									<input type="hidden" id="username" name="username" value="${user.username }" />
									<input type="button" value="提交" class="butt curr" onclick="updatepwd();">
								</dd>
							</dl>
						</form>
						<img src="/resources/home/images/revise.png" alt="">
					</div>
					<div class="passwordSet" style="display: none;">
						<form action="">
							<dl>
								<dt>旧密码：</dt>
								<dd>
									<input type="password" name="oldpassword" id="oldpassword" onblur="ispw();" placeholder="请输入旧密码" />
									<p class="d" id="chk_oldpassword"></p></dd>
								</dd>
							</dl>
							<dl>
								<dt>新密码：</dt>
								<dd>
									<input type="password" name="newpassword" id="newpassword" onblur="ispwd();" placeholder="请输入新密码" />
									<p class="d" id="chk_newpassword"></p></dd>
								</dd>
							</dl>
							<dl>
								<dt>确认新密码：</dt>
								<dd>
									<input type="password" name="newpassword2" id="newpassword2" onblur="isqpwd();" placeholder="请确认密码" />
									<p class="d" id="chk_newpassword2"></p></dd>
								</dd>
							</dl>
							<dl>
								<dt></dt>
								<dd>
									<span>密码长度为6-16位，建议您使用字母加数字或符号的组合密码，不能使用重复或连续的数字、字母或符号，不能与自己的用户名或注册邮箱相同！</span>
								</dd>
							</dl>
							<dl>
								<dt>&nbsp;</dt>
								<dd>
									<button type="button" name="pwdsubmit" value="true" class="butt curr" onclick="updatepwd1();">确认修改</button>
								</dd>
							</dl>
						</form>
						<img src="/resources/home/images/revisePassword.png" alt="">
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
        //tsbThird页切换函数
        $(".tabThird .tabHead a").click(function () {
            $(".tabThird .tabHead a").removeClass("curr") && $(this).addClass("curr");
            if($(this).index()==0){
                $(".myData").css("display","block");
                $(".passwordSet").css("display","none");
            }
            if($(this).index()==1){
                $(".myData").css("display","none");
                $(".passwordSet").css("display","block");
            }
        });
			 
       
        //单选按钮修改图片函数
        $('label em').click(function(){
            $('label em').removeAttr('class') && $(this).attr('class', 'checked');
            $('input[type="radio"]').removeAttr('checked') && $(this).siblings('input[type="radio"]').attr('checked', 'checked');
        });
        
        
        //上传插件初始化
        var webuploader = WebUploader.create({
            swf:"/resources/admin/script/webuploader/uploader.swf",
            server:"/admin/upload.jsp",
            auto:true,
            pick:"#uploaderFace"
        });
       	
        setTimeout(function(){ 
            webuploader.on("uploadSuccess",function(file,data){
        		if (data.error == 0) {
               		$("#face_pic").attr('src',data.url);
               		$("#fileUrl").attr('value',data.url);
                } else {
                	layer.alert("请上传正确的图片格式！");
                }
        	});  
            
          	//时间插件
            $('#datetimepicker9').datetimepicker({
                lang:'ch',
                timepicker:false,
                scrollMonth:false,
                scrollTime:false,
                scrollInput:false,
                format:'Y-m-d',
                formatDate:'Y-m-d',
                maxDate:0
            });
        },1000);
        
        
    })
</script>
</body>
</html>
