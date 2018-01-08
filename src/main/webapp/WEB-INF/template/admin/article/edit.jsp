<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="/ruiec-tags" prefix="ruiec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="renderer" content="webkit|ie-comp|ie-stand" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0,user-scalable=no" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <base href="${base}" />
    <title>编辑帖子</title>
    <script type="text/javascript">
    	var adminMenuData='/admin/common/nav.shtml';
    </script>
    <link media="all" type="text/css" rel="stylesheet" href="/resources/admin/skin/style.css" />
    <link media="all" type="text/css" rel="stylesheet" href="/resources/admin/script/wangEditor/dist/css/wangEditor.min.css" />
    <link media="all" type="text/css" rel="stylesheet" href="/resources/admin/script/multsel/multsel.css" />
    <link media="all" type="text/css" rel="stylesheet" href="/resources/admin/css/select2.min.css" />
    <script type="text/javascript" src="/resources/admin/script/jquery/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="/resources/admin/script/layer/layer.js"></script>
    <script type="text/javascript" src="/resources/admin/script/jquery/Validform_v5.3.2_min.js"></script>
    <script type="text/javascript" src="/resources/admin/script/webuploader/webuploader.min.js"></script>
    <script type="text/javascript" src="/resources/admin/js/uploader.js"></script>
    <script type="text/javascript" src="/resources/admin/js/laymain.js"></script>
    <script type="text/javascript" src="/resources/admin/js/layindex.js"></script>
    <script type="text/javascript" src="/resources/admin/script/common.js"></script>
    <script type="text/javascript" src="/resources/admin/script/wangEditor/dist/js/wangEditor.min.js"></script>
    <script type="text/javascript" src="/resources/admin/script/multsel/multsel.js"></script>
    <script type="text/javascript" src="/resources/admin/js/select2.min.js"></script>
     	<link rel="stylesheet" href="/resources/home/js/ueditor/themes/default/css/ueditor.css" />
		<script type="text/javascript" charset="utf-8" src="/resources/home/js/ueditor/ueditor.config.js"></script>
		<script type="text/javascript" charset="utf-8" src="/resources/home/js/ueditor/ueditor.all.min.js"></script>
		<script type="text/javascript" charset="utf-8" src="/resources/home/js/ueditor/third-party/codemirror/codemirror.js"></script>
		<script type="text/javascript" charset="utf-8" src="/resources/home/js/ueditor/third-party/zeroclipboard/ZeroClipboard.js"></script>
		<script type="text/javascript" charset="utf-8" src="/resources/home/js/ueditor/lang/zh-cn/zh-cn.js"></script>
</head>
<body class="mainbody">
 <!--    <form method="post" action="update.shtml" accept-charset="UTF-8" id="form1"> -->
    	<input name="id" type="hidden" value="${article.id }" />
        <!--导航栏-->
        <div class="location">
            <a href="javascript:history.back(-1);" class="back"><i></i><span>返回上一页</span></a>
            <a href="../common/center.shtml" class="home"><i></i><span>首页</span></a>
            <i class="arrow"></i>
            <a href="list.shtml"><span>帖子管理</span></a>
            <i class="arrow"></i>
            <span>编辑帖子</span>
        </div>
        <div class="line10"></div>
        <!--/导航栏-->

        <!--内容-->
        <div id="floatHead" class="content-tab-wrap">
            <div class="content-tab">
                <div class="content-tab-ul-wrap">
                    <ul>
                        <li><a class="selected" href="javascript:;">帖子信息</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="tab-content">
        	<input type="hidden" value="${article.id }" name="id" id="id"/>
            <dl>
                <dt>帖子标题</dt>
                <dd>
                    <input id="titlename" class="input normal" datatype="*" value="${article.title }" name="title" type="text" />
                    <span class="Validform_checktip">*</span>
                </dd>
            </dl>
            <dl>
                <dt>帖子内容</dt>
                <dd>
                    <dd>
						<%-- <textarea id="editor_id" name="content" value="" style="width:700px; height: 300px;" datatype="*">${article.content}</textarea> --%>
						<script id="editor" name="content" type="text/plain" style="width:700px;height: 300px">${article.content}</script>					
					</dd>
                    
                </dd>
            </dl>
            <dl>
                <dt>访问量</dt>
                <dd>
                    <input id="hitname" class="input normal" datatype="/^\d{1,11}(?:\.\d{1,2})?$/" errormsg="请填写1-11位整数 " value="${article.hit }" name="hit" type="text" />
                    <span class="Validform_checktip"></span>
                </dd>
            </dl>
             <dl>
                <dt>所属模块</dt>
                <dd>
                    <input value="${article.moduleId}" name="moduleId" type="hidden" />
                    <input class="input normal" disabled="disabled" value='<ruiec:module parentId="${article.moduleId}"/>' type="text" />
                </dd>
            </dl>
             <dl>
                <dt>发帖时间</dt>
                <dd>
                    <input class="input normal" disabled="disabled" value='<fmt:formatDate value="${article.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>' type="text" />
                </dd>
            </dl>
            <dl>
                <dt>发帖人</dt>
                <dd>
                    <input class="input normal" disabled="disabled" value='<ruiec:user var="r" userId="${article.userId}">${r.username}</ruiec:user>' type="text" />
                    <span class="Validform_checktip"></span>
                </dd>
            </dl>
        </div>
        <!--/内容-->
        <!--工具栏-->
        <div class="page-footer">
            <div class="btn-wrap">
                <!-- <input class="btn" type="submit" value="提交保存" /> -->
                <input type="button" value="提交保存" class="btn" id="secBtn">
                <input class="btn yellow" onclick="javascript:history.back(-1);" type="submit" value="返回上一页" />
            </div>
        </div>
        <!--/工具栏-->
<!--     </form> -->
    <script type="text/javascript">
	    $(function () {
	        $('#form1').initValidform();
	        var ue = UE.getEditor('editor',{elementPathEnabled : false});
	    })
	      $("#secBtn").click(function(){
  	    	var replyId = $("#id").val();
  	    	var title =$("#titlename").val();
  	    	var hit = $("#hitname").val();
  	    	var arr = [];
  	        arr.push(UE.getEditor('editor').getContent());
	  	      var type = /^[0-9]*[1-9][0-9]*$/;
	          var re = new RegExp(type);
	          if (hit.match(re) == null) {
	              alert("请输入大于零的整数!");
	              return;
	          }else if(hit.length>9){
	        	  alert("访问量不能超过999999999");
	              return;
	          }else if(arr[0].length>10000){
	        	  alert("内容字数不能超过一万");
	              return;
	          }
	          
  	    	$.ajax({
  	       	 type:"POST",
  	       	 url:"/admin/article/update.shtml",
  	       	 data:{ 
  	       		 "id":replyId,
  	       		 "title":title,
  	       	     "content":arr[0],
  	       	     "hit":hit,
  	       	 },
  	       	 success:function(data){
  	       		 var stare = data.message;
  	       		 if( stare == "0" || stare == 0 ){
  	       			alert("编辑成功"); 
  	       			window.location.href="list.shtml";
  	       		 }else if(stare == "1" || stare == 1){
  	       		 	alert("标题不能为空!");
  	       		 }else if(stare == "2" || stare == 2){
  	       		 	alert("内容不能为空!");
  	       		 }else if(stare == "3" || stare == 3){
  	       			alert("点击数必须大于0");
  	       		 }
  	       		 else if(stare == "5" || stare == 5){
       				alert("项目报错!");
       			 }
  	       	 } 
  	        });	
  	    })
    </script>
</body>
</html>
    