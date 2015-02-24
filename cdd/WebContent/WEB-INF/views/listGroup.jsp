<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<head>

<!-- js -->
<script src="resources/js/modernizr.js"></script>
<script>
    $(document).ready(function(){
        if (Modernizr.touch) {
            // show the close overlay button
            $(".close-overlay").removeClass("hidden");
            // handle the adding of hover class when clicked
            $(".img").click(function(e){
                if (!$(this).hasClass("hover")) {
                    $(this).addClass("hover");
                }
            });
            // handle the closing of the overlay
            $(".close-overlay").click(function(e){
                e.preventDefault();
                e.stopPropagation();
                if ($(this).closest(".img").hasClass("hover")) {
                    $(this).closest(".img").removeClass("hover");
                }
            });
        } else {
            // handle the mouseenter functionality
            $(".img").mouseenter(function(){
                $(this).addClass("hover");
            })
            // handle the mouseleave functionality
            .mouseleave(function(){
                $(this).removeClass("hover");
            });
        }
    });
</script>
<script type="text/javascript">
  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-34160351-1']);
  _gaq.push(['_trackPageview']);
  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();
</script>


</head>




<header class="head">
	<!-- <div>
			<div class="contentHead">Groups</div>
		</div> -->

	<div>
		<div class="main-bar">
			<ul id="tsc_breadcrumb-4">
				<li><a class="current"><span
						class="glyphicon glyphicon-home"></span></a></li>
			</ul>
		</div>
	</div>

</header>
<div class="outer" style="background-color: white;">
	<div class="inner innerBorderAll">
		<div class="row">
			<div class="col-lg-12">

				<div id="responsiveTable">
					<br>
					<div class="table-responsive overflow" style="overflow: hidden;">
						<table class="table data-table sortable sortableTable"
							style="width: 90%; border: 3px solid teal; -moz-border-radius: 5px; -webkit-border-radius: 5px; border-radius: 5px;">
							<thead>
								<tr>
									<th>GROUP NAME</th>
									<th>SERVICE OWNER</th>
									<th style="text-align: center">Number OF Tracks</th>
									<th style="text-align: center">Code Quality Status</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${groupListBean}" var="groupListBean">
									<tr>
										<td><spring:url value="/tracks/{groupId}/{groupName}"
												var="goToUrl">
												<spring:param name="groupId" value="${groupListBean._id}" />
												<spring:param name="groupName"
													value="${groupListBean.group_name}" />
											</spring:url> <a href="${goToUrl}"> ${groupListBean.group_name} </a></td>
										<td>${groupListBean.group_owner_name}</td>
										<td align="center">${groupListBean.no_of_resources}</td>
										<td align="center"><c:choose>
												<c:when test="${groupListBean.quality_index == 'NA'}">
														NA
													</c:when>
												<c:otherwise>
													<div style="margin-top: 0px;">
														<c:choose>
															<c:when test="${groupListBean.quality_index == 'red'}">
																<img height="21px" width="30px"
																	src="${pageContext.request.contextPath}/resources/images/red.png" title="0-4.9 : RED, 5-6.9 : YELLOW, 7-10 : GREEN">
															</c:when>
															<c:when test="${groupListBean.quality_index == 'yellow'}">
																<img height="21px" width="30px"
																	src="${pageContext.request.contextPath}/resources/images/yellow.png" title="0-4.9 : RED, 5-6.9 : YELLOW, 7-10 : GREEN">
															</c:when>
															<c:otherwise>
																<img height="21px" width="30px"
																	src="${pageContext.request.contextPath}/resources/images/green.png" title="0-4.9 : RED, 5-6.9 : YELLOW, 7-10 : GREEN">
															</c:otherwise>
														</c:choose>
													</div>
												</c:otherwise>
											</c:choose></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>


						<br>

						<div id="wrapper">

							<!-- /#top-bar -->
							<!-- /header -->


							<div id="main">
								<div class="container" style="padding-left: 65px">

									<!-- /nav -->
									<div id="effect-5" class="effects clearfix">
										<div class="img">
											<img class="mainPageImages"
												src="resources/images/frontPageImages/screws.png" alt="">
											<!--  <div style="background-color:#00ff00;"><br><br><br><br><br></div>   -->
											<div class="overlay">
												<a
													href="http://iwe.cisco.com/web/it-delivery-transformation"
													target="blank" class="expand">+</a> <a
													class="close-overlay hidden">x</a>
											</div>
										</div>

										<div class="img">
											<img class="mainPageImages"
												src="resources/images/frontPageImages/helmet.jpg" alt="">
											<div class="overlay">
												<a href="http://iwe.cisco.com/web/dft" target="blank"
													class="expand">+</a> <a class="close-overlay hidden">x</a>
											</div>
										</div>
										<div class="img">
											<img class="mainPageImages"
												src="resources/images/frontPageImages/adopt.png" alt="">
											<div class="overlay">
												<a href='<c:url value="/usageMetrics"></c:url>'
													target="blank" class="expand">+</a> <a
													class="close-overlay hidden">x</a>
											</div>
										</div>
										<div class="img">
											<img class="mainPageImages"
												src="resources/images/frontPageImages/delivery_man.jpg"
												alt="">
											<div class="overlay">
												<a href="http://iwe.cisco.com/web/dft" target="blank"
													class="expand">+</a> <a class="close-overlay hidden">x</a>
											</div>
										</div>
									</div>

									<div id="effect-5" class="effects clearfix"
										style="font-size: 12px">

										<div class="img">
											<b>ITDT Accelerator</b><br> IT Delivery Transformation,
											one of Cisco<br> IT's FY15 priorities, is driving the<br>
											evolution of application delivery by<br> providing
											adaptive tools and processes<br> that dramatically
											reduce the time<br> necessary to enable critical
											business<br> capabilities.
										</div>
										<div class="img" style="margin-left: 4px;">
											<b>DFT Dev Mgmt</b><br>The DFT team is primariliy
											associated<br> with the 'Standardization and Automation<br>
											of Development Processes' track of<br> ITDT.
										</div>
										<div class="img">
											<b>Adoption Metrics</b>
											<br>Total Projects :&nbsp;<%=session.getAttribute("totalProjects")%>
											<br>JAVA Projects :&nbsp;<%=session.getAttribute("totalJAVAProjects")%>
											<br> PL/SQL Projects :&nbsp;<%=session.getAttribute("totalPLSQLProjects")%>
											<br>Onboarded
											Teams: CITS-Customer<br> Care, CITS-CSIT, CVC-Q2O,<br>
											CVC-EDS
										</div>
										<div class="img">
											<b>Coming Up...</b><br>Functional Testing Reports<br>Mobile
											App for CDD!<br>Security and Vulnerability Assessments<br>Developer-based
											Metrics
										</div>
									</div>

								</div>
								<!-- /.container -->
							</div>
							<!-- #main -->

							<!-- /footer -->
						</div>

					</div>
				</div>
			</div>
		</div>
		<!-- /row -->
		<br>
	</div>
</div>

