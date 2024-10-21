package mosbach.dhbw.de.smarthome.model;

public class Action {
    private int id;
    private String deviceID;
    private String deviceName;
    private String setTo;
    private int userID;

    /**
     * Constructor
     * @param id 
     * @param deviceID 
     * @param deviceName
     * @param action
     * @param userID
     */
    public Action(int id, String deviceID, String deviceName, String setTo, int userID) {
        this.id = id;
        this.deviceID = deviceID;
        this.deviceName = deviceName;
        this.setTo = setTo;
        this.userID = userID;
    }

    public Action() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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


    public String getSetTo() {
        return setTo;
    }


    public void setSetTo(String setTo) {
        this.setTo = setTo;
    }

    public int getUserID() {
        return userID;
    }

    public void setUser(int userID) {
        this.userID = userID;
    }

    

    
}
