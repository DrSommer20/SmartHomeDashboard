package mosbach.dhbw.de.smarthome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import mosbach.dhbw.de.smarthome.dto.AuthMessage;
import mosbach.dhbw.de.smarthome.dto.MessageAnswer;
import mosbach.dhbw.de.smarthome.dto.MessageToken;
import mosbach.dhbw.de.smarthome.model.User;
import mosbach.dhbw.de.smarthome.service.AuthService;
import mosbach.dhbw.de.smarthome.service.UserService;

@CrossOrigin(origins = "https://smarthomefrontend-surprised-oryx-bl.apps.01.cf.eu01.stackit.cloud", allowedHeaders = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String getAuth() {
        return "I am alive.";
    }

    @PostMapping(
        consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> signIn(@RequestBody AuthMessage authMessage) { 
        User user = userService.getUserByEmail(authMessage.getEmail());
        if(user != null && user.checkPassword(authMessage.getPassword())) {
            return new ResponseEntity<MessageToken>(new MessageToken(authService.generateToken(user)), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<MessageAnswer>(new MessageAnswer("Wrong credentials"), HttpStatus.UNAUTHORIZED);
        }
    }
    
    @DeleteMapping
    public ResponseEntity<?> signOut(@RequestHeader("Authorization") String token) { //Sign out implementation
        return new ResponseEntity<MessageAnswer>(new MessageAnswer("Logout successful"), HttpStatus.OK);
    }

    @PostMapping("/validate-token")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String token){
        if(authService.isTokenExpired(token)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<MessageAnswer>(new MessageAnswer("Wrong credentials"), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/validate-email/{id}")
    public ResponseEntity<?> validateEmail(@PathVariable String id){
        if(id ==""){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<MessageAnswer>(new MessageAnswer("Wrong credentials"), HttpStatus.UNAUTHORIZED);
        }
    }

    
}
