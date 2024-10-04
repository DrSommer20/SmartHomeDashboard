package mosbach.dhbw.de.smarthome.service.api;

import java.util.List;

import mosbach.dhbw.de.smarthome.model.Room;

public interface RoomService {
    public List<Room> getRooms(Integer userID);
    public Room getRoomById(String roomId, Integer userID);
    public void addRoom(Room room, Integer userID);
    public void updateRoom(String roomId, Room room, Integer userID);
    public boolean removeRoom(String roomId, Integer userID);
}
