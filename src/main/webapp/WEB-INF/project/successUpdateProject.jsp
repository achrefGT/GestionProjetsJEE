<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Success - Update Project</title>
    
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  	
  	<!-- Bootstrap JS -->
  	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
  	
  	<!-- Normalize CSS -->
  	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
    
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Nunito+Sans:400,400i,700,900&display=swap" rel="stylesheet">
    
    <!-- Custom CSS -->
    <style>
        <%@include file="../css/success.css"%>
    </style>
</head>
<body>
	<div class="card">
        <div class="main-container">
            <div class="check-container">
                <div class="check-background">
                    <svg viewBox="0 0 65 51" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path d="M7 25L27.3077 44L58.5 7" stroke="white" stroke-width="13" stroke-linecap="round" stroke-linejoin="round" />
                    </svg>
                </div>
                <div class="check-shadow"></div>
            </div>
        <h1>Success - Project Updated</h1> 
        <p>The project has been successfully Updated.</p>
        <br>
        <h5>Project details:</h5> 
        <table class="table text-center">
            <thead>
                <tr>
                    <th>Project Code</th>
                    <th>Project Description</th>
                    <th>Project Start Date</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>${ model.project.code }</td>
                    <td>${ model.project.description }</td>
                    <td>${ model.project.startDate }</td>
                </tr>
            </tbody>
        </table>
        <br>
        <button type="button" class="btn btn-success" onclick="window.location.href='projectController'">Go back</button>
        </div>
	</div>
</body>
</html>
