<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
			</header>
			<div class="topnav">
				<div class="btn-group" style="color: white;">
					<% String userId = (String)session.getAttribute("userName"); %>
					<%=userId%>&nbsp;&nbsp;&nbsp;&nbsp;
				</div>
				
				<!-- sending report on mail using ajax -->
				<!-- <script>
					function mailReport(groupId, groupName) {
						if (confirm("Do you want to mail this report?") == true) {
							var request = $
									.ajax({
										url : '${pageContext.request.contextPath}/mailReport/',
										type : "POST",
										data : {
											groupId : groupId,
											groupName : groupName
										},
										dataType : "html"
									});
							request.done(function(msg) {
								
									});
							request.fail(function(jqXHR, textStatus) {
								alert("Request failed: " + textStatus);
							});
						} else {
							return false;
						}
					}
				</script> -->
				<%-- <div class="btn-group">
					<spring:url value="/mailReport/{groupId}/{groupName}" var="goToUrl">
						<spring:param name="groupId" value="${groupId}" />
						<spring:param name="groupName" value="${groupName}" />
					</spring:url>
					<a onclick="mailReport('${groupId}','${groupName}')" class="btn btn-metis-4 btn-sm" title="Email CI Report for ${groupName}">
						<i class="glyphicon glyphicon-share"></i>
					</a>
				</div> --%>
				<div class="btn-group" data-toggle="modal" data-target="#modalShare">
					<%-- <spring:url value="/mailReport/{groupId}/{groupName}" var="goToUrl">
						<spring:param name="groupId" value="${groupId}" />
						<spring:param name="groupName" value="${groupName}" />
					</spring:url> --%>
					<a class="btn btn-metis-3 btn-sm" title="Share CI Report for ${groupName}">
						<i class="glyphicon glyphicon-envelope"></i>
					</a>
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

	<script type="text/javascript">

	function myAlert(msg, duration) {
		var el = document.createElement("div");
		el.setAttribute("style",
				"position: absolute; top: 85%; left: 85%; border: thick; background-color: black; padding: 4px; color: white; font-size: 20px");
		el.innerHTML = msg;
		setTimeout(function() {
			el.parentNode.removeChild(el);
		}, duration);
		document.body.appendChild(el);
	}

	function validateEmail(groupId, groupName) {
		var str = $('#shareEmail').val();
		if (/^(\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+)(,(\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+))*$/
				.test(str) == false) {
			alert('Please add comma seperated valid email id');
			return false;
		} else {
			if (confirm("Do you want to share this report?") == true) {
				var request = $.ajax({
					url : '${pageContext.request.contextPath}/shareReport/',
					type : "POST",
					data : {
						groupId : groupId,
						groupName : groupName,
						mails : str
					},
					dataType : "html"
				});
				$('#modalShare').modal('hide')
				request.done(function(msg) {
					myAlert("Mail sent sucessfully.", 3000)
				});
				request.fail(function(jqXHR, textStatus) {
					//alert("Request failed: " + textStatus);
					myAlert("Mail not sent, try again.", 3000)
				});
			} else {
				return false;
			}
		}
	}
</script>

<!-- modal -->
	<div class="modal fade" id="modalShare" tabindex="-1" style="top: 21%">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h5 class="modal-title" id="myModalLabel">Add comma seperated mail id's to share report</h5>
				</div>
				<div class="modal-body">
					<div class="container">
							<!-- onsubmit="return validateForm()" -->
								
										<div class="form-group">
											<label for="inputEmail3" class="col-sm-offset-1 col-sm-2 control-label">Email ID</label>

											<div class="col-sm-8">
												<input type="text" name="shareEmail" id="shareEmail"
													class="form-control input-sm" autofocus="autofocus" required/>
											</div>
										</div>
										<br><br><br>
										
										<div class="form-group">
											<div class="col-sm-10">
												<div class="col-sm-offset-5">
													<button type="button" class="btn btn-default"
														data-dismiss="modal">Close</button>
													<input type="button" name="Create" class="btn btn-primary"
														value="Share" onclick="return validateEmail('${groupId}','${groupName}')"/>
												</div>
											</div>
										</div>
										<br />
								
					</div>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
