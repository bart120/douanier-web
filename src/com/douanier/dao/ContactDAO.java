package com.douanier.dao;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.douanier.model.Contact;
import com.douanier.utils.BddConnexion;

public class ContactDAO {

	public static ArrayList<Contact> listerContacts() {
		ArrayList<Contact> contacts = new ArrayList<>();

		//try(Connection con = BddConnexion.getInstance().getConnexion()){
		
		try (BddConnexion bddcon = BddConnexion.getInstance()) {
			//Connection con = bddcon.getConnexion();
			// SQL
			try (PreparedStatement prep = bddcon.getConnexion().prepareStatement("SELECT * FROM contact ORDER BY nom")) {
				
				try (ResultSet res = prep.executeQuery()) {
					while(res.next()) {
						Contact c = new Contact();
						c.setNom(res.getString("nom"));
						c.setPrenom(res.getString("prenom"));
						c.setMail(res.getString("mail"));
						c.setDateNaissance(res.getDate("datenaissance"));
						c.setId(res.getInt("id"));
						contacts.add(c);
					}
				}/*finally {
					res.close();
				}*/
			}
			return contacts;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
	
	public static Contact getById(int id) {
		try (BddConnexion bddcon = BddConnexion.getInstance()) {
			try(PreparedStatement prep = bddcon.getConnexion().prepareStatement("SELECT * FROM contact WHERE id=?")){
				prep.setInt(1, id);
				try(ResultSet res = prep.executeQuery()){
					if(res.next()) {
						Contact c = new Contact();
						c.setNom(res.getString("nom"));
						c.setPrenom(res.getString("prenom"));
						c.setMail(res.getString("mail"));
						c.setDateNaissance(res.getDate("datenaissance"));
						c.setId(res.getInt("id"));
						return c;
					}
				}
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void enregistrer(Contact c) {
		String req = "INSERT INTO contact (nom, prenom, mail, datenaissance) VALUES (?, ?, ?, ?)";
		try (BddConnexion bddcon = BddConnexion.getInstance()) {
			 try(PreparedStatement prep =  bddcon.getConnexion().prepareStatement(req)){
				 prep.setString(1, c.getNom());
				 prep.setString(2, c.getPrenom());
				 prep.setString(3, c.getMail());
				 Date dt = new Date(c.getDateNaissance().getTime());
				 prep.setDate(4, dt);
				 
				 prep.executeUpdate();
			 }
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
