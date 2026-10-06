package dao;

import java.util.List;

import eshop.model.Client;
import eshop.model.Fournisseur;
import eshop.model.Personne;

public interface IDAOPersonne extends IDAO<Personne,Integer> {

	public List<Fournisseur> findAllFournisseur();

	public	List<Client>findAllClient();

	public Client  findByIdWithAchats(Integer idClient);

	public Fournisseur  findByIdWithStock (Integer idFournisseur);
}
