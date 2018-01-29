<%--
  Created by IntelliJ IDEA.
  User: jcarretero
  Date: 23/01/2018
  Time: 15:34
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.bookmarks/donateTagLib" prefix="donate" %>
<html>
<head>
    <title>Simple BookMarks</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        .rectangle{
            width: 90px;
            height: 90px;
            border: 2px solid purple;
            display: block;
            float: left;
            margin-left: 20px;
            margin-right: 20px;
            text-align: center;
            background-color: white;
            margin-bottom: 20px;
        }
    </style>

    <script language="JavaScript" type="text/javascript">
        function logout ( ) {
            document.logoutForm.submit() ;
        }
    </script>
    <script language="JavaScript" type="text/javascript">
        function donate ( ) {
            document.donate.submit() ;
        }
    </script>
</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/bookmarkList">Simple BookMarks</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="/bookmarkList"><spring:message code="home"/></a></li>
            <li><a href="/getAllBookmarks"><spring:message code="manage.bookmarks"/></a></li>
            <li><a href="javascript:donate()"><spring:message code="donate"/></a></li>
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Rest APIS <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/restBookmarks"><spring:message code="bookmarks.list"/></a></li>
                </ul>
            </li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><spring:message code="lang"/><span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                    <li><a href="?lang=en" class="language" rel="en-GB"><img style="width:25px;height:25px" src="https://lipis.github.io/flag-icon-css/flags/4x3/gb.svg" alt="English" /><spring:message code="lang.en"/></a></li>
                    <li><a href="?lang=es" class="language" rel="es-ES"><img style="width:25px;height:25px" src="https://lipis.github.io/flag-icon-css/flags/4x3/es.svg" alt="Spanish" /><spring:message code="lang.es"/></a></li>
                </ul>
            </li>
            <li><a href="#"><span class="glyphicon glyphicon-user"></span> ${username}</a></li>
            <li><a href="javascript:logout()"><span class="glyphicon glyphicon-log-in"></span> <spring:message code="logout"/></a></li>
        </ul>
    </div>
</nav>

<form name="logoutForm" action="/logout" method="post">
    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
</form>

<donate:donate/>

<h3 style="margin-left: 10px"><spring:message code="bookmarks"/></h3>
<c:if test="${!empty listOfBookmarks}">
    <c:forEach items="${listOfBookmarks}" var="bookmark">
        <c:choose>
            <c:when test="${!empty bookmark.imageName}">
                <a href="${bookmark.address}" target="_blank"><img src="/images/${bookmark.imageName}" class="rectangle" /></a>
            </c:when>
            <c:when test="${!empty bookmark.color && bookmark.showText}">
                <a href="${bookmark.address}" target="_blank" class="rectangle" style="background-color:${bookmark.color};" >${bookmark.initials}</a>
            </c:when>
            <c:when test="${!empty bookmark.color && !bookmark.showText}">
                <a href="${bookmark.address}" target="_blank" class="rectangle" style="background-color:${bookmark.color};" ></a>
            </c:when>
            <c:otherwise>
                <a href="${bookmark.address}" target="_blank" class="rectangle"></a>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</c:if>

</body>
</html>
