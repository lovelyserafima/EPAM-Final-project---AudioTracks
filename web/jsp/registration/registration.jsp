<%--
  Created by IntelliJ IDEA.
  User: boriska
  Date: 16.09.18
  Time: 16:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${changeLanguage}"/>
<fmt:setBundle basename="resources.pagecontent" var="var"/>
<html>
<head>
    <meta charset="UTF-8">
    <link href="css/reset.css" rel="stylesheet" type="text/css"/>
    <title><fmt:message key="label.registrationPage" bundle="${var}"/></title>
    <link href="/assets/css/auth/registrationpage.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<form id="login" action="/controller" name="command" method="post">
    <h1><fmt:message key="label.registrationPage" bundle="${var}"/></h1>
    <fieldset id="inputs">
        <input id="email" name="email" type="text" placeholder="Email" autofocus required>
        <error>${errorWrongEmail}</error>
        <input id="password" name="password" type="password" placeholder="<fmt:message key="label.password"
        bundle="${var}"/>" required>
        <br>
        <error>${errorWrongPassword}</error>
        <input name="samePassword" type="password" placeholder="<fmt:message key="label.repeatYourPassword"
        bundle="${var}"/>" required/>
        <br>
        <error>${errorNotSamePasswords}</error>
        <input name="login" type="text" placeholder="<fmt:message key="label.login" bundle="${var}"/>"
               required/>
        <error>${wrongLogin}</error>
        <input name="firstName" type="text" placeholder="<fmt:message key="label.firstName" bundle="${var}"/>"
               required/>
        <input name="secondName" type="text" placeholder="<fmt:message key="label.secondName" bundle="${var}"/>"
               required/>
        <input type="hidden" name="command" value="Register"/>
        <input id="submit" type="submit" value="<fmt:message key="label.register" bundle="${var}"/>"/>
    </fieldset>
</form>
</body>
</html>
