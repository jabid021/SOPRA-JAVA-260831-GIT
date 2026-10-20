package quest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import quest.model.Matiere;

public interface IDAOMatiere extends JpaRepository<Matiere,Integer>{

	public List<Matiere> findByLibelleContaining(String recherche);
	public List<Matiere> findAllByOrderByLibelleDesc();
	public Integer countByLibelleContaining(String recherche);
}
