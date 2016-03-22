<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="Monitor">
<meta name="author" content="Dhairya Sanghvi">

<title>CCIX to LAE Migration - View Reports</title>

<!-- Set the icon for the browser tab -->
<link type="image/x-icon" rel="shortcut icon"
	href="${pageContext.request.contextPath}/resources/images/settings_icon.png">

<!-- Java Scripts -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.2/jquery.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/view_reports.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/datatables/1.10.8/js/jquery.dataTables.min.js"></script>
<script
	src="https://cdn.datatables.net/plug-ins/1.10.9/features/searchHighlight/dataTables.searchHighlight.min.js"></script>
<script src="https://bartaz.github.io/sandbox.js/jquery.highlight.js"></script>
<script
	src="${pageContext.request.contextPath}/resources/js/dataTables.bootstrap.js"></script>
<script
	src="https://cdn.datatables.net/1.10.10/js/dataTables.bootstrap.min.js"></script>
<script
	src="https://cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>

<script
	src="https://cdn.datatables.net/buttons/1.1.0/js/dataTables.buttons.min.js"></script>
<script
	src="https://cdn.datatables.net/buttons/1.1.0/js/buttons.flash.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jszip/2.5.0/jszip.min.js"></script>
<script
	src="https://cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/vfs_fonts.js"></script>
<script
	src="https://cdn.datatables.net/buttons/1.1.0/js/buttons.html5.min.js"></script>

<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

<!-- CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<link
	href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<link
	href="https://cdn.datatables.net/1.10.10/css/jquery.dataTables.min.css"
	rel="stylesheet">

<link
	href="https://cdn.datatables.net/buttons/1.1.0/css/buttons.dataTables.min.css"
	rel="stylesheet">


<link
	href='https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300'
	rel='stylesheet' type='text/css' />
<link href='https://fonts.googleapis.com/css?family=Raleway'
	rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Roboto+Condensed'
	rel='stylesheet' type='text/css'>
<link
	href='https://fonts.googleapis.com/css?family=Work+Sans:400,300,700'
	rel='stylesheet' type='text/css'>

<script>
	$(document).ready(function() {
		$('#tableBranchData').DataTable({
	        "order": [[ 0, "asc" ]],
	        "paging": false,
			dom : 'Bfrtip',
			buttons : [ {
				extend : 'excel',
				text : 'Export to Excel'
			} ]
		});
	});
</script>

<script type="text/javascript">
	$(document).ready(function() {
		$('.navigation').removeClass("active")
		$('#navView').addClass("active")
		
		var chartData = ${svp_chart};
		createPlannedMigChart(chartData);
		
		var pieData = ${pieData};
		breakupChart(pieData);
		
		var comm_temp = '${status.committed}';
		var comm = (comm_temp == '' ? 0 : +comm_temp);
		
		var uncomm_temp = '${status.uncommitted}';
		var uncomm = (uncomm_temp == '' ? 0 : +uncomm_temp);
		
		var comp_temp = '${status.completed}';
		var comp = (comp_temp == '' ? 0 : +comp_temp);
		
		/* var prog_temp = '${status.in_progress}';		
	    var prog = (prog_temp == '' ? 0 : +prog_temp); */
		
		var total_apps = +comm + +uncomm + +comp;
		var committed_apps = +comm + +comp;
		
		var committed_percent = (committed_apps/total_apps)*100;
		
		if (committed_percent < 70) {
			document.getElementById("status1").style.color = "red";
		//	document.getElementById("status2").style.color = "red";
		} else if (committed_percent > 90) {
			document.getElementById("status1").style.color = "green";
		//	document.getElementById("status2").style.color = "green";
		} else {
			document.getElementById("status1").style.color = "orange";
		//	document.getElementById("status2").style.color = "orange";
		}
		
	});
</script>

