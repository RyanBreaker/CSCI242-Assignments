package csci242.assignments.vehicle;

/**
 * Implement a computer-based inventory system using Java, NetBeans and the
 * concepts of inheritance and polymorphism.
 * <p>
 * The Vehicle class
 *
 * @author Ryan Breaker
 * @edu.uwp.cs.242.course CSCI242 - Computer Science II
 * @edu.uwp.cs.242.section 001
 * @edu.uwp.cs.242.assignment 2
 * @bugs None
 */
public class Vehicle implements Comparable<Vehicle> {

    protected final String TYPE = "VEHICLE";
    /**
     * The VIN of the Vehicle.
     */
    protected int vehicleId = 1;
    /**
     * The manufacturer of the Vehicle.
     */
    protected String manufacturer = "";
    /**
     * The model of the Vehicle.
     */
    protected String model = "";


    /**
     * Empty constructor, initializes vehicleId to 1 and the the String
     * fields to an empty String.
     */
    public Vehicle() {
    }

    //region Constructors

    /**
     * Sets the VIN, manufacturer, and model of the Vehicle on instantiation.
     *
     * @param vehicleId    VIN of the Vehicle.
     * @param manufacturer Manufacturer of the Vehicle.
     * @param model        Model of the Vehicle.
     */
    public Vehicle(int vehicleId, String manufacturer, String model) {
        this.vehicleId = vehicleId;
        this.manufacturer = manufacturer;
        this.model = model;
    }

    /**
     * In lieu of overriding the infamous Object.clone(), this constructor
     * can be used instead to deep copy an existing instance.
     *
     * @param vehicle The Vehicle to copy.
     */
    public Vehicle(Vehicle vehicle) {
        vehicleId = vehicle.getVehicleId();
        manufacturer = vehicle.manufacturer;
        model = vehicle.model;
    }

    /**
     * Replaces the given oldTYPE string with the newTYPE from the start of the
     * given line; for overridden toString() methods.
     *
     * @param line    The line in which to replace the TYPEs.
     * @param oldTYPE The old TYPE to replace.
     * @param newTYPE The new TYPE to replace the old TYPE with.
     * @return Returns the fixed string.
     */
    protected final String replaceTYPE(String line, String oldTYPE, String newTYPE) {
        return newTYPE + line.substring(oldTYPE.length());
    }
    //endregion


    //region Getters & Setters

    /**
     * Returns the Vehicle's VIN.
     *
     * @return The Vehicle's VIN.
     */
    public int getVehicleId() {
        return vehicleId;
    }

    /**
     * Sets a new VIN for the Vehicle.
     *
     * @param vehicleId New VIN of the Vehicle.
     */
    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    /**
     * Returns the Vehicle's manufacturer.
     *
     * @return The Vehicle's manufacturer.
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Changes the manufaturer of the Vehicle to the given.
     *
     * @param manufacturer New manufacturer of the Vehicle.
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * Returns the Vehicle's model.
     *
     * @return The Vehicle's model.
     */
    public String getModel() {
        return model;
    }

    /**
     * Changes the model of the Vehicle.
     *
     * @param model New model of the Vehicle.
     */
    public void setModel(String model) {
        this.model = model;
    }
    //endregion


    //region Overrides
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vehicle vehicle = (Vehicle) o;

        if (vehicleId != vehicle.vehicleId) return false;
        if (!manufacturer.equals(vehicle.manufacturer)) return false;
        return model.equals(vehicle.model);
    }

    @Override
    public int hashCode() {
        int result = vehicleId;
        result = 31 * result + manufacturer.hashCode();
        result = 31 * result + model.hashCode();
        return result;
    }

    @Override
    public int compareTo(Vehicle o) {
        if (o.vehicleId > vehicleId)
            return -1;
        if (o.vehicleId < vehicleId)
            return 1;
        return 0;
    }

    @Override
    public String toString() {
        return String.format("%s - %s %s VIN: %d", TYPE, manufacturer, model, vehicleId);
    }
    //endregion
}
