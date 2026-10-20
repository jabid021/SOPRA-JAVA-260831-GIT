package eshop.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import eshop.config.AppConfig;
import eshop.model.Fournisseur;
import eshop.model.Genre;
import eshop.model.Produit;
import jakarta.transaction.Transactional;


@SpringJUnitConfig(AppConfig.class)
@Transactional
@Rollback
public class DAOProduitTest {

	@Autowired
	IDAOProduit daoProduit;
	
	@Autowired
	IDAOPersonne daoPersonne;
	
	@Test
	public void autowiredDAOProduit() 
	{
		assertNotNull(daoProduit);
	}
	
	@Test
	public void insertProduit() 
	{
		//Arrange
		Fournisseur fournisseur = new Fournisseur(null,"Doe","John",Genre.homme,"Makers");
		fournisseur = (Fournisseur)daoPersonne.save(fournisseur);
		Produit produit = new Produit(null,"Formation",850.99,fournisseur);
		Produit produitBDD=null;
		
		//Act
		produit=daoProduit.save(produit);
		Integer id= produit.getId();
		//Assert
		assertNotNull(id);
		produitBDD=daoProduit.findById(id);
		assertNotNull(produitBDD);
		assertEquals("Formation", produitBDD.getLibelle());
		assertEquals(850.99, produitBDD.getPrix());
		assertNotNull(produitBDD.getFournisseur());
		assertEquals(fournisseur.getId(),produitBDD.getFournisseur().getId());
	}
	
	@Test
	public void findProduit() 
	{
	
	}
	
	@Test
	public void deleteProduitParId() 
	{
		
		
	}
	
}
