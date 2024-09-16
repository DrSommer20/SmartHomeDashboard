package mosbach.dhbw.de.smarthome.service;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import mosbach.dhbw.de.smarthome.model.Device;
import mosbach.dhbw.de.smarthome.model.User;

public class DeviceService {
    private static HashMap<User, HashSet<Device>> deviceMap = new HashMap<User, HashSet<Device>>();

    public static void addDevice(Device device, User user) {
        if (deviceMap.containsKey(user)) {
            deviceMap.get(user).add(device);
        } else {
            HashSet<Device> devices = new HashSet<Device>();
            devices.add(device);
            deviceMap.put(user, devices);
        }
    }

    public static List<Device> getDevices(User user) {
        if (deviceMap.containsKey(user)) {
            return new ArrayList<Device>(deviceMap.get(user));
        }
        return null;
    }

    public static Device getDeviceById(String id, User user) {
        if (deviceMap.containsKey(user)) {
            for (Device device : deviceMap.get(user)) {
                if (device.getId().equals(id)) {
                    return device;
                }
            }
        }
        return null;
    }

    public static boolean deleteDevice(String id, User user) {
        if (deviceMap.containsKey(user)) {
            for (Device device : deviceMap.get(user)) {
                if (device.getId().equals(id)) {
                    deviceMap.get(user).remove(device);
                    return true;
                }
            }
        }
        return false;
    }
}
