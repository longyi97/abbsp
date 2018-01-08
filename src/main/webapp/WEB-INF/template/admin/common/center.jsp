<%@ page import="java.net.*" %>
<%@ page import="java.util.Properties" %>
<%@ page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="renderer" content="webkit|ie-comp|ie-stand" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0,user-scalable=no" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <base href="${base}" />
    <title>管理首页</title>
    <script type="text/javascript">
    	var adminMenuData='nav.shtml';
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

<%
    //net info
    InetAddress inetAddress = InetAddress.getLocalHost();
    //system info
    Properties properties = System.getProperties();
    //database info
    DatabaseMetaData databaseMetaData;
    String databaseType = "";
    String databaseName = "";
    try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.20.212:3306/ruiecfivedb", "root", "123456");
        databaseMetaData = connection.getMetaData();
        databaseType = databaseMetaData.getDatabaseProductName();
/*         ResultSet resultSet = databaseMetaData.getTables("ruiecfivedb","",null,null);
        if (resultSet.next()){
        	databaseName = resultSet.getString(1); 
        } 
        */
        databaseName = "ruiecbbs";
    } catch (Exception e) {
        e.printStackTrace();
    }
%>
    <!--导航栏-->
    <div class="location">
        <a href="javascript:history.back(-1);" class="back"><i></i><span>返回上一页</span></a>
        <a href="/admin/common/center.shtml" class="home"><i></i><span>首页</span></a>
        <i class="arrow"></i>
        <span>管理中心</span>
    </div>
    <!--/导航栏-->
    <!--内容-->
    <div class="line10"></div>
        <div class="nlist-1">
        <ul>
            <li>本次登录IP：<%=request.getRemoteAddr()%></li>
            <li>本次登录计算机名：<%=inetAddress.getHostName()%></li>
        </ul>
    </div>
    <div class="line10"></div>
    <div class="nlist-2">
        <h3><i></i>机器参数</h3>
        <ul>
            <li>系统根目录地址：<%=request.getServletContext().getRealPath("/")%></li>
            <li>服务器IP：<%=inetAddress.getHostAddress()%></li>
            <li>Java版本：<%=properties.getProperty("java.version")%></li>
            <li>操作系统：<%=properties.getProperty("os.name")%></li>
            <li>数据库类型：<%=databaseType%></li>
            <li>数据库名称：<%=databaseName%></li>
        </ul>
    </div>
    <div class="line20"></div>
    <div class="nlist-2">
        <h3><i class="site"></i>建站三步曲</h3>
        <ul>
            <li>无</li>
        </ul>
        <h3><i class="msg"></i>最新消息</h3>
        <ul>
            <li>无</li>
        </ul>
    </div>
    <!--/内容-->
<script language="javascript" type="text/javascript">
    //禁用Enter键表单自动提交
    document.onkeydown = function(event) {
        var target, code, tag;
        if (!event) {
            event = window.event; //针对ie浏览器
            target = event.srcElement;
            code = event.keyCode;
            if (code == 13) {
                tag = target.tagName;
                if (tag == "TEXTAREA") { return true; }
                else { return false; }
            }
        }
        else {
            target = event.target; //针对遵循w3c标准的浏览器，如Firefox
            code = event.keyCode;
            if (code == 13) {
                tag = target.tagName;
                if (tag == "INPUT") { return false; }
                else { return true; }
            }
        }
    };
</script>
</body>
</html>
    