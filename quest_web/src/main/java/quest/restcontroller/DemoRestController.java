package quest.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import quest.dao.IDAOSalle;
import quest.model.Salle;

@RestController
@RequestMapping("/demoRest")
public class DemoRestController {

	@Autowired
	IDAOSalle daoSalle;
	
	@GetMapping
	public String demo1() 
	{
		System.out.println("On est dans notre api");
		return "Resultat de mon api !";
	}
	
	@GetMapping("/demoParam-{nb}")
	public String demo2(@PathVariable Integer nb,@RequestParam String mot) 
	{
		System.out.println(mot);
		System.out.println(nb);
		return "J'ai bien recu le param "+mot+" et le nb dans l'url "+nb;
	}
	
	
	 @GetMapping("/salle")
	 public Salle demoSalle() 
	 {
		// Salle s1 = new Salle(null,"Salle Rouge","1bis","Rue de paris","Paris","75009");
		 Salle s1 = daoSalle.findById(1).orElse(null);
		 return s1;
	 }
}
