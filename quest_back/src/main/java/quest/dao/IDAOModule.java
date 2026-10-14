package quest.dao;

import java.util.List;

import quest.model.Module;

public interface IDAOModule extends IDAO<Module,Integer>{
	public List<Module> findAllByFiliereId(Integer idFiliere);
}
