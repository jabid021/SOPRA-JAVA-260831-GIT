package quest.controller;

import java.io.IOException;
import java.time.LocalDate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import quest.context.Singleton;
import quest.model.Filiere;
import quest.model.Salle;


@WebServlet("/filiere")
public class FiliereController extends HttpServlet {

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





	public void afficherTous(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setAttribute("filieres", Singleton.getInstance().getDaoFiliere().findAll());
		request.setAttribute("salles", Singleton.getInstance().getDaoSalle().findAll());
		this.getServletContext().getRequestDispatcher("/WEB-INF/filieres.jsp").forward(request, response);
	}


	public void afficherParId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Integer id = Integer.parseInt(request.getParameter("id"));
		Filiere filiere = Singleton.getInstance().getDaoFiliere().findById(id);
		request.setAttribute("filiere", filiere);
		request.setAttribute("filieres", Singleton.getInstance().getDaoFiliere().findAll());
		request.setAttribute("salles", Singleton.getInstance().getDaoSalle().findAll());

		this.getServletContext().getRequestDispatcher("/WEB-INF/filieres.jsp").forward(request, response);
	}

	public void supprimer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Integer id = Integer.parseInt(request.getParameter("id"));
		Singleton.getInstance().getDaoFiliere().deleteById(id);
		
		response.sendRedirect("filiere");
	}

	public void ajouter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String libelle = request.getParameter("libelle");
		LocalDate debut = null;
		if(request.getParameter("debut")!="") 
		{
			debut = LocalDate.parse(request.getParameter("debut"));
		}	
		LocalDate fin = null;
		if(request.getParameter("fin")!="") 
		{
			fin = LocalDate.parse(request.getParameter("fin"));
		}
		Salle salle = null;
		if(request.getParameter("salle.id")!="") 
		{
			Integer idSalle = Integer.parseInt(request.getParameter("salle.id"));
			salle = Singleton.getInstance().getDaoSalle().findById(idSalle);
		}
		
		Filiere filiere = new Filiere(null,libelle,debut,fin,salle);
		Singleton.getInstance().getDaoFiliere().save(filiere);
		response.sendRedirect("filiere");
	}

	public void modifier(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Integer id = Integer.parseInt(request.getParameter("id"));
		String libelle = request.getParameter("libelle");
		LocalDate debut = null;
		if(request.getParameter("debut")!="") 
		{
			debut = LocalDate.parse(request.getParameter("debut"));
		}	
		LocalDate fin = null;
		if(request.getParameter("fin")!="") 
		{
			fin = LocalDate.parse(request.getParameter("fin"));
		}
		Salle salle = null;
		if(request.getParameter("salle.id")!="") 
		{
			Integer idSalle = Integer.parseInt(request.getParameter("salle.id"));
			salle = Singleton.getInstance().getDaoSalle().findById(idSalle);
		}
		
		Filiere filiere = new Filiere(id,libelle,debut,fin,salle);
		Singleton.getInstance().getDaoFiliere().save(filiere);
		response.sendRedirect("filiere");
	}

}
