package fr.bibliotek.security.config;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import fr.bibliotek.api.LivreApiController;
import fr.bibliotek.service.LivreService;

@WebMvcTest(LivreApiController.class)
@Import(SecurityConfig.class)
class SecurityConfigTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtEncoder jwtEncoder;

    @MockitoBean
    private LivreService livreService;

    @Test
    void shouldRequireAuthenticationForLivres() throws Exception {
        // Given
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
            .subject("utilisateur")
            .issuedAt(now)
            .expiresAt(now.plus(1, ChronoUnit.HOURS))
            .claim("scope", "ROLE_USER")
            .build()
        ;

        String token = jwtEncoder.encode(
            JwtEncoderParameters.from(
                JwsHeader.with(MacAlgorithm.HS256).build(),
                claims)
            ).getTokenValue()
        ;

        Mockito.when(livreService.findAll()).thenReturn(List.of());

        // When
        ResultActions anonymous = mockMvc.perform(MockMvcRequestBuilders.get("/livre"));
        ResultActions authenticated = mockMvc.perform(MockMvcRequestBuilders.get("/livre")
            .header("Authorization", "Bearer " + token))
        ;

        // Then
        anonymous.andExpect(MockMvcResultMatchers.status().isUnauthorized());
        authenticated.andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(livreService).findAll();
    }
}
