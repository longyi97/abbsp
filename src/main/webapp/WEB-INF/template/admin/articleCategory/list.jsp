<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="renderer" content="webkit|ie-comp|ie-stand" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0,user-scalable=no" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <base href="${base}" />
    <title>主题管理</title>
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
</head>
<body class="mainbody">
    <!-- <form method="post" action="" accept-charset="UTF-8"> -->
    <form method="post" action="list.shtml" accept-charset="UTF-8" id="list_form">
    <input name="_token" type="hidden" value="iFzn6pfKbyNzYFcc8udsxhIV0o4A5X7FOrwwwqIV" />
    <!--导航栏-->
    <div class="location">
        <a href="javascript:history.back(-1);" class="back"><i></i><span>返回上一页</span></a>
        <a href="/admin/common/center" class="home"><i></i><span>首页</span></a>
        <i class="arrow"></i>
        <a href="/admin/module/list.shtml"><span>版块管理</span></a>
        <i class="arrow"></i>
        <span>主题管理</span>
    </div>
    <!--/导航栏-->

    <!--工具栏-->
    <div id="floatHead" class="toolbar-wrap">
        <div class="toolbar">
            <div class="box-wrap">
                <a class="menu-btn"></a>
                <div class="l-list">
                    <ul class="icon-list">
                        <li><a class="add" href="edit.shtml?moduleId=${moduleId}"><i></i><span>新增</span></a></li>
                       	<li><a class="all" href="javascript:;" onclick="checkAll(this);"><i></i><span>全选</span></a></li>
                        <li onclick="del();"><a href="javascript:void(0);" class="del btndel" ><i></i><span>删除</span></a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <!--列表-->
    <div class="table-container">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="ltable table">
            <tr>
                <th>选择</th>
                <th align="left">主题名称</th>
                <th align="left">排序</th>
                <th align="left">所属板块</th>
                <th>操作</th>
            </tr>
            <c:forEach var="list" items="${page.list}">
              <tr>
                    <td align="center">
                        <span class="checkall" style="vertical-align:middle;">
                            <input class="checker" type="checkbox" name="ids" value="${list.id }" />
                        </span>
                    </td>
                    <td>${list.name}</td>
                    <td>${list.sort}</td>
                    <td>${list.module.name}</td>
                    <td align="center">
                        <a href="edit.shtml?id=${list.id}&moduleId=${moduleId}">编辑</a>
                    </td>
                </tr>
            </c:forEach>
                    </table>
    </div>
    <!--/列表-->
    <div class="u_page page pageBar">
	    <jsp:include page="../common/page.jsp" />
    </div>
    
    <input id="name" type="hidden" name="moduleId" value="${moduleId }" />
    
    </form>
    
    <script type="text/javascript">
	//批量删除
	function del() {
		var $ids = $(".table input[name='ids']:enabled:checked");
		if ($ids.size() < 1) {
			alert('请选择要删除的选项');
			return;
		}
		if (confirm("是否将此信息删除?")) {
			$.ajax({
				type : "post",
				url : "delete.shtml",
				data : $ids.serialize(),
				dataType : "json",
				success : function(data) {
					if (data.type == "info") {
						alert(data.message, 1);
						$("form").submit();
					} else if (data.message == '您没有此操作权限') {
						alert(data.operation, 1);
					} else if (data.type == "error") {
						alert(data.message, 2);
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(errorThrown);
				}
			});
		}
	}
	</script>
</body>
</html>
    