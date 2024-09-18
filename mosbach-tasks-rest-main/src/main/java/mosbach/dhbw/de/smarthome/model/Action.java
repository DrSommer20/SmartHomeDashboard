package mosbach.dhbw.de.smarthome.model;

public class Action {

    private String deviceID;
    private String action;


    public Action(String deviceID, String action) {
        this.deviceID = deviceID;
        this.action = action;
    }

    public String getDeviceID() {
        return deviceID;
    }


    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }


    public String getAction() {
        return action;
    }


    public void setAction(String action) {
        this.action = action;
    }

    

    
}
