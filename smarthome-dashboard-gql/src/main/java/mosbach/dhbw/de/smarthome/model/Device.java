package mosbach.dhbw.de.smarthome.model;

public class Device {

    private String id;
    private String name;
    private DeviceType type;
    private String status;
    private String state;
    private String roomName;
    private int location;
    

    /**
     * Constructor
     * 
     * @param id
     * @param name
     * @param typeID
     * @param location
     */
    public Device(String id, String name, DeviceType type, int location) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.status = "Offline";
        this.state = "Off";
    }

    public Device() {
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

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }


    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
