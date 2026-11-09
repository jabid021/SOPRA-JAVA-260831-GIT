package fr.bibliotek.api;

import java.time.LocalDate;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import fr.bibliotek.api.request.CreateOrUpdateLivreRequest;
import fr.bibliotek.enumerator.NationaliteEnum;
import fr.bibliotek.model.Auteur;
import fr.bibliotek.model.Editeur;
import fr.bibliotek.model.Livre;
import fr.bibliotek.service.LivreService;

@WebMvcTest(LivreApiController.class)
@AutoConfigureMockMvc(addFilters = false) // Supprime la chaine de filtres - supprime donc la sécurité
@WithMockUser
class LivreApiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private LivreService livreService;

    private Livre livre;
    private Auteur auteur;
    private Editeur editeur;

    @BeforeEach
    void setUp() {
        auteur = new Auteur();
        auteur.setId("auteur-1");
        auteur.setNom("Proust");
        auteur.setPrenom("Marcel");
        auteur.setNationalite(NationaliteEnum.FR);

        editeur = new Editeur();
        editeur.setId("editeur-1");
        editeur.setNom("Bernard Grasset");
        editeur.setPays(NationaliteEnum.FR);

        livre = new Livre();
        livre.setId("livre-1");
        livre.setNom("À la recherche du temps perdu");
        livre.setResume("Chef-d'oeuvre de la littérature française");
        livre.setPublication(LocalDate.of(1913, 1, 1));
        livre.setAuteur(auteur);
        livre.setEditeur(editeur);
        livre.setCollection(null);

    }

    @Test
    void shouldFindAllLivres() throws Exception {
        // Given
        Mockito.when(livreService.findAll()).thenReturn(List.of(livre));

        // When
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/livre")
            .contentType(MediaType.APPLICATION_JSON))
        ;

        // Then
        result.andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value("livre-1"))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].nom").value("À la recherche du temps perdu"))
        ;
    }

    @Test
    void shouldFindLivreById() throws Exception {
        // Given
        Mockito.when(livreService.findById("livre-1")).thenReturn(livre);

        // When
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/livre/livre-1")
            .contentType(MediaType.APPLICATION_JSON))
        ;

        // Then
        result.andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("livre-1"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("À la recherche du temps perdu"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.auteurNom").value("Proust"))
        ;
    }

    @Test
    void shouldCreateLivre() throws Exception {
        // Given
        Mockito.when(livreService.save(ArgumentMatchers.any(CreateOrUpdateLivreRequest.class))).thenReturn(livre);

        // When
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/livre")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
                {"nom":"Germinal","publication":"1885-02-25","auteurId":"auteur-1","editeurId":"editeur-1"}
            """)
        );

        // Then
        result.andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("livre-1"))
        ;
    }

    @Test
    void shouldRejectCreateWithInvalidData() throws Exception {
        // Given
        String invalidRequest = "{\"nom\":\"\"}";

        // When
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/livre")
            .contentType(MediaType.APPLICATION_JSON)
            .content(invalidRequest))
        ;

        // Then
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());
        Mockito.verifyNoInteractions(livreService);
    }

    @Test
    void shouldUpdateLivre() throws Exception {
        // Given
        Mockito.when(livreService.save(ArgumentMatchers.anyString(),
                ArgumentMatchers.any(CreateOrUpdateLivreRequest.class))).thenReturn(livre);

        // When
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.put("/livre/livre-1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
                {"nom":"Germinal","publication":"1885-02-25","auteurId":"auteur-1","editeurId":"editeur-1"}
            """)
        );

        // Then
        result.andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("livre-1"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
        ;
    }

    @Test
    void shouldDeleteLivre() throws Exception {
        // Given
        String id = "livre-1";

        // When
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.delete("/livre/{id}", id)
            .contentType(MediaType.APPLICATION_JSON))
        ;

        // Then
        result.andExpect(MockMvcResultMatchers.status().isNoContent());
        Mockito.verify(livreService).deleteById(id);
    }
}
