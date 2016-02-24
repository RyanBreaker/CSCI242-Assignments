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
public class Vehicle {

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
     * Empty constructor, initializes vehicleId to 1 and the other String
     * fields to an empty String.
     */
    public Vehicle() {}

    /**
     *
     *
     * @param vehicleId
     * @param manufacturer
     * @param model
     */
    public Vehicle(int vehicleId, String manufacturer, String model) {
        this.vehicleId = vehicleId;
        this.manufacturer = manufacturer;
        this.model = model;
    }


    /**
     * In lieu of overriding the infamous Object.clone(), this constructor
     * can be used instead to create a clone/copy of an existing method.
     *
     * @param clonee The Vehicle to clone.
     */
    public Vehicle(Vehicle clonee) {
        vehicleId = clonee.getVehicleId();
        manufacturer = clonee.manufacturer;
        model = clonee.model;
    }


    //region Getters & Setters
    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
    //endregion


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
    public String toString() {
        return "Vehicle{" +
                "vehicleId=" + vehicleId +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