</head>
<div>
	<nav class="navbar navbar-inverse navbar-static-top"
		style="background: -moz-linear-gradient(#646464, #323232); background: -webkit-linear-gradient(#646464, #323232); background: linear-gradient(#646464, #323232); background: -o-linear-gradient(#646464, #323232); background-color: #323232;">
		<div class="container-fluid">
			<div class="col-lg-7 col-md-7 col-sm-12 col-xs-12 navbar-header">
				<a class="navbar-brand pull-left" style="padding: 12px;"
					href="${pageContext.request.contextPath}/"> <img
					src="${pageContext.request.contextPath}/resources/images/ciscologo.png"
					height=25px; width="50px;" />
				</a>
			</div>
			<div class="col-lg-5 col-md-5 hidden-sm hidden-xs navbar-header"
				style="margin-left: -53%;">
				<a class="navbar-brand"
					style="color: white; font-family: 'Raleway', sans-serif; font-weight: none; font-size: 20px;"
					href="${pageContext.request.contextPath}/"> CCIX to LAE
					Migration Dashboard </a>
			</div>
			<div class="col-lg-7 col-md-7 col-sm-12 col-xs-12 navbar-header"
				style="margin-top: -3.5%; margin-left: 42%; color: white;">
				<a class="navbar-brand pull-right"
					href="#"
					style="color: #E6E6E6; font-family: 'Raleway', sans-serif; font-size: 15px;">
					${username}&nbsp;&nbsp;<span class="glyphicon glyphicon-cog"
					style="margin-top: 1%; color: #E6E6E6;"></span>
				</a>
			</div>
		</div>
	</nav>

	<nav class="navbar navbar-default">
		<div class="container-fluid maxScreenSize">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target=".search">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>
			<div id="navbar">
				<div class="col-lg-8 col-md-6 col-sm-4 col-xs-12">
					<ul class="nav navbar-nav">

						<spring:url value="/view" var="goToView">
						</spring:url>
						<li id="navView" class="navigation"><a href="${goToView}"
							style="font-family: 'Raleway', sans-serif;">View Reports</a></li>

						<spring:url value="/edit" var="goToEdit">
						</spring:url>
						<li id="navEdit" class="navigation">
						<c:choose>
										<c:when test="${(username == 'dhsanghv') || (username == 'smuvva') || (username == 'sagovind') || (username == 'tlittlef')}">
       <a href="${goToEdit}" style="font-family: 'Raleway', sans-serif;">Edit Plan</a>
    </c:when>
										<c:otherwise>
       <a href="${goToEdit}"
							style="font-family: 'Raleway', sans-serif; pointer-events: none;
   cursor: default; opacity: 0.8;">Edit Plan</a>
    </c:otherwise>
									</c:choose>
						
   
   </li>

						<spring:url value="/home" var="goToHome">
						</spring:url>
						<li id="navHome" class="navigation"><a href="${goToHome}"
							style="font-family: 'Raleway', sans-serif; pointer-events: none;
   cursor: default; opacity: 0.8;">Submit Plan</a></li>
					</ul>
				</div>
			</div>
			<div class="col-lg-4 col-md-6 col-sm-8 col-xs-12"
				style="padding-top: 7px; padding-left: 208px;">
				<a href="http://lae-mig.cloudapps.cisco.com/index.cgi" role="button"
					class="btn btn-warning"
					style="margin-left: 30px; border-color: #AAB0AF; background-color: #fff; color: #6E6E6E; font-size: 14px;"><img
					src="${pageContext.request.contextPath}/resources/images/settings_icon.png"
					width="20px" height="20px" />&nbsp;CCIX-LAE Migration Tool</a>
			</div>
		</div>
	</nav>
</div>
<br>

