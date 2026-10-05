package duck.test;

import duck.context.Singleton;
import duck.model.Boutique;
import duck.model.Employe;

public class TestDAO {

	public static void main(String[] args) {
		
		System.out.println("Liste des jeux : ");

		System.out.println(Singleton.getInstance().getDaoJeu().findAll());
		
		System.out.println("Liste des boutiques : ");

		System.out.println(Singleton.getInstance().getDaoBoutique().findAll());
		
		System.out.println("Boutique 1 avec son staff :");
		
		Boutique b = Singleton.getInstance().getDaoBoutique().findByIdWithStaff(1);
		System.out.println(b);
		for(Employe e : b.getStaff()) 
		{
			System.out.println(e);
		}
	}

}
