package fr.bibliotek.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import fr.bibliotek.api.request.CreateOrUpdateEditeurRequest;
import fr.bibliotek.exception.EditeurNotFoundException;
import fr.bibliotek.model.Editeur;
import fr.bibliotek.repo.EditeurRepository;

@Service
public class EditeurService {
    private static final Logger log = LoggerFactory.getLogger(EditeurService.class);
    private final EditeurRepository repository;

    public EditeurService(EditeurRepository repository) {
        this.repository = repository;
    }

    public List<Editeur> findAll() {
        log.debug("Liste des éditeurs ...");

        return this.repository.findAll();
    }

    public Editeur findById(String id) {
        log.debug("Recherche de l'éditeur {} ...", id);

        return this.repository.findById(id).orElseThrow(EditeurNotFoundException::new);
    }

    public Editeur save(CreateOrUpdateEditeurRequest request) {
        log.debug("Création d'un éditeur ...");

        Editeur editeur = this.save(new Editeur(), request);

        log.debug("Editeur {} ajouté !", editeur.getId());

        return editeur;
    }

    public Editeur save(String id, CreateOrUpdateEditeurRequest request) {
        log.debug("Modification de l'éditeur {} ...", id);

        Editeur editeur = this.findById(id);

        editeur = this.save(editeur, request);

        log.debug("Editeur {} modifié !", id);

        return editeur;
    }

    public void deleteById(String id) {
        log.debug("Suppression de l'éditeur {} ...", id);

        this.repository.deleteById(id);

        log.debug("Editeur {} supprimé !", id);
    }

    private Editeur save(Editeur editeur, CreateOrUpdateEditeurRequest request) {
        editeur.setNom(request.getNom());
        editeur.setPays(request.getPays());

        return this.repository.save(editeur);
    }
}
