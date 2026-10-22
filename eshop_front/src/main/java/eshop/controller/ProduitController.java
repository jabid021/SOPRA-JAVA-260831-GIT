package eshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import eshop.dao.IDAOProduit;
import eshop.model.Fournisseur;
import eshop.model.Produit;
import eshop.service.FournisseurService;



@Controller
@RequestMapping("/produit")
public class ProduitController {

	@Autowired
	IDAOProduit daoProduit;
	
	@Autowired
	FournisseurService fournisseurSrv;
	
	@GetMapping
	public String afficherTous(Model model) 
	{
		model.addAttribute("produits", daoProduit.findAll());
		model.addAttribute("fournisseurs",fournisseurSrv.getAll());
		return "/WEB-INF/produits.jsp";
	}

	@GetMapping("/{id}")
	public String afficherParId(@PathVariable Integer id, Model model) 
	{
		Produit produit = daoProduit.findById(id).orElse(null);
		model.addAttribute("produit", produit);
		model.addAttribute("produits", daoProduit.findAll());
		model.addAttribute("fournisseurs",fournisseurSrv.getAll());
		return "/WEB-INF/produits.jsp";
	}
	
	@GetMapping("/{id}/delete")
	public String supprimer(@PathVariable Integer id)
	{
		daoProduit.deleteById(id);
		return "redirect:/produit";
	}

	@PostMapping
	public String ajouter(@RequestParam String libelle,@RequestParam double prix,@RequestParam(name="fournisseur.id") Integer idFournisseur)
	{
		Fournisseur fournisseur = fournisseurSrv.getById(idFournisseur);
		Produit produit = new Produit(null,libelle,prix,fournisseur);
		daoProduit.save(produit);
		return "redirect:/produit";
	}
	
	@PostMapping("/{id}")
	public String modifier(@PathVariable Integer id,@RequestParam String libelle,@RequestParam double prix,@RequestParam(name="fournisseur.id") Integer idFournisseur) 
	{
		Fournisseur fournisseur = fournisseurSrv.getById(idFournisseur);
		Produit produit = new Produit(id,libelle,prix,fournisseur);
		daoProduit.save(produit);
		return "redirect:/produit";
	}

}
