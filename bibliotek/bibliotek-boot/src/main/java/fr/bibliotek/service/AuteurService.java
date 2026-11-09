package fr.bibliotek.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import fr.bibliotek.api.request.CreateOrUpdateAuteurRequest;
import fr.bibliotek.exception.AuteurNotFoundException;
import fr.bibliotek.model.Auteur;
import fr.bibliotek.repo.AuteurRepository;

@Service
public class AuteurService {
    private static final Logger log = LoggerFactory.getLogger(AuteurService.class);
    private final AuteurRepository repository;

    public AuteurService(AuteurRepository repository) {
        this.repository = repository;
    }

    public List<Auteur> findAll() {
        log.debug("Liste des auteurs ...");

        return this.repository.findAll();
    }

    public Auteur findById(String id) {
        log.debug("Recherche de l'auteur {} ...", id);

        return this.repository.findById(id).orElseThrow(AuteurNotFoundException::new);
    }

    public Auteur save(CreateOrUpdateAuteurRequest request) {
        log.debug("Création d'un auteur ...");

        Auteur auteur = this.save(new Auteur(), request);

        log.debug("Auteur {} ajouté !", auteur.getId());

        return auteur;
    }

    public Auteur save(String id, CreateOrUpdateAuteurRequest request) {
        log.debug("Modification de l'auteur {} ...", id);

        Auteur auteur = this.findById(id);

        auteur = this.save(auteur, request);

        log.debug("Auteur {} modifié !", id);

        return auteur;
    }

    public void deleteById(String id) {
        log.debug("Suppression de l'auteur {} ...", id);

        this.repository.deleteById(id);

        log.debug("Auteur {} supprimé !", id);
    }

    private Auteur save(Auteur auteur, CreateOrUpdateAuteurRequest request) {
        auteur.setNom(request.getNom());
        auteur.setPrenom(request.getPrenom());
        auteur.setNationalite(request.getNationalite());

        return this.repository.save(auteur);
    }
}
