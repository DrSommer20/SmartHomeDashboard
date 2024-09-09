package mosbach.dhbw.de.smarthome.model;

public class Device {
    private static int idCounter = 0;
    private int id;
    private String name;
    private String type;
    private String status;
    private String location;

    public Device(String name, String type, String location) {
        id = idCounter++;
        this.name = name;
        this.type = type;
        this.location = location;
        this.status = "Online";
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

    public String getLocation() {
        return location;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLocation(String location) {
        this.location = location;
    }


}
