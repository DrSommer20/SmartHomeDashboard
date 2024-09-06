package mosbach.dhbw.de.smarthome.controller;

import org.aspectj.bridge.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import mosbach.dhbw.de.smarthome.model.AuthMessage;
import mosbach.dhbw.de.smarthome.model.MessageAnswer;
import mosbach.dhbw.de.smarthome.model.MessageReason;
import mosbach.dhbw.de.smarthome.model.MessageToken;


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

    @PostMapping(
        path = "/auth",
        consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> signIn(@RequestBody AuthMessage authMessage) {
        if(authMessage.getEmail().equals("test") && authMessage.getPasswort().equals("test")) {
            return new ResponseEntity<MessageToken>(new MessageToken("1234567890"), HttpStatus.OK);
        } else {
            return new ResponseEntity<MessageAnswer>(new MessageAnswer("Wrong credentials"), HttpStatus.UNAUTHORIZED);
        }
    }
    
    @DeleteMapping(
        path = "/auth",
        consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> signOut(@RequestBody MessageToken messageToken) {
        if(messageToken.getToken() == "1234567890") {
            return new ResponseEntity<MessageAnswer>(new MessageAnswer("Logout successful"), HttpStatus.OK);
        } else {
            return new ResponseEntity<MessageReason>(new MessageReason("Logout failed"), HttpStatus.UNAUTHORIZED);
        }
     }
}