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
		
		for(Client c : daoPersonne.findAllClient()) 
		{
			System.out.println(c);
		}
		
		System.out.println("--------");
		
		System.out.println(daoProduit.findByPrixBetween(2000, 3000));
		System.out.println(daoProduit.findByPrixBetween(500, 1000));
		System.out.println(daoProduit.findByPrixLessThan(200));
		System.out.println(daoProduit.findByPrixLessThan(2000));
	}
}
