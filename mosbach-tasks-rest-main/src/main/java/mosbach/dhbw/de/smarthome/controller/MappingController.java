package mosbach.dhbw.de.smarthome.controller;

import java.util.ArrayList;
import java.util.List;

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

import mosbach.dhbw.de.smarthome.dto.AllDevices;
import mosbach.dhbw.de.smarthome.dto.AllRooms;
import mosbach.dhbw.de.smarthome.dto.AuthMessage;
import mosbach.dhbw.de.smarthome.dto.ChangeRequest;
import mosbach.dhbw.de.smarthome.dto.DeviceDTO;
import mosbach.dhbw.de.smarthome.dto.DeviceGetResponse;
import mosbach.dhbw.de.smarthome.dto.MessageAnswer;
import mosbach.dhbw.de.smarthome.dto.MessageReason;
import mosbach.dhbw.de.smarthome.dto.MessageToken;
import mosbach.dhbw.de.smarthome.dto.RoomDTO;
import mosbach.dhbw.de.smarthome.dto.RoutineDTO;
import mosbach.dhbw.de.smarthome.dto.UserDTO;
import mosbach.dhbw.de.smarthome.dto.smartthings.DeviceST;
import mosbach.dhbw.de.smarthome.model.Device;
import mosbach.dhbw.de.smarthome.model.Room;
import mosbach.dhbw.de.smarthome.model.User;
import mosbach.dhbw.de.smarthome.service.AuthService;
import mosbach.dhbw.de.smarthome.service.DeviceService;

import mosbach.dhbw.de.smarthome.service.SmartThings;
import mosbach.dhbw.de.smarthome.service.RoomService;
import mosbach.dhbw.de.smarthome.service.UserService;

