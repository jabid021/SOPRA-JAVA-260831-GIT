package quest.controller;

import java.io.IOException;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import quest.dao.IDAOFiliere;
import quest.dao.IDAOMatiere;
import quest.dao.IDAOModule;
import quest.dao.IDAOPersonne;
import quest.model.Filiere;
import quest.model.Formateur;
import quest.model.Matiere;
import quest.model.Module;


@WebServlet("/module")
public class ModuleController extends HttpServlet {

	@Autowired
	IDAOPersonne daoPersonne;
	
	@Autowired
	IDAOModule daoModule;
	
	@Autowired
	IDAOFiliere daoFiliere;
	
	@Autowired
	IDAOMatiere daoMatiere;
	
	public void init(ServletConfig config) throws ServletException 
	{
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}
	
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
		request.setAttribute("modules", daoModule.findAllByFiliereId(idFiliere));
		request.setAttribute("matieres", daoMatiere.findAll());
		request.setAttribute("formateurs", daoPersonne.findAllFormateurs());
		this.getServletContext().getRequestDispatcher("/WEB-INF/modules.jsp").forward(request, response);
	}


	public void afficherParId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Integer id = Integer.parseInt(request.getParameter("id"));
		Integer idFiliere = Integer.parseInt(request.getParameter("filiere")); 

		Module module = daoModule.findById(id).orElse(null);
		request.setAttribute("module", module);
		request.setAttribute("modules", daoModule.findAllByFiliereId(idFiliere));
		request.setAttribute("matieres", daoMatiere.findAll());
		request.setAttribute("formateurs", daoPersonne.findAllFormateurs());
		this.getServletContext().getRequestDispatcher("/WEB-INF/modules.jsp").forward(request, response);
	}

	public void supprimer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Integer id = Integer.parseInt(request.getParameter("id"));
		Integer idFiliere = Integer.parseInt(request.getParameter("filiere")); 

		daoModule.deleteById(id);
		
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
		Filiere filiere = daoFiliere.findById(idFiliere).orElse(null);
		Integer idMatiere = Integer.parseInt(request.getParameter("matiere.id")); 
		Matiere matiere = daoMatiere.findById(idMatiere).orElse(null);
		Formateur formateur = null;
		if(request.getParameter("formateur.id")!="") 
		{
			Integer idFormateur = Integer.parseInt(request.getParameter("formateur.id"));
			formateur = (Formateur) daoPersonne.findById(idFormateur).orElse(null);
		}
		
		Module module = new Module(null,quest,debut,fin,matiere,filiere,formateur);
		daoModule.save(module);
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
		Filiere filiere = daoFiliere.findById(idFiliere).orElse(null);
		Integer idMatiere = Integer.parseInt(request.getParameter("matiere.id")); 
		Matiere matiere = daoMatiere.findById(idMatiere).orElse(null);
		Formateur formateur = null;
		if(request.getParameter("formateur.id")!="") 
		{
			Integer idFormateur = Integer.parseInt(request.getParameter("formateur.id"));
			formateur = (Formateur) daoPersonne.findById(idFormateur).orElse(null);
		}
		
		Module module = new Module(id,quest,debut,fin,matiere,filiere,formateur);
		daoModule.save(module);
		response.sendRedirect("module?filiere="+idFiliere);
	}

}
