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
    <link href="bookmarks.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

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
            <li class="active"><a href="/getAllBookmarks"><spring:message code="manage.bookmarks"/></a></li>
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

<form:form method="post" modelAttribute="bookmark" action="${pageContext.request.contextPath}/addBookmark" enctype="multipart/form-data">
    <table>
        <tr>
            <th colspan="2"><spring:message code="bookmark.add"/></th>
        </tr>
        <tr>
            <form:hidden path="id" />
            <td><form:label path="name"><spring:message code="bookmark.name"/></form:label></td>
            <td><form:input path="name" size="30" maxlength="30" required="true"></form:input></td>
        </tr>
        <tr>
            <td><form:label path="address"><spring:message code="bookmark.address"/></form:label></td>
            <c:choose>
                <c:when test="${!empty bookmark.address}">
                    <td><input type="url" name="address" size="30" maxlength="30" value=${bookmark.address} required></td>
                </c:when>
                <c:otherwise>
                    <td><input type="url" name="address" size="30" maxlength="30" placeholder="http://www.url.com" required></td>
                </c:otherwise>
            </c:choose>
        </tr>
        <tr>
            <td><form:label path="initials"><spring:message code="bookmark.initials"/></form:label></td>
            <td><form:input path="initials" size="6" maxlength="6"></form:input></td>
        </tr>
        <tr>
            <td><form:label path="showText"><spring:message code="bookmark.show.initials"/></form:label></td>
            <td><form:checkbox path="showText"></form:checkbox></td>
        </tr>
        <tr>
            <td><form:label path="color"><spring:message code="bookmark.color"/></form:label></td>
            <td><input type="color" id="color" name="color" value=${bookmark.color}></td>
        </tr>
        <tr>
            <td><spring:message code="bookmark.image"/></td>
            <td><input type="file" name="image" size="50"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" class="blue-button"/></td>
        </tr>
        <tr>
            <c:if test="${!empty id}">
                <td><a href="<c:url value='/getAllBookmarks'/>" ><spring:message code="cancel"/></a></td>
            </c:if>
        </tr>
    </table>
</form:form>
<h3 style="margin-left: 10px"><spring:message code="bookmark.list"/></h3>
<c:if test="${!empty listOfBookmarks}">
    <table class="tg">
        <tr>
            <th width="80">Id</th>
            <th width="120"><spring:message code="bookmark.name_"/></th>
            <th width="120"><spring:message code="bookmark.address_"/></th>
            <th width="120"><spring:message code="bookmark.initials_"/></th>
            <th width="120"><spring:message code="bookmark.show.initials_"/></th>
            <th width="120"><spring:message code="bookmark.color_"/></th>
            <th width="120"><spring:message code="bookmark.image.name_"/></th>
            <th width="60"><spring:message code="edit"/></th>
            <th width="60"><spring:message code="delete"/></th>
            <th width="120"></th>
        </tr>
        <c:forEach items="${listOfBookmarks}" var="bookmark">
            <tr>
                <td>${bookmark.id}</td>
                <td>${bookmark.name}</td>
                <td><a href='${bookmark.address}' target="_blank">${bookmark.address}</a></td>
                <td>${bookmark.initials}</td>
                <td>${bookmark.showText}</td>
                <td>${bookmark.color}</td>
                <td>${bookmark.imageName}</td>
                <td><a href="<c:url value='/updateBookmark/${bookmark.id}' />" ><spring:message code="edit"/></a></td>
                <td><a href="<c:url value='/deleteBookmark/${bookmark.id}' />" ><spring:message code="delete"/></a></td>
                <td><c:choose>
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
                </c:choose></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

</body>
</html>
