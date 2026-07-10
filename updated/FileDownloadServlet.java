package com;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FileDownloadServlet
 */
public class FileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static int BUFFER_SIZE = 1024 * 100;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileDownloadServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		handleRequest(request, response);
	}

	public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String fileName = request.getParameter("fileName");
		File file = new File(FileStore.UPLOAD_DIR, fileName);

		if (fileName == null || !file.exists()) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found: " + fileName);
			return;
		}

		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentLength((int) file.length());

		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
			OutputStream out = response.getOutputStream();
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead;
			while ((bytesRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
			}
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
