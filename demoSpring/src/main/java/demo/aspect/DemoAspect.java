package demo.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class DemoAspect {

	@Pointcut("execution(public String demo.composant.Game.demoMetier2())")
	public void monPointCutCustom() {}
	
	@Before("execution(public * demo.composant.Game.demoMetier*())")
	public void seLanceAvantLes2Demos() 
	{
		System.out.println("Methode dans demoAspect qui doit se lancer avant les demo d'un objet GAME");
	}
	
	@After("monPointCutCustom()")
	public void seLanceApresLaDemo2() 
	{
		System.out.println("----Apres la fonction demo2 (After se lance dans tous les cas)----------");
	}
	
	@AfterReturning(returning="resultatDansDemo2", pointcut = "monPointCutCustom()")
	public void seLanceApresLeSuccesDemo2(String resultatDansDemo2) 
	{
		System.out.println("Le resultat de demo2 est : "+resultatDansDemo2);
		
	}
	
	@AfterThrowing(pointcut = "execution(public String demo.composant.Game.demoMetier3ExceptionPossible())",throwing = "e")
	public void seLanceSiEchec(Exception e) 
	{
		System.out.println(e.getMessage());
	}
	
	@After("execution(public String demo.composant.Game.toString())")
	public void seLanceApresToStringGame() 
	{
		System.out.println("On vient de faire .toString d'un game");
	}
		
}
