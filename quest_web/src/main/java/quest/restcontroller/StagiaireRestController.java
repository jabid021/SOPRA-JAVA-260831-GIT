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

import quest.dao.IDAOPersonne;
import quest.model.Stagiaire;
import view.Views;

@RestController
@RequestMapping("/api/stagiaire")
public class StagiaireRestController {

	
	@Autowired
	IDAOPersonne daoPersonne;
	
	@GetMapping
	@JsonView(Views.Stagiaire.class)
	public List<Stagiaire> afficherTous() 
	{
	  return daoPersonne.findAllStagiaires();
	}

	@GetMapping("/{id}")
	@JsonView(Views.Stagiaire.class)
	public Stagiaire afficherParId(@PathVariable Integer id) 
	{
		return (Stagiaire) daoPersonne.findById(id).orElse(null);
	}
	
	@DeleteMapping("/{id}")
	public void supprimer(@PathVariable Integer id)
	{
		daoPersonne.deleteById(id);
	}

	@PostMapping
	@JsonView(Views.Stagiaire.class)
	public Stagiaire ajouter(@RequestBody Stagiaire stagiaire)
	{
		daoPersonne.save(stagiaire);
		return stagiaire;
	}
	
	@PutMapping("/{id}")
	@JsonView(Views.Stagiaire.class)
	public Stagiaire modifier(@PathVariable Integer id,@RequestBody Stagiaire stagiaire) 
	{
	  stagiaire.setId(id);
      daoPersonne.save(stagiaire);
      return stagiaire;
	}
}
