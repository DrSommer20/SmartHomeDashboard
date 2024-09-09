package mosbach.dhbw.de.smarthome.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import mosbach.dhbw.de.smarthome.model.AuthMessage;
import mosbach.dhbw.de.smarthome.model.DeviceCreateRequest;
import mosbach.dhbw.de.smarthome.model.DeviceGetResponse;
import mosbach.dhbw.de.smarthome.model.MailTokenRequest;
import mosbach.dhbw.de.smarthome.model.MessageAnswer;
import mosbach.dhbw.de.smarthome.model.MessageReason;
import mosbach.dhbw.de.smarthome.model.MessageToken;
import mosbach.dhbw.de.smarthome.model.User;
import mosbach.dhbw.de.smarthome.model.ChangeRequest;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class MappingController {

    public MappingController() {

    }

    
    //######################################################
    //AUTH
    //######################################################

    @GetMapping("/auth")
    public String getAuth() {
        return "I am alive.";
    }

    @PostMapping(
        path = "/auth",
        consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> signIn(@RequestBody AuthMessage authMessage) { //Sign in implementation
        if(authMessage.getEmail().equals("test") && authMessage.getPasswort().equals("test")) {
            return new ResponseEntity<MessageToken>(new MessageToken("1234567890"), HttpStatus.OK);
        } else {
            return new ResponseEntity<MessageAnswer>(new MessageAnswer("Wrong credentials"), HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping(
        path = "/auth",
        consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> signOut(@RequestBody MessageToken messageToken) { //Sign out implementation
        if(messageToken.getToken() == "1234567890") {
            return new ResponseEntity<MessageAnswer>(new MessageAnswer("Logout successful"), HttpStatus.OK);
        } else {
            return new ResponseEntity<MessageReason>(new MessageReason("Logout failed"), HttpStatus.BAD_REQUEST);
        }
     }


     //######################################################
     //user
     //######################################################

    @GetMapping(
        path = "/user",
        consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> getUser(@RequestBody MailTokenRequest mailTokenRequest) { //TODO: Get User Implementation
        if(mailTokenRequest.getToken() == "123456" && mailTokenRequest.getEmail() == "test@test.com"){
            return new ResponseEntity<User>(new User("Tim", "Sommer", "test@test.com", "pw"), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<MessageReason>(new MessageReason("Wrong Credetials"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(
        path = "/user",
        consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> createUser(@RequestBody User user) { //TODO: Create User Implementation
        if(user.getEmail() != "test@test.com"){
            return new ResponseEntity<MessageAnswer>(new MessageAnswer("Account created"), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<MessageReason>(new MessageReason("Mail already exists"), HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping(
        path = "/user",
        consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> deleteUser(@RequestBody MailTokenRequest mailTokenRequest) { //TODO: Delete User Implementation
        if(mailTokenRequest.getToken() == "123456" && mailTokenRequest.getEmail() == "test@test.com"){
            return new ResponseEntity<MessageAnswer>(new MessageAnswer("Account deleted"), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<MessageReason>(new MessageReason("Wrong Credetials"), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/user")
    public ResponseEntity<?> changeUser(@RequestBody ChangeRequest changeRequest) {
        //TODO: process PUT request
        if (changeRequest.getToken() == "123456") {
            return new ResponseEntity<MessageAnswer>(new MessageAnswer("Account updated"), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<MessageReason>(new MessageReason("Wrong Credetials"), HttpStatus.BAD_REQUEST);
        }
    }

    
    //######################################################
     //device
     //######################################################
     @GetMapping(
        path = "/device",
        consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> getAllDevices(@RequestBody MessageToken messageToken) { //TODO: Get Device Implementation
        if(messageToken.getToken() == "123456"){
            return new ResponseEntity<>(new User("Tim", "Sommer", "test@test.com", "pw"), HttpStatus.OK);   //TODO: DeviceListResponse
        }
        else {
            return new ResponseEntity<MessageReason>(new MessageReason("Wrong Credetials"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(
        path = "/device",
        consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> createDevice(@RequestBody DeviceCreateRequest createRequest) { //TODO: Create device Implementation
        if(createRequest.getToken() == "123456"){
            return new ResponseEntity<MessageAnswer>(new MessageAnswer("Device created"), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<MessageReason>(new MessageReason("Wrong Credetials"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(
        path = "/device/{id}",
        consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> getDevice(@PathVariable String id, @RequestBody MessageToken tokenRequest) { //TODO: Delete User Implementation
        if(tokenRequest.getToken() == "123456" && id == "device01"){
            return new ResponseEntity<DeviceGetResponse>(new DeviceGetResponse("device01","Test Device","outlet","bedroom","active"), HttpStatus.OK);
        }
        else if(tokenRequest.getToken() == "123456"){
            return new ResponseEntity<MessageReason>(new MessageReason("Device not found"), HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity<MessageReason>(new MessageReason("Wrong Credetials"), HttpStatus.BAD_REQUEST);
        }
    } 

    
    @DeleteMapping(
        path = "/device/{id}",
        consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> deleteDevice(@PathVariable String id, @RequestBody MessageToken tokenRequest) { //TODO: Delete User Implementation
        if(tokenRequest.getToken() == "123456" && id == "device01"){
            return new ResponseEntity<MessageAnswer>(new MessageAnswer("Device deleted"), HttpStatus.OK);
        }
        else if(tokenRequest.getToken() == "123456"){
            return new ResponseEntity<MessageReason>(new MessageReason("Device not found"), HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity<MessageReason>(new MessageReason("Wrong Credetials"), HttpStatus.BAD_REQUEST);
        }
    } 

    @PutMapping(
        path = "/device/{id}",
        consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> putMethodName(@PathVariable String id, @RequestBody ChangeRequest changeRequest) {
        if (changeRequest.getToken() == "123456" && id == "device01") {
            return new ResponseEntity<MessageAnswer>(new MessageAnswer("Account updated"), HttpStatus.OK);
        }
        else if(changeRequest.getToken() == "123456"){
            return new ResponseEntity<MessageReason>(new MessageReason("Device not found"), HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity<MessageReason>(new MessageReason("Wrong Credetials"), HttpStatus.BAD_REQUEST);
        }
    }
    
     //######################################################
     //routine
     //######################################################

     
}