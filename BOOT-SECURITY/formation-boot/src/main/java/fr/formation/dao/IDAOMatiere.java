package fr.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Matiere;

public interface IDAOMatiere extends JpaRepository<Matiere, Integer> {

}
