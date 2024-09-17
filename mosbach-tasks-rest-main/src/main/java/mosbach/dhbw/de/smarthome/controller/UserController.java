package mosbach.dhbw.de.smarthome.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mosbach.dhbw.de.smarthome.dto.ChangeRequest;
import mosbach.dhbw.de.smarthome.dto.MessageAnswer;
import mosbach.dhbw.de.smarthome.dto.MessageReason;
import mosbach.dhbw.de.smarthome.dto.UserDTO;
import mosbach.dhbw.de.smarthome.model.User;
import mosbach.dhbw.de.smarthome.service.AuthService;
import mosbach.dhbw.de.smarthome.service.UserService;

@CrossOrigin(origins = "https://smarthomefrontend-surprised-oryx-bl.apps.01.cf.eu01.stackit.cloud", allowedHeaders = "*")
@RestController
@RequestMapping("/api/user")
public class UserController {
    
    @GetMapping
    public ResponseEntity<?> getUser(@RequestHeader("Authorization") String token) {
        User user = AuthService.getUser(token);
        if(user != null){
            return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<MessageReason>(new MessageReason("Wrong Credentials"), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping(
        consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> createUser(@RequestBody UserDTO userRequest) {
        User user = null;
        if(userRequest.getPat() != null) user = new User(userRequest.getFirstName(), userRequest.getLastName(), userRequest.getEmail(), userRequest.getPasswort(), userRequest.getPat());
        else user = new User(userRequest.getFirstName(), userRequest.getLastName(), userRequest.getEmail(), userRequest.getPasswort());
        if(UserService.getUserByEmail(user.getEmail()) == null){
            UserService.addUser(user);
            return new ResponseEntity<MessageAnswer>(new MessageAnswer("Account created"), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<MessageReason>(new MessageReason("Mail already exists"), HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping(
        consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> deleteUser(@RequestHeader("Authorization") String token) {   
        User user = AuthService.getUser(token);     
        if(user != null){
            UserService.deleteUser(user.getEmail());
            return new ResponseEntity<MessageAnswer>(new MessageAnswer("Account deleted"), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<MessageReason>(new MessageReason("Wrong Credentials"), HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping
    public ResponseEntity<?> changeUser(@RequestHeader("Authorization") String token, @RequestBody ChangeRequest changeRequest) {
        User user = AuthService.getUser(token);
        if (user != null) {
            switch(changeRequest.getField()){
                case "firstName":
                    user.setFirstName(changeRequest.getNewValue());
                    break;
                case "lastName":
                    user.setLastName(changeRequest.getNewValue());
                    break;
                case "email":
                    user.setEmail(changeRequest.getNewValue());
                    break;
                case "passwort":
                    user.setPasswort(changeRequest.getNewValue());
                    break;
                case "pat":
                    user.setPat(changeRequest.getNewValue());
                    break;
                default:
                    return new ResponseEntity<MessageReason>(new MessageReason("Field not available"), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<MessageAnswer>(new MessageAnswer("Account updated"), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<MessageReason>(new MessageReason("Wrong Credentials"), HttpStatus.UNAUTHORIZED);
        }
    }

}