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
public class Motorcycle extends PassengerVehicle {

    /**
     * The weight of the Motorcycle
     */
    protected int weight = 400;


    public Motorcycle() {
    }

    public Motorcycle(int vehicleId, String manufacturer, String model, int weight) {
        super(vehicleId, manufacturer, model, 1);
        this.weight = weight;
    }

    public Motorcycle(Vehicle vehicle) {
        super(vehicle);
    }


    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Motorcycle that = (Motorcycle) o;

        return weight == that.weight;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + weight;
        return result;
    }

    @Override
    public String toString() {
        String s = replaceTYPE(super.toString(), super.TYPE, TYPE);
        return String.format("%s Weight: %d", s, weight);
    }
}
