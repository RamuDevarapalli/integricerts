package edu.integricert;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import edu.integricert.service.HomeService;

@SpringBootApplication
public class IntegriCertApplication implements CommandLineRunner {

	@Autowired
	private HomeService homeService;

	public static void main(String[] args) {
		SpringApplication.run(IntegriCertApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/**").allowedOrigins("http://localhost:3000", "http://localhost:4000");
				registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE").allowedHeaders("*");
			}
		};
	}

	@Override
	public void run(String... args) {
	//	homeService.loadIntegriCertData("admin");
	}

}
