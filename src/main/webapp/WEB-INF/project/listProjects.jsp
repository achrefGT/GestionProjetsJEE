<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>List of projects</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <script type="text/javascript">
	function confirmer(url) {
		var rep = confirm("Are you sure you want to delete ?");
		if (rep == true) {
			document.location = url;
		}
	}
    function redirectToReference(url) {
        window.location.href = url;
    }
	</script>
</head>
<body>
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	  <div class="container-fluid">
	    <ul class="navbar-nav">
	      <li class="nav-item">
	        <a class="nav-link active" href="controller">Project Management</a>
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
	<div class="container p-5 my-5">
	<form action="projectController" method="post">
		<table class="table table-hover text-center">
			<tr>
				<td class="fw-bold">SEARCH BY CODE </td>
				<td><input class="form-control" name="projectCode" required/></td>
				<td><button type="submit" class="btn btn-primary" name="action" value="search">Search</button></td>
			</tr>
		</table>
	</form>
		<table class="table table-hover text-center">
		 <tr>
			<th>CODE</th> <th>DESCRIPTION</th> <th>STAR DATE</th><th>REMOVE</th><th>UPDATE</th>
		 </tr>
			<c:forEach  items="${model.projects}" var="p">
				<tr>
					<td><a href="projectController?action=projectTasks&projectCode=${ p.code }">${p.code}</a></td>
					<td>${p.description}</td>
					<td>${p.startDate}</td>
					<td><button class="btn btn-outline-danger" onclick="javascript:confirmer('projectController?action=remove&projectCode=${p.code}')">Remove</button></td>
    				<td><button class="btn btn-outline-secondary" onclick="window.location.href='projectController?mode=update&projectCode=${p.code}'">Update</button></td>
				</tr>
			</c:forEach>

		</table>
	</div>
</body>
</html>