package com;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UploadedFilesServlet
 */
public class UploadedFilesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadedFilesServlet() {
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
		File uploadDir = new File("/tmp/uploads");
		List<UploadDetail> fileList = new ArrayList<UploadDetail>();

		if (uploadDir.exists() && uploadDir.isDirectory()) {
			File[] files = uploadDir.listFiles();
			if (files != null) {
				for (File f : files) {
					fileList.add(new UploadDetail(f.getName(), f.length()));
				}
			}
		}

		request.setAttribute("fileList", fileList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("allfiles.jsp");
		dispatcher.forward(request, response);
	}

}
