package mosbach.dhbw.de.smarthome.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import mosbach.dhbw.de.smarthome.model.Device;
import mosbach.dhbw.de.smarthome.service.api.DeviceService;

import org.springframework.stereotype.Service;

@Service
public class DeviceServiceImpl implements DeviceService {
    private static HashMap<Integer, HashSet<Device>> deviceMap = new HashMap<Integer, HashSet<Device>>();

    public void addDevice(Device device, Integer userID) {
        if (deviceMap.containsKey(userID)) {
            deviceMap.get(userID).add(device);
        } else {
            HashSet<Device> devices = new HashSet<Device>();
            devices.add(device);
            deviceMap.put(userID, devices);
        }
    }

    public List<Device> getDevices(Integer userID) {
        if (deviceMap.containsKey(userID)) {
            return new ArrayList<Device>(deviceMap.get(userID));
        }
        return null;
    }

    public Device getDeviceById(String id, Integer userID) {
        if(deviceMap.get(userID) == null) return null;
        return deviceMap
                    .get(userID)
                    .stream()
                    .filter(device -> device.getId().equals(id))
                    .findFirst()
                    .orElse(null);
    }

    public boolean deleteDevice(String id, Integer userID) {
        if (deviceMap.containsKey(userID)) {
            for (Device device : deviceMap.get(userID)) {
                if (device.getId().equals(id)) {
                    deviceMap.get(userID).remove(device);
                    return true;
                }
            }
        }
        return false;
    }
}
