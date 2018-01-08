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
    <title>用户签到明细管理</title>
    <script type="text/javascript">
		var adminMenuData='/admin/common/nav.shtml';
    </script>
    <link media="all" type="text/css" rel="stylesheet" href="/resources/admin/skin/style.css" />
    <link media="all" type="text/css" rel="stylesheet" href="/resources/admin/script/artdialog/ui-dialog.css" />
    <script type="text/javascript" src="/resources/admin/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="/resources/admin/script/jquery/Validform_v5.3.2_min.js"></script>
    <script type="text/javascript" src="/resources/admin/script/common.js"></script>
    <script type="text/javascript" src="/resources/admin/js/common.js"></script>
    <script type="text/javascript" src="/resources/admin/script/artdialog/dialog-min.js"></script>
    <script type="text/javascript" src="/resources/admin/script/artdialog/dialog-plus-min.js"></script>
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
        <span>用户签到明细管理</span>
    </div>
    <!--/导航栏-->

    <!--工具栏-->
    <div id="floatHead" class="toolbar-wrap">
        <div class="toolbar">
            <div class="box-wrap">
                <a class="menu-btn"></a>
                    <div class="l-list">
                </div>
                 <div class="r-list">
				<%-- 	<div class="rule-single-select single-select"><div class="boxwrap"><a class="select-tit" href="javascript:;"><span>全部
					</span><i></i></a><div class="select-items"><ul><li class="selected">全部</li><li>账号</li><li>IP</li></ul></div>
					<i class="arrow"></i></div>
                            <select name="selectWho" id="band_list" class="keyword" style="display: none;">
                            		<option value="" >请选择条件</option>
                					<option value="1"<c:if test="${selectWho==1}">selected="selected"</c:if> >总签到次数</option>
                					<option value="2"<c:if test="${selectWho==2}">selected="selected"</c:if> >连续签到次数</option>
							</select>
                            </div>
                    <div class="rule-single-select single-select"><div class="boxwrap"><a class="select-tit" href="javascript:;"><span>全部
					</span><i></i></a><div class="select-items"><ul><li class="selected">全部</li><li>账号</li><li>IP</li></ul></div>
					<i class="arrow"></i></div>
                            <select name="selectSort" id="band_list2" class="keyword" style="display: none;">
									<option value="" >请选择排序</option>
									<option value="3"<c:if test="${selectSort==3}">selected="selected"</c:if> >正序</option>
                					<option value="6"<c:if test="${selectSort==6}">selected="selected"</c:if> >倒叙</option>
							</select>
                            </div> --%>
                            <input name="username" type="text" id="txtKeywords" class="keyword" value="${userSignDetail.username }" placeholder="请输入用户名" />
                            <a id="lbtnSearch" class="btn-search" href="javascript:__doPostBack('lbtnSearch','')" onclick="document.getElementById('list_form').submit();">查询</a>
                        </div>
            </div>
        </div>
    </div>

    <!--列表-->
    <div class="table-container">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="ltable table">
        	<colgroup>
               	 <col width="30%" />
               	 <col width="20%" />
               	 <col width="15%" />
               	 <col width="15%" />
               	 <col width="20%" />
             </colgroup>
            <tr>
                <th align="left" class="paddingLeft">用户名</th>
                <th>签到时间</th>
                <th>总签到次数</th>
                <th>连续签到次数</th>
                <th>创建时间</th>
            </tr>
            <c:forEach var="list" items="${page.list}" varStatus="i">
              <tr>
                    <td class="paddingLeft">${list.username}</td>
                    <td align="center"><fmt:formatDate value="${list.lastSignTime}" pattern="yyyy-MM-dd"/></td>
                    <td align="center">${list.signCount}</td>
                    <td align="center">${list.signContinuousCount}</td>
                    <td align="center"><fmt:formatDate value="${list.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                </tr>
            </c:forEach>
                    </table>
    </div>
    <!--/列表-->
    <div class="u_page page pageBar">
	    <jsp:include page="../common/page.jsp" />
    </div>
    </form>
</body>
</html>
    