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
import quest.dao.IDAOPersonne;
import quest.model.Genre;
import quest.model.Stagiaire;

@Controller
@RequestMapping("/stagiaire")
public class StagiaireController {

	@Autowired
	IDAOPersonne daoPersonne;
	
	@Autowired
	IDAOFiliere daoFiliere;
	
	@GetMapping
	public String afficherTous(Model model) 
	{
		List<Stagiaire> stagiaires = daoPersonne.findAllStagiaires();
		model.addAttribute("stagiaires", stagiaires);
		model.addAttribute("genres", Genre.values());
		model.addAttribute("stagiaire", new Stagiaire());
		model.addAttribute("filieres",daoFiliere.findAll());
		return "stagiaires.jsp";
	}
	
	@GetMapping("/{id}")
	public String afficherParId(@PathVariable Integer id,  Model model) 
	{
		Stagiaire stagiaire = (Stagiaire) daoPersonne.findById(id).orElse(null);
		List<Stagiaire> stagiaires = daoPersonne.findAllStagiaires();
		model.addAttribute("stagiaires", stagiaires);
		model.addAttribute("genres", Genre.values());
		model.addAttribute("stagiaire", stagiaire);
		model.addAttribute("filieres",daoFiliere.findAll());
		return "stagiaires.jsp";
	}
	
	@GetMapping("/{id}/delete")
	public String supprimer(@PathVariable Integer id) 
	{
		daoPersonne.deleteById(id);
		
		return "redirect:/stagiaire";
	}
	
	@PostMapping
	public String ajouter(@ModelAttribute Stagiaire stagiaire){
		System.out.println(stagiaire);
		daoPersonne.save(stagiaire);
		return "redirect:/stagiaire";
		
	}

	/*@PostMapping
	public String ajouter(@RequestParam String login,@RequestParam String password,
			@RequestParam String nom,@RequestParam String prenom,@RequestParam Genre genre,@RequestParam String email, 
			@RequestParam(name="adresse.numero") String numero,@RequestParam(name="adresse.voie") String voie,@RequestParam(name="adresse.ville") String ville,@RequestParam(name="adresse.cp") String cp,@RequestParam(name="filiere.id") Integer idFiliere) 
	{
		Filiere filiere = daoFiliere.findById(idFiliere).orElse(null);
		Stagiaire stagiaire = new Stagiaire(null,login,password,nom,prenom,genre,email,numero,voie,ville,cp,filiere);
		daoPersonne.save(stagiaire);
		return "redirect:/stagiaire";
		
	}*/
	
	@PostMapping("/{id}")
	public String modifier(@PathVariable Integer id,Stagiaire stagiaire) 
	{
		stagiaire.setId(id);
		daoPersonne.save(stagiaire);
		return "redirect:/stagiaire";
	}
	
	
	

}
