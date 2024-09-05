package mosbach.dhbw.de.tasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class TasksBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(TasksBackendApplication.class, args);
    }

}
