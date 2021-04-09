package com.douanier.controleur;

import java.io.IOException;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/home")
public class ServletHome extends HttpServlet {

	Logger logger = LogManager.getLogger(ServletHome.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.log(Level.INFO, "execution de doGet");
		req.getRequestDispatcher("/WEB-INF/vues/home/index.jsp").forward(req, resp);
	}

}
