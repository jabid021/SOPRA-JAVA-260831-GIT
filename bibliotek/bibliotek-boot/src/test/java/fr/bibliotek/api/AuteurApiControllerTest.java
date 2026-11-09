package fr.bibliotek.api;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import fr.bibliotek.api.request.CreateOrUpdateAuteurRequest;
import fr.bibliotek.enumerator.NationaliteEnum;
import fr.bibliotek.model.Auteur;
import fr.bibliotek.service.AuteurService;

@WebMvcTest(AuteurApiController.class)
@WithMockUser
class AuteurApiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuteurService auteurService;

    private Auteur auteur;

    @BeforeEach
    void setUp() {
        auteur = new Auteur();
        auteur.setId("auteur-1");
        auteur.setNom("Rimbaud");
        auteur.setPrenom("Arthur");
        auteur.setNationalite(NationaliteEnum.FR);

    }

    @Test
    void shouldFindAllAuteurs() throws Exception {
        // Given
        Mockito.when(auteurService.findAll()).thenReturn(List.of(auteur));

        // When
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/auteur")
                .contentType(MediaType.APPLICATION_JSON));

        // Then
        result.andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].nom").value("Rimbaud"))
            .andExpect(MockMvcResultMatchers.jsonPath("$[0].prenom").value("Arthur"))
        ;
    }

    @Test
    void shouldFindAuteurById() throws Exception {
        // Given
        Mockito.when(auteurService.findById("auteur-1")).thenReturn(auteur);

        // When
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/auteur/auteur-1")
            .contentType(MediaType.APPLICATION_JSON))
        ;

        // Then
        result.andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("auteur-1"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.nom").value("Rimbaud"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.prenom").value("Arthur"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.nationalite").value("FR"))
        ;
    }

    @Test
    void shouldCreateAuteur() throws Exception {
        // Given
        Mockito.when(auteurService.save(ArgumentMatchers.any(CreateOrUpdateAuteurRequest.class))).thenReturn(auteur);

        // When
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/auteur")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
                {"nom":"Rimbaud","prenom":"Arthur","nationalite":"FR"}
            """)
            .with(SecurityMockMvcRequestPostProcessors.csrf())
        );

        // Then
        result.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("auteur-1"));
    }

    @Test
    void shouldRejectCreateWithMissingFields() throws Exception {
        // Given
        String invalidRequest = "{\"nom\":\"\"}";

        // When
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/auteur")
            .contentType(MediaType.APPLICATION_JSON)
            .content(invalidRequest)
            .with(SecurityMockMvcRequestPostProcessors.csrf())
        );

        // Then
        result.andExpect(MockMvcResultMatchers.status().isBadRequest());
        Mockito.verifyNoInteractions(auteurService);
    }

    @Test
    void shouldUpdateAuteur() throws Exception {
        // Given
        Mockito.when(auteurService.save(ArgumentMatchers.anyString(), ArgumentMatchers.any(CreateOrUpdateAuteurRequest.class))).thenReturn(auteur);

        // When
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.put("/auteur/auteur-1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
                {"nom":"Rimbaud","prenom":"Arthur","nationalite":"FR"}
            """)
            .with(SecurityMockMvcRequestPostProcessors.csrf())
        );

        // Then
        result.andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("auteur-1"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
        ;
    }

    @Test
    void shouldDeleteAuteur() throws Exception {
        // Given
        String id = "auteur-1";

        // When
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.delete("/auteur/{id}", id)
            .contentType(MediaType.APPLICATION_JSON)
            .with(SecurityMockMvcRequestPostProcessors.csrf())
        );

        // Then
        result.andExpect(MockMvcResultMatchers.status().isNoContent());
        Mockito.verify(auteurService).deleteById(id);
    }
}