<div class="page_body">
	<div class="page_body_title">View Commitment Plan Reports</div>
	<h3 align="center">
		<span id="status1" class="glyphicon glyphicon-th"
			style="margin-top: 1%; color: #346581;" title="Tooltip here"></span>
		&nbsp;Cisco IT&nbsp;&nbsp;<span class=""></span>
	</h3>
	<hr>
	<br>
	<div class="row" style="margin-left: -15px; max-width: 100%;">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="panel panel-default">
					<div class="panel-body">
						<table id="tableData" class="table table-hover dt-responsive">
							<thead>
								<tr style="color: #346581;">
									<th style="text-align: center;">Migration Completed</th>
									<th style="text-align: center;">Committed</th>
									<!-- <th style="text-align: center;">In Progress</th> -->
									<th style="text-align: center;">Uncommitted</th>
									<th style="text-align: center;">Decommissioned</th>
									<th style="text-align: center;">To Be EOL</th>
									<th style="text-align: center;">Stay on CCIX</th>
								</tr>
							</thead>
							<tr>
								<td style="text-align: center;"><a
									href="${pageContext.request.contextPath}/completedApps"
									style="font-weight: bold; color: green;"><c:choose>
										<c:when test='${status.completed == null}'>
       0
    </c:when>
										<c:otherwise>
       <div id="comp"> ${status.completed} </div>
    </c:otherwise>
									</c:choose></a></td>
								<td style="text-align: center;"><a
									href="${pageContext.request.contextPath}/committedApps"
									style="font-weight: bold;"><c:choose>
										<c:when test='${status.committed == null}'>
      0
    </c:when>
										<c:otherwise>
       <div id="comm"> ${status.committed} </div>
    </c:otherwise>
									</c:choose></a></td>
								<%-- <td style="text-align: center;"><a
									href="${pageContext.request.contextPath}/inProgressApps"
									style="font-weight: bold;"><c:choose>
										<c:when test='${status.in_progress == null}'>
       0
    </c:when>
										<c:otherwise>
       <div id="prog"> ${status.in_progress} </div>
    </c:otherwise>
									</c:choose></a></td> --%>
								<td style="text-align: center;"><a
									href="${pageContext.request.contextPath}/uncommittedApps"
									style="font-weight: bold; color: red;"> <c:choose>
											<c:when test='${status.uncommitted == null}'>
       0
    </c:when>
											<c:otherwise>
       <div id="uncomm"> ${status.uncommitted} </div>
    </c:otherwise>
										</c:choose>
								</a></td>
								<td style="text-align: center;"><a
									href="${pageContext.request.contextPath}/decommApps"
									style="font-weight: bold;"><c:choose>
											<c:when test='${status.decommissioned == null}'>
       0
    </c:when>
											<c:otherwise>
        ${status.decommissioned}
    </c:otherwise>
										</c:choose></a></td>
								<td style="text-align: center;"><a
									href="${pageContext.request.contextPath}/eolApps"
									style="font-weight: bold;"> <c:choose>
											<c:when test='${status.eol == null}'>
       0
    </c:when>
											<c:otherwise>
        ${status.eol}
    </c:otherwise>
										</c:choose></a></td>
								<td style="text-align: center;"><a
									href="${pageContext.request.contextPath}/stayApps"
									style="font-weight: bold;"> <c:choose>
											<c:when test='${status.stay == null}'>
       0
    </c:when>
											<c:otherwise>
        ${status.stay}
    </c:otherwise>
										</c:choose></a></td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row" style="margin-left: -15px; max-width: 100%;">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="panel panel-default">
					<div class="panel-body" id="dvdata">
						<table id="tableBranchData"
							class="table table-hover dt-responsive">
							<thead>
								<tr style="color: #346581;">
								    <th>Senior Staff</th>
									<th>Service Owner</th>
									<th style="text-align: center;">Migration Status</th>
									<th style="text-align: center;">Committed</th>
									<th style="text-align: center;">Completed</th>
									<th style="text-align: center;">Uncommitted</th>
									
								</tr>
							</thead>
							<c:forEach items="${dir_table}" var="dir"><tr>
							<td> <%-- ${dir.svp_name.split(': ')[1]} --%>
							  ${dir.svp} 
							</td>
							<td>
								<spring:url value="/groupView/{id}"
					var="goTo">
					<spring:param name="id" value="${dir.director}"></spring:param>
				</spring:url>
							 <%-- <a
									href="${goTo}">${dir.director_name.split(': ')[1]}</a>  --%>
									 <a
									href="${goTo}">${dir.director}</a> 
									</td>
									
									<td style="text-align: center;">
									<c:set var="test" scope="session" value="${(dir.completed + dir.committed) / (dir.committed + dir.completed + dir.uncommitted)}"/>
							 <c:choose>
							 <c:when test="${test < 0.7 }">
							 	<span class="glyphicon glyphicon-th"
			 id="status2" style="color: red;" title="Tooltip here"></span>							 
			 </c:when>
			 <c:when test="${test > 0.9 }">
							 	<span class="glyphicon glyphicon-th"
			 id="status2" style="color: green;" title="Tooltip here"></span>							 
			 </c:when>
			 <c:otherwise>
			 <span class="glyphicon glyphicon-th"
			 id="status2" style="color: orange;" title="Tooltip here"></span>
			 </c:otherwise>
							 </c:choose>
							 
							</td>
									
								<td style="text-align: center;"><spring:url value="/dirCommittedView/{dirid1}"
					var="goTo">
					<spring:param name="dirid1" value="${dir.director}"></spring:param>
				</spring:url>
							<a
									href="${goTo}"><div id="committed">${dir.committed}</div></a></td>
									
								<td style="text-align: center;"><spring:url value="/dirCompletedView/{dirid2}"
					var="goTo">
					<spring:param name="dirid2" value="${dir.director}"></spring:param>
				</spring:url>
							<a
									href="${goTo}"><div id="completed">${dir.completed}</div></a></td>
									
								<td style="text-align: center;"><c:choose>
										<c:when test='${dir.uncommitted == null}'>
      <div id="uncommitted">0</div>
    </c:when>
										<c:otherwise>
        <spring:url value="/dirUncommittedView/{dirid3}"
					var="goTo">
					<spring:param name="dirid3" value="${dir.director}"></spring:param>
				</spring:url>
							<a
									href="${goTo}"><div id="uncommitted">${dir.uncommitted}</div></a>
    </c:otherwise>
									</c:choose></td>
								<!-- <td style="text-align: center;">Cisco Commerce</td> -->
		                       
							</tr></c:forEach>

						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


