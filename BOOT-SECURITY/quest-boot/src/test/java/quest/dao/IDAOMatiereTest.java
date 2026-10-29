package quest.dao;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import quest.model.Matiere;

@DataJpaTest
public class IDAOMatiereTest {
    @Autowired
    private IDAOMatiere dao;

    @ParameterizedTest
    @CsvSource({
        "ANG,1",
        "SPRING,3"
    })
    @Sql(scripts = "classpath:/create-matiere.sql")
    void shouldFindByLibelleContainingSizeOk(String libelle, int size) {
        // given

        // when
        List<Matiere> matieres = this.dao.findByLibelleContaining(libelle);

        // then
        Assertions.assertNotNull( matieres);
        Assertions.assertEquals(size, matieres.size());
    }
}
