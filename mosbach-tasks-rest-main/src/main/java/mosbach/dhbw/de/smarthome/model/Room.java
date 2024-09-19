package mosbach.dhbw.de.smarthome.model;

public class Room {
    static int roomIdCounter = 0;
    private int roomId;
    private String name;

    public Room(String name) {
        this.roomId = roomIdCounter++;
        this.name = name;
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
