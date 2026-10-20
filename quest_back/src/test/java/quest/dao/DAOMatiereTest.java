package quest.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import jakarta.transaction.Transactional;
import quest.config.AppConfig;
import quest.model.Matiere;

@SpringJUnitConfig(AppConfig.class)
@Transactional
@Rollback(true) 
public class DAOMatiereTest {

	@Autowired
	IDAOMatiere daoMatiere;
	
	@Test
	@DisplayName("On test l'autowired DAOMatiere")
	public void injectDAOAutowiredOk() 
	{
		//Arrange
		//Act
		//Assert
		assertNotNull(daoMatiere);
	}
	
	
	@Test
	@DisplayName("Test ajout d'une matiere")
	public void insertMatiere() 
	{
		//Arrange
		Matiere m1 = new Matiere(null,"Maths");
		Integer id;
		Matiere matiereBdd;
		//Act
		m1 = daoMatiere.save(m1);
		id=m1.getId();
		matiereBdd = daoMatiere.findById(id).orElse(null);
		//Assert
		assertNotNull(id);
		assertNotNull(matiereBdd);
		assertEquals(matiereBdd.getLibelle(),"Maths");
	}
	
	
	

}
