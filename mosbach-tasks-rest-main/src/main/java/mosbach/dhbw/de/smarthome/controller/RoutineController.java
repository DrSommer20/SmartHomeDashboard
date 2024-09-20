package mosbach.dhbw.de.smarthome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mosbach.dhbw.de.smarthome.dto.ActionDTO;
import mosbach.dhbw.de.smarthome.dto.AllRoutines;
import mosbach.dhbw.de.smarthome.dto.MessageAnswer;
import mosbach.dhbw.de.smarthome.dto.MessageReason;
import mosbach.dhbw.de.smarthome.dto.RoutineDTO;
import mosbach.dhbw.de.smarthome.model.Routine;
import mosbach.dhbw.de.smarthome.model.User;
import mosbach.dhbw.de.smarthome.service.RoutineService;
import mosbach.dhbw.de.smarthome.service.UserService;

@CrossOrigin(origins = "https://smarthomefrontend-surprised-oryx-bl.apps.01.cf.eu01.stackit.cloud", allowedHeaders = "*")
@RestController
@RequestMapping("/api/routine")
public class RoutineController {

    @Autowired
    private RoutineService routineService;

    @Autowired
    private UserService userService;
    
    @GetMapping
    public ResponseEntity<?> getAllRoutines(@RequestHeader("Authorization") String token) { 
        User user = userService.getUser(token);
        if(user != null){
            AllRoutines allRoutines = AllRoutines.convertToDTO(routineService.getRoutines(user));
            return new ResponseEntity<>(allRoutines, HttpStatus.OK);
        }
         return new ResponseEntity<MessageReason>(new MessageReason("Wrong Credentials"), HttpStatus.UNAUTHORIZED);
    }

    @PostMapping(
        consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> createRoutine(@RequestHeader("Authorization") String token, @RequestBody RoutineDTO routinePostRequest) {
        User user = userService.getUser(token);
        if(user != null){
            Routine routine = RoutineDTO.convertToModel(routinePostRequest, user);
            routineService.addRoutine(user, routine);
            return new ResponseEntity<MessageAnswer>(new MessageAnswer("Routine created"),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<MessageReason>(new MessageReason("Wrong Credentials"), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping(
        path = "/{id}",
        consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> getRoutine(@PathVariable String id, @RequestHeader("Authorization") String token) {
        User user = userService.getUser(token);
        if(user != null){
            Routine routine = routineService.getRoutineByID(id, user);
            if(routine != null){
                return new ResponseEntity<>(RoutineDTO.convertToDTO(routine), HttpStatus.OK);
            }
            else{
                return new ResponseEntity<MessageReason>(new MessageReason("Routine not found"), HttpStatus.NOT_FOUND);
            }
        }
        else{
            return new ResponseEntity<MessageReason>(new MessageReason("Wrong Credentials"), HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping(
        path = "/{id}",
        consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> deleteRoutine(@PathVariable String id, @RequestHeader("Authorization") String token) {
        User user = userService.getUser(token);
        if(user != null){
            if(routineService.deleteRoutine(id, user)){
                return new ResponseEntity<MessageAnswer>(new MessageAnswer("Routine deleted"), HttpStatus.OK);
            }
            else{
                return new ResponseEntity<MessageReason>(new MessageReason("Routine not found"), HttpStatus.NOT_FOUND);
            }
        }
        else{
            return new ResponseEntity<MessageReason>(new MessageReason("Wrong Credentials"), HttpStatus.UNAUTHORIZED);
        }
    }
     
    @PutMapping(
        path = "/{id}",
        consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> changeRoutine(@PathVariable String id, @RequestHeader("Authorization") String token,@RequestBody RoutineDTO changeRequest) {
        User user = userService.getUser(token);
        if(user != null){
            Routine routine = routineService.getRoutineByID(id, user);
            if(routine != null){
                routine.setName(changeRequest.getName());
                routine.setActions(ActionDTO.convertToModel(changeRequest.getActions(), user));
                routine.setTriggerTime(changeRequest.getTrigger().getValue());
                routine.setState(changeRequest.isState());
                routine.refresh();
                return new ResponseEntity<MessageAnswer>(new MessageAnswer("Routine changed"), HttpStatus.OK);
            }
            else{
                return new ResponseEntity<MessageReason>(new MessageReason("Routine not found"), HttpStatus.NOT_FOUND);
            }
        }
        else{
            return new ResponseEntity<MessageReason>(new MessageReason("Wrong Credentials"), HttpStatus.UNAUTHORIZED);
        }

    }

}
