package quest.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import quest.dao.IDAOFiliere;
import quest.dao.IDAOPersonne;
import quest.model.Filiere;
import quest.model.Genre;
import quest.model.Stagiaire;


@WebServlet("/stagiaire")
public class StagiaireController extends HttpServlet {

	@Autowired
	IDAOPersonne daoPersonne;
	
	@Autowired
	IDAOFiliere daoFiliere;
	
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
		List<Stagiaire> stagiaires = daoPersonne.findAllStagiaires();
		request.setAttribute("stagiaires", stagiaires);
		request.setAttribute("genres", Genre.values());
		request.setAttribute("filieres",daoFiliere.findAll());
		request.getServletContext().getRequestDispatcher("/WEB-INF/stagiaires.jsp").forward(request, response);
	}
	
	
	public void afficherParId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Integer id = Integer.parseInt(request.getParameter("id"));
		Stagiaire stagiaire = (Stagiaire) daoPersonne.findById(id).orElse(null);
		List<Stagiaire> stagiaires = daoPersonne.findAllStagiaires();
		request.setAttribute("stagiaires", stagiaires);
		request.setAttribute("genres", Genre.values());
		request.setAttribute("stagiaire", stagiaire);
		request.setAttribute("filieres",daoFiliere.findAll());
		request.getServletContext().getRequestDispatcher("/WEB-INF/stagiaires.jsp").forward(request, response);
	}
	
	public void supprimer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Integer id = Integer.parseInt(request.getParameter("id"));
		daoPersonne.deleteById(id);
		
		response.sendRedirect("stagiaire");
	}

	public void ajouter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		Genre genre = Genre.valueOf(request.getParameter("civilite"));
		String email = request.getParameter("email");
		String numero = request.getParameter("adresse.numero");
		String voie = request.getParameter("adresse.voie");
		String ville = request.getParameter("adresse.ville");
		String cp = request.getParameter("adresse.cp");
		Integer idFiliere = Integer.parseInt(request.getParameter("filiere.id"));
		Filiere filiere = daoFiliere.findById(idFiliere).orElse(null);
		
		Stagiaire stagiaire = new Stagiaire(null,login,password,nom,prenom,genre,email,numero,voie,ville,cp,filiere);
		daoPersonne.save(stagiaire);
		
		response.sendRedirect("stagiaire");
		
	}
		
	public void modifier(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Integer id = Integer.parseInt(request.getParameter("id"));
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		Genre genre = Genre.valueOf(request.getParameter("civilite"));
		String email = request.getParameter("email");
		String numero = request.getParameter("adresse.numero");
		String voie = request.getParameter("adresse.voie");
		String ville = request.getParameter("adresse.ville");
		String cp = request.getParameter("adresse.cp");
		Integer idFiliere = Integer.parseInt(request.getParameter("filiere.id"));
		Filiere filiere = daoFiliere.findById(idFiliere).orElse(null);
		
		Stagiaire stagiaire = new Stagiaire(id,login,password,nom,prenom,genre,email,numero,voie,ville,cp,filiere);
		daoPersonne.save(stagiaire);
		
		response.sendRedirect("stagiaire");
	}
	
	
	

}
