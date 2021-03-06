<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@taglib uri="/ruiec-tags" prefix="ruiec"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="renderer" content="webkit|ie-comp|ie-stand" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0,user-scalable=no" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <base href="${base}" />
    <title>回复管理</title>
    <script type="text/javascript">
    	var adminMenuData='/admin/common/nav.shtml';
    </script>
    <link media="all" type="text/css" rel="stylesheet" href="/resources/admin/skin/style.css" />
    <link media="all" type="text/css" rel="stylesheet" href="/resources/admin/script/artdialog/ui-dialog.css" />
    <link media="all" type="text/css" rel="stylesheet" href="/resources/admin/script/wangEditor/dist/css/wangEditor.min.css" />
    <script type="text/javascript" src="/resources/admin/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="/resources/admin/script/layer/layer.js"></script>
    <script type="text/javascript" src="/resources/admin/script/webuploader/webuploader.min.js"></script>
    <script type="text/javascript" src="/resources/admin/js/uploader.js"></script>
    <script type="text/javascript" src="/resources/admin/js/laymain.js"></script>
    <script type="text/javascript" src="/resources/admin/js/layindex.js"></script>
    <script type="text/javascript" src="/resources/admin/script/common.js"></script>
    <script type="text/javascript" src="/resources/admin/js/common.js"></script>
    <script type="text/javascript" src="/resources/admin/script/artdialog/dialog-min.js"></script>
    <script type="text/javascript" src="/resources/admin/script/artdialog/dialog-plus-min.js"></script>
    <script type="text/javascript" src="/resources/admin/script/wangEditor/dist/js/wangEditor.min.js"></script>
    <script type="text/javascript" src="/resources/admin/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
</head>
<body class="mainbody">
    <form method="post" action="list.shtml" accept-charset="UTF-8" id="list_form">
    <input name="_token" type="hidden" value="iFzn6pfKbyNzYFcc8udsxhIV0o4A5X7FOrwwwqIV" />
    <!--导航栏-->
    <div class="location">
        <a href="javascript:history.back(-1);" class="back"><i></i><span>返回上一页</span></a>
        <a href="../common/center.shtml" class="home"><i></i><span>首页</span></a>
        <i class="arrow"></i>
        <span>帖子管理</span>
    </div>
    <!--/导航栏-->

    <!--工具栏-->
    <div id="floatHead" class="toolbar-wrap">
        <div class="toolbar">
            <div class="box-wrap">
                <a class="menu-btn"></a>
                <div class="l-list">
                    <ul class="icon-list">
                        <li><a class="all" href="javascript:;" onclick="checkAll(this);"><i></i><span>全选</span></a></li>
                        <li onclick="del();"><a href="javascript:void(0);" class="del btndel" ><i></i><span>删除</span></a></li>
                    </ul>
                    
                </div>
                
                        <div class="r-list">
					<div class="rule-single-select single-select"><div class="boxwrap"><a class="select-tit" href="javascript:;"><span>全部
					</span><i></i></a><div class="select-items"><ul><li class="selected">全部</li><li>账号</li><li>IP</li></ul></div>
					<i class="arrow"></i></div>
                            <select name="selectWho" id="band_list" class="keyword" style="display: none;">
									<option value="" >请选择搜索条件</option>
                					<option value="1"<c:if test="${selectWho==1}">selected="selected"</c:if> >标题</option>
                					<option value="2"<c:if test="${selectWho==2}">selected="selected"</c:if> >内容</option>
                					<option value="3"<c:if test="${selectWho==3}">selected="selected"</c:if> >部门</option>
                					<option value="4"<c:if test="${selectWho==4}">selected="selected"</c:if> >回复人</option>
							</select>
                     </div>
                            <input name="selectContent" type="text" id="txtKeywords" class="keyword" value="${selectContent}" placeholder="请输入" />
                            <a id="lbtnSearch" class="btn-search" href="javascript:__doPostBack('lbtnSearch','')" onclick="document.getElementById('list_form').submit();">查询</a>
                        </div>
            </div>
        </div>
    </div>
    <!--/工具栏-->

    <!--列表-->
    <div class="table-container">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="ltable table">
        	<colgroup>
               	 <col width="10%" />
               	 <col width="17%" />
               	 <col width="26%" />
               	 <col width="17%" />
               	 <col width="10%" />
               	 <col width="10%" />
               	 <col width="10%" />
             </colgroup>
            <tr>
                <th>选择</th>
                <th  align="left" >回复帖子标题</th>  
		        <th  align="left" >回复内容</th>
		        <th>回复时间</th>
		        <th>回复人</th>
		        <th>所在部门</th>
		        <th>操作</th>      
            </tr>
               <c:forEach var="articleReplay" items="${page.list}" varStatus="i">
              <tr>
                    <td align="center">
                        <span class="checkall" style="vertical-align:middle;">
                            <input class="checker" type="checkbox" name="ids" value="${articleReplay.id }" />
                        </span>
                    </td>
                    <td align="left" >${articleReplay.title}</td>
                    <td align="left"><div class="replyContent aim">${articleReplay.content}</div></td>
                    <td align="center"><fmt:formatDate value="${articleReplay.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                    <td align="center">${articleReplay.username}</td>
                    <td align="center">${articleReplay.name}</td>
                    <td align="center">
						<c:if test="${ArticleIdArticleReply.get(i.index)>0}">
						 <a href="list.shtml?id=${articleReplay.id}">子类</a>
						</c:if>                    	 
                   		 <a href="edit.shtml?id=${articleReplay.id}">编辑</a>
                    </td>
                </tr>
            </c:forEach> 
		</table>
    </div>
    <!--/列表-->
    <jsp:include page="../common/page.jsp" />
    </form>


 <script type="text/javascript">
 	/* $(".aim").mouseover(function(){
 		$(this).removeClass("replyContent");
 	}).mouseout(function(){
 		$(this).addClass("replyContent");
 	}); */
 	
 </script> 
    
    
    
</body>
</html>
    