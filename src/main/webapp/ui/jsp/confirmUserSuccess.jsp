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
    <link href="bookmarks.css" rel="stylesheet">
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
            <li><a href="/bookmarkList"><spring:message code="home"/></a></li>
            <li><a href="/getAllBookmarks"><spring:message code="manage.bookmarks"/></a></li>
            <li><a href="javascript:donate()"><spring:message code="donate"/></a></li>
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Rest APIS <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/restBookmarks.json"><spring:message code="bookmarks.list.json"/></a></li>
                    <li><a href="/restBookmarks.xml"><spring:message code="bookmarks.list.xml"/></a></li>
                </ul>
            </li>
            <c:if test="${loggedUser == 'javier'}">
                <li class="active"><a href="/confirmUserForm"><spring:message code="user.confirmation"/></a></li>
            </c:if>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><spring:message code="lang"/><span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                    <li><a href="?lang=en" class="language" rel="en-GB"><img style="width:25px;height:25px" src="https://lipis.github.io/flag-icon-css/flags/4x3/gb.svg" alt="English" /><spring:message code="lang.en"/></a></li>
                    <li><a href="?lang=es" class="language" rel="es-ES"><img style="width:25px;height:25px" src="https://lipis.github.io/flag-icon-css/flags/4x3/es.svg" alt="Spanish" /><spring:message code="lang.es"/></a></li>
                </ul>
            </li>
            <li><a href="#"><span class="glyphicon glyphicon-user"></span> ${loggedUser}</a></li>
            <li><a href="javascript:logout()"><span class="glyphicon glyphicon-log-in"></span> <spring:message code="logout"/></a></li>
        </ul>
    </div>
</nav>

<form name="logoutForm" action="/logout" method="post">
    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
</form>

<donate:donate/>

<c:choose>
    <c:when test="${loggedUser == 'javier'}">
        <h2 style="margin-top: 100px; text-align: center;"><spring:message code="success.confirmation"/></h2>
    </c:when>
    <c:otherwise>
        <h2 style="margin-top: 100px; text-align: center;"><spring:message code="permissions.confirmation"/></h2>
    </c:otherwise>
</c:choose>

</body>
</html>
