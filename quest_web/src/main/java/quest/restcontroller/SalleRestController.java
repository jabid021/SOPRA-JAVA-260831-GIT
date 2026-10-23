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

import quest.dao.IDAOSalle;
import quest.model.Salle;

@RestController
@RequestMapping("/api/salle")
public class SalleRestController {

	
	@Autowired
	IDAOSalle daoSalle;
	
	@GetMapping
	public List<Salle> afficherTous() 
	{
	  return daoSalle.findAll();
	}

	@GetMapping("/{id}")
	public Salle afficherParId(@PathVariable Integer id) 
	{
		return daoSalle.findById(id).orElse(null);
	}
	
	@DeleteMapping("/{id}")
	public void supprimer(@PathVariable Integer id)
	{
		daoSalle.deleteById(id);
	}

	@PostMapping
	public Salle ajouter(@RequestBody Salle salle)
	{
		daoSalle.save(salle);
		return salle;
	}
	
	@PutMapping("/{id}")
	public Salle modifier(@PathVariable Integer id,@RequestBody Salle salle) 
	{
	  salle.setId(id);
      daoSalle.save(salle);
      return salle;
	}
}
