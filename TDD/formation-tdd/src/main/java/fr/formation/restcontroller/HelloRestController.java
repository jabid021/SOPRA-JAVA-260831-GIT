package fr.formation.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.dao.IDAOHello;
import fr.formation.model.Hello;

@RestController
@RequestMapping("/api/hello")
public class HelloRestController {
    @Autowired
    private IDAOHello dao;

    @GetMapping
    public String hello() {
        return "hello";
    }

    @GetMapping("/find")
    public List<Hello> findAll() {
        return this.dao.findAll();
    }
}
