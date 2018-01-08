<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="renderer" content="webkit|ie-comp|ie-stand" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0,user-scalable=no" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <base href="${base}" />
    <title>添加用户</title>
    <script type="text/javascript">
		var adminMenuData='/admin/common/nav.shtml';
    </script>
    <link media="all" type="text/css" rel="stylesheet" href="/resources/admin/skin/style.css" />
    <link media="all" type="text/css" rel="stylesheet" href="/resources/admin/script/wangEditor/dist/css/wangEditor.min.css" />
     <link rel="stylesheet" href="/resources/admin/css/jquery.datetimepicker.css" />
    <script type="text/javascript" src="/resources/admin/script/jquery/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="/resources/admin/script/layer/layer.js"></script>
    <script type="text/javascript" src="/resources/admin/script/jquery/Validform_v5.3.2_min.js"></script>
    <script type="text/javascript" src="/resources/admin/script/webuploader/webuploader.min.js"></script>
    <script type="text/javascript" src="/resources/admin/js/uploader.js"></script>
    <script type="text/javascript" src="/resources/admin/js/laymain.js"></script>
    <script type="text/javascript" src="/resources/admin/js/layindex.js"></script>
    <script type="text/javascript" src="/resources/admin/script/common.js"></script>
    <script type="text/javascript" src="/resources/admin/script/wangEditor/dist/js/wangEditor.min.js"></script>
     <script type="text/javascript" src="/resources/admin/js/jquery.datetimepicker.js" type="text/javascript"></script>
      <script type="text/javascript">
 document.onkeydown=function(e){
	 var keycode=document.all?event.keyCode:e.which;
	 if(keycode==13)
		 submitzhuc();
};
function isEmail(){
	  var email = document.getElementById("email");    //获取email控件对象
    if (!email.value == "") {
              var reg =/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
              var bool = reg.test(email.value);
              if (bool == false) {
              	 $("#emailSpan").html("请输入正确的邮箱格式");
              	 $("#emailSpan").css({
    				"color": "red",
    			    "padding-left": "20px",
    			    "white-space": "nowrap",
    			    "background": "url(/resources/admin/skin/valid_icons.png) no-repeat -20px -40px"});
          		 $("#emailSpan").show();
          		 return false;
              }else{
            	 $("#emailSpan").html("信息通过验证");
            	 $("#emailSpan").css({
     				"color": "#71b83d",
     			    "padding-left": "20px",
     			    "white-space": "nowrap",
     			    "background": "url(/resources/admin/skin/valid_icons.png) no-repeat -40px -20px"});
         		 $("#emailSpan").show();
         		 return true;
         	 }
    }
    $("#emailSpan").hide();
    return true;
} 
function isMobile(){
	 var mobile = document.getElementById("mobile");
	if(!mobile.value == ""){
		 var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
		 if(!myreg.test($("#mobile").val())) 
		 { 
			$("#mobileSpan").html("请输入正确的手机格式");
			$("#mobileSpan").css({
				"color": "red",
			    "padding-left": "20px",
			    "white-space": "nowrap",
			    "background": "url(/resources/admin/skin/valid_icons.png) no-repeat -20px -40px"});
   		 	$("#mobileSpan").show(); 
   		 return false;
	 	 }else{
	 	 	$("#mobileSpan").html("信息通过验证");	
	 	 	$("#mobileSpan").css({
 				"color": "#71b83d",
 			    "padding-left": "20px",
 			    "white-space": "nowrap",
 			    "background": "url(/resources/admin/skin/valid_icons.png) no-repeat -40px -20px"});
  		 	$("#mobileSpan").show();
  		 	return true;
  	 	}
	} 
	 $("#mobileSpan").hide();
	 return true;
} 
 function isUserName(){
	 var flag=false;
	 var name=$.trim($("#name").val());
	 if(""==name){
		 $("#namespan").html("请输入用户名");
		 $("#namespan").css({
				"color": "red",
			    "padding-left": "20px",
			    "white-space": "nowrap",
			    "background": "url(/resources/admin/skin/valid_icons.png) no-repeat -20px -40px"});
		 $("#namespan").show();
		 return flag;
	 }else if(name.length<2||name.length>10){
		 $("#namespan").html("用户长度只能在2-10位");
		 $("#namespan").css({
				"color": "red",
			    "padding-left": "20px",
			    "white-space": "nowrap",
			    "background": "url(/resources/admin/skin/valid_icons.png) no-repeat -20px -40px"});
		 $("#namespan").show();
		 return flag;
	 }
	 else{
		 $("#namespan").hide();
		 var param={};
		 param.username=name;
		 $.ajax({
			   type: "POST",
			   url: "${path}/admin/user/isUserName.shtml",
			   data: param,
			   dataType:"json",
			   async: false,
			   success: function(data){
				   if(data=="0"){
					   $("#namespan").html("用户名重复");
					   $("#namespan").css({
							"color": "red",
						    "padding-left": "20px",
						    "white-space": "nowrap",
						    "background": "url(/resources/admin/skin/valid_icons.png) no-repeat -20px -40px"});
						$("#namespan").show();
						flag=false;
				   }else{
						$("#namespan").html("信息通过验证");
						$("#namespan").css({
							"color": "#71b83d",
						    "padding-left": "20px",
						    "white-space": "nowrap",
						    "background": "url(/resources/admin/skin/valid_icons.png) no-repeat -40px -20px"});
						$("#namespan").show();
						flag=true;
				   }
			   }
		 });
		 return flag; 
	 }
 } 
  function isPwd(){
	 var pwd=$.trim($("#pwd").val());
	 $("#pwd").val(pwd);
	 if(""==pwd){
		 $("#pwdspan").html("请输入密码");
		 $("#pwdspan").css({
				"color": "red",
			    "padding-left": "20px",
			    "white-space": "nowrap",
			    "background": "url(/resources/admin/skin/valid_icons.png) no-repeat -20px -40px"});
		 $("#pwdspan").show();
		 return false;
	 }else if(pwd.length<6||pwd.length>16){
		 $("#pwdspan").html("密码长度应该在6-16位");
		 $("#pwdspan").css({
				"color": "red",
			    "padding-left": "20px",
			    "white-space": "nowrap",
			    "background": "url(/resources/admin/skin/valid_icons.png) no-repeat -20px -40px"});
		 $("#pwdspan").show();
		 return false;
	 }else{
		 $("#pwdspan").html("信息通过验证");
		 $("#pwdspan").css({
				"color": "#71b83d",
			    "padding-left": "20px",
			    "white-space": "nowrap",
			    "background": "url(/resources/admin/skin/valid_icons.png) no-repeat -40px -20px"});
		 $("#pwdspan").show();
		 return true; 
	 }
 } 
 function isqpwd(){
	 var pwd=$.trim($("#pwd").val());
	 var qpwd=$.trim($("#qpwd").val());
	 if(qpwd==""){
		 $("#qpwdspan").html("请输入密码");
		 $("#qpwdspan").css({
				"color": "red",
			    "padding-left": "20px",
			    "white-space": "nowrap",
			    "background": "url(/resources/admin/skin/valid_icons.png) no-repeat -20px -40px"});
		 $("#qpwdspan").show();
		 return false;
	 }else if(pwd!=qpwd){
		 $("#qpwdspan").html("两次密码不一致");
		 $("#qpwdspan").css({
				"color": "red",
			    "padding-left": "20px",
			    "white-space": "nowrap",
			    "background": "url(/resources/admin/skin/valid_icons.png) no-repeat -20px -40px"});
		 $("#qpwdspan").show();
		 return false;
	 }else{
		 $("#qpwdspan").html("信息通过验证");
		 $("#qpwdspan").css({
				"color": "#71b83d",
			    "padding-left": "20px",
			    "white-space": "nowrap",
			    "background": "url(/resources/admin/skin/valid_icons.png) no-repeat -40px -20px"});
		$("#qpwdspan").show();
		 return true;
	 }
 }
 function submitzhuc(){
	 if(isUserName()&&isPwd()&&isqpwd()&&isEmail()&&isMobile()){
		 var param={};
		 param.username=$.trim($("#name").val());
		 param.password=$.trim($("#qpwd").val());
		 param.sex=$("input:radio:checked").val();
		 param.birthday=$.trim($("#datetimepicker9").val());
		 param.email=$.trim($("#email").val());
		 param.departmentId=$.trim($("#departmentId").val());
		 param.mobile=$.trim($("#mobile").val());
		 param.sign=$.trim($("#sign").val());
		 param.headImage=$.trim($("#fileUrl").val());
		 $.ajax({
			   type: "POST",
			   url: "${path}/admin/user/save.shtml",
			   data: param,
			   dataType:"json",
			   async: false,
			   success: function(data){
				   var flag=data.flag;
				   var msg=data.msg;
				   if(flag=="1"){
					   alert(msg);
					   window.location.href='${path}/admin/user/list.shtml';
				   }else{
					   alert(msg);
				   }
			   }
		 });
	 }
 }
 </script> 
    
