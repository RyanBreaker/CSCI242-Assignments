package csci242.assignments.vehicle;

import java.util.ArrayList;

/**
 * Implement a computer-based inventory system using Java, NetBeans and the
 * concepts of inheritance and polymorphism.
 * <p/>
 * Long description.
 *
 * @author Ryan Breaker
 * @edu.uwp.cs.242.course CSCI242 - Computer Science II
 * @edu.uwp.cs.242.section 001
 * @edu.uwp.cs.242.assignment 2
 * @bugs None
 */
public class Inventory {

    public ArrayList<PassengerVehicle> pvList = new ArrayList<PassengerVehicle>();
    public ArrayList<TransportationVehicle> tvList = new ArrayList<TransportationVehicle>();


    public static void main(String args[]) {
        System.out.println(new Car(1001, "Ford", "Fusion", 5, 4));
    }


    public void init() {}

    public void report() {}

    public void printList(ArrayList<Vehicle> al) {
        for(Vehicle v : al) {
            System.out.println(v);
        }
    }
}
