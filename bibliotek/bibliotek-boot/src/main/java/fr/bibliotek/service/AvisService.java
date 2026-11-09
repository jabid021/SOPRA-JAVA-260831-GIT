package fr.bibliotek.service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import fr.bibliotek.api.request.CreateOrUpdateAvisRequest;
import fr.bibliotek.exception.AvisNotFoundException;
import fr.bibliotek.model.Avis;
import fr.bibliotek.repo.AvisRepository;
import fr.bibliotek.repo.LivreRepository;

@Service
public class AvisService {
    private static final Logger log = LoggerFactory.getLogger(AvisService.class);
    private final AvisRepository repository;
    private final LivreRepository livreRepository;

    public AvisService(AvisRepository repository, LivreRepository livreRepository) {
        this.repository = repository;
        this.livreRepository = livreRepository;
    }

    public List<Avis> findAll() {
        log.debug("Liste des avis ...");

        return this.repository.findAll();
    }

    public Avis findById(String id) {
        log.debug("Recherche de l'avis {} ...", id);

        return this.repository.findById(id).orElseThrow(AvisNotFoundException::new);
    }

    public Avis save(CreateOrUpdateAvisRequest request) {
        log.debug("Création d'un avis ...");

        Avis avis = this.save(new Avis(), request);

        log.debug("Avis {} créé !", avis.getId());

        return avis;
    }

    public Avis save(String id, CreateOrUpdateAvisRequest request) {
        log.debug("Modification de l'avis {} ...", id);

        Avis avis = this.findById(id);

        avis = this.save(avis, request);

        log.debug("Avis {} modifié !", id);

        return avis;
    }

    public void deleteById(String id) {
        log.debug("Suppression de l'avis {} ...", id);

        this.repository.deleteById(id);

        log.debug("Avis {} supprimé !", id);
    }

    private Avis save(Avis avis, CreateOrUpdateAvisRequest request) {
        if (avis.getDate() == null) {
            avis.setDate(LocalDateTime.now());
        }

        avis.setNote(request.getNote());
        avis.setCommentaire(request.getCommentaire());

        avis.setLivre(this.livreRepository.getReferenceById(request.getLivreId()));

        return this.repository.save(avis);
    }
}
