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
import quest.dao.IDAOMatiere;
import quest.model.Matiere;


@WebServlet("/matiere")
public class MatiereController extends HttpServlet {

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
			if(request.getParameter("filter")==null) 
			{
				afficherTous(request, response);
			}
			else 
			{
				filtrerAjax(request, response);
			}

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




	public void filtrerAjax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String recherche = request.getParameter("texte");
		List<Matiere> matieres = daoMatiere.findByLibelleContaining(recherche);
		
		if(matieres.isEmpty()) 
		{
			response.getWriter().println("<tr><td align='center' colspan='3'>Aucune matiere</td></tr>");
		}
		
			
		for(Matiere m : matieres) {
			response.getWriter().println("<tr>");
			response.getWriter().println("<td>"+m.getId()+"</td>");
			response.getWriter().println("<td>"+m.getLibelle()+"</td>");
			response.getWriter().println("<td>");
			response.getWriter().println("<a class='btn btn-warning' href='matiere?id="+m.getId()+"'>Modifier</a>");
			response.getWriter().println("<a class='btn btn-danger' href='matiere?id="+m.getId()+"&delete'>Supprimer</a>");
			response.getWriter().println("</td>");
			response.getWriter().println("</tr>");		
		}
	}


	public void afficherTous(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setAttribute("matieres", daoMatiere.findAll());
		this.getServletContext().getRequestDispatcher("/WEB-INF/matieres.jsp").forward(request, response);
	}


	public void afficherParId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Integer id = Integer.parseInt(request.getParameter("id"));
		Matiere matiere = daoMatiere.findById(id).orElse(null);
		request.setAttribute("matiere", matiere);
		request.setAttribute("matieres", daoMatiere.findAll());
		this.getServletContext().getRequestDispatcher("/WEB-INF/matieres.jsp").forward(request, response);
	}

	public void supprimer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Integer id = Integer.parseInt(request.getParameter("id"));
		daoMatiere.deleteById(id);

		response.sendRedirect("matiere");
	}

	public void ajouter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String libelle = request.getParameter("libelle");
		Matiere matiere = new Matiere(null,libelle);
		daoMatiere.save(matiere);
		response.sendRedirect("matiere");
	}

	public void modifier(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Integer id = Integer.parseInt(request.getParameter("id"));
		String libelle = request.getParameter("libelle");
		Matiere matiere = new Matiere(id,libelle);
		daoMatiere.save(matiere);
		response.sendRedirect("matiere");
	}

}
