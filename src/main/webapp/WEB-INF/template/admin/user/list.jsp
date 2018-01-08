<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="renderer" content="webkit|ie-comp|ie-stand" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0,user-scalable=no" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <base href="${base}" />
    <title>用户管理</title>
    <script type="text/javascript">
		var adminMenuData='/admin/common/nav.shtml';
    </script>
    <link media="all" type="text/css" rel="stylesheet" href="/resources/admin/skin/style.css" />
    <link media="all" type="text/css" rel="stylesheet" href="/resources/admin/script/artdialog/ui-dialog.css" />
    <link media="all" type="text/css" rel="stylesheet" href="/resources/admin/script/wangEditor/dist/css/wangEditor.min.css" />
    <script type="text/javascript" src="/resources/admin/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="/resources/admin/script/layer/layer.js"></script>
    <script type="text/javascript" src="/resources/admin/script/jquery/Validform_v5.3.2_min.js"></script>
    <script type="text/javascript" src="/resources/admin/script/webuploader/webuploader.min.js"></script>
    <script type="text/javascript" src="/resources/admin/js/uploader.js"></script>
    <script type="text/javascript" src="/resources/admin/js/laymain.js"></script>
    <script type="text/javascript" src="/resources/admin/js/layindex.js"></script>
    <script type="text/javascript" src="/resources/admin/script/common.js"></script>
    <script type="text/javascript" src="/resources/admin/js/common.js"></script>
    <script type="text/javascript" src="/resources/admin/script/artdialog/dialog-min.js"></script>
    <script type="text/javascript" src="/resources/admin/script/artdialog/dialog-plus-min.js"></script>
    <script type="text/javascript" src="/resources/admin/script/wangEditor/dist/js/wangEditor.min.js"></script>
    <script type="text/javascript" src="/resources/admin/js/search.js" ></script>
     <script type="text/javascript">
     function isDepartment(){
    	 var department=$.trim($("#departmentId").val());
    	 var param={};
    	 param.department=department;
    	 $.ajax({
    		   type: "POST",
    		   url: "${path}/admin/user/list.shtml",
    		   data: param,
    		   dataType:"json",
    		   async: false,
    		   success: function(data){
    			   window.location.href='${path}/admin/user/list.shtml';
    		   }
    	 });
    } 
     </script> 
