<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/6/29
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String context = request.getContextPath();
    String path = application.getRealPath("/");
    pageContext.setAttribute("context_", context);
    pageContext.setAttribute("path", path);
%>
