package csci242.assignments.vehicle;

import csci242.assignments.vehicle.PassengerVehicle;
import csci242.assignments.vehicle.VehicleTestData;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Short description.
 * <p>
 * Long description.
 *
 * @author Ryan Breaker
 * @edu.uwp.cs.242.course CSCI242 - Computer Science II
 * @edu.uwp.cs.242.section 001
 * @edu.uwp.cs.242.assignment 2
 * @bugs None
 */
public class PassengerVehicleTest {

    PassengerVehicle vehicle;

    @Before
    public void setUp() throws Exception {
        vehicle = VehicleTestData.randomPassengerVehicle();
    }


    @Test
    public void testConstructors() throws Exception {
        PassengerVehicle vehicle = new PassengerVehicle(3);

        assertEquals(3, vehicle.numOfPassengers);
        assertEquals(2, new PassengerVehicle().numOfPassengers);
    }

    @Test
    public void testGettersSetters() throws Exception {
        int newNum;

        // Test getter
        assertEquals(vehicle.numOfPassengers, vehicle.getNumOfPassengers());

        newNum = vehicle.numOfPassengers + 15;

        // Test setter
        vehicle.setNumOfPassengers(newNum);
        assertEquals(newNum, vehicle.getNumOfPassengers());
    }

    @Test
    public void testEquals() throws Exception {
        assertEquals(new PassengerVehicle(), new PassengerVehicle());
        assertEquals(new PassengerVehicle(3), new PassengerVehicle(3));
        assertEquals(new PassengerVehicle(vehicle), new PassengerVehicle(vehicle));
        assertFalse(new PassengerVehicle(3).equals(new PassengerVehicle(4)));
    }

    @Test
    public void testHashcode() throws Exception {
        assertEquals(vehicle.hashCode(), new PassengerVehicle(vehicle).hashCode());
    }
}
