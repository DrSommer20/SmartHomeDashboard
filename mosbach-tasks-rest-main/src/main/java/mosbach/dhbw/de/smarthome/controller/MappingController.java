package mosbach.dhbw.de.smarthome.controller;

import java.util.List;
import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import mosbach.dhbw.de.smarthome.dto.AllDevices;
import mosbach.dhbw.de.smarthome.dto.AuthMessage;
import mosbach.dhbw.de.smarthome.dto.ChangeRequest;
import mosbach.dhbw.de.smarthome.dto.DeviceCreateRequest;
import mosbach.dhbw.de.smarthome.dto.DeviceGetResponse;
import mosbach.dhbw.de.smarthome.dto.MailTokenRequest;
import mosbach.dhbw.de.smarthome.dto.MessageAnswer;
import mosbach.dhbw.de.smarthome.dto.MessageReason;
import mosbach.dhbw.de.smarthome.dto.MessageToken;
import mosbach.dhbw.de.smarthome.dto.RoutinePostRequest;
import mosbach.dhbw.de.smarthome.dto.UserDTO;
import mosbach.dhbw.de.smarthome.model.Device;
import mosbach.dhbw.de.smarthome.model.User;
import mosbach.dhbw.de.smarthome.service.DeviceService;
import mosbach.dhbw.de.smarthome.service.UserService;

import org.springframework.web.bind.annotation.PathVariable;


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
    public ResponseEntity<?> getUser(@RequestBody MailTokenRequest mailTokenRequest) {
        User user = UserService.getUserByEmail(mailTokenRequest.getEmail());
        if(mailTokenRequest.getToken() == "123456" && user != null){
            return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.OK);
        }
        else if (user == null) {
            return new ResponseEntity<MessageReason>(new MessageReason("User not found"), HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity<MessageReason>(new MessageReason("Wrong Credetials"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(
        path = "/user",
        consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> createUser(@RequestBody UserDTO userRequest) {
        User user = new User(userRequest.getFirstName(), userRequest.getLastName(), userRequest.getEmail(), userRequest.getPasswort());
        if(UserService.getUserByEmail(user.getEmail()) == null){
            UserService.addUser(user);
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
    public ResponseEntity<?> deleteUser(@RequestBody MailTokenRequest mailTokenRequest) {        
        if(mailTokenRequest.getToken() == "123456" && UserService.getUserByEmail(mailTokenRequest.getEmail()) != null){
            UserService.deleteUser(mailTokenRequest.getEmail());
            return new ResponseEntity<MessageAnswer>(new MessageAnswer("Account deleted"), HttpStatus.OK);
        }
        else if(UserService.getUserByEmail(mailTokenRequest.getEmail()) == null){
            return new ResponseEntity<MessageReason>(new MessageReason("User not found"), HttpStatus.BAD_REQUEST);
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
    public ResponseEntity<?> getAllDevices(@RequestBody MessageToken messageToken) { 
        if(messageToken.getToken() == "123456"){
            List<DeviceGetResponse> devicesDTO = new ArrayList<>();
            List<Device> devices = DeviceService.getDevices(messageToken.getToken());

            for (Device device : devices) {
                devicesDTO.add(new DeviceGetResponse(device));
            }
            return new ResponseEntity<AllDevices>(new AllDevices(devicesDTO), HttpStatus.OK);
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
            Device device = new Device(createRequest.getDevice().getName(), createRequest.getDevice().getType(), createRequest.getDevice().getLocation());
            DeviceService.addDevice(device, createRequest.getToken());
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
    public ResponseEntity<?> getDevice(@PathVariable String id, @RequestBody MessageToken tokenRequest) {
        Device device = DeviceService.getDeviceById(id, tokenRequest.getToken());
        if(tokenRequest.getToken() == "123456" && device != null){
            return new ResponseEntity<DeviceGetResponse>(new DeviceGetResponse(device), HttpStatus.OK);
        }
        else if(device == null){
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
    public ResponseEntity<?> deleteDevice(@PathVariable String id, @RequestBody MessageToken tokenRequest) { 
        Device device = DeviceService.getDeviceById(id, tokenRequest.getToken());
        if(tokenRequest.getToken() == "123456" && device != null){
            DeviceService.deleteDevice(Integer.parseInt(id), tokenRequest.getToken());
            return new ResponseEntity<MessageAnswer>(new MessageAnswer("Device deleted"), HttpStatus.OK);
        }
        else if(device == null){
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
    public ResponseEntity<?> changedevice(@PathVariable String id, @RequestBody ChangeRequest changeRequest) {
        Device device = DeviceService.getDeviceById(id, changeRequest.getToken());

        
        if (changeRequest.getToken() == "123456" && device != null) {
            switch(changeRequest.getChange().getField()){
                case "name":
                    device.setName(changeRequest.getChange().getNewValue());
                    break;
                case "type":
                    device.setType(changeRequest.getChange().getNewValue());
                    break;
                case "location":
                    device.setLocation(changeRequest.getChange().getNewValue());
                    break;
                case "status":
                    device.setStatus(changeRequest.getChange().getNewValue());
                    break;
                default:
                    return new ResponseEntity<MessageReason>(new MessageReason("Wrong Field"), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<MessageAnswer>(new MessageAnswer("Account updated"), HttpStatus.OK);
        }
        else if(device == null){
            return new ResponseEntity<MessageReason>(new MessageReason("Device not found"), HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity<MessageReason>(new MessageReason("Wrong Credetials"), HttpStatus.BAD_REQUEST);
        }
    }
    
     //######################################################
     //routine
     //######################################################


    @GetMapping(
        path = "/routine",
        consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> getAllRoutines(@RequestBody MessageToken messageToken) { 
        //TODO: Get All Routine Implementation
        return null;
    }

    @PostMapping(
        path = "/routine",
        consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> createRoutine(@RequestBody RoutinePostRequest routinePostRequest) {
        //TODO: Create Routine Implementation
        return null;
    }

    @GetMapping(
        path = "/routine/{id}",
        consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> getRoutine(@PathVariable String id, @RequestBody MessageToken tokenRequest) {
        //TODO: Get Routine Implementation
        return null;
    }

    @DeleteMapping(
        path = "/routine/{id}",
        consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> deleteRoutine(@PathVariable String id, @RequestBody MessageToken tokenRequest) {
        return null;
        //TODO: Delete Routine Implementation
    }
     
    @PutMapping(
        path = "/routine/{id}",
        consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> changeRoutine(@PathVariable String id, @RequestBody ChangeRequest changeRequest) {
        //TODO: Put Routine Implementation
        return null;
    }
}