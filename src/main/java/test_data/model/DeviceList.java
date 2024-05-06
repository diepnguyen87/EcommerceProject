package test_data.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DeviceList {
    private List<Device> androidDeviceList;
    private List<Device> iosDeviceList;

    @Data
    @AllArgsConstructor
    public class Device {
        private String deviceName;
        private String platformVersion;
    }
}
