package fr.eni_ecole.jee.servlet;

import java.io.*;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import org.w3c.dom.*;

import fr.eni_ecole.jee.bean.*;
import fr.eni_ecole.jee.dal.*;


public class ListerInscriptions extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Stagiaire> listeStagiaires = null;
		try {
			
			// Construction de la liste des stagiaires avec leurs inscriptions à des formations
			listeStagiaires = StagiaireDAO.lister();			
			
			
			// Génération du flux XML
			Document flux = genererFlux(listeStagiaires);
			
			//Ecrire dans la réponse
			Source source=new DOMSource(flux);			
			Result destination=new StreamResult(response.getWriter());
			response.setContentType("text/xml");
			response.setStatus(HttpServletResponse.SC_OK);
			TransformerFactory.newInstance().newTransformer().transform(source, destination);
			
			response.getWriter().flush();
			response.getWriter().close();
			
		}catch (Exception e){
			e.printStackTrace();
			//Mettre l'exception dans le contexte de requete
			request.setAttribute("erreur", e);
			//Passer la main à la JSP d'affichage des erreurs
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/erreur/erreur.jsp");
			dispatcher.forward(request, response);
		}
		
	}

	private Document genererFlux(List<Stagiaire> listeStagiaire) throws ParserConfigurationException, SQLException {
		//Construire le document vide
		Document documentXML = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		
		//Ajouter la racine
		Element racine = documentXML.createElement("stagiaires");
		documentXML.appendChild(racine);
		
		// Insérer les stagiaires
		for (Stagiaire s : listeStagiaire) {
			Element stagiaire = documentXML.createElement("stagiaire"); racine.appendChild(stagiaire);			
			
			stagiaire.setAttribute("id", String.valueOf(s.getId()));
			
			Element nom, prenom;
			nom = documentXML.createElement("nom"); stagiaire.appendChild(nom);
			nom.appendChild(documentXML.createTextNode(s.getNom()));
			prenom = documentXML.createElement("prenom"); stagiaire.appendChild(prenom);
			prenom.appendChild(documentXML.createTextNode(s.getPrenom()));
			
			List<Formation> listeInscrit;
			listeInscrit=InscriptionDAO.getListe(s,true);
			// Si le stagiaire est inscrit à des formations...
			if (!listeInscrit.isEmpty()) {
				
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			
				Element listeFormations = documentXML.createElement("formations"); stagiaire.appendChild(listeFormations);
				
				//Pour chaque inscription, création d'une balise formation
				for(Formation f : listeInscrit)
				{
					Element formation = documentXML.createElement("formation"); listeFormations.appendChild(formation);
					
					Element libelle, dateDebut, dateFin;
					libelle = documentXML.createElement("libelle"); formation.appendChild(libelle);
					libelle.appendChild(documentXML.createTextNode(f.getLibelle()));
					dateDebut = documentXML.createElement("debut"); formation.appendChild(dateDebut);
					String debut = df.format(f.getDateDebut());
					dateDebut.appendChild(documentXML.createTextNode(debut));
					
					dateFin = documentXML.createElement("fin"); formation.appendChild(dateFin);
					String fin = df.format(f.getDateFin());
					dateFin.appendChild(documentXML.createTextNode(fin));
				}			
			}			
			
		}

		return documentXML;
	}

}
