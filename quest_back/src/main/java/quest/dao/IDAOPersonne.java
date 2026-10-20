package quest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import quest.model.Formateur;
import quest.model.Personne;
import quest.model.Stagiaire;

public interface IDAOPersonne extends JpaRepository<Personne,Integer>{

	@Query("FROM Formateur")
	public List<Formateur> findAllFormateurs(); 
	@Query("FROM Stagiaire")
	public List<Stagiaire> findAllStagiaires();
	
	public Personne findByLoginAndPassword(String login,String password);
	//SELECT s FROM Stagiaire s where s.ordinateur is null
	public List<Stagiaire> findByOrdinateurIsNull();
}
