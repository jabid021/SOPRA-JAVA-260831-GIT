package quest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import quest.dao.IDAOFiliere;
import quest.dao.IDAOMatiere;
import quest.dao.IDAOModule;
import quest.dao.IDAOPersonne;
import quest.model.Module;

@Controller
@RequestMapping("/module")
public class ModuleController {

	@Autowired
	IDAOPersonne daoPersonne;
	
	@Autowired
	IDAOModule daoModule;
	
	@Autowired
	IDAOFiliere daoFiliere;
	
	@Autowired
	IDAOMatiere daoMatiere;
	

	@GetMapping("/filiere-{idFiliere}")
	public String afficherTous(@PathVariable Integer idFiliere,Model model)  
	{
		model.addAttribute("module", new Module());
		model.addAttribute("modules", daoModule.findAllByFiliereId(idFiliere));
		model.addAttribute("matieres", daoMatiere.findAll());
		model.addAttribute("formateurs", daoPersonne.findAllFormateurs());
		model.addAttribute("filiereId",idFiliere);
		return "modules.jsp";
	}

	@GetMapping("/{id}/filiere-{idFiliere}")
	public String afficherParId(@PathVariable Integer id,@PathVariable Integer idFiliere,Model model)  
	{
		Module module = daoModule.findById(id).orElse(null);
		model.addAttribute("module", module);
		model.addAttribute("modules", daoModule.findAllByFiliereId(idFiliere));
		model.addAttribute("matieres", daoMatiere.findAll());
		model.addAttribute("formateurs", daoPersonne.findAllFormateurs());
		model.addAttribute("filiereId",idFiliere);
		return "modules.jsp";
	}

	@GetMapping("/{id}/delete/filiere-{idFiliere}")
	public String supprimer(@PathVariable Integer id,@PathVariable Integer idFiliere)  
	{
		daoModule.deleteById(id);
		
		return "redirect:/module/filiere-"+idFiliere;
	}

	@PostMapping("/filiere-{idFiliere}")
	public String ajouter(Module module,@PathVariable Integer idFiliere)  
	{
		if(module.getFormateur().getId()==null) 
		{
			module.setFormateur(null);
		}
		daoModule.save(module);
		return "redirect:/module/filiere-"+idFiliere; 
	
	}

	@PostMapping("/{id}/filiere-{idFiliere}")
	public String modifier(@PathVariable Integer id,@PathVariable Integer idFiliere,Module module)  
	{
		module.setId(id);
		if(module.getFormateur().getId()==null) 
		{
			module.setFormateur(null);
		}
		daoModule.save(module);
		return "redirect:/module/filiere-"+idFiliere; 
	}

}
