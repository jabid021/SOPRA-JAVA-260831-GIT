package quest.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import quest.context.Singleton;
import quest.model.Ordinateur;
import quest.model.Stagiaire;


@WebServlet("/ordinateur")
public class OrdinateurController extends HttpServlet {

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
		if(request.getParameter("numero")=="") 
		{
			ajouter(request, response);
		}
		else 
		{
			modifier(request, response);
		}
	}





	public void afficherTous(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setAttribute("ordinateurs", Singleton.getInstance().getDaoOrdinateur().findAll());
		request.setAttribute("utilisateurs", Singleton.getInstance().getDaoPersonne().findByOrdinateurIsNull());
		this.getServletContext().getRequestDispatcher("/WEB-INF/ordinateurs.jsp").forward(request, response);
	}


	public void afficherParId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Integer id = Integer.parseInt(request.getParameter("id"));
		
		Ordinateur ordinateur = Singleton.getInstance().getDaoOrdinateur().findById(id);
		List<Stagiaire> disponibles = Singleton.getInstance().getDaoPersonne().findByOrdinateurIsNull();
		
		if(ordinateur.getUtilisateur()!=null) {
			disponibles.add(ordinateur.getUtilisateur());
		}
		
		request.setAttribute("ordinateur", ordinateur);
		request.setAttribute("ordinateurs", Singleton.getInstance().getDaoOrdinateur().findAll());
		request.setAttribute("utilisateurs", disponibles);
		this.getServletContext().getRequestDispatcher("/WEB-INF/ordinateurs.jsp").forward(request, response);
	}

	public void supprimer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Integer id = Integer.parseInt(request.getParameter("id"));
		

		Singleton.getInstance().getDaoOrdinateur().deleteById(id);
		
		response.sendRedirect("ordinateur");
	}

	public void ajouter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		String marque = request.getParameter("marque");
		Integer ram = Integer.parseInt(request.getParameter("ram")); 
		Stagiaire utilisateur = null;
		if(request.getParameter("utilisateur.id")!="") 
		{
			Integer idUtilisateur = Integer.parseInt(request.getParameter("utilisateur.id"));
			utilisateur = (Stagiaire) Singleton.getInstance().getDaoPersonne().findById(idUtilisateur);
		}
		
		Ordinateur ordinateur = new Ordinateur(null,marque,ram,utilisateur);
		Singleton.getInstance().getDaoOrdinateur().save(ordinateur);
		response.sendRedirect("ordinateur");
	}

	public void modifier(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Integer id = Integer.parseInt(request.getParameter("numero"));
		String marque = request.getParameter("marque");
		Integer ram = Integer.parseInt(request.getParameter("ram")); 
		Stagiaire utilisateur = null;
		if(request.getParameter("utilisateur.id")!="") 
		{
			Integer idUtilisateur = Integer.parseInt(request.getParameter("utilisateur.id"));
			utilisateur = (Stagiaire) Singleton.getInstance().getDaoPersonne().findById(idUtilisateur);
		}
		
		Ordinateur ordinateur = new Ordinateur(id,marque,ram,utilisateur);
		Singleton.getInstance().getDaoOrdinateur().save(ordinateur);
		response.sendRedirect("ordinateur");
	}

}
