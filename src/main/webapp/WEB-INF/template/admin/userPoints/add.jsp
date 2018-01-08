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
    <title>更改积分</title>
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
</head>
<body class="mainbody">
    <form method="post" action="save.shtml" accept-charset="UTF-8" id="form1">
    	<input name="_token" type="hidden" value="iFzn6pfKbyNzYFcc8udsxhIV0o4A5X7FOrwwwqIV" />
        <!--导航栏-->
        <div class="location">
            <a href="javascript:history.back(-1);" class="back"><i></i><span>返回上一页</span></a>
            <a href="http://localhost:8021/background/center" class="home"><i></i><span>首页</span></a>
            <i class="arrow"></i>
            <a href="list.shtml"><span>用户积分管理</span></a>
            <i class="arrow"></i>
            <span>更改用户积分</span>
        </div>
        <div class="line10"></div>
        <!--/导航栏-->

        <!--内容-->
        <div id="floatHead" class="content-tab-wrap">
            <div class="content-tab">
                <div class="content-tab-ul-wrap">
                    <ul>
                        <li><a class="selected" href="javascript:;">用户积分信息</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="tab-content">
            <dl>
                <dt>用户名</dt>
                <dd>
                    <input class="input normal" name="username" type="text" id="username" onblur="isUserName()" />
                	<i id="namespan"> <span>*</span></i>
                </dd>
            </dl>
             <dl>
                <dt>积分操作</dt>
                <dd>			
                	<div class="rule-single-select single-select">
                    	<select name="flag">
            				<option value="+">加分</option>
            				<option value="-">减分</option>
						</select>
					</div>
                </dd>
            </dl>
            <dl>
                <dt>分值</dt>
                <dd>
                    <input class="input normal" datatype="/^\d{1,11}(?:\.\d{1,2})?$/" errormsg="请填写1-11位整数 保留2位小数" name="changePoints" type="text" />
                    <span class="Validform_checktip">*</span>
                </dd>
            </dl>
            <dl>
                <dt>备注</dt>
                <dd>
                    <input class="input normal" datatype="*1-255" name="item" type="text" />
                    <span class="Validform_checktip">*</span>
                </dd>
            </dl>
        </div>
        <!--/内容-->
        <!--工具栏-->
        <div class="page-footer">
            <div class="btn-wrap">
                <input id="btn" class="btn" type="button"" value="提交保存" />
                <input class="btn yellow" onclick="javascript:history.back(-1);" type="button" value="返回上一页" />
            </div>
        </div>
        <!--/工具栏-->
    </form>
    <script type="text/javascript">
        $(function () {
            $('#form1').initValidform();
        })
        
     function isUserName(){        
	 var username=$.trim($("#username").val());
	 if(""==username){
		 $("#namespan").html("请输入用户名");
		 $("#namespan").css({
				"color": "red",
				"margin-left": "8px",
			    "padding-left": "20px",
			    "white-space": "nowrap",
			    "background": "url(/resources/admin/skin/valid_icons.png) no-repeat -20px -40px"});
		 $("#namespan").show();
	 }else{
		 $("#namespan").html("信息通过验证");
		 $("#namespan").css({
				"color": "#71b83d",
			    "padding-left": "20px",
			    "white-space": "nowrap",
			    "background": "url(/resources/admin/skin/valid_icons.png) no-repeat -40px -20px"});
		 $("#namespan").show();
	 }
	 var param={};
	 param.username=username;
	 $.ajax({
		   type: "POST",
		   url: "${path}/admin/userPoints/isUserName.shtml",
		   data: param,
		   dataType:"json",
		   async: false,
		   success: function(data){
			   if(data=="0"){
				   $("#btn").click(function() {
				        $("#form1").submit();
						})
			   }else{
				   $("#namespan").html("用户名不存在！");
					 $("#namespan").css({
							"color": "red",
						    "padding-left": "20px",
						    "white-space": "nowrap",
						    "background": "url(/resources/admin/skin/valid_icons.png) no-repeat -20px -40px"});
					 $("#namespan").show();
			   }
		   }
	 });
 }
        
    </script>
</body>
</html>
    