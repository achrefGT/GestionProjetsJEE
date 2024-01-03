<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Home</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	  <div class="container-fluid">
	    <ul class="navbar-nav">
	      <li class="nav-item">
	        <a class="nav-link active" href="#">Project Management</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="controller?view=project">Project</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="controller?view=task">Task</a>
	      </li>
	    </ul>
	  </div>
	</nav>
	<div class="container p-5 my-5 border">
	  <h1>Welcome to the Project Management Web Application</h1>
	  <p> Made by <strong>ACHREF GATAA</strong> for the JEE exam</p>
	</div>
    
</body>
</html>