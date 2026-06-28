<%@page import="java.util.List"%>
<%@page import="com.UploadDetail"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><title>Uploaded Files</title></head>
<body>
    <h2>Uploaded Files</h2>
    <table border="1">
        <tr><th>File Name</th><th>File Size</th><th>Actions</th></tr>
        <%
            List<UploadDetail> fileList = (List<UploadDetail>) request.getAttribute("fileList");
            if (fileList != null) {
                for (UploadDetail f : fileList) {
        %>
        <tr>
            <td><%= f.getFileName() %></td>
            <td><%= f.getFileSize() %> bytes</td>
            <td><a href="downloadServlet?fileName=<%= f.getFileName() %>">Download</a></td>
        </tr>
        <%
                }
            }
        %>
    </table>
    <br/><a href="index.jsp">Upload another file</a>
</body>
</html>
