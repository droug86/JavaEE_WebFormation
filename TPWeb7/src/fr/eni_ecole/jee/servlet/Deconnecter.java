package fr.eni_ecole.jee.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Deconnecter extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		deconnecter(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		deconnecter(request, response);
	}

	protected void deconnecter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		session.invalidate();
		response.sendRedirect(request.getContextPath()+"/");
	}
}
