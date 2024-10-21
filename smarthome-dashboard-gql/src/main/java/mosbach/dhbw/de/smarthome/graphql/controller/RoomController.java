package mosbach.dhbw.de.smarthome.graphql.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import mosbach.dhbw.de.smarthome.graphql.interceptor.AuthInterceptor;
import mosbach.dhbw.de.smarthome.model.Room;
import mosbach.dhbw.de.smarthome.model.User;
import mosbach.dhbw.de.smarthome.service.api.RoomService;

@Controller
public class RoomController {

    @Autowired
    private RoomService roomService;

    @QueryMapping
    public Room roomById(@Argument int id) {
        User authenticatedUser = AuthInterceptor.getAuthenticatedUser();
        if (authenticatedUser == null) {
            throw new RuntimeException("User not authenticated");
        }
        return roomService.getRoomById(id, authenticatedUser.getId());
    }

    @QueryMapping
    public List<Room> allRooms() {
        User authenticatedUser = AuthInterceptor.getAuthenticatedUser();
        if (authenticatedUser == null) {
            throw new RuntimeException("User not authenticated");
        }
        return roomService.getRooms(authenticatedUser.getId());
    }

     @MutationMapping
    public Room createRoom(@Argument String name) {
        User authenticatedUser = AuthInterceptor.getAuthenticatedUser();
        if (authenticatedUser == null) {
            throw new RuntimeException("User not authenticated");
        }
        Room room = new Room();
        room.setName(name);
        roomService.addRoom(room, authenticatedUser.getId());
        return room;
    }

    @MutationMapping
    public Room updateRoom(@Argument int id, @Argument String name) {
        User authenticatedUser = AuthInterceptor.getAuthenticatedUser();
        if (authenticatedUser == null) {
            throw new RuntimeException("User not authenticated");
        }
        Room room = roomService.getRoomById(id, authenticatedUser.getId());
        if (room == null) {
            throw new RuntimeException("Room not found");
        }
        room.setName(name);
        roomService.updateRoom(room, authenticatedUser.getId());
        return room;
    }

    @MutationMapping
    public Boolean deleteRoom(@Argument int id) {
        User authenticatedUser = AuthInterceptor.getAuthenticatedUser();
        if (authenticatedUser == null) {
            throw new RuntimeException("User not authenticated");
        }
        return roomService.removeRoom(id, authenticatedUser.getId());
    }
    
}
