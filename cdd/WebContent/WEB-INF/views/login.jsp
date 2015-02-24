<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>dashboard login</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.min.css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/cimvMain.css" />
<link type="image/x-icon" rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/tabIconBW.png">

</head>

<body class="login">
	<div class="container">
		<div class="text-center">
			<img style="width : 150px; height: 100px" src="${pageContext.request.contextPath}/resources/images/cisco.png" alt="cisco Logo">
		</div>
		<div class="tab-content">
			<div id="login" class="tab-pane active">
				<form method="post" action="<c:url value="/validate" />" class="form-signin">
					<p class="text-muted text-center">Enter your cec user id and password</p>
					<fieldset class="form">
						<div class="form-signin">
							<div>
								<div>
									<input type="text" name="userId" id="userId" title="Enter CEC User Id" autofocus="autofocus"
										class="form-control" required/>
								</div>
								<div>
									<input type="password" name="password" id="password" class="form-control" />
								</div>
								<c:if test="${errorMessage != 'null' }">
									<div>
										<p align="center" style="color:red; font-weight: bold;">${errorMessage}</p>
									</div>
								</c:if>
							</div>
						</div>
					</fieldset>
					<fieldset class="buttons">
						<div>
							<input type="submit" name="create" 
								class="btn btn-lg btn-primary btn-block" value="Login" />
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
	<!-- /container -->
</body>
</html>