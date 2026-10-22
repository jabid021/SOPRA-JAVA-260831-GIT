package quest.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import quest.dao.IDAOFiliere;
import quest.model.Filiere;
import quest.model.Salle;
import quest.service.SalleService;


@Controller
@RequestMapping("/filiere")
public class FiliereController {

	@Autowired
	IDAOFiliere daoFiliere;
	
	@Autowired
	SalleService salleSrv;
	
	
	@GetMapping
	public String afficherTous(Model model) 
	{
		model.addAttribute("filieres", daoFiliere.findAll());
		model.addAttribute("salles", salleSrv.getAll());
		return "filieres.jsp";
	}

	@GetMapping("/{id}")
	public String afficherParId(@PathVariable Integer id, Model model) 
	{
		Filiere filiere = daoFiliere.findById(id).orElse(null);
		model.addAttribute("filiere", filiere);
		model.addAttribute("filieres", daoFiliere.findAll());
		model.addAttribute("salles", salleSrv.getAll());
		return "filieres.jsp";
	}
	
	@GetMapping("/{id}/delete")
	public String supprimer(@PathVariable Integer id)
	{
		daoFiliere.deleteById(id);
		return "redirect:/filiere";
	}

	@PostMapping
	public String ajouter(@RequestParam String libelle,LocalDate debut,LocalDate fin,@RequestParam(name="salle.id",required = false)Integer idSalle)
	{
		Salle salle = (idSalle==null)? null : salleSrv.getById(idSalle);
		Filiere filiere = new Filiere(null,libelle,debut,fin,salle);
		daoFiliere.save(filiere);
		return "redirect:/filiere";
	}
	
	@PostMapping("/{id}")
	public String modifier(@PathVariable Integer id,@RequestParam String libelle,LocalDate debut,LocalDate fin,@RequestParam(name="salle.id",required = false)Integer idSalle) 
	{
		Salle salle = (idSalle==null)? null : salleSrv.getById(idSalle);
		Filiere filiere = new Filiere(id,libelle,debut,fin,salle);
		daoFiliere.save(filiere);
		return "redirect:/filiere";
	}

}
