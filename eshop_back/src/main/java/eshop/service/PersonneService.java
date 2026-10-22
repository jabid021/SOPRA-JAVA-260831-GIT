package eshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eshop.dao.IDAOPersonne;
import eshop.model.Personne;

@Service
public class PersonneService {
	
	@Autowired
	IDAOPersonne daoPersonne;
	
	public List<Personne> getAll()
	{
		return daoPersonne.findAll();
	}
	
	public Personne getById(Integer id) 
	{
		
		return daoPersonne.findById(id).orElse(null);
	}
	
	public Personne insert(Personne personne) 
	{
		return daoPersonne.save(personne);
	}
	
	public Personne update(Personne personne) 
	{
		if(personne.getId()==null) 
		{
			throw new RuntimeException("Une personne doit avoir un id lors d'un update ...");
		}
		return daoPersonne.save(personne);
	}
	
	
	public void delete(Personne personne) 
	{
		if(personne==null) 
		{
			throw new RuntimeException("Delete d'une personne null ???!");
		}
		deleteById(personne.getId());
	}
	
	
	public void deleteById(Integer id) 
	{
		if(id==null) 
		{
			throw new RuntimeException("Delete d'une personne avec un id null ???!");
		}
		daoPersonne.deleteById(id);
	}
	
	
	

}
