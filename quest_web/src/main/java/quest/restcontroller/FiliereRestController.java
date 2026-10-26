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

import quest.dao.IDAOFiliere;
import quest.model.Filiere;

@RestController
@RequestMapping("/api/filiere")
public class FiliereRestController {

	
	@Autowired
	IDAOFiliere daoFiliere;
	
	@GetMapping
	public List<Filiere> afficherTous() 
	{
	  return daoFiliere.findAll();
	}

	@GetMapping("/{id}")
	public Filiere afficherParId(@PathVariable Integer id) 
	{
		return daoFiliere.findById(id).orElse(null);
	}
	
	@GetMapping("/{id}/stagiaires")
	public Filiere afficherParIdAvecStagiaires(@PathVariable Integer id) 
	{
		return daoFiliere.findByIdWithStagiaires(id);
	}
	
	@DeleteMapping("/{id}")
	public void supprimer(@PathVariable Integer id)
	{
		daoFiliere.deleteById(id);
	}

	@PostMapping
	public Filiere ajouter(@RequestBody Filiere filiere)
	{
		daoFiliere.save(filiere);
		return filiere;
	}
	
	@PutMapping("/{id}")
	public Filiere modifier(@PathVariable Integer id,@RequestBody Filiere filiere) 
	{
	  filiere.setId(id);
      daoFiliere.save(filiere);
      return filiere;
	}
}
