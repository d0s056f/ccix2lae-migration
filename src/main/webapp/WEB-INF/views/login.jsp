<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<head>
	
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Monitor">
    <meta name="author" content="Dhairya Sanghvi">
    
	<title>CCIX to LAE Migration - Login</title>
	
	<!-- Set the icon for the browser tab -->
	<link type="image/x-icon" rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/settings_icon.png">
	
	<!-- Java Scripts -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.2/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
		
	<!-- CSS -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
	<link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	
	<link href='https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300' rel='stylesheet' type='text/css' />
	<link href='https://fonts.googleapis.com/css?family=Raleway' rel='stylesheet' type='text/css'>
	<link href='https://fonts.googleapis.com/css?family=Roboto+Condensed' rel='stylesheet' type='text/css'>
	<link href='https://fonts.googleapis.com/css?family=Work+Sans:400,300,700' rel='stylesheet' type='text/css'>
	
</head>
<div>
	<nav class="navbar navbar-inverse navbar-static-top"
		style="background: -moz-linear-gradient(#646464, #323232);
										background: -webkit-linear-gradient(#646464, #323232);
										background: linear-gradient(#646464, #323232);
										background: -o-linear-gradient(#646464, #323232);
										background-color:#323232;">
		<div class="container-fluid">
		<div class="col-lg-7 col-md-7 col-sm-12 col-xs-12 navbar-header">
				<a class="navbar-brand pull-left" style="padding: 12px;"
					href="${pageContext.request.contextPath}/">
					<img src="${pageContext.request.contextPath}/resources/images/ciscologo.png" height=25px; width="50px;" />
				</a>				
			</div>
			<div class="col-lg-5 col-md-5 hidden-sm hidden-xs navbar-header" style="margin-left: -53%;">
				<a class="navbar-brand" style="color: white; font-family: 'Raleway', sans-serif; font-weight: none; font-size: 20px;" href="${pageContext.request.contextPath}/">
					CCIX to LAE Migration Dashboard </a>
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
				<ul class="nav navbar-nav" style="margin-left: -2%;">
					<li><a href="#" style="font-family: 'Raleway', sans-serif;">Login</a></li>						
				</ul>
			</div>
		</div>
	</div>
</nav>
</div>
<br><br>




<div class="login_container">
  <div class="profile">
    <button class="profile__avatar" id="toggleProfile">
     <img src="${pageContext.request.contextPath}/resources/images/login-icon.png" alt="Avatar" /> 
    </button>
    <div class="profile__form" style="margin-top: 70px;">
      <div class="profile__fields">
        <div class="field">
          <input type="text" id="fieldUser" class="input" required pattern=.*\S.* />
          <label for="fieldUser" class="label" style=" position: absolute;
  height: 2rem;
  line-height: 2rem;
  bottom: 0;
  color: #999;
  transition: all .3s cubic-bezier(0.175, 0.885, 0.32, 1.275);
   font-family: 'Raleway', sans-serif;"> CEC Username</label>
        </div>
        <div class="field">
          <input type="password" id="fieldPassword" class="input" required pattern=.*\S.* />
          <label for="fieldPassword" class="label" style=" position: absolute;
  height: 2rem;
  line-height: 2rem;
  bottom: 0;
  color: #999;
  transition: all .3s cubic-bezier(0.175, 0.885, 0.32, 1.275);
  font-family: 'Raleway', sans-serif;">CEC Password</label>
        </div>
        <div class="profile__footer">
        <a href="${pageContext.request.contextPath}/view"> <button class="btn" style=" border: 0;
  font-size: 0.95rem;
  height: 2.5rem;
  line-height: 2.5rem;
  padding: 0 1.5rem;
  color: white;
  background: #29516B;
  text-transform: uppercase;
  border-radius: .25rem;
  letter-spacing: .2em;
  transition: background .2s;">Login</button></a>
        </div>
      </div>
     </div>
  </div>
</div>
    
<script src="${pageContext.request.contextPath}/resources/js/index.js"></script>



<br><br>
<div>
<nav class="navbar navbar-default navbar-fixed-bottom" role="navigation">
<a href="${pageContext.request.contextPath}/"> 
				<img style="margin-top:12px; float:left; margin-left:20px;" src="${pageContext.request.contextPath}/resources/images/ciscologo_grey.png" width="50px" height="25px"/>
			</a>
	<ul class="nav navbar-nav navbar-left" style="left: 83%; position: relative;">
		<li><a href="mailto:lae-operations@cisco.com?Subject=CCIX%20to%20LAE%20Migration%20Support" target="_top" style="font-family: 'Raleway', sans-serif;">
		<font size="2px;" color="#AEB1B1"> Contact Us </font></a></li>
					<li><a href="#" style="font-family: 'Raleway', sans-serif;"><font size="2px;" color="#AEB1B1">About</font></a></li>
	</ul>
</nav>
</div>