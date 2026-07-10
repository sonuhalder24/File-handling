<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Upload Successful</title>
</head>
<body>
	<h1>File Uploaded Successfully</h1>
	<p>File Name: <%= request.getParameter("fileName") %></p>
	<a href="uploadedFilesServlet">List all Uploaded Files</a>
</body>
</html>
