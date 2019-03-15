package fr.eni_ecole.jee.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni_ecole.jee.bean.Formation;
import fr.eni_ecole.jee.bean.Stagiaire;
import fr.eni_ecole.jee.dal.InscriptionDAO;

/**
 * Servlet implementation class GererInscriptions
 */
public class GererInscriptions extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		Stagiaire s;
		s=(Stagiaire)request.getSession().getAttribute("stagiaireConnecte");
		
		String action;
		action=request.getParameter("action");
		String[] ins;
		String[] non;
		non=request.getParameterValues("nonInscrit");
		ins=request.getParameterValues("inscrit");
		if(action!=null && action.equals("ajouter"))
		{
			if(non!=null)
			{
				
				for(String f:non)
				{
					try 
					{
						InscriptionDAO.ajouterInscription(s.getId(),Integer.parseInt(f));
					}
					catch (NumberFormatException | SQLException e) 
					{
						e.printStackTrace();
					}
				}
			}
			response.sendRedirect(request.getContextPath()+"/stagiaire/formations");
			return;
		}
		if(action!=null && action.equals("supprimer"))
		{
			if(ins!=null)
			{
				
				for(String f:ins)
				{
					try 
					{
						InscriptionDAO.supprimerInscription(s.getId(),Integer.parseInt(f));
					}
					catch (NumberFormatException | SQLException e) 
					{
						e.printStackTrace();
					}
				}
			}
			response.sendRedirect(request.getContextPath()+"/stagiaire/formations");
			return;
		}
		
		ArrayList<Formation> listeInscrit;
		ArrayList<Formation> listeNonInscrit;
		
		try 
		{
			listeInscrit=InscriptionDAO.getListe(s, true);
			listeNonInscrit=InscriptionDAO.getListe(s, false);
			RequestDispatcher rd;
			request.setAttribute("nonInscrit",listeNonInscrit);
			request.setAttribute("inscrit",listeInscrit);
			
			rd=getServletContext().getRequestDispatcher("/stagiaire/gererFormations.jsp");
			rd.forward(request,response);
			
		}
		catch (SQLException | ServletException e) 
		{
			e.printStackTrace();
		} 		
		
 	}

}
