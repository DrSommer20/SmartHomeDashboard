package mosbach.dhbw.de.smarthome.model;

public class Device {

    private String id;
    private String name;
    private DeviceType type;
    private String status;
    private String state;
    private Room room;
    

    /**
     * Constructor
     * 
     * @param id
     * @param name
     * @param typeID
     * @param location
     */
    public Device(String id, String name, DeviceType type, Room room) {
        this();
        this.id = id;
        this.name = name;
        this.type = type;
        this.room = room;
        
    }

    public Device() {
        this.status = "Offline";
        this.state = "Off";
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DeviceType getType() {
        return type;
    }

    public void setType(DeviceType type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    

    public boolean getStatusBoolean() {
        return status.equals("Online");
    }

    public void setStatusBoolean(boolean status) {
        this.status = status ? "Online" : "Offline";
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
