package mosbach.dhbw.de.smarthome.service;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import mosbach.dhbw.de.smarthome.model.Room;
import mosbach.dhbw.de.smarthome.model.User;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    private static HashMap<User, HashSet<Room>> roomMap = new HashMap<User, HashSet<Room>>();

    public static List<Room> getRooms(User user) {
        if (roomMap.containsKey(user)) {
            return new ArrayList<Room>(roomMap.get(user));
        }
        return null;
    }

    public static Room getRoomById(String roomId, User user) {
        if (roomMap.containsKey(user)) {
            for (Room room : roomMap.get(user)) {
                if (room.getRoomId() == Integer.parseInt(roomId)) {
                    return room;
                }
            }
        }
        return null;
    }

    public static void addRoom(Room room, User user) {
        if (roomMap.containsKey(user)) {
            roomMap.get(user).add(room);
        } else {
            HashSet<Room> rooms = new HashSet<Room>();
            rooms.add(room);
            roomMap.put(user, rooms);
        }
    }

    public static void updateRoom(String roomId, Room room, User user) {
        Room roomOld = getRoomById(roomId, user);
        roomOld.setName(room.getName());
    }

    public static boolean removeRoom(String roomId, User user){
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
