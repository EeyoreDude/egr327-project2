package project2.project2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DealerController {

    @Autowired
    public VehicleDao vehicleDao;


    @RequestMapping(value = "/addVehicle", method = RequestMethod.POST)
    public Vehicle addVehicle(@RequestBody Vehicle newVehicle) {
        vehicleDao.create(newVehicle);
        return newVehicle;
    }

    @RequestMapping(value = "/getVehicle/{id}", method = RequestMethod.GET)
    public Vehicle getVehicle(@PathVariable("id") int id) {
        return vehicleDao.getById(id);
    }

    @RequestMapping(value = "/updateVehicle", method = RequestMethod.PUT)
    public Vehicle updateVehicle(@RequestBody Vehicle updatedVehicle) {
        if(updatedVehicle.getId() < 0) {
            throw new IllegalArgumentException("Please provide an ID");
        }
        return vehicleDao.update(updatedVehicle);
    }

    @RequestMapping(value = "/deleteVehicle/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteVehicle(@PathVariable("id") int id) {
        try {
            vehicleDao.delete(id);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<>("No vehicle with id " + id, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Successfully removed vehicle with id " + id, HttpStatus.OK);
    }

    @RequestMapping(value = "/getLatestVehicles", method = RequestMethod.GET)
    public List<Vehicle> getLatestVehicles() {
        return vehicleDao.getLatest();
    }







}
