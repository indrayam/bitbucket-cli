<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Standards</title>
</head>
<body>
	<header class="head">
		<!-- <div>
			<div class="contentHead">Standards</div>
		</div> -->

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
							Standards
						</a>
					</li>
				</ul>
			</div>
		</div>

	</header>
	<div class="outer" style="background-color: white; height: 548px">
		<div class="inner">
			<div class="row">
				<div class="col-lg-12">
					<div class="box">
						<header>
						<!-- <h5>Standards</h5> -->
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
										<th></th>
										<th>Applicability</th>
										<th>Acceptance</th>
										<th>Recommended</th>

									</tr>
								</thead>
								<tbody>
									<tr>

										<td>Quality Index</td>
										<td>java pl/sql</td>
										<td>&gt; 6</td>
										<td>&gt; 7</td>
									</tr>
									<tr>

										<td>Rules Compliance</td>
										<td>java pl/sql</td>
										<td>&gt; 70</td>
										<td>Blocker Critical Major = 0</td>
									</tr>
									<tr>

										<td>Code Coverage</td>
										<td>java</td>
										<td>&gt; 70 %</td>
										<td>&gt; 80 %</td>

									</tr>
									<tr>

										<td>Unit Test Success</td>
										<td>java</td>
										<td>100 %</td>
										<td>100 %</td>
									</tr>
									<tr>

										<td>Comments</td>
										<td>java pl/sql</td>
										<td>&gt; 60 %</td>
										<td>&gt; 80 %</td>
									</tr>
									<tr>

										<td>Duplications</td>
										<td>java pl/sql</td>
										<td>&lt; 10 %</td>
										<td>0 %</td>
									</tr>
									<tr>

										<td>Complexity Class</td>
										<td>java pl/sql</td>
										<td>&lt; 50 %</td>
										<td>&lt; 10 %</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>