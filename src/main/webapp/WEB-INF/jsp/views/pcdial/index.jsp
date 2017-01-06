<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <meta name="application-tap-highlight" content="no"/>
    <meta name="format-detection" content="email=no"/>
    <title>活动首页</title>
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
    <script src="${ctx }/js/pcdial/jQueryRotate.2.2.js"></script>
    <script src="${ctx }/js/pcdial/index.js"></script>
</head>
<body style="background-color: #feda56">
<input type="hidden" id="openid" value="${user.userid}">
<input type="hidden" id="tel" value="${user.phonenum}">
<div class="all">
    <img src="${ctx }/images/pcdial/top_01.png"/>
    <p style="width: 100%;text-align: center;color:red;font-size: 0.4rem;">信息登记</p>
    <!--<div class="content">
        <img id="zhuan" class="zhuan" src="${ctx }/images/pcdial/zhuanpan.png"/>
        <img id="zhizhen" class="zhizhen" src="${ctx }/images/pcdial/dong.png"/>
    </div>-->
    <!--<div style="width: 100%;margin-top: 0.45rem">
        <a id="log" style="width:85%;margin-left: 7.5%;background:url('${ctx }/images/pcdial/log.png') no-repeat;background-size:100% 100%;color:white;font-size: 0.3rem;text-align: center;padding: 0.15rem 0;}">信息登记</a>
    </div>-->
    <!--<div class="btn" style="margin-top: 0.25rem">
        <a href="${ctx}/pcdial/rule">活动规则</a><a href="${ctx }/pcdial/award?openid=${user.userid}">我的奖品</a>
    </div>-->

    <!--<p style="width: 90%;margin: 5% 5% -5% 5%;font-size: 0.2rem;">无法参与本活动的非移动用户及非本市用户，仍可参与活动期间每月幸运抽奖活动。</p>-->
    <!--<div class="bottom">
        <div class="liu">
            <c:forEach begin="1"  end="30" items="${list}" var="user">
                <c:if test="${fn:length(user.phonenum) > '10'}">
                    <p>${user.phonenum.substring(0,3)}****${user.phonenum.substring(7)}刚刚抽中了${user.award}</p>
                </c:if>
            </c:forEach>
        </div>
    </div>-->
</div>
<div class="tan">
    <!--<div class="get1">
        <p>离大奖只差一步，加油哟!</p>
    </div>
    <div class="get">
        <p>哇哦，</p>
        <p>恭喜您获得<span>100M</span>流量券奖励！</p>
    </div>
    <!--抽奖验证码获取弹窗-->
    <!--<div class="test" id="raffle">

        <input class="num" type="tel" maxlength="11" placeholder="请输入手机号码"/>
        <div>
            <a id="getNumb">获取验证码</a><input type="text" id="code"  placeholder="请输入验证码"/>
        </div>
        <a class="now" id="doRaffle">立即抽奖</a>
    </div>-->
    <!--抽奖验证码获取弹窗-->
    <div class="test" id="logDiv">
        <div>
            <select name ="area" id="area">
                <option value="default" selected = "selected">请选择您所属的地区</option>
                <option value="0">南湖</option>
                <option value="1">秀洲</option>
                <option value="2">嘉善</option>
                <option value="3">平湖</option>
                <option value="4">海盐</option>
                <option value="5">海宁</option>
                <option value="6">桐乡</option>
                <option value="7">经开</option>
                <option value="8">港区</option>
                <option value="9">出入境管理局</option>
            </select>
        </div>
        <div>
            <input class="num" style="width:100%;margin: 0 auto;" id="wayType" type="text" placeholder="您从哪里知道嘉兴公安？（请输入）"/>
        </div>
        <!--<a class="now" style="float:left;width:40%;margin-left: 6.7%" id="cancel">取消</a>-->
        <a class="now" style="width:50%;margin-left: 25%" id="doLog">确 定</a>
    </div>
</div>
<script type="text/javascript">
    $(function(){
       $("#log").click(function(){
           $(".tan").show();
           $("#logDiv").show();
       }) ;

        $("#cancel").click(function(){
            $(".tan").hide();
            $("#logDiv").hide();
        });

        var flag = true;
        $("#doLog").click(function(){
            flag = false;
            var openid = $("#openid").val();
            var area = $("#area").val();
            var wayType = $("#wayType").val();
            if(area == null || area == 'default'){
                alert("请选择推荐您关注的公安分局!");
                flag = true;
                return;
            }
            /*if(wayType == null || wayType == ''){
                alert("请输入您获取嘉兴公安的渠道！");
                flag = true;
                return;
            }*/

            $.ajax({
               type:'post',
                url:'doLog',
                data:{openid:openid,area:area,wayType:wayType},
                dataType:'json',
                success:function (rs) {
                    if(rs.result == 'success'){
                        //$(".tan").hide();
                        //$("#logDiv").hide();
                        alert("登记成功");
                    }else if(rs.result == 'repeat'){
                        alert("请勿重复登记");
                    }else{
                        alert("登记失败!");
                    }
                    flag = true;
                }
            });
        });
    });
</script>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script>
    function onBridgeReady(){
        WeixinJSBridge.call('hideOptionMenu');
    }

    if (typeof WeixinJSBridge == "undefined"){
        if( document.addEventListener ){
            document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
        }else if (document.attachEvent){
            document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
            document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
        }
    }else{
        onBridgeReady();
    }
</script>
</body>
</html>