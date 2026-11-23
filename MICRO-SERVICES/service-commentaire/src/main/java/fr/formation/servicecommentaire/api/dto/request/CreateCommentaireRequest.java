package fr.formation.servicecommentaire.api.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public record CreateCommentaireRequest(@Positive @Max(5) int note, @NotEmpty String produitId) {

}
