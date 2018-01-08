<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="/ruiec-tags" prefix="ruiec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>回复用户</title>
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1,maximum-scale=1, user-scalable=no">
		<link rel="stylesheet" href="/resources/home/css/common.css" />
		<link rel="stylesheet" href="/resources/home/css/member.css" />
		<link rel="stylesheet" href="/resources/home/css/style.css" />
		<link rel="stylesheet" href="/resources/home/iconfont/iconfont.css" />
		<link rel="stylesheet" href="/resources/home/css/bootstrap.css" />
		<script type="text/javascript" src="/resources/home/js/jquery-1.9.1.min.js" ></script>

		<script type="text/javascript" src="/resources/home/js/layer.js" ></script>
     	<script type="text/javascript" src="/resources/admin/script/common.js"></script>
		<script type="text/javascript" src="/resources/home/js/wangEditor/wangEditor.min.js"></script>
	</head>
<body>
			<input name="replyArticleId" type="hidden" value="${id}" id="replyArticleId"/>
			<input name="articleId" type="hidden" value="${articleId}" id="articleId"/>
			<dl style="margin-bottom:10px">
				<dd>
					<div id="editor" style="width:680px;margin-left: 10px;margin-top:20px;"></div>
				</dd>
			</dl>
	
			<dl style="margin-bottom:0">
				<dd>
					<input type="button" value="发表" class="ativop curoop" id="secBtn" style="margin-left: 20px;width:50px;height:30px;line-height:30px;background:red;color:#fff">
					<input type="button" value="取消" class="ativop" onclick="javascript:history.back(-1);" style="margin-left: 20px;width:50px;height:30px;line-height:30px;background:#ccc;color:#000">
				</dd>
			</dl>
			<input id="content" type="hidden" name="content">
</body>
	 <script type="text/javascript">
		 var E = window.wangEditor;
	     var editor = new E('#editor');
	     editor.customConfig.uploadImgServer = '/uploadWangEditor.jsp';
	     editor.customConfig.pasteFilterStyle = true;
	     editor.customConfig.uploadImgShowBase64 = true;
	     editor.create();
	 $(function () {
         $('#form1').initValidform();
         
     })
 		
 	     //回复当前帖子
 	    $("#secBtn").click(function(){
 	    	var replyArticleId=$("#replyArticleId").val();
			var articleId=$("#articleId").val();
			var editorText = editor.txt.html().replace(/&nbsp;/g,""); 
  	    	console.log(editor.txt.html());
 	    	$.ajax({
 	       	 type:"POST",
 	       	 url:"addsave1.shtml",
 	       	 data:{ 
 	       		"replyArticleId": replyArticleId,
				"articleId": articleId,
				"content":editorText
 	       	 },
 	       	 success:function(data){
 	       		 var stare = data;
 	       		 if( stare == "1" || stare == 1 ){
 	       			$(".ativop").attr("disabled","disabled");
 	       			layer.msg('回复成功', {icon: 6}); 
	 	       		setTimeout(function(){
	 	       			window.parent.parent.location.reload();
					},1000)
 	       		 }else{
 	       			//window.location.href= "/home/articleDetails/list.shtml?id="+TitID+"";
 	       			layer.msg('评论不能为空', {icon: 5});
 	       		 }
 	       	 } 
 	        });	
 	    })
         
        
        
    </script>
</html>