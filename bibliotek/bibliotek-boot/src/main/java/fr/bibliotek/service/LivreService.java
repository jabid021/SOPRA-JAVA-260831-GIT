package fr.bibliotek.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import fr.bibliotek.api.request.CreateOrUpdateLivreRequest;
import fr.bibliotek.exception.LivreNotFoundException;
import fr.bibliotek.model.Livre;
import fr.bibliotek.repo.AuteurRepository;
import fr.bibliotek.repo.CollectionRepository;
import fr.bibliotek.repo.EditeurRepository;
import fr.bibliotek.repo.LivreRepository;

@Service
public class LivreService {
    private static final Logger log = LoggerFactory.getLogger(LivreService.class);
    private final LivreRepository repository;
    private final AuteurRepository auteurRepository;
    private final EditeurRepository editeurRepository;
    private final CollectionRepository collectionRepository;

    public LivreService(LivreRepository repository, AuteurRepository auteurRepository, EditeurRepository editeurRepository, CollectionRepository collectionRepository) {
        this.repository = repository;
        this.auteurRepository = auteurRepository;
        this.editeurRepository = editeurRepository;
        this.collectionRepository = collectionRepository;
    }

    public List<Livre> findAll() {
        log.debug("Liste des livres ...");

        return this.repository.findAll();
    }

    public Livre findById(String id) {
        log.debug("Recherche du livre {} ...", id);

        return this.repository.findById(id).orElseThrow(LivreNotFoundException::new);
    }

    public Livre save(CreateOrUpdateLivreRequest request) {
        log.debug("Création d'un livre ...");

        Livre livre = this.save(new Livre(), request);

        log.debug("Livre {} ajouté !", livre.getId());

        return livre;
    }

    public Livre save(String id, CreateOrUpdateLivreRequest request) {
        log.debug("Modification du livre {} ...", id);

        Livre livre = this.findById(id);

        livre = this.save(livre, request);

        log.debug("Livre {} modifié !", id);

        return livre;
    }

    public void deleteById(String id) {
        log.debug("Suppression du livre {} ...", id);

        this.repository.deleteById(id);

        log.debug("Livre {} supprimé !", id);
    }

    private Livre save(Livre livre, CreateOrUpdateLivreRequest request) {
        livre.setNom(request.getNom());
        livre.setResume(request.getResume());
        livre.setPublication(request.getPublication());

        livre.setAuteur(this.auteurRepository.getReferenceById(request.getAuteurId()));
        livre.setEditeur(this.editeurRepository.getReferenceById(request.getEditeurId()));

        if (request.getCollectionId() != null && !request.getCollectionId().isBlank()) {
            livre.setCollection(this.collectionRepository.getReferenceById(request.getCollectionId()));
        }

        else {
            livre.setCollection(null);
        }

        return this.repository.save(livre);
    }
}
