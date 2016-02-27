package csci242.assignments.vehicle;

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
public class Car extends PassengerVehicle {

    protected int numOfDoors = 2;


    /**
     * Empty constructor, numOfDoors defaults to 2.
     */
    public Car() {}

    /**
     * Copies a Vehicle's fields' values to the new Car's.
     *
     * @param vehicle The Vehicle to pass up the chain.
     */
    public Car(Vehicle vehicle) {
        super(vehicle);
    }

    /**
     * Cloning constructor, takes an existing Car and copies its fields'
     * values into its own.
     *
     * @param car The Car to duplicate.
     */
    public Car(Car car) {
        super(car);
        numOfDoors = car.numOfDoors;
    }

    /**
     *
     *
     * @param vehicleId VIN of the vehicle.
     * @param manufacturer Manufacturer of the vehicle.
     * @param model Model of the vehicle.
     * @param numOfPassengers Number of passengers the vehicle can accomodate.
     * @param numOfDoors Number of doors the vehicle has.
     */
    public Car(int vehicleId, String manufacturer, String model,
               int numOfPassengers, int numOfDoors) {
        super(vehicleId, manufacturer, model, numOfPassengers);
        this.numOfDoors = numOfDoors;
    }


    /**
     * Returns the number of doors this Car has.
     *
     * @return The number of doors this Car has.
     */
    public int getNumOfDoors() {
        return numOfDoors;
    }

    /**
     * Sets the number of doors this Car has.
     *
     * @param numOfDoors the number of doors to set.
     */
    public void setNumOfDoors(int numOfDoors) {
        this.numOfDoors = numOfDoors;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Car car = (Car) o;

        return numOfDoors == car.numOfDoors;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + numOfDoors;
        return result;
    }
}
