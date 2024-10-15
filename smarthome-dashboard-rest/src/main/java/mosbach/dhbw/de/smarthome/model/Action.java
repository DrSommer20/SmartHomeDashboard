package mosbach.dhbw.de.smarthome.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Action {
    @Id
    private int id;
    private int deviceID;
    private String deviceName;
    private String action;
    private int userID;

    /**
     * Constructor
     * @param id 
     * @param deviceID 
     * @param deviceName
     * @param action
     * @param userID
     */
    public Action(int id, int deviceID, String deviceName, String action, int userID) {
        this.id = id;
        this.deviceID = deviceID;
        this.deviceName = deviceName;
        this.action = action;
        this.userID = userID;
    }

    public Action() {
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getDeviceID() {
        return deviceID;
    }


    public void setDeviceID(int deviceID) {
        this.deviceID = deviceID;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }


    public String getAction() {
        return action;
    }


    public void setAction(String action) {
        this.action = action;
    }

    public int getUserID() {
        return userID;
    }

    public void setUser(int userID) {
        this.userID = userID;
    }

    

    
}
