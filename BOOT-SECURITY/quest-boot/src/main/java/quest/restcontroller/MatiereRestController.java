package quest.restcontroller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
// @CrossOrigin("*")
public class MatiereRestController {
	private static final Logger log = LoggerFactory.getLogger(MatiereRestController.class);

	@Autowired
	IDAOMatiere daoMatiere;

	@GetMapping
	public List<Matiere> afficherTous()
	{
		System.out.println("Affichage de la liste des matieres");
		log.info("Affichage de la liste des matieres");
	  	return daoMatiere.findAll();
	}

	@GetMapping("/{id}")
	public Matiere afficherParId(@PathVariable Integer id)
	{
		// !! NE PAS METTRE D'INFO SENSIBLE !!
		// log.info("Recherche de la matière " + id);
		log.info("Recherche de la matière {} ({})", id, "autre valeur");

		log.debug("Niveau de debug");

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
