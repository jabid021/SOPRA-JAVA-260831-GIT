package fr.formation.servicecommentaire.repo;

import fr.formation.servicecommentaire.model.Commentaire;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CommentaireRepository implements PanacheRepositoryBase<Commentaire, String> {
    public Integer findNoteByProduitId(String produitId) {
        return this.find("select avg(note) as int from Commentaire c where c.produitId = ?1", produitId)
            .project(Double.class)
            .singleResultOptional()
            .map(avg -> avg != null ? avg.intValue() : -1)
            .orElse(-1)
        ;
    }

    public long deleteAllByProduitId(String produitId) {
        return this.delete("produitId", produitId);
    }
}
