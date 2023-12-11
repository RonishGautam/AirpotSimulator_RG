import java.util.LinkedList;
import java.util.Queue;

/**
 * The {Runway} class represents a runway in the airport simulation.
 * It maintains a queue of planes currently on the runway.
 * Planes are added to the runway and cleared from the runway when they have landed.
 *
 * @author Ronish Gautam
 * @version 1.0
 */
class Runway {
    private Queue<Plane> planesOnRunway;

    /**
     * Constructs a runway with an empty queue of planes.
     */
    public Runway() {
        this.planesOnRunway = new LinkedList<>();
    }

    /**
     * Adds a plane to the runway.
     *
     * @param plane the plane to add to the runway
     */
    public void addPlaneToRunway(Plane plane) {
        planesOnRunway.add(plane);
    }

    /**
     * Clears the runway by removing all planes from the queue.
     */
    public void clearRunway() {
        planesOnRunway.clear();
    }

    /**
     * Gets the queue of planes currently on the runway.
     *
     * @return the queue of planes on the runway
     */
    public Queue<Plane> getPlanesOnRunway() {
        return planesOnRunway;
    }
}
