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
    <title>帖子管理</title>
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
                            <select name="selectModule" id="band_list2" class="keyword" style="display: none;" onchange="moduleNamee()">
									<option  value="" >版块</option>
									<c:forEach var="list" items="${moduleList}" varStatus="i">
										<option value="${list.name}"<c:if test="${selectModule==list.name}">selected="selected"</c:if> >${list.name}</option>
									</c:forEach>
							</select>
                            </div>
					<div class="rule-single-select single-select"><div class="boxwrap"><a class="select-tit" href="javascript:;"><span>全部
					</span><i></i></a><div class="select-items"><ul><li class="selected">全部</li><li>账号</li><li>IP</li></ul></div>
					<i class="arrow"></i></div>
                            <select name="selectWho" id="band_list1" class="keyword" style="display: none;">
									<option value="" >请选择搜索条件</option>
                					<option value="1"<c:if test="${selectWho==1}">selected="selected"</c:if> >标题</option>
                					<option value="2"<c:if test="${selectWho==2}">selected="selected"</c:if> >内容</option>
                				<%-- 	<option value="3"<c:if test="${selectWho==3}">selected="selected"</c:if> >版块</option> --%>
                					<option value="4"<c:if test="${selectWho==4}">selected="selected"</c:if> >发帖人</option>
							</select>
                     </div>
                            <input name="selectContent" type="text" id="txtKeywords" class="keyword" value="${selectContent}" placeholder="请输入" />
                            <a id="lbtnSearch" class="btn-search" href="javascript:__doPostBack('lbtnSearch','')" onclick="selectContent1()">查询</a>
                        </div>
            </div>
        </div>
    </div>
    <!--/工具栏-->

    <!--列表-->
    <div class="table-container">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" class="ltable table">
           	<colgroup>
               	 <col width="5%" />
               	 <col width="12%" />
               	 <col width="8%" />
               	 <col width="8%" />
               	 <col width="7%" />
               	 <col width="5%" />
               	 <col width="2%" />
               	 <col width="2%" />
               	 <col width="2%" />
               	 <col width="2%" />
               	 <col width="4%" />
               	 <col width="18%" />
             </colgroup>
            <tr>
                <th>选择</th>
                <th align="left">帖子标题</th>  
		        <th align="left">帖子内容</th>
		        <th>所属版块</th>
		        <th>发帖时间</th>
		        <th>访问量</th>
		        <th>置顶</th>
		        <th>精华</th>
		        <th>推荐</th>
		        <th>火帖</th>
		        <th align="center">发帖人</th>       
		        <th>操作</th>       
            </tr>
            <c:forEach var="article" items="${page.list}" varStatus="i">
              <tr>
                    <td align="center">
                        <span class="checkall" style="vertical-align:middle;">
                            <input class="checker" type="checkbox" name="ids" value="${article.id }" />
                        </span>
                    </td>
                    <td>${article.title}</td>
                    <td>
                    	<div class="artContent">
						<c:if test="${fn:length(content[i.index])<31}">
						${content[i.index]}
						</c:if>
						<c:if test="${fn:length(content[i.index])>30}">
						${fn:substring(content[i.index],0,29)}
						</c:if>
						</div>
                    </td>
                    <td align="center"><ruiec:module parentId="${article.moduleId}"/></td>
                    <td align="center"><fmt:formatDate value="${article.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td align="center">${article.hit}</td>
                    <td align="center"><c:if test="${article.isTop==false}">否</c:if><c:if test="${article.isTop==true}">是</c:if></td>
                    <td align="center"><c:if test="${article.isGood==false}">否</c:if><c:if test="${article.isGood==true}">是</c:if></td>
                    <td align="center"><c:if test="${article.isRecommend==false}">否</c:if><c:if test="${article.isRecommend==true}">是</c:if></td>
                    <td align="center"><c:if test="${article.isHot==false}">否</c:if><c:if test="${article.isHot==true}">是</c:if></td>
                    <td align="center">
                    <ruiec:user var="r" userId="${article.userId}">${r.username}</ruiec:user>
                    </td>
                    <td align="center" style="position: relative;">
                        <a href="edit.shtml?id=${article.id}">编辑</a>
                        <!--设置一个下拉框，if判断用户是什的当前状态，  -->
                        <%--  <select id="band_list" class="keyword">
                         	<option> <a href="edit.shtml?id=${article.id}">设置置顶</a></option>
							<option> <a href="edit.shtml?id=${article.id}">取消置顶</a></option>
						</select> --%>
						<!--  <a href="##" class="tz_gl">帖子管理</a>
						<ul class="menu" style="display: none"> -->
						<c:if test="${article.isDelete==false}">
								<c:if test="${article.isTop==false}">
									<a href="update2.shtml?id=${article.id}&isTop=true">设置置顶</a>
								</c:if>
							   <c:if test="${article.isTop==true}">
									<a href="update2.shtml?id=${article.id}&isTop=false">取消置顶</a>
							   </c:if>
							   <c:if test="${article.isGood==false}">
									<a href="update2.shtml?id=${article.id}&isGood=true">设置精华</a>
								</c:if>
							   <c:if test="${article.isGood==true}">
									<a href="update2.shtml?id=${article.id}&isGood=false">取消精华</a>
							   </c:if>
							   <c:if test="${article.isRecommend==false}">
									<a href="update2.shtml?id=${article.id}&isRecommend=true">设置推荐</a>
								</c:if>
							   <c:if test="${article.isRecommend==true}">
									<a href="update2.shtml?id=${article.id}&isRecommend=false">取消推荐</a>
							   </c:if>
							   	<c:if test="${article.isHot==false}">
									<a href="update2.shtml?id=${article.id}&isHot=true">设置火帖</a>
								</c:if>
							   <c:if test="${article.isHot==true}">
									<a href="update2.shtml?id=${article.id}&isHot=false">取消火帖</a>
							   </c:if>
						</c:if>	   
							    <c:if test="${article.isDelete==true}">
									已删除
							   </c:if>
							   <c:if test="${article.isDelete==false}">
									<a href="fakeDelete.shtml?id=${article.id}&isDelete=true">水帖</a>
							   </c:if>
							   <a href="/admin/articleReplay/list.shtml?articleId=${article.id}">回复</a>
							   <a href="/admin/articleAttachment/list.shtml?articleId=${article.id}">附件</a>
							   
			<!-- 			</ul> -->
                    </td>
                </tr>
            </c:forEach>
		</table>
    </div>
    <!--/列表-->
    <jsp:include page="../common/page.jsp" />
    </form>
</body>
<script type="text/javascript">
function moduleNamee() {
	$("#band_list1").val("");
	$("#txtKeywords").val("");
	$("#list_form").submit();
	 }
	
function  selectContent1() {
	 $("#band_list2").val("");
	$("#list_form").submit();
	}
//绑定keydown事件，监听用户的按键,禁用Enter键自动提交表单
document.onkeydown = function(event) {  
    var target, code, tag;  
    if (!event) {  
        event = window.event; //针对ie浏览器  
        target = event.srcElement;  
        code = event.keyCode;  
        if (code == 13) {  
            tag = target.tagName;  
            if (tag == "TEXTAREA") { return true; }  //只有当事件源是TEXTAREA时才return true，允许默认动作；其他元素全部return false，禁止表单提交和任何响应
            else { return false; }  
        }  
    }  
    else {  
        target = event.target; //针对遵循w3c标准的浏览器，如Firefox  
        code = event.keyCode;  
        if (code == 13) {  
            tag = target.tagName;  
            if (tag == "INPUT") { return false; }  //当事件源是INPUT时才return false禁止表单默认动作
            else { return true; }  
        }  
    }  
}; 
</script>
</html>
    