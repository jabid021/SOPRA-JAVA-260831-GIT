package quest.controller;

import java.io.IOException;

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
			response.sendRedirect("home");
		}
	}

}