@CrossOrigin(origins = "https://smarthomefrontend-surprised-oryx-bl.apps.01.cf.eu01.stackit.cloud", allowedHeaders = "*")
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
            return new ResponseEntity<MessageAnswer>(new MessageAnswer("Wrong credentials"), HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping(
        path = "/auth",
        consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> signOut(@RequestBody MessageToken messageToken) { //Sign out implementation
        User user = AuthService.getUser(getAuth());
        if(user != null) {
            AuthService.removeUser(user);
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
    public ResponseEntity<?> getUser(@RequestHeader("Authorization") String token) {
        User user = AuthService.getUser(token);
        if(user != null){
            return new ResponseEntity<UserDTO>(new UserDTO(user), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<MessageReason>(new MessageReason("Wrong Credentials"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(
        path = "/user",
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
        path = "/user",
        consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> deleteUser(@RequestHeader("Authorization") String token) {   
        User user = AuthService.getUser(token);     
        if(user != null){
            UserService.deleteUser(user.getEmail());
            return new ResponseEntity<MessageAnswer>(new MessageAnswer("Account deleted"), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<MessageReason>(new MessageReason("Wrong Credentials"), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/user")
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
            return new ResponseEntity<MessageReason>(new MessageReason("Wrong Credentials"), HttpStatus.BAD_REQUEST);
        }
    }

    
    //######################################################
     //device
     //######################################################
     @GetMapping("/device")
    public ResponseEntity<?> getAllDevices(@RequestHeader("Authorization") String token) {
        User user = AuthService.getUser(token);
        if(user != null){
            List<DeviceGetResponse> devicesDTO = new ArrayList<>();
            List<Device> devices = DeviceService.getDevices(user);

            for (Device device : devices) {
                devicesDTO.add(new DeviceGetResponse(device));
            }
            return new ResponseEntity<AllDevices>(new AllDevices(devicesDTO), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<MessageReason>(new MessageReason("Wrong Credentials"), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(
        path = "/device",
        consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> createDevice(@RequestHeader("Authorization") String token, @RequestBody DeviceDTO deviceDTO) {
        User user = AuthService.getUser(token);
        if(user != null){
            Device device = new Device(deviceDTO.getDeviceId(),deviceDTO.getName(), deviceDTO.getType(), deviceDTO.getLocation());
            DeviceService.addDevice(device, user);
            return new ResponseEntity<MessageAnswer>(new MessageAnswer("Device created"), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<MessageReason>(new MessageReason("Wrong Credentials"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(
        path = "/device/{id}",
        consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> getDevice(@PathVariable String id, @RequestHeader("Authorization") String token) {
        User user = AuthService.getUser(token);
        if(user != null){
            Device device = DeviceService.getDeviceById(id, user);
            if(device == null) return new ResponseEntity<MessageReason>(new MessageReason("Device not found"), HttpStatus.BAD_REQUEST);
            return new ResponseEntity<DeviceGetResponse>(new DeviceGetResponse(device), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<MessageReason>(new MessageReason("Wrong Credentials"), HttpStatus.BAD_REQUEST);
        }
    } 

    
    @DeleteMapping(
        path = "/device/{id}",
        consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> deleteDevice(@PathVariable String id, @RequestHeader("Authorization") String token) { 
        User user = AuthService.getUser(token);
        if(user != null){
            boolean result = DeviceService.deleteDevice(id, user);
            if(!result) return new ResponseEntity<MessageReason>(new MessageReason("Device not found"), HttpStatus.BAD_REQUEST);
            return new ResponseEntity<MessageAnswer>(new MessageAnswer("Device deleted"), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<MessageReason>(new MessageReason("Wrong Credential"), HttpStatus.BAD_REQUEST);
        }
    } 

    @PutMapping(
        path = "/device/{id}",
        consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> changeDevice(@RequestHeader("Authorization") String token, @PathVariable String id, @RequestBody ChangeRequest changeRequest) {
        User user = AuthService.getUser(token);


        if (user != null) {
            Device device = DeviceService.getDeviceById(id, user);
            if(device == null){
                return new ResponseEntity<MessageReason>(new MessageReason("Device not found"), HttpStatus.BAD_REQUEST);
            }
            switch(changeRequest.getField()){
                case "name":
                    device.setName(changeRequest.getNewValue());
                    break;
                case "type":
                    device.setType(changeRequest.getNewValue());
                    break;
                case "location":
                    device.setLocation(changeRequest.getNewValue());
                    break;
                default:
                    return new ResponseEntity<MessageReason>(new MessageReason("Field not available"), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<MessageAnswer>(new MessageAnswer("Device updated"), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<MessageReason>(new MessageReason("Wrong Credentials"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/device/smartthings")
    public ResponseEntity<?> getAllSmartThingsDevices(@RequestHeader("Authorization") String token) {
        User user = AuthService.getUser(token);
        List<DeviceGetResponse> deviceGetResponse = new ArrayList<>();
        if(user != null){
            if(user.getPat().isBlank()){
                return new ResponseEntity<>(new MessageReason("No PAT found"), HttpStatus.BAD_REQUEST);
            }
            for(DeviceST deviceST : SmartThings.getAllDevices(user.getPat()).getItems()){  //TODO: PAT einpflegen
                if(DeviceService.getDeviceById(deviceST.getDeviceId(), user) == null ) deviceGetResponse.add(new DeviceGetResponse(deviceST.getDeviceId(), deviceST.getLabel(), "", "", ""));
            }
            return new ResponseEntity<>(new AllDevices(deviceGetResponse), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(new MessageReason("Wrong Credentials"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/device/{id}/health-check")
    public ResponseEntity<?> GetHealth(@PathVariable String id, @RequestHeader("Authorization") String token) {
        User user = AuthService.getUser(token);
        if(user != null){
            if(user.getPat().isBlank()){
                return new ResponseEntity<>(new MessageReason("No PAT found"), HttpStatus.BAD_REQUEST);
            }
            if(DeviceService.getDeviceById(id, user) != null){
                if(SmartThings.isOnline(id, user.getPat())) return new ResponseEntity<>(new MessageAnswer("Online"), HttpStatus.OK);
                else return new ResponseEntity<>(new MessageAnswer("Offline"), HttpStatus.OK);  
            }
            else return new  ResponseEntity<>(new MessageReason("Device not found"), HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>(new MessageReason("Wrong Credentials"), HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping("/device/{id}/switch/{action}")
    public ResponseEntity<?> setDeviceSwitchStatus(@PathVariable String id, @PathVariable String action, @RequestHeader("Authorization") String token) {
        User user = AuthService.getUser(token);
        if(user != null){if(DeviceService.getDeviceById(id, user) != null){
            if(user.getPat().isBlank()){
                return new ResponseEntity<>(new MessageReason("No PAT found"), HttpStatus.BAD_REQUEST);
            }
            if(SmartThings.setDeviceStatus(action,id, "switch",user.getPat())) return new ResponseEntity<>(new MessageAnswer("Accepted"), HttpStatus.OK);
            else return new ResponseEntity<>(new MessageAnswer("Connection error"), HttpStatus.BAD_REQUEST);  
        }
            else return new  ResponseEntity<>(new MessageReason("Device not found"), HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>(new MessageReason("Wrong Credentials"), HttpStatus.BAD_REQUEST);
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
    public ResponseEntity<?> createRoutine(@RequestBody RoutineDTO routinePostRequest) {
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

    //######################################################
    //Rooms
    //######################################################

    @GetMapping(
        path = "/room"
    )
    public ResponseEntity<?> getAllRooms(@RequestHeader("Authorization") String token) { 
        User user = AuthService.getUser(token);
        if(user != null){
            List<Room> rooms = RoomService.getRooms(user);
            List<RoomDTO> roomDTOs = new ArrayList<>();

            for(Room room : rooms){
                roomDTOs.add(new RoomDTO(room));
            }
            return new ResponseEntity<AllRooms>(new AllRooms(roomDTOs), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<MessageReason>(new MessageReason("Wrong Credentials"), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(
        path = "/room",
        consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> createRoom(@RequestHeader("Authorization") String token, @RequestBody RoomDTO roomDTO) {
        User user = AuthService.getUser(token);
        if(user != null){
            Room room = new Room(roomDTO.getName());
            RoomService.addRoom(room, user);
            return new ResponseEntity<MessageAnswer>(new MessageAnswer("Room created"), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<MessageReason>(new MessageReason("Wrong Credentials"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(
        path = "/room/{id}"
    )
    public ResponseEntity<?> getRoom(@PathVariable String id, @RequestHeader("Authorization") String token) {
        User user = AuthService.getUser(token);
        if(user != null){
            Room room = RoomService.getRoomById(id, user);
            if(room == null) return new ResponseEntity<MessageReason>(new MessageReason("Room not found"), HttpStatus.BAD_REQUEST);
            return new ResponseEntity<RoomDTO>(new RoomDTO(room), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<MessageReason>(new MessageReason("Wrong Credentials"), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(
        path = "/room/{id}",
        consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> deleteRoom(@PathVariable String id, @RequestHeader("Authorization") String token) {
        User user = AuthService.getUser(token);
        if(user != null){
            boolean result = RoomService.removeRoom(id, user);
            if(!result){
                return new ResponseEntity<MessageReason>(new MessageReason("Room not found"), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<MessageAnswer>(new MessageAnswer("Room deleted"), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<MessageReason>(new MessageReason("Wrong Credential"), HttpStatus.BAD_REQUEST);
        }
    }
     
    @PutMapping(
        path = "/room/{id}",
        consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> changeRoom(@RequestHeader("Authorization") String token, @PathVariable String id, @RequestBody ChangeRequest changeRequest) {
        User user = AuthService.getUser(token);


        if (user != null) {
            Room room = RoomService.getRoomById(id, user);
            if(room == null){
                return new ResponseEntity<MessageReason>(new MessageReason("Room not found"), HttpStatus.BAD_REQUEST);
            }
            switch(changeRequest.getField()){
                case "name":
                    room.setName(changeRequest.getNewValue());
                    break;
                default:
                    return new ResponseEntity<MessageReason>(new MessageReason("Field not available"), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<MessageAnswer>(new MessageAnswer("Room updated"), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<MessageReason>(new MessageReason("Wrong Credentials"), HttpStatus.BAD_REQUEST);
        }
    }

}