package project2.project2;

import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CentersHolder {

    public VehicleDao vehicleDao;

    public CentersHolder() throws IOException {
    }
}
