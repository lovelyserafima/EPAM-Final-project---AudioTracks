<%--
  Created by IntelliJ IDEA.
  User: boriska
  Date: 28.09.18
  Time: 13:18
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: boriska
  Date: 22.09.18
  Time: 21:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${changeLanguage}"/>
<fmt:setBundle basename="resources.pagecontent"/>
<html>
<head>
    <title><fmt:message key="label.mainPageTitleSearch"/></title>
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
                <li class="active"><a href="#">Search</a></li>
                <li><a href="#">MediaLibrary</a></li>
                <li><a href="#">Profile</a></li>
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
            <ul class="nav nav-pills nav-stacked">
                <li><a href="/controller?command=main"><fmt:message key="label.main"/></a></li>
                <li class="active"><a href="/controller?command=search"><fmt:message key="label.search"/></a></li>
                <li><a href="/controller?command=media library"><fmt:message key="label.mediaLibrary"/></a></li>
                <li><a href="/controller?command=profile"><fmt:message key="label.profile"/></a></li>
                <li><a href="/controller?command=basket"><fmt:message key="label.basketTitle"/></a></li>
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
                <button type="submit" name="command" value="Search music"><fmt:message key="label.search"/></button>
                <h4><fmt:message key="label.allSongs"/></h4>
                <table class="table">
                    <thead class="text-primary">
                    <tr>
                        <th>#</th>
                        <th><fmt:message key="label.name"/></th>
                        <th><fmt:message key="label.album"/></th>
                        <th><fmt:message key="label.band"/></th>
                        <th><fmt:message key="label.year"/></th>
                        <th><fmt:message key="label.price"/></th>
                        <th><fmt:message key="label.listenToDemoVersion"/></th>
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
                        <td>${audioTrack.album}</td>
                        <td>${audioTrack.year}</td>
                        <td>${audioTrack.price}</td>
                        <td>
                            <audio controls>
                            <source src=${audioTrack.demoAudioPath} type="audio/mpeg">
                            </audio>
                        </td>
                        <td><a href="/controller?command=Add to basket&trackId=${audioTrack.id}">
                            <fmt:message key="label.addToBasket"/>
                        </a></td>
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
