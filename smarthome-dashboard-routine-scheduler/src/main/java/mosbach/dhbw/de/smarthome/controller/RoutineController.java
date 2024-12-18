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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mosbach.dhbw.de.smarthome.dto.ActionDTO;
import mosbach.dhbw.de.smarthome.dto.AllRoutines;
import mosbach.dhbw.de.smarthome.dto.MessageReason;
import mosbach.dhbw.de.smarthome.dto.RoutineDTO;
import mosbach.dhbw.de.smarthome.model.Routine;
import mosbach.dhbw.de.smarthome.model.User;
import mosbach.dhbw.de.smarthome.service.api.RoutineService;
import mosbach.dhbw.de.smarthome.service.api.UserService;

@CrossOrigin(origins = {"https://smarthomefrontend-terrific-wolverine-ur.apps.01.cf.eu01.stackit.cloud/", "https://smarthome-spa.apps.01.cf.eu01.stackit.cloud/"}, allowedHeaders = "*")
@RestController
@RequestMapping("/api/intern/routine")
public class RoutineController {

    @Autowired
    private RoutineService routineService;

    @Autowired
    private UserService userService;

    
    /**
     * Retrieves all routines for the authenticated user.
     *
     * @param token The authorization token of the user.
     * @return A ResponseEntity containing all routines if the user is authenticated,
     *         or an error message if the credentials are wrong.
     */
    @GetMapping
    public ResponseEntity<?> getAllRoutines(@RequestHeader("Authorization") String token) { 
        User user = userService.getUser(token);
        if(user != null){
            AllRoutines allRoutines = AllRoutines.convertToDTO(routineService.getRoutines(user.getUserID()));
            return new ResponseEntity<>(allRoutines, HttpStatus.OK);
        }
         return new ResponseEntity<MessageReason>(new MessageReason("Wrong Credentials"), HttpStatus.UNAUTHORIZED);
    }

    /**
     * Creates a new routine.
     *
     * @param token The authorization token of the user.
     * @param routinePostRequest The routine to create.
     * @return A ResponseEntity containing a success message if the routine was created,
     *         or an error message if the credentials were wrong.
     */
    @PostMapping(
        consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> createRoutine(@RequestHeader("Authorization") String token, @RequestBody RoutineDTO routinePostRequest) {
        User user = userService.getUser(token);
        if(user != null){
            Routine routine = RoutineDTO.convertToModel(routinePostRequest, user);
            routineService.addRoutine(user.getUserID(), routine);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<MessageReason>(new MessageReason("Wrong Credentials"), HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Retrieves a routine by its ID.
     * 
     * @param id The ID of the routine to retrieve.
     * @param token The authorization token of the user.
     * @return A ResponseEntity containing the routine if it was found,
     */
    @GetMapping(
        path = "/{id}"
    )
    public ResponseEntity<?> getRoutine(@PathVariable int id, @RequestHeader("Authorization") String token) {
        User user = userService.getUser(token);
        if(user != null){
            Routine routine = routineService.getRoutineByID(id, user.getUserID());
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

    /**
     * Deletes a routine by its ID.
     *
     * @param id The ID of the routine to delete.
     * @param token The authorization token of the user.
     * @return A ResponseEntity containing a success message if the routine was deleted,
     *         or an error message if the routine was not found or the credentials were wrong.
     */
    @DeleteMapping(
        path = "/{id}"
    )
    public ResponseEntity<?> deleteRoutine(@PathVariable int id, @RequestHeader("Authorization") String token) {
        User user = userService.getUser(token);
        if(user != null){
            if(routineService.deleteRoutine(id, user.getUserID())) return new ResponseEntity<>( HttpStatus.OK);
            else return new ResponseEntity<MessageReason>(new MessageReason("Routine not found"), HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<MessageReason>(new MessageReason("Wrong Credentials"), HttpStatus.UNAUTHORIZED);
        }
    }
     
    /**
     * Updates a routine by its ID.
     *
     * @param id The ID of the routine to update.
     * @param token The authorization token of the user.
     * @param changeRequest The change request containing the field to update and the new value.
     * @return A ResponseEntity containing a success message if the routine was updated,
     *         or an error message if the routine was not found or the credentials were wrong.
     */
    @PutMapping(
        path = "/{id}",
        consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> changeRoutine(@PathVariable int id, @RequestHeader("Authorization") String token,@RequestBody RoutineDTO changeRequest) {
        User user = userService.getUser(token);
        if(user != null){
            Routine routine = routineService.getRoutineByID(id, user.getUserID());
            if(routine != null){
                routine.setName(changeRequest.getName());
                routine.setActions(ActionDTO.convertToModel(changeRequest.getActions(), user));
                routine.setTriggerTime(changeRequest.getTrigger().getValue());
                routine.setState(changeRequest.isState());
                routineService.updateRoutine(routine, user.getUserID());
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else{
                return new ResponseEntity<MessageReason>(new MessageReason("Routine not found"), HttpStatus.NOT_FOUND);
            }
        }
        else{
            return new ResponseEntity<MessageReason>(new MessageReason("Wrong Credentials"), HttpStatus.UNAUTHORIZED);
        }

    }

    /**
     * Switches a routine by its ID.
     *
     * @param id The ID of the routine to switch.
     * @param token The authorization token of the user.
     * @param state The state to switch the routine to.
     * @return A ResponseEntity containing a success message if the routine was switched,
     *         or an error message if the routine was not found or the credentials were wrong.
     */
    @PostMapping(
        path = "/{id}/{state}"
    )
    public ResponseEntity<?> switchRoutine(@RequestHeader("Authorization") String token, @PathVariable String state, @PathVariable int id) {
        User user = userService.getUser(token);
        if(user != null){
            if(routineService.switchRoutine(id, state.equals("on"), user.getUserID())){
                return new ResponseEntity<>(HttpStatus.OK);
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
