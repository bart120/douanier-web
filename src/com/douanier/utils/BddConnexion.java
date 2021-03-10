package com.douanier.utils;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class BddConnexion implements Closeable {
	
	private Connection connexion;
	
	private static BddConnexion instance;
	
	private BddConnexion() {
		
	}
	
	public static BddConnexion getInstance() throws ClassNotFoundException, SQLException {
		if(instance != null) {
			return instance;
		}else {
			instance = new BddConnexion();
			Class.forName("org.postgresql.Driver");
			instance.connexion = DriverManager.getConnection("");
			return instance;
		}
	}
	
	public Connection getConnexion() {
		return this.connexion;
	}

	@Override
	public void close() throws IOException {
		
		try {
			if(instance != null) {
				if(!instance.connexion.isClosed()) {
					if(!instance.connexion.getAutoCommit()) {
						try {
							instance.connexion.commit();
						}catch (Exception e) {
							instance.connexion.rollback();
						}
					}
					instance.connexion.close();
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			instance = null;	
		}
		
	}

}
