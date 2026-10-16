package quest;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import quest.context.Singleton;

public class TestJPA {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contextJPA");
		emf.close();
	}

}
