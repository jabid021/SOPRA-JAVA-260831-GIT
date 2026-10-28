package fr.formation.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.dao.IDAOUtilisateur;
import fr.formation.dto.SubscribeRequest;
import fr.formation.model.Utilisateur;

@RestController
@RequestMapping("/api/user")
public class UtilisateurRestController {
    @Autowired
    private IDAOUtilisateur dao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/subscribe")
    public Integer subscribe(@RequestBody SubscribeRequest request) {
        Utilisateur utilisateur = new Utilisateur();

        // utilisateur.setUsername(request.getUsername());
        // utilisateur.setPassword(this.passwordEncoder.encode(request.getPassword()));
        utilisateur.setUsername(request.username());
        utilisateur.setPassword(this.passwordEncoder.encode(request.password()));

        this.dao.save(utilisateur);

        return utilisateur.getId();
    }
}
