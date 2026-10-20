package eshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import eshop.model.Achat;

public interface IDAOAchat extends JpaRepository<Achat,Integer> {

}
