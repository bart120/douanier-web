package com.douanier.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.douanier.model.Contact;
import com.douanier.utils.BddConnexion;

public class ContactDAO {
	
	public Contact getContactByID(int id) {
		/*Class.forName("org.postgresql.Driver");
		
		try(Connection connexion = DriverManager.getConnection("jdbc:postgresql://formationjava.postgres.database.azure.com:5432/jee?user=formation@formationjava&password=Inow@2021")){
			
		//sql...
			PreparedStatement stat = connexion.prepareStatement("SELECT * FROM contact where id=4");
			
			try {
				connexion.commit();
			}catch (Exception e) {
				connexion.rollback();
			}
			
		}*/
	}

	public ArrayList<Contact> listerContacts() throws SQLException, ClassNotFoundException {
		
		/*Class.forName("org.postgresql.Driver");
		
		try(Connection connexion = DriverManager.getConnection("jdbc:postgresql://formationjava.postgres.database.azure.com:5432/jee?user=formation@formationjava&password=Inow@2021")){
			
		//sql...
			PreparedStatement stat = connexion.prepareStatement("SELECT * FROM contact");
			
			try {
				connexion.commit();
			}catch (Exception e) {
				connexion.rollback();
			}
			
		}*/
		
		try (Connection con =  BddConnexion.getInstance().getConnexion()){
			
		}
		
		Connection con =  BddConnexion.getInstance().getConnexion();
		try {
			
			
		}catch (Exception e) {
			con.fermer();
		}
		
		
	}
	
}
