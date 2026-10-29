package fr.formation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.model.Hello;

public interface IDAOHello extends JpaRepository<Hello, Integer> {

}
