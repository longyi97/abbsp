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
});