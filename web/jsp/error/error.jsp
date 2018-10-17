<%--
  Created by IntelliJ IDEA.
  User: boriska
  Date: 15.09.18
  Time: 22:46
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${changeLanguage}"/>
<fmt:setBundle basename="resources.pagecontent"/>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
        <link href="/assets/css/error/error.css" type="text/css" rel="stylesheet"/>
    <title><fmt:message key="label.pageAboutError"/></title>
</head>
<body>
        Request from ${pageContext.errorData.requestURI} is failed
        <br/>
        Servlet name or type: ${pageContext.errorData.servletName}
        <br/>
        Status code: ${pageContext.errorData.statusCode}
        <br/>
        Exception: ${pageContext.exception}
</body>
</html>
