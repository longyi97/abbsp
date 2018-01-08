//返回带两位小数的数字
 function changeTwoDecimal(floatvar)
{
 var f_x = parseFloat(floatvar);
if (isNaN(f_x))
{
alert('function:changeTwoDecimal->parameter error');
return false;
}
 var f_x = Math.round(floatvar*100)/100;
 return f_x;
}

$(function() {

	//定位页面，更改导航样式
	var pagePosition=$("#page_position").val();
	$(".sidebar_menu .curr").removeClass("curr");
	$("."+pagePosition).addClass("curr");
	
	/* 添加页面返回事件 */
	$("#back").click(function() {
		window.history.back();
	});
	/* 全选全取消 */
	$("#checkbox").live("click", function() {
		var $checkbox = $(this);
		
		$(".checker").prop("checked", $checkbox.prop("checked"));
	});

	var $sortType = $("#sortType");
	var $sortProperty = $("#sortProperty");
	var $sort = $(".sort");
	var $pageNumber = $("#pageNumber");
	var $to = $("#goto");

	// 排序
	$sort.click(function() {
		$this = $(this);
		if ($this.attr("name") == $sortProperty.val()) {
			if ($sortType.val() == "asc") {
				$sortType.val("desc");
			} else {
				$sortType.val("asc");
			}
		} else {
			$sortProperty.val($this.attr("name"));
			$sortType.val("asc");
		}
		$pageNumber.val(1);
		$this.closest("form").submit();
	});
	
	//提示框(icon: warning)
	$.message = function(message, time){
		art.dialog({
			title: '提示信息',
			icon: 'warning',
			content: message,
			lock: true,
			/*time: time,*/
			opacity: 0.01,
			height: 20,
			fixed:true,
			resize:false			
		});
	};
	$.message2 = function(message){
		art.dialog({
			title: '提示信息',
			icon: 'warning',
			content: message,
			lock: true,
			opacity: 0.01,
			height: 20,
			cancelVal: '确定',
			cancel: function(){
			},fixed:true,
			resize:false			
		});
	};
	
	// 提示框(icon: succeed)
	$.message3 = function(message, time){
		art.dialog({
			title: '提示信息',
			icon: 'succeed',
			content: message,
			lock: true,
			/*time: time,*/
			opacity: 0.01,
			height: 20,
			fixed:true,
			resize:false			
		});
	};


	//展示图片
	$("tr td a.image").click(function() {
		imagePath = $(this).attr("id");
		art.dialog({
			width: 400,
			heigth: 400,
			title: '证件图片',
			content: "<img  src='" + imagePath + "'/>",
			lock: true,
			opacity: 0.01,
			cancelVal: '确定',
			cancel: function(){
			},fixed:true,
			resize:false	
		});	
	});
	
	//单独删除
	$("a.btn_del").click(function(){
		var $this = $(this);
		var id = $(this).attr("val");
		art.dialog.confirm("确定删除吗？", function () {
			$.ajax({
				type : "post",
				async: false,
				url : "delete.shtml",
				data : {"ids": id},
				dataType : "json",
				success : function(data) {
					if (data.type === "info") {
						$this.closest("tr").remove();
						$.message(data.message, 1);
					} else {
						$.message(data.message, 2);
					}
				}
			});
		}, function(){});
	});
	
	//批量删除
	$("#batchDelete").click(function(){
		var $ids = $(".table input[name='ids']:enabled:checked");
		if ($ids.size() < 1) {
			$.message('请选择要删除的选项');
			return;
		}
		art.dialog.confirm("确定删除吗？", function () {
		$.ajax({
			type : "post",
			url : "delete.shtml",
			data : $ids.serialize(),
			dataType : "json",
			success : function(data) {
				if (data.type == "info") {
					$.each($ids, function(index, value) {
						var $this = $(this);
						$this.closest("tr").remove();
					});
					$.message(data.message, 1);
				} else if (data.message == 'noPerms') {
					$.message(data.operation, 1);
				}else if(data.type == "error"){
					$.message(data.message, 2);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(errorThrown);
			}
		});
		}, function(){});
		
	});
	
	// 切换每页数量
	$(".pageSelect").mouseover(function() {
		var position = $(".pageSelect").offset();
		var left = position.left;
		var top = position.top;
		var height = $(".pageSelect").height();
		$(".select").css("left", left).css("top", top + height + 1).show();
		$(this).mouseout(function() {
			$(".select").hide();
		});
	});
	$(".select a").click(function() {
		var $this = $(this);
		$("#pageSize").val($this.text());
		$(".table").closest("form").submit();
	});

	
	// 跳页
	$.pageTo = function(num) {
		$pageNumber.val(num);
		$(".table").closest("form").submit();
	};

	// go
	$.go = function() {
		$pageNumber.val($to.val());
		$(".table").closest("form").submit();
	};

	// 数字输入控制
	$.isNum = function(e) {
		var k = window.event ? e.keyCode : e.which;
		if (((k >= 48) && (k <= 57)) || k == 8 || k == 0) {
		} else {
			if (window.event) {
				window.event.returnValue = false;
			} else {
				e.preventDefault(); // for firefox
			}
		}
	};

	if ($.validator != null) {
		/* 添加验证数字精度的验证器 */
		$.validator
				.addMethod(
						"decimal",
						function(value, element, param) {
							return this.optional(element)
									|| new RegExp(
											"^-?\\d{1,"
													+ (param.integer != null ? param.integer
															: "")
													+ "}"
													+ (param.fraction != null ? (param.fraction > 0 ? "(\\.\\d{1,"
															+ param.fraction
															+ "})?$"
															: "$")
															: "(\\.\\d+)?$"))
											.test(value);
						}, "numeric value out of bounds");
		/*添加qq验证器*/
		$.validator.addMethod("qq", function(value, element, param){
			return this.optional(element) || /^[1-9]\d*$/.test(value);
		},"qq format is error");
		/*添加手机验证器*/
		$.validator.addMethod("phone", function(value, element, param){
			return this.optional(element) || /^[1-9]\d{10}$/.test(value);
		},"phone format is error");
		/*添加用户名验证器*/
		$.validator.addMethod("userName", function(value, element, param){
			return this.optional(element) || /^[a-zA-Z][0-9a-zA-Z_]{5,29}$/.test(value);
		},"userName format is error");
		$.validator.addMethod("x50", function(value, element, param){
			return this.optional(element) || value%50 == 0;
		},"必须是50的倍数");
		/* 修改默认表单验证提示消息 */
		$.extend($.validator.messages, {
			required : "必选字段",
			remote : "该用户名已被使用!用户名不能重复!",
			email : "请输入正确格式的电子邮件",
			url : "请输入合法的网址",
			date : "请输入合法的日期",
			dateISO : "请输入合法的日期 (ISO).",
			number : "请输入合法的数字",
			digits : "只能输入零和正整数",
			creditcard : "请输入合法的信用卡号",
			equalTo : "请再次输入相同的值",
			accept : "请输入拥有合法后缀名的字符串",
			qq: "qq号只能由数字组成",
			phone: "请输入合法的手机号码",
			userName: "用户名必须以字母开头由数字字母和下划线组成6-30位",
			maxlength : $.validator.format("请输入一个长度最多是 {0} 的字符串"),
			minlength : $.validator.format("请输入一个长度最少是 {0} 的字符串"),
			rangelength : $.validator.format("请输入一个长度介于 {0} 和 {1} 之间的字符串"),
			range : $.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
			max : $.validator.format("请输入一个最大为 {0} 的值"),
			min : $.validator.format("请输入一个最小为 {0} 的值"),
			decimal : "请输入精度合法的数字"
		});
		$.validator.setDefaults({
			/* errorClass: "fieldError", */
			ignore : ".ignore",
			ignoreTitle : true
		/*
		 * errorPlacement: function(error, element) { var fieldSet =
		 * element.closest("span.fieldSet"); if (fieldSet.size() > 0) {
		 * error.appendTo(fieldSet); } else { error.insertAfter(element); } },
		 * submitHandler: function(form) {
		 * $(form).find(":submit").prop("disabled", true); form.submit(); }
		 */
		});
	}

	//$(".button").button();
	//精度设置
	 $(".scale").each(function(){
		    var $this = $(this);
			var suffix = $this.attr("suffix");
			if(suffix == "undefined"){
				suffix = "";
			}
			var val = $this.attr("val");
			if(val != "undefined" && val != ""){
				val = changeTwoDecimal(val)+suffix;
			}
			$this.text(val); 
		}); 
	 	
	 	//公共分页点击事件
		$(".pageable").live("click", function(){
			var $this = $(this);
			var pageNumber = $this.attr("val");
			var $page_label = $this.closest(".page_label");
			var $page = $page_label.find(".pageBar").eq(0);
			var url = $page.attr("url");
			var props = $page.attr("props");
			var formData = {"pageNumber": pageNumber, "pageSize": 10}
			if(props){
				var array = props.split(",");
				for (var index = 0; index < array.length; index++) {
				//for(var index in array){
					var prop = array[index];
					var propValue = $page_label.find("." + prop).eq(0).val();
					if(propValue && prop){
						formData[prop] = propValue;
					}
				}
			}
			$.ajax({
				data : formData,
				type : "post",
				async: true,
				url : url,
				dataType : "text",
				success : function(data){
					$this.closest(".page_label").html(data);
				}
			});
		});
		
		//公共跳页点击事件
		$("#pageTo").live("click", function(){
			var $this = $(this);
			var pageNumber = $("#inputPageNumber").val().trim();
			var $page_label = $this.closest(".page_label");
			var $page = $page_label.find(".pageBar").eq(0);
			var url = $page.attr("url");
			var props = $page.attr("props");
			if(/^\d+$/.test(pageNumber)){
			}else{
				pageNumber = 1;
			}
			var formData = {"page.pageNumber": pageNumber, "page.pageSize": 5}
			if(props){
				var array = props.split(",");
				for(var index in array){
					var prop = array[index];
					var propValue = $page_label.find("." + prop).eq(0).val();
					if(propValue && prop){
						formData[prop] = propValue;
					}
				}
			}
			$.ajax({
				data : formData,
				type : "post",
				async: true,
				url : url,
				dataType : "text",
				success : function(data){
					$this.closest(".page_label").html(data);
				}
			});
		});
		
	// 令牌	
	$(document).ajaxSend(function(event, request, settings) {
		if (!settings.crossDomain && settings.type != null && settings.type.toLowerCase() == "post") {
			var token = getCookie("token");
			if (token != null) {
				request.setRequestHeader("token", token);
			}
		}
	});
		
	// 令牌
	$().ready(function() {

		$("form").submit(function() {
			var $this = $(this);
			if ($this.attr("method") != null && $this.attr("method").toLowerCase() == "post" && $this.find("input[name='token']").size() == 0) {
				var token = getCookie("token");
				if (token != null) {
					$this.append('<input type="hidden" name="token" value="' + token + '" \/>');
				}
			}
		});

	});
	
	// 刷新验证码
	$("#imgverCode").click(function(){
		var date = new Date();
		var url = "/captcha/image.shtml?" + date.getMilliseconds();
		$(this).attr("src", url);
	});
		
	//控制排序选项切换样式
	/*$("#sortBoxDiv a").live('click',"a",function(){
		$("#sortBoxDiv.curr").removeClass("curr");
		$(this).addClass("curr");
	})*/
	
});
// 添加cookie
function addCookie(objName, objValue, objHours) {
	var str = objName + "=" + escape(objValue);
	if (objHours > 0) {// 为0时不设定过期时间，浏览器关闭时cookie自动消失
		var date = new Date();
		var ms = objHours * 3600 * 1000;
		date.setTime(date.getTime() + ms);
		str += "; expires=" + date.toGMTString();
	}
	document.cookie = str;
}
// 获取指定名称的cookie的值
function getCookie(objName) {
	var arrStr = document.cookie.split("; ");
	for ( var i = 0; i < arrStr.length; i++) {
		var temp = arrStr[i].split("=");
		if (temp[0] == objName)
			return unescape(temp[1]);
	}
}

//执行弹窗方法
function do_alertInfos(obj,alert_title,alert_infos,success_button_title,fail_button_title){ 
	if($("#lrx_alert_layout").size()<1){//如果页面存在弹窗则显示弹窗，否则新增弹窗
		var _container_layout,  //弹窗容器
			_cover_layout,      //遮罩层容器
			_content_layout,	//弹窗内容容器
			_layout_title,		//弹窗内容标题
			_layout_content ,   //弹窗内容体
			_success_button_title = success_button_title == null ? '已完成支付': success_button_title,//成功按钮标题
			_fail_button_title = fail_button_title == null ? '支付遇到问题': fail_button_title,//失败按钮标题
		 	_corrent_url = obj.attr("current_url") == undefined ? "javascript:void(0)": obj.attr("current_url"),  //完成支付的跳转地址
			_error_url = obj.attr("error_url") == undefined ? "javascript:void(0)": obj.attr("error_url");  //支付遇到问题的时候跳转的地址
			_close_url = obj.attr("close_url") == undefined ? "javascript:void(0)": obj.attr("close_url");  //关闭的时候跳转的地址

			
		_cover_layout = "<div class='cover_layout'></div>";   //遮罩层
		_layout_title = "<div class='layout_title'>"+alert_title+"<a href='"+_close_url+"' class='iconfont close_btn'></a></div>"; //标题
		_layout_content = "<div class='layout_content'>";
		_layout_content +=	"<div class='infos'>"+alert_infos+"</div>"; 
		_layout_content +=	"<div class='layout_btnline'>";
		_layout_content +=	 	"<a class='current' href='"+_corrent_url+"'>" + _success_button_title +"</a>"; 
		_layout_content +=	 	"<a class='' href='"+_error_url+"'>" + _fail_button_title + "</a>";
		_layout_content +=	"</div>";
		_layout_content +="</div>";  
		_content_layout = "<div class='content_layout'>"+_layout_title + _layout_content+"</div>";
		_container_layout = "<div class='lrx_alert_layout' id='lrx_alert_layout'>"+_cover_layout+_content_layout+"</div>";
		
		$("body").append($(_container_layout));
		
		/*关闭按钮
		$("body").on("click","#lrx_alert_layout .close_btn",function(){
			$("#lrx_alert_layout").hide();
		});
		*/
	}else{
		$("#lrx_alert_layout").show();
	}	
}

//控制主菜单切换样式
function InitMenu(num){
	var children=document.getElementById("index_menu_div").children;
	children[num].setAttribute('class','index_menu_style');
}

$(function(){
	$(".tz_gl").click(function(){
		$(".menu").css('display','none');
		$(this).parent().find("ul").css("display","block");
	});
	$(document).bind('click',function(e){ 
		var e = e || window.event; //浏览器兼容性 
		var elem = e.target || e.srcElement; 
			if (elem.className && elem.className=='tz_gl') { 
				$(this).parent().find(".menu").css("display","block");
			} else{
				$('.menu').css('display','none'); //点击的不是div或其子元素 
			}
		
		}); 

	
})