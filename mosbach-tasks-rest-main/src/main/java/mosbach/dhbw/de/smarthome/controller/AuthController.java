package mosbach.dhbw.de.smarthome.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mosbach.dhbw.de.smarthome.dto.AuthMessage;
import mosbach.dhbw.de.smarthome.dto.MessageAnswer;
import mosbach.dhbw.de.smarthome.dto.MessageReason;
import mosbach.dhbw.de.smarthome.dto.MessageToken;
import mosbach.dhbw.de.smarthome.model.User;
import mosbach.dhbw.de.smarthome.service.AuthService;
import mosbach.dhbw.de.smarthome.service.UserService;

@CrossOrigin(origins = "https://smarthomefrontend-surprised-oryx-bl.apps.01.cf.eu01.stackit.cloud", allowedHeaders = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @GetMapping
    public String getAuth() {
        return "I am alive.";
    }

    @PostMapping(
        consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> signIn(@RequestBody AuthMessage authMessage) { //Sign in implementation
        User user = UserService.getUserByEmail(authMessage.getEmail());
        if(user != null && user.getPasswort().equals(authMessage.getPassword())) {
            if(AuthService.checkUser(user)){
                return new ResponseEntity<MessageToken>(new MessageToken(AuthService.getToken(user)), HttpStatus.OK);
            } else {
                String token = AuthService.addUser(user);
                return new ResponseEntity<MessageToken>(new MessageToken(token), HttpStatus.OK);
            }
        }
        else {
            return new ResponseEntity<MessageAnswer>(new MessageAnswer("Wrong credentials"), HttpStatus.UNAUTHORIZED);
        }
    }
    
    @DeleteMapping(
        consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> signOut(@RequestBody MessageToken messageToken) { //Sign out implementation
        User user = AuthService.getUser(getAuth());
        if(user != null) {
            AuthService.removeUser(user);
            return new ResponseEntity<MessageAnswer>(new MessageAnswer("Logout successful"), HttpStatus.OK);
        } else {
            return new ResponseEntity<MessageReason>(new MessageReason("Logout failed"), HttpStatus.UNAUTHORIZED);
        }
     }

    
}
