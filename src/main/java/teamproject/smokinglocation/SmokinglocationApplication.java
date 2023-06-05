package teamproject.smokinglocation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SmokinglocationApplication {
	public static void main(String[] args) {
		SpringApplication.run(SmokinglocationApplication.class, args);
	}
}