<div class="row" style="max-width: 100%; margin-left: 20px;">
	<div class="col-lg-7 col-md-12 col-sm-12 col-xs-12 clearRight">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="panel panel-default">
				<div class="panel-body">
					<div id="planned_mig"
						style="height: 400px; margin: 0 auto"></div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-lg-5 col-md-12 col-sm-12 col-xs-12  clearLeft"
		style="margin-left: -20px;">
		<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
			<div class="panel panel-default">
				<div class="panel-body">
					<div id="overall_breakup" style="min-height: 400px; margin: 0 auto"></div>
				</div>
			</div>

		</div>

	</div>
</div>
<br>
<br>
<br>
<br>

<div>
	<nav class="navbar navbar-default navbar-fixed-bottom"
		role="navigation">
		<a href="${pageContext.request.contextPath}/"> <img
			style="margin-top: 12px; float: left; margin-left: 20px;"
			src="${pageContext.request.contextPath}/resources/images/ciscologo_grey.png"
			width="50px" height="25px" />
		</a>
		<ul class="nav navbar-nav navbar-left"
			style="left: 83%; position: relative;">
			<li><a
				href="mailto:lae-operations@cisco.com?Subject=CCIX%20to%20LAE%20Migration%20Support"
				target="_top" style="font-family: 'Raleway', sans-serif;"> <font
					size="2px;" color="#AEB1B1"> Contact Us </font></a></li>
			<li><a href="#" style="font-family: 'Raleway', sans-serif;"><font
					size="2px;" color="#AEB1B1">About</font></a></li>
		</ul>
	</nav>
</div>