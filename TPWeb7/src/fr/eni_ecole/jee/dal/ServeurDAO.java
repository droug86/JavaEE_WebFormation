package fr.eni_ecole.jee.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import fr.eni_ecole.jee.bean.Formation;
import fr.eni_ecole.jee.util.AccesBase;

public class ServeurDAO 
{
	public static void ajouter(String event,Date date) throws SQLException{
		Connection cnx=null;
		PreparedStatement rqt=null;

		try{
			cnx=AccesBase.getConnection();
			rqt=cnx.prepareStatement("insert into infos_serveur(event,date) values (?,?)");
			rqt.setString(1, event);
			rqt.setTimestamp(2, new Timestamp(date.getTime()));
			System.out.println(new java.sql.Date(date.getTime()).toString());
			rqt.executeUpdate();
		}finally{
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
	}
	public static boolean ipPresente(String ip)
	{
		Connection cnx=null;
		PreparedStatement rqt=null;

		try{
			cnx=AccesBase.getConnection();
			rqt=cnx.prepareStatement("select * from ips where ip=?");
			rqt.setString(1,ip);
			return !rqt.executeQuery().next();
			}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	finally{
		try {
				if (rqt!=null)
					rqt.close();
				if (cnx!=null) 
					cnx.close();
			}
			catch (SQLException e) 
			{
				
				e.printStackTrace();
			}
		
	}
		return false;
	}
	
	public static void ajouterIP(String ip,Date date) throws SQLException{
		Connection cnx=null;
		PreparedStatement rqt=null;

		try{
			cnx=AccesBase.getConnection();
			rqt=cnx.prepareStatement("insert into ips(ip,date) values (?,?)");
			rqt.setString(1, ip);
			rqt.setTimestamp(2, new Timestamp(date.getTime()));
			System.out.println(new java.sql.Date(date.getTime()).toString());
			rqt.executeUpdate();
		}finally{
			if (rqt!=null) rqt.close();
			if (cnx!=null) cnx.close();
		}
	}
	
	
}
