package fr.eni_ecole.jee.dal;

import java.sql.*;
import java.util.*;

import fr.eni_ecole.jee.bean.*;
import fr.eni_ecole.jee.util.*;

public class FormationDAO {

	public static void ajouter(Formation formation) throws SQLException{
		Connection cnx=null;
		PreparedStatement rqt=null;

		try{
			cnx=AccesBase.getConnection();
			rqt=cnx.prepareStatement("insert into formations(libelle, debut, fin, description) values (?,?,?,?)");
			rqt.setString(1, formation.getLibelle());
			rqt.setDate(2, new java.sql.Date(formation.getDateDebut().getTime()));
			rqt.setDate(3, new java.sql.Date(formation.getDateFin().getTime()));
			rqt.setString(4, formation.getDescription());
			rqt.executeUpdate();
		}finally{
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
	}
	
	public static void supprimer(Formation formation) throws SQLException{
		Connection cnx=null;
		PreparedStatement rqt=null;
		try{
			cnx=AccesBase.getConnection();
			rqt=cnx.prepareStatement("delete from formations where id = ?");
			rqt.setInt(1, formation.getId());
			rqt.executeUpdate();
		}finally{
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
	}
	
	public static void modifier(Formation formation) throws SQLException{
		Connection cnx=null;
		PreparedStatement rqt=null;
		try{
			cnx=AccesBase.getConnection();
			rqt=cnx.prepareStatement("update formations set libelle = ?, debut = ?, fin = ?, description = ? where id = ?");
			rqt.setString(1, formation.getLibelle());
			rqt.setDate(2, new java.sql.Date(formation.getDateDebut().getTime()));
			rqt.setDate(3, new java.sql.Date(formation.getDateFin().getTime()));
			rqt.setString(4,formation.getDescription());
			rqt.setInt(5, formation.getId());

			rqt.executeUpdate();
		}finally{
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
	}
	
	public static Formation rechercher(Formation formation) throws SQLException{
		Connection cnx=null;
		PreparedStatement rqt=null;
		ResultSet rs=null;
		try{
			cnx=AccesBase.getConnection();
			rqt=cnx.prepareStatement("select * from formations where id = ?");
			rqt.setInt(1, formation.getId());
			rs=rqt.executeQuery();
			while (rs.next()){
				formation.setLibelle(rs.getString("libelle"));				
				formation.setDateDebut(rs.getDate("debut"));
				formation.setDateFin(rs.getDate("fin"));
				formation.setDescription(rs.getString("description"));
			}
		}finally{
			if (rs!=null) rs.close();
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		
		return formation;
	}
	
	/**
	 * @return La liste peut être vide mais jamais <code>null</code>
	 * @throws SQLException
	 */
	public static ArrayList<Formation> lister() throws SQLException{
		Connection cnx=null;
		Statement rqt=null;
		ResultSet rs=null;
		ArrayList<Formation> listeFormations = new ArrayList<Formation>();
		try{
			cnx=AccesBase.getConnection();
			rqt=cnx.createStatement();			
			rs=rqt.executeQuery("select * from formations");
			Formation formation;
			while (rs.next()){
				formation = new Formation(
									rs.getInt("id"),
									rs.getString("libelle"),
									rs.getDate("debut"),
									rs.getDate("fin"),
									rs.getString("description")
						);
				listeFormations.add(formation);				
			}
		}finally{
			if (rs!=null) rs.close();
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
		
		return listeFormations;
	}
	
}
