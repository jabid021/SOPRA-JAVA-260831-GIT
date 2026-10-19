package orchestre.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class PublicAspect {

	
	@AfterReturning(pointcut = "execution(public String orchestre.model.Guitariste.toString())",returning = "variable")
	public void afterToStringGuitariste(String variable) 
	{
		System.out.println(variable);
		System.out.println("Le guitariste vient de se presenter");
	}
	
	
	public void installer()
	{
		System.out.println("Le public s'installe");
	}
	
	public void applaudir()
	{
		System.out.println("Le public applaudit");
	}

	public void huer()
	{
		System.out.println("Le public jette des tomates");
	}
	
	
	
}
