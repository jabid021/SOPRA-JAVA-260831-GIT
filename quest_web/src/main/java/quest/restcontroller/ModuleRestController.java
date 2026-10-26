package quest.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import quest.dao.IDAOModule;
import quest.model.Module;
import quest_dto.ModuleDTO;

@RestController
@RequestMapping("/api/module")
public class ModuleRestController {

	
	@Autowired
	IDAOModule daoModule;
	
	@GetMapping
	public List<ModuleDTO> afficherTous() 
	{
	  return daoModule.findAll().stream().map(m->ModuleDTO.convert(m)).toList();
	}
	
	@GetMapping("/filiere-{filiere}")
	public List<ModuleDTO> afficherTousParFiliere(@PathVariable Integer filiere) 
	{
	  return daoModule.findAllByFiliereId(filiere).stream().map(m->ModuleDTO.convert(m)).toList();
	}

	@GetMapping("/{id}")
	public ModuleDTO afficherParId(@PathVariable Integer id) 
	{
		return ModuleDTO.convert(daoModule.findById(id).orElse(null));
	}
	
	@DeleteMapping("/{id}")
	public void supprimer(@PathVariable Integer id)
	{
		daoModule.deleteById(id);
	}

	@PostMapping
	public Module ajouter(@RequestBody Module module)
	{
		daoModule.save(module);
		return module;
	}
	
	@PutMapping("/{id}")
	public Module modifier(@PathVariable Integer id,@RequestBody Module module) 
	{
	  module.setId(id);
      daoModule.save(module);
      return module;
	}
}
