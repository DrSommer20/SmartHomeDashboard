package mosbach.dhbw.de.smarthome.model;

public class Action {
    private int id;
    private String deviceID;
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
    public Action(int id, String deviceID, String deviceName, String action, int userID) {
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
