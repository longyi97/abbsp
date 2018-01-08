$(document).ready(function(){
	//通过ajax把已签到的时间和进度条内容写进相应的位置，
	if ($("#activity14").size() > 0) {
    $.ajax({
        type: "get",
        url: "/user/userSignDate.shtml",
        success: function (data) {
                var strVar = "";
                $(".signin").empty();
                if (data.TodayIsSignIn) {
                    strVar += " <a href=\"javascript:void(0);\" class=\"today_qd_btn\">已签到 <\/a>\n";
                } else {
                    strVar += " <a href=\"javascript:void(0);\" onclick=\"SignIn()\" class=\"today_qd_btn\">立即签到<\/a>\n";
                }
                $(".signin").append(strVar);
                if (data.signDate != undefined && data.signDate.length > 0) {
                    ruiec_SignUpdate(data.signDate);
                } else {
                    var datas = [];
                    ruiec_SignUpdate(datas);
                }
	            $(".signinnum").text(data.count);
	            strVar = "";
	            var barWidth = data.progress;
	            $(".bar").css("width",data.progress);
	            $.each(data.list,function (index, item) {
	                strVar += "                <div class=\"bitext\" style=\"left:"+item.left+"\">\n";
	                if(parseInt(barWidth)>=(parseInt(item.left)+4)){
	                	strVar += "                    <i class=\"curr\">"+item.Number+"<\/i>\n";
	                	if(parseInt(barWidth)==(parseInt(item.left)+4)){
	                		strVar += "                    <span class=\"curr\">" + item.Day + "天<br>赠送" + item.Number + "<\/span>\n";
	                	}else{
	                		strVar += "                    <span class=\"olded\">" + item.Day + "天<br>赠送" + item.Number + "<\/span>\n";
	                	}
	                }else{ 
	                	strVar += "                    <i>"+item.Number+"<\/i>\n";
	                	strVar += "                    <span>" + item.Day + "天<br>赠送" + item.Number + "<\/span>\n";
	                }
	               	               
	                strVar += "                <\/div>\n";
	            });
	            $("#ruleList").empty().append(strVar);
	        }
	    });
	}
});
/**
 * @content 签到日期效果
 * @param currdata 数组，本月的签到记录
 */
function ruiec_SignUpdate(currdata) {

    if (currdata == undefined) { currdata = [0, 1, 2, 4, 5, 7, 10, 12, 16, 18, 20] }
    var _theDate = new Date();  //获取当前时间  2015 07 25 表示的 是  2015年8月25日
    var _theDay = _theDate.getDate();
    var _ToDays = _theDate.getFullYear() + "-" + (_theDate.getMonth() + 1) + "-" + _theDate.getDate();
    $("#today_js").empty().text(_ToDays);
    //Date(2015,08,0) //获取的是8月份的最后一天的索引[08表示的是9月]
    var _theNextMonthData = new Date(_theDate.getFullYear(), _theDate.getMonth() + 1, 0);
    //console.log(_theNextMonthData.getDate());  //获取本月总共多少天

    //Date(2015,08,1) //获取的是9月份的第一天的 [08表示的是9月]
    var _theMonthData = new Date(_theDate.getFullYear(), _theDate.getMonth(), 1);
    //console.log(_theMonthData.getDay()); //本月第一天是星期几
    var _M_startWeekDay = _theMonthData.getDay();
    var _M_DayLength = _theNextMonthData.getDate();

    //获取上月的日期
    var _thePrevDate = new Date(_theDate.getFullYear(), _theDate.getMonth(), 0);
    var _M_PrevDayLength = _thePrevDate.getDate(); //获取上一月最后的值 

    //填补本月之前的日期
    for (var j = _M_startWeekDay - 1; 0 <= j; j--) {
        var theTdObj = $("#qd_date").find("td").eq(j);
        //判断当前 天数 是否在数组中存在
        var theCurrData = false;
        var _html="<div><em><\/em><\/div>";
        theTdObj.empty().html(_html);
        _M_PrevDayLength--;
    }

    var _days = 1;
    //开始的索引为第几个td(即本月的第几天)
    for (var i = _M_startWeekDay; i < (_M_DayLength + _M_startWeekDay) ; i++) {
        var theTdObj = $("#qd_date").find("td").eq(i);
        //判断当前 天数 是否在数组中存在
        var theCurrData = CheckStrInArray2(_days, currdata);
        var _html="<div><em>"+_days+"<\/em><\/div>";

        if (theCurrData) { theTdObj.addClass("curr"); } else { theTdObj.removeClass("curr"); }
        if (_theDay == _days) {
            if (theCurrData) {
                theTdObj.addClass("curr");
                theTdObj.empty().html(_html);
                $(".qd_title .qd_ico ").addClass("curr");
                $(".qd_title .qd_info h4").empty().text("今日签到成功");
            } else {
                theTdObj.empty().html("<a href='javascript:void(0)' class='today_qd_btn'>" + _html + "</a>");
            }
        }
        else {
            theTdObj.empty().html(_html);
        }
        _days++;
    }

    var _nextDay = 1;
    //填补本月之后的日期
    for (var k = (_M_DayLength + _M_startWeekDay) ; k <= 42; k++) {
        var theTdObj = $("#qd_date").find("td").eq(k);
        var _html="<div>"+_M_PrevDayLength+"<\/div>";
        var _htmlN="<div><em></em></div>";
        //判断当前 天数 是否在数组中存在 
        theTdObj.empty().html(_html).css("color", "#ccc");
        theTdObj.empty().html(_htmlN);
        _nextDay++;
    }
}

/**
 * @content 判断字符串是否在数组当中
 * @param str 字符串
 * @param currdata 数组
 */
function CheckStrInArray2(str, arry) {
    var theState = false;
    for (var i = 0; i < arry.length; i++) {
        if (arry[i] == str) {
            i = arry.length;
            theState = true;
        }
    }
    return theState;
}
ruiec_SignUpdate();
//签到
function SignIn() {
    var today = new Date();
    today = today.getFullYear() + "-" + (today.getMonth() + 1) + "-" + (today.getDate());
    $.ajax({
        type: "POST",
        url: "/user/succeedSign.shtml",
        success: function (data) {
            if (data.noSucceed) {
            	setTimeout(function () {
                    window.location.reload();
                }, 200);
            } else {
            	alert("签到失败");
            }
        }
    });
}