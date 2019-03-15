package fr.eni_ecole.jee.dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import fr.eni_ecole.jee.bean.*;
import fr.eni_ecole.jee.util.*;

public class StagiaireDAO {
	

	/*
	 * Recherche par login et mot de passe
	 */
	public static Stagiaire rechercher(Stagiaire stagiaire) throws SQLException{
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		try{
			cnx = AccesBase.getConnection();
			rqt = cnx.prepareStatement("select id, nom, prenom from stagiaires where email=? and motdepasse=?");
			rqt.setString(1, stagiaire.getEmail());
			rqt.setString(2, stagiaire.getMotDePasse());
			rs=rqt.executeQuery();
			// SI on trouve au moins 1 résultat, on prend le 1er pour mettre à jour les informations du stagiaire utilisé pour la recherche.
			if (rs.next()){
				stagiaire.setId(rs.getInt("id"));
				stagiaire.setNom(rs.getString("nom"));
				stagiaire.setPrenom(rs.getString("prenom"));
			}
			// ...sinon on renvoie null
			else {
				stagiaire = null;
			}
			
		}finally{
			if (rs!=null) rs.close();
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		return stagiaire;
	}
	public static void modifierPassword(Stagiaire stagiaire,String password) throws SQLException{
		Connection cnx=null;
		PreparedStatement rqt=null;
		try{
			cnx=AccesBase.getConnection();
			rqt=cnx.prepareStatement("update stagiaires set motdepasse= ? where id = ?");
			rqt.setString(1, password);
			rqt.setInt(2, stagiaire.getId());

			rqt.executeUpdate();
		}finally{
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
	}
	
	/**
	 * 
	 * @return La liste peut être vide mais jamais <code>null</code>
	 * @throws SQLException
	 */
	public static List<Stagiaire> lister() throws SQLException {
		
		List<Stagiaire> listeStagiaires = new ArrayList<Stagiaire>();
		
		Connection cnx = null;
		PreparedStatement rqt = null;
		ResultSet rs = null;
		try{
			cnx = AccesBase.getConnection();
			rqt = cnx.prepareStatement("select * from stagiaires");
			rs=rqt.executeQuery();
			
			while (rs.next()){
				Stagiaire stagiaire = new Stagiaire();
				stagiaire.setId(rs.getInt("id"));
				stagiaire.setNom(rs.getString("nom"));
				stagiaire.setPrenom(rs.getString("prenom"));
				stagiaire.setEmail(rs.getString("email"));
				stagiaire.setMotDePasse(rs.getString("motdepasse"));
				
				listeStagiaires.add(stagiaire);
			}

			
		}finally{
			if (rs!=null) rs.close();
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		return listeStagiaires;
		
	}

}
