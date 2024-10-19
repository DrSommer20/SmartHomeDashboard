import dhbw.mosbach.de.smarthome.model.DeviceType;
import dhbw.mosbach.de.smarthome.service.api.DeviceService;
import dhbw.mosbach.de.smarthome.service.api.RoomService;

@Controller
public class DeviceController {

    @Autowired
    DeviceService deviceService;

    @Autowired
    RoomService roomService;

    @QueryMapping
    public Device deviceById(@Argument int id) {
        return deviceService.getDeviceById(id);
    }

    @SchemaMapping
    public DeviceType deviceType(int id){
        return deviceService.getTypeByID(id);
    }
}
