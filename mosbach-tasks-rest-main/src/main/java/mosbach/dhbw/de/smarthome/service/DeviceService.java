package mosbach.dhbw.de.smarthome.service;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import mosbach.dhbw.de.smarthome.model.Device;

public class DeviceService {
    private static HashMap<String, HashSet<Device>> deviceMap = new HashMap<String, HashSet<Device>>();

    public static void addDevice(Device device, String token) {
        if (deviceMap.containsKey(token)) {
            deviceMap.get(token).add(device);
        } else {
            HashSet<Device> devices = new HashSet<Device>();
            devices.add(device);
            deviceMap.put(token, devices);
        }
    }

    public static List<Device> getDevices(String token) {
        if (deviceMap.containsKey(token)) {
            return new ArrayList<Device>(deviceMap.get(token));
        }
        return new ArrayList<Device>();
    }

    public static Device getDeviceById(String id, String token) {
        if (deviceMap.containsKey(token)) {
            for (Device device : deviceMap.get(token)) {
                if (device.getId() == Integer.parseInt(id)) {
                    return device;
                }
            }
        }
        return null;
    }

    public static boolean updateDevice(int id, Device updatedDevice, String token) {
        if (deviceMap.containsKey(token)) {
            for (Device device : deviceMap.get(token)) {
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

    public static boolean deleteDevice(int id, String token) {
        if (deviceMap.containsKey(token)) {
            for (Device device : deviceMap.get(token)) {
                if (device.getId() == id) {
                    deviceMap.get(token).remove(device);
                    return true;
                }
            }
        }
        return false;
    }

}
