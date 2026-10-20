package quest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import quest.model.Module;

public interface IDAOModule extends JpaRepository<Module,Integer>{
	public List<Module> findAllByFiliereId(Integer idFiliere);

}
