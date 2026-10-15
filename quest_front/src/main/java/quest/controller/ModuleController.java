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
import quest.model.Formateur;
import quest.model.Matiere;
import quest.model.Module;


@WebServlet("/module")
public class ModuleController extends HttpServlet {

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
		Integer idFiliere = Integer.parseInt(request.getParameter("filiere")); 
		request.setAttribute("modules", Singleton.getInstance().getDaoModule().findAllByFiliereId(idFiliere));
		request.setAttribute("matieres", Singleton.getInstance().getDaoMatiere().findAll());
		request.setAttribute("formateurs", Singleton.getInstance().getDaoPersonne().findAllFormateurs());
		this.getServletContext().getRequestDispatcher("/modules.jsp").forward(request, response);
	}


	public void afficherParId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Integer id = Integer.parseInt(request.getParameter("id"));
		Integer idFiliere = Integer.parseInt(request.getParameter("filiere")); 

		Module module = Singleton.getInstance().getDaoModule().findById(id);
		request.setAttribute("module", module);
		request.setAttribute("modules", Singleton.getInstance().getDaoModule().findAllByFiliereId(idFiliere));
		request.setAttribute("matieres", Singleton.getInstance().getDaoMatiere().findAll());
		request.setAttribute("formateurs", Singleton.getInstance().getDaoPersonne().findAllFormateurs());
		this.getServletContext().getRequestDispatcher("/modules.jsp").forward(request, response);
	}

	public void supprimer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Integer id = Integer.parseInt(request.getParameter("id"));
		Integer idFiliere = Integer.parseInt(request.getParameter("filiere")); 

		Singleton.getInstance().getDaoModule().deleteById(id);
		
		response.sendRedirect("module?filiere="+idFiliere);
	}

	public void ajouter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		
		Integer quest = Integer.parseInt(request.getParameter("quest")); 
		LocalDate debut = null;
		if(request.getParameter("debut")!="") 
		{
			debut = LocalDate.parse(request.getParameter("debut"));
		}
		
		LocalDate fin = LocalDate.parse(request.getParameter("fin"));
		
		Integer idFiliere = Integer.parseInt(request.getParameter("filiere.id")); 
		Filiere filiere = Singleton.getInstance().getDaoFiliere().findById(idFiliere);
		Integer idMatiere = Integer.parseInt(request.getParameter("matiere.id")); 
		Matiere matiere = Singleton.getInstance().getDaoMatiere().findById(idMatiere);
		Formateur formateur = null;
		if(request.getParameter("formateur.id")!="") 
		{
			Integer idFormateur = Integer.parseInt(request.getParameter("formateur.id"));
			formateur = (Formateur) Singleton.getInstance().getDaoPersonne().findById(idFormateur);
		}
		
		Module module = new Module(null,quest,debut,fin,matiere,filiere,formateur);
		Singleton.getInstance().getDaoModule().save(module);
		response.sendRedirect("module?filiere="+idFiliere);
	}

	public void modifier(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Integer id = Integer.parseInt(request.getParameter("id"));
		Integer quest = Integer.parseInt(request.getParameter("quest")); 
		/*LocalDate debut = null;
		if(request.getParameter("debut")!="") 
		{
			debut = LocalDate.parse(request.getParameter("debut"));
		}*/
		
		LocalDate debut = LocalDate.parse(request.getParameter("debut"));
		
		LocalDate fin = LocalDate.parse(request.getParameter("fin"));
		
		Integer idFiliere = Integer.parseInt(request.getParameter("filiere.id")); 
		Filiere filiere = Singleton.getInstance().getDaoFiliere().findById(idFiliere);
		Integer idMatiere = Integer.parseInt(request.getParameter("matiere.id")); 
		Matiere matiere = Singleton.getInstance().getDaoMatiere().findById(idMatiere);
		Formateur formateur = null;
		if(request.getParameter("formateur.id")!="") 
		{
			Integer idFormateur = Integer.parseInt(request.getParameter("formateur.id"));
			formateur = (Formateur) Singleton.getInstance().getDaoPersonne().findById(idFormateur);
		}
		
		Module module = new Module(id,quest,debut,fin,matiere,filiere,formateur);
		Singleton.getInstance().getDaoModule().save(module);
		response.sendRedirect("module?filiere="+idFiliere);
	}

}
