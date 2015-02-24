<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<title><tiles:insertAttribute name="title" ignore="true" /></title>
	
	<!-- <meta name="viewport" content="width=device-width, initial-scale=1"> -->
	
	<link type="image/x-icon" rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/tabIconBW.png">
	
	<!-- CSS -->
	
	<link href="${pageContext.request.contextPath}/resources/css/cimvMain.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<%-- <link href="<c:url value="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet"> --%>
	<link href="${pageContext.request.contextPath}/resources/css/main.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/css/theme.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/css/ionicons.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/icons/font-awesome/css/font-awesome.min.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/css/leftNavigation.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/css/mainPageEffects.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/resources/css/breadcrumb.css" rel="stylesheet">
	
	<!-- Java Scripts -->
	
	<script src="${pageContext.request.contextPath}/resources/js/sorttable.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/pace.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.2.min.js"></script>
		
	<script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.js"></script>
	<script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap-dropdown.js"></script>
	<%-- <script src="<c:url value="resources/bootstrap/js/bootstrap-modal.js" />"></script> --%>
	<script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap-popover.js"></script>
	<script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap-collapse.js"></script>
	<script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap-carousel.js"></script>
	<script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap-tooltip.js"></script>
	<script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap-button.js"></script>
	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
	
</head>

