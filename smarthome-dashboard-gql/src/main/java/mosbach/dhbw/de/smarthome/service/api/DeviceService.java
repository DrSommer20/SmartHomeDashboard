package mosbach.dhbw.de.smarthome.service.api;

import java.util.List;

import mosbach.dhbw.de.smarthome.model.Device;
import mosbach.dhbw.de.smarthome.model.DeviceType;

public interface DeviceService {

    /**
     * Adds a device to the database
     * 
     * @param device the device
     * @param userID the user id
     */
    public void addDevice(Device device, Integer userID);

    /**
     * Gets all devices of a user
     * 
     * @param userID the user id
     * @return the devices
     */
    public List<Device> getDevices(Integer userID);

    /**
     * Gets a device by its id
     * 
     * @param id the id
     * @param userID the user id
     * @return the device
     */
    public Device getDeviceById(String id, Integer userID);

    /**
     * Deletes a device
     * 
     * @param id the id
     * @param userID the user id
     * @return true if the device was deleted, false otherwise
     */
    public boolean deleteDevice(String id, Integer userID);

    /**
     * Updates a device
     * 
     * @param device the device
     * @param userID the user id
     * @return true if the device was updated, false otherwise
     */
    public boolean updateDevice(Device device, Integer userID);


    public List<DeviceType> getTypes();

    public DeviceType getTypeByID(int id);
    
}
