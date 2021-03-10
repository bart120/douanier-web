package com.douanier.controleur;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.douanier.dao.ContactDAO;
import com.douanier.model.Contact;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/contact/*")
public class ServletContact extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getPathInfo();

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

		resp.sendError(404);
	}

}
