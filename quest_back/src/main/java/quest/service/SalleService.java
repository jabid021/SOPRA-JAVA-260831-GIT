package quest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import quest.dao.IDAOSalle;
import quest.model.Salle;

@Service
public class SalleService {
	
	@Autowired
	IDAOSalle daoSalle;
	
	public List<Salle> getAll()
	{
		return daoSalle.findAll();
	}
	
	public Salle getById(Integer id) 
	{
		return daoSalle.findById(id);
	}
	
	public Salle insert(Salle salle) 
	{
		return daoSalle.save(salle);
	}
	
	public Salle update(Salle salle) 
	{
		if(salle.getId()==null) 
		{
			throw new RuntimeException("Un salle doit avoir un id lors d'un update ...");
		}
		return daoSalle.save(salle);
	}
	
	
	public void delete(Salle salle) 
	{
		if(salle==null) 
		{
			throw new RuntimeException("Delete d'une salle null ???!");
		}
		deleteById(salle.getId());
	}
	
	
	public void deleteById(Integer id) 
	{
		if(id==null) 
		{
			throw new RuntimeException("Delete d'une salle avec un id null ???!");
		}
		daoSalle.deleteById(id);
	}
	
	
	

}
