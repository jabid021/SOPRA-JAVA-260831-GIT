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

import quest.dao.IDAOOrdinateur;
import quest.model.Ordinateur;
import quest_dto.OrdinateurDTO;

@RestController
@RequestMapping("/api/ordinateur")
public class OrdinateurRestController {

	
	@Autowired
	IDAOOrdinateur daoOrdinateur;
	
	@GetMapping
	public List<OrdinateurDTO> afficherTous() 
	{
	  return daoOrdinateur.findAll().stream().map(o->OrdinateurDTO.convert(o)).toList();
	}

	@GetMapping("/{id}")
	public OrdinateurDTO afficherParId(@PathVariable Integer id) 
	{
		return  OrdinateurDTO.convert(daoOrdinateur.findById(id).orElse(null));
	}
	
	@DeleteMapping("/{id}")
	public void supprimer(@PathVariable Integer id)
	{
		daoOrdinateur.deleteById(id);
	}

	@PostMapping
	public Ordinateur ajouter(@RequestBody Ordinateur ordinateur)
	{
		daoOrdinateur.save(ordinateur);
		return ordinateur;
	}
	
	@PutMapping("/{id}")
	public Ordinateur modifier(@PathVariable Integer id,@RequestBody Ordinateur ordinateur) 
	{
	  ordinateur.setNumero(id);
      daoOrdinateur.save(ordinateur);
      return ordinateur;
	}
}
