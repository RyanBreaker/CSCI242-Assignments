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

    protected int numOfDoors;


    public Car() {}

    public Car(int vehicleId, String manufacturer, String model,
               int numOfPassengers, int numOfDoors) {
        this.vehicleId = vehicleId;
        this.manufacturer = manufacturer;
        this.model = model;
        this.numOfPassengers = numOfPassengers;
        this.numOfDoors = numOfDoors;
    }


    public int getNumOfDoors() {
        return numOfDoors;
    }

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
