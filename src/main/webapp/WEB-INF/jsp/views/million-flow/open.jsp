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
<div class="friend">

    <img src="http://oss.188jielan.net/jielanwx/jx/million-flow/friend_1.png" alt="" class="dialog">
    <img src="http://oss.188jielan.net/jielanwx/jx/million-flow/friend_2.png" alt="" class="dialog">
    <img src="http://oss.188jielan.net/jielanwx/jx/million-flow/friend_3.png" alt="" class="dialog">
    <img src="http://oss.188jielan.net/jielanwx/jx/million-flow/friend_4.png" alt="" class="dialog">
    <img src="http://oss.188jielan.net/jielanwx/jx/million-flow/friend_5.png" alt="" class="dialog">

    <img src="http://oss.188jielan.net/jielanwx/jx/million-flow/friend_6.png" alt="" class="liuliang_f">
    <!-- 领取流量按钮 -->
    <a href="javascript:;" class="lingqu_f"><img src="http://oss.188jielan.net/jielanwx/jx/million-flow/friend_7.png" alt=""></a>

    <div class="mask">

        <div class="erweima_f">
            <img src="http://oss.188jielan.net/jielanwx/jx/million-flow/mask_erweima.png" alt="">
            <p>识别二维码关注嘉兴移动官微 回复“<span>我要流量</span>”参与活动！</p>
            <a href="javascript:;" class="know">我知道了</a>
        </div>

    </div>

</div>
</body>
</html>
<script src="${ctx }/js/million-flow/index.js"></script>