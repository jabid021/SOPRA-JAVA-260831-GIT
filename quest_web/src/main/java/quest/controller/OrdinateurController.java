package quest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import quest.dao.IDAOFiliere;
import quest.dao.IDAOOrdinateur;
import quest.model.Genre;
import quest.model.Ordinateur;

@Controller
@RequestMapping("/ordinateur")
public class OrdinateurController {

	
	
	@GetMapping
	public String afficherTous(Model model) 
	{
		//Code ICI
		return "ordinateurs.jsp";
	}
	
	@GetMapping("/{id}")
	public String afficherParId(@PathVariable Integer id,  Model model) 
	{
		//Code ICI
		return "ordinateurs.jsp";
	}
	
	@GetMapping("/{id}/delete")
	public String supprimer(@PathVariable Integer id) 
	{
		daoOrdinateur.deleteById(id);
		return "redirect:/ordinateur";
	}
	
	@PostMapping
	public String ajouter(@ModelAttribute Ordinateur ordinateur){
		//Verifier si stagiaire null ?
		daoOrdinateur.save(ordinateur);
		return "redirect:/ordinateur";
		
	}

	
	@PostMapping("/{id}")
	public String modifier(@PathVariable Integer id,Ordinateur ordinateur) 
	{
		ordinateur.setNumero(id);
		//Verifier si stagiaire null ?
		daoOrdinateur.save(ordinateur);
		return "redirect:/ordinateur";
	}
	
	
	

}
