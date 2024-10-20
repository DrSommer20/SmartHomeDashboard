package mosbach.dhbw.de.smarthome.graphql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import mosbach.dhbw.de.smarthome.graphql.interceptor.AuthInterceptor;
import mosbach.dhbw.de.smarthome.model.Device;
import mosbach.dhbw.de.smarthome.model.DeviceType;
import mosbach.dhbw.de.smarthome.model.User;
import mosbach.dhbw.de.smarthome.service.api.DeviceService;
import mosbach.dhbw.de.smarthome.service.api.RoomService;

@Controller
public class DeviceController {

    @Autowired
    DeviceService deviceService;

    @Autowired
    RoomService roomService;

    @QueryMapping
    public Device deviceById(@Argument String id) {
        User authenticatedUser = AuthInterceptor.getAuthenticatedUser();
        if (authenticatedUser == null) {
            throw new RuntimeException("User not authenticated");
        }
        return deviceService.getDeviceById(id, authenticatedUser.getUserID());
    }

    @SchemaMapping
    public DeviceType deviceType(Device device) {
        return deviceService.getTypeByID(device.getType().getId());
    }
}
