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
            var rep = confirm("Are you sure you want to delete?");
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
    	<h1>List of Tasks</h1><br>
        <c:if test="${ model.mode == 'projectView' }">
            <table class="table table-hover text-center">
                <tr>
                    <th>CODE</th> <th>DESCRIPTION</th> <th>START DATE</th> <th>END DATE</th><th>REMOVE FROM PROJECT</th><th>UPDATE</th>
                </tr>
                <c:forEach items="${model.tasks}" var="t">
                    <tr>
                        <td>${t.code}</td>
                        <td>${t.description}</td>
                        <td>${t.startDate}</td>
                        <td>${t.endDate}</td>
                        <td><button class="btn btn-outline-danger" onclick="javascript:confirmer('projectController?action=removeTask&taskCode=${ t.code }&projectCode=${ model.project.code }')">Remove</button></td>
    					<td><button class="btn btn-outline-secondary" onclick="window.location.href='taskController?mode=update&taskCode=${ t.code }'">Update</button></td>
                    </tr>
                </c:forEach>
            </table>
            <div class="btn-group d-flex align-items-center justify-content-center">
                <button type="button" class="btn btn-primary btn-block" onclick="redirectToReference('projectController?action=addTask&projectCode=${ model.project.code }')">Add a Task</button>
            </div>
        </c:if>
        
        <c:if test="${ model.mode == 'taskView' }">
        	<form action="taskController" method="post">
			<table class="table table-hover ">
				<tr>
					<td class="fw-bold">SEARCH BY CODE </td>
					<td><input class="form-control" name="taskCode" required/></td>
					<td><button type="submit" class="btn btn-primary" name="action" value="search">Search</button></td>
				</tr>
			</table>
		</form>
		<br>
            <table class="table table-hover text-center">
                <tr>
                    <th>CODE</th> <th>DESCRIPTION</th> <th>START DATE</th> <th>END DATE</th> <th>Associated Project</th><th>REMOVE</th><th>UPDATE</th>
                </tr>
                <c:forEach items="${model.tasks}" var="t">
                    <tr>
                        <td>${t.code}</td>
                        <td>${t.description}</td>
                        <td>${t.startDate}</td>
                        <td>${t.endDate}</td>
                        <td><a href="projectController?action=projectTasks&projectCode=${t.project.code}">${t.project.code}</a></td>
                        <td><button class="btn btn-outline-danger" onclick="javascript:confirmer('taskController?action=remove&taskCode=${ t.code }')">Remove</button></td>
    					<td><button class="btn btn-outline-secondary" onclick="window.location.href='taskController?mode=update&taskCode=${ t.code }'">Update</button></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
</c:if>
