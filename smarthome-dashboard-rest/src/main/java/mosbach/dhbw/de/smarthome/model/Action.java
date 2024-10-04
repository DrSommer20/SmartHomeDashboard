package mosbach.dhbw.de.smarthome.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Action {
    @Id
    private String deviceID;
    private String deviceName;
    private String action;
    private int userID;


    public Action(String deviceID, String deviceName, String action, int userID) {
        this.deviceID = deviceID;
        this.deviceName = deviceName;
        this.action = action;
        this.userID = userID;
    }

    public String getDeviceID() {
        return deviceID;
    }


    public void setDeviceID(String deviceID) {
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
