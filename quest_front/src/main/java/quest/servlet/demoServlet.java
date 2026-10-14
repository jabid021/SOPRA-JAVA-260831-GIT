package quest.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import quest.context.Singleton;
import quest.model.Matiere;

@WebServlet("/demo")
public class demoServlet extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		/*String test = request.getParameter("test");
		System.out.println(test);
		System.out.println("On est en GET");*/
		
		List<Matiere> matieres = Singleton.getInstance().getDaoMatiere().findAll();
		
		response.getWriter().println("<html>");
		response.getWriter().println("<body>");
		response.getWriter().println("<h1>Gestion des matieres</h1>");
		response.getWriter().println("<table>");
		response.getWriter().println("<tr><th>ID</th><th>Libelle</th></tr>");
		for(Matiere m : matieres) 
		{
			response.getWriter().println("<tr><td>"+m.getId()+"</td><td>"+m.getLibelle()+"</td></tr>");
		}
		response.getWriter().println("</table>");
		response.getWriter().println("</body>");
		response.getWriter().println("</html>");
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		/*String prenom= request.getParameter("prenom");
		System.out.println(prenom);
		int age = Integer.parseInt(request.getParameter("age"));
		
		System.out.println(age);
		System.out.println("On est en POST");*/
	}
	
	
	
}
