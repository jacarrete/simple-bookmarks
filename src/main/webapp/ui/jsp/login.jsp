<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
	<title>Simple BookMarks</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
			<li><a href="/registration"><span class="glyphicon glyphicon-user"></span> <spring:message code="sign.up"/></a></li>
		</ul>
	</div>
</nav>

	<div class="container" style="margin: 50px;border: 1px solid green;">
		<h3><spring:message code="login.page"/></h3>
		<c:if test="${param.error ne null}">
			<div style="color: red"><spring:message code="invalid.credentials"/></div>
		</c:if>
		<form action="/login" method="post">
			<div class="form-group">
				<label for="username"><spring:message code="username"/></label> <input type="text"
					class="form-control" id="username" name="username">
			</div>
			<div class="form-group">
				<label for="pwd"><spring:message code="password"/></label> <input type="password"
					class="form-control" id="pwd" name="password">
			</div>

			<button type="submit" class="btn btn-success"><spring:message code="submit"/></button>

			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
	</div>
</body>
</html>