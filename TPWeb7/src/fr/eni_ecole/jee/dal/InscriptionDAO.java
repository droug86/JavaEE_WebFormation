package fr.eni_ecole.jee.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import fr.eni_ecole.jee.bean.Formation;
import fr.eni_ecole.jee.bean.Stagiaire;
import fr.eni_ecole.jee.util.AccesBase;

public class InscriptionDAO 
{

	
public static ArrayList<Formation> getListe(Stagiaire s,boolean inscrit) throws SQLException
{
	Connection cnx=null;
	PreparedStatement rqt=null;
	ResultSet rs=null;
	ArrayList<Formation> listeFormations = new ArrayList<Formation>();
	try
	{
		cnx=AccesBase.getConnection();
		if (inscrit)
		{
			rqt=cnx.prepareStatement("select * from formations where id  in(select formation from inscriptions where stagiaire=?)");
		}
		else
		{
			rqt=cnx.prepareStatement("select * from formations where id not in(select formation from inscriptions where stagiaire=?)");
		}
		rqt.setInt(1,s.getId());
		rs=rqt.executeQuery();
		Formation formation;
		while (rs.next())
		{
			formation = new Formation(
										rs.getInt("id"),
										rs.getString("libelle"),
										rs.getDate("debut"),
										rs.getDate("fin"),
										rs.getString("description")
								);
			listeFormations.add(formation);				
		}
	}
	finally
	{
		if (rs!=null) rs.close();
		if (rqt!=null) rqt.close();
		if (cnx!=null) cnx.close();
	}
	return listeFormations;
	}

public static void ajouterInscription(int idStagiaire,int idFormation) throws SQLException
{
	Connection cnx=null;
	PreparedStatement rqt=null;
	try
	{
		cnx=AccesBase.getConnection();
		rqt=cnx.prepareStatement("insert into inscriptions(stagiaire,formation,dateinscription) values (?,?,?)");
		rqt.setInt(1,idStagiaire);
		rqt.setInt(2,idFormation);
		rqt.setDate(3,new java.sql.Date(new Date().getTime()));
		rqt.executeUpdate();
		
	}
	catch (Exception e)
	{
		e.printStackTrace();
	}
	finally
	{
		if (rqt!=null) rqt.close();
		if (cnx!=null) cnx.close();
	}
	
	}


public static void supprimerInscription(int idStagiaire,int idFormation) throws SQLException
{
	Connection cnx=null;
	PreparedStatement rqt=null;
	
	try
	{
		cnx=AccesBase.getConnection();
		rqt=cnx.prepareStatement("delete from  inscriptions where stagiaire=? and formation=?");
		rqt.setInt(1,idStagiaire);
		rqt.setInt(2,idFormation);
		rqt.executeUpdate();
		
	}
	catch (Exception e)
	{
		e.printStackTrace();
	}
	finally
	{
		if (rqt!=null) rqt.close();
		if (cnx!=null) cnx.close();
	}
	
	}


}


