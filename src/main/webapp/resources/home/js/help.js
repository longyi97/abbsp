$(document).ready(function(){
//	导航栏
	$(".language_a").hover(function(){
	$(".lang_list").show();
	},function(){$(".lang_list").hide();}
	)
	
	
	if ($("#ruivalidate_form_class").length > 0) {
	        var _FormValidate = new $.rui_validate();
	        _FormValidate.initload();
	        _FormValidate.initForm({
	            FocusTip: false,
	            BlurChange: false,
	            ShowTip: "alert_layer",   //IconText
	            FormObj: $("#ruivalidate_form_class"),
	            FormIdName: 'ruivalidate_form_class',
	            SubBtn: 'submit'
	        });
	    }

	if ($("#member").length > 0) {
	        var _FormValidate = new $.rui_validate();
	        _FormValidate.initload();
	        _FormValidate.initForm({
	            FocusTip: false,
	            BlurChange: false,
	            ShowTip: "alert_layer",   //IconText
	            FormObj: $("#member"),
	            FormIdName: 'member',
	            SubBtn: 'submit'
	        });
	    }
	
	if ($("#register").length > 0) {
	        var _FormValidate = new $.rui_validate();
	        _FormValidate.initload();
	        _FormValidate.initForm({
	            FocusTip: false,
	            BlurChange: false,
	            ShowTip: "alert_layer",   //IconText
	            FormObj: $("#register"),
	            FormIdName: 'register',
	            SubBtn: 'submit'
	        });
	    }
	if ($("#login").length > 0) {
	        var _FormValidate = new $.rui_validate();
	        _FormValidate.initload();
	        _FormValidate.initForm({
	            FocusTip: false,
	            BlurChange: false,
	            ShowTip: "alert_layer",   //IconText
	            FormObj: $("#login"),
	            FormIdName: 'login',
	            SubBtn: 'submit'
	        });
	    }
	 //选择国家
    $(".change_city").change(function () {
        var _val = $(this).find("option:checked").attr("value");
        $(".call_ph").text(_val);
        $(".area").val(_val);
        $(".getYzm").attr('area_code', _val);
    });
    //导航栏效果
     var navStorage = $(".logo_nav .fr >a");
     navStorage.each(function () {
        //		console.log($(this)[0].href);
        //		console.log(String(window.location).split('?')[0]);
        if ($(this)[0].href == String(window.location).split('?')[0]) {
            $(this).addClass('bg_hove23').siblings().removeClass('bg_hove23');


        } else {

        }
    })

    window.onresize = function() {
    var window_width = window.innerWidth
        || document.documentElement.clientWidth
        || document.body.clientWidth;
                                   
    if(window_width < 1000) {
        // 部分浏览器需要延迟
        setTimeout(function() {
            // 窗口最大化
            window.resizeTo(screen.width, screen.height);
            // 窗口移动至 top=0, left=0
            window.moveTo(0, 0);
        }, 100);
    }
};
});