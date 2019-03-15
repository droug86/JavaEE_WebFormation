package fr.eni_ecole.jee.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni_ecole.jee.bean.Stagiaire;
import fr.eni_ecole.jee.bean.Utilisateur;
import fr.eni_ecole.jee.dal.StagiaireDAO;

/**
 * Servlet implementation class ModifPassword
 */
public class ModifPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		processRequest(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		processRequest(request,response);
	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException 
	{
		String oldPassword;
		String newPassword;
		String confirmPassword;
		String message=null;
		Utilisateur stagiaireConnecte = (Utilisateur)request.getSession().getAttribute("stagiaireConnecte");
		newPassword=request.getParameter("newmotdepasse");
		oldPassword=request.getParameter("oldmotdepasse");
		confirmPassword=request.getParameter("confirmmotdepasse");
		if(!oldPassword.equals(stagiaireConnecte.getMotDePasse())| !(newPassword.equals(confirmPassword)))
		{
			message= "erreur sur les mots de passe";
			try 
			{
				getServletContext().getRequestDispatcher("/stagiaire/profil").forward(request, response);
			}
			catch (ServletException | IOException e) 
			{
				redirectionErreur(e, request, response);
			}
		}		
		else
		{
			try
			{
				StagiaireDAO.modifierPassword((Stagiaire) stagiaireConnecte, newPassword);
				stagiaireConnecte.setMotDePasse(newPassword);
				response.sendRedirect(request.getContextPath()+"/stagiaire/menu.jsp");
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
				redirectionErreur(e, request, response);
			}
		}
	}
	protected void redirectionErreur(Exception e, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Placer l'objet représentant l'exception dans le contexte de requete
		request.setAttribute("erreur", e);
		// Passer la main à la page de présentation des erreurs
		RequestDispatcher dispatcher = request.getRequestDispatcher("/erreur/erreur.jsp"); 
		dispatcher.forward(request, response);
		
	}

}
