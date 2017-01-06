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
    <title>各区域信息汇总</title>
    <style>
        table{
            /*border:1px solid  #b3d4fc;*/
            width:100%
        }
        tr{
            width:100%;
        }
        td{
            width:33%;
            text-align: center;

        }
    </style>
</head>
<body>
<table>
    <tr style="width:100%"><td>区域</td><td>昨日新增</td><td>总量</td></tr>
    <tr><td>南湖</td><td>${area_1}</td><td>${count_1}</td></tr>
    <tr><td>秀洲</td><td>${area_2}</td><td>${count_2}</td></tr>
    <tr><td>嘉善</td><td>${area_3}</td><td>${count_3}</td></tr>
    <tr><td>平湖</td><td>${area_4}</td><td>${count_4}</td></tr>
    <tr><td>海盐</td><td>${area_5}</td><td>${count_5}</td></tr>
    <tr><td>海宁</td><td>${area_6}</td><td>${count_6}</td></tr>
    <tr><td>桐乡</td><td>${area_7}</td><td>${count_7}</td></tr>
    <tr><td>经开</td><td>${area_8}</td><td>${count_8}</td></tr>
    <tr><td>港区</td><td>${area_9}</td><td>${count_9}</td></tr>
    <tr><td>出入境管理</td><td>${area_10}</td><td>${count_10}</td></tr>
</table>
</body>
</html>
