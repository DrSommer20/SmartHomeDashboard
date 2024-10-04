package mosbach.dhbw.de.smarthome.service.api;

import java.util.List;

import mosbach.dhbw.de.smarthome.model.Device;

public interface DeviceService {

    public void addDevice(Device device, Integer userID);
    public List<Device> getDevices(Integer userID);
    public Device getDeviceById(String id, Integer userID);
    public boolean deleteDevice(String id, Integer userID);
    
}
