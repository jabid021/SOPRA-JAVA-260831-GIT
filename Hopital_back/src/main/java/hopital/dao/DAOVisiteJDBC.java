package hopital.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import hopital.context.Singleton;
import hopital.model.Medecin;
import hopital.model.Patient;
import hopital.model.Visite;

public class DAOVisiteJDBC implements IDAOVisite {

	@Override
	public List<Visite> findAll() {
		List<Visite> visites = new ArrayList();

		try {
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);
			PreparedStatement requete = conn.prepareStatement("SELECT * from visite LEFT JOIN patient on patient.id=visite.id_patient");

			ResultSet resultat = requete.executeQuery();

			while(resultat.next()) 
			{
				//Patient patient = Singleton.getInstance().getDaoPatient().findById(resultat.getInt("id_patient"));
				Patient patient = null;
				if(resultat.getInt("id_patient")!=0) 
				{
					patient = new Patient(resultat.getInt("patient.id"),resultat.getString("prenom"),resultat.getString("nom"));
				}
	
				Medecin medecin = (Medecin) Singleton.getInstance().getDaoCompte().findById(resultat.getInt("id_medecin"));
				Visite visite = new Visite(resultat.getInt("numero"),LocalDate.parse(resultat.getString("date_visite")),resultat.getDouble("prix"),resultat.getInt("salle"),patient,medecin);
				visites.add(visite);
			}
			resultat.close();
			requete.close();
			conn.close();
		}catch(Exception e) {e.printStackTrace();}

		return visites;
	}

	@Override
	public Visite findById(Integer id) {
		Visite visite = null;
		try {
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);
			PreparedStatement requete = conn.prepareStatement("SELECT * from visite where numero=?");
			requete.setInt(1, id);

			ResultSet resultat = requete.executeQuery();

			while(resultat.next()) 
			{
				Patient patient = Singleton.getInstance().getDaoPatient().findById(resultat.getInt("id_patient"));
				Medecin medecin = (Medecin) Singleton.getInstance().getDaoCompte().findById(resultat.getInt("id_medecin"));
				
				visite = new Visite(resultat.getInt("numero"),LocalDate.parse(resultat.getString("date_visite")),resultat.getDouble("prix"),resultat.getInt("salle"),patient,medecin);
				
			}
			resultat.close();
			requete.close();
			conn.close();
		}catch(Exception e) {e.printStackTrace();}

		return visite;
	}

	@Override
	public Visite insert(Visite visite) {
		try {
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);
			PreparedStatement requete = conn.prepareStatement("INSERT INTO visite (id_patient,id_medecin,prix,salle,date_visite) VALUES (?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);

			requete.setInt(1, visite.getPatient().getId());
			requete.setInt(2, visite.getMedecin().getId());
			requete.setDouble(3, visite.getPrix());
			requete.setInt(4, visite.getSalle());
			requete.setString(5, visite.getDateVisite().toString());

			requete.executeUpdate();
			
			ResultSet resultat = requete.getGeneratedKeys();
			if(resultat.next()) 
			{
				visite.setNumero(resultat.getInt(1));
			}
		
			requete.close();
			conn.close();
		}catch(Exception e) {e.printStackTrace();}
		return visite;
	}

	@Override
	public Visite update(Visite visite) {
		try {
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);
			PreparedStatement requete = conn.prepareStatement("UPDATE visite set id_patient=?,id_medecin=?, prix=?, salle=?, date_visite=? where numero=?");

			requete.setInt(1, visite.getPatient().getId());
			requete.setInt(2, visite.getMedecin().getId());
			requete.setDouble(3, visite.getPrix());
			requete.setInt(4, visite.getSalle());
			requete.setString(5, visite.getDateVisite().toString());
			requete.setInt(6, visite.getNumero());
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
			PreparedStatement requete = conn.prepareStatement("DELETE FROM visite where numero=?");

			requete.setInt(1, id);

			requete.executeUpdate();

			requete.close();
			conn.close();
		}catch(Exception e) {e.printStackTrace();}
	}

	
	public List<Visite> findByPatientId(Integer idPatient) {
		List<Visite> visites = new ArrayList();
		try {
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);
			PreparedStatement requete = conn.prepareStatement("SELECT * from visite where id_patient=?");
			requete.setInt(1, idPatient);
			
			ResultSet resultat = requete.executeQuery();

			while(resultat.next()) 
			{
				Patient patient = Singleton.getInstance().getDaoPatient().findById(resultat.getInt("id_patient"));
				Medecin medecin = (Medecin) Singleton.getInstance().getDaoCompte().findById(resultat.getInt("id_medecin"));
				Visite visite = new Visite(resultat.getInt("numero"),LocalDate.parse(resultat.getString("date_visite")),resultat.getDouble("prix"),resultat.getInt("salle"),patient,medecin);
				visites.add(visite);
			}
			resultat.close();
			requete.close();
			conn.close();
		}catch(Exception e) {e.printStackTrace();}

		return visites;
	}
	
	
	public List<Visite> findByMedecinIdAndDateVisiteBetween(Integer idMedecin,String debut,String fin) {
		List<Visite> visites = new ArrayList();
		
		try {
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);
			PreparedStatement requete = conn.prepareStatement("SELECT * from visite where id_medecin=? and date_visite between ? and ?");
			requete.setInt(1, idMedecin);
			requete.setString(2, debut);
			requete.setString(3, fin);
			
			ResultSet resultat = requete.executeQuery();

			while(resultat.next()) 
			{
				Patient patient = Singleton.getInstance().getDaoPatient().findById(resultat.getInt("id_patient"));
				Medecin medecin = (Medecin) Singleton.getInstance().getDaoCompte().findById(resultat.getInt("id_medecin"));
				
				Visite visite = new Visite(resultat.getInt("numero"),LocalDate.parse(resultat.getString("date_visite")),resultat.getDouble("prix"),resultat.getInt("salle"),patient,medecin);
				visites.add(visite);
			}
			resultat.close();
			requete.close();
			conn.close();
		}catch(Exception e) {e.printStackTrace();}

		return visites;
	}
	
	
	public double findSumByMedecinIdAndDateVisiteBetween(Integer idMedecin,String debut,String fin) {
		List<Visite> visites = new ArrayList();
		double somme = 0;
		try {
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);
			PreparedStatement requete = conn.prepareStatement("SELECT SUM(prix) 'total' from visite where id_medecin=? and date_visite between ? and ?");
			requete.setInt(1, idMedecin);
			requete.setString(2, debut);
			requete.setString(3, fin);
			
			ResultSet resultat = requete.executeQuery();

			while(resultat.next()) 
			{
				somme=resultat.getInt("total");
			}
			resultat.close();
			requete.close();
			conn.close();
		}catch(Exception e) {e.printStackTrace();}

		return somme;
	}
	
	
	public void updatePatientSetNull(Integer idPatient) {
		try {
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);
			PreparedStatement requete = conn.prepareStatement("UPDATE visite set id_patient=null where id_patient=?");

			requete.setInt(1, idPatient);
		
			requete.executeUpdate();

			requete.close();
			conn.close();
		}catch(Exception e) {e.printStackTrace();}
	}
}
