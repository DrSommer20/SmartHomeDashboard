package mosbach.dhbw.de.smarthome.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import mosbach.dhbw.de.smarthome.dto.smartthings.GetFullStatusResponse;

@Service
public class TimeScheduler {

    @Scheduled(fixedRate = 30000) // 300000 milliseconds = 5 minutes
    public void checkDeviceStatus() {
        UserService.getAllUsers().forEach(user -> {
            DeviceService.getDevices(user).forEach(device -> {
                GetFullStatusResponse response = SmartThings.getDeviceFullStatus(device.getId(), user.getPat());
                boolean isOn = false;
                boolean isOnline = false;
                String status = response.getSwitch().getSwitch().getValue();
                String state = response.getHealthCheck().getDeviceWatchDeviceStatus().getValue();
                if(state != null) isOnline = state.equals("online");
                if(status != null) isOn = status.equals("on");
                device.setStatus(isOnline ? "Online" : "Offline");
                device.setState(isOn ? "On" : "Off");
                System.out.println("Device: " + device.getName() + " is " + device.getState() + " and " + device.getStatus());
            });
        });
    }
}
