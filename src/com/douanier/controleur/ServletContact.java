package com.douanier.controleur;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.douanier.dao.ContactDAO;
import com.douanier.model.Contact;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet pour la formation JAVA
 * @author vlecl
 * @version 1
 * @see ContactDAO
 */
@WebServlet("/contact/*")
public class ServletContact extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger("log.debug");

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String action = req.getPathInfo();
			
			logger.debug("doGet " + action);
			
			/* bloc
			 * sur plusieurs lignes
			 */
			if (action == null) {
				ArrayList<Contact> liste = ContactDAO.listerContacts();
				req.setAttribute("contacts", liste);
				req.getRequestDispatcher("/WEB-INF/vues/contact/liste.jsp").forward(req, resp);
				return;
			}
	
			if (action.equals("/detail")) {
				int idContact = Integer.parseInt(req.getParameter("id"));
	
				Contact c = ContactDAO.getById(idContact);
	
				req.setAttribute("c", c);
				req.getRequestDispatcher("/WEB-INF/vues/contact/detail.jsp").forward(req, resp);
				return;
			}
	
			if (action.equals("/creer")) {
				req.getRequestDispatcher("/WEB-INF/vues/contact/creer.jsp").forward(req, resp);
				return;
			}
	
			resp.sendError(404);
		}catch(Exception e) {
			req.getRequestDispatcher("/WEB-INF/vues/erreur.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getPathInfo();

		if (action.equals("/creer")) {
			try {
				Contact c = new Contact();
				c.setNom(req.getParameter("inputNom"));
				c.setPrenom(req.getParameter("inputPrenom"));
				c.setMail(req.getParameter("inputMail"));
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

				c.setDateNaissance(sf.parse(req.getParameter("inputDateNaissance")));

				ContactDAO.enregistrer(c);

				resp.sendRedirect("/douanier-web/contact");
				return;
				
			} catch (ParseException e) {
				e.printStackTrace();
				req.getRequestDispatcher("/WEB-INF/vues/contact/creer.jsp").forward(req, resp);
				return;
			}
		}
		if (action.equals("/api")) {
			Contact c = ContactDAO.getById(1);
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			
			Gson gson = new Gson();
			String json =  gson.toJson(c);
			
			resp.getWriter().print(json);
			resp.getWriter().flush();
			resp.setStatus(200);
			return;
		}

		resp.sendError(404);
	}

}
