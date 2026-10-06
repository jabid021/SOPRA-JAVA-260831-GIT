package hopital.service;

import java.util.List;

import hopital.context.Singleton;
import hopital.dao.IDAOPatient;
import hopital.dao.IDAOVisite;
import hopital.model.Patient;

public class PatientService {
	
	private static IDAOPatient daoPatient= Singleton.getInstance().getDaoPatient();
	private static IDAOVisite daoVisite= Singleton.getInstance().getDaoVisite();
	
	public List<Patient> getAll()
	{
		return daoPatient.findAll();
	}
	
	public Patient getById(Integer id) 
	{
		return daoPatient.findById(id);
	}
	
	public Patient insert(Patient patient) 
	{
		if(patient.getPrenom().length()==0) 
		{
			throw new RuntimeException("Un patient doit avoir un prenom dans un insert...");
			
		}
		return daoPatient.save(patient);
	}
	
	public Patient update(Patient patient) 
	{
		if(patient.getId()==null) 
		{
			throw new RuntimeException("Un patient doit avoir un id lors d'un update ...");
		}
		return daoPatient.save(patient);
	}
	
	
	public void delete(Patient patient) 
	{
		if(patient==null) 
		{
			throw new RuntimeException("Delete d'un patient null ???!");
		}
		deleteById(patient.getId());
	}
	
	
	public void deleteById(Integer id) 
	{
		daoVisite.updatePatientSetNull(id);
		daoPatient.deleteById(id);
	}
	
	
	

}
