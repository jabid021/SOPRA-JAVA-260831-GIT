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
import quest.model.Stagiaire;
import quest_dto.StagiaireDTO;

@RestController
@RequestMapping("/api/stagiaire")
public class StagiaireRestController {

	
	@Autowired
	IDAOPersonne daoPersonne;
	
	@GetMapping
	public List<StagiaireDTO> afficherTous() 
	{
		/*List<StagiaireDTO> listeStagiaireDTO = new ArrayList();
		for(Stagiaire s : daoPersonne.findAllStagiaires()) 
		{
			listeStagiaireDTO.add(StagiaireDTO.convert(s));
		}*/
		System.out.println(daoPersonne.findAllStagiaires());
		System.out.println(daoPersonne.findAllStagiaires().stream().map(s->StagiaireDTO.convert(s)).toList());
	  return daoPersonne.findAllStagiaires().stream().map(s->StagiaireDTO.convert(s)).toList();
	}

	@GetMapping("/{id}")
	public StagiaireDTO afficherParId(@PathVariable Integer id) 
	{
		return StagiaireDTO.convert((Stagiaire) daoPersonne.findById(id).orElse(null));
	}
	
	@DeleteMapping("/{id}")
	public void supprimer(@PathVariable Integer id)
	{
		daoPersonne.deleteById(id);
	}

	@PostMapping
	public Stagiaire ajouter(@RequestBody Stagiaire stagiaire)
	{
		daoPersonne.save(stagiaire);
		return stagiaire;
	}
	
	@PutMapping("/{id}")
	public Stagiaire modifier(@PathVariable Integer id,@RequestBody Stagiaire stagiaire) 
	{
	  stagiaire.setId(id);
      daoPersonne.save(stagiaire);
      return stagiaire;
	}
}
