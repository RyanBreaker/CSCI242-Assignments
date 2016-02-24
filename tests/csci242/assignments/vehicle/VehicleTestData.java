package csci242.assignments.vehicle;

import java.util.Random;

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
public class VehicleTestData {

    private static Random rand = new Random();


    //region Consts
    public static final String[] MANUFACTURERS = {
            "Chevrolet",
            "Plymouth",
            "Volksvagen",
            "Ford"
    };

    public static final String[][] MODELS = {{
            "Aveo",
            "Sonic",
            "Spark",
            "Cruze",
            "Volt",
            "Malibu",
            "Caprice",
            "Corvette"
    }, {
            "Acclaim",
            "Breeze",
            "Grand Voyager",
            "Superbird"
    }, {
            "Jetta",
            "Beetle",
            "Passat",
            "Golf",
            "Kübelwagen"
    }, {
            "Focus",
            "Fiesta",
            "Fusion",
            "Taurus",
            "Mustang"
    }};
    //endregion


    public static Vehicle randomVehicle() {
        int manu = rand.nextInt(MANUFACTURERS.length);
        int model = rand.nextInt(MODELS[manu].length);

        return new Vehicle(rand.nextInt(),
                MANUFACTURERS[manu], MODELS[manu][model]);
    }

    public static PassengerVehicle randomPassengerVehicle() {
        PassengerVehicle vehicle = new PassengerVehicle(randomVehicle());
        vehicle.numOfPassengers = rand.nextInt(4) + 2;  // Range of 2-6
        return vehicle;
    }
}
