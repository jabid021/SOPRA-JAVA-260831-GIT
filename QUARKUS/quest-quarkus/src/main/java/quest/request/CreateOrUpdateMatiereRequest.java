package quest.request;

import jakarta.validation.constraints.NotBlank;

public record CreateOrUpdateMatiereRequest(@NotBlank String libelle) {

}
