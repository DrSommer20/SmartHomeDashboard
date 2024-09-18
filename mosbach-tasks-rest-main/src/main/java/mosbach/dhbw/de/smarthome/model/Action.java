package mosbach.dhbw.de.smarthome.model;

public class Action {

    private String deviceID;
    private String action;
    private User user;


    public Action(String deviceID, String action, User user) {
        this.deviceID = deviceID;
        this.action = action;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    

    
}
