<%--
  Created by IntelliJ IDEA.
  User: boriska
  Date: 23.09.18
  Time: 20:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${changeLanguage}"/>
<fmt:setBundle basename="resources.pagecontent"/>
<html>
<head>
    <meta charset="UTF-8">
    <link href="css/reset.css" rel="stylesheet" type="text/css"/>
    <title><fmt:message key="label.confirmTitle"/></title>
    <link href="/assets/css/auth/confirmpage.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<form id="login" action="/controller" name="command" method="post">
    <h1><fmt:message key="label.confirmMessage"/></h1>
    <fieldset id="inputs">
        <input id="code" name="confirmCode" type="text" placeholder="<fmt:message key="label.enterYourCode"/>" autofocus
               required>
        <error>${errorWrongConfirmCode}</error>
        <input type="hidden" name="command" value="Confirm"/>
        <input id="submit" type="submit" value="<fmt:message key="label.confirmCode"/>"/>
    </fieldset>
</form>
</body>
</html>
