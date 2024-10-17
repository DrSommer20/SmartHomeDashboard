package mosbach.dhbw.de.smarthome.service.api;

import mosbach.dhbw.de.smarthome.dto.smartthings.AllDevices;
import mosbach.dhbw.de.smarthome.dto.smartthings.DeviceST;
import mosbach.dhbw.de.smarthome.dto.smartthings.GetFullStatusResponse;

public interface SmartThings {

    /**
     * Gets all devices of a user
     * 
     * @param accessToken the access token
     * @return the devices
     */
    public AllDevices getAllDevices(String accessToken);

    /**
     * Gets a device by its id
     * 
     * @param deviceID the id
     * @param accessToken the access token
     * @return the device
     */
    public DeviceST getDevice(String deviceID, String accessToken);

    /**
     * Sets the status of a device
     * 
     * @param status the status
     * @param deviceID the id
     * @param capabilityId the capability id
     * @param accessToken the access token
     * @return true if the status was set, false otherwise
     */
    public boolean setDeviceStatus(String status, String deviceID, String capabilityId, String accessToken);
    
    /**
     * Gets the full status of a device
     * 
     * @param deviceID the id
     * @param accessToken the access token
     * @return the full status
     */
    public GetFullStatusResponse getDeviceFullStatus(String deviceID, String accessToken );

    /**
     * Checks if a switch is on
     * 
     * @param deviceID the id
     * @param accessToken the access token
     * @return true if the switch is on, false otherwise
     */
    public boolean isSwitchOn(String deviceID, String accessToken );

    /**
     * Checks if a device is online
     * 
     * @param deviceID the id
     * @param accessToken the access token
     * @return true if the device is online, false otherwise
     */
    public boolean isOnline(String deviceID, String accessToken );
}
