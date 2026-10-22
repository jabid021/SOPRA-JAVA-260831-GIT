package quest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import quest.dao.IDAOPersonne;
import quest.model.Formateur;
import quest.model.Personne;
import quest.model.Stagiaire;

@Controller

public class HomeController {
	
	@Autowired
	IDAOPersonne daoPersonne;
	
	@GetMapping({"/home","/"})
	public String home(HttpSession session) 
	{
		if(session.getAttribute("connected")==null) 
		{
			return "forward:/index.jsp";
		}
		else 
		{
			Personne connected = (Personne) session.getAttribute("connected");
			if(connected instanceof Stagiaire) 
			{
				return "menuStagiaire.jsp";
			}
			else
			{
				if(((Formateur) connected).isAdmin()) 
				{
					return "menuAdmin.jsp";
				}
				else 
				{
					return "menuFormateur.jsp";
				}
			}
		}
	}
	
	@GetMapping("/logout")
	public String disconnect(HttpSession session) 
	{
		session.invalidate(); //kill la session de mon user
		return "redirect:/home";
	}
	
	@PostMapping("/home")
	public String connect(@RequestParam String login, @RequestParam String password,HttpSession session) 
	{
		Personne connected = daoPersonne.findByLoginAndPassword(login, password);
		if(connected==null) 
		{
			return "redirect:/home?error";
		}
		else 
		{
			session.setAttribute("connected", connected);
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
			
			session.setAttribute("roles", roles);
		}
		return "redirect:/home";
	}
}
