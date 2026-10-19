package eshop.test;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;

import eshop.dao.IDAOPersonne;
import eshop.dao.IDAOProduit;
import eshop.model.Client;
import eshop.model.Genre;
import eshop.model.Produit;

public class TestSpring {

	@Autowired
	IDAOProduit daoProduit;
	@Autowired
	IDAOPersonne daoPersonne;
	
	public void run() 
	{
		
		for(Produit p : daoProduit.findAll()) 
		{
			System.out.println(p);
		}
		
		Client client= new Client(null,"AbidTPTPTPTP","Jordan",Genre.homme,LocalDate.parse("1993-05-01") ,"1 bis","Rue de paris","Paris","75009");;
		
		daoPersonne.save(client);
		
		
		for(Client c : daoPersonne.findAllClient()) 
		{
			System.out.println(c);
		}
	}
}
