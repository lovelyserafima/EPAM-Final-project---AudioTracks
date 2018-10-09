<%--
  Created by IntelliJ IDEA.
  User: boriska
  Date: 15.09.18
  Time: 22:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Error page</title>
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
