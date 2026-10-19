package demo.aspect;

public class DemoAspect {

	public void seLanceAvantLes2Demos() 
	{
		System.out.println("Methode dans demoAspect qui doit se lancer avant les demo d'un objet GAME");
	}
	
	public void seLanceApresLaDemo2() 
	{
		System.out.println("----Apres la fonction demo2 (After se lance dans tous les cas)----------");
	}
	
	public void seLanceApresLeSuccesDemo2(String resultatDansDemo2) 
	{
		System.out.println("Le resultat de demo2 est : "+resultatDansDemo2);
		
	}
	
	public void seLanceSiEchec(Exception e) 
	{
		System.out.println(e.getMessage());
	}
	
	
	public void seLanceApresToStringGame() 
	{
		System.out.println("On vient de faire .toString d'un game");
	}
		
}
