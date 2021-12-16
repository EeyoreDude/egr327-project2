package project2.project2;

import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object - provide some specific data operations without exposing details of the database
 * Access data for the Vehicle entity.
 * Repository annotation allows Spring to find and configure the DAO.
 * Transactional annonation will cause Spring to call begin() and commit()
 * at the start/end of the method. If exception occurs it will also call rollback().
 */

@Repository
@Transactional
public class VehicleDao {

    //PersistenceContext annotation used to specify there is a database source.
    //EntityManager is used to create and remove persistent entity instances,
    //to find entities by their primary key, and to query over entities.
    @PersistenceContext
    private EntityManager entityManager;

    //Insert vehicle into the database.
    public void create(Vehicle vehicle) {
        entityManager.persist(vehicle);
    }

    //Return the vehicle with the passed-in id.
    public Vehicle getById(int id) {
        return entityManager.find(Vehicle.class, id);
    }

    public Vehicle update(Vehicle vehicle){
        entityManager.merge(vehicle);
        return vehicle;
    }

    public void delete(int id){
        entityManager.remove(entityManager.find(Vehicle.class, id));
    }

    public List<Vehicle> getLatest(){
        ArrayList<Vehicle> latestVehicles = new ArrayList<>();
        List<Integer> latestIds = entityManager.createQuery("select id from Vehicle order by id desc", Integer.class).setMaxResults(10).getResultList();
        for(Integer id : latestIds){
            latestVehicles.add(getById(id));
        }
        return latestVehicles;
    }

    public Integer greatestId(){
        return entityManager.createQuery("select id from Vehicle order by id desc", Integer.class).getFirstResult();
    }
}