</head>
<body class="mainbody">
    <form method="post" action="save.shtml" accept-charset="UTF-8" id="form1" enctype="multipart/form-data">
    	<input name="_token" type="hidden" value="iFzn6pfKbyNzYFcc8udsxhIV0o4A5X7FOrwwwqIV" />
        <!--导航栏-->
        <div class="location">
            <a href="javascript:history.back(-1);" class="back"><i></i><span>返回上一页</span></a>
            <a href="../common/center.shtml" class="home"><i></i><span>首页</span></a>
            <i class="arrow"></i>
            <a href="list.shtml"><span>用户管理</span></a>
            <i class="arrow"></i>
            <span>添加用户</span>
        </div>
        <div class="line10"></div>
        <!--/导航栏-->

        <!--内容-->
        <div id="floatHead" class="content-tab-wrap">
            <div class="content-tab">
                <div class="content-tab-ul-wrap">
                    <ul>
                        <li><a class="selected" href="javascript:;">用户信息</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="tab-content">
        
        	<dl>
                <dd>
                    <span class="" style="color: red;">${msg}</span>
                </dd>
            </dl>
			<dl>
				<dt>头像：</dt>
				<dd>
					<div class="img-show" style="border: 0;">
						<span><img width="80px" height="100px" id="face_pic" /></span> <span
							class="fl clr b0"> <a href="javascript:void(0)"
							id="uploaderFace" class="upload-img2 add_file fl "> 上传头像 </a>
						</span>
					</div>
					<input type="file" id="upload_file" style="display: none;" /> 
					<input id="fileUrl" type="hidden" name="headImage"/>
				</dd>
			</dl>
			<dl>
                <dt>用户名</dt>
                <dd>
                	<input class="input normal" name="username" type="text"  id="name"  onblur="isUserName();"/>
                	 <span>*</span>
                	<i id="namespan"  style="color:red"></i>
                </dd>
            </dl>
            <dl>
                <dt>密码</dt>
                <dd>
                    <input class="input normal" type="password" id="pwd"  onblur="isPwd();"/>
                    <span>*</span>
                    <i id="pwdspan"  style="color:red"></i>
                </dd>
            </dl>
            <dl>
                <dt>确认密码</dt>
                <dd>
                    <input class="input normal" name="password" type="password" id="qpwd" onblur="isqpwd();"/>
                    <span>*</span>
              		<i id="qpwdspan"  style="color:red"></i>
                </dd>
            </dl>
            <dl>
            <dt>性别</dt>
            <dd>
                <div class="rule-multi-radio">
                    <span>
                        <input id="status1" name="sex" type="radio" value="true"  checked="checked" />
                        <label for="status1">男</label>
                        <input id="status2" name="sex" type="radio" value="false"/>
                        <label for="status2">女</label>
                    </span>
                </div>
            </dd>
        	</dl>
            <dl>
                <dt>部门</dt>
                <dd>
                	<div class="rule-single-select single-select">
                		<div class="boxwrap">
                			<a class="select-tit" href="javascript:;">
                			<span>全部</span>
                			<i></i></a>
                			<div class="select-items">
                				<ul><li class="selected">全部</li><li>账号</li><li>IP</li></ul>
                			</div><i class="arrow"></i></div><div class="boxwrap" style="display: none;"><a class="select-tit" href="javascript:;"><i></i></a><div class="select-items"><ul></ul></div>
					<i class="arrow"></i></div>
                            <select name="departmentId" id="departmentId" class="keyword" style="display: none;">
								 <c:forEach var="list" items="${departments}">
                					 <option value="${list.id }">${list.name}</option>
                				 </c:forEach>
							</select>
                            </div>
                            <span>*</span>
                </dd>
            </dl>
            <dl>
                <dt>生 日</dt>
                <dd>
                <input type="text" readonly="readonly" id="datetimepicker9" name="birthday" class="input normal"/>
                   <!--  <input class="input normal" name="birthday" type="date" id="birthday"/> -->
                </dd>
            </dl>
            <dl>
                <dt>邮箱</dt>
                <dd>
                	<input class="input normal" name="email" type="text"  id="email"  onblur="isEmail();"/>
                	 <i id="emailSpan"></i>
                </dd>
            </dl>
            <dl>
                <dt>手机</dt>
                <dd>
                	<input class="input normal" name="mobile" type="text"  id="mobile"  onblur="isMobile();"/>
                	 <i id="mobileSpan"></i>
                </dd>
            </dl>
            <dl>
				<dt>个性签名</dt>
				<dd><textarea maxlength="50" onchange="this.value=this.value.substring(0, 50)" onkeydown="this.value=this.value.substring(0, 50)" onkeyup="this.value=this.value.substring(0, 50)"  class="input normal" type="text" cols ="60" rows = "7" name="sign" id="sign"  placeholder="请用汉字介绍下自己吧，限50字以内。">${user.sign}</textarea>
			</dl>
        </div>
        <!--/内容-->
        <!--工具栏-->
        <div class="page-footer">
            <div class="btn-wrap">
                <input class="btn" type="button" value="提交保存" onclick="submitzhuc();" id="zhucbut"/>
                <input class="btn yellow" onclick="javascript:history.back(-1);" type="button" value="返回上一页" />
            </div>
        </div>
        <!--/工具栏-->
    </form>
    <script type="text/javascript">
    
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
    
  //绑定keydown事件，监听用户的按键,禁用Enter键自动提交表单
    document.onkeydown = function(event) {  
        var target, code, tag;  
        if (!event) {  
            event = window.event; //针对ie浏览器  
            target = event.srcElement;  
            code = event.keyCode;  
            if (code == 13) {  
                tag = target.tagName;  
                if (tag == "TEXTAREA") { return true; }  //只有当事件源是TEXTAREA时才return true，允许默认动作；其他元素全部return false，禁止表单提交和任何响应
                else { return false; }  
            }  
        }  
        else {  
            target = event.target; //针对遵循w3c标准的浏览器，如Firefox  
            code = event.keyCode;  
            if (code == 13) {  
                tag = target.tagName;  
                if (tag == "INPUT") { return false; }  //当事件源是INPUT时才return false禁止表单默认动作
                else { return true; }  
            }  
        }  
    }; 
    
        $(function () {
            $('#form1').initValidform();
        })
        
        $(function(){
	    	 
	    	var webuploader = WebUploader.create({
	    		swf:"/resources/home/js/webuploader/Uploader.swf",
	    		server:"/admin/upload.jsp",
	    		auto:true,
	    		pick:"#uploaderFace"
	    	}); 
	    	
	    	webuploader.on("uploadSuccess",function(file,data){
	    		if (data.error == 0) {
               		$("#face_pic").attr('src',data.url);
               		$("#fileUrl").attr('value',data.url);
                } else {
                	alert("哈哈！出错了。");
                }
	    	});
	    	
	    	webuploader.on("uploadError",function(){
	    		console.log("error");
	    	}); 
	    })
    </script>
    
</body>
</html>
    