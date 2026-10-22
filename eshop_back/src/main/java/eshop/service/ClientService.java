package eshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eshop.dao.IDAOPersonne;
import eshop.model.Client;

@Service
public class ClientService {
	
	@Autowired
	IDAOPersonne daoPersonne;
	
	public List<Client> getAll()
	{
		return daoPersonne.findAllClient();
	}
	
	public Client getById(Integer id) 
	{
		
		return (Client) daoPersonne.findById(id).orElse(null);
	}
	
	public Client insert(Client client) 
	{
		return daoPersonne.save(client);
	}
	
	public Client update(Client client) 
	{
		if(client.getId()==null) 
		{
			throw new RuntimeException("Un client doit avoir un id lors d'un update ...");
		}
		return daoPersonne.save(client);
	}
	
	
	public void delete(Client client) 
	{
		if(client==null) 
		{
			throw new RuntimeException("Delete d'un client null ???!");
		}
		deleteById(client.getId());
	}
	
	
	public void deleteById(Integer id) 
	{
		if(id==null) 
		{
			throw new RuntimeException("Delete d'un client avec un id null ???!");
		}
		daoPersonne.deleteById(id);
	}
	
	
	

}
