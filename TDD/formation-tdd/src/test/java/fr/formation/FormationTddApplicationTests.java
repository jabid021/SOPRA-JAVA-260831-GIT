package fr.formation;

import org.junit.jupiter.api.Test;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;

// Cette annotation permet de charger TOUT le contexte de Spring pour le test unitaire
// @SpringBootTest

// Cette annotation permet de ne charger QUE la partie Web (Contrôleurs & Sécurité) du contexte de Spring pour le test unitaire
// @WebMvcTest

// Cette annotation permet de ne charger QUE la partie DATA-JPA (JPA, Hibernate & DATA-JPA) du contexte de Spring pour le test unitaire
// @DataJpaTest
@SpringBootTest
class FormationTddApplicationTests {
	@Test
	void contextLoads() {
	}
}
