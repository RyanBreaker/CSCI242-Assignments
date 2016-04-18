package csci242.assignments.vehicle;

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
public class PassengerVehicle extends Vehicle {

    /**
     * The type of the car for toString().
     */
    protected final String TYPE = "PASSENGERVEHICLE";
    /**
     * The number of passengers the PassengerVehicle can accomodate.
     */
    protected int numOfPassengers = 2;

    //region Constructors

    /**
     * Empty constructor, numOfPassengers defaulted to 2.
     */
    public PassengerVehicle() {
    }

    /**
     * Sets the number of passengers to the given integer.
     *
     * @param numOfPassengers The number of passengers the PassengerVehicle can
     *                        accomodate.
     */
    public PassengerVehicle(int numOfPassengers) {
        this.numOfPassengers = numOfPassengers;
    }

    public PassengerVehicle(int vehicleId, String manufacturer,
                            String model, int numOfPassengers) {
        super(vehicleId, manufacturer, model);
        this.numOfPassengers = numOfPassengers;
    }

    public PassengerVehicle(Vehicle vehicle) {
        super(vehicle);
    }

    /**
     * Cloning constructor; copies the given PassengerVehicle's fields to its
     * own.
     *
     * @param vehicle The PassengerVehicle to clone.
     */
    public PassengerVehicle(PassengerVehicle vehicle) {
        super(vehicle);
        numOfPassengers = vehicle.numOfPassengers;
    }
    //endregion


    public int getNumOfPassengers() {
        return numOfPassengers;
    }

    public void setNumOfPassengers(int numOfPassengers) {
        this.numOfPassengers = numOfPassengers;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PassengerVehicle that = (PassengerVehicle) o;

        return numOfPassengers == that.numOfPassengers;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + numOfPassengers;
        return result;
    }

    @Override
    public String toString() {
        String s = replaceTYPE(super.toString(), super.TYPE, TYPE);
        return String.format("%s Number of passengers: %d", s, numOfPassengers);
    }
}
