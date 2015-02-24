<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

 
  <header class="head">
 
 <script type="text/javascript">
 function check(selectedRelease){
	 var form = document.getElementById('releaseForm');
	    form.setAttribute('selectedRelease', '${pageContext.request.contextPath}/home?selectedRelease=' + selectedRelease);
	  	   form.submit();
 }
 
 </script>
 
 
	<div class="title-bar">
		<div class="btn-group" style="width: 200px;">
			<!-- Single button -->


			<form id="releaseForm" action='<c:url value="/home"></c:url>' method="GET">
				<select style="width: 180px; height: 30px; border-radius: 5px; border: 2px solid #ccc;"
					id="comboA" name="selectedRelease" onchange="check(this.value)">
					<option value="" class="releaseSelect">&nbsp;&nbsp;&nbsp;Release
						<%
						String newSelectedRelease = (String) session
								.getAttribute("selected");
						pageContext.setAttribute("selectedview", newSelectedRelease);
					%>
						<%=newSelectedRelease%></option>
					<c:forEach items="${releaseListBean}" var="releaseListBean">
						<c:set var="view" value="${releaseListBean._id}" />
						<c:if test="${view !=selectedview}">
							<option class="releaseSelect" value="${releaseListBean._id}">&nbsp;&nbsp;&nbsp;Release
								${releaseListBean._id}</option>
						</c:if>

					</c:forEach>
				</select>
			</form>
			
		</div>
	</div>
	<!-- /.search-bar -->
</header>

<!-- Left Navigation -->
<ul id="menu" class="affix-top">
	
	<li onmouseleave="{document.getElementById('favList').style.display = 'none'}">
		<div class="hi-icon-effect" style="padding-left: 25px">
			<div class="hi-icon glyphicon-thumbs-up" data-toggle="modal" data-target="#myModal"
							onmouseover="{document.getElementById('favList').style.display = 'block'}" 
													   title="Set as Favorite">
				<span style="font-size: 10px; color : black; font-weight: bold">Favorites</span>
			</div>
			<spring:url value="/getDeveloperMetrics/{page}" var="goToUrlD">
				<spring:param name="page" value="0" />
			</spring:url>
			<%-- <a href="${goToUrlD}"> --%>
			<div class="hi-icon glyphicon-dashboard" title="Developer Metrics">
				<span style="font-size: 10px; color : black; font-weight: bold">Developer Metrics</span>
			</div>
			<!-- </a> -->
			<spring:url value="/trends/{trendId}/{trendName}" var="goToUrl">
			<spring:param name="trendId" value="${trendId}" />
			<spring:param name="trendName" value="${trendName}" />
		</spring:url>
		
		<!-- This is to avoid trends on standards page and group list page as there its not required -->
		<c:choose>
			<c:when test="${trendId != null}"> 
				<a href="${goToUrl}"> 
					<div class="hi-icon glyphicon-sort-by-attributes-alt" title="Trends">
						<span style="font-size: 10px; color : black; font-weight: bold">Trends</span>
					</div>
				</a>
			</c:when>
			<c:otherwise>
					<div class="hi-icon glyphicon-sort-by-attributes-alt" title="Trends">
						<span style="font-size: 10px; color : black; font-weight: bold">Trends</span>
					</div>
			</c:otherwise>
		</c:choose>
			
			<a href='<c:url value="/standards"></c:url>'>
			<div class="hi-icon glyphicon-barcode" title="Standards">
				<span style="font-size: 10px; color : black; font-weight: bold">Standards</span>
			</div>
			</a> 
			<!-- '<c:url value="/usageMetrics"></c:url>' -->
			<a href='<c:url value="/usageMetrics"></c:url>'>
				<div class="hi-icon glyphicon-user" title="Adoption Metrics">
					<span style="font-size: 10px; color : black; font-weight: bold">Adoption Metrics</span>
				</div>
			</a>

		</div> 
		
		<!-- deleting the favorite using ajax --> <script>
		
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
		
			function deleteConfirm(elemId) {

				if (confirm("Do you want to delete this favorite?") == true) {
					var request = $.ajax({
								url : '${pageContext.request.contextPath}/deleteFavorite',
								type : "POST",
								data : {
									favName : elemId
								},
								dataType : "html"
							});
					request.done(function(msg) {
						$('ul.ulFavorite li.liFavorite' + elemId).remove();
						if($('ul.ulFavorite li').size() === 0){
							$('ul.ulFavorite').append("<li><a><i class=" + 'glyphicon glyphicon-star-empty' + "></i>&nbsp;&nbsp;<span class=" + "link-title" + ">No Favorites</span></a></li>");
						}
						myAlert("Favorite deleted.", 3000);
					});
					request.fail(function(jqXHR, textStatus) {
						//alert("Request failed: " + textStatus);
						myAlert("Favorite not deleted, try again", 3000);
					});
				} else {
					return false;
				}
			}
		</script>
		<div id="favList" class="popover right"
			onmouseover="{document.getElementById('favList').style.display = 'block'}"
			onmouseleave="{document.getElementById('favList').style.display = 'none'}"
			style="left: 140px; top: 0px">
			<div class="arrow"></div>
			<div class="popover-content" style="text-align: left">
			<ul class="ulFavorite">
				<c:choose>
					<c:when test="${favoriteListBean != null}">
						<c:forEach items="${favoriteListBean}" var="favoriteListBean">
							<li class="liFavorite${favoriteListBean.fav_name}">
							<div style="max-width: 200px">
								<a href="${favoriteListBean.fav_url}">
								<span class="link-title"> ${favoriteListBean.fav_name} </span> </a>
							<%-- <form  method="post"
								action="<c:url value="/deleteFavorite" />">
								<input type="hidden" name="favName"
									value="${favoriteListBean.fav_name}" />
							</form> --%>
							</div>
							<div class="favDelete" style="float: right">
								<a id="del" class="favDeleteA" title="Delete Favorite"
									onclick="deleteConfirm('${favoriteListBean.fav_name}')">
									<i class="glyphicon glyphicon-remove"></i>
								</a>
							</div>
							
							</li>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<li>
							<a><i class="glyphicon glyphicon-star-empty"></i>&nbsp;&nbsp;
								<span class="link-title"> No Favorites </span> </a>
						</li>
					</c:otherwise>
				</c:choose>
				</ul>
			</div>
		</div>
	</li>
</ul>
<!-- / Left Navigation -->
