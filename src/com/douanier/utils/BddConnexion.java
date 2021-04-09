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
	
	/**
	 * Récupère l'instance de la connexion de la bdd
	 * 
	 * @return Une connexion ouverte sur la base
	 * @throws ClassNotFoundException Le driver JBDC n'est pas trouvé
	 * @throws SQLException Une erreur dans la requete SQL
	 */
	public static BddConnexion getInstance() throws ClassNotFoundException, SQLException {
		if(instance != null) {
			return instance;
		}else {
			instance = new BddConnexion();
			Class.forName("org.postgresql.Driver");
			instance.connexion = DriverManager.getConnection("jdbc:postgresql://formationjava.postgres.database.azure.com:5432/jee?user=formation@formationjava&password=Inow@2021");
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
