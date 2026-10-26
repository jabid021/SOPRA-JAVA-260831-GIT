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

import eshop.dao.IDAOProduit;
import eshop.model.Produit;
import view.Views;

@RestController
@RequestMapping("/api/produit")
public class ProduitRestController {

	@Autowired
	IDAOProduit daoProduit;
	
	@GetMapping
	@JsonView(Views.Produit.class)
	public List<Produit> afficherTous() 
	{
	  //Coder Ici
		return null;
	}

	@GetMapping("/{id}")
	@JsonView(Views.Produit.class)
	public Produit afficherParId(@PathVariable Integer id) 
	{
		//Coder Ici
		return null;
	}
	@GetMapping("/{id}/ventes")
	@JsonView(Views.ProduitWithVentes.class)
	public Produit afficherParIdAvecVentes(@PathVariable Integer id) 
	{
		//Coder Ici
		return null;
	}
	
	@DeleteMapping("/{id}")
	public void supprimer(@PathVariable Integer id)
	{
		daoProduit.deleteById(id);
	}

	@PostMapping
	@JsonView(Views.Produit.class)
	public Produit ajouter(@RequestBody Produit produit)
	{
		//Coder Ici
		return null;
	}
	
	@PutMapping("/{id}")
	@JsonView(Views.Produit.class)
	public Produit modifier(@PathVariable Integer id,@RequestBody Produit produit) 
	{
	  produit.setId(id);
	  //Coder Ici
	  return null;
	}
}
