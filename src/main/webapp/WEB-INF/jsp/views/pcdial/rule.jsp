<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    <title>活动规则</title>
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
    <img src="${ctx }/images/pcdial/top_01.png">
    <div class="des">
        <p class="rule">活动规则</p>
        <p class="day">关注嘉兴公安就是关注你的平安</p>
        <p class="day">第一阶段活动时间：2016/9/20-2016/12/31</p>
        <p class="day">活动对象： 嘉兴公安官微粉丝及嘉兴移动用户</p>
        <div class="ss">(一)本地移动用户：<br/>
            1）新关注用户均可获得一次抽奖机会，中奖率100%（仅限嘉兴移动用户）；<br/>
            2）前往嘉兴公安官微，回复“抽奖”获取活动链接；<br/>
            3）用户输入手机号码及验证码后参与大转盘活动，点击按钮开启大转盘，可随机获得奖励；<br/>
            4）已经关注但未参与过活动的用户，在活动期间都可以参与抽奖，中奖率100%；<br/>
        </div>
        <div class="ss">（二）无法参与本活动的非移动用户及非本市用户，仍可参与活动期间每月幸运抽奖活动。<br/>
        </div>
        <div class="ss">活动奖励：<br/>
            5元话费<br/>
            10元话费<br/>
            20元话费<br/>
            50元话费<br/>
            50M流量券<br/>
            100M流量券<br/>
            200M流量券<br/>
            500M流量券<br/>
        </div>
        <div class="ss">奖励发放：<br/>
            1）话费奖励：活动结束后的7个工作日内发放至您参与活动的手机号码内，请注意查收；<br/>
            2）流量奖励：活动结束后的15个工作日内发放至手机营业厅APP内，请注意查收并及时前往客户端进行兑换；
        </div>


        </div>
</div>
</body>
</html>