package eshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eshop.dao.IDAOPersonne;
import eshop.model.Fournisseur;

@Service
public class FournisseurService {
	
	@Autowired
	IDAOPersonne daoPersonne;
	
	public List<Fournisseur> getAll()
	{
		return daoPersonne.findAllFournisseur();
	}
	
	public Fournisseur getById(Integer id) 
	{
		
		return (Fournisseur) daoPersonne.findById(id).orElse(null);
	}
	
	public Fournisseur insert(Fournisseur fournisseur) 
	{
		return daoPersonne.save(fournisseur);
	}
	
	public Fournisseur update(Fournisseur fournisseur) 
	{
		if(fournisseur.getId()==null) 
		{
			throw new RuntimeException("Un fournisseur doit avoir un id lors d'un update ...");
		}
		return daoPersonne.save(fournisseur);
	}
	
	
	public void delete(Fournisseur fournisseur) 
	{
		if(fournisseur==null) 
		{
			throw new RuntimeException("Delete d'un fournisseur null ???!");
		}
		deleteById(fournisseur.getId());
	}
	
	
	public void deleteById(Integer id) 
	{
		if(id==null) 
		{
			throw new RuntimeException("Delete d'un fournisseur avec un id null ???!");
		}
		daoPersonne.deleteById(id);
	}
	
	
	

}
