package mosbach.dhbw.de.smarthome.service.api;

import mosbach.dhbw.de.smarthome.dto.smartthings.AllDevices;
import mosbach.dhbw.de.smarthome.dto.smartthings.DeviceST;
import mosbach.dhbw.de.smarthome.dto.smartthings.GetFullStatusResponse;

public interface SmartThings {
    public AllDevices getAllDevices(String accessToken);
    public DeviceST getDevice(String deviceID, String accessToken);
    public boolean setDeviceStatus(String status, String deviceID, String capabilityId, String accessToken);
    public GetFullStatusResponse getDeviceFullStatus(String deviceID, String accessToken );
    public boolean isSwitchOn(String deviceID, String accessToken );
    public boolean isOnline(String deviceID, String accessToken );
}
