package mosbach.dhbw.de.smarthome.model;

public class Room {

    private int roomId;
    private String name;

    /**
     * Constructor
     * @param name
     */
    public Room(String name) {
        this.name = name;
    }

    public Room() {
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
