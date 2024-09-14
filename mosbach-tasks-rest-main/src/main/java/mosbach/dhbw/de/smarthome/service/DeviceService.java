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
                if (device.getId() == Integer.parseInt(id)) {
                    return device;
                }
            }
        }
        return null;
    }

    public static boolean updateDevice(int id, Device updatedDevice, User user) {
        if (deviceMap.containsKey(user)) {
            for (Device device : deviceMap.get(user)) {
                if (device.getId() == id) {
                    device.setName(updatedDevice.getName());
                    device.setType(updatedDevice.getType());
                    device.setStatus(updatedDevice.getStatus());
                    device.setLocation(updatedDevice.getLocation());
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean deleteDevice(String id, User user) {
        if (deviceMap.containsKey(user)) {
            for (Device device : deviceMap.get(user)) {
                if (device.getId() == Integer.parseInt(id)) {
                    deviceMap.get(user).remove(device);
                    return true;
                }
            }
        }
        return false;
    }

}
