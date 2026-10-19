package eshop.test;

import java.time.LocalDate;

import dao.IDAOPersonne;
import dao.IDAOProduit;
import eshop.model.Client;
import eshop.model.Genre;
import eshop.model.Produit;

public class TestSpring {

	IDAOProduit daoProduit;
	
	IDAOPersonne daoPersonne;
	
	public void run() 
	{
		
		for(Produit p : daoProduit.findAll()) 
		{
			System.out.println(p);
		}
		
		Client client= new Client(null,"Abid","Jordan",Genre.homme,LocalDate.parse("1993-05-01") ,"1 bis","Rue de paris","Paris","75009");;
		
		daoPersonne.save(client);
		
		
		for(Client c : daoPersonne.findAllClient()) 
		{
			System.out.println(c);
		}
	}
}