<body class="padTop53">
	<div id="wrap">
		<!-- Header -->
		<tiles:insertAttribute name="header" />
		<!-- / Header -->
		<div id="left">
			<!-- Left Bar -->
			<tiles:insertAttribute name="leftNavigation" />
			<!-- / Left Bar -->
		</div>
		<div id="content">
			<!-- Content -->
			<tiles:insertAttribute name="body" />
			<!-- / Content -->
		</div>
	</div>
	<div>
		<tiles:insertAttribute name="footer" />
	</div>
	<script type="text/javascript">
	function validateFavName() {
		var str = $('#favName').val();
		if(/^\w+$/.test(str) == false) {
		    alert('Favorite name cannot have special characters other then underscore.');
		    return false;
		}
	}
	</script>
	<!-- modal -->
	<div class="modal fade" id="myModal" tabindex="-1" style="top: 21%">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h5 class="modal-title" id="myModalLabel">Save Favorite</h5>
				</div>
				<div class="modal-body">
					<div class="container">
					<!-- This is to restrict user so that he cannot add more then 4 favorites -->
						<c:choose>
							<c:when test="${favoriteListBeanSize < 4 }">
							<!-- onsubmit="return validateForm()" -->
								<form name="favForm"  method="post" onsubmit="return validateFavName()" action="<c:url value="/saveFavorite" />">
										<div class="form-group">
											<label for="inputEmail3" class="col-sm-offset-3 col-sm-3 control-label">View Name</label>

											<div class="col-xs-4">
												<input type="text" name="favName" id="favName"
													class="form-control input-sm" autofocus="autofocus" required/>
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-10">
												<div class="checkbox col-sm-offset-6">
													&nbsp;
													<!-- <label><input type="checkbox" name="favDefault" value="1"/>Set as Default</label> -->
												</div>
												<div class="col-sm-offset-5">
													<button type="button" class="btn btn-default"
														data-dismiss="modal">Close</button>
													<input type="submit" name="Create" class="btn btn-primary"
														value="Save" />
												</div>
											</div>
										</div>
										<br />
								</form>
							</c:when>
							<c:otherwise>
								You cannot save more then four favorites, delete existing to add more.
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	
	<!-- Modal for tooltip information -->
	
	<!-- Modal #modalUnitTestCoverage-->
	<div id="modalUnitTestCoverage" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" >&times;</button>
            <h4 class="modal-title">Unit Test Coverage</h4>
          </div>
          <div class="modal-body">
            <p>
				It is a mix of Line coverage and Branch coverage.<br> 
				Its goal is to provide an even more accurate answer to the following question:<br> 
				How much of the source code has been covered by the unit tests?<br><br>

				Coverage = (CT + CF + LC) / (2 * B + EL)<br><br>

				where<br><br>

				CT = branches that have been evaluated to 'true' at least once<br> 
				CF = branches that have been evaluated to 'false' at least once<br> 
				LC = covered lines = lines_to_cover - uncovered_lines<br> 
				B = total number of branches<br> 
				EL = total number of executable lines (lines_to_cover)<br><br>  

				It is recommended to have a Unit Test Coverage value greater than 70%.<br> 
            </p>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    <!-- /#modalUnitTestCoverage -->
    
    <!-- Modal #modalBlockerIssues-->
	<div id="modalBlockerIssues" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" >&times;</button>
            <h4 class="modal-title">Blocker Issues</h4>
          </div>
          <div class="modal-body">
					<p>
						Operational/security risk: This issue might make the whole
						application unstable in production. Ex: calling garbage collector,
						not closing a socket, etc. It is recommended to have 0 blocker
						issues.
					</p>
				</div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    <!-- /#modalBlockerIssues -->
	
	<!-- Modal #modalCriticalIssues-->
	<div id="modalCriticalIssues" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" >&times;</button>
            <h4 class="modal-title">Critical Issues</h4>
          </div>
          <div class="modal-body">
					<p>
						Operational/security risk: This issue might lead to an
						unexpected behavior in production without impacting the integrity
						of the whole application. Ex: NullPointerException, badly caught
						exceptions, lack of unit tests, etc. I is recommended to have 0
						critical issues.
					</p>
				</div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    <!-- /#modalCriticalIssues -->
    
    <!-- Modal #modalRulesComplianceIndex-->
	<div id="modalRulesComplianceIndex" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" >&times;</button>
            <h4 class="modal-title">Rules Compliance Index</h4>
          </div>
          <div class="modal-body">
					<p>
						Rules compliance index (RCI) = 100 - (Weighted issues / Lines of code * 100) <br><br>
						
						where <br><br>
						
						Weighted issues = ( (Blocker_violatons * 10) + (Critical_violations * 5) + (Major_violations * 3) + Minor_violations ).<br><br> 
						
						If the value is negative, it is rounded to 0%.<br>
						You should aim for a RCI of &gt;80%.
					</p>
				</div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    <!-- /#modalRulesComplianceIndex -->
    
    <!-- Modal #modalLinesOfCode-->
	<div id="modalLinesOfCode" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" >&times;</button>
            <h4 class="modal-title">Lines Of Code</h4>
          </div>
          <div class="modal-body">
					<p>
						Number of physical lines that contain at least one character
						which is neither a whitespace or a tabulation or part of a
						comment.
					</p>
				</div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    <!-- /#modalLinesOfCode -->
    
    <!-- Modal #modalDuplication-->
	<div id="modalDuplication" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" >&times;</button>
            <h4 class="modal-title">Duplication</h4>
          </div>
          <div class="modal-body">
					<p>
						Density of duplication = (Duplicated lines / Lines) * 100 
					</p>
				</div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    <!-- /#modalDuplication -->
    
    <!-- Modal #modalComplexityFunction-->
	<div id="modalComplexityFunction" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" >&times;</button>
            <h4 class="modal-title">Complexity / Function</h4>
          </div>
          <div class="modal-body">
					<p>
						Average complexity by function.<br><br>

						If avg. complexity/function &lt; 4, complexity no. = 0<br>
						If avg. complexity/function &lt; 8, complexity no. = 0.2<br>
						If avg. complexity/function &lt; 12, complexity no. = 0.4<br>
						If avg. complexity/function &lt; 16, complexity no. = 0.6<br>
						If avg. complexity/function &lt; 20, complexity no. = 0.8<br>
						If avg. complexity/function &gt; 20, complexity no. = 1.0<br><br>

						Thus, Complexity/function has a recommended value &lt; 10
					</p>
				</div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    <!-- /#modalComplexityFunction -->
    
     <!-- Modal #modalComments-->
	<div id="modalComments" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" >&times;</button>
            <h4 class="modal-title">Comments</h4>
          </div>
          <div class="modal-body">
					<p>
						Density of comment lines = Comment lines / (Lines of code + Comment lines) * 100<br><br>

						With such a formula:<br>
						50% means that the number of lines of code equals the number of comment lines<br>
						100% means that the file only contains comment lines

					</p>
				</div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    <!-- /#modalComments -->
    
    <!-- Modal #modalQualityIndex-->
	<div id="modalQualityIndex" class="modal fade">
      <div class="modal-dialog" style="width: 770px">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" >&times;</button>
            <h4 class="modal-title">Quality Index</h4>
          </div>
          <div class="modal-body">
					<p style="margin-bottom: 0px">
						PLSQL Projects : <br>
						QI = 10 - [(8 * Coding) + (1 * Complexity no.) + (0.5 * Duplicates)
								+ (0.5 * Comments)] <br>
						Java Projects : <br>
						QI = 10 - [(5.5 * Coding) + (2.5 * Coverage) + (1 * Complexity no.)
								+ (0.5 * Duplicates) + (0.5 * Comments)]<br><br>
 
						Where<br>
 
						Coding = (Blocker*10 + Critical*7 + Major*3) / (LOC - Duplicated lines)<br><br>
 
						Coverage = 1 - [(Unit Tests Coverage)% / 100]<br>
						(Eg. For this project https://csqi.cisco.com/dashboard/index/14340?did=10000,
						it is 1 - 0.66 = 0.34)<br><br>
 
						Complexity no. (tentative) = <br>
						If avg. complexity/function &lt; 4, complexity no. = 0<br>
						If avg. complexity/function &lt; 8, complexity no. = 0.2<br>
						If avg. complexity/function &lt; 12, complexity no. = 0.4<br>
						If avg. complexity/function &lt; 16, complexity no. = 0.6<br>
						If avg. complexity/function &lt; 20, complexity no. = 0.8<br>
						If avg. complexity/function &gt; 20, complexity no. = 1.0<br><br>
						
						Duplicates = Duplicated Lines /(LOC - Duplicated lines)<br><br>
						
						Comments = 1 - [5 * (Comment Lines) / (LOC - Duplicated lines)] 
						(if value comes out negative, convert it to 0)
					</p>
				</div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    <!-- /#modalQualityIndex -->
    
    <!-- Modal #modalHealthStatus-->
	<div id="modalHealthStatus" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" >&times;</button>
            <h4 class="modal-title">Health Status</h4>
          </div>
          <div class="modal-body">
					<p>
						The project status is based on the quality index.<br><br>
						If QI = 0 to 4.9, status = red; <br>
						If QI = 5 to 6.9, status = yellow; <br>
						If QI = 7 to 10, status = green. <br><br>

						Soon, we will incorporate other metrics into the health status.
					</p>
				</div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    <!-- /#modalHealthStatus -->
</body>
</html>