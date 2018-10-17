<%--
  Created by IntelliJ IDEA.
  User: boriska
  Date: 03.10.18
  Time: 0:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${changeLanguage}"/>
<fmt:setBundle basename="resources.pagecontent"/>
<html>
<head>
    <title><fmt:message key="label.editParametres"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="/assets/css/client/client.css" type="text/css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-inverse visible-xs">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">LYUPAudio</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li><a href="#">Main</a></li>
                <li><a href="#">Search</a></li>
                <li class="active"><a href="#">Profile</a></li>
                <li><a href="#">Settings</a></li>
                <li><a href="#">Log out</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row content">
        <div class="col-sm-3 sidenav hidden-xs">
            <h2>LYUPAudio</h2>
            <h4><fmt:message key="label.client"/></h4>
            <ul class="nav nav-pills nav-stacked">
                <li><a href="/controller?command=main"><fmt:message key="label.main"/></a></li>
                <li><a href="/controller?command=search"><fmt:message key="label.search"/></a></li>
                <li class="active"><a href="/controller?command=profile"><fmt:message key="label.profile"/></a></li>
                <li><a href="#section3"><fmt:message key="label.settings"/></a></li>
                <li><a href="/controller?command=log out"><fmt:message key="label.logOut"/></a></li>
            </ul><br>
        </div>
        <br>
        <form action="/controller" method="post">
            <div class="col-sm-9">
                <div class="well">
                    <table class="table">
                        <thead class="text-primary">
                        <tr>
                            <th></th>
                            <th></th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <th>Email</th>
                            <th><input type="email" name="email" value="${user.email}" required></th>
                            <th><p class="text-danger">${errorWrongEmail}</p></th>
                        </tr>
                        <tr>
                            <th><fmt:message key="label.login"/></th>
                            <th><input type="text" name="login" value="${user.login}" required maxlength="20"></th>
                            <th><p class="text-danger">${wrongLogin}</p></th>
                        </tr>
                        <tr>
                            <th><fmt:message key="label.firstName"/></th>
                            <th><input type="text" name="firstName" value="${user.firstName}" required maxlength="255"></th>
                        </tr>
                        <tr>
                            <th><fmt:message key="label.secondName"/></th>
                            <th><input type="text" name="secondName" value="${user.secondName}" required maxlength="255" ></th>
                        </tr>
                        </tbody>
                    </table>
                    <button type="submit" name="command" value="Confirm editing parametres">
                        <fmt:message key="label.editParametres"/>
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>

</body>
</html>
