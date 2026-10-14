package demo.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import quest.context.Singleton;
import quest.model.Salle;


@WebServlet("/demoController")
public class DemoController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Integer id = Integer.parseInt(request.getParameter("id"));
		List<Salle> salles = Singleton.getInstance().getDaoSalle().findAll();
		Salle salle = Singleton.getInstance().getDaoSalle().findById(id);
		request.setAttribute("listeDesSalles", salles);
		request.setAttribute("salle", salle); //Permet d'attacher une donnee dans la request qu'on redirige vers la jsp
		request.getServletContext().getRequestDispatcher("/afficheSalle.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
