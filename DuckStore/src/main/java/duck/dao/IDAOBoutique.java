package duck.dao;

import duck.model.Boutique;

public interface IDAOBoutique extends IDAO<Boutique,Integer> {

	public Boutique findByIdWithStaff(Integer id);
	public Boutique findByIdWithCataloguef(Integer id);
	
}
