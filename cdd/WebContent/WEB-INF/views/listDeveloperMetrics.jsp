<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<head>
	<script type="text/javascript">
		function changePage(page){
			alert(page)
		}
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
							Developers
						</a>
					</li>
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
									<th>Developer</th>
									<th>Lines of Code</th>
									<th style="text-align: center">Code Quality Index</th>
									<th>Blocker</th>
									<th>Critical</th>
									<th>Major</th>
									<th>Duplication</th>
									<th>Comments</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${developerMetricsBean}" var="developerMetricsBean">
									<tr>
										<td>${developerMetricsBean.dev}</td>
										<td>${developerMetricsBean.loc}</td>
										<td align="center">${developerMetricsBean.qi}</td>
										<td>${developerMetricsBean.blocker}</td>
										<td>${developerMetricsBean.critical}</td>
										<td>${developerMetricsBean.major}</td>
										<td>${developerMetricsBean.duplication}</td>
										<td>${developerMetricsBean.comments}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<div class="col-md-10 col-md-offset-1">
					<ul class="pagination">
						<li><a href="#">&laquo;</a></li>
						<li><a id="1" href="javascript:changePage('1');">1</a></li>
						<li><a id="2" href="javascript:changePage('2');">2</a></li>
						<li><a id="3" href="javascript:changePage('3');">3</a></li>
						<li><a id="4" href="javascript:changePage('4');">4</a></li>
						<li><a id="5" href="javascript:changePage('5');">5</a></li>
						<li><a id="6" href="javascript:changePage('6');">6</a></li>
						<li><a id="7" href="javascript:changePage('7');">7</a></li>
						<li><a id="8" href="javascript:changePage('8');">8</a></li>
						<li><a id="9" href="javascript:changePage('9');">9</a></li>
						<li><a id="10" href="javascript:changePage('10');">10</a></li>
						<li><a id="11" href="javascript:changePage('11');">11</a></li>
						<li><a id="12" href="javascript:changePage('12');">12</a></li>
						<li><a id="13" href="javascript:changePage('13');">13</a></li>
						<li><a id="14" href="javascript:changePage('14');">14</a></li>
						<li><a id="15" href="javascript:changePage('15');">15</a></li>
						<li><a id="16" href="javascript:changePage('16');">16</a></li>
						<li><a id="17" href="javascript:changePage('17');">17</a></li>
						<li><a id="18" href="javascript:changePage('18');">18</a></li>
						<li><a id="19" href="javascript:changePage('19');">19</a></li>
						<li><a href="#">&raquo;</a></li>
					</ul>
				</div>
			</div>
		</div>
		<!-- /row -->
		<br>
	</div>
</div>