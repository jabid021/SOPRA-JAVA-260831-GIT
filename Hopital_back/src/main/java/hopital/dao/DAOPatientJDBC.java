package hopital.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import hopital.model.Patient;

public class DAOPatientJDBC implements IDAOPatient {

	@Override
	public List<Patient> findAll() {
		
		List<Patient> patients = new ArrayList();
		try {
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);
			PreparedStatement requete = conn.prepareStatement("SELECT * from patient");

			ResultSet resultat = requete.executeQuery();

			while(resultat.next()) 
			{
				Patient patient = new Patient(resultat.getInt("id"),resultat.getString("prenom"),resultat.getString("nom"));
				patients.add(patient);
			}
			resultat.close();
			requete.close();
			conn.close();
		}catch(Exception e) {e.printStackTrace();}

		return patients;
	}

	@Override
	public Patient findById(Integer id) {
		Patient patient = null;
		try {
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);
			PreparedStatement requete = conn.prepareStatement("SELECT * from patient where id=?");
			requete.setInt(1, id);

			ResultSet resultat = requete.executeQuery();

			while(resultat.next()) 
			{
				patient = new Patient(resultat.getInt("id"),resultat.getString("prenom"),resultat.getString("nom"));
			
			}
			resultat.close();
			requete.close();
			conn.close();
		}catch(Exception e) {e.printStackTrace();}

		return patient;
	}

	@Override
	public Patient insert(Patient patient) {
		try {
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);
			PreparedStatement requete = conn.prepareStatement("INSERT INTO patient (id,prenom,nom) VALUES (?,?,?)");

			requete.setInt(1,patient.getId());
			requete.setString(2, patient.getPrenom());
			requete.setString(3, patient.getNom());
		
			requete.executeUpdate();

			requete.close();
			conn.close();
		}catch(Exception e) {e.printStackTrace();}
		return null;
	}

	@Override
	public Patient update(Patient patient) {
		try {
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);
			PreparedStatement requete = conn.prepareStatement("UPDATE patient set prenom= ?,nom=? where id=?");

			requete.setString(1, patient.getPrenom());
			requete.setString(2, patient.getNom());
			requete.setInt(3,patient.getId());
			
			requete.executeUpdate();

			requete.close();
			conn.close();
		}catch(Exception e) {e.printStackTrace();}
		return null;
	}

	@Override
	public void deleteById(Integer id) {
		try {
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);
			PreparedStatement requete = conn.prepareStatement("DELETE FROM patient where id=?");

			requete.setInt(1, id);

			requete.executeUpdate();

			requete.close();
			conn.close();
		}catch(Exception e) {e.printStackTrace();}
	}

}
