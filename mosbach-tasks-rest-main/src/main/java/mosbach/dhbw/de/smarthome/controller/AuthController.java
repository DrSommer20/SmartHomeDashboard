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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mosbach.dhbw.de.smarthome.dto.AuthMessage;
import mosbach.dhbw.de.smarthome.dto.MessageAnswer;
import mosbach.dhbw.de.smarthome.dto.MessageReason;
import mosbach.dhbw.de.smarthome.dto.MessageToken;
import mosbach.dhbw.de.smarthome.dto.UserDTO;
import mosbach.dhbw.de.smarthome.model.User;
import mosbach.dhbw.de.smarthome.service.AuthService;
import mosbach.dhbw.de.smarthome.service.UserService;
import mosbach.dhbw.de.smarthome.service.VerificationService;

@CrossOrigin(origins = "https://smarthomefrontend-surprised-oryx-bl.apps.01.cf.eu01.stackit.cloud", allowedHeaders = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private VerificationService verificationService;

    @GetMapping
    public String getAuth() {
        return "I am alive.";
    }

    @PostMapping(
        consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> signIn(@RequestBody AuthMessage authMessage) { 
        User user = userService.getUserByEmail(authMessage.getEmail());
        if(user != null) {
            if(user.isVerified() == false){
                return new ResponseEntity<MessageAnswer>(new MessageAnswer("User not verified"), HttpStatus.UNAUTHORIZED);
            }
            if(user.checkPassword(authMessage.getPassword()))return new ResponseEntity<MessageToken>(new MessageToken(authService.generateToken(user)), HttpStatus.OK);
            return new ResponseEntity<MessageAnswer>(new MessageAnswer("Wrong credentials"), HttpStatus.UNAUTHORIZED);
        }
        else {
            return new ResponseEntity<MessageAnswer>(new MessageAnswer("User not found"), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping(
        path = "/sign-up",
        consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> signIn(@RequestBody UserDTO userRequest) { 
        User user;
        if(userService.getUserByEmail(userRequest.getEmail()) == null){
            if(userRequest.getPat() != null) user = new User(userRequest.getFirstName(), userRequest.getLastName(), userRequest.getEmail(), userRequest.getPassword(), userRequest.getPat());
            else user = new User(userRequest.getFirstName(), userRequest.getLastName(), userRequest.getEmail(), userRequest.getPassword());
            userService.addUser(user);
            String verificationToken = authService.generateVerificationToken(user);
            verificationService.sendVerificationEmail(user.getEmail(), "https://smarthomefrontend-surprised-oryx-bl.apps.01.cf.eu01.stackit.cloud/public/login-page/verify-email.html?token="+verificationToken);
            return new ResponseEntity<MessageAnswer>(new MessageAnswer("Account created"), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<MessageReason>(new MessageReason("Mail already exists"), HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping
    public ResponseEntity<?> signOut(@RequestHeader("Authorization") String token) { 
        if(authService.isTokenExpired(token)) return new ResponseEntity<MessageAnswer>(new MessageAnswer("Wrong credentials"), HttpStatus.UNAUTHORIZED);
        authService.invalidateToken(token);
        return new ResponseEntity<MessageAnswer>(new MessageAnswer("Logout successful"), HttpStatus.OK);
    }

    @PostMapping("/validate-token")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String token){
        System.out.println(token);
        if(!authService.isTokenExpired(token)){
            System.out.println(token);
            String newtoken = authService.generateToken(userService.getUser(token));
            return new ResponseEntity<MessageToken>(new MessageToken(newtoken),HttpStatus.OK);
        }
        else {
            return new ResponseEntity<MessageAnswer>(new MessageAnswer("Wrong credentials"), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/validate-email/{validateToken}")
    public ResponseEntity<?> validateEmail(@PathVariable String validateToken){
        try {
            if (!authService.isTokenExpired(validateToken)) {
                userService.getUserByEmail(authService.extractUsername(validateToken)).setVerified(true);;
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new MessageAnswer("Wrong credentials"), HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new MessageAnswer("Internal Server Error" + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
}
