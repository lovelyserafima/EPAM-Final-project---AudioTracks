<%--
  Created by IntelliJ IDEA.
  User: boriska
  Date: 13.10.18
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${changeLanguage}"/>
<fmt:setBundle basename="resources.pagecontent"/>
<html>
<head>
    <title><fmt:message key="label.readMore"/></title>
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
                <li class="active"><a href="#">Audio</a></li>
                <li><a href="#">Users</a></li>
                <li><a href="#">Log out</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row content">
        <div class="col-sm-3 sidenav hidden-xs">
            <h2>LYUPAudio</h2>
            <ul class="nav nav-pills nav-stacked">
                <li><a href="/controller?command=main"><fmt:message key="label.main"/></a></li>
                <li class="active"><a href="/controller?command=Search"><fmt:message key="label.audio"/></a></li>
                <li><a href="/controller?command=media library"><fmt:message key="label.users"/></a></li>
                <li><a href="/controller?command=log out"><fmt:message key="label.logOut"/></a></li>
            </ul><br>
        </div>
        <br>

        <div class="col-sm-9">
            <div class="well">
                <form action="/controller" method="post">
                    <h4><fmt:message key="label.infoAboutTrack"/></h4>
                    <table class="table">
                        <thead class="text-primary">
                        <tr>
                            <th></th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <th>#</th>
                            <th>${audioTrack.id}</th>
                        </tr>
                        <tr>
                            <th><fmt:message key="label.name"/></th>
                            <th>${audioTrack.name}</th>
                        </tr>
                        <tr>
                            <th><fmt:message key="label.band"/></th>
                            <th>${audioTrack.band}</th>
                        </tr>
                        <tr>
                            <th><fmt:message key="label.album"/></th>
                            <th>${audioTrack.album}</th>
                        </tr>
                        <tr>
                            <th><fmt:message key="label.year"/></th>
                            <th>${audioTrack.year}</th>
                        </tr>
                        <tr>
                            <th><fmt:message key="label.price"/></th>
                            <th>${audioTrack.price}</th>
                        </tr>
                        <tr>
                            <th><fmt:message key="label.listenToDemoVersion"/></th>
                            <th>
                                <audio controls>
                                    <source src=${audioTrack.demoAudioPath} type="audio/mpeg">
                                </audio>
                            </th>
                        </tr>
                        <tr>
                            <th><fmt:message key="label.listenToFullVersion"/></th>
                            <th>
                                <audio controls>
                                    <source src=${audioTrack.fullAudioPath} type="audio/mpeg">
                                </audio>
                            </th>
                        </tr>
                        </tbody>
                    </table>
                    <h4><fmt:message key="label.usersHavingTrack"/></h4>
                    <p class="text-danger">${noUsers}</p>
                    <table class="table">
                        <thead class="text-primary">
                        <tr>
                            <th>#</th>
                            <th><fmt:message key="label.login"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.users}" var="user">
                            <tr>
                                <td>${user.id}</td>
                                <td>${user.login}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
    </div>
</div>
</div>

</body>
</html>