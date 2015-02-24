<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div id="top">
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<header class="navbar-header">
				<a class="navbar-brand" href="${pageContext.request.contextPath}/home"> <img
					src="${pageContext.request.contextPath}/resources/images/logo.png"
					class="logo" />
				</a>
				<div style="float: left; height: 50px; padding:2px; padding-top:12px;">
				<a class="navbar-brand" href="${pageContext.request.contextPath}/home">
				 	<font color="white" size="5">&nbsp;&nbsp;Continuous Delivery Dashboard</font>
					<font color="white" size="2"> &nbsp;&nbsp;Code. Build. Analyze.</font>	 
				</a>
				</div>
				<%-- <div style="float: left; height: 50px; padding:2px; padding-left: 10px;">
				<a class="navbar-brand" href="http://dft-mongo.cisco.com:8080/cimv-dashboard/home">
				 <img
					src="${pageContext.request.contextPath}/resources/images/cimvLogo.png"
					class="logo" />
				</a>
				</div> --%>
			</header>
			<div class="topnav">
				<div class="btn-group" style="color: white;">
					<% String userId = (String)session.getAttribute("userName"); %>
					<%=userId%>&nbsp;&nbsp;&nbsp;&nbsp;
				</div>
				<div class="btn-group">
					<a data-toggle="modal" data-original-title="Help" title="Help"
						data-placement="bottom" class="btn btn-default btn-sm"
						href="#helpModal"> <i class="fa fa-question"></i>
					</a>
				</div>
				<div class="btn-group">
					<spring:url value="/logout" var="goToUrl">
					</spring:url>
					<a href="${goToUrl}" class="btn btn-metis-1 btn-sm" title="Logout">
						<i class="fa fa-power-off"></i>
					</a>
				</div>
			</div>
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<!-- .nav -->
				<!-- /.nav -->
			</div>
		</div>
		<!-- /.container-fluid -->
	</nav>
	<!-- /.navbar -->
</div>
<!-- / Static navbar -->
	
	<!-- Modal --><div id="helpModal" class="modal fade" style="top: 25%">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">&times;</button>
            <h4 class="modal-title">Help</h4>
          </div>
          <div class="modal-body">
            <p>
				For further help feel free to reach the development team at : <br><br>
				<a href="mailto:sdlc-dev-team@cisco.com?subject=Feedback">sdlc-dev-team@cisco.com</a>
            </p>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal --><!-- /#helpModal -->
