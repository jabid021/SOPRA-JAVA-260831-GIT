package hopital.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import hopital.model.Compte;
import hopital.model.Medecin;
import hopital.model.Secretaire;

public class DAOCompteJDBC implements IDAOCompte  {


	@Override
	public List<Compte> findAll() {
		List<Compte> comptes = new ArrayList();
		try {
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);
			PreparedStatement requete = conn.prepareStatement("SELECT * from compte");

			ResultSet resultat = requete.executeQuery();

			while(resultat.next()) 
			{
				Compte compte;
				if(resultat.getString("type_compte").equals("Secretaire")) 
				{
					compte = new  Secretaire(resultat.getInt("id"),resultat.getString("login"),resultat.getString("password"));
				}
				else 
				{
					compte = new Medecin(resultat.getInt("id"),resultat.getString("login"),resultat.getString("password"));
				}
				comptes.add(compte);
			}
			resultat.close();
			requete.close();
			conn.close();
		}catch(Exception e) {e.printStackTrace();}

		return comptes;
	}

	@Override
	public Compte findById(Integer id) {
		Compte compte = null;
		try {
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);
			PreparedStatement requete = conn.prepareStatement("SELECT * from compte where id=?");
			requete.setInt(1, id);

			ResultSet resultat = requete.executeQuery();

			while(resultat.next()) 
			{
				if(resultat.getString("type_compte").equals("Secretaire")) 
				{
					compte = new  Secretaire(resultat.getInt("id"),resultat.getString("login"),resultat.getString("password"));
				}
				else 
				{
					compte = new  Medecin(resultat.getInt("id"),resultat.getString("login"),resultat.getString("password"));
				}
			}
			resultat.close();
			requete.close();
			conn.close();
		}catch(Exception e) {e.printStackTrace();}

		return compte;
	}

	@Override
	public Compte insert(Compte compte) {
		try {
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);
			PreparedStatement requete = conn.prepareStatement("INSERT INTO compte (login,password,type_compte) VALUES (?,?,?)");

			requete.setString(1, compte.getLogin());
			requete.setString(2, compte.getPassword());
			requete.setString(3,compte.getClass().getSimpleName());
			
			requete.executeUpdate();

			requete.close();
			conn.close();
		}catch(Exception e) {e.printStackTrace();}
		return null;
	}

	@Override
	public Compte update(Compte compte) {
		try {
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);
			PreparedStatement requete = conn.prepareStatement("UPDATE compte set login=?,password=? where id=?");

			requete.setString(1, compte.getLogin());
			requete.setString(2, compte.getPassword());
			requete.setInt(3, compte.getId());
			
			requete.executeUpdate();

			requete.close();
			conn.close();
		}catch(Exception e) {e.printStackTrace();}
		return null;
	}

	@Override
	public void deleteById(Integer numero) {
		try {
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);
			PreparedStatement requete = conn.prepareStatement("DELETE FROM compte where id=?");

			requete.setInt(1, numero);

			requete.executeUpdate();

			requete.close();
			conn.close();
		}catch(Exception e) {e.printStackTrace();}
	}
	

	
	public Compte findByLoginAndPassword(String login,String password) {
		Compte compte = null;
		try {
			Connection conn = DriverManager.getConnection(urlBdd,loginBdd,passwordBdd);
			PreparedStatement requete = conn.prepareStatement("SELECT * from compte where login=? and password=?");
			requete.setString(1, login);
			requete.setString(2, password);

			ResultSet resultat = requete.executeQuery();

			while(resultat.next()) 
			{
				if(resultat.getString("type_compte").equals("Secretaire")) 
				{
					compte = new  Secretaire(resultat.getInt("id"),resultat.getString("login"),resultat.getString("password"));
				}
				else 
				{
					compte = new Medecin(resultat.getInt("id"),resultat.getString("login"),resultat.getString("password"));
				}
			}
			resultat.close();
			requete.close();
			conn.close();
		}catch(Exception e) {e.printStackTrace();}

		return compte;
	}
}
