package csci242.assignments.vehicle;

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


public class PassengerVehicle extends Vehicle {

    protected int numOfPassengers;


    public PassengerVehicle() {}

    public PassengerVehicle(int numOfPassengers) {
        this.numOfPassengers = numOfPassengers;
    }


    public int getNumOfPassengers() {
        return numOfPassengers;
    }

    public void setNumOfPassengers(int numOfPassengers) {
        this.numOfPassengers = numOfPassengers;
    }
}
