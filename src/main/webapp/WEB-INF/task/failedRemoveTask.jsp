<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Failed - Remove Task</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
	<style><%@include file="../css/error.css"%></style>
</head>
<body>
    <h4>Task Removal Failed</h4>
    <h5>Error Details :</h5>
    <div class="box">
        <span></span><span></span>
        <span></span><span></span>
        <span></span>
    </div>
    <div class="box">
        <span></span><span></span>
        <span></span><span></span>
        <span></span>
    </div>
    <p>
	    ${ model.errors }<br><br>
	    <button type="button" class="btn btn-danger custom-btn" onclick="window.location.href='taskController'">Go back</button>
	</p>
</body>
</html>
