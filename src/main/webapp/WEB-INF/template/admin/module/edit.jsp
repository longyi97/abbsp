<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="renderer" content="webkit|ie-comp|ie-stand" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0,user-scalable=no" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <base href="${base}" />
    <title>编辑版块</title>
    <script type="text/javascript">
    	var adminMenuData='/admin/common/nav.shtml';
    </script>
    <link media="all" type="text/css" rel="stylesheet" href="/resources/admin/skin/style.css" />
    <link media="all" type="text/css" rel="stylesheet" href="/resources/admin/script/multsel/multsel.css" />
    <link media="all" type="text/css" rel="stylesheet" href="/resources/admin/css/select2.min.css" />
    <script type="text/javascript" src="/resources/admin/script/jquery/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="/resources/admin/script/layer/layer.js"></script>
    <script type="text/javascript" src="/resources/admin/script/jquery/Validform_v5.3.2_min.js"></script>
    <script type="text/javascript" src="/resources/admin/script/webuploader/webuploader.min.js"></script>
    <script type="text/javascript" src="/resources/admin/js/uploader.js"></script>
    <script type="text/javascript" src="/resources/admin/js/laymain.js"></script>
    <script type="text/javascript" src="/resources/admin/js/layindex.js"></script>
    <script type="text/javascript" src="/resources/admin/script/common.js"></script>
    <script type="text/javascript" src="/resources/admin/script/multsel/multsel.js"></script>
    <script type="text/javascript" src="/resources/admin/js/select2.min.js"></script>
</head>
<body class="mainbody">
    <form method="post" action="update.shtml" accept-charset="UTF-8" id="form1">
    	<input name="_token" type="hidden" value="iFzn6pfKbyNzYFcc8udsxhIV0o4A5X7FOrwwwqIV" />
        <!--导航栏-->
        <div class="location">
            <a href="javascript:history.back(-1);" class="back"><i></i><span>返回上一页</span></a>
            <a href="../common/center.shtml" class="home"><i></i><span>首页</span></a>
            <i class="arrow"></i>
            <a href="list.shtml"><span>版块管理</span></a>
            <i class="arrow"></i>
            <span>编辑版块</span>
        </div>
        <div class="line10"></div>
        <!--/导航栏-->

        <!--内容-->
        <div id="floatHead" class="content-tab-wrap">
            <div class="content-tab">
                <div class="content-tab-ul-wrap">
                    <ul>
                        <li><a class="selected" href="javascript:;">版块信息</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="tab-content">
        	<input type="hidden" value="${module.id }" name="id"/>
        	<input type="hidden" value="${parentId }" name="parentId"/>
        	
        	<dl>
                <dd>
                    <span class="" style="color: red;">${msg}</span>
                </dd>
            </dl>
			<dl>
				<dt>板块logo：</dt>
				<dd>
					<div class="img-show" style="border: 0;">
						<span><img width="80px" height="100px" id="face_pic" 
						src="${module.logoImage}"/></span> <span
							class="fl clr b0"> <a href="javascript:void(0)"
							id="uploaderFace" class="upload-img2 add_file fl "> 上传头像 </a>
						</span>
					</div>
					<input type="file" id="upload_file" style="display: none;" /> 
					<input id="fileUrl" type="hidden" name="logoImage"/>
				</dd>
			</dl>
			
            <dl>
                <dt>版块名称</dt>
                <dd>
                    <input class="input normal" datatype="*1-25" value="${module.name }" name="name" type="text" />
                    <span class="Validform_checktip">*</span>
                </dd>
            </dl>
            <dl>
                <dt>描述</dt>
                <dd>
                    <input class="input normal" datatype="*0-255" value="${module.description }" name="description" type="text" />
                    <span class="Validform_checktip"></span>
                </dd>
            </dl>
            <dl>
                <dt>所属部门</dt>
                <dd>
					<select class="js-example-basic-multiple" name="departmentIds"
					 multiple="multiple" style="min-width:300px;min-height:32px;line-height:32px" datatype="*">
						<c:forEach var="item" items="${departments }">
							<c:set var="isSelected" value="false"/>
							<c:forEach var="department" items="${module.departments }">
								<c:if test="${department.id eq item.id }"> <c:set var="isSelected" value="true"/> </c:if>
							</c:forEach>
							<option <c:if test="${isSelected }"> selected="selected"</c:if> value="${item.id }">${item.name }</option>
						</c:forEach>
					</select>
                </dd>
            </dl>
            <dl>
                <dt>排序</dt>
                <dd>
                    <input class="input normal" datatype="n0-2" value="${module.sort }" name="sort" type="text" />
                    <span class="Validform_checktip"></span>
                </dd>
            </dl>
        </div>
        <!--/内容-->
        <!--工具栏-->
        <div class="page-footer">
            <div class="btn-wrap">
                <input class="btn" type="submit" value="提交保存" />
                <input class="btn yellow" onclick="javascript:history.back(-1);" type="button" value="返回上一页" />
            </div>
        </div>
        <!--/工具栏-->
    </form>
    <script type="text/javascript">
        $(function () {
            $('#form1').initValidform();
        });
        
        // 按组装部门
        function subList(data, conList, strTab) {
        	for (var i = 0; i < data.length; i++) {
        		if(null != data[i].parentId) {
	        		data[i].name = strTab + data[i].name;
        		}
				conList.push(data[i]);
				if(null != data[i].subDepartments) {
					subList(data[i].subDepartments, conList, "　" + strTab);
				}
			}
		}
        
        // 初始化select2
        $(document).ready(function() {
            $('.js-example-basic-multiple').select2({
            	placeholder: "请选择至少一个部门"
			});
		});
        
        
        $(function(){
	    	var webuploader = WebUploader.create({
	    		swf:"/resources/home/js/webuploader/Uploader.swf",
	    		server:"/admin/upload.jsp",
	    		auto:true,
	    		pick:"#uploaderFace"
	    	}); 
	    	
	    	webuploader.on("uploadSuccess",function(file,data){
	    		if (data.error == 0) {
               		$("#face_pic").attr('src',data.url);
               		$("#fileUrl").attr('value',data.url);
                } else {
                	alert("哈哈！出错了。");
                }
	    	});
	    	
	    	webuploader.on("uploadError",function(){
	    		console.log("error");
	    	}); 
	    })
        
	</script>
</body>
</html>
    