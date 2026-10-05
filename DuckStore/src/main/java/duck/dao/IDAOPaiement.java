package duck.dao;

import java.util.List;

import duck.model.CB;
import duck.model.Paiement;
import duck.model.Paypal;

public interface IDAOPaiement extends IDAO<Paiement,Integer> {

	public List<CB> findAllCB();
	public List<Paypal> findAllPaypal();
}
