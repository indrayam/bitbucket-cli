<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="main.java.com.cisco.dft.cimv.model.trackInfo"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
	<script type="text/javascript">
		$(document).click(
			function(e) {
				if (!$(e.target).hasClass("more")
						&& $(e.target).parents(".popover").length === 0) {
					$(".popover").hide();
				}
			});
	</script>
<script type="text/javascript">
	google.load("visualization", "1", {
		packages : [ "corechart" ]
	});
	google.setOnLoadCallback(drawChart);
	function drawChart() {

		var jsonData = ${jsonDataChartTrack};
		
		var options = {
				
				vAxes: {0: {viewWindowMode:'explicit',
                    gridlines: {color: 'transparent'}, 
                    textStyle: {color: 'red'},
                    minValue: 0, 
                    maxValue: 10
                    },
                1: {gridlines: {color: 'transparent'},
              	  textStyle: {color: 'green'},
              	 minValue: 0
                    },
                },
	          series: {0: {targetAxisIndex:0},
	                   1:{targetAxisIndex:1},
	                  },
			    colors: ["red", "green"]}
		
	// Create our data table out of JSON data loaded from server.
		var data = new google.visualization.DataTable(jsonData);

		// Instantiate and draw our chart, passing in some options.
		var chart = new google.visualization.LineChart(document
				.getElementById('chart_div'));
		
		chart.draw(data,options);
	}
	
	/* function DateDiff() {
		var d1 = new Date();
	    var day = d1.getDate();
	    var month = d1.getMonth() + 1;
	    var year = d1.getFullYear();
	    var date1 = day + "/" + month + "/" + year ;
	    var date2 = "3/10/2013";
	    
		var datetemp1 = date1.split("/");
		var datetemp2 = date2.split("/");
	    var datediff = datetemp1[0] - datetemp2[0];
	    alert(datediff);
	    document.getElementById('svnDate').style.color = 'red';
	    //store the getTime diff - or +
	    //return (datediff / (24 * 60 * 60 * 1000));
	    //Convert values to -/+ days and return value
	} */
	 
</script>

<script type="text/javascript">
				function showhide(elem) {
				    if(document.getElementById(elem).style.display=='none') {
				      document.getElementById(elem).style.display='block';
				    }
				    else{
				          document.getElementById(elem).style.display='none';
				    }
				    return false;
				}
				
				function show(elem) {
				      document.getElementById(elem).style.display='block';
				    return false;
				}
				
				function close(elem) {
				      document.getElementById(elem).style.display='none';
				    return false;
				}	
			</script>

</head>
<body>
	<header class="head">
		<%-- <div>
			<div id="contentHead" class="contentHead">
				<span id="contentHeadInner">${trackName}</span>
			</div>
		</div> --%>

		<div>
			<div class="main-bar">
				<%-- <% def sdlcuserVO = session.getAttribute('SDLCUSERVO') %> --%>
				<ul id="tsc_breadcrumb-4">
					<li><a href="<c:url value="/home" />"> <span
							class="glyphicon glyphicon-home"></span>
					</a></li>
					<li><spring:url value="/tracks/{groupId}/{groupName}"
							var="goToUrl">
							<spring:param name="groupId" value="${groupId}" />
							<spring:param name="groupName" value="${groupName}" />
						</spring:url> <a href="${goToUrl}"> ${groupName} </a></li>
					<li><a class="current">${trackName}</a></li>
				</ul>
			</div>
		</div>

	</header>
	
	<div class="outer" style="background-color: white">
		<div class="inner">
			<div class="row">
				<div class="col-lg-6">
					<div class="box">
						<header>
							<h5>
								Quality Index Timeline *
							</h5>
						</header>
						<%--<div style="width: 500px; height: 250px;"><br><b>&nbsp;&nbsp;Trends Coming soon...</b></div>
						--%>
						<div id="chart_div" style="height: 260px;"></div>
						<p align="center" style="margin:0px; font-family: verdana, arial, sans-serif;">* Since first build</p>
					</div>
				</div>

				<div class="col-lg-6">
					<div class="text-center">
						<div class="qiGradient">
							<font size="5">Quality Index &nbsp; : &nbsp;
							${metricsBean.quality_index}/10 </font>&nbsp;&nbsp;
							<c:choose>
								<c:when test="${metricsBean.quality_index >= 0 && metricsBean.quality_index < 5}">
									<img height="21px" width="30px" src="${pageContext.request.contextPath}/resources/images/red.png" title="0-4.9 : RED, 5-6.9 : YELLOW, 7-10 : GREEN">
								</c:when>
								<c:when test="${metricsBean.quality_index >= 5 && metricsBean.quality_index < 7}">
									<img height="21px" width="30px" src="${pageContext.request.contextPath}/resources/images/yellow.png" title="0-4.9 : RED, 5-6.9 : YELLOW, 7-10 : GREEN">
								</c:when>
								<c:otherwise>
									<img height="21px" width="30px" src="${pageContext.request.contextPath}/resources/images/green.png" title="0-4.9 : RED, 5-6.9 : YELLOW, 7-10 : GREEN">
								</c:otherwise>
							</c:choose>

							<a class="more" style="font-size: 14px; cursor: pointer; text-shadow: none;"
								onclick="showhide('infoQI');">more</a>
								<div class="myTooltip1" title="Help" style="right:8%">
								<a data-toggle="modal"
									data-placement="bottom" class="myTooltip2A"
									href="#modalQualityIndex"> <i class="glyphicon glyphicon-question-sign"></i>
								</a>
							</div>
						</div>
						<div id="infoQI" class="popover bottom"
							style="left: 264px; top: -12px; display: none;">
							<div class="arrow"></div>
							<h3 class="popover-title" style="font-weight: bold;">
								Rating Breakup (out of 1.0)
								<!-- <g:message code="sdlcdashboard.widgets.comments" />-->
							</h3>
							<div class="popover-content" style="font-size: 13px">
								<div style="width: 80%; margin: auto; font-weight: bold;">
									<table>
										<tr>
											<td align="left" style="width: 100px"><div>
													<b>Coding</b> &nbsp;&nbsp;
												</div></td>
											<td align="left"><div>
													:&nbsp;&nbsp;&nbsp;${metricsBean.coding_qi}</div></td>

										</tr>
										<tr>
											<td align="left"><div>
													<b>Coverage</b> &nbsp;&nbsp;
												</div></td>
											<td align="left"><div>
													:&nbsp;&nbsp;&nbsp;${metricsBean.coverage_qi}</div></td>

										</tr>
										<tr>
											<td align="left" style="width: 71px"><div>
													<b>Complexity</b> &nbsp;&nbsp;
												</div></td>
											<td align="left"><div>
													:&nbsp;&nbsp;&nbsp;${metricsBean.complexity_qi}</div></td>

										</tr>
										<tr>
											<td align="left"><div>
													<b>Duplication</b> &nbsp;&nbsp;
												</div></td>
											<td align="left"><div>
													:&nbsp;&nbsp;&nbsp;${metricsBean.duplicates_qi}</div></td>

										</tr>
										<tr>
											<td align="left"><div>
													<b>Comments</b> &nbsp;&nbsp;
												</div></td>
											<td align="left"><div>
													:&nbsp;&nbsp;&nbsp;${metricsBean.comments_qi}</div></td>

										</tr>
									</table>
								</div>
							</div>
						</div>
					</div>


					<!-- ./col -->
					<div class="col-lg-6 col-xs-6" style="margin-top: 14px">
						<!-- small box -->
						<div class="small-box bg-red">
							<div class="inner innerBorder" style="background-color: teal;">
								<h3>${metricsBean.blocker_issues}</h3>
								<p>
									<b>Blocker Issues</b>
								</p>
								<div class="icon">
									<i class="glyphicon glyphicon-ban-circle"></i>
								</div>
								<div class="myTooltip1" title="Help">
									<a data-toggle="modal" data-original-title="Help"
										data-placement="bottom" class="myTooltip1A"
										href="#modalBlockerIssues"> <i class="glyphicon glyphicon-question-sign"></i>
									</a>
								</div>
							</div>

						</div>
					</div>
					<!-- ./col -->

					<div class="col-lg-6 col-xs-6" style="margin-top: 14px">
						<!-- small box -->
						<div class="small-box bg-red">
							<div class="inner innerBorder" style="background-color: #a64d4d; height: 115px">
								<h3>${metricsBean.rules_compliance}</h3>
								<p>
									<b>Rules Compliance</b><br><b>Index</b>
								</p>

								<div class="icon">
									<i class="glyphicon glyphicon-exclamation-sign"></i>
								</div>
								<div class="myTooltip1" title="Help">
									<a data-toggle="modal" data-original-title="Help"
										data-placement="bottom" class="myTooltip1A"
										href="#modalRulesComplianceIndex"> <i class="glyphicon glyphicon-question-sign"></i>
									</a>
								</div>
							</div>

						</div>
					</div>

					<div class="col-lg-6 col-xs-6" style="margin-top: 20px;">
						<!-- small box -->
						<div class="small-box bg-yellow">
							<div class="inner innerBorder" style="background-color: #F39C12;">
								<h3>${metricsBean.critical_issues}</h3>
								<p>
									<b>Critical Issues</b>
								</p>
								<div class="icon">
									<i class="glyphicon glyphicon-warning-sign"></i>
								</div>
								<div class="myTooltip1" title="Help">
									<a data-toggle="modal" data-original-title="Help"
										data-placement="bottom" class="myTooltip1A"
										href="#modalCriticalIssues"> <i class="glyphicon glyphicon-question-sign"></i>
									</a>
								</div>
							</div>

						</div>
					</div>
					<!-- ./col -->
					<div class="col-lg-6 col-xs-6" style="margin-top: 20px">
						<!-- small box -->
						<div class="small-box bg-red">
							<div class="inner innerBorder"
								style="background-color: #A264E7; height: 115px">
								<h3>${metricsBean.unit_test_coverage}%</h3>
								<div>
									<p style="margin:0px">
									<b>Unit Test Coverage</b><br>  </p><a class="more"
										onclick="showhide('infoCodeCoverage');"
										style="color: white; cursor: pointer;">more</a>
									<div id="infoCodeCoverage" class="popover bottom"
										style="color: black; top: 61px; display: none;">
										<div class="arrow"></div>
										<div class="popover-title" style="font-weight: bold;">
											Unit Test Coverage
											<!-- <g:message code="sdlcdashboard.widgets.rulescompliance.codecoverage" />-->
										</div>
										<div class="popover-content">
											<div style="width: 80%; margin: auto; font-weight: bold;">
												<table>
													<tr>
														<td align="left" style="width: 78px"><div>
																<b>Unit Test</b> &nbsp;&nbsp;
															</div></td>
														<td align="left"><div>
																:&nbsp;&nbsp;&nbsp;${metricsBean.unit_test_coverage}</div></td>

													</tr>
													<tr>
														<td align="left"><div>
																<b>Line</b> &nbsp;&nbsp;
															</div></td>
														<td align="left"><div>
																:&nbsp;&nbsp;&nbsp;${metricsBean.line_coverage}</div></td>

													</tr>
													<tr>
														<td align="left"><div>
																<b>Branch</b> &nbsp;&nbsp;
															</div></td>
														<td align="left"><div>
																:&nbsp;&nbsp;&nbsp;${metricsBean.branch_coverage}</div></td>

													</tr>
												</table>
											</div>
										</div>
									</div>
								</div>
								<div class="icon">
									<i class="glyphicon glyphicon-zoom-in"></i>
								</div>
								<div class="myTooltip1" title="Help">
									<a data-toggle="modal" data-original-title="Help"
										data-placement="bottom" class="myTooltip1A"
										href="#modalUnitTestCoverage"> <i class="glyphicon glyphicon-question-sign"></i>
									</a>
								</div>
							</div>

						</div>
					</div>

				</div>

			</div>
			<!-- /.row -->

			<hr>

			<div id="thirdRow" class="text-center">
				<ul class="stats_box">
					<li>
						<div class="sparkline" style="height: 52px">
							<div style="font-size: 47px; color: rgba(0, 0, 0, 0.35);">
								<i class="glyphicon glyphicon-list"></i>
							</div>
						</div>
						<div class="stat_text">
							<b>Lines of Code<!-- <g:message code="sdlcdashboard.widgets.entitysize.numberoftracks" />--></b><strong></strong>
							<a class="more" onclick="showhide('infoloc');" style="cursor: pointer;">more</a>
							<div id="infoloc" class="popover bottom" style="display: none;">
								<div class="arrow"></div>
								<h3 class="popover-title" style="font-weight: bold;">
									Entity Size</h3>
								<div class="popover-content">
									<div style="width: 86%; margin: auto; font-weight: bold;">
										<table>
											<tr>
												<td align="left" style="width: 76px"><div>
														<b>Packages</b> &nbsp;&nbsp;
													</div></td>
												<td align="left"><div>
														:&nbsp;&nbsp;&nbsp;${metricsBean.packages}</div></td>

											</tr>
											<tr>
												<td align="left"><div>
														<b>Classes</b> &nbsp;&nbsp;
													</div></td>
												<td align="left"><div>
														:&nbsp;&nbsp;&nbsp;${metricsBean.classes}</div></td>

											</tr>
											<tr>
												<td align="left"><div>
														<b>Methods</b> &nbsp;&nbsp;
													</div></td>
												<td align="left"><div>
														:&nbsp;&nbsp;&nbsp;${metricsBean.functions}</div></td>

											</tr>
										</table>
									</div>
								</div>
							</div>
							<div class="percent down">
								<div>${metricsBean.lines_of_code}</div>
							</div>
							<div class="myTooltip2" title="Help">
								<a data-toggle="modal" data-original-title="Help"
										data-placement="bottom" class="myTooltip2A"
										href="#modalLinesOfCode"> <i class="glyphicon glyphicon-question-sign"></i>
									</a>
							</div>
						</div>

					</li>
					<li>
						<div class="sparkline">
							<div style="font-size: 47px; color: rgba(0, 0, 0, 0.35);">
								<i class="fa fa-copy"></i>
							</div>
						</div>
						<div class="stat_text">

							<b>Duplication<!-- <g:message code="sdlcdashboard.widgets.duplications" />--></b><strong></strong>
							<a class="more" style="cursor: pointer;"
								onclick="showhide('infoDuplication');">more</a>
							<div id="infoDuplication" class="popover bottom" style="display: none;">
								<div class="arrow"></div>
								<h3 class="popover-title" style="font-weight: bold;">
									Duplication
									<!-- <g:message code="sdlcdashboard.widgets.duplications" />-->
								</h3>
								<div class="popover-content">
									<div style="width: 80%; margin: auto; font-weight: bold;">
										<table>
											<tr>
												<td align="left" style="width: 60px"><div>
														<b>Lines<!-- <g:message
														code="sdlcdashboard.widgets.duplications.lines" />--></b>
														&nbsp;&nbsp;
													</div></td>
												<td align="left"><div>
														:&nbsp;&nbsp;&nbsp;${metricsBean.duplicated_lines}</div></td>

											</tr>
											<tr>
												<td align="left"><div>
														<b>Blocks<!-- <g:message
														code="sdlcdashboard.widgets.duplications.blocks" />--></b>
														&nbsp;&nbsp;
													</div></td>
												<td align="left"><div>:&nbsp;&nbsp;&nbsp;NA</div></td>

											</tr>
											<tr>
												<td align="left"><div>
														<b>Files<!-- <g:message
														code="sdlcdashboard.widgets.duplications.files" />--></b>
														&nbsp;&nbsp;
													</div></td>
												<td align="left"><div>:&nbsp;&nbsp;&nbsp;NA</div></td>

											</tr>
										</table>
									</div>
								</div>
							</div>
							<div class="percent down">
								<div>${metricsBean.duplication}</div>
							</div>
							<div class="myTooltip2" title="Help">
								<a data-toggle="modal" data-original-title="Help"
										data-placement="bottom" class="myTooltip2A"
										href="#modalDuplication"> <i class="glyphicon glyphicon-question-sign"></i>
									</a>
							</div>
						</div>
					</li>
					<li>
						<div class="sparkline">
							<div style="font-size: 47px; color: rgba(0, 0, 0, 0.35);">
								<i class="fa fa-wrench"></i>
							</div>
						</div>
						<div class="stat_text">

							<b>Complexity / function<!-- <g:message code="sdlcdashboard.widgets.complexity" />--></b><strong></strong>
							<a class="more" style="cursor: pointer;" onclick="showhide('infoComplexity');">more</a>
							<div id="infoComplexity" class="popover bottom" style="display: none;">
								<div class="arrow"></div>
								<h3 class="popover-title" style="font-weight: bold;">
									Complexity
									<!-- <g:message code="sdlcdashboard.widgets.complexity" />-->
								</h3>
								<div class="popover-content">
									<div style="width: 89%; margin: auto; font-weight: bold;">
										<table>
											<tr>
												<td align="left"><div>
														<b>Complexity</b> &nbsp;&nbsp;
													</div></td>
												<td align="left"><div>
														:&nbsp;&nbsp;&nbsp;${metricsBean.complexity}</div></td>

											</tr>
											<tr>
												<td align="left" style="width: 94px"><div>
														<b>per File</b> &nbsp;&nbsp;
													</div></td>
												<td align="left"><div>
														:&nbsp;&nbsp;&nbsp;${metricsBean.complexity_per_file}</div></td>

											</tr>
											<tr>
												<td align="left"><div>
														<b>per Class</b> &nbsp;&nbsp;
													</div></td>
												<td align="left"><div>
														:&nbsp;&nbsp;&nbsp;${metricsBean.complexity_per_class}</div></td>

											</tr>
										</table>
									</div>
								</div>
							</div>
							<div class="percent down">
								<div>${metricsBean.complexity_per_function}</div>
							</div>
							<div class="myTooltip2" title="Help">
								<a data-toggle="modal" data-original-title="Help"
										data-placement="bottom" class="myTooltip2A"
										href="#modalComplexityFunction"> <i class="glyphicon glyphicon-question-sign"></i>
									</a>
							</div>
						</div>
					</li>
					<li>
						<div class="sparkline">
							<div style="font-size: 47px; color: rgba(0, 0, 0, 0.35);">
								<i class="fa fa-quote-right"></i>
							</div>
						</div>
						<div class="stat_text">

							<b>Comments<!-- <g:message code="sdlcdashboard.widgets.comments" />--></b><strong></strong>
							<a class="more" style="cursor: pointer;" onclick="showhide('infoComments');">more</a>
							<div id="infoComments" class="popover bottom" style="display: none;">
								<div class="arrow"></div>
								<h3 class="popover-title" style="font-weight: bold;">
									Comments
									<!-- <g:message code="sdlcdashboard.widgets.comments" />-->
								</h3>
								<div class="popover-content">
									<div style="width: 80%; margin: auto; font-weight: bold;">
										<table>
											<tr>
												<td align="left" style="width: 71px"><div>
														<b>Out LOC</b> &nbsp;&nbsp;
													</div></td>
												<td align="left"><div>
														:&nbsp;&nbsp;&nbsp;${metricsBean.commented_out_loc}</div></td>

											</tr>
											<tr>
												<td align="left"><div>
														<b>Lines</b> &nbsp;&nbsp;
													</div></td>
												<td align="left"><div>
														:&nbsp;&nbsp;&nbsp;${metricsBean.comment_lines}</div></td>

											</tr>
										</table>
									</div>
								</div>
							</div>
							<div class="percent down">
								<div>${metricsBean.comments}</div>
							</div>
							<div class="myTooltip2" title="Help">
								<a data-toggle="modal" data-original-title="Help"
										data-placement="bottom" class="myTooltip2A"
										href="#modalComments"> <i class="glyphicon glyphicon-question-sign"></i>
									</a>
							</div>
						</div>
					</li>
				</ul>
			</div>

			
			<c:if test="${subtrackTableShow == 'true'}">
			<hr>
			<!--Begin Datatables-->
			<div class="row">
				<div class="col-lg-12">
					<div class="box">
						<header>
							<h5>Sub-Tracks</h5>
							<div class="toolbar">
								<div class="btn-group">
									<a href="#collapse4" data-toggle="collapse"
										class="btn btn-sm btn-default minimize-box"> <i
										class="fa fa-angle-up"></i>
									</a>
								</div>
							</div>
						</header>
						<div id="collapse4" class="body collapse in table-responsive">
							<table id="dataTable"
								class="table data-table sortable sortableTable">
								<thead>
									<tr>
										<th>SubTrack Name<!-- <g:message
												code="sdlcdashboard.usertracklistview.table.header.trackname" />--></th>
										<th>SubTrack POC<!-- <g:message
												code="sdlcdashboard.usertracklistview.table.header.trackpoc" />--></th>
										<th style="text-align: center">Number of Projects<!-- <g:message
												code="sdlcdashboard.usertracklistview.table.header.numberofprojects" />--></th>
										<th style="text-align: center">Code Quality Status<!-- <g:message
												code="sdlcdashboard.usertracklistview.table.header.qualityindex" />--></th>

									</tr>
								</thead>
								<tbody>
									<c:forEach items="${subtrackListBean}" var="subtrackListBean">
										<tr>
											<td><spring:url
													value="/projects/{groupId}/{groupName}/{trackId}/{trackName}/{subtrackId}/{subtrackName}"
													var="goToUrl">
													<spring:param name="groupId" value="${groupId}" />
													<spring:param name="groupName" value="${groupName}" />
													<spring:param name="trackId" value="${trackId}" />
													<spring:param name="trackName" value="${trackName}" />
													<spring:param name="subtrackId" value="${subtrackListBean._id}" />
													<spring:param name="subtrackName" value="${subtrackListBean.subtrack_name}" />
												</spring:url> <a href="${goToUrl}"> <%-- <a href="<c:url value="/projects?groupId=${groupId}&groupName=${groupName}&trackId=${trackListBean._id}&trackName=${trackListBean.track_name}" />"> --%>
													${subtrackListBean.subtrack_name}
											</a></td>
											<td>${subtrackListBean.subtrack_owner_name}</td>
											<td align="center">${subtrackListBean.no_of_resources}</td>
											<td align="center"><c:choose>
													<c:when test="${subtrackListBean.quality_index == 'NA'}">
														NA
													</c:when>
													<c:otherwise>
														<%-- <div class="circleBase type1"
															style="margin-top: 0px; background-color: ${subtrackListBean.quality_index}">
														</div> --%>
														<div style="margin-top: 0px;">
														<c:choose>
															<c:when
																test="${subtrackListBean.quality_index == 'red'}">
																<img height="21px" width="30px"
															src="${pageContext.request.contextPath}/resources/images/red.png" title="0-4.9 : RED, 5-6.9 : YELLOW, 7-10 : GREEN">
															</c:when>
															<c:when
																test="${subtrackListBean.quality_index == 'yellow'}">
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
						</div>
						<!-- /.row -->

						<!--End Datatables-->
					</div>
				</div>
			</div>
			</c:if>
			<c:if test="${projectTableShow == 'true'}">
			<hr>

			<!--Begin Datatables-->
			<div class="row">
				<div class="col-lg-12">
					<div class="box">
						<header>
							<h5>Projects</h5>
							<div class="toolbar">
								<div class="btn-group">
									<a href="#collapse4" data-toggle="collapse"
										class="btn btn-sm btn-default minimize-box"> <i
										class="fa fa-angle-up"></i>
									</a>
								</div>
							</div>
						</header>
						<div id="collapse4" class="body collapse in table-responsive">
							<table id="dataTable"
								class="table data-table sortable sortableTable ">
								<thead>
									<tr>
										<th>Project Name <!--		<g:message
														code="sdlcdashboard.userprojectlistview.table.header.projectname" />  -->
										</th>
										<th>POC</th>
										<th style="text-align: center">Code Quality Status</th>
										<th>Last Check-in</th>
										<th>Last build</th>
										<th>Last static analysis</th>
										<!-- <th>Code Coverage</th>
										<th>DAF Score</th> -->
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${projectListBean}" var="projectListBean">
										<tr>

											<td><spring:url
													value="/projectInfoOrphan/{groupId}/{groupName}/{trackId}/{trackName}/{projectId}/{projectName}"
													var="goToUrl">
													<spring:param name="groupId" value="${groupId}" />
													<spring:param name="groupName" value="${groupName}" />
													<spring:param name="trackId" value="${trackId}" />
													<spring:param name="trackName" value="${trackName}" />
													<spring:param name="projectId"
														value="${projectListBean._id}" />
													<spring:param name="projectName"
														value="${projectListBean.project_name}" />
												</spring:url> <a href="${goToUrl}"> <%-- <a href="<c:url value="/projectInfo?groupId=${groupId}&groupName=${groupName}&trackId=${trackId}&trackName=${trackName}&projectId=${projectListBean._id}&projectName=${projectListBean.project_name}" />"> --%>
													${projectListBean.project_name}
											</a></td>
											<td>${projectListBean.provisioning_pm}</td>
											<td align="center"><c:choose>
													<c:when test="${projectListBean.quality_index == 'NA'}">
															NA
														</c:when>
													<c:otherwise>
														<%-- <div class="circleBase type1"
															style="margin-top: 0px; background-color: ${projectListBean.quality_index}">
														</div> --%>
														<div style="margin-top: 0px;">
														<c:choose>
															<c:when
																test="${projectListBean.quality_index == 'red'}">
																<img height="21px" width="30px"
															src="${pageContext.request.contextPath}/resources/images/red.png" title="0-4.9 : RED, 5-6.9 : YELLOW, 7-10 : GREEN">
															</c:when>
															<c:when
																test="${projectListBean.quality_index == 'yellow'}">
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
											<td>${projectListBean.last_svn_checkin_timestamp}</td>
											<td>${projectListBean.last_build_timestamp}</td>
											<td>${projectListBean.last_sonar_code_analysis_timestamp}</td>
											<%-- <td>${projectListBean.unit_test_coverage}</td>
											<td>Coming soon..</td> --%>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<!-- /.row -->

						<!--End Datatables-->
					</div>
				</div>
			</div>
			</c:if>
			
		</div><!-- /.inner -->
	</div><!-- /.outer -->
</body>
</html>
