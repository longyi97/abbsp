<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="/ruiec-tags" prefix="ruiec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
		<title>发帖</title>
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1,maximum-scale=1, user-scalable=no">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<link rel="stylesheet" href="/resources/home/css/common.css" />
		<link rel="stylesheet" href="/resources/home/css/member.css" />
		<link rel="stylesheet" href="/resources/home/css/style.css" />
		<link rel="stylesheet" href="/resources/home/iconfont/iconfont.css" />
		<link rel="stylesheet" href="/resources/home/css/bootstrap.css" />
		<link rel="stylesheet" href="/resources/home/js/ueditor/third-party/codemirror/codemirror.css" />
		<script type="text/javascript" src="/resources/home/js/jquery-1.9.1.min.js" ></script>
		<script type="text/javascript" src="/resources/home/js/layer.js" ></script>
		<script type="text/javascript" src="/resources/admin/script/jquery/Validform_v5.3.2_min.js"></script>
		<script type="text/javascript" src="/resources/admin/script/webuploader/webuploader.min.js"></script>
    	<script type="text/javascript" src="/resources/admin/js/uploader.js"></script>
     	<script type="text/javascript" src="/resources/admin/script/common.js"></script>
     	<script type="text/javascript" src="/resources/home/js/wangEditor/wangEditor.min.js"></script>
	</head>
	<body class="body">
		<!--头部-->
		<div class="header">
			  <%@ include file="../common/index-top.jsp"%>
		</div>
		<!--头部-->
		<!--中间-->
			<%@ include file="../common/index-leftMenu.jsp"%>
			<!--右边-->
			<!--1200内容-->
			<div class="content-con">
				<div class="w1200">
					<div class="cont-120">	
						<div class="active-coner addArt">
							<div class="yuio">
								<p class="fl blod">发表帖子</p>
								<a href="javascript:history.back(-1);" class="fr">返回上一页</a>
							</div>
							<div class="con-from">
								<form method="post" action="save.shtml" accept-charset="UTF-8" id="form1">
									<dl>
										<dt>文章标题：</dt>
										<dd><input id="title" name="title" type="text" class="title" datatype="*1-20" nullmsg="标题不能为空" errormsg="标题长度不能超过20"></dd>
									</dl>
									<dl>
										<dt>发往版块：</dt>
										<dd id="category">
											<!-- 版块下拉框 -->
										</dd>
									</dl>
									<dl>
										<dt>发帖主题：</dt>
										<dd>
											<select name="articleCategoryId" id="articleCategory" datatype="*" nullmsg="主题不能为空，请联系管理员">
												<!-- 主题选项 -->
											</select>
										</dd>
									</dl>
									<dl>
										<dt>附件：</dt>
										<dd>
											<div class="addAttachment">
												<a href="javascript:void(0)" id="uploaderFile">
													<i class="iconfont">&#xe601;</i>
													<em>上传附件</em>
												</a>
												<ul class="fileType">
													<!-- 附件展示 -->
												</ul>
											</div>
										</dd>
									</dl>
									<dl>
										<dt>正文内容：</dt>
										<dd>
											<div id="editor"></div>
										</dd>
									</dl>
									<dl>
										<dt>&nbsp;</dt>
										<dd>
								   			<input type="submit" value="发表" class="addSubBtn">
											<input type="button" value="取消" class="addCan" onclick="javascript:history.back(-1);">
										</dd>
									</dl>
									<input id="content" type="hidden" name="content">
								</form>							
							</div>
						</div>
					</div>
				</div>
			</div>
			<!--1200内容-->

		<!--中间-->
	<script type="text/javascript">
		// 修改默认alert为layer.alert
		var alert = layer.alert;
		
		var E = window.wangEditor;
		var editor = new E('#editor');
		editor.customConfig.uploadImgServer = '/uploadWangEditor.jsp';
		editor.customConfig.uploadImgShowBase64 = true; 
	    editor.customConfig.pasteFilterStyle = true;
		editor.create();
		
		/* 上传插件 */
	 	var webuploader = WebUploader.create({
	 		swf:"/resources/admin/script/webuploader/uploader.swf",
	 		server:"/fileUploadAnyFile.jsp",
	 		auto:true,
	 		pick:"#uploaderFile",
	 		duplicate:true 
	 	}); 
	 	
	 	webuploader.on("uploadSuccess",function(file,data){
	 		if (data.error == 0) {
	 			var value = data.url+";"+data.fileName;
	 			$(".fileType").append('<li class="txt"><div class="delFile">X</div></li>');
	 			$(".fileType").append('<input class="attachments" type="hidden" name="attachments" value="'+value+'"/>');
	 			layer.msg('上传成功！', {icon: 6}); 
	         } else {
	         	layer.msg('上传失败！', {icon: 5}); 
	         }
	 	});
	 	
	 	webuploader.on("uploadError",function(){
	 		console.log("error");
	 	}); 
		/* 上传插件end */
				
	    //上传附件部分，鼠标移上和点击事件
	    $(".fileType").on("mouseover","li",function(){
	    	$(this).find(".delFile").css("display","block");
	    }).on("mouseout","li",function(){
	    	$(this).find(".delFile").css("display","none");
	    }).on("click","li",function(){
	    	$(this).next("input.attachments").remove();
	    	$(this).remove();
		})
		
		$(function () {
			
            $("#form1").Validform({
                tiptype:function(msg,o){
                    // 只有在验证失败的时候，才会弹出相关的信息
                    if (o.type == 3){
                        layer.msg(msg);
                	}
                },
                callback:function(){
                	var title = $("#title").val();
          	    	var editorText = editor.txt.html();
          	    	// 内容为空判断
          	    	if (editorText.trim().replace(/&nbsp;|\s|<\/?((?!img).)*?\/?>/g,"")=="") {
          	    		layer.msg("内容不能为空!");
//           	    		return false;
					}
          	    	var articleCategory = $("#articleCategory").val();
          	    	var subModules = $("#subModules").val();
          	    	var attachments = [];
          	    	// 获取附件数组
          	    	$(".attachments").each(function() {
          	    		attachments.push($(this).val());
					});

					$.ajax({
          	    		async:false, 
          	    		type:"POST",
          	    		traditional: true,
          	    		url:"save.shtml",
             	       	data:{ 
							"title":title,
							"content":editorText,
							"moduleId":subModules,
							"articleCategoryId":articleCategory,
							"attachments":attachments
             	       	},
             	       	success:function(data){
	             	       	if( data.message == "success"){
	             	       		$(".btnSub").attr("disabled","disabled");
	             	       		layer.msg('发帖成功！', {icon: 6, time: 1000}, function(){location.href= data.url;}); 
	             	       	} else if(data.message){
	          	       			layer.msg(data.message);
	             	       	} else{
	              	       	 	layer.alert("发帖出错了，请刷新后重试");
	                 		} 
             	       	}
          	        });
          	    	
          	    	// 阻止表单提交
          	    	return false;
                }
            });
            
            // AJAX加载版块
        	$.ajax({
	            url: "/home/article/getAllModulesLayer.shtml",
	            dataType:"json",
	            type:"POST",
	        	success:function (data) {
	            	loadData(data); 
	        	},
	        	error:function(er){
	                console.log(er);
	            }
        	})
        	
		});
		
		// 加载版块
		function loadData(data){
			// 清除旧select状态
			var currSelect = document.getElementById("subModules");
			if(currSelect) {
				currSelect.removeAttribute("id");
				currSelect.removeAttribute("name");
				currSelect.removeAttribute("datatype");
			}
			
    		//创建一个select对象
			var select=document.createElement("select");
   			select.setAttribute("id", "subModules");
   			select.setAttribute("name", "moduleId");
   			select.setAttribute("datatype", "*");
   			select.setAttribute("nullmsg", "版块不能为空，请联系管理员");
    		//遍历data中每个类别对象
    		for(var i=0;i<data.length;i++){
    			// 每遍历一个，就要在select中追加一个option，内容为当前对象的name属性，值为当前对象的id属性
    			select.add(new Option(data[i].name,data[i].id));
    		}
    		document.getElementById("category").appendChild(select);
    		
    		//为新创建的select，添加onchange事件
    		select.onchange=function(){
    			//将当前select后所有元素都删除
    			var parent=this.parentNode;
    			while(parent.lastChild!=this){
    				parent.removeChild(parent.lastChild);
    			}
    			this.setAttribute("id", "subModules");
       			this.setAttribute("name", "moduleId");
       			this.setAttribute("datatype", "*");
    			//获得和选中项位置对应的类别子对象
    			var category=data[this.selectedIndex];
    			if(category.subModules){
    				loadData(category.subModules);
    			} else {
    				getArticleCategories();
				}
    		}
    		
    		// 如果第一个选项有子级，初始化子级；否则初始化主题
    		if(data[0].subModules){
				loadData(data[0].subModules);
			} else {
				getArticleCategories();
			}
		}
		
		// AJAX获取主题
  		function getArticleCategories() {
          	var moduleId = $("#subModules").val();
          	
			$.ajax({
				data:{"moduleId":moduleId},						
				url:"getArticleCategories.shtml",
				dataType:"json",
				error:function(xhr,status,error) {
   					layer.alert("获取主题失败，请重试");
	   			},
	   			success:function (data) {
	   				var articleCategories = data.data;
	  					$("#articleCategory").empty();
	   				for (var i = 0; i < articleCategories.length; i++) {
	   					$("#articleCategory").append("<option value='"+articleCategories[i].id+"'>"+articleCategories[i].name+"</option>");
	   				}
	   			}
			});
		}
		
	</script>
	</body>
</html>
