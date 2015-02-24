<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<head>
	<script type="text/javascript">
	$( document ).ready(function() {
		var table = document.getElementById('myTable');
		var totalLOC = 0;
		var totalProjects = 0;
		var averageQI = 0;
		var totalBuilds = 0;
        for (var r = 1, n = table.rows.length; r < n; r++) {
        	totalLOC = parseInt(totalLOC,10) + parseInt(table.rows[r].cells[5].innerHTML, 10);
        	totalProjects = parseInt(totalProjects,10) + parseInt(table.rows[r].cells[2].innerHTML, 10);
        	averageQI = parseFloat(averageQI,10) + parseFloat(table.rows[r].cells[6].innerHTML, 10);
        	totalBuilds = parseInt(totalBuilds,10) + parseInt(table.rows[r].cells[4].innerHTML, 10);
        }
        averageQI = parseFloat(averageQI).toFixed(2)/(n - 1);
        document.getElementById('totalLOC').innerHTML = totalLOC;
        document.getElementById('totalProjects').innerHTML = totalProjects;
        document.getElementById('averageQI').innerHTML = (averageQI).toFixed(2);
        document.getElementById('totalBuilds').innerHTML = totalBuilds;
		});
	</script>
</head>

<header class="head">
		<div>
			<div class="main-bar">
				<ul id="tsc_breadcrumb-4">
					<li>
						<a href="<c:url value="/home" />"> 
							<span class="glyphicon glyphicon-home"></span>
						</a>
					</li>
					<li>
						<a class="current"> 
							CI Usage Metrics
						</a>
					</li>
				</ul>
			</div>
		</div>
	</header>
	
	<div class="outer" style="background-color: white;">
		<div class="inner innerBorderAll">
			<div class="row">
				<div class="col-lg-3">
					<div class="box" style="padding-left: 15px">
						<div>
						<font face="calibri" style="text-align: center;">
							<br><b><span id="averageQI" style="font-size: 22px"></span>/10</b><br>Average Quality Index<br></font><br>
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="box" style="padding-left: 15px">
						<div>
						<font face="calibri">
							<br>
							<b><span id="totalProjects" style="font-size: 22px"></span></b><br>
							Total projects in
								<%
								String newSelectedRelease = (String) session
									.getAttribute("selected");
								pageContext.setAttribute("selectedview", newSelectedRelease);
								%>
								<%=newSelectedRelease%>
							<br></font><br>
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="box" style="padding-left: 15px">
						<div>
						<font face="calibri">
							<br><b><span id="totalLOC" style="font-size: 22px"></span></b><br>Total Lines of code<br>
						</font><br>
						</div>
					</div>
				</div>
				<div class="col-lg-3">
					<div class="box" style="padding-left: 15px">
						<font face="calibri">
							<br><b><span id="totalBuilds" style="font-size: 22px"></span></b><br>
							<!-- Average Quality Index: <b><span id="averageQI"></span>/10</b> <br>(36% improvement in Rules Compliance for CITS-CC in Q1FY15)<br> -->
							Total Builds on Jenkins in <%=newSelectedRelease%><br><br>
						</font>
					</div>
				</div>
			</div>
			<hr style="margin-bottom: 0px">
			<div class="row">
				<div class="col-lg-12">					
					<div id="responsiveTable">
                    <br>
                    <div class="table-responsive overflow" style="overflow: hidden;">
                        <table id="myTable" class="table data-table sortable sortableTable" style="width:100%; border:3px solid teal;-moz-border-radius: 5px; -webkit-border-radius: 5px; border-radius: 5px;">
                            <thead>
									<tr>
										<th>Group Name</th>
										<th>Group Owner</th>
										<th align="center">Total # of Projects</th>
										<th>Project Type</th>
										<th align="center">Total # of Builds</th>
										<th align="center">Total Lines of Code</th>
										<th style="display : none">Quality Index</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${adoptionMetricsBean}" var="adoptionMetricsBean">
										<tr>
											<td>${adoptionMetricsBean.group_name}</td>
											<td>${adoptionMetricsBean.group_owner}</td>
											<td align="center">${adoptionMetricsBean.no_of_projects}</td>
											<td>java : ${adoptionMetricsBean.projectType['java']} , plsql : ${adoptionMetricsBean.projectType['plsql']}</td>
											<td align="center">${adoptionMetricsBean.total_no_of_builds}</td>
											<td id="tdLinesOfCode" align="center">${adoptionMetricsBean.total_lines_of_code}</td>
											<td style="display : none">${adoptionMetricsBean.group_QI}</td>
										</tr>
									</c:forEach>
								</tbody>
                        </table>
                    </div>
                </div>
				</div>
			</div>
			<!-- /row -->
		</div>
	</div>