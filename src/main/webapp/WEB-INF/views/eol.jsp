<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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


<script type="text/javascript">
	$(document).ready(function() {
		$('.navigation').removeClass("active")
		$('#navView').addClass("active")
	});
</script>
<script>
	$(document).ready(function() {
		$('#tableBranchData').DataTable({
			dom : 'Bfrtip',
			buttons : [ {
				extend : 'excel',
				text : 'Export to Excel'
			} ]
		});
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
						<li id="navEdit" class="navigation"><a href="${goToEdit}"
							style="font-family: 'Raleway', sans-serif; pointer-events: none;
   cursor: default; opacity: 0.8;">Edit Plan</a></li>

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
		<span class="glyphicon glyphicon-th"
			style="margin-top: 1%; color: #346581;" title="Tooltip here"></span>
		&nbsp;Cisco IT - To Be EOL Apps
	</h3>
	<hr>
	<br>
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