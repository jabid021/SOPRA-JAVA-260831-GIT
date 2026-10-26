package eshop.restcontroller;

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

import eshop.dao.IDAOPersonne;
import eshop.model.Fournisseur;
import view.Views;

@RestController
@RequestMapping("/api/fournisseur")
public class FournisseurRestController {

	@Autowired
	IDAOPersonne daoPersonne;

	@GetMapping
	@JsonView(Views.Fournisseur.class)
	public List<Fournisseur> afficherTous() 
	{
		return daoPersonne.findAllFournisseur();
	}

	@GetMapping("/{id}")
	@JsonView(Views.Fournisseur.class)
	public Fournisseur afficherParId(@PathVariable Integer id) 
	{
		return (Fournisseur) daoPersonne.findById(id).orElse(null);
	}
	@GetMapping("/{id}/stock")
	@JsonView(Views.FournisseurWithStock.class)
	public Fournisseur afficherParIdAvecVentes(@PathVariable Integer id) 
	{
		return daoPersonne.findByIdWithStock(id);
	}

	@DeleteMapping("/{id}")
	public void supprimer(@PathVariable Integer id)
	{
		daoPersonne.deleteById(id);
	}

	@PostMapping
	@JsonView(Views.Fournisseur.class)
	public Fournisseur ajouter(@RequestBody Fournisseur fournisseur)
	{
		daoPersonne.save(fournisseur);
		return fournisseur;
	}

	@PutMapping("/{id}")
	@JsonView(Views.Fournisseur.class)
	public Fournisseur modifier(@PathVariable Integer id,@RequestBody Fournisseur fournisseur) 
	{
		fournisseur.setId(id);
		daoPersonne.save(fournisseur);
		return fournisseur;
	}
}
