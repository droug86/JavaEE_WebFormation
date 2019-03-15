package fr.eni_ecole.jee.servlet;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.*;
import javax.servlet.http.*;

import fr.eni_ecole.jee.bean.*;
import fr.eni_ecole.jee.dal.*;

 public class ListerFormations extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		processRequest(request, response);		
	}  	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}   	  	    
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		ArrayList<Formation> listeFormations = null;
		
		// Construire la liste des formations et la placer en session
		try {
			listeFormations = FormationDAO.lister();
		}catch (SQLException sqle){
			// Placer l'objet représentant l'exception dans le contexte de requete
			request.setAttribute("erreur", sqle);
			// Passer la main à la page de présentation des erreurs
			dispatcher = request.getRequestDispatcher("/erreur/erreur.jsp"); 
			dispatcher.forward(request, response);
			return;
		}
		request.getSession().setAttribute("listeFormations", listeFormations);			
		dispatcher = request.getRequestDispatcher("/formation/listeFormations.jsp"); 
		dispatcher.forward(request, response);
		
	}
}