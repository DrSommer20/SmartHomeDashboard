package mosbach.dhbw.de.smarthome.controller;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import mosbach.dhbw.de.smarthome.dto.AllRooms;
import mosbach.dhbw.de.smarthome.dto.ChangeRequest;
import mosbach.dhbw.de.smarthome.dto.MessageAnswer;
import mosbach.dhbw.de.smarthome.dto.MessageReason;
import mosbach.dhbw.de.smarthome.dto.RoomDTO;
import mosbach.dhbw.de.smarthome.model.Room;
import mosbach.dhbw.de.smarthome.model.User;
import mosbach.dhbw.de.smarthome.service.api.RoomService;
import mosbach.dhbw.de.smarthome.service.api.UserService;

@CrossOrigin(origins = "https://smarthomefrontend-terrific-wolverine-ur.apps.01.cf.eu01.stackit.cloud/", allowedHeaders = "*")
@RestController
@RequestMapping("/api/room")
public class RoomController {
    
    @Autowired
    private RoomService roomService;

    @Autowired
    private UserService userService;
    
    @GetMapping
    public ResponseEntity<?> getAllRooms(@RequestHeader("Authorization") String token) { 
        User user = userService.getUser(token);
        if(user != null){
            List<Room> rooms = roomService.getRooms(user.getUserID());
            List<RoomDTO> roomDTOs = new ArrayList<>();

            for(Room room : rooms){
                roomDTOs.add(new RoomDTO(room));
            }
            return new ResponseEntity<AllRooms>(new AllRooms(roomDTOs), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<MessageReason>(new MessageReason("Wrong Credentials"), HttpStatus.UNAUTHORIZED);
        }

    }

    @PostMapping(
        consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> createRoom(@RequestHeader("Authorization") String token, @RequestBody RoomDTO roomDTO) {
        User user = userService.getUser(token);
        if(user != null){
            Room room = new Room(roomDTO.getName());
            roomService.addRoom(room, user.getUserID());
            return new ResponseEntity<MessageAnswer>(new MessageAnswer("Room created"), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<MessageReason>(new MessageReason("Wrong Credentials"), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRoom(@PathVariable String id, @RequestHeader("Authorization") String token) {
        User user = userService.getUser(token);
        if(user != null){
            Room room = roomService.getRoomById(id, user.getUserID());
            if(room == null) return new ResponseEntity<MessageReason>(new MessageReason("Room not found"), HttpStatus.NOT_FOUND);
            return new ResponseEntity<RoomDTO>(new RoomDTO(room), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<MessageReason>(new MessageReason("Wrong Credentials"), HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable String id, @RequestHeader("Authorization") String token) {
        User user = userService.getUser(token);
        if(user != null){
            boolean result = roomService.removeRoom(id, user.getUserID());
            if(!result){
                return new ResponseEntity<MessageReason>(new MessageReason("Room not found"), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<MessageAnswer>(new MessageAnswer("Room deleted"), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<MessageReason>(new MessageReason("Wrong Credential"), HttpStatus.UNAUTHORIZED);
        }
    }
     
    @PutMapping(
        path = "/{id}",
        consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> changeRoom(@RequestHeader("Authorization") String token, @PathVariable String id, @RequestBody ChangeRequest changeRequest) {
        User user = userService.getUser(token);
        if (user != null) {
            Room room = roomService.getRoomById(id, user.getUserID());
            if(room == null){
                return new ResponseEntity<MessageReason>(new MessageReason("Room not found"), HttpStatus.NOT_FOUND);
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
            return new ResponseEntity<MessageReason>(new MessageReason("Wrong Credentials"), HttpStatus.UNAUTHORIZED);
        }
    }

}
