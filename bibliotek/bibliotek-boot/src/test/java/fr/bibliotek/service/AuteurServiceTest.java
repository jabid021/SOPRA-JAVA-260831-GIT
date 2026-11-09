package fr.bibliotek.service;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.bibliotek.api.request.CreateOrUpdateAuteurRequest;
import fr.bibliotek.enumerator.NationaliteEnum;
import fr.bibliotek.exception.AuteurNotFoundException;
import fr.bibliotek.model.Auteur;
import fr.bibliotek.repo.AuteurRepository;

@ExtendWith(MockitoExtension.class)
class AuteurServiceTest {
    @Mock
    private AuteurRepository auteurRepository;

    @InjectMocks
    private AuteurService auteurService;

    private Auteur auteur;
    private CreateOrUpdateAuteurRequest request;

    @BeforeEach
    void setUp() {
        auteur = new Auteur();
        auteur.setId("auteur-id");
        auteur.setNom("Flaubert");
        auteur.setPrenom("Gustave");
        auteur.setNationalite(NationaliteEnum.FR);

        request = new CreateOrUpdateAuteurRequest();
        request.setNom("Flaubert");
        request.setPrenom("Gustave");
        request.setNationalite(NationaliteEnum.FR);
    }

    @Test
    void shouldFindAllAuteurs() {
        // Given
        Mockito.when(auteurRepository.findAll()).thenReturn(List.of(auteur));

        // When
        List<Auteur> result = auteurService.findAll();

        // Then
        Assertions.assertThat(result).hasSize(1);
        Assertions.assertThat(result.get(0).getNom()).isEqualTo("Flaubert");
        Mockito.verify(auteurRepository).findAll();
    }

    @Test
    void shouldFindAuteurById() {
        // Given
        Mockito.when(auteurRepository.findById("auteur-id")).thenReturn(Optional.of(auteur));

        // When
        Auteur result = auteurService.findById("auteur-id");

        // Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getId()).isEqualTo("auteur-id");
        Assertions.assertThat(result.getNom()).isEqualTo("Flaubert");
    }

    @Test
    void shouldThrowAuteurNotFoundExceptionWhenAuteurNotFound() {
        // Given
        Mockito.when(auteurRepository.findById("invalid-id")).thenReturn(Optional.empty());

        // When
        Throwable exception = Assertions.catchThrowable(() -> auteurService.findById("invalid-id"));

        // Then
        Assertions.assertThat(exception).isInstanceOf(AuteurNotFoundException.class);
    }

    @Test
    void shouldCreateAuteur() {
        // Given
        Mockito.when(auteurRepository.save(ArgumentMatchers.any(Auteur.class))).thenReturn(auteur);

        // When
        Auteur result = auteurService.save(request);

        // Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getNom()).isEqualTo("Flaubert");
        ArgumentCaptor<Auteur> captor = ArgumentCaptor.forClass(Auteur.class);
        Mockito.verify(auteurRepository).save(captor.capture());
        Assertions.assertThat(captor.getValue().getNom()).isEqualTo(request.getNom());
        Assertions.assertThat(captor.getValue().getPrenom()).isEqualTo(request.getPrenom());
        Assertions.assertThat(captor.getValue().getNationalite()).isEqualTo(request.getNationalite());
    }

    @Test
    void shouldUpdateAuteur() {
        // Given
        Mockito.when(auteurRepository.findById("auteur-id")).thenReturn(Optional.of(auteur));
        Mockito.when(auteurRepository.save(ArgumentMatchers.any(Auteur.class))).thenReturn(auteur);

        request.setPrenom("Gustave-Édouard");
        // When
        Auteur result = auteurService.save("auteur-id", request);

        // Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getPrenom()).isEqualTo("Gustave-Édouard");
        Mockito.verify(auteurRepository).findById("auteur-id");
        Mockito.verify(auteurRepository).save(ArgumentMatchers.any(Auteur.class));
    }

    @Test
    void shouldDeleteAuteur() {
        // Given
        String id = "auteur-id";

        // When
        auteurService.deleteById(id);

        // Then
        Mockito.verify(auteurRepository).deleteById(id);
    }
}
