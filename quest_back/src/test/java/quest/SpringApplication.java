package quest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import quest.config.AppConfig;

public class SpringApplication {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		ctx.getBeanFactory().createBean(Test.class).run();
		ctx.close();
	}

}
