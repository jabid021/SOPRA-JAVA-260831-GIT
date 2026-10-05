package duck.dao;

import java.util.List;

import duck.model.Client;
import duck.model.Compte;
import duck.model.Employe;

public interface IDAOCompte extends IDAO<Compte,Integer> {
	
	public List<Client> findAllClient();
	public Client findByIdWithAchats(Integer idClient);
	public List<Employe> findAllEmploye();
	public Compte findByLoginAndPassword(String login,String password);

	
}
