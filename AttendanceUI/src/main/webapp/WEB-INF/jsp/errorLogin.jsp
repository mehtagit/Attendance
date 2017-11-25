<!DOCTYPE html>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>

<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

<!-- 
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->
<c:url value="/css/main.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />

</head>
<body>

	<!-- <nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Spring Boot</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Home</a></li>
					<li><a href="#about">About</a></li>
				</ul>
			</div>
		</div>
	</nav> -->
	<div class="container">

		<div class="row" style="margin-top: 20px">
			<div
				class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
				<spring:url value="/login" var="loginUrl" />
				<form:form class="form-horizontal" method="post"
					modelAttribute="industryForm" action="${loginUrl}">
					<fieldset>
						<h2>${message}</h2>
						<hr class="colorgraph">
						<div class="form-group">
							<spring:bind path="industry_id">
								<form:input path="industry_id" class="form-control" id="industry_id"
									placeholder="Email" />
								<form:errors path="industry_id" class="control-label" />

							</spring:bind>
						</div>
						<div class="form-group">
							<spring:bind path="registration_pin">
								<form:password path="registration_pin" class="form-control"
									id="registration_pin" placeholder="registration_pin" />
								<form:errors path="industry_id" class="control-label" />

							</spring:bind>

						</div>
						<span class="button-checkbox">
							<button type="button" class="btn" data-color="info">Remember
								Me</button> <input type="checkbox" name="remember_me" id="remember_me"
							checked="checked" class="hidden"> <a href=""
							class="btn btn-link pull-right">Forgot Password?</a>
						</span>
						<hr class="colorgraph">
						<div class="row">
							<div class="col-xs-6 col-sm-6 col-md-6">
								<c:choose>
									<c:when test="${industryForm['new']}">
										<button type="submit" class="btn btn-lg btn-primary btn-block">Login
										</button>
									</c:when>
									<c:otherwise>
										<button type="submit" class="btn btn-lg btn-primary btn-block">Update
										</button>
									</c:otherwise>
								</c:choose>
							</div>
							<div class="col-xs-6 col-sm-6 col-md-6">
								<a href="" class="btn btn-lg btn-primary btn-block">Register</a>
							</div>
						</div>
					</fieldset>
				</form:form>
			</div>
		</div>

	</div>
	<%-- <div class="container">

		<div class="starter-template">
			<h1>Spring Boot Web JSP Example</h1>
			<h2>Message: ${message}</h2>
		</div>

	</div>
	<!-- /.container -->

	<script type="text/javascript"
		src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script> --%>

</body>

</html>
