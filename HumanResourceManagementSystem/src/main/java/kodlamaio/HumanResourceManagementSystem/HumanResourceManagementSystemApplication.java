package kodlamaio.HumanResourceManagementSystem;

import kodlamaio.HumanResourceManagementSystem.business.abstracts.CandidateService;
import kodlamaio.HumanResourceManagementSystem.business.abstracts.UserService;
import kodlamaio.HumanResourceManagementSystem.entities.abstracts.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class HumanResourceManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(HumanResourceManagementSystemApplication.class, args);
	}

	@Bean("passwordEncoder")
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("kodlamaio.HumanResourceManagementSystem"))
				.build();
	}


}
