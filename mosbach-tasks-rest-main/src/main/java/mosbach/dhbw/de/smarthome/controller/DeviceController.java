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
import mosbach.dhbw.de.smarthome.dto.ChangeRequest;
import mosbach.dhbw.de.smarthome.dto.DeviceDTO;
import mosbach.dhbw.de.smarthome.dto.DeviceGetResponse;
import mosbach.dhbw.de.smarthome.dto.MessageAnswer;
import mosbach.dhbw.de.smarthome.dto.MessageReason;
import mosbach.dhbw.de.smarthome.dto.smartthings.DeviceST;
import mosbach.dhbw.de.smarthome.model.Device;
import mosbach.dhbw.de.smarthome.model.User;
import mosbach.dhbw.de.smarthome.service.AuthService;
import mosbach.dhbw.de.smarthome.service.DeviceService;
import mosbach.dhbw.de.smarthome.service.SmartThings;

@CrossOrigin(origins = "https://smarthomefrontend-surprised-oryx-bl.apps.01.cf.eu01.stackit.cloud", allowedHeaders = "*")
@RestController
@RequestMapping("/api/device")
public class DeviceController {
    
    @GetMapping
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
            return new ResponseEntity<MessageReason>(new MessageReason("Wrong Credentials"), HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping(
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
            return new ResponseEntity<MessageReason>(new MessageReason("Wrong Credentials"), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDevice(@PathVariable String id, @RequestHeader("Authorization") String token) {
        User user = AuthService.getUser(token);
        if(user != null){
            Device device = DeviceService.getDeviceById(id, user);
            if(device == null) return new ResponseEntity<MessageReason>(new MessageReason("Device not found"), HttpStatus.NOT_FOUND);
            return new ResponseEntity<DeviceGetResponse>(new DeviceGetResponse(device), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<MessageReason>(new MessageReason("Wrong Credentials"), HttpStatus.UNAUTHORIZED);
        }
    } 

    
    @DeleteMapping(
        path = "/{id}",
        consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> deleteDevice(@PathVariable String id, @RequestHeader("Authorization") String token) { 
        User user = AuthService.getUser(token);
        if(user != null){
            boolean result = DeviceService.deleteDevice(id, user);
            if(!result) return new ResponseEntity<MessageReason>(new MessageReason("Device not found"), HttpStatus.NOT_FOUND);
            return new ResponseEntity<MessageAnswer>(new MessageAnswer("Device deleted"), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<MessageReason>(new MessageReason("Wrong Credential"), HttpStatus.UNAUTHORIZED);
        }
    } 

    @PutMapping(
        path = "/{id}",
        consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> changeDevice(@RequestHeader("Authorization") String token, @PathVariable String id, @RequestBody ChangeRequest changeRequest) {
        User user = AuthService.getUser(token);


        if (user != null) {
            Device device = DeviceService.getDeviceById(id, user);
            if(device == null){
                return new ResponseEntity<MessageReason>(new MessageReason("Device not found"), HttpStatus.NOT_FOUND);
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
            return new ResponseEntity<MessageReason>(new MessageReason("Wrong Credentials"), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/smartthings")
    public ResponseEntity<?> getAllSmartThingsDevices(@RequestHeader("Authorization") String token) {
        User user = AuthService.getUser(token);
        List<DeviceGetResponse> deviceGetResponse = new ArrayList<>();
        if(user != null){
            if(user.getPat().isBlank()){
                return new ResponseEntity<>(new MessageReason("No PAT found"), HttpStatus.NOT_FOUND);
            }
            for(DeviceST deviceST : SmartThings.getAllDevices(user.getPat()).getItems()){ 
                if(DeviceService.getDeviceById(deviceST.getDeviceId(), user) == null ) deviceGetResponse.add(new DeviceGetResponse(deviceST.getDeviceId(), deviceST.getLabel(), "", "", "", ""));
            }
            return new ResponseEntity<>(new AllDevices(deviceGetResponse), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(new MessageReason("Wrong Credentials"), HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/{id}/health-check")
    public ResponseEntity<?> GetHealth(@PathVariable String id, @RequestHeader("Authorization") String token) {
        User user = AuthService.getUser(token);
        if(user != null){
            if(user.getPat().isBlank()){
                return new ResponseEntity<>(new MessageReason("No PAT found"), HttpStatus.NOT_FOUND);
            }
            if(DeviceService.getDeviceById(id, user) != null){
                if(SmartThings.isOnline(id, user.getPat())) return new ResponseEntity<>(new MessageAnswer("Online"), HttpStatus.OK);
                else return new ResponseEntity<>(new MessageAnswer("Offline"), HttpStatus.OK);  
            }
            else return new  ResponseEntity<>(new MessageReason("Device not found"), HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(new MessageReason("Wrong Credentials"), HttpStatus.UNAUTHORIZED);
        }
    }
    
    @PostMapping("/{id}/switch/{action}")
    public ResponseEntity<?> setDeviceSwitchStatus(@PathVariable String id, @PathVariable String action, @RequestHeader("Authorization") String token) {
        User user = AuthService.getUser(token);
        if(user != null){
            Device device = DeviceService.getDeviceById(id, user);
            if(device != null){
                if(user.getPat().isBlank()){
                    return new ResponseEntity<>(new MessageReason("No PAT found"), HttpStatus.NOT_FOUND);
                }
                if(SmartThings.setDeviceStatus(action,id, "switch",user.getPat())) {
                    if(SmartThings.isSwitchOn(id, user.getPat())) device.setState("On");
                    else device.setState("Off");

                    return new ResponseEntity<>(new MessageAnswer("Accepted"), HttpStatus.OK);
                }
                else return new ResponseEntity<>(new MessageAnswer("Connection error"), HttpStatus.BAD_REQUEST);
            }
            else return new  ResponseEntity<>(new MessageReason("Device not found"), HttpStatus.NOT_FOUND);
        }
        else{
            return new ResponseEntity<>(new MessageReason("Wrong Credentials"), HttpStatus.UNAUTHORIZED);
        }

    }
    
    
}
