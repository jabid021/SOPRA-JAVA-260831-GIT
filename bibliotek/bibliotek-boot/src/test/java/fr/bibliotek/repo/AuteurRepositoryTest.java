package fr.bibliotek.repo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import fr.bibliotek.enumerator.NationaliteEnum;
import fr.bibliotek.model.Auteur;

@DataJpaTest
@Sql("classpath:/sql/auteurs.sql")
class AuteurRepositoryTest {
    @Autowired
    private AuteurRepository auteurRepository;

    @Test
    void shouldSaveAuteurWithAllFields() {
        // Given
        Auteur auteur = new Auteur();
        auteur.setNom("Hugo");
        auteur.setPrenom("Victor");
        auteur.setNationalite(NationaliteEnum.FR);

        // When
        Auteur savedAuteur = auteurRepository.saveAndFlush(auteur);
        String id = savedAuteur.getId();

        // Then
        Assertions.assertThat(id).isNotNull();
        Auteur foundAuteur = auteurRepository.findById(id).orElseThrow();
        Assertions.assertThat(foundAuteur.getNom()).isEqualTo("Hugo");
        Assertions.assertThat(foundAuteur.getPrenom()).isEqualTo("Victor");
        Assertions.assertThat(foundAuteur.getNationalite()).isEqualTo(NationaliteEnum.FR);
    }

    @Test
    void shouldFindAuteurById() {
        // Given
        String id = "auteur-dumas";

        // When
        Auteur foundAuteur = auteurRepository.findById(id).orElseThrow();

        // Then
        Assertions.assertThat(foundAuteur.getNom()).isEqualTo("Dumas");
        Assertions.assertThat(foundAuteur.getPrenom()).isEqualTo("Alexandre");
        Assertions.assertThat(foundAuteur.getNationalite()).isEqualTo(NationaliteEnum.FR);
    }

    @Test
    void shouldFindAllAuteurs() {
        // Given

        // When
        var auteurs = auteurRepository.findAll();

        // Then
        Assertions.assertThat(auteurs)
            .extracting(Auteur::getNom)
            .containsExactlyInAnyOrder("Dumas", "Balzac")
        ;
    }

    @Test
    void shouldDeleteAuteur() {
        // Given
        String id = "auteur-dumas";

        // When
        auteurRepository.deleteById(id);
        auteurRepository.flush();

        // Then
        Assertions.assertThat(auteurRepository.findById(id)).isEmpty();
        Assertions.assertThat(auteurRepository.findById("auteur-balzac")).isPresent();
    }
}
