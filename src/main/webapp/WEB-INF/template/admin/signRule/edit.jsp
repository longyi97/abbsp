<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta name="renderer" content="webkit|ie-comp|ie-stand" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,initial-scale=1.0,user-scalable=no" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <base href="${base}" />
    <title>签到规则</title>
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
            <a href="list.shtml"><span>签到规则管理</span></a>
            <i class="arrow"></i>
            <span>签到规则</span>
        </div>
        <div class="line10"></div>
        <!--/导航栏-->

        <!--内容-->
        <div id="floatHead" class="content-tab-wrap">
            <div class="content-tab">
                <div class="content-tab-ul-wrap">
                    <ul>
                        <li><a class="selected" href="javascript:;">签到规则信息</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="tab-content">
        	<input type="hidden" value="${signRule.id }" name="id"/>
            <dl>
                <dt>连续签到的天数</dt>
                <dd>
                    <input id="oldContinuousDays" value="${signRule.continuousDays }" type="hidden"/>
                    <input id="continuousDays" class="input normal" value="${signRule.continuousDays }" datatype="/(?!0)^\d{1,9}$/" 
                     name="continuousDays" type="text" onblur="isContinuousDays();"/>
                  	<span class="Validform_checktip continuousDays">*</span>
                  	
                </dd>
            </dl>
            <dl>
                <dt>额外奖励的分值</dt>
                <dd>
                    <input class="input normal"  value="${signRule.rewardPoints }" name="rewardPoints" type="text" datatype="/^\d{1,11}(?:\.\d{1,2})?$/"/>
                    <span class="Validform_checktip">*</span>
                </dd>
            </dl>
            <dl>
                <dt>备注</dt>
                <dd>
                    <input class="input normal" value="${signRule.note }" name="note" type="text" datatype="*1-255"/>
                    <span class="Validform_checktip">*</span>
                </dd>
            </dl>
        </div>
        <!--/内容-->
        <!--工具栏-->
        <div class="page-footer">
            <div class="btn-wrap">
                <input id="btn_submit" class="btn" type="button" value="提交保存" onclick="dianji();"/>
                <input class="btn yellow" onclick="javascript:history.back(-1);" type="button" value="返回上一页" />
            </div>
        </div>
        <!--/工具栏-->
    </form>
    <script type="text/javascript">
	    $(function () {
			$('#form1').initValidform(); 
	    })
	 function isContinuousDays(){
	 var flag=false;
	 var continuousDays=$.trim($("#continuousDays").val());
	 var oldContinuousDays=$.trim($("#oldContinuousDays").val());
	 if(""==oldContinuousDays){
		 flag=true;
     }else if(continuousDays==oldContinuousDays){
    	 flag=false;
	 }else{
		 flag=true;
		 }
	  if(flag){
		 $.ajax({
			   type: "POST",
			   url: "/admin/signRule/isContinuousDays.shtml",
	           data:{"continuousDays":continuousDays},
			   dataType:"json",
			   async: false,
			   success: function(data){
				   if( "0"== data){
					   $(".continuousDays").addClass("Validform_wrong");
                       $(".continuousDays").text("该连续天数已存在！");
                       flag= false;
				   }else if(data=="1"){
					   flag=true;
				   }
			   }
		 });
	 }else{
		  $(".continuousDays").addClass("Validform_right");
          $(".continuousDays").text("信息通过验证！");
          flag=true;
	 }
	  return flag;
 }
	    function dianji(){
	    	if(isContinuousDays()){
	    		  $("#form1").submit();
	    	}
	    }	    
	    
	    
	    //绑定keydown事件，监听用户的按键
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
</body>
</html>
    