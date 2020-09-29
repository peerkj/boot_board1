package web.project.seok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SeokApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeokApplication.class, args);
	}

}
