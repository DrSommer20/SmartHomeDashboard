@Controller
public class DeviceController {
    @QueryMapping
    public Device deviceById(@Argument int id) {
        return deviceService.getDeviceById(id);
    }

    @SchemaMapping
    public 
}
