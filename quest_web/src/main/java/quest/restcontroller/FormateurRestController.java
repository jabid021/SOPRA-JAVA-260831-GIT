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

import quest.dao.IDAOPersonne;
import quest.model.Formateur;

@RestController
@RequestMapping("/api/formateur")
public class FormateurRestController {

	
	@Autowired
	IDAOPersonne daoPersonne;
	
	@GetMapping
	public List<Formateur> afficherTous() 
	{
	  return daoPersonne.findAllFormateurs();
	}

	@GetMapping("/{id}")
	public Formateur afficherParId(@PathVariable Integer id) 
	{
		return (Formateur) daoPersonne.findById(id).orElse(null);
	}
	
	@DeleteMapping("/{id}")
	public void supprimer(@PathVariable Integer id)
	{
		daoPersonne.deleteById(id);
	}

	@PostMapping
	public Formateur ajouter(@RequestBody Formateur formateur)
	{
		daoPersonne.save(formateur);
		return formateur;
	}
	
	@PutMapping("/{id}")
	public Formateur modifier(@PathVariable Integer id,@RequestBody Formateur formateur) 
	{
	  formateur.setId(id);
      daoPersonne.save(formateur);
      return formateur;
	}
}
