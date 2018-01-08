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
    	<input name="_token" type="hidden" value="iFzn6pfKbyNzYFcc8udsxhIV0o4A5X7FOrwwwqIV" />
        <!--导航栏-->
        <div class="location">
            <a href="javascript:history.back(-1);" class="back"><i></i><span>返回上一页</span></a>
            <a href="../common/center.shtml" class="home"><i></i><span>首页</span></a>
            <i class="arrow"></i>
            <a href="list.shtml"><span>回复管理</span></a>
            <i class="arrow"></i>
            <span>编辑回复</span>
        </div>
        <div class="line10"></div>
        <!--/导航栏-->

        <!--内容-->
        <div id="floatHead" class="content-tab-wrap">
            <div class="content-tab">
                <div class="content-tab-ul-wrap">
                    <ul>
                        <li><a class="selected" href="javascript:;">回复信息</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="tab-content">
        	<input type="hidden" value="${articleReply.id }" name="id" id="id"/>
            <dl>
		       <dt>回复帖子名称：</dt>
                 <dd>
	                <td>
						${article.title}                    	
					</td>
       			</dd>
            </dl>
            <dl>
                <dt>回复内容</dt>
                <dd>
                    <dd>
                    	<script id="editor" name="content" type="text/plain" style="width:700px;height: 300px">${articleReply.content}</script>
					</dd>
                    
                </dd>
            </dl>
             <dl>
                <dt>回复时间:</dt>
                <td>
                	<td>
                    	<fmt:formatDate value="${articleReply.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
             		</td>
               <td>
            </dl>
            <dl>
                <dt>回帖人:</dt>
                <td>
                    <ruiec:user var="r" userId="${articleReply.userId}">${r.username}</ruiec:user>
                <td>
            </dl>
        </div>
        <!--/内容-->
        <!--工具栏-->
        <div class="page-footer">
            <div class="btn-wrap">
            	<input type="button" value="提交保存" class="btn" id="secBtn">
               <!--  <input class="btn" type="submit" value="提交保存" /> -->
                <input class="btn yellow" onclick="javascript:history.back(-1);" type="submit" value="返回上一页" />
            </div>
        </div>
        <!--/工具栏-->
   <!--  </form> -->
    <script type="text/javascript">
	    $(function () {
	        $('#form1').initValidform();
	        var ue = UE.getEditor('editor',{elementPathEnabled : false});
	    })
	    
	    
	    $("#secBtn").click(function(){
  	    	/* var articleId = $("#id").val(); */
  	    	var replyId = $("#id").val();
  	    	var arr = [];
  	        arr.push(UE.getEditor('editor').getContent());
  	        if(arr[0].length>10000){
        	  alert("内容字数不能超过一万");
              return;
            }
  	    	$.ajax({
  	       	 type:"POST",
  	       	 url:"update.shtml",
  	       	 data:{ 
  	       		 "id":replyId,
  	       	     "content":arr[0],
  	       	 },
  	       	 success:function(data){
  	       		 var stare = data.message;
  	       		 if( stare == "0" || stare == 0 ){
  	       			alert("编辑成功"); 
  	       			window.location.href="list.shtml";
  	       		 }else if(stare == "1" || stare == 1){
  	       		 	alert("内容不能为空!");
  	       		 }else if(stare == "5" || stare == 5){
       				alert("项目报错!");
       			 }
  	       	 } 
  	        });	
  	    })
	    
    </script>
</body>
</html>