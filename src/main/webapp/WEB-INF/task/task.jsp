<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Task Management</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
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

        <form action="taskController" method="post">
            <table class="table">
                <c:if test="${model.mode == 'update' || model.mode == 'search'}">
                    <tr>
                        <td class="fw-bold">TASK CODE</td>
                        <td>${model.task.code}<input type="hidden" name="taskCode" value="${model.task.code}" class="form-control"/></td>
                        <td></td>
                    </tr>
                </c:if>

                <c:if test="${model.mode != 'update' && model.mode != 'search'}">
                    <tr>
                        <td class="fw-bold">TASK CODE</td>
                        <td><input type="text" name="taskCode" class="form-control" required/></td>
                    </tr>
                </c:if>

                <tr>
                    <td class="fw-bold">TASK DESCRIPTION</td>
                    <td><input type="text" name="taskDescription" value="${model.task.description}" class="form-control"/></td>
                </tr>
                <tr>
                    <td class="fw-bold">START DATE</td>
                    <td><input type="date" name="startDate" value="${model.task.startDate}" class="form-control"/></td>
                </tr>
                <tr>
                    <td class="fw-bold">END DATE</td>
                    <td><input type="date" name="endDate" value="${model.task.endDate}" class="form-control"/></td>
                </tr>

                <tr>
                    <td class="fw-bold">PROJECT</td>
                    <td>
                        <select name="taskProject" class="form-select mt-0">
                            <c:choose>
                                <c:when test="${model.mode != 'addTaskToProject' && model.mode != 'update' && model.mode != 'search'}">
                                    <c:forEach items="${model.projects}" var="p">
                                        <option value="${p.code}">${p.code}</option>
                                    </c:forEach>
                                </c:when>
                                <c:when test="${model.mode == 'addTaskToProject'}">
                                    <option value="${model.project.code}">${model.project.code}</option>
                                </c:when>
                                <c:when test="${model.mode == 'update' || model.mode == 'search'}">
                                    <option value="${model.project.code}">${model.task.project.code}</option>
                                </c:when>
                            </c:choose>
                        </select>
                    </td>
                </tr>
            </table>

            <div class="btn-group d-flex align-items-center justify-content-center">
                <c:choose>
                    <c:when test="${model.mode == 'addTaskToProject'}">
                        <button type="submit" class="btn btn-primary" name="action" value="create">Add Task</button>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${model.mode != 'update' && model.mode != 'search'}"><button type="submit" class="btn btn-primary" name="action" value="create">Create</button></c:if>
                        <button type="submit" class="btn btn-primary" name="action" value="remove">Remove</button>
                        <button type="submit" class="btn btn-primary" name="action" value="update">Update</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </form>

       <div class="btn-group d-flex align-items-center justify-content-center">
            <button type="submit" class="btn btn-outline-primary" onclick="redirectToReference('taskController?action=list')">List of Tasks</button>
        </div>
    </div>
</body>
</html>