package quest.dto.response;

import java.time.LocalDate;

import quest.model.Filiere;

public record FiliereResponse(
    Integer id,
    String libelle,
    LocalDate debut,
    LocalDate fin,
    Integer salleId,
    String salleNom,
    SalleResponse salle,
    Integer stagiaireCount
) {


    public static FiliereResponse convert(Filiere filiere) {
        return new FiliereResponse(
            filiere.getId(),
            filiere.getLibelle(),
            filiere.getDebut(),
            filiere.getFin(),

            filiere.getSalle().getId(),
            filiere.getSalle().getNom(),

            new SalleResponse(filiere.getSalle().getId(), filiere.getSalle().getNom()),

            filiere.getStagiaires().size()
        );
    }

}
