package quest.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import quest.context.Singleton;
import quest.model.Salle;


@WebServlet("/salle")
public class SalleController extends HttpServlet {

	/*
	 * 
	 GET =>
	  	GET findAll=> pas de parametre id
	 	GET findById =>  parametre id present
	 	DELETE delete =>  parametres id + delete presents 


	 POST => 
	 	insert : pas de parametre id
	 	update : parametre id present
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("id")==null) 
		{
			afficherTous(request, response);
		} 
		else 
		{
			if(request.getParameter("delete")==null) 
			{
				afficherParId(request, response);
			}
			else 
			{
				supprimer(request, response);
			}
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("id")=="") 
		{
			ajouter(request, response);
		}
		else 
		{
			modifier(request, response);
		}
	}
	
	
	
	
	////ON CHANGE LE CODE A PARTIR D'ICI DANS LES AUTRES CONTROLLERS
	
	public void afficherTous(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		List<Salle> salles = Singleton.getInstance().getDaoSalle().findAll();
		request.setAttribute("salles", salles);
		
		request.getServletContext().getRequestDispatcher("/WEB-INF/salles.jsp").forward(request, response);
	}
	
	
	public void afficherParId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Integer id = Integer.parseInt(request.getParameter("id"));
		Salle salle = Singleton.getInstance().getDaoSalle().findById(id);
		List<Salle> salles = Singleton.getInstance().getDaoSalle().findAll();
		request.setAttribute("salles", salles);
		request.setAttribute("salle", salle);
		request.getServletContext().getRequestDispatcher("/WEB-INF/salles.jsp").forward(request, response);
	}
	
	public void supprimer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Integer id = Integer.parseInt(request.getParameter("id"));
		Singleton.getInstance().getDaoSalle().deleteById(id);
		
		response.sendRedirect("salle");
	}

	public void ajouter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String nom = request.getParameter("nom");
		String numero = request.getParameter("adresse.numero");
		String voie = request.getParameter("adresse.voie");
		String ville = request.getParameter("adresse.ville");
		String cp = request.getParameter("adresse.cp");
		
		Salle salle = new Salle(null,nom,numero,voie,ville,cp);
		Singleton.getInstance().getDaoSalle().save(salle);
		
		response.sendRedirect("salle");
		
	}
		
	public void modifier(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Integer id = Integer.parseInt(request.getParameter("id"));
		String nom = request.getParameter("nom");
		String numero = request.getParameter("adresse.numero");
		String voie = request.getParameter("adresse.voie");
		String ville = request.getParameter("adresse.ville");
		String cp = request.getParameter("adresse.cp");
		int version = Integer.parseInt(request.getParameter("version"));
		
		Salle salle = new Salle(id,nom,numero,voie,ville,cp);
		salle.setVersion(version);
		
		Singleton.getInstance().getDaoSalle().save(salle);
		
		response.sendRedirect("salle");
	}
	
	
	

}
