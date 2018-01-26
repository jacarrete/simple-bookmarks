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
            <li class="active"><a href="/bookmarkList">Home</a></li>
            <li><a href="/getAllBookmarks">Manage Bookmarks</a></li>
            <li><a href="javascript:donate()">Donate</a></li>
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Rest APIS <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/restBookmarks">Bookmarks List</a></li>
                </ul>
            </li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="#"><span class="glyphicon glyphicon-user"></span> ${username}</a></li>
            <li><a href="javascript:logout()"><span class="glyphicon glyphicon-log-in"></span> Logout</a>
            </li>
        </ul>
    </div>
</nav>

<form name="logoutForm" action="/logout" method="post">
    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
</form>
<form name="donate" action="https://www.paypal.com/cgi-bin/webscr" method="post">
    <!-- Identify your business so that you can collect the payments. -->
    <input type="hidden" name="business"
           value="javi_1986@hotmail.com">
    <!-- Specify a Donate button. -->
    <input type="hidden" name="cmd" value="_donations">
    <!-- Specify details about the contribution -->
    <input type="hidden" name="item_name" value="Simple BookMarks Web">
    <input type="hidden" name="item_number" value="Donation">
    <input type="hidden" name="amount" value="25.00">
    <input type="hidden" name="currency_code" value="GBP">
    <!-- Display the payment button. -->
    <%--<input type="image" name="submit" src="https://www.paypalobjects.com/en_US/i/btn/btn_donate_LG.gif" alt="Donate">--%>
    <img alt="" width="1" height="1" src="https://www.paypalobjects.com/en_US/i/scr/pixel.gif" >
</form>

<h3 style="margin-left: 10px">Bookmarks</h3>
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
