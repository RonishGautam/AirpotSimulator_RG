import java.util.*;

/**
 * The Plane class represents an airplane in the airport simulation.
 * Each plane has a unique ID, a name, a distance from the airport, and an emergency status.
 * It implements the Comparable interface to define a natural ordering based on emergency status.
 *
 * @author Ronish Gautam
 * @version 1.0
 */
public class Plane implements Comparable<Plane> {

    private static int idCounter = 0;
    private int id;
    private String name;
    private int distance;
    private boolean emergency;

    private static final String[] PLANE_NAMES = {"Boeing 747", "Airbus A320", "Boeing 787", "Embraer E190", "Airbus A380"};

    /**
     * Constructs a plane with a given distance from the airport.
     *
     * @param distance the distance of the plane from the airport
     */
    public Plane(int distance) {
        this.id = ++idCounter;
        this.name = PLANE_NAMES[new Random().nextInt(PLANE_NAMES.length)];
        this.distance = distance;
        this.emergency = false;
    }

    /**
     * Gets the unique ID of the plane.
     *
     * @return the unique ID of the plane
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the name of the plane.
     *
     * @return the name of the plane
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the distance of the plane from the airport.
     *
     * @return the distance of the plane from the airport
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Sets the distance of the plane from the airport.
     *
     * @param distance the new distance of the plane
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }

    /**
     * Checks if the plane is in an emergency situation.
     *
     * @return {@code true} if the plane is in an emergency, {@code false} otherwise
     */
    public boolean isEmergency() {
        return emergency;
    }

    /**
     * Sets the emergency status of the plane.
     *
     * @param emergency the new emergency status of the plane
     */
    public void setEmergency(boolean emergency) {
        this.emergency = emergency;
    }

    /**
     * Compares this plane with another plane based on their emergency status.
     * Emergency planes have higher priority in the readyToLandQueue.
     *
     * @param other the plane to compare with
     * @return a negative integer, zero, or a positive integer as this plane is less than, equal to,
     *         or greater than the specified plane
     */
    @Override
    public int compareTo(Plane other) {
        return Boolean.compare(this.isEmergency(), other.isEmergency());
    }

    /**
     * Returns a string representation of the plane, including its name and ID.
     *
     * @return a string representation of the plane
     */
    @Override
    public String toString() {
        return name + " #" + id;
    }
}
