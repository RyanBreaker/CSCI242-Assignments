package csci242.assignments.vehicle;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Short description.
 * <p/>
 * Long description.
 *
 * @author Ryan Breaker
 * @edu.uwp.cs.242.course CSCI242 - Computer Science II
 * @edu.uwp.cs.242.section 001
 * @edu.uwp.cs.242.assignment 2
 * @bugs None
 */
public class VehicleTest {

    Vehicle vehicle;

    @Before
    public void setUp() throws Exception {
        vehicle = new Vehicle(1, "Chevy", "Sonic");
    }


    @Test
    public void testGettersSetters() throws Exception {
        int newId = vehicle.getVehicleId() + 1;
        String newManufacturer = "Doge";
        String newModel = "Wow";

        vehicle.setVehicleId(newId);
        vehicle.setManufacturer(newManufacturer);
        vehicle.setModel(newModel);

        assertEquals(newId, vehicle.getVehicleId());
        assertEquals(newManufacturer, vehicle.getManufacturer());
        assertEquals(newModel, vehicle.getModel());
    }
}