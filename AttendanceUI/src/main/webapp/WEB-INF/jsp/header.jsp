<!DOCTYPE html>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<title>Collapsible sidebar using Bootstrap 3</title>

<!-- Bootstrap CSS CDN -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- Our Custom CSS -->
<%-- <c:url value="/css/style4.css" var="jstlCss" /> --%>
<link rel="stylesheet"
	href="https://bootstrapious.com/tutorial/sidebar/style4.css">
</head>
<body>



	<div class="wrapper">
		<!-- Sidebar Holder -->
		<nav id="sidebar">
			<div class="sidebar-header">
				<h3>${industry.name}</h3>
				<strong>AA</strong>
			</div>

			<ul class="list-unstyled components">
				<li class="active"><a href="#"> <i
						class="glyphicon glyphicon-home"></i> Home
				</a></li>
				<li><a href="<c:url value="/attendance" />"> <i
						class="glyphicon glyphicon-briefcase"></i> Attendance
				</a> <a href="<c:url value="/salary" />"> <i
						class="glyphicon glyphicon-briefcase"></i> Salary
				</a> <a href="<c:url value="/advancePayment" />"> <i
						class="glyphicon glyphicon-briefcase"></i> Advance Payment
				</a> <a href="<c:url value="/employees" />"> <i
						class="glyphicon glyphicon-briefcase"></i> Employees
				</a> <a href="<c:url value="/fullAndFinal" />"> <i
						class="glyphicon glyphicon-briefcase"></i> Full and Final
				</a> <a href="#pageSubmenu" data-toggle="collapse" aria-expanded="false">
						<i class="glyphicon glyphicon-duplicate"></i> Reports
				</a>
					<ul class="collapse list-unstyled" id="pageSubmenu">
						<li><a href="#">Report 1</a></li>
						<li><a href="#">Report 2</a></li>
						<li><a href="#">Report 3</a></li>
					</ul></li>
				<li><a href="#"> <i class="glyphicon glyphicon-link"></i>
						Portfolio
				</a></li>
				<li><a href="#"> <i class="glyphicon glyphicon-paperclip"></i>
						FAQ
				</a></li>
				<li><a href="#"> <i class="glyphicon glyphicon-send"></i>
						Logout
				</a></li>
			</ul>

			<ul class="list-unstyled CTAs">
				<li><a
					href="https://bootstrapious.com/tutorial/files/sidebar.zip"
					class="download">Download source</a></li>
				<li><a href="https://bootstrapious.com/p/bootstrap-sidebar"
					class="article">Back to article</a></li>
			</ul>
		</nav>

		<!-- Page Content Holder -->
		<div id="content">

			<nav class="navbar navbar-default">
				<div class="container-fluid no-padding">

					<div class="navbar-header">
						<button type="button" id="sidebarCollapse"
							class="btn btn-info navbar-btn">
							<i class="glyphicon glyphicon-align-left"></i> <span>Toggle
								Sidebar</span>
						</button>
					</div>

					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav navbar-right">
							<!-- <li><a href="#">Page</a></li>
							<li><a href="#">Page</a></li>
							<li><a href="#"></a></li> -->
							<li><a href="#">Logout</a></li>
						</ul>
					</div>
				</div>
			</nav>