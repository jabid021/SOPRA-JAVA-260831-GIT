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

import quest.dao.IDAOOrdinateur;
import quest.dao.IDAOPersonne;
import quest.model.Ordinateur;
import quest.model.Stagiaire;

@Controller
@RequestMapping("/ordinateur")
public class OrdinateurController {

	@Autowired
	IDAOOrdinateur daoOrdinateur;
	
	@Autowired
	IDAOPersonne daoPersonne;
	
	
	@GetMapping
	public String afficherTous(Model model) 
	{
		model.addAttribute("ordinateur",new Ordinateur());
		model.addAttribute("ordinateurs",daoOrdinateur.findAll());
		model.addAttribute("utilisateurs",daoPersonne.findByOrdinateurIsNull());
		
		return "ordinateurs.jsp";
	}
	
	@GetMapping("/{id}")
	public String afficherParId(@PathVariable Integer id,  Model model) 
	{
		Ordinateur ordinateur = daoOrdinateur.findById(id).orElse(null);
		model.addAttribute("ordinateur", ordinateur);
		model.addAttribute("ordinateurs",daoOrdinateur.findAll());
		
		List<Stagiaire> disponibles = daoPersonne.findByOrdinateurIsNull();
		if(ordinateur.getUtilisateur()!=null)
		{
			disponibles.add(ordinateur.getUtilisateur());
		}
		model.addAttribute("utilisateurs",disponibles);
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
		if(ordinateur.getUtilisateur().getId()==null) 
		{
			ordinateur.setUtilisateur(null);
		}
		daoOrdinateur.save(ordinateur);
		return "redirect:/ordinateur";
		
	}

	
	@PostMapping("/{id}")
	public String modifier(@PathVariable Integer id,Ordinateur ordinateur) 
	{
		ordinateur.setNumero(id);
		if(ordinateur.getUtilisateur().getId()==null) 
		{
			ordinateur.setUtilisateur(null);
		}
		daoOrdinateur.save(ordinateur);
		return "redirect:/ordinateur";
	}
	
	
	

}
