package fr.formation.restcontroller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

// @SpringBootTest
// @AutoConfigureMockMvc // Permet d'injecter un MockMvc dans le contexte de Spring

@WebMvcTest(controllers = HelloRestController.class)
public class HelloRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser // Simuler un utilisateur connecté
    void shouldHelloStatusOk() throws Exception {
        // given
        String url = "/api/hello";

        // when
        ResultActions result = this.mockMvc.perform(
            MockMvcRequestBuilders.get(url)
        );

        // then
        result.andExpect(MockMvcResultMatchers.status().isOk());
    }
}
