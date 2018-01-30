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
<html>
<head>
    <title>Simple BookMarks</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link href="bookmarks.css" rel="stylesheet">
    <style>
        .error
        {
            color: #ff0000;
            font-weight: bold;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Simple BookMarks</a>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><spring:message code="lang"/><span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                    <li><a href="?lang=en" class="language" rel="en-GB"><img style="width:25px;height:25px" src="https://lipis.github.io/flag-icon-css/flags/4x3/gb.svg" alt="English" /><spring:message code="lang.en"/></a></li>
                    <li><a href="?lang=es" class="language" rel="es-ES"><img style="width:25px;height:25px" src="https://lipis.github.io/flag-icon-css/flags/4x3/es.svg" alt="Spanish" /><spring:message code="lang.es"/></a></li>
                </ul>
            </li>
            <li><a href="/login"><span class="glyphicon glyphicon-user"></span> <spring:message code="login"/></a></li>
        </ul>
    </div>
</nav>
<form:form method="post" modelAttribute="user" action="${pageContext.request.contextPath}/addUser">
    <table>
        <tr>
            <th colspan="2">Request new user:</th>
        </tr>
        <tr>
            <td><form:label path="username"><spring:message code="username"/></form:label></td>
            <td><form:input path="username" size="20" maxlength="20"></form:input><form:errors path="username" cssClass="error" /></td>
        </tr>
        <tr>
            <td><form:label path="password"><spring:message code="password"/></form:label></td>
            <td><form:password path="password" size="20" maxlength="20"></form:password><form:errors path="password" cssClass="error" /></td>
        </tr>
        <tr>
            <td><form:label path="email"><spring:message code="email"/></form:label></td>
            <td><form:input path="email" size="30" maxlength="30"></form:input><form:errors path="email" cssClass="error" /></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit"
                                   class="blue-button" /></td>
        </tr>
    </table>
</form:form>
</body>
</html>
