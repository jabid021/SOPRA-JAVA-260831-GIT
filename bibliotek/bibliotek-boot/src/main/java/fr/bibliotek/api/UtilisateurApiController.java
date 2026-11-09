package fr.bibliotek.api;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.bibliotek.api.request.SubscriptionRequest;
import fr.bibliotek.api.response.EntityCreatedResponse;
import fr.bibliotek.model.Utilisateur;
import fr.bibliotek.repo.UtilisateurRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/utilisateur")
public class UtilisateurApiController {
    private final UtilisateurRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UtilisateurApiController(UtilisateurRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/inscription")
    public EntityCreatedResponse subscribe(@Valid @RequestBody SubscriptionRequest request) {
        Utilisateur utilisateur = new Utilisateur();

        utilisateur.setUsername(request.getUsername());
        utilisateur.setPassword(this.passwordEncoder.encode(request.getPassword()));

        this.repository.save(utilisateur);

        return new EntityCreatedResponse(utilisateur.getId());
    }
}
