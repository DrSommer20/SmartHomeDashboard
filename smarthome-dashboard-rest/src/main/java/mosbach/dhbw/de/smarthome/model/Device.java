package mosbach.dhbw.de.smarthome.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Device {
    @Id
    private int id;
    private String name;
    private String type;
    private String typeIcon;
    private int typeID;
    private String status;
    private int location;
    private String state;

    /**
     * Constructor
     * 
     * @param id
     * @param name
     * @param typeID
     * @param location
     */
    public Device(int id, String name, int typeID, int location) {
        this.id = id;
        this.name = name;
        this.typeID = typeID;
        this.location = location;
        this.status = "Offline";
        this.state = "Off";
    }

    public Device() {
    }


    public void setId(int id) {
        this.id = id;
    }  

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public int getLocation() {
        return location;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTypeIcon() {
        return typeIcon;
    }

    public void setTypeIcon(String typeIcon) {
        this.typeIcon = typeIcon;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public void setStatus(boolean status) {
        this.status = status ? "Online" : "Offline";
    }

    public boolean getStatusBoolean() {
        return status.equals("Online");
    }

}
