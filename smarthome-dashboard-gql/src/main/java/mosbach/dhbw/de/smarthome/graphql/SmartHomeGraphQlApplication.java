package mosbach.dhbw.de.smarthome.graphql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = "mosbach.dhbw.de.smarthome")
public class SmartHomeGraphQlApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartHomeGraphQlApplication.class, args);
	}

}
