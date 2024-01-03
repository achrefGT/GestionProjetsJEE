<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Project Management</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <script>
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
        <c:if test="${model.mode != 'update' && model.mode != 'search'}">
            <h1>Quick Access</h1>
        </c:if>
        <c:if test="${model.mode == 'update' || model.mode == 'search'}">
            <h1>Update mode</h1>
        </c:if>
        <br>

        <form action="projectController" method="post">
            <table class="table">
                <c:choose>
                    <c:when test="${model.mode == 'update' || model.mode == 'search'}">
                        <tr>
                            <td class="fw-bold">PROJECT CODE</td>
                            <td>${model.project.code}<input type="hidden" class="form-control" name="projectCode" value="${model.project.code}" /></td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${model.mode != 'update' && model.mode != 'search'}">
                            <tr>
                                <td class="fw-bold">PROJECT CODE</td>
                                <td><input type="text" name="projectCode" class="form-control" required/></td>
                            </tr>
                        </c:if>
                    </c:otherwise>
                </c:choose>

                <tr>
                    <td class="fw-bold">PROJECT DESCRIPTION</td>
                    <td><input type="text" name="projectDescription" value="${model.project.description}" class="form-control" /></td>
                </tr>
                <tr>
                    <td class="fw-bold">START DATE</td>
                    <td><input type="date" name="startDate" value="${model.project.startDate}" class="form-control" /></td>
                </tr>
            </table>

            <div class="btn-group d-flex align-items-center justify-content-center">
                <c:if test="${model.mode != 'update' && model.mode != 'search'}">
                    <button type="submit" class="btn btn-primary" name="action" value="create">Create</button>
                </c:if>
                <button type="submit" class="btn btn-primary" name="action" value="remove">Remove</button>
                <button type="submit" class="btn btn-primary" name="action" value="update">Update</button>
            </div>
        </form>

        <div class="btn-group d-flex align-items-center justify-content-center">
            <button type="submit" class="btn btn-outline-primary" onclick="redirectToReference('projectController?action=list')">List of Projects</button>
            <c:if test="${model.mode == 'update' || model.mode == 'search'}">
                <button class="btn btn-outline-primary" onclick="redirectToReference('projectController?action=projectTasks&projectCode=${model.project.code}')">List of Tasks</button>
            </c:if>
        </div>
    </div>
</body>
</html>
