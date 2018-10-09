<%-- Created by IntelliJ IDEA. --%>

<%@ page contentType="text/html;charset=utf-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${changeLanguage}"/>
<fmt:setBundle basename="resources.pagecontent" var="var"/>
<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="label.title" bundle="${var}"/></title>
    <link href="/assets/css/auth/loginpage.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div>LYUPAudio</div>
<form id="login" action="/controller" name="command" method="post">
    <h1><fmt:message key="label.welcome" bundle="${var}"/></h1>
    <fieldset id="inputs">
        <input id="email" name="email" type="text" placeholder="Email" autofocus required>
        <input id="password" name="password" type="password" placeholder="<fmt:message key="label.password"
        bundle="${var}"/>" required>
    </fieldset>
    <error>${errorSignInMessage}</error>
    <fieldset id="actions">
        <input type="hidden" name="command" value="Sign in"/>
        <input type="submit" id="submit" value="<fmt:message key="label.signIn" bundle="${var}"/>"/>
        <a href="/controller?command=sign up"><fmt:message key="label.signUp" bundle="${var}"/></a>
    </fieldset>
    <br>
    <a href="/controller?command=english">EN</a>
    <a href="/controller?command=russian">RU</a>
</form>
</body>
</html>
