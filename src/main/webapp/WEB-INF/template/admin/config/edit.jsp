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
    <title>编辑系统配置</title>
    <script type="text/javascript">
    	var adminMenuData='/admin/common/nav.shtml';
    </script>
    <link media="all" type="text/css" rel="stylesheet" href="/resources/admin/skin/style.css" />
    <script type="text/javascript" src="/resources/admin/script/jquery/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="/resources/admin/script/layer/layer.js"></script>
    <script type="text/javascript" src="/resources/admin/script/jquery/Validform_v5.3.2_min.js"></script>
    <script type="text/javascript" src="/resources/admin/script/webuploader/webuploader.min.js"></script>
    <script type="text/javascript" src="/resources/admin/js/uploader.js"></script>
    <script type="text/javascript" src="/resources/admin/js/laymain.js"></script>
    <script type="text/javascript" src="/resources/admin/js/layindex.js"></script>
    <script type="text/javascript" src="/resources/admin/script/common.js"></script>
</head>
<body class="mainbody">
    <form method="post" action="update.shtml" accept-charset="UTF-8" id="form1">
        <!--导航栏-->
        <div class="location">
            <a href="javascript:history.back(-1);" class="back"><i></i><span>返回上一页</span></a>
            <a href="/admin/common/center.shtml" class="home"><i></i><span>首页</span></a>
            <i class="arrow"></i>
            <span>系统配置</span>
        </div>
        <div class="line10"></div>
        <!--/导航栏-->

        <!--内容-->
        <div id="floatHead" class="content-tab-wrap">
            <div class="content-tab">
                <div class="content-tab-ul-wrap">
                    <ul>
                        <li><a class="selected" href="javascript:;">系统配置信息</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="tab-content">
            <dl>
                <dt>签到积分值</dt>
                <dd>
                    <input class="input normal" datatype="/^\d{1,11}(?:\.\d{1,2})?$/" value="${configs['points_sign'] }" name="points_sign" type="text" />
                    <span class="Validform_checktip">*</span>
                </dd>
            </dl>
            <dl>
                <dt>发帖积分值</dt>
                <dd>
                    <input class="input normal" datatype="/^\d{1,11}(?:\.\d{1,2})?$/" value="${configs['points_post'] }" name="points_post" type="text" />
                    <span class="Validform_checktip">*</span>
                </dd>
            </dl>
            <dl>
                <dt>发帖被回复积分值</dt>
                <dd>
                    <input class="input normal" datatype="/^\d{1,11}(?:\.\d{1,2})?$/" value="${configs['points_post_replied'] }" name="points_post_replied" type="text" />
                    <span class="Validform_checktip">*</span>
                </dd>
            </dl>
            <dl>
                <dt>回复帖子积分值</dt>
                <dd>
                    <input class="input normal" datatype="/^\d{1,11}(?:\.\d{1,2})?$/" value="${configs['points_reply'] }" name="points_reply" type="text" />
                    <span class="Validform_checktip">*</span>
                </dd>
            </dl>
            <dl>
                <dt>水贴积分值</dt>
                <dd>
                    <input class="input normal" datatype="/^-?\d{1,11}(?:\.\d{1,2})?$/" value="${configs['points_bad_article'] }" name="points_bad_article" type="text" />
                    <span class="Validform_checktip">*</span>
                </dd>
            </dl>
            <dl>
                <dt>每日发帖可积分次数</dt>
                <dd>
                    <input class="input normal" datatype="n1-11" value="${configs['points_post_times'] }" name="points_post_times" type="text" />
                    <span class="Validform_checktip">*</span>
                </dd>
            </dl>
            <dl>
                <dt>每日回复可积分次数</dt>
                <dd>
                    <input class="input normal" datatype="n1-11" value="${configs['points_reply_times'] }" name="points_reply_times" type="text" />
                    <span class="Validform_checktip">*</span>
                </dd>
            </dl>
            <dl>
                <dt>每帖积分上限</dt>
                <dd>
                    <input class="input normal" datatype="/^\d{1,11}(?:\.\d{1,2})?$/" value="${configs['points_pre_article_max'] }" name="points_pre_article_max" type="text" />
                    <span class="Validform_checktip">*</span>
                </dd>
            </dl>
            <dl>
                <dt>每人每日积分上限</dt>
                <dd>
                    <input class="input normal" datatype="/^\d{1,11}(?:\.\d{1,2})?$/" value="${configs['points_pre_user_pre_day_max'] }" name="points_pre_user_pre_day_max" type="text" />
                    <span class="Validform_checktip">*</span>
                </dd>
            </dl>
        </div>
        <!--/内容-->
        <!--工具栏-->
        <div class="page-footer">
            <div class="btn-wrap">
                <input class="btn" type="submit" value="提交保存" />
            </div>
        </div>
        <!--/工具栏-->
    </form>
    <script type="text/javascript">
        $(function () {
            $('#form1').initValidform();
        })
    </script>
</body>
</html>
    