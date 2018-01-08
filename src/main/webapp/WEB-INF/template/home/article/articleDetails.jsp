<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="/ruiec-tags" prefix="ruiec"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta charset="utf-8" />
		<title>帖子详情</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1,maximum-scale=1, user-scalable=no">
		<link rel="stylesheet" href="/resources/home/css/common.css" />
		<link rel="stylesheet" href="/resources/home/css/member.css" />
		<link rel="stylesheet" href="/resources/home/css/style.css" />
		<link rel="stylesheet" href="/resources/home/iconfont/iconfont.css" />
		<link rel="stylesheet" href="/resources/home/css/bootstrap.css" />
		<link rel="stylesheet" href="/resources/home/layer/theme/default/layer.css" />
		<script type="text/javascript" src="/resources/home/js/jquery-1.9.1.min.js" ></script>
		<script type="text/javascript" src="/resources/home/js/index.js"></script>
		<script type="text/javascript" src="/resources/home/layer/layer.js"></script>	
		<script type="text/javascript" src="/resources/admin/script/common.js"></script> 
		<script type="text/javascript" src="/resources/admin/script/jquery/Validform_v5.3.2_min.js"></script>
		<script type="text/javascript" src="/resources/home/js/wangEditor/wangEditor.min.js"></script>
		<style>
			html,body{-ms-overflow-style:scrollbar;}
			/* table 样式 */
			table {border-top: 1px solid #ccc;border-left: 1px solid #ccc;}
			table td,table th {border-bottom: 1px solid #ccc;border-right: 1px solid #ccc;padding: 3px 5px;}
			table th {border-bottom: 2px solid #ccc;text-align: center;}	
			/* blockquote 样式 */
			blockquote {display: block;border-left: 8px solid #d0e5f2;padding: 5px 10px;margin: 10px 0;line-height: 1.4;font-size: 100%;background-color: #f1f1f1;}
			/* code 样式 */
			code {display: inline-block;*display: inline;*zoom: 1;background-color: #f1f1f1;border-radius: 3px;padding: 3px 5px;margin: 0 3px;}
			pre code {display: block;}
			
		</style>
	</head>
	<body class="body">
		<!--头部-->
	  <%@ include file="../common/index-top.jsp"%>
		<!--头部-->
		<!--中间-->
		<div class="tipContent">
			<%@ include file="../common/index-leftMenu.jsp"%>
		</div>
			<!--右边-->
			<!--内容-->
			<div class="content-con yibuhyu">
				<div class="xiug">
					<div class="opp">
						<a href="/home/index.shtml">内部测试论坛 </a>><a href="/home/article/list.shtml?moduleId=${article.moduleId }"><ruiec:module var="r" parentId="${article.moduleId}">${r.name}</ruiec:module></a>>${articleCategory.name}
					</div>
					<div class="tipBtn">
						<a href="/home/article/add.shtml" class="curr">我要发帖</a>
						<a href="/home/article/list.shtml?moduleId=${article.moduleId }">返回列表</a>
					</div>
					<div class="tieziTitle">
						<div class="tieziTitleL">
							<em>查看：<i>${article.hit}</i></em>
							<em>回帖：<i>${articleReplySize}</i></em>
						</div>
						<div class="tieziTitleR">
							<em class="wunai">${article.title}</em>
							 		    <c:if test="${article.isTop==true}">
			                            <i>【置顶】</i>
			                            </c:if>
			                             <c:if test="${article.isGood==true}">
										<i>【精华】</i>
										</c:if>
										 <c:if test="${article.isRecommend==true}">
										<i>【推荐】</i>
										</c:if>
										 <c:if test="${article.isHot==true}">
										<i>【火帖】</i>
										</c:if>
						</div>
					</div>
					<c:if test="${page.pageNumber eq 1 }">
					<div class="tiezi">
						<div class="yuihu fl">
							<dl>
								<dt><label><img src="${userImg.headImage}" width="100px" height="100px"></label></dt>
								<dd>
									<div class="yuio">
										<i class="iconfont">&#xe65f;</i><em style="color: #333;"><ruiec:user var="r" userId="${article.userId}">${r.username}</ruiec:user></em>								
									</div>
									<div class="inte">
										<p>积分：<i style="color: #da251d;">${userPoints.points}</i></p>
									</div>
									<span class="boyu">
										<em>性别：</em>	
										<i style="color: #666;"><c:if test="${articleUser.sex==true}">男</c:if><c:if test="${articleUser.sex==false}">女</c:if> </i>								
									</span>
									<span class="boyu">
										<em>发帖：</em>
										<i>${articleCount}</i>
									</span>
									<span class="boyu">
										<em>部门：</em>
										<i>
											<c:if test="${dep !=null}" >${dep}</c:if>
											<c:if test="${dep ==null}" >暂无部门</c:if>
										</i>
									</span>
									<span class="boyu">
										<em>生日：</em><i><fmt:formatDate value="${articleUser.birthday }" pattern="yyyy-MM-dd"/></i>
									</span>	 						
								</dd>
							</dl>
					    </div>
					    <div class="yuie-op">
						    <div class="fl liangbu">					    
								<div class="guanzhu">																												
									<span class="yuer yu-ll">
										<em>发表于：<fmt:formatDate value="${article.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></em>
									</span>							
									<div class="fenxiang fl"></div>
									<div class="chakn fr" style="margin-top: initial;">
									</div>
								</div>
							</div>
						    <div class="wenhznag">
						    	<p>${article.content}</p>
						    </div>
						    <c:if test="${articleAttachments.size() gt 0 }">
						    <div class="attachment">
							    <c:forEach items="${articleAttachments }" var="item">
								<dl>
									<dt><img src="/resources/home/images/fileName.png"/>${item.name }</dt>
									<dd>
										<a href="${item.url }">下载</a>
									</dd>
								</dl>
							    </c:forEach>
						    </div>
						    </c:if>
						    <div class="huifu">
						    	<span class="fr"></span>
						    </div>					    
					    </div>
					</div>
					</c:if>
											
				<form method="post" action="view.shtml" accept-charset="UTF-8" id="form1">
					<input name="id" type="hidden" value="${article.id}" />
					<c:forEach items="${page.list}" var="articleReply" varStatus="i">
						<div class="tiezi">
							<div class="yuihu fl">
								<dl>
									<dt><label><img src="${userList.get(i.index).headImage}" width="100px" height="100px"></label></dt>
									<dd>
										<div class="yuio">
											<em style="color: #333;">${userList.get(i.index).username}</em>								
										</div>
										<div class="inte">
											<p>积分：<i style="color: #da251d;">${points.get(i.index).points}</i></p>
										</div>
										<span class="boyu">
										<em>性别：</em>
										<i style="color: #666;"><c:if test="${userList.get(i.index).sex==true}">男</c:if><c:if test="${userList.get(i.index).sex==false}">女</c:if> </i>									
									</span> 
									<span class="boyu">
										<em>发帖：</em>
										<i>${articleCountList.get(i.index)}</i>
									</span>
									  <span class="boyu"><em>部门：</em><i>
											<c:if test="${departmentNamelist.get(i.index)!=null}">
												${departmentNamelist.get(i.index)}
											</c:if>
											<c:if test="${departmentNamelist.get(i.index)==null}">
												暂无部门
											</c:if>
										</i></span>
									<span class="boyu"><em>生日：</em><i>
									<fmt:formatDate value="${userList.get(i.index).birthday}" pattern="yyyy-MM-dd"/>
									</i></span>		
									</dd>
								</dl>
						    </div>
						    <div class="yuie-op">
						    	<div class="wetyq">
		                            <div class="zuihou">
		                            	<span class="fl" style="color: #666;font-size: 12px;">发表于：<fmt:formatDate value="${articleReply.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
		                            </div>
	                                <div class="wenyuk">
				                        <p style="color:#ccc">   
				                             <c:if test="${articleReply.replyArticleId!=0}">对：
									    			<ruiec:replyArticle var="r" replyArticleId="${articleReply.replyArticleId}">${r.content}</ruiec:replyArticle>
									    		回复：
									    	</c:if>	 
									    </p>
									    
	                                	<p>${articleReply.content}</p>
	                                </div>
	                                <div class="fr yyer">
	                                <!--判断如果是非空就不显示，还有判断是发帖用户 还需要判断是否是总经办的人 ↓↓↓↓↓↓↓↓-->
	                              	    <c:if test="${not empty sessionScope.session_user }">
	                              		<a href="javascript:void(0);" onclick="func7(${articleReply.id},${articleReply.articleId})">回复</a>
	                               	</c:if>
	                                <!--判断如果是非空就不显示，还有判断是发帖用户 还需要判断是否是总经办的↑↑↑↑↑↑↑↑↑↑-->
	                                </div>
	                            </div>
						    </div>
						</div>
					</c:forEach>
					<div class="tipBtn spBtn">
						<a href="/home/article/add.shtml" class="curr">我要发帖</a>
						<%@include file="../common/page.jsp"%>
						<a href="/home/article/list.shtml?moduleId=${article.moduleId }">返回列表</a>
					</div>
				</form>
					
					<div class="tiezi">
						<div class="yuihu fl">
							<img src="${sessionScope.session_user.headImage==null?'/resources/home/images/images6.png':sessionScope.session_user.headImage}" width="100px" height="100px">
					    </div>
					    <div class="yuie-op">	
							<div class="zuihou-list">
									<c:if test="${not empty sessionScope.session_user }">
											<input name="articleId" id="articleId" type="hidden" value="${article.id}" />
											<input name="article" id="id" type="hidden" value="${article.id}" />
											
											<div id="editor" style="width:1000px;"></div>
											<p class="ptm pnpost">
												<button type="submit" class="btnSub" id="secBtn">发表回复</button>
											</p>
									</c:if>
							</div>
						</div>
					</div>
					
				</div>																																					
			</div>			
			<!--内容-->
		
		<!--中间-->
		<!--侧边栏-->
		<!--侧边栏-->
	<div class="layer_notice" style="display:none;">
		123456
		<input name="replyArticleId" type="hidden" value="${id}" id="replyArticleId"/>
		<input name="articleId" type="hidden" value="${articleId}" id="articleId"/>
		
		<div class="box">
			<textarea id="editor_id2" name="content" style="width:1000px; height: 300px;" datatype="*"></textarea>
		</div>
		
		<dl style="margin-bottom:0">
		<dd>
			<input type="button" value="发表" class="ativop curoop" id="deferId" style="margin-left: 20px;width:50px;height:30px;line-height:30px;background:red;color:#fff">
			<input type="button" value="取消" class="ativop" onclick="cancle();" style="margin-left: 20px;width:50px;height:30px;line-height:30px;background:#ccc;color:#000">
		</dd>
		</dl>
		
	</div>	
	<script type="text/javascript">
	
	 var E = window.wangEditor;
     var editor = new E('#editor');
     editor.customConfig.uploadImgServer = '/uploadWangEditor.jsp'; 
     editor.customConfig.uploadImgShowBase64 = true; 
     editor.customConfig.pasteFilterStyle = true;
     editor.create();
     $("html").scrollTop("0");
     $(function () {
            $('#form1').initValidform();
        });
                                                                                           
		function deltiezi(id){
			if(confirm("确认删除此帖子吗?删除此贴将导致此贴所有回复消失")){
				var param={};
				 param.id=id;
				 $.ajax({
					   type: "POST",
					   url: "/home/articleDetails/delete.shtml",
					   data: param,
					   dataType: "json", 
					   async: false,
					   success: function(data){
						   window.location.href='/home/article/list.shtml';
					   }
				 });
			}
		}
        //回复弹出框
     function func7(id,articleId ) {
    	//页面层
  		layer.open({
  		type: 2,
  		area: ['700px', '450px'], //宽高
  		content: '/home/articleDetails/articleReplyInfo.shtml?id='+ id+'&articleId='+articleId
  		});
 	 }
     function slideUp(){
    	 $("html,body").animate({scrollTop:0}, 500);
     }
     function slideDown() {  
    	 $("html,body").animate({scrollTop:document.documentElement.scrollHeight-document.documentElement.clientHeight}, 500);
    	 }    
     //回复当前帖子
    $("#secBtn").click(function(){
    	var TitID = $("#articleId").val();
    	var tearName = $("#editor_id").attr("name");
        var editorText = editor.txt.html().replace(/&nbsp;/g,""); 

    	$.ajax({
       	 type:"POST",
       	 url:"addsave1.shtml",
       	 data:{ 
       		 "articleId":TitID,
       		 "name":tearName, 
       		 "content":editorText,
       	 },
       	 success:function(data){
       		 var stare = data;
       		 if(stare == "3" || stare == 3){
       			window.location.href= "/home/view.shtml";
       		 }
       		 if( stare == "1" || stare == 1 ){
       			$(".btnSub").attr("disabled","disabled");
       			layer.msg('回复成功', {icon: 6}); 
       			setTimeout(function(){
       				window.location.href= "/home/articleDetails/view.shtml?id="+TitID+"";
				},1000);
       		 }else if(stare == "0" || stare == 0){
       			//window.location.href= "/home/articleDetails/list.shtml?id="+TitID+"";
       		 	layer.msg('评论不能为空', {icon: 5});
       		 }else{
       			window.location.href= "/home/view.shtml";
       		 }
       	 } 
        });	
    })  
	</script>
	</body>
</html>