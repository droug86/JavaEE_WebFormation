package fr.eni_ecole.jee.servlet;

import java.io.*;
import java.sql.*;
import java.text.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import fr.eni_ecole.jee.bean.*;
import fr.eni_ecole.jee.dal.*;

public class GererFormations extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher; 
			
		String ajouterParam = request.getParameter("bAjouter"); // Ajout d'une nouvelle formation
		String enregistrerParam = request.getParameter("bEnregistrer"); // Enregistrement de la formation courante
		
		// Récupérer la liste des formations en session		
		ArrayList<Formation> listeFormations = (ArrayList<Formation>) request.getSession().getAttribute("listeFormations");
		if (listeFormations== null) listeFormations=new ArrayList<Formation>();// Pour le 1er passage dans la servlet
			
		
		// Identification de l'action Modifier (et de la formation à modifier)
		String actionModifier = null;
		Formation formationAModifer=null;
		for(int i=0;i<listeFormations.size();i++) {
			actionModifier=request.getParameter("bModifier_"+i);
			if (actionModifier!=null) {
				formationAModifer = listeFormations.get(i);				
				break;
			}
		}
		
		// L'action est "Modifier" : on positione la formation à modifier comme formationCourante 
		if (formationAModifer!=null) {
			request.getSession().setAttribute("formationCourante", formationAModifer);
			dispatcher = request.getRequestDispatcher("/animateur/modifierFormation.jsp"); 
			dispatcher.forward(request, response);
			return;
		}
		
		// Si l'action est "Ajouter", création d'une nouvelle formation prérenseignée qui devient la formation courante
		// La valeur -1 comme identifiant n'est possible que pour une formation qui n'est pas encore en base   
		else if (ajouterParam!=null) {
			Formation nouvelleFormation = new Formation(-1,"",new java.util.Date(),new java.util.Date(), "");
			request.getSession().setAttribute("formationCourante",nouvelleFormation);
			dispatcher = request.getRequestDispatcher("/animateur/ajouterFormation.jsp"); 
			dispatcher.forward(request, response);
			return;
		}
		
		// L'action est l'enregistrement d'une formation (nouvelle ou déjà en base) 
		else if("Enregistrer".equals(enregistrerParam)) {
			
			Formation formationCourante = (Formation)request.getSession().getAttribute("formationCourante");
			
			if (formationCourante==null) {
				dispatcher = request.getRequestDispatcher("/animateur/gererFormations.jsp"); 
				dispatcher.forward(request, response);
				return;
			}
			
			try {
				
				// Récupération et formatage des valeurs saisies
				java.util.Date formationDebut, formationFin;
				String sDateDebut, sDateFin;
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				
				sDateDebut = request.getParameter("tDebut");
				sDateFin = request.getParameter("tFin");
				formationDebut = df.parse(sDateDebut);
				formationFin = df.parse(sDateFin);			
				
				// Mise à jour de formationCourante
				formationCourante.setLibelle(request.getParameter("tLibelle"));
				formationCourante.setDateDebut(formationDebut);
				formationCourante.setDateFin(formationFin);
				formationCourante.setDescription(request.getParameter("tDescription"));
				
				// Enregistrement d'une nouvelle formation
				if (formationCourante.getId()==-1 )
				{					
					FormationDAO.ajouter(formationCourante);
				}
				// Modification d'une formation existante
				else {
					FormationDAO.modifier(formationCourante);
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
				redirectionErreur(sqle, request, response);
				return;
			} catch (NumberFormatException nfe) {
				nfe.printStackTrace();
				redirectionErreur(nfe, request, response);
				return;
			} catch (ParseException pe) {
				pe.printStackTrace();
				redirectionErreur(pe, request, response);
				return;
			}
		}

		// Suppression d'une formation
		else {
			// Identification de la formation à supprimer
			String actionSupprimer = null;
			Formation formationASupprimer=null;
			for(int i=0;i<listeFormations.size();i++) {
				actionSupprimer=request.getParameter("bSupprimer_"+i);
				if (actionSupprimer!=null) {
					formationASupprimer = listeFormations.get(i);
					break;
				}
			}	
			
			if (formationASupprimer!=null) {
				try {
					FormationDAO.supprimer(formationASupprimer);
				} catch (SQLException sqle) {
					redirectionErreur(sqle, request, response);
					return;
				}	
			
				request.getSession().removeAttribute("formationCourante");
			}
		}
		
		// Construire la liste des formations et la placer en session
		try {
			listeFormations = FormationDAO.lister();
		}catch (SQLException sqle){
			redirectionErreur(sqle, request, response);
			return;
		}
		request.getSession().setAttribute("listeFormations", listeFormations);			
		dispatcher = request.getRequestDispatcher("/animateur/gererFormations.jsp"); 
		dispatcher.forward(request, response);
	}
	
	protected void redirectionErreur(Exception e, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Placer l'objet représentant l'exception dans le contexte de requete
		request.setAttribute("erreur", e);
		// Passer la main à la page de présentation des erreurs
		RequestDispatcher dispatcher = request.getRequestDispatcher("/erreur/erreur.jsp"); 
		dispatcher.forward(request, response);
		
	}
		
}
