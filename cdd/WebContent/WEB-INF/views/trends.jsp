<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
    	  var jsonData = ${jsonDataChartTrend}
  		// Create our data table out of JSON data loaded from server.
  			var data = new google.visualization.DataTable(jsonData);

  			// Instantiate and draw our chart, passing in some options.
  			var chart = new google.visualization.LineChart(document
  					.getElementById('chart_div'));
  			chart.draw(data);
      }
    </script>
    <script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
    	  var jsonData = ${jsonDataChartTrend1}
  		// Create our data table out of JSON data loaded from server.
  			var data = new google.visualization.DataTable(jsonData);

  			// Instantiate and draw our chart, passing in some options.
  			var chart = new google.visualization.LineChart(document
  					.getElementById('chart_div1'));
  			chart.draw(data);
      }
    </script>
    <script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
    	  var jsonData = ${jsonDataChartTrend2}
  		// Create our data table out of JSON data loaded from server.
  			var data = new google.visualization.DataTable(jsonData);

  			// Instantiate and draw our chart, passing in some options.
  			var chart = new google.visualization.LineChart(document
  					.getElementById('chart_div2'));
  			chart.draw(data);
      }
    </script>
    <script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
    	  var jsonData = ${jsonDataChartTrend3}
  		// Create our data table out of JSON data loaded from server.
  			var data = new google.visualization.DataTable(jsonData);

  			// Instantiate and draw our chart, passing in some options.
  			var chart = new google.visualization.LineChart(document
  					.getElementById('chart_div3'));
  			chart.draw(data);
      }
    </script>

</head>
<body>
	<!-- / Right Content -->
	<header class="head">
		<!-- <div>

			<div id="contentHead" class="contentHead">
				<span id="contentHeadInner">Trends</span>
			</div>
		</div> -->

		<div>
			<div class="main-bar">
				<%-- <% def sdlcuserVO = session.getAttribute('SDLCUSERVO') %> --%>
				<ul id="tsc_breadcrumb-4">
					<li><a href="<c:url value="/home" />"> <span
							class="glyphicon glyphicon-home"></span>
					</a></li>
					<li><a href="${trendRedirect}"> 
						${trendName}
					</a></li>
				</ul>
			</div>
		</div>

	</header>

	<!-- Content -->

	<div id="outdiv" class="outer"
		style="background-color: white;">
		<div class="inner">
			<div class="row">
				<div class="col-lg-6">
					<div class="box">
						<header>
							<h5>
								Technical Debt Timeline *
							</h5>
						</header>
						<%--<div style="width: 500px; height: 250px;"><br><b>&nbsp;&nbsp;Trends Coming soon...</b></div>
						--%>
						<div id="chart_div" style="height: 260px;"></div>
						<p align="center" style="margin:0px; font-family: verdana, arial, sans-serif;">* Since first build</p>
					</div>
				</div>
				
				<div class="col-lg-6">
					<div class="box">
						<header>
							<h5>
								Rules Compliance Timeline *
							</h5>
						</header>
						<%--<div style="width: 500px; height: 250px;"><br><b>&nbsp;&nbsp;Trends Coming soon...</b></div>
						--%>
						<div id="chart_div1" style="height: 260px;"></div>
						<p align="center" style="margin:0px; font-family: verdana, arial, sans-serif;">* Since first build</p>
					</div>
				</div>
				
			</div>
			<!-- /.row -->
			<hr>
			
			<div class="row">
				<div class="col-lg-6">
					<div class="box">
						<header>
							<h5>
								Blocker Issues Timeline *
							</h5>
						</header>
						<%--<div style="width: 500px; height: 250px;"><br><b>&nbsp;&nbsp;Trends Coming soon...</b></div>
						--%>
						<div id="chart_div2" style="height: 260px;"></div>
						<p align="center" style="margin:0px; font-family: verdana, arial, sans-serif;">* Since first build</p>
					</div>
				</div>
				
				<div class="col-lg-6">
					<div class="box">
						<header>
							<h5>
								Lines of Code Timeline *
							</h5>
						</header>
						<%--<div style="width: 500px; height: 250px;"><br><b>&nbsp;&nbsp;Trends Coming soon...</b></div>
						--%>
						<div id="chart_div3" style="height: 260px;"></div>
						<p align="center" style="margin:0px; font-family: verdana, arial, sans-serif;">* Since first build</p>
					</div>
				</div>
				
			</div>
			<!-- /.row -->

		</div>
		<!-- /.inner -->
	</div>
	<!-- /.outer -->

</body>
</html>
