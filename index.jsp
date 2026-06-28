<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><title>File Upload</title></head>
<body>
    <h2>Upload File</h2>
    <form action="fileUploadServlet" method="post" enctype="multipart/form-data">
        <input type="file" id="fileAttachment" name="fileAttachment" />
        <input type="submit" id="uploadBtn" value="Upload" />
    </form>
</body>
</html>
