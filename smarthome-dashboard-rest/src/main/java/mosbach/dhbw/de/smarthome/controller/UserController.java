package mosbach.dhbw.de.smarthome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
import mosbach.dhbw.de.smarthome.service.api.UserService;

@CrossOrigin(origins = {"https://smarthomefrontend-terrific-wolverine-ur.apps.01.cf.eu01.stackit.cloud/", "https://smarthome-spa.apps.01.cf.eu01.stackit.cloud/"}, allowedHeaders = "*")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    /**
     * Retrieves the user for the authenticated user.
     * 
     * @param token the authorization token
     * @return a ResponseEntity containing the user or an error message
     */
    @GetMapping
    public ResponseEntity<?> getUser(@RequestHeader("Authorization") String token) {
        User user = userService.getUser(token);
        if(user != null){
            return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<MessageReason>(new MessageReason("Wrong Credentials"), HttpStatus.UNAUTHORIZED);
        }
    }
    
    /**
     * Deletes the user for the authenticated user.
     * 
     * @param token the authorization token
     * @return a ResponseEntity containing a message if successful or an error message
     */
    @DeleteMapping()
    public ResponseEntity<?> deleteUser(@RequestHeader("Authorization") String token) {   
        User user = userService.getUser(token);    
        if(user != null){
            userService.deleteUser(user.getEmail());
            return new ResponseEntity<MessageAnswer>(new MessageAnswer("Account deleted"), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<MessageReason>(new MessageReason("Wrong Credentials"), HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Changes the user for the authenticated user.
     * 
     * @param token the authorization token
     * @param changeRequest the change request
     * @return a ResponseEntity containing a message if successful or an error message
     */
    @PutMapping
    public ResponseEntity<?> changeUser(@RequestHeader("Authorization") String token, @RequestBody ChangeRequest changeRequest) {
        User user = userService.getUser(token);
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
                case "password":
                    user.setPassword(changeRequest.getNewValue());
                    break;
                case "pat":
                    user.setPat(changeRequest.getNewValue());
                    break;
                default:
                    return new ResponseEntity<MessageReason>(new MessageReason("Field not available"), HttpStatus.BAD_REQUEST);
            }
            if(userService.updateUser(user)) return new ResponseEntity<MessageAnswer>(new MessageAnswer("Account updated"), HttpStatus.OK);
            else return new ResponseEntity<MessageReason>(new MessageReason("Error updating account"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else {
            return new ResponseEntity<MessageReason>(new MessageReason("Wrong Credentials"), HttpStatus.UNAUTHORIZED);
        }
    }

}
