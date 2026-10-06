package dao;

import java.util.List;

import eshop.model.Produit;

public interface IDAOProduit extends IDAO<Produit,Integer> {

	public List<Produit>  findByLibLike(String libelle);

	public	Produit  findByIdWithVentes(Integer idProduit);
	
	public List<Produit> findByLibContaining(String libelle);
}
