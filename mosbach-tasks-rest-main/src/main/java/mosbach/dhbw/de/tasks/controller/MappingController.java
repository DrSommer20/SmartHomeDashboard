package mosbach.dhbw.de.tasks.controller;

import mosbach.dhbw.de.tasks.model.MessageAnswer;
import mosbach.dhbw.de.tasks.model.TokenTask;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class MappingController {

    public MappingController() {

    }

    @GetMapping("/auth")
    public String getAuth() {
        return "I am alive.";
    }

//    @GetMapping("/tasks")
//    public String getTasks() {
//        return ""; //TODO: Answer wrong
//    }

    @PostMapping(
            path = "/tasks",
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public MessageAnswer createTasks(@RequestBody TokenTask tokenTask) {
        double gradeDouble = Double.parseDouble(tokenTask.getTask().getGrade());
        String answer = "You were a bit lazy";
        if(gradeDouble < 2.3) answer = "OK you learned";

        return new MessageAnswer("Task created and your result: " + answer);
    }
}