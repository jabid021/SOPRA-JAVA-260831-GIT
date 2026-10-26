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

import com.fasterxml.jackson.annotation.JsonView;

import quest.dao.IDAOFiliere;
import quest.model.Filiere;
import view.Views;

@RestController
@RequestMapping("/api/filiere")
public class FiliereRestController {

	
	@Autowired
	IDAOFiliere daoFiliere;
	
	@GetMapping
	@JsonView(Views.Filiere.class)
	public List<Filiere> afficherTous() 
	{
	  return daoFiliere.findAll();
	}

	@GetMapping("/{id}")
	@JsonView(Views.Filiere.class)
	public Filiere afficherParId(@PathVariable Integer id) 
	{
		return daoFiliere.findById(id).orElse(null);
	}
	
	@GetMapping("/{id}/stagiaires")
	@JsonView(Views.FiliereWithStagiaires.class)
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
	@JsonView(Views.Filiere.class)
	public Filiere ajouter(@RequestBody Filiere filiere)
	{
		daoFiliere.save(filiere);
		return filiere;
	}
	
	@PutMapping("/{id}")
	@JsonView(Views.Filiere.class)
	public Filiere modifier(@PathVariable Integer id,@RequestBody Filiere filiere) 
	{
	  filiere.setId(id);
      daoFiliere.save(filiere);
      return filiere;
	}
}
