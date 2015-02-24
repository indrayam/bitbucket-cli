<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link
	href="${pageContext.request.contextPath}/resources/css/cimvMain.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/css/main.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/theme.css"
	rel="stylesheet">

<title>Error</title>
</head>
<body>
	<div id="wrap">
		<div id="top">
			<nav class="navbar navbar-inverse navbar-fixed-top">
				<div class="container-fluid">
					<!-- Brand and toggle get grouped for better mobile display -->
					<header class="navbar-header">
						<a class="navbar-brand"
							href="${pageContext.request.contextPath}/home"> <img
							src="${pageContext.request.contextPath}/resources/images/logo.png"
							class="logo" />
						</a>
						<div
							style="float: left; height: 50px; padding: 2px; padding-top: 12px;">
							<a class="navbar-brand"
								href="${pageContext.request.contextPath}/home"> <font
								color="white" size="5">&nbsp;&nbsp;Continuous Delivery
									Dashboard</font> <font color="white" size="2">
									&nbsp;&nbsp;Code. Build. Analyze.</font>
							</a>
						</div>
					</header>
					<div class="collapse navbar-collapse navbar-ex1-collapse">
						<!-- .nav -->
						<!-- /.nav -->
					</div>
				</div>
				<!-- /.container-fluid -->
			</nav>
			<!-- /.navbar -->
		</div>
		<div class="container">
			<div class="text-center" style="margin-top: 250px; color: white;">
				<!-- Content -->
				<h3>Try again...</h3>
				<a href="${pageContext.request.contextPath}">Click here</a>
				<!-- / Content -->
			</div>
		</div>
	</div>
</body>
</html>