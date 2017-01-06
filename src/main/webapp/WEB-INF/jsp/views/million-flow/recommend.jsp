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
    <link href="${ctx}/css/million-flow/style.css" rel="stylesheet"/>
    <script src="${ctx }/common/js/jquery-1.11.0.min.js"></script>
</head>
<body>
<div class="yituijian">

    <div class="tj_list">
        <h4>我邀请的好友</h4>
        <ul>
            <c:forEach items="${list}" var="bean">
                <li>
                    <div class="avatar"><img src="${bean.headimgurl }" alt=""></div>
                    <div class="name">昵称：<span>${bean.sname}</span></div>
                    <div class="time">${bean.sBindTime}</div>
                </li>
            </c:forEach>
        </ul>
    </div>

    <a href="javascript:history.go(-1);" class="back">返回</a>


</div>
</body>
</html>
<script src="${ctx}/js/million-flow/index.js"></script>