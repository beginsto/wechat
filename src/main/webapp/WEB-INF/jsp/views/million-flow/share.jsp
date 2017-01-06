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
	<script type="text/javascript" src="${ctx }/js/million-flow/jquery.qrcode.min.js"></script>
    
<script>
$(function(){
	var str = "http://jiaxing.jielanwx.com/wx/million-flow/open?pid=${user.id}";
    var eurl = encodeURIComponent(str);
    var url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx68c88f3dca13e997&redirect_uri="+eurl+"&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
	$('#code').qrcode({
		render: "table",
		width: 160,
		height:160,
		text: url
	});
	
	/*$("#sub_btn").click(function(){
		$("#code").empty();
		var str = toUtf8($("#mytxt").val());
		
		$("#code").qrcode({
			render: "table",
			width: 200,
			height:200,
			text: str
		});
	});*/
})
</script>
</head>
<body>
<div class="share">

    <!--------- 头像及昵称 ----------->
    <div class="avatar">
        <img src="${user.headimgurl}" alt="">
    </div>
    <div class="name">昵称：<span>${user.nickname}</span></div>

    <div class="gankuai">赶快扫码领取流量，已有<span>${count }</span>人参与！</div>
   <div style="width:40%;margin:0 auto;margin-top:2rem;" id="code"></div>
    <!--<img src="http://oss.188jielan.net/jielanwx/jx/million-flow/erweima.png" alt="" class="erweima">-->
    <img src="http://oss.188jielan.net/jielanwx/jx/million-flow/arrow.png" alt="" class="arrow">
    <div class="tips">「长按或扫描此二维码进入 一起赢得1G流量咯！」</div>

    <div class="mask">
        <div class="share_mask">
            <p>第一步：先截图保存本页面</p>
            <p>第二步：赶紧把名片分享给你好友吧！</p>
            <a href="javascript:;" class="know"><img src="http://oss.188jielan.net/jielanwx/jx/million-flow/know.png" alt=""></a>
        </div>
    </div>

</div>
</body>
</html>
<script src="${ctx }/js/million-flow/index.js"></script>