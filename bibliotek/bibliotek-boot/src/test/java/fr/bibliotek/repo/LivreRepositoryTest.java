package fr.bibliotek.repo;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import fr.bibliotek.model.Livre;

@DataJpaTest
@Sql("classpath:/sql/livres.sql")
class LivreRepositoryTest {
    @Autowired
    private LivreRepository livreRepository;

    @Autowired
    private AuteurRepository auteurRepository;

    @Autowired
    private EditeurRepository editeurRepository;

    @Test
    void shouldSaveLivreInDatabase() {
        // Given
        Livre livre = new Livre();
        livre.setNom("Le Dernier Jour d'un condamné");
        livre.setPublication(LocalDate.of(1829, 1, 1));
        livre.setAuteur(auteurRepository.getReferenceById("auteur-hugo"));
        livre.setEditeur(editeurRepository.getReferenceById("editeur-hachette"));

        // When
        Livre savedLivre = livreRepository.saveAndFlush(livre);
        String id = savedLivre.getId();

        // Then
        Assertions.assertThat(id).isNotNull();
        Livre foundLivre = livreRepository.findById(id).orElseThrow();
        Assertions.assertThat(foundLivre.getNom()).isEqualTo("Le Dernier Jour d'un condamné");
        Assertions.assertThat(foundLivre.getAuteur().getId()).isEqualTo("auteur-hugo");
        Assertions.assertThat(foundLivre.getEditeur().getId()).isEqualTo("editeur-hachette");
    }

    @Test
    void shouldFindLivreById() {
        // Given
        String id = "livre-miserables";

        // When
        Livre foundLivre = livreRepository.findById(id).orElseThrow();

        // Then
        Assertions.assertThat(foundLivre.getNom()).isEqualTo("Les Misérables");
        Assertions.assertThat(foundLivre.getResume()).isEqualTo("Un grand classique");
        Assertions.assertThat(foundLivre.getPublication()).isEqualTo(LocalDate.of(1862, 4, 3));
        Assertions.assertThat(foundLivre.getAuteur().getId()).isEqualTo("auteur-hugo");
        Assertions.assertThat(foundLivre.getEditeur().getId()).isEqualTo("editeur-hachette");
    }

    @Test
    void shouldFindAllLivres() {
        // Given

        // When
        var livres = livreRepository.findAll();

        // Then
        Assertions.assertThat(livres)
            .extracting(Livre::getNom)
            .containsExactlyInAnyOrder("Les Misérables", "Notre-Dame de Paris")
        ;
    }

    @Test
    void shouldUpdateLivre() {
        // Given
        Livre livre = livreRepository.findById("livre-miserables").orElseThrow();
        livre.setNom("Les Misérables - Updated");
        livre.setResume("Un grand classique réédité");

        // When
        livreRepository.saveAndFlush(livre);

        // Then
        Livre updatedLivre = livreRepository.findById("livre-miserables").orElseThrow();
        Assertions.assertThat(updatedLivre.getNom()).isEqualTo("Les Misérables - Updated");
        Assertions.assertThat(updatedLivre.getResume()).isEqualTo("Un grand classique réédité");
    }

    @Test
    void shouldDeleteLivre() {
        // Given
        String id = "livre-miserables";

        // When
        livreRepository.deleteById(id);
        livreRepository.flush();

        // Then
        Assertions.assertThat(livreRepository.findById(id)).isEmpty();
        Assertions.assertThat(livreRepository.findById("livre-notre-dame")).isPresent();
    }

    @Test
    void shouldFindLivreWithoutCollection() {
        // Given
        String id = "livre-miserables";

        // When
        Livre foundLivre = livreRepository.findById(id).orElseThrow();

        // Then
        Assertions.assertThat(foundLivre.getCollection()).isNull();
    }
}
