<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/home/css/common.css" />
<style>
	.errorMain{width:700px;height:320px;margin:200px auto 0 auto;}
	.errorMain img{display:block;width:252px;height:313px;float:left;margin-right:30px;}
	.errorRight{width:400px;padding-top:60px;float:left;text-align:center;}
	.errorRight span{font-size:22px;color:#666;display:block;}
	.errorRight span em{font-size:28px;color:#333;margin-right:15px;}
	.errorRight a{display:inline-block;width:170px;height:50px;background-color:#ccc;color:#000;font-size:24px;text-align:center;line-height:50px;margin-top:30px;margin-right:15px;border-radius:5px;}
	.errorRight a.curr{background-color:#da251d;color:#fff;}
	.errorRight p{font-size:18px;margin-top:30px;color:#666;}
	.errorRight p .num{display:inline-block;margin-right:15px;color:red;font-size:26px;border-bottom: 2px solid #da251d;padding:0 8px;}
</style>
</head>
<body>
		<div class="errorMain">
			<img src="/resources/home/images/error.jpg"/>
			<div class="errorRight">
				<span><em>抱歉，</em>您没有这个帖子的权限！</span>
				<a href="/home/index.shtml" class="curr">首页</a>
				<a href="javascript:history.back(-1);">返回</a>
				<p><i class="num" id="sp">3</i>秒后自动跳转</p>
				<input id="comingUrl" type="hidden" value="${comingUrl }" >
			</div>	
		
		</div>
	<script>
	onload=function(){  
        setInterval(go, 1000);  
    };  
    var x=3; //利用了全局变量来执行  
    function go(){  
    	x--;  
        if(x>0){  
        document.getElementById("sp").innerHTML=x;  //每次设置的x的值都不一样了。  
        }else{  
        	 window.location.href=document.getElementById("comingUrl").value;
        }  
    }  
		
	</script>
</body>
</html>