<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<jsp:include page="../common/common.jsp"></jsp:include>
<c:set var="path" value="${pageContext.request.getAttribute('path')}" />
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<head lang="en">
    <meta http-equiv="Content-type" content="text/html;charset=utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <meta name="format-detection" content="telephone=no">
    <meta name="msapplication-tap-highlight" content="no"/>
    <meta name="format-detection" content="email=no"/>
    <title>我的奖品</title>
    <link href="${ctx }/css/pcdial/index.css" rel="stylesheet"/>
    <script>
        (function (doc, win) {
            var docEl = doc.documentElement,
                    resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
                    recalc = function () {
                        var clientWidth = docEl.clientWidth;
                        if (!clientWidth) return;
                        if (clientWidth >= 640) {
                            docEl.style.fontSize = '100px';
                        } else {
                            docEl.style.fontSize = 100 * (clientWidth / 640) + 'px';
                        }
                    };

            if (!doc.addEventListener) return;
            win.addEventListener(resizeEvt, recalc, false);
            doc.addEventListener('DOMContentLoaded', recalc, false);
        })(document, window);
    </script>
    <script src="${ctx }/common/js/jquery-1.7.2.js"></script>
    <script>
        $(function(){
            var height = $(document).height();
            $('.all1').css({'height': height});
        })
    </script>
</head>
<body>
<div class="all1">
    <img src="${ctx}/images/pcdial/top_01.png"/>
    <div class="des">
        <p class="rule">活动规则</p>
        <ul>
            <li>
                <c:forEach items="${user}" var="list">
                    <p><span>${list.dtime}</span></p>
                    <p>${list.award}</p>
                </c:forEach>

            </li>
        </ul>
    </div>
</div>
</body>

</html>