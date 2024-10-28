package mosbach.dhbw.de.smarthome.graphql.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import mosbach.dhbw.de.smarthome.dto.smartthings.AllDevices;
import mosbach.dhbw.de.smarthome.dto.smartthings.DeviceST;
import mosbach.dhbw.de.smarthome.dto.smartthings.GetFullStatusResponse;
import mosbach.dhbw.de.smarthome.graphql.interceptor.AuthInterceptor;
import mosbach.dhbw.de.smarthome.model.Device;
import mosbach.dhbw.de.smarthome.model.DeviceType;
import mosbach.dhbw.de.smarthome.model.Room;
import mosbach.dhbw.de.smarthome.model.User;
import mosbach.dhbw.de.smarthome.service.api.DeviceService;
import mosbach.dhbw.de.smarthome.service.api.RoomService;
import mosbach.dhbw.de.smarthome.service.api.SmartThings;

@Controller
public class DeviceController {

    @Autowired
    DeviceService deviceService;

    @Autowired
    RoomService roomService;

    @Autowired
    SmartThings smartThings;

    @QueryMapping
    public Device deviceById(@Argument String id) {
        User authenticatedUser = AuthInterceptor.getAuthenticatedUser();
        if (authenticatedUser == null) {
            throw new RuntimeException("User not authenticated");
        }
        return deviceService.getDeviceById(id, authenticatedUser.getId());
    }

    @QueryMapping
    public List<Device> allDevices() {
        User authenticatedUser = AuthInterceptor.getAuthenticatedUser();
        if (authenticatedUser == null) {
            throw new RuntimeException("User not authenticated");
        }
        List<Device> devices = deviceService.getDevices(authenticatedUser.getId());
        devices.forEach(device -> {
            // Get full status of each device from SmartThings
            GetFullStatusResponse response = smartThings.getDeviceFullStatus(device.getId(), authenticatedUser.getPat());

            if (response != null) {
                boolean isOn = false;
                boolean isOnline = false;
                String status = response.getSwitch().getSwitch().getValue();
                String state = response.getHealthCheck().getDeviceWatchDeviceStatus().getValue();
                if (state != null) isOnline = state.equals("online");
                if (status != null) isOn = status.equals("on");
                device.setStatus(isOnline ? "Online" : "Offline");
                device.setState(isOn ? "On" : "Off");
            } else {
                device.setStatus("Offline");
                device.setState("Off");
            }
        });
        return devices;
    }

    @QueryMapping
    public DeviceType deviceTypeById(@Argument String id) {
        return deviceService.getTypeByID(Integer.parseInt(id));
    }

    @QueryMapping
    public List<DeviceType> allDeviceTypes() {
        return deviceService.getTypes();
    }

    @QueryMapping
    public List<Device> smartThingsDevices() {
        User authenticatedUser = AuthInterceptor.getAuthenticatedUser();
        if (authenticatedUser == null) {
            throw new RuntimeException("User not authenticated");
        }
        System.out.println(authenticatedUser.getPat());
        AllDevices allDevices = smartThings.getAllDevices(authenticatedUser.getPat());
        for(DeviceST deviceST : allDevices.getItems()){
            System.out.println(deviceST.getDeviceId());
            System.out.println(deviceST.getLabel());
        }

        List<Device> devices = new ArrayList<>();

        for(DeviceST deviceST : allDevices.getItems()){
            Device device = new Device();
            device.setId(deviceST.getDeviceId());
            device.setName(deviceST.getLabel());
            devices.add(device);
        }

        for(Device d : devices){
            System.out.println(d.getId());
            System.out.println(d.getName());
        }
        return devices;
    }

    @MutationMapping
    public Device createDevice(@Argument String id, @Argument String name, @Argument String typeId, @Argument String roomId) {
        User authenticatedUser = AuthInterceptor.getAuthenticatedUser();
        if (authenticatedUser == null) {
            throw new RuntimeException("User not authenticated");
        }
        Device device = new Device();
        device.setId(id);
        device.setName(name);

        DeviceType type = deviceService.getTypeByID(Integer.parseInt(typeId));
        if (type == null) {
            throw new RuntimeException("Device type not found");
        }
        device.setType(type);

        Room room = roomService.getRoomById(Integer.parseInt(roomId), authenticatedUser.getId());
        if (room == null) {
            throw new RuntimeException("Room not found");
        }
        device.setRoom(room);

        deviceService.addDevice(device, authenticatedUser.getId());
        return device;
    }

    @MutationMapping
    public Device updateDevice(@Argument String id, @Argument String name, @Argument String typeId, @Argument String roomId) {
        User authenticatedUser = AuthInterceptor.getAuthenticatedUser();
        if (authenticatedUser == null) {
            throw new RuntimeException("User not authenticated");
        }
        Device device = deviceService.getDeviceById(id, authenticatedUser.getId());
        if (device == null) {
            throw new RuntimeException("Device not found");
        }
        if(name != null){
            device.setName(name);
        }
        if(typeId != null){
            DeviceType type = deviceService.getTypeByID(Integer.parseInt(typeId));
            if (type == null) {
                throw new RuntimeException("Device type not found");
            }
            device.setType(type);
        }
        if (roomId != null) {
            Room room = roomService.getRoomById(Integer.parseInt(roomId), authenticatedUser.getId());
            if (room == null) {
                throw new RuntimeException("Room not found");
            }
            device.setRoom(room);
        }

        deviceService.updateDevice(device, authenticatedUser.getId());
        return device;
    }

    @MutationMapping
    public Device switchDevice(@Argument String id, @Argument String state) {
        User authenticatedUser = AuthInterceptor.getAuthenticatedUser();
        if (authenticatedUser == null) {
            throw new RuntimeException("User not authenticated");
        }
        Device device = deviceService.getDeviceById(id, authenticatedUser.getId());
        if (device == null) {
            throw new RuntimeException("Device not found");
        }
        if(smartThings.setDeviceStatus(state, id, "switch", authenticatedUser.getPat())) {
            device.setState(state);
        }
        return device;
    }

    @MutationMapping
    public boolean deleteDevice(@Argument String id) {
        User authenticatedUser = AuthInterceptor.getAuthenticatedUser();
        if (authenticatedUser == null) {
            throw new RuntimeException("User not authenticated");
        }
        return deviceService.deleteDevice(id, authenticatedUser.getId());
    }
}