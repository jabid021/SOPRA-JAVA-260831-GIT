package fr.bibliotek.service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.ArgumentMatchers;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.bibliotek.api.request.CreateOrUpdateLivreRequest;
import fr.bibliotek.enumerator.NationaliteEnum;
import fr.bibliotek.exception.LivreNotFoundException;
import fr.bibliotek.model.Auteur;
import fr.bibliotek.model.Editeur;
import fr.bibliotek.model.Livre;
import fr.bibliotek.repo.AuteurRepository;
import fr.bibliotek.repo.CollectionRepository;
import fr.bibliotek.repo.EditeurRepository;
import fr.bibliotek.repo.LivreRepository;

@ExtendWith(MockitoExtension.class)
class LivreServiceTest {
    @Mock
    private LivreRepository livreRepository;

    @Mock
    private AuteurRepository auteurRepository;

    @Mock
    private EditeurRepository editeurRepository;

    @Mock
    private CollectionRepository collectionRepository;

    @InjectMocks
    private LivreService livreService;

    private Livre livre;
    private Auteur auteur;
    private Editeur editeur;
    private CreateOrUpdateLivreRequest request;

    @BeforeEach
    void setUp() {
        this.auteur = new Auteur();
        this.editeur = new Editeur();
        this.livre = new Livre();
        this.request = new CreateOrUpdateLivreRequest();

        this.auteur.setId("auteur-id");
        this.auteur.setNom("Zola");
        this.auteur.setPrenom("Émile");
        this.auteur.setNationalite(NationaliteEnum.FR);

        this.editeur.setId("editeur-id");
        this.editeur.setNom("Gallimard");
        this.editeur.setPays(NationaliteEnum.FR);

        this.livre.setId("livre-id");
        this.livre.setNom("Germinal");
        this.livre.setResume("Un grand roman");
        this.livre.setPublication(LocalDate.of(1885, 2, 25));
        this.livre.setAuteur(this.auteur);
        this.livre.setEditeur(this.editeur);

        this.request.setNom("Germinal");
        this.request.setResume("Un grand roman");
        this.request.setPublication(LocalDate.of(1885, 2, 25));
        this.request.setAuteurId("auteur-id");
        this.request.setEditeurId("editeur-id");
        this.request.setCollectionId(null);
    }

    @Test
    void shouldFindAllLivres() {
        // Given
        Mockito.when(livreRepository.findAll()).thenReturn(List.of(livre));

        // When
        List<Livre> result = livreService.findAll();

        // Then
        Assertions.assertThat(result).hasSize(1);
        Assertions.assertThat(result.get(0).getNom()).isEqualTo("Germinal");
        Mockito.verify(livreRepository).findAll();
    }

    @Test
    void shouldFindLivreById() {
        // Given
        Mockito.when(livreRepository.findById("livre-id")).thenReturn(Optional.of(livre));

        // When
        Livre result = livreService.findById("livre-id");

        // Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getId()).isEqualTo("livre-id");
        Assertions.assertThat(result.getNom()).isEqualTo("Germinal");
        Mockito.verify(livreRepository).findById("livre-id");
    }

    @Test
    void shouldThrowLivreNotFoundExceptionWhenLivreNotFound() {
        // Given
        Mockito.when(livreRepository.findById("invalid-id")).thenReturn(Optional.empty());

        // When
        Throwable exception = Assertions.catchThrowable(() -> livreService.findById("invalid-id"));

        // Then
        Assertions.assertThat(exception).isInstanceOf(LivreNotFoundException.class);
    }

    @Test
    void shouldCreateLivre() {
        // Given
        Mockito.when(auteurRepository.getReferenceById("auteur-id")).thenReturn(auteur);
        Mockito.when(editeurRepository.getReferenceById("editeur-id")).thenReturn(editeur);
        Mockito.when(livreRepository.save(ArgumentMatchers.any(Livre.class))).thenReturn(livre);

        // When
        Livre result = livreService.save(request);

        // Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getId()).isEqualTo("livre-id");
        Assertions.assertThat(result.getNom()).isEqualTo("Germinal");

        ArgumentCaptor<Livre> captor = ArgumentCaptor.forClass(Livre.class);
        Mockito.verify(livreRepository).save(captor.capture());

        Assertions.assertThat(captor.getValue().getNom()).isEqualTo(request.getNom());
        Assertions.assertThat(captor.getValue().getResume()).isEqualTo(request.getResume());
        Assertions.assertThat(captor.getValue().getPublication()).isEqualTo(request.getPublication());
        Assertions.assertThat(captor.getValue().getAuteur()).isSameAs(auteur);
        Assertions.assertThat(captor.getValue().getEditeur()).isSameAs(editeur);
        Assertions.assertThat(captor.getValue().getCollection()).isNull();
    }

    @Test
    void shouldUpdateLivre() {
        // Given
        Mockito.when(livreRepository.findById("livre-id")).thenReturn(Optional.of(livre));
        Mockito.when(auteurRepository.getReferenceById("auteur-id")).thenReturn(auteur);
        Mockito.when(editeurRepository.getReferenceById("editeur-id")).thenReturn(editeur);
        Mockito.when(livreRepository.save(ArgumentMatchers.any(Livre.class))).thenReturn(livre);

        request.setNom("Germinal - Édition Spéciale");

        // When
        Livre result = livreService.save("livre-id", request);

        // Then
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getId()).isEqualTo("livre-id");
        Assertions.assertThat(result.getNom()).isEqualTo("Germinal - Édition Spéciale");

        Mockito.verify(livreRepository).findById("livre-id");
        Mockito.verify(livreRepository).save(ArgumentMatchers.any(Livre.class));
    }

    @Test
    void shouldDeleteLivre() {
        // Given
        String id = "livre-id";

        // When
        livreService.deleteById(id);

        // Then
        Mockito.verify(livreRepository).deleteById(id);
    }
}
