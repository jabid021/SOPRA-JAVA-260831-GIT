package quest.repo;

import java.util.Optional;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import quest.model.Matiere;

@ApplicationScoped
public class MatiereRepository implements PanacheRepositoryBase<Matiere, Integer> {
    public Optional<Matiere> findByLibelle(String libelle) {
        return this.find("libelle", libelle).firstResultOptional();
    }
}
