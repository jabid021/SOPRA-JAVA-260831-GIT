package eshop.test;

import java.util.List;

import context.Singleton;
import eshop.model.Achat;
import eshop.model.Produit;

public class TestDAO {

	public static void main(String[] args) {
		
		String rechercheLike = "%Q_";
		List<Produit> produitLibLike = Singleton.getInstance().getDaoProduit().findByLibLike(rechercheLike);
		System.out.println("Liste des produits contenant le pattern : "+rechercheLike);
		for(Produit p : produitLibLike) {System.out.println(p);}
		
		
		String rechercheContaining = "a";
		List<Produit> produitLibContaining = Singleton.getInstance().getDaoProduit().findByLibContaining(rechercheContaining);
		System.out.println("Liste des produits contenant  : "+rechercheContaining);
		for(Produit p : produitLibContaining) {System.out.println(p);}
		
		
		System.out.println(Singleton.getInstance().getDaoPersonne().findAllClient());
		
		System.out.println(Singleton.getInstance().getDaoPersonne().findAllFournisseur());
		
		Produit produit = Singleton.getInstance().getDaoProduit().findByIdWithVentes(1);
		
		System.out.println("Fiche du produit 1 :" + produit);
		if(produit.getVentes().isEmpty()) {System.out.println("Ce produit n'a aucune vente");}
		for(Achat a : produit.getVentes()) {System.out.println(a);}
	}

}
