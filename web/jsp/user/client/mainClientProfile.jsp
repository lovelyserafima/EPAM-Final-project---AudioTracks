<%--
  Created by IntelliJ IDEA.
  User: boriska
  Date: 22.09.18
  Time: 21:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${changeLanguage}"/>
<fmt:setBundle basename="resources.pagecontent"/>
<html>
<head>
    <title><fmt:message key="label.profile"/></title>
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
                <li><a href="#">MediaLibrary</a></li>
                <li class="active"><a href="#">Profile</a></li>
                <li><a href="#">Basket</a></li>
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
                <li><a href="/controller?command=media library"><fmt:message key="label.mediaLibrary"/></a></li>
                <li class="active"><a href="/controller?command=profile"><fmt:message key="label.profile"/></a></li>
                <li><a href="/controller?command=basket"><fmt:message key="label.basketTitle"/></a></li>
                <li><a href="/controller?command=log out"><fmt:message key="label.logOut"/></a></li>
            </ul><br>
        </div>
        <br>

        <div class="col-sm-9">
            <div class="well">
                <table class="table">
                    <thead class="text-primary">
                    <tr>
                        <th><fmt:message key="label.parametres"/></th>
                        <th><fmt:message key="label.values"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <th>Email</th>
                        <th>${user.email}</th>
                    </tr>
                    <tr>
                        <th><fmt:message key="label.login"/></th>
                        <th>${user.login}</th>
                    </tr>
                    <tr>
                        <th><fmt:message key="label.firstName"/></th>
                        <th>${user.firstName}</th>
                    </tr>
                    <tr>
                        <th><fmt:message key="label.secondName"/></th>
                        <th>${user.secondName}</th>
                    </tr>
                    <tr>
                        <th><fmt:message key="label.balance"/></th>
                        <th>${user.money}</th>
                    </tr>
                    </tbody>
                </table>
                <p class="text-success">${resultChanging}</p>
                <br>
                <form action="/controller" name="command" method="post">
                    <button type="submit" value="Edit password" name="command">
                        <fmt:message key="label.changePassword"/>
                    </button>
                    <button type="submit" value="Edit parametres" name="command">
                        <fmt:message key="label.editParametres"/>
                    </button>
                    <button type="submit" name="command" value="Top up account">
                        <fmt:message key="label.topUpAccount"/>
                    </button>
                </form>
            </div>
        </div>
    </div>
</div>
</div>

</body>
</html>
