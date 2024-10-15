package mosbach.dhbw.de.smarthome.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import mosbach.dhbw.de.smarthome.model.Room;
import mosbach.dhbw.de.smarthome.model.User;
import mosbach.dhbw.de.smarthome.service.api.RoomService;
import mosbach.dhbw.de.smarthome.service.api.UserService;

public class RoomServiceImpl implements RoomService {
    private static HashMap<User, HashSet<Room>> roomMap = new HashMap<User, HashSet<Room>>();

    @Autowired
    private UserService userService;

    public List<Room> getRooms(Integer userID) {
        User user = userService.getUserById(userID);
        if (roomMap.containsKey(user)) return new ArrayList<Room>(roomMap.get(user));
        return null;
    }

    public Room getRoomById(String roomId, Integer userID) {
        User user = userService.getUserById(userID);
        if(roomMap.get(user) == null) return null;
        return roomMap
                .get(user)
                .stream()
                .filter(room -> room.getRoomId() == Integer.parseInt(roomId))
                .findFirst()
                .orElse(null);
    }

    public void addRoom(Room room, Integer userID) {
        User user = userService.getUserById(userID);
        if (roomMap.containsKey(user)) {
            roomMap.get(user).add(room);
        } else {
            HashSet<Room> rooms = new HashSet<Room>();
            rooms.add(room);
            roomMap.put(user, rooms);
        }
    }

    public void updateRoom(Room room, Integer userID) {
        Room roomOld = getRoomById(room.getRoomId()+"", userID);
        roomOld.setName(room.getName());
    }

    public boolean removeRoom(String roomId, Integer userID){
        User user = userService.getUserById(userID);
        if(roomMap.containsKey(user)){
            for(Room room : roomMap.get(user)){
                if(room.getRoomId() == Integer.parseInt(roomId)){
                    roomMap.get(user).remove(room);
                    return true;
                }
            }
        }
        return false;
    }

}
