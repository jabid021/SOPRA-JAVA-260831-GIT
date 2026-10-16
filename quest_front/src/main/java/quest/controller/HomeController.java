package quest.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import quest.context.Singleton;
import quest.model.Formateur;
import quest.model.Personne;
import quest.model.Stagiaire;


@WebServlet("/home")
public class HomeController extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("logout")!=null) 
		{
			request.getSession().invalidate(); //kill la session de mon user
		}
		
		if(request.getSession().getAttribute("connected")==null) 
		{
			this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		}
		else 
		{
			Personne connected = (Personne) request.getSession().getAttribute("connected");
			if(connected instanceof Stagiaire) 
			{
				this.getServletContext().getRequestDispatcher("/menuStagiaire.jsp").forward(request, response);
			}
			else if(connected instanceof Formateur) 
			{
				if(((Formateur) connected).isAdmin()) 
				{
					this.getServletContext().getRequestDispatcher("/menuAdmin.jsp").forward(request, response);
				}
				else 
				{
					this.getServletContext().getRequestDispatcher("/menuFormateur.jsp").forward(request, response);
				}
			}
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		
		Personne connected = Singleton.getInstance().getDaoPersonne().findByLoginAndPassword(login, password);
		
	
		if(connected==null) 
		{
			response.sendRedirect("home?error");
		}
		else 
		{
			request.getSession().setAttribute("connected", connected);
			List<String> roles = new ArrayList();
			if(connected instanceof Stagiaire) 
			{
				roles.add("ROLE_STAGIAIRE");
			}
			else if(connected instanceof Formateur) 
			{
				roles.add("ROLE_FORMATEUR");
				if(((Formateur) connected).isAdmin()) 
				{
					roles.add("ROLE_ADMIN");
					roles.add("ROLE_STAGIAIRE");
				}
			}
			
			request.getSession().setAttribute("roles", roles);
			
			response.sendRedirect("home");
		}
	}

}
