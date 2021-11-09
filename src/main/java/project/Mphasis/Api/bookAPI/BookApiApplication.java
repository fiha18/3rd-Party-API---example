package project.Mphasis.Api.bookAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.client.RestTemplate;
import project.Mphasis.Api.bookAPI.repository.BookRepository;

@SpringBootApplication
@ComponentScan(basePackages = { "project.Mphasis.Api.bookAPI" })
@EnableMongoRepositories(basePackageClasses = BookRepository.class)
public class BookApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookApiApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
