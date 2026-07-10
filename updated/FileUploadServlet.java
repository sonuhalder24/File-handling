package com;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


@MultipartConfig
public class FileUploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;


	/***** This Method Is Called By The Servlet Container To Process A 'POST' Request *****/
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		handleRequest(request, response);
	}

	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Part filePart = request.getPart("fileAttachment");
		String fileName = extractFileName(filePart);

		File uploadDir = new File(FileStore.UPLOAD_DIR);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}

		File destFile = new File(uploadDir, fileName);
		try (InputStream in = filePart.getInputStream();
				OutputStream out = new FileOutputStream(destFile)) {
			byte[] buffer = new byte[4096];
			int bytesRead;
			while ((bytesRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
			}
		}

		FileStore.addUploadedFile(new UploadDetail(fileName, destFile.length()));

		response.sendRedirect(request.getContextPath() + "/fileuploadResponse.jsp?fileName="
				+ URLEncoder.encode(fileName, "UTF-8"));
	}

	private String extractFileName(Part part) {
		String contentDisposition = part.getHeader("content-disposition");
		for (String item : contentDisposition.split(";")) {
			if (item.trim().startsWith("filename")) {
				return item.substring(item.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return "";
	}

}
