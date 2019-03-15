package fr.eni_ecole.jee.util;

import java.sql.*;
import javax.naming.*;
import javax.sql.*;

public class AccesBase {	
	public static Connection getConnection() throws SQLException{
		Connection cnx=null;

		// Charger l'annuaire JNDI
		InitialContext jndi = null;
		try {
			jndi = new InitialContext();
		} catch (NamingException ne) {
			ne.printStackTrace();
			throw new SQLException("Impossible d'atteindre l'arbre JNDI");
		}
		
				
		// Chercher le pool de connexions dans l'annuaire
		DataSource ds = null;
		try {
			ds=(DataSource) jndi.lookup("java:comp/env/jdbc/dsTPWeb");
		} catch (NamingException ne) {
			ne.printStackTrace();
			throw new SQLException("Pool de connexion introuvable dans l'arbre jndi");
		}
		
		// Activer une connexion du pool
		cnx=ds.getConnection();
		
		return cnx;
	}
}
