package fr.eni_ecole.listeners;

import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import fr.eni_ecole.jee.dal.ServeurDAO;

/**
 * Application Lifecycle Listener implementation class StartStopListener
 *
 */
public class StartStopListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public StartStopListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
        try 
        {
			ServeurDAO.ajouter("stop",new Date());
		}
        catch (SQLException e) 
        {
			e.printStackTrace();
		}
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  
    { 
    	   try 
           {
   			ServeurDAO.ajouter("start",new Date());
           }
           catch (SQLException e) 
           {
   			e.printStackTrace();
   		}
    }
	
}
