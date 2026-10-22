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

import quest.dao.IDAOPersonne;
import quest.model.Formateur;
import quest.model.Genre;

@Controller
@RequestMapping("/formateur")
public class FormateurController {

	@Autowired
	IDAOPersonne daoPersonne;
	
	@GetMapping
	public String afficherTous(Model model) 
	{
		List<Formateur> formateurs = daoPersonne.findAllFormateurs();
		model.addAttribute("formateurs", formateurs);
		model.addAttribute("genres", Genre.values());
		model.addAttribute("formateur", new Formateur());
		return "formateurs.jsp";
	}
	
	@GetMapping("/{id}")
	public String afficherParId(@PathVariable Integer id,  Model model) 
	{
		Formateur formateur = (Formateur) daoPersonne.findById(id).orElse(null);
		List<Formateur> formateurs = daoPersonne.findAllFormateurs();
		model.addAttribute("formateurs", formateurs);
		model.addAttribute("genres", Genre.values());
		model.addAttribute("formateur", formateur);
		System.out.println(formateur);
		return "formateurs.jsp";
	}
	
	@GetMapping("/{id}/delete")
	public String supprimer(@PathVariable Integer id) 
	{
		daoPersonne.deleteById(id);
		
		return "redirect:/formateur";
	}
	
	@PostMapping
	public String ajouter(@ModelAttribute Formateur formateur){
		daoPersonne.save(formateur);
		return "redirect:/formateur";
		
	}
	
	@PostMapping("/{id}")
	public String modifier(@PathVariable Integer id,Formateur formateur) 
	{
		formateur.setId(id);
		daoPersonne.save(formateur);
		return "redirect:/formateur";
	}
	
	
	

}
