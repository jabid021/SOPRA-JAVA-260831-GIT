package duck.dao;

import duck.model.Jeu;

public interface IDAOJeu extends IDAO<Jeu,Integer> {

	public Jeu findByIdWithVentes(Integer idJeu);
}
