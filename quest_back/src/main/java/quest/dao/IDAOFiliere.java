package quest.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import quest.model.Filiere;

public interface IDAOFiliere extends JpaRepository<Filiere,Integer>{

	@Query("Select f from Filiere f where f.debut > :date")
	public List<Filiere> findFiliereDateApres(@Param("date") LocalDate date);
	
	public List<Filiere> findByDebutAfter(LocalDate date);
	
	@Query("SELECT f FROM Filiere f LEFT JOIN FETCH f.stagiaires where f.id=:filiere")
	public Filiere findByIdWithStagiaires(@Param("filiere") Integer idFiliere);
}
