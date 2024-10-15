package mosbach.dhbw.de.smarthome.service.api;

import java.util.List;

import mosbach.dhbw.de.smarthome.model.Room;

public interface RoomService {

    /**
     * Gets all rooms of a user
     * 
     * @param userID the user id
     * @return the rooms
     */
    public List<Room> getRooms(Integer userID);

    /**
     * Gets a room by its id
     * 
     * @param roomId the id
     * @param userID the user id
     * @return the room
     */
    public Room getRoomById(String roomId, Integer userID);

    /**
     * Adds a room to the database
     * 
     * @param room the room
     * @param userID the user id
     */
    public void addRoom(Room room, Integer userID);

    /**
     * Updates a room
     * 
     * @param room the room
     * @param userID the user id
     */
    public void updateRoom(Room room, Integer userID);

    /**
     * Removes a room
     * 
     * @param roomId the id
     * @param userID the user id
     * @return true if the room was removed, false otherwise
     */
    public boolean removeRoom(String roomId, Integer userID);
}
