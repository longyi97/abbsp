<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="renderer" content="webkit|ie-comp|ie-stand" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0,user-scalable=no" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <base href="${base}" />
    <title>后台修改密码</title>
    <script type="text/javascript">
		var adminMenuData='/admin/common/nav.shtml';
    </script>
    <link media="all" type="text/css" rel="stylesheet" href="/resources/admin/skin/style.css" />
    <link media="all" type="text/css" rel="stylesheet" href="/resources/admin/script/wangEditor/dist/css/wangEditor.min.css" />
    <script type="text/javascript" src="/resources/admin/script/jquery/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="/resources/admin/script/layer/layer.js"></script>
    <script type="text/javascript" src="/resources/admin/script/jquery/Validform_v5.3.2_min.js"></script>
    <script type="text/javascript" src="/resources/admin/script/webuploader/webuploader.min.js"></script>
    <script type="text/javascript" src="/resources/admin/js/uploader.js"></script>
    <script type="text/javascript" src="/resources/admin/js/laymain.js"></script>
    <script type="text/javascript" src="/resources/admin/js/layindex.js"></script>
    <script type="text/javascript" src="/resources/admin/script/common.js"></script>
    <script type="text/javascript" src="/resources/admin/script/wangEditor/dist/js/wangEditor.min.js"></script>
    
     <script type="text/javascript">
	 
 function ispwd(){
	 var pwd=$.trim($("#pwd").val());
	 $("#pwd").val(pwd);
	 if(pwd.length<6||pwd.length>16){
		 $("#pwdspan").html("密码长度必须是6-16位");
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
	 if(pwd!=qpwd){
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
	 if(isqpwd()&&ispwd()){
		 var param={};
		 param.id=$.trim($("#id").val());
		 param.password=$.trim($("#qpwd").val());
		 $.ajax({
			   type: "POST",
			   url: "${path}/admin/user/savaPwd.shtml",
			   data: param,
			   dataType:"json",
			   async: false,
			   success: function(data){
				   if(data==1){
					   alert("密码修改成功！");
					   window.location.href='${path}/admin/user/list.shtml';
				   }else if(data==0){
					   alert("密码修改错误");
				   }
				   else{
					   alert("系统错误");
				   }
			   }
		 }); 
	 }
	 }
 </script> 
    
</head>
<body class="mainbody">
    <form method="post" action="savaPwd.shtml" accept-charset="UTF-8" id="form1">
    <input name="_token" type="hidden" value="iFzn6pfKbyNzYFcc8udsxhIV0o4A5X7FOrwwwqIV" />
        <!--导航栏-->
        <div class="location">
            <a href="javascript:history.back(-1);" class="back"><i></i><span>返回上一页</span></a>
            <a href="../common/center.shtml" class="home"><i></i><span>首页</span></a>
            <i class="arrow"></i>
            <a href="list.shtml"><span>后台修改密码</span></a>
            <i class="arrow"></i>
            <span>后台修改密码</span>
        </div>
        <div class="line10"></div>
        <!--/导航栏-->

        <!--内容-->
        <div id="floatHead" class="content-tab-wrap">
            <div class="content-tab">
                <div class="content-tab-ul-wrap">
                    <ul>
                        <li><a class="selected" href="javascript:;">后台修改密码</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="tab-content">
            <dl>
                <dt>密 码</dt>
                <dd>
                    <input class="input normal"type="password" value="${user.password}" id="pwd"  onblur="ispwd();"/>
                    <span>*</span>
                    <i id="pwdspan"></i>
                </dd>
            </dl>
             <dl>
                <dt>确认密码</dt>
                <dd>
                    <input class="input normal" name="password"  value="${user.password}"  type="password" id="qpwd" onblur="isqpwd();"/>
                    <span>*</span>
              		<i id="qpwdspan"></i>
                </dd>
        </div>
        <!--/内容-->
        <!--工具栏-->
        <div class="page-footer">
            <div class="btn-wrap">
                <input type="hidden" name="id" value="${id}" id="id"/>
                <input class="btn" type="button" value="提交保存" id="zhucbut" onclick="submitzhuc();"/>
                <input class="btn yellow" onclick="javascript:history.back(-1);" type="button" value="返回上一页" />
            </div>
        </div>
        <!--/工具栏-->
    </form>
    <script type="text/javascript">
    //禁用Enter键自动提交表单
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
    