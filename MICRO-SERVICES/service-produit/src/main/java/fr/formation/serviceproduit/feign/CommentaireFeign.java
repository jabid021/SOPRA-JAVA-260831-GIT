package fr.formation.serviceproduit.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "commentaire-service", url = "http://localhost:8082/api/commentaire")
public interface CommentaireFeign {
    @GetMapping("/by-produit-id/note/{produitId}")
    public Integer findNoteByProduitId(@PathVariable String produitId);

    @DeleteMapping("/by-produit-id/{produitId}")
    public String deleteAllByProduitId(@PathVariable String produitId);
}
