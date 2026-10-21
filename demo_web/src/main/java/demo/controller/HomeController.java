package demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController  {

	
	@RequestMapping({"/home","/"}) //par defaut requestMapping fait TOUS les modes (GET / POST / PUT / DELETE)
	public String demo() 
	{
		return "/index.jsp"; 
		//par defaut, le return d'une methode fait office de forward vers une jsp
		//on a deux options dans le return 
		//"forward:/x.jsp" (pour envoyer vers une jsp)
		//"redirect:home" (pour envoyer vers l'url d'une methode d'un controller
	}
	
	@RequestMapping(value = "home/test", method = RequestMethod.GET) //Uniquement en GET
	public String demo2(@RequestParam(required = true) String nom,@RequestParam(defaultValue = "0")  Integer age)  
	//par defaut, les params sont optionnels (mettre @RequestParam(required=true)) pour rendre obligatoire
	{
		System.out.println(nom);
		System.out.println(age);
		return "/WEB-INF/uneAutrePage.jsp";
	}
	
	@RequestMapping(value="home/test",method = RequestMethod.POST)
	public String demoForm(String value1,Integer value2,Model model,HttpSession session) //L'objet Model permet d'attacher des données vers une page JSP
	{
		System.out.println(value1+" - "+value2);
		model.addAttribute("donne1",value1);
		model.addAttribute("donne2",value2);
		session.setAttribute("connected", "oui");
		return "/WEB-INF/resultat.jsp";
	}
	
	/*public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String login = request.getParameter("login");
		request.setAttribute("uneVar", "saValue");
		request.getServletContext().getRequestDispatcher("/WEB-INF/formateurs.jsp").forward(request, response);
	}*/
}
