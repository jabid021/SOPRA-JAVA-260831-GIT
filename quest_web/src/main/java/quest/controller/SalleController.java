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
import quest.model.Genre;
import quest.model.Salle;
import quest.service.SalleService;

@Controller
@RequestMapping("/salle")
public class SalleController {

	@Autowired
	SalleService salleSrv;

	
	@GetMapping
	public String afficherTous(Model model) 
	{
		List<Salle> salles = salleSrv.getAll();
		model.addAttribute("salles", salles);
		model.addAttribute("salle", new Salle());
		return "salles.jsp";
	}
	
	@GetMapping("/{id}")
	public String afficherParId(@PathVariable Integer id,  Model model) 
	{
		Salle salle = salleSrv.getById(id);
		List<Salle> salles = salleSrv.getAll();
		model.addAttribute("salles", salles);
		model.addAttribute("salle", salle);
		return "salles.jsp";
	}
	
	@GetMapping("/{id}/delete")
	public String supprimer(@PathVariable Integer id) 
	{
		salleSrv.deleteById(id);
		
		return "redirect:/salle";
	}
	
	@PostMapping
	public String ajouter(@ModelAttribute Salle salle){
		salleSrv.insert(salle);
		return "redirect:/salle";
		
	}
	
	@PostMapping("/{id}")
	public String modifier(@PathVariable Integer id,Salle salle) 
	{
		salle.setId(id);
		salleSrv.update(salle);
		return "redirect:/salle";
	}
	
	
	

}
