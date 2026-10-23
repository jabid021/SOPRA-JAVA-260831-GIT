package quest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@Configuration
@ComponentScan({"quest.controller","quest.restcontroller"})
@EnableWebMvc
@Import(AppConfig.class)
public class WebConfig  implements WebMvcConfigurer {

	public void addResourceHandlers(ResourceHandlerRegistry registry ) 
	{
		registry.addResourceHandler ("/assets/**").addResourceLocations("/assets/");
		registry.addResourceHandler ("/style.css").addResourceLocations("/style.css");
	}
	
	@Bean
	public UrlBasedViewResolver viewResolver() {
		UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		//viewResolver.setSuffix(".jsp");
		viewResolver.setViewClass(JstlView.class);
		return viewResolver;
	}
}
