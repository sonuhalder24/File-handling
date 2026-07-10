<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>File Upload</title>
</head>
<body>
	<h1>File Upload</h1>
	<form action="fileUploadServlet" method="post" enctype="multipart/form-data">
		<input type="file" name="fileAttachment" id="fileAttachment" />
		<input type="submit" value="Upload" id="uploadBtn" />
	</form>
	<a href="uploadedFilesServlet">View Uploaded Files</a>
</body>
</html>
