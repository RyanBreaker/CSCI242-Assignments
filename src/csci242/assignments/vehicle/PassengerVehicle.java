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


public class PassengerVehicle extends Vehicle {

    /**
     * The number of passengers the PassengerVehicle can accomodate.
     */
    protected int numOfPassengers = 2;


    /**
     *
     */
    public PassengerVehicle() {}

    /**
     * Sets the
     *
     * @param numOfPassengers The number of passengers the PassengerVehicle can
     *                        accomodate.
     */
    public PassengerVehicle(int numOfPassengers) {
        this.numOfPassengers = numOfPassengers;
    }

    protected PassengerVehicle(int vehicleId, String manufacturer,
                               String model, int numOfPassengers) {
        super(vehicleId, manufacturer, model);
        this.numOfPassengers = numOfPassengers;
    }

    public PassengerVehicle(Vehicle vehicle) {
        super(vehicle);
    }

    public PassengerVehicle(PassengerVehicle vehicle) {
        super(vehicle);
        numOfPassengers = vehicle.numOfPassengers;
    }


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
}
