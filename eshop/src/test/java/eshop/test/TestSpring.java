package eshop.test;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import eshop.dao.IDAOAchat;
import eshop.dao.IDAOPersonne;
import eshop.dao.IDAOProduit;
import eshop.model.Client;
import eshop.model.Fournisseur;
import eshop.model.Genre;
import eshop.model.Produit;

public class TestSpring {

	@Autowired
	IDAOProduit daoProduit;
	
	@Autowired
	IDAOPersonne daoPersonne;
	
	@Autowired
	IDAOAchat daoAchat;
	
	public void run() 
	{
		
		for(Produit p : daoProduit.findAll()) 
		{
			System.out.println(p);
		}
		
		Client client= new Client(null,"Abid","Jordan",Genre.homme,LocalDate.parse("1993-05-01") ,"1 bis","Rue de paris","Paris","75009");;
		
		daoPersonne.save(client);
		
		
		for(Fournisseur f : daoPersonne.findAllFournisseur()) 
		{
			System.out.println(f);
		}
	}
}
