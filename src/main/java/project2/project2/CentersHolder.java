package project2.project2;

import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CentersHolder {

    public Dealer dealer = new Dealer("report.txt");
    public int globalId = dealer.getLastID() + 1;
    public VehicleDao vehicleDao;

    public CentersHolder() throws IOException {
    }
}