</head>
<body class="mainbody">
    <!-- <form method="post" action="" accept-charset="UTF-8"> -->
    <form method="post" action="list.shtml" accept-charset="UTF-8"id="list_form">
    <input name="_token" type="hidden" value="iFzn6pfKbyNzYFcc8udsxhIV0o4A5X7FOrwwwqIV" />
    <!--导航栏-->
    <div class="location">
        <a href="javascript:history.back(-1);" class="back"><i></i><span>返回上一页</span></a>
        <a href="../common/center.shtml" class="home"><i></i><span>首页</span></a>
        <i class="arrow"></i>
        <span>用户管理</span>
    </div>
    <!--/导航栏-->

    <!--工具栏-->
    <div id="floatHead" class="toolbar-wrap">
        <div class="toolbar">
            <div class="box-wrap">
                <a class="menu-btn"></a>
                <div class="l-list">
                    <ul class="icon-list">
                        <li><a class="add" href="add.shtml"><i></i><span>新增</span></a></li>
                    <!--    <li><a class="all" href="javascript:;" onclick="checkAll(this);"><i></i><span>全选</span></a></li>
                       <li onclick="del();"><a href="javascript:void(0);" class="del btndel" ><i></i><span>删除</span></a></li> -->
                    </ul>
                     <%-- <ul class="icon-list" style="float: right;">
                     	<li>
                    		<select class="input normal" name="departmentId">
                    			<option  value="" >请选择部门</option>
                    			<c:forEach var="list" items="${allDepartment}">
                					<option value="${list.id }"<c:if test="${user.departmentId==list.id}">selected="selected"</c:if> >${list.name}</option>
                				</c:forEach>
                			</select>
                		</li>
                     	<li>
                     		<input type="text" class="input normal " name="username" value="${user.username }" placeholder="请输入用户名" />
                     		<a id="lbtnSearch" class="btnSub" href="javascript:__doPostBack('lbtnSearch','')">查询</a>
                     	</li>
                    </ul> --%>
                   
                </div>
                 <div class="r-list">
					<div class="rule-single-select single-select"><div class="boxwrap"><a class="select-tit" href="javascript:;"><span>全部
					</span><i></i></a><div class="select-items"><ul><li class="selected">全部</li><li>账号</li><li>IP</li></ul></div>
					<i class="arrow"></i></div>
                            <select name="isLocked" id="band_list" class="keyword" style="display: none;">
									<option  value="" >用户状态</option>
                					<option value="true"<c:if test="${user.isLocked==true}">selected="selected"</c:if> >用户已锁定</option>
                					<option value="false"<c:if test="${user.isLocked==false}">selected="selected"</c:if> >用户未锁定</option>
							</select>
                            </div>
                    <div class="rule-single-select single-select"><div class="boxwrap"><a class="select-tit" href="javascript:;"><span>全部
					</span><i></i></a><div class="select-items"><ul><li class="selected">全部</li><li>账号</li><li>IP</li></ul></div>
					<i class="arrow"></i></div>
                            <select name="departmentId" id="band_list2" class="keyword" style="display: none;">
									<option  value="" >请选择部门</option>
                    			<c:forEach var="list" items="${allDepartment}">
                					<option value="${list.id }"<c:if test="${user.departmentId==list.id}">selected="selected"</c:if> >${list.name}</option>
                				</c:forEach>
							</select>
                            </div>
                            <input name="username" type="text" id="txtKeywords" class="keyword" value="${user.username }" placeholder="请输入用户名" />
                            <a id="lbtnSearch" class="btn-search" href="javascript:__doPostBack('lbtnSearch','')" onclick="document.getElementById('list_form').submit();">查询</a>
                        </div>
            </div>
        </div>
    </div>

    <!--列表-->
    <div class="table-container">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="ltable table">
        	<colgroup>
               	 <col width="10%" />
               	 <col width="5%" />
               	 <col width="10%" />
               	 <col width="12%" />
               	 <col width="13%" />
               	 <col width="15%" />
               	 <col width="15%" />
               	 <col width="5%" />
               	 <col width="15%" />
             </colgroup>
            <tr>
               <!--  <th>选择</th> -->
                <th align="left" class="paddingLeft">用户名</th>
                <th>性别</th>
                <th align="left" class="paddingLeft">部门</th>
                <th>生日</th>
                <th>手机</th>
                <th>邮箱</th>
                <th>注册时间</th>
                <th>是否锁定</th>
                <th>操作</th>
            </tr>
            <c:forEach var="list" items="${page.list}" varStatus="i">
              <tr>
                    <td class="paddingLeft">${list.username}</td>
                    <td align="center"><c:if test="${list.sex==true}">男</c:if><c:if test="${list.sex==false}">女</c:if></td>
                    <td class="paddingLeft">
                    <c:if test="${null!=departmentNamelist.get(i.index)}">${departmentNamelist.get(i.index)}</c:if>
                    <c:if test="${null==departmentNamelist.get(i.index)}"></c:if>
                    </td>
                    <td align="center">
                    <c:if test="${null!=list.birthday}"><fmt:formatDate value="${list.birthday}" pattern="yyyy-MM-dd"/></c:if>
                    <c:if test="${null==list.birthday}"></c:if>
                   	</td>
                    <td align="center">${list.mobile}</td>
                    <td align="center">${list.email}</td>
                    <td align="center"><fmt:formatDate value="${list.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td align="center"><c:if test="${list.isLocked==true}">是</c:if><c:if test="${list.isLocked==false}">否</c:if></td>
                    <td align="center">
                        <a href="edit.shtml?id=${list.id}">编辑</a>
                        <a href="/admin/user/updatePwd.shtml?id=${list.id}">修改密码</a>
                    </td>
                </tr>
            </c:forEach>
                    </table>
    </div>
    <!--/列表-->
    <div class="u_page page pageBar">
	    <jsp:include page="../common/page.jsp" />
    </div>
    </form>
<script type="text/javascript">
$(function(){
	$(".span_department i").click(function(){
		//部门ID放入隐藏域
		$("#departmentId").val($(this).attr("departmentId"));
		$("form").submit();
	})
})
</script>
</body>
</html>
    