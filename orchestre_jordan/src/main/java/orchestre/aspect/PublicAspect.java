package orchestre.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class PublicAspect {

	
	@AfterReturning(pointcut = "execution(public String orchestre.model.Guitariste.toString())",returning = "variable")
	public void afterToStringGuitariste(String variable) 
	{
		System.out.println(variable);
		System.out.println("Le guitariste vient de se presenter");
	}
	
	
	@Before("execution(public void orchestre.model.*.jouer())")
	public void installer()
	{
		System.out.println("Le public s'installe");
	}
	
	@AfterReturning("execution(public void orchestre.model.*.jouer())")
	public void applaudir()
	{
		System.out.println("Le public applaudit");
	}

	@AfterThrowing("execution(public void orchestre.model.*.jouer())")
	public void huer()
	{
		System.out.println("Le public jette des tomates");
	}
	
	
	
}
