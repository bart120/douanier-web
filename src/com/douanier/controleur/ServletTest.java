package com.douanier.controleur;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ServletTest extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String action = req.getPathInfo();
		if(action.equals("/bonjour")) {
			req.getRequestDispatcher("/WEB-INF/vues/test/bonjour.jsp").forward(req, resp);
			return;
		}
		
		if(action.equals("/google")) {
			resp.sendRedirect("https://www.google.fr");
			return;
		}
		
		resp.sendError(404, "L'action demandée n'existe pas.");		
		
	}

}
