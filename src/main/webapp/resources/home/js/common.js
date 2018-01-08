// 布局脚本
/*====================================
 *基于JQuery 1.11.2主框架
 *DTcms管理界面
 *作者：一些事情
====================================*/
//绑定需要浮动的表头
$(function(){
    $(".rule-single-select").ruleSingleSelect();
});

//单选下拉框
$.fn.ruleSingleSelect = function () {
    var singleSelect = function (parentObj) {
        if (parentObj.find("select").length == 0) {
            parentObj.remove();
            return false;
        }
        parentObj.addClass("single-select"); //添加样式
        parentObj.children('.boxwrap').remove(); //防止重复初始化
        parentObj.children().hide(); //隐藏内容
        var divObj = $('<div class="boxwrap"></div>').prependTo(parentObj); //前插入一个DIV
        //创建元素
        var titObj = $('<a class="select-tit" href="javascript:;"><span></span><i class="iconfont icon-xiala"></i></a>').appendTo(divObj);
        var itemObj = $('<div class="select-items"><ul></ul></div>').appendTo(divObj);
        var selectObj = parentObj.find("select").eq(0); //取得select对象
        //遍历option选项
        selectObj.find("option").each(function (i) {
            var indexNum = selectObj.find("option").index(this); //当前索引
            var liObj = $('<li>' + $(this).text() + '</li>').appendTo(itemObj.find("ul")); //创建LI
            if ($(this).prop("selected") == true) {
                liObj.addClass("selected");
                titObj.find("span").text($(this).text());
            }
            //检查控件是否启用
            if ($(this).prop("disabled") == true) {
                liObj.css("cursor", "default");
                return;
            }
            //绑定事件
            liObj.click(function () {
                $(this).siblings().removeClass("selected");
                $(this).addClass("selected"); //添加选中样式
                selectObj.find("option").prop("selected", false);
                selectObj.find("option").eq(indexNum).prop("selected", true); //赋值给对应的option
                titObj.find("span").text($(this).text()); //赋值选中值
                itemObj.hide(); //隐藏下拉框
                selectObj.trigger("change"); //触发select的onchange事件
                //alert(selectObj.find("option:selected").text());
            });
        });
        //设置样式
        //titObj.css({ "width": titObj.innerWidth(), "overflow": "hidden" });
        //itemObj.children("ul").css({ "max-height": $(document).height() - titObj.offset().top - 62 });

        //检查控件是否启用
        if (selectObj.prop("disabled") == true) {
            titObj.css("cursor", "default");
            return;
        }
        //绑定单击事件
        titObj.click(function (e) {
            e.stopPropagation();
            if (itemObj.is(":hidden")) {
                //隐藏其它的下位框菜单
                $(".single-select .select-items").hide();
                $(".single-select .arrow").hide();
                //位于其它无素的上面
                itemObj.css("z-index", "1");
                //显示下拉框
                itemObj.show();

                //5.0新增判断下拉框上或下呈现
                if(parentObj.parents('.tab-content').length > 0){
                    var tabObj = parentObj.parents('.tab-content');
                    //容器高度-下拉框TOP坐标值-容器TOP坐标值
                    var itemBttomVal = tabObj.innerHeight() - itemObj.offset().top + tabObj.offset().top - 12;
                    if(itemBttomVal < itemObj.height()){
                        var itemTopVal = tabObj.innerHeight() - itemBttomVal - 61;
                        if(itemBttomVal > itemTopVal){
                            itemObj.children('ul').height(itemBttomVal);
                        }else{
                            if(itemTopVal < itemObj.height()){
                                itemObj.children('ul').height(itemTopVal);
                            }
                            if(!parentObj.hasClass('up')){
                                parentObj.addClass("up"); //添加样式
                            }
                        }
                    }
                }

            } else {
                //位于其它无素的上面
                itemObj.css("z-index", "");
                //隐藏下拉框
                itemObj.hide();
            }
        });
        //绑定页面点击事件
        $(document).click(function (e) {
            selectObj.trigger("blur"); //触发select的onblure事件
            itemObj.hide(); //隐藏下拉框
        });
    };
    return $(this).each(function () {
        singleSelect($(this));
    });
}

