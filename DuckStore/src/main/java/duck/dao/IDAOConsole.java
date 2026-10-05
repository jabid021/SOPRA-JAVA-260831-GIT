package duck.dao;

import java.util.List;

import duck.model.Console;
import duck.model.Hybride;
import duck.model.Portable;
import duck.model.Salon;

public interface IDAOConsole extends IDAO<Console,Integer> {

	public List<Portable> findAllPortable();
	public List<Hybride> findAllHybride();
	public List<Salon> findAllSalon();
	
}
