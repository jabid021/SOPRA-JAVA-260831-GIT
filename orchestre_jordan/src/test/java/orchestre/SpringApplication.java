package orchestre;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import orchestre.config.AppConfig;

public class SpringApplication {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		ctx.getBeanFactory().createBean(Test.class).run();
		ctx.close();
	}

}
