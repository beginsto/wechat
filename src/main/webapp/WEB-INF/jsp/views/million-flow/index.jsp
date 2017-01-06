<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<jsp:include page="../common/common.jsp"></jsp:include>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<head lang="en">
    <meta http-equiv="Content-type" content="text/html;charset=utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <meta name="format-detection" content="telephone=no">
    <meta name="msapplication-tap-highlight" content="no"/>
    <meta name="format-detection" content="email=no"/>
    <title>百万话费送不停</title>
    <script>
        (function (doc, win) {
            var docEl = doc.documentElement,
                    resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
                    recalc = function () {
                        var clientWidth = docEl.clientWidth;
                        if (!clientWidth) return;
                        docEl.style.fontSize = 32 * (clientWidth / 640) + 'px';
                    };
            if (!doc.addEventListener) return;
            win.addEventListener(resizeEvt, recalc, false);
            doc.addEventListener('DOMContentLoaded', recalc, false);
        })(document, window);
    </script>
    <link href="${ctx }/css/million-flow/style.css" rel="stylesheet"/>
    <script src="${ctx }/common/js/jquery-1.11.0.min.js"></script>
</head>
<body>
<input type="hidden" id="invited" value="${info.successNum }" />
<input type="hidden" id="num" value="${user.phone }" />
<input type="hidden" id="openid" value="${openid }" />
<div class="index">

    <!------ 中间头像移动 ------>
    <div class="main">
        <ul>
            <li id="go">
                <div class="avatar_box">
                    <div class="avatar"><img src="${user.headimgurl}" alt=""></div>
                </div>
            </li>
            <li id="go_1">
                <div class="avatar_box">
                    <div class="avatar"><img src="${user.headimgurl}" alt=""></div>
                </div>
            </li>
            <li id="go_2">
                <div class="avatar_box">
                    <div class="avatar"><img src="${user.headimgurl}" alt=""></div>
                </div>
            </li>
            <li id="go_3">
                <div class="avatar_box">
                    <div class="avatar"><img src="${user.headimgurl}" alt=""></div>
                </div>
            </li>
            <li id="go_4">
                <div class="avatar_box">
                    <div class="avatar"><img src="${user.headimgurl}" alt=""></div>
                </div>
            </li>
            <li id="go_5">
                <img src="http://oss.188jielan.net/jielanwx/jx/million-flow/flag_2.png" alt="" id="flag_2" class="flag">
                <div class="avatar_box">
                    <div class="avatar"><img src="${user.headimgurl}" alt=""></div>
                </div>
            </li>
            <li id="go_6">
                <div class="avatar_box">
                    <div class="avatar"><img src="${user.headimgurl}" alt=""></div>
                </div>
            </li>
            <li id="go_7">
                <div class="avatar_box">
                    <div class="avatar"><img src="${user.headimgurl}" alt=""></div>
                </div>
            </li>
            <li id="go_8">
                <img src="http://oss.188jielan.net/jielanwx/jx/million-flow/flag_1.png" alt="" id="flag_1" class="flag">
                <div class="avatar_box">
                    <div class="avatar"><img src="${user.headimgurl}" alt=""></div>
                </div>
            </li>
        </ul>
    </div>

    <!-------- 4个按钮 -------->
    <div class="btn_box">
        <a id="share" class="btn btn_1"><img src="http://oss.188jielan.net/jielanwx/jx/million-flow/btn_1.png" alt=""></a><!---- 推荐好友 ---->
        <a href="javascript:;" class="btn btn_2"><img src="http://oss.188jielan.net/jielanwx/jx/million-flow/btn_2.png" alt=""></a><!----- 新用户 ----->
        <a href="recommend?pid=${user.id}" class="btn btn_3"><img src="http://oss.188jielan.net/jielanwx/jx/million-flow/btn_3.png" alt=""></a><!-- 已成功推荐好友 -->
        <a href="javascript:;" class="btn btn_4"><img src="http://oss.188jielan.net/jielanwx/jx/million-flow/btn_4.png" alt=""></a><!----- 活动规则 ---->
    </div>
    <!--------- 底部 --------->
    <div class="footer">
        <div class="num">已成功推荐<span>2</span>名好友</div>
        <div class="lingqu">未领取</div>
    </div>

    <div class="mask">

        <!-------- 活动规则 -------->
        <div class="rules">
            <span class="close">&times;</span>
            <p>1）活动时间：2017年1月4日——2017年12月31日（每月开展一次）</p>
            <p>2）活动对象：嘉兴移动官微老用户及新用户（仅限嘉兴移动用户）</p>
            <p>3）活动介绍：</p>
            <p>3.1）在嘉兴移动官微回复“我要流量”，获取活动链接</p>
            <p>3.2）新用户进入活动页面，可直接领取新用户奖励（100M流量）</p>
            <p>3.3）老用户可通过分享页面邀请好友，每月累计成功邀请5位好友可获得500M流量奖励；每月累计成功邀请8位好友可获得1GB流量奖励；</p>
        </div>

        <!-------- 新用户登录 -------->
        <div class="login">
            <span id="login_close" style="position: absolute;font-size: 2.4rem;color: #fff; right: 0;top: -0.3rem;">&times;</span>
            <h3>一键绑定赢流量</h3>
            <div class="row">
                <input type="text" id="phone" placeholder="请输入嘉兴移动手机号码">
            </div>
            <div class="row">
                <input type="text" id="yzm" placeholder="请输入验证码">
                <a href="javascript:;" id="huoqu">获取验证码</a>
            </div>
            <div class="row"><a href="javascript:;" id="send">提交</a></div>
        </div>

        <!-------- 已绑定老用户 -------->
        <div class="old">
            <p class="red">被我识破啦，您已经是老用户啦！</p>
            <p class="red">不可再领奖哦！</p>
            <p>快邀新朋友，1G流量大奖向你招手哦！</p>
</div>

<!-------- 绑定成功 -------->
<div class="succ">
    <h3>绑定成功！</h3>
    <p>欢迎您关注嘉兴移动官方微信平台！ <span>100M</span>流量已被您收入囊中，现在立即去邀请小伙伴吧，更有机会享1G流量大奖~</p>
</div>

</div>

</div>
</body>
</html>
<script src="${ctx }/js/million-flow/index.js"></script>