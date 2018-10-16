<%--
  Created by IntelliJ IDEA.
  User: boriska
  Date: 13.10.18
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${changeLanguage}"/>
<fmt:setBundle basename="resources.pagecontent"/>
<html>
<head>
    <title><fmt:message key="label.audio"/></title>
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
                <li><a href="/controller?command=log out"><fmt:message key="label.logOut"/></a></li>
            </ul><br>
        </div>
        <br>

        <div class="col-sm-9">
            <div class="well">
                <form action="/controller" method="post">
                    <input type="text" class="form-control" name="searchingItem"
                           placeholder="<fmt:message key="label.searchByNamePerformerAlbum"/>">
                    <br>
                    <p class="text-success">${goodResultOfAdding}</p>
                    <button type="submit" name="command" value="Search music"><fmt:message key="label.search"/></button>
                    <button type="submit" name="command" value="Add track">
                        <fmt:message key="label.addNewTrack"/>
                    </button>
                    <button type="submit" name="command" value="Add album">
                        <fmt:message key="label.addNewAlbum"/>
                    </button>
                    <h4><fmt:message key="label.allSongs"/></h4>
                    <table class="table">
                        <thead class="text-primary">
                        <tr>
                            <th>#</th>
                            <th><fmt:message key="label.name"/></th>
                            <th><fmt:message key="label.band"/></th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <p class="text-danger">${resultOfSearching}</p>
                        <p class="text-success">${resultOfAddingToBasket}</p>
                        <c:forEach items="${requestScope.audioTracks}" var="audioTrack">
                            <tr>
                                <td>${audioTrack.id}</td>
                                <td>${audioTrack.name}</td>
                                <td>${audioTrack.band}</td>
                                <td>
                                    <a href="/controller?command=read more&trackId=${audioTrack.id}">
                                        <fmt:message key="label.readMore"/>
                                    </a>
                                </td>
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