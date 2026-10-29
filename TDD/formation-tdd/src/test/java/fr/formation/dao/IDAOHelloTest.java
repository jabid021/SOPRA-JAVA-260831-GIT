package fr.formation.dao;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import fr.formation.model.Hello;

// @SpringBootTest
// @Transactional // Permet d'activer des transactions ACID
// @Rollback // Permet d'annuler l'application des requêtes (fonctionne qu'avec @Transactional)

@DataJpaTest // @Transactional & @Rollback sont implicites
public class IDAOHelloTest {
    @Autowired
    private IDAOHello dao;

    @Test
    // BEFORE_TEST_METHOD est déjà par défaut
    // @Sql(statements = "INSERT INTO hello (message) VALUES ('hello!')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:/create-hello.sql")
    void shouldCountOk() {
        // given

        // when
        long result = this.dao.count();

        // then
        Assertions.assertEquals(2, result);
    }

    @Test
    @Sql(scripts = "classpath:/create-hello.sql")
    void shouldFindByIdPresent() {
        // given

        // when
        Optional<Hello> optHello = this.dao.findById(1);

        // then
        Assertions.assertTrue(optHello.isPresent());
        Assertions.assertEquals("hello 1 !", optHello.get().getMessage());
    }
}
