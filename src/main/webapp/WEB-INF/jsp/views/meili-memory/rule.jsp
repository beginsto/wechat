<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% request.getSession().setAttribute("count","1");%>
<html>
<jsp:include page="../common/common.jsp"></jsp:include>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<head lang="en">
    <meta http-equiv="Content-type" content="text/html;charset=utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <meta name = "format-detection" content = "telephone=no">
    <meta name="msapplication-tap-highlight" content="no"/>

    <title>梅里记忆</title>

</head>

<body style="margin: 0 auto;font-family:'微软雅黑';border:none;margin:0 auto">
<img width="100%" src="http://oss.188jielan.net/jielanwx/jx/meili-memory/rule.jpg">
</body>
</html>
