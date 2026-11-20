package quest.response;

import quest.model.Matiere;

public record MatiereResponse(int id, String libelle) {
    public static MatiereResponse convert(Matiere matiere) {
        return new MatiereResponse(matiere.getId(), matiere.getLibelle());
    }
}
