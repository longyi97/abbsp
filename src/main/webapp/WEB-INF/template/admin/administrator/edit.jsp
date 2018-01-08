<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="renderer" content="webkit|ie-comp|ie-stand" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0,user-scalable=no" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <base href="${base}" />
    <title>管理员编辑</title>
    <script type="text/javascript">
		var adminMenuData='/admin/common/nav.shtml';
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
    <form method="post" action="update.shtml" accept-charset="UTF-8" id="form1">
    <input name="_token" type="hidden" value="iFzn6pfKbyNzYFcc8udsxhIV0o4A5X7FOrwwwqIV" />
        <!--导航栏-->
        <div class="location">
            <a href="javascript:history.back(-1);" class="back"><i></i><span>返回上一页</span></a>
            <a href="../common/center.shtml" class="home"><i></i><span>首页</span></a>
            <i class="arrow"></i>
            <a href="list.shtml"><span>管理员管理</span></a>
            <i class="arrow"></i>
            <span>管理员编辑</span>
        </div>
        <div class="line10"></div>
        <!--/导航栏-->

        <!--内容-->
        <div id="floatHead" class="content-tab-wrap">
            <div class="content-tab">
                <div class="content-tab-ul-wrap">
                    <ul>
                        <li><a class="selected" href="javascript:;">管理员编辑</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="tab-content">
        <%--     <dl>
                <dt>管理员用户名</dt>
                <dd>
                    <input class="input normal" datatype="*" name="username" type="text" value="${admin.username}" />
                    <span class="Validform_checktip">*</span>
                    <input type="hidden" name="id" value="${admin.id }" />
                </dd>
            </dl> --%>
            <dl>
                <dt>管理员密码</dt>
                <dd>
                    <input class="input normal" datatype="*" name="password" type="password"  autocomplete='off' />
                    <span class="Validform_checktip">*</span>
                </dd>
            </dl>
        <%--     <dl>
                <dt>手机号</dt>
                <dd>
                    <input class="input normal" datatype="*" name="mobile" type="text" value="${admin.mobile}" />
                    <span class="Validform_checktip">*</span>
                </dd>
            </dl> --%>
        </div>
        <!--/内容-->
        <!--工具栏-->
        <div class="page-footer">
            <div class="btn-wrap">
                <input class="btn" type="submit" value="提交保存" />
                <input class="btn yellow" onclick="javascript:history.back(-1);" type="submit" value="返回上一页" />
            </div>
        </div>
        <!--/工具栏-->
    </form>
    <script type="text/javascript">
        $(function () {
            $('#form1').initValidform();
        })
    </script>
</body>
</html>
    