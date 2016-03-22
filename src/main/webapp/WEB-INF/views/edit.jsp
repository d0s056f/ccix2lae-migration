<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="Monitor">
<meta name="author" content="Dhairya Sanghvi">

<title>CCIX to LAE Migration - Edit</title>

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
<script
	src="${pageContext.request.contextPath}/resources/js/bootstrap-dropdown.js"></script>

<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

<!-- CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<link
	href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css"
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

<link rel="stylesheet"
	href="https://kendo.cdn.telerik.com/2015.3.930/styles/kendo.common.min.css">
<link rel="stylesheet"
	href="https://kendo.cdn.telerik.com/2015.3.930/styles/kendo.default.min.css">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/css/bootstrap-datepicker.css">

<script
	src="https://kendo.cdn.telerik.com/2015.3.930/js/kendo.all.min.js"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.0/js/bootstrap-datepicker.js"></script>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$('.navigation').removeClass("active")
						$('#navEdit').addClass("active")

						// set the selected dropdown in the button text
						$("#listOrgs a")
								.click(
										function() {
											var functionalGroup = $(this)
													.text();
											$('#dropdownMenu1')
													.html(
															$(this).text()
																	+ ' <span class="caret"></span>');

											var request = $
													.ajax({
														url : '${pageContext.request.contextPath}/get-app',
														type : "POST",
														data : {
															functionalGroup : functionalGroup
														},

														dataType : "json"
													});
											request
													.done(function(msg) {
														$('#dropdownMenu2')
																.prop(
																		"disabled",
																		false);
														$('#listApps').empty();
														var appList = msg;

														$
																.each(
																		appList,
																		function(
																				key,
																				value) {
																			var ul = document
																					.getElementById("listApps");
																			var li = document
																					.createElement('li');
																			var a = document
																					.createElement('a');
																			ul
																					.appendChild(li);
																			li
																					.appendChild(a);
																			a
																					.setAttribute(
																							'data-ref',
																							key);
																			a
																					.setAttribute(
																							'data-name',
																							value);
																			a.innerHTML = value.app_id;
																		});
													});
											request
													.fail(function(jqXHR,
															textStatus) {
														alert("Cannot complete the request");
													});
										});
						var self;
						$('#listApps')
								.on(
										'click',
										'a',
										function() {
											self = $(this);
											$('#dropdownMenu2')
													.html(
															self.text()
																	+ ' <span class="caret"></span>');
											$('#dropdownMenu2').val(
													self.data('value'));
											$('#dropdownMenu2').attr(
													'data-ref',
													self.data('ref'));

										});

						$('#get')
								.on(
										'click',
										function() {
											var appId = self.text();
											var request = $
													.ajax({
														url : '${pageContext.request.contextPath}/get-app-info',
														type : 'POST',
														dataType : 'json',
														data : {
															appId : appId
														}
													});
											request
													.done(function(msg) {
														//				alert("done dana done "
														//						+ msg.svp + msg.director + msg.func_grp + msg.status + msg.mig_date);
														$('#toggler').css(
																"display",
																"block");
														document
																.getElementById('appid').innerHTML = appId;
														document
																.getElementById('app_id').value = appId;
														document
																.getElementById('func_grp').value = msg.func_grp;
														document
																.getElementById('svp').value = msg.svp;
														document
																.getElementById('director').value = msg.director;
														document
																.getElementById('mig_date').value = msg.mig_date;
														document
																.getElementById('status').value = msg.status;
														document
														.getElementById('cutover_type').value = msg.cutover_type;
														document
														.getElementById('migration_lead').value = msg.migration_lead;
														document
														.getElementById('comments').value = msg.comments;
													});
											request
													.fail(function(jqXHR,
															textStatus) {
														alert("Cannot complete the request");
													});

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
							style="font-family: 'Raleway', sans-serif;">Edit Plan</a></li>
							
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
					style="margin-left: 30px; border-color: #AAB0AF; background-color: #fff; color: #6E6E6E; font-family: 'Raleway', sans serif; font-size: 14px;"><img
					src="${pageContext.request.contextPath}/resources/images/settings_icon.png"
					width="20px" height="20px" />&nbsp;CCIX-LAE Migration Tool</a>
			</div>
		</div>
	</nav>
</div>
<br>

<div class="page_body">

	<div class="page_body_title">Submit your Commitment Plan</div>
	<br> <br>
	<div class="page_body_left">
		<div class="demo-section k-content">
			<div class="dropdown">
				<button class="btn btn-default dropdown-toggle" type="button"
					id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false">
					Select Functional Group <span class="caret"></span>
				</button>
				<ul class="dropdown-menu" aria-labelledby="dropdownMenu1"
					id="listOrgs">
					<c:forEach items="${orgs}" var="a">
						<li><a href="#">${a.func_group}</a></li>
					</c:forEach>
				</ul>
			</div>
			<br>
			<div class="dropdown">
				<button class="btn btn-default dropdown-toggle" type="button"
					id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false" disabled>
					Select Service Asset (JVM) <span class="caret"></span>
				</button>
				<ul class="dropdown-menu" aria-labelledby="dropdownMenu2"
					id="listApps">
					<%-- <c:forEach items="${xx}" var="b">
						<li><a href="#">${b.xx}</a></li>
					</c:forEach> --%>
				</ul>
			</div>
			<br>
			<button class="k-button k-primary" id="get"
				style="margin-top: 2em; float: left; font-size: 13px;">Get
				Started</button>

		</div>
		<br> <br>
	</div>
	<div class="page_body_right">
		<div class="container-fluid" id="toggler" style="display: none;">
			<h3 style="margin-left: -65px; font-weight: bolder;">
				Service Asset ID:
				<div id="appid" style="display: inline; color: #346581;"></div>
				<hr>
			</h3>
			<br>
			<form action="<c:url value="/savePlan" />" method="POST"
				class="form-horizontal" style="margin-left: -150px;">
				<div class="form-group">
					<input type="hidden" class="form-control" id="app_id" name="app_id">
					<label class="control-label col-sm-2">Organization</label>
					<div class="col-md-4">
						<input type="text" class="form-control" id="func_grp"
							name="func_grp" readonly>
					</div>
				</div>
				<br>
				<div class="form-group">
					<label class="control-label col-sm-2">SVP</label>
					<div class="col-md-4">
						<input type="text" class="form-control" id="svp" name="svp"
							readonly required>
					</div>
					<label class="control-label col-sm-1">Director</label>
					<div class="col-md-4">
						<input type="text" class="form-control" id="director"
							name="director" readonly>
					</div>
				</div>
				<br>
				<div class="form-group">
					<label class="control-label col-sm-2">POCs</label>
					<div class="col-md-3">
						<input type="text" class="form-control" id="mig_pocs"
							name="mig_pocs">
					</div>
					<label class="control-label col-sm-2">Migration Lead</label>
					<div class="col-md-3">
						<input type="text" class="form-control" id="migration_lead"
							name="migration_lead" required>
					</div>
					<label class="control-label col-sm-1">Cutover Type</label>
				<div class="col-md-1">
						<select class="form-control" id="cutover_type" name="cutover_type"
							required>
							<option value="N/A">N/A</option>
							<option value="Go-live">Go-live</option>
							<option value="Redirect">Redirect</option>
							<option value="Proxy">Proxy</option>
						</select>
					</div>
				</div>
				<br>
				<div class="form-group">
					<label class="control-label col-sm-2">Migration Date</label>
					<div class="col-md-2">
						<div id="datepicker" class="input-group date"
							data-date-format="mm-dd-yyyy">
							<input class="form-control" type="text" /> <span
								class="input-group-addon"><i
								class="glyphicon glyphicon-calendar"></i></span>
						</div>
					</div>
					<label class="control-label col-sm-3">Migration Quarter</label>
					<div class="col-md-2">
						<select class="form-control" id="mig_date" name="mig_date"
							required>
							<option value="N/A">N/A</option>
							<option value="Q4FY15">Q4FY15</option>
							<option value="Q1FY16">Q1FY16</option>
							<option value="Q2FY16">Q2FY16</option>
							<option value="Q3FY16">Q3FY16</option>
							<option value="Q4FY16">Q4FY16</option>
						</select>
					</div>
					<label class="control-label col-sm-1">Status</label>
					<div class="col-md-2">
						<select class="form-control" id="status" name="status">
							<option value="committed">Committed</option>
							<option value="in progress">In Progress</option>
							<option value="completed">Completed</option>
							<option value="stay">Stay</option>
							<option value="decommissioned">Decommissioned</option>
						</select>
					</div>
				</div>
				<br>
				<div class="form-group">
					<label class="control-label col-sm-2">Comments</label>
					<div class="col-md-4">
						<input type="text" class="form-control" id="comments"
							name="comments">
					</div>
				</div>
                <br>
				<button type="submit" class="btn btn-primary"
					style="margin-left: 550px;">Submit</button>
<br><br>
			</form>
<br><br>
		</div>
	</div>
</div>

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

<script>
	$(function() {
		$("#datepicker").datepicker({
			autoclose : true,
			todayHighlight : true
		}).datepicker('update', new Date());
		;
	});
</script>