package fr.formation.serviceproduit.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.serviceproduit.api.dto.request.CreateOrUpdateProduitRequest;
import fr.formation.serviceproduit.api.dto.response.ProduitResponse;
import fr.formation.serviceproduit.exception.EntityNotFoundException;
import fr.formation.serviceproduit.feign.CommentaireFeign;
import fr.formation.serviceproduit.model.Produit;
import fr.formation.serviceproduit.repo.ProduitRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/produit")
@RequiredArgsConstructor
@Log4j2
public class ProduitApiController {
    private final ProduitRepository repository;
    private final CommentaireFeign commentaireFeign;

    @GetMapping
    public List<ProduitResponse> findAll() {
        log.debug("Listing des produits ...");

        return this.repository.findAll().stream()
            .map(p -> {
                ProduitResponse resp = ProduitResponse.convert(p);

                int note = this.commentaireFeign.findNoteByProduitId(p.getId());

                resp.setNote(note);

                return resp;
            })
            .toList()
        ;
    }

    @GetMapping("/is-notable/{id}")
    public boolean isNotable(@PathVariable String id) {
        log.debug("Est-ce que le produit {} est notable ?", id);

        return this.repository.findById(id).orElseThrow(EntityNotFoundException::new).isNotable();
    }

    @PostMapping
    public String create(@Valid @RequestBody CreateOrUpdateProduitRequest request) {
        Produit produit = new Produit();

        log.debug("Création d'un nouveau produit ...");

        produit.setLibelle(request.libelle());
        produit.setPrix(request.prix());
        produit.setNotable(request.notable());

        this.repository.save(produit);

        log.debug("Produit {} créé !", produit.getId());

        return produit.getId();
    }

    @PutMapping("/{id}")
    public String update(@PathVariable String id, @Valid @RequestBody CreateOrUpdateProduitRequest request) {
        Produit produit = this.repository.findById(id).orElseThrow(EntityNotFoundException::new);

        log.debug("Modification du produit {} ...", id);

        produit.setLibelle(request.libelle());
        produit.setPrix(request.prix());
        produit.setNotable(request.notable());

        this.repository.save(produit);

        log.debug("Produit {} modifié !", id);

        return produit.getId();
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable String id) {
        Produit produit = this.repository.findById(id).orElseThrow(EntityNotFoundException::new);

        log.debug("Suppression du produit {} ...", id);

        this.repository.deleteById(id);

        log.debug("Produit {} supprimé !", id);
        log.debug("Suppression des commentaires du produit {} ...", id);

        this.commentaireFeign.deleteAllByProduitId(id);

        return produit.getId();
    }
}
