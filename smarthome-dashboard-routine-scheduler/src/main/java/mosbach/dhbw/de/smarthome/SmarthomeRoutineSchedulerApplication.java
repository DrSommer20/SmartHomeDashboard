package mosbach.dhbw.de.smarthome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import mosbach.dhbw.de.smarthome.service.impl.RoutineServicePostgres;

@SpringBootApplication
public class SmarthomeRoutineSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmarthomeRoutineSchedulerApplication.class, args);
		RoutineServicePostgres.initializeRoutines();		
	}

}
