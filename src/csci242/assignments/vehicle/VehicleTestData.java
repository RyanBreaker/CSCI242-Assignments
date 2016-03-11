package csci242.assignments.vehicle;

import java.util.Random;

/**
 * Implement a computer-based inventory system using Java, NetBeans and the
 * concepts of inheritance and polymorphism.
 * <p>
 * Long description.
 *
 * @author Ryan Breaker
 * @edu.uwp.cs.242.course CSCI242 - Computer Science II
 * @edu.uwp.cs.242.section 001
 * @edu.uwp.cs.242.assignment 2
 * @bugs None
 */
public final class VehicleTestData {

    /**
     * Random number generator for randomVehicle methods.
     */
    private static Random rand = new Random();


    //region Consts
    public static final String[] MANUFACTURERS = {
            "Chevrolet",
            "Plymouth",
            "Volksvagen",
            "Ford"
    };

    public static final String[][] CAR_MODELS = {{
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

    public static final String[][] TRUCK_MODELS = {{
            "Colorado",
            "Montana",
            "Silverado"
    }, {
            "Trail Duster"
    }, {
            "Constellation"
    }, {
            "F-150",
            "F-650",
            "Model TT",
            "Model AA",
            "Model BB"
    }};
    //endregion


    /**
     * Don't let anyone instantiate this class.
     */
    private VehicleTestData() {}

    /**
     * @return A random index for manufacturers.
     */
    private static int randomManuI() {
        return rand.nextInt(MANUFACTURERS.length);
    }

    /**
     * @return A randomly-generated Vehicle.
     */
    public static Vehicle randomVehicle() {
        int manu = randomManuI();
        int model = rand.nextInt(CAR_MODELS[manu].length);

        return new Vehicle(rand.nextInt(),
                MANUFACTURERS[manu], CAR_MODELS[manu][model]);
    }


    //region PassengerVehicle
    /**
     * @return A randomly-generated PassengerVehicle.
     */
    public static PassengerVehicle randomPassengerVehicle() {
        PassengerVehicle vehicle = new PassengerVehicle(randomVehicle());

        vehicle.numOfPassengers = rand.nextInt(6) + 2;  // Range of 2-8

        return vehicle;
    }

    /**
     * @return A randomly-generated Car.
     */
    public static Car randomCar() {
        PassengerVehicle vehicle = randomPassengerVehicle();
        Car car = new Car(vehicle);

        car.numOfPassengers = vehicle.numOfPassengers;
        car.numOfDoors = rand.nextInt(2) + 2;  // Range 2-4

        return car;
    }

    public static Motorcycle randomMotorcycle() {
        return new Motorcycle();
    }
    //endregion


    //region TransportationVehicle
    /**
     * @return A randomly-generated TransportationVehicle.
     */
    public static TransportationVehicle randomTransportationVehicle() {
        int manu = randomManuI();
        int model = rand.nextInt(TRUCK_MODELS[manu].length);

        return new TransportationVehicle(rand.nextInt(),
                MANUFACTURERS[manu], TRUCK_MODELS[manu][model],
                rand.nextInt(1500) + 500);  // Range 500-2000
    }

    /**
     * @return A randomly-generated Truck.
     */
    public static Truck randomTruck() {
        TransportationVehicle vehicle = randomTransportationVehicle();

        return new Truck(vehicle.vehicleId, vehicle.manufacturer, vehicle.model,
                vehicle.loadCapacity, rand.nextInt(2) + 2);  // Range 2-4
    }
    //endregion
}
