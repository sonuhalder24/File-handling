<%@page import="java.util.List"%>
<%@page import="com.UploadDetail"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Uploaded Files</title>
</head>
<body>
	<h1>Uploaded Files</h1>
	<ul>
<%
	List<UploadDetail> uploadedFiles = (List<UploadDetail>) request.getAttribute("uploadedFiles");
	if (uploadedFiles != null) {
		for (UploadDetail file : uploadedFiles) {
%>
		<li><a href="downloadServlet?fileName=<%= file.getFileName() %>"><%= file.getFileName() %></a></li>
<%
		}
	}
%>
	</ul>
</body>
</html>
