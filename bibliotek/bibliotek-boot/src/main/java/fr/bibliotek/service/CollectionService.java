package fr.bibliotek.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.bibliotek.api.request.CreateOrUpdateCollectionRequest;
import fr.bibliotek.exception.CollectionNotFoundException;
import fr.bibliotek.model.Collection;
import fr.bibliotek.repo.CollectionRepository;

@Service
public class CollectionService {
    private static final Logger log = LoggerFactory.getLogger(CollectionService.class);
    private final CollectionRepository repository;
    private final ApplicationEventPublisher eventPublisher;

    public CollectionService(CollectionRepository repository, ApplicationEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public List<Collection> findAll() {
        log.debug("Liste des collections ...");

        return this.repository.findAll();
    }

    public Collection findById(String id) {
        log.debug("Recherche de la collection {} ...", id);

        return this.repository.findById(id).orElseThrow(CollectionNotFoundException::new);
    }

    @Transactional
    public Collection save(CreateOrUpdateCollectionRequest request) {
        log.debug("Création d'une collection ...");

        Collection collection = this.save(new Collection(), request);

        log.debug("Collection {} ajoutée !", collection.getId());

        return collection;
    }


    @Transactional
    public Collection save(String id, CreateOrUpdateCollectionRequest request) {
        log.debug("Modification de la collection {} ...", id);

        Collection collection = this.findById(id);

        collection = this.save(collection, request);

        log.debug("Collection {} modifiée !", id);

        return collection;
    }


    @Transactional
    public void deleteById(String id) {
        log.debug("Suppression de la collection {} ...", id);

        this.repository.deleteById(id);
        this.eventPublisher.publishEvent(new CollectionsChangedEvent());

        log.debug("Collection {} supprimée !", id);
    }

    private Collection save(Collection collection, CreateOrUpdateCollectionRequest request) {
        collection.setNom(request.getNom());

        Collection savedCollection = this.repository.save(collection);
        this.eventPublisher.publishEvent(new CollectionsChangedEvent());

        return savedCollection;
    }
}
