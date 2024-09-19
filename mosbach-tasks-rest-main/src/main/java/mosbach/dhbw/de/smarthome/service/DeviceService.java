package mosbach.dhbw.de.smarthome.service;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import mosbach.dhbw.de.smarthome.model.Device;
import mosbach.dhbw.de.smarthome.model.User;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {
    private static HashMap<User, HashSet<Device>> deviceMap = new HashMap<User, HashSet<Device>>();

    static{
        Device device1 = new Device("35df5c2a-ec4a-4283-9c9a-af7ec3d62464", "Test Smart Plug", "Switch", "Living Room");
        Device device2 = new Device("43837c08-b007-4ed8-b0b4-c669fbac8d58", "Schreibtischlampe", "Switch", "Living Room");
        Device device3 = new Device("c0e7e30d-85f6-4284-a05c-f30a43f1886e", "Beamer", "Switch", "Living Room");
        Device device4 = new Device("cf387b43-b9c4-4710-aeec-68f16f644c5e", "Ventilator", "Switch", "Living Room");
        User user = UserService.getUserByEmail("max@mustermann.de");
        user.setPat("81a66f03-0f26-4ca0-813a-fc27ba6343e5");
        addDevice(device1, user);
        addDevice(device2, user);
        addDevice(device3, user);
        addDevice(device4, user);
    }

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

    static List<Device> getAllDevices() {
        List<Device> devices = new ArrayList<>();
        for (HashSet<Device> deviceSet : deviceMap.values()) {
            devices.addAll(deviceSet);
        }
        return devices;
    }
}
