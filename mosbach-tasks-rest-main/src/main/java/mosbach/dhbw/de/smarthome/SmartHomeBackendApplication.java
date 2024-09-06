package mosbach.dhbw.de.smarthome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class SmartHomeBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartHomeBackendApplication.class, args);
    }

}
