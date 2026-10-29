package quest.restcontroller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
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

import quest.dao.IDAOMatiere;
import quest.model.Matiere;

@WebMvcTest(controllers = MatiereRestController.class)
public class MatiereRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IDAOMatiere dao;

    @Test
    @WithMockUser
    void shouldFindAllStatusOk() throws Exception {
        // given
        String url = "/api/matiere";

        // when
        ResultActions result = this.mockMvc.perform(
            MockMvcRequestBuilders.get(url)
        );

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk()); // Status OK
        result.andExpect(MockMvcResultMatchers.jsonPath("$").exists()); // On vérifie si y'a bien une liste

        Mockito.verify(this.dao).findAll(); // On vérifie si findAll a été appelée
    }

    @Test
    @WithMockUser
    void shouldCreateStatusOk() throws Exception {
        // given
        String url = "/api/matiere";

        // when
        ResultActions result = this.mockMvc.perform(
            MockMvcRequestBuilders
                .post(url)
                .content("""
                    {
                        "libelle": "TEST DE CREATION"
                    }
                """)
                .contentType(MediaType.APPLICATION_JSON)
                .with(SecurityMockMvcRequestPostProcessors.csrf())
        );

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(this.dao).save(Mockito.any()); // On vérifie si save a été appelée
    }

    @Test
    @WithMockUser
    void shouldCreateStatusOkVersion2() throws Exception {
        // given
        String url = "/api/matiere";
        ArgumentCaptor<Matiere> cptMatiere = ArgumentCaptor.captor();

        // when
        ResultActions result = this.mockMvc.perform(
            MockMvcRequestBuilders
                .post(url)
                .content("""
                    {
                        "libelle": "TEST DE CREATION"
                    }
                """)
                .contentType(MediaType.APPLICATION_JSON)
                .with(SecurityMockMvcRequestPostProcessors.csrf())
        );

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(this.dao).save(cptMatiere.capture()); // On vérifie si save a été appelée

        // On vérifie la matière envoyée dans save
        Matiere savedMatiere = cptMatiere.getValue();
        Assertions.assertEquals("TEST DE CREATION", savedMatiere.getLibelle());
        Assertions.assertNotEquals(0, savedMatiere.getId()); // Après save, l'id n'est plus à 0 car auto-incrémenté
    }

    @Test
    @WithMockUser
    void shouldDeleteByIdStatusOk() throws Exception {
        // given
        int id = 1;
        String url = "/api/matiere/" + id;

        // when
        ResultActions result = this.mockMvc.perform(
            MockMvcRequestBuilders
                .delete(url)
                .with(SecurityMockMvcRequestPostProcessors.csrf())
        );

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(this.dao).deleteById(id); // On vérifie si deleteById a été appelée
    }
}
