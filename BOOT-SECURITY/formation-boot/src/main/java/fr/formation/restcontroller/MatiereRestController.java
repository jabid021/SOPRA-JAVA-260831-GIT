package fr.formation.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.dao.IDAOMatiere;
import fr.formation.model.Matiere;

@RestController
@RequestMapping("/api/matiere")
public class MatiereRestController {
    @Autowired
    private IDAOMatiere dao;

    @GetMapping
    public List<Matiere> findAll() {
        return this.dao.findAll();
    }
}
