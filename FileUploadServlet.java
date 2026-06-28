package com;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
public class FileUploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIR = "/tmp/uploads";

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}

	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		File uploadDir = new File(UPLOAD_DIR);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}

		Part filePart = request.getPart("fileAttachment");
		String fileName = getFileName(filePart);

		if (fileName != null && !fileName.isEmpty()) {
			File dest = new File(uploadDir, fileName);
			filePart.write(dest.getAbsolutePath());
		}

		response.sendRedirect("fileuploadResponse.jsp");
	}

	private String getFileName(Part part) {
		String contentDisposition = part.getHeader("content-disposition");
		for (String token : contentDisposition.split(";")) {
			if (token.trim().startsWith("filename")) {
				return token.substring(token.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}
}