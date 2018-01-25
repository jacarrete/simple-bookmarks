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
        .blue-button{
            background: #25A6E1;
            filter: progid: DXImageTransform.Microsoft.gradient( startColorstr='#25A6E1',endColorstr='#188BC0',GradientType=0);
            padding:3px 5px;
            color:#fff;
            font-family:'Helvetica Neue',sans-serif;
            font-size:12px;
            border-radius:2px;
            -moz-border-radius:2px;
            -webkit-border-radius:4px;
            border:1px solid #1A87B9
        }
        table {
            font-family: "Helvetica Neue", Helvetica, sans-serif;
            width: 50%;
            margin-top: 100px;
        }
        th {
            background: SteelBlue;
            color: white;
        }
        td,th{
            border: 1px solid gray;
            width: 25%;
            text-align: left;
            padding: 5px 10px;
        }
    </style>
</head>
<body>
<div>
    <h2>Welcome: ${username}</h2>
    <form action="/logout" method="post">
        <button type="submit" class="btn btn-danger">Log Out</button>
        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
    </form>
</div>

<h3>Bookmarks List</h3>
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
    </br></br>
    <table class="tg">
        <tr>
            <th width="80">Id</th>
            <th width="120">Bookmark Name</th>
            <th width="120">Address</th>
            <th width="120">Initials</th>
            <th width="120">Show Initials</th>
            <th width="120">Color</th>
            <th width="120">Image Name</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
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
                <td><a href="<c:url value='/updateBookmark/${bookmark.id}' />" >Edit</a></td>
                <td><a href="<c:url value='/deleteBookmark/${bookmark.id}' />" >Delete</a></td>
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
</br></br>
<form:form method="post" modelAttribute="bookmark" action="${pageContext.request.contextPath}/addBookmark" enctype="multipart/form-data">
    <table>
        <tr>
            <th colspan="2">Add Bookmark</th>
        </tr>
        <tr>
            <form:hidden path="id" />
            <td><form:label path="name">Bookmark Name:</form:label></td>
            <td><form:input path="name" size="30" maxlength="30" required="true"></form:input></td>
        </tr>
        <tr>
            <td><form:label path="address">Address:</form:label></td>
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
            <td><form:label path="initials">Initials:</form:label></td>
            <td><form:input path="initials" size="6" maxlength="6"></form:input></td>
        </tr>
        <tr>
            <td><form:label path="showText">Show Initials:</form:label></td>
            <td><form:checkbox path="showText"></form:checkbox></td>
        </tr>
        <tr>
            <td><form:label path="color">Color:</form:label></td>
            <td><input type="color" id="color" name="color" value=${bookmark.color}></td>
        </tr>
        <tr>
            <td>Image:</td>
            <td><input type="file" name="image" size="50"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" class="blue-button"/></td>
        </tr>
        <tr>
            <c:if test="${!empty id}">
                <td><a href="<c:url value='/getAllBookmarks'/>" >Cancel</a></td>
            </c:if>
        </tr>
    </table>
</form:form>
</body>
</html>
