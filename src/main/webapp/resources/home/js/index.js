$(function(){
	//获取地址栏参数进行判断左侧菜单的选中状态
	var addUrl =window.location.pathname+window.location.search;
	$(".panel_left dt").each(function(){
		var _href=$(this).find("a").attr("href")
		if(_href==addUrl){
			$(".panel_left dt").removeClass("curr");
			$(this).addClass("curr");
		}
	});
});
