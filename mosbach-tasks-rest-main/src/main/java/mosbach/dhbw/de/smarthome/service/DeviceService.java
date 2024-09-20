package mosbach.dhbw.de.smarthome.service;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import mosbach.dhbw.de.smarthome.model.Device;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {
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
        if (deviceMap.containsKey(userID)) {
            for (Device device : deviceMap.get(userID)) {
                if (device.getId().equals(id)) {
                    return device;
                }
            }
        }
        return null;
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

    List<Device> getAllDevices() {
        List<Device> devices = new ArrayList<>();
        for (HashSet<Device> deviceSet : deviceMap.values()) {
            devices.addAll(deviceSet);
        }
        return devices;
    }
}
