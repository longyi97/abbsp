<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0,user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <base href="${base}" />
    <title>后台管理中心</title>
    <script type="text/javascript">
        var adminMenuData='nav.shtml';
    </script>
    <link media="all" type="text/css" rel="stylesheet" href="/resources/admin/skin/style.css">
    <script type="text/javascript" src="/resources/admin/script/jquery/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="/resources/admin/script/jquery/jquery.nicescroll.js"></script>
    <script type="text/javascript" src="/resources/admin/js/layindex.js"></script>
    <script type="text/javascript" src="/resources/admin/script/layer/layer.js"></script>
    <script type="text/javascript" src="/resources/admin/script/common.js"></script>

</head>

<body class="indexbody">
<!--全局菜单-->
<a class="btn-paograms" onclick="togglePopMenu();"></a>
<div class="pop-menu" id="pop-menu">
    <div class="pop-box" style="width: 610px;">
        <h1 class="title"><i></i>导航菜单</h1>
        <i class="close" onclick="togglePopMenu();">关闭</i>
        <div class="list-box">
        </div>
    </div>
    <i class="arrow">箭头</i>
</div>
<!--/全局菜单-->

<div class="main-top">
    <a class="icon-menu"></a>
    <div class="main-nav" id="main-nav">
    </div>
    <div class="nav-right">
        <div class="info">
           <i></i> 
           <span> 您好，${session_admin_user.username}<br>
		  </span>
		</div>
        <div class="option">
            <i></i>
            <div class="drop-wrap">
                <div class="arrow"></div>
                <ul class="item">
                    <li>
                        <a href="/" target="_blank">预览网站</a>
                    </li>
                   <!--  <li>
                        <a href="background/clear">清空缓存</a>
                    </li>
                    <li>
                        <a href="" >初始化缓存</a>
                    </li>
                    <li>
                        <a href="background/center" target="mainframe">管理中心</a>
                    </li>-->
                    <li>
                        <a href="/admin/adminUser/edit.shtml?id=${sessionScope.session_admin_user.id}" target="mainframe">修改密码</a>
                    </li> 
                    <li>
                        <a href="/admin/login/out.shtml">注销登陆</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="main-left">
    <h1 class="logo">后台管理中心</h1>
    <div class="sidebar-nav" id="sidebar-nav">

    </div>
</div>
<div class="main-container">
    <iframe id="mainframe" name="mainframe" frameborder="0" src="center.shtml"></iframe>
</div>
</body>
</html>