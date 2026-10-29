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

import quest.dao.IDAOMatiere;
import quest.model.Matiere;

@RestController
@RequestMapping("/api/matiere")
public class MatiereRestController {
	@Autowired
	IDAOMatiere daoMatiere;

	@GetMapping
	public List<Matiere> afficherTous()
	{
	  return daoMatiere.findAll();
	}

	@GetMapping("/{id}")
	public Matiere afficherParId(@PathVariable Integer id)
	{
		return daoMatiere.findById(id).orElse(null);
	}

	@DeleteMapping("/{id}")
	public void supprimer(@PathVariable Integer id)
	{
		daoMatiere.deleteById(id);
	}

	@PostMapping
	public Matiere ajouter(@RequestBody Matiere matiere)
	{
		daoMatiere.save(matiere);
		return matiere;
	}

	@PutMapping("/{id}")
	public Matiere modifier(@PathVariable Integer id,@RequestBody Matiere matiere)
	{
	  matiere.setId(id);
      daoMatiere.save(matiere);
      return matiere;
	}
}
