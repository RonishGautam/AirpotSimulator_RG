import java.util.*;

/**
 * The {@code Airport} class represents an airport in the simulation.
 * It manages queues of planes approaching and ready to land, as well as a list of runways.
 * The class simulates the flow of planes approaching, landing, and clearing the runways.
 *
 * @author Your Name
 * @version 1.0
 */
class Airport {
    private Queue<Plane> approachQueue;
    private PriorityQueue<Plane> readyToLandQueue;
    private List<Runway> runways;
    private int timestepCount;

    /**
     * Constructs an airport with the specified number of runways.
     *
     * @param numOfRunways the number of runways at the airport
     */
    public Airport(int numOfRunways) {
        this.approachQueue = new LinkedList<>();
        this.readyToLandQueue = new PriorityQueue<>();
        this.runways = new ArrayList<>();

        for (int i = 0; i < numOfRunways; i++) {
            runways.add(new Runway());
        }

        this.timestepCount = 0;
    }

    /**
     * Gets the approach queue of planes.
     *
     * @return the approach queue
     */
    public Queue<Plane> getApproachQueue() {
        return approachQueue;
    }

    /**
     * Gets the priority queue of planes ready to land.
     *
     * @return the ready-to-land queue
     */
    public PriorityQueue<Plane> getReadyToLandQueue() {
        return readyToLandQueue;
    }

    /**
     * Gets the list of runways at the airport.
     *
     * @return the list of runways
     */
    public List<Runway> getRunways() {
        return runways;
    }

    /**
     * Gets the current timestep count.
     *
     * @return the timestep count
     */
    public int getTimestepCount() {
        return timestepCount;
    }

    /**
     * Updates the queues and runways based on the current simulation timestep.
     * Generates new planes, updates approach queues, and handles runways.
     */
    public void updateQueues() {
        if (timestepCount >= 100) {
            endSimulation();
        }

        Random rand = new Random();
        if (rand.nextDouble() < 0.1 && approachQueue.size() < 10) {
            generateNewPlane(rand);
        }

        updateApproachQueue();
        updateRunways();

        timestepCount++;
    }

    /**
     * Generates a new plane with a random distance and adds it to the approach queue.
     *
     * @param rand the random number generator
     */
    private void generateNewPlane(Random rand) {
        Plane newPlane = new Plane(1000);
        if (rand.nextDouble() < 0.1) {
            newPlane.setEmergency(true);
        }
        readyToLandQueue.add(newPlane);
    }

    /**
     * Updates the approach queue by simulating planes approaching and landing.
     * Moves planes to the ready-to-land queue once they reach the airport.
     */
    private void updateApproachQueue() {
        Iterator<Plane> iterator = approachQueue.iterator();
        while (iterator.hasNext()) {
            Plane plane = iterator.next();

            // Simulate the approach by decreasing the distance
            plane.setDistance(plane.getDistance() - 50);

            // Check if the plane has reached the airport
            if (plane.getDistance() <= 0) {
                // Move the plane to the ready-to-land queue
                readyToLandQueue.add(plane);

                // Set the distance to 0 to represent that the plane has landed
                plane.setDistance(0);

                // Remove the plane from the approach queue
                iterator.remove();
            }
        }
    }

    /**
     * Updates the runways by handling planes on each runway.
     */
    private void updateRunways() {
        for (Runway runway : runways) {
            handleRunway(runway);
        }
    }

    /**
     * Handles the specified runway by simulating landing and clearing the runway.
     *
     * @param runway the runway to handle
     */
    private void handleRunway(Runway runway) {
        if (!runway.getPlanesOnRunway().isEmpty()) {
            Plane landingPlane = runway.getPlanesOnRunway().poll();

            if (landingPlane != null) {
                simulateLanding(landingPlane);
            }
        } else if (!readyToLandQueue.isEmpty()) {
            Plane planeToLand = readyToLandQueue.poll();
            if (planeToLand != null) {
                runway.addPlaneToRunway(planeToLand);
            }
        }
    }

    /**
     * Simulates the landing of the specified plane.
     *
     * @param plane the plane to simulate landing for
     */
    private void simulateLanding(Plane plane) {
        System.out.println("Plane " + plane.getId() +
                (plane.isEmergency() ? " (Emergency)" : "") + " has cleared the runway.");
    }

    /**
     * Ends the simulation and prints a message.
     */
    private void endSimulation() {
        System.out.println("Simulation ended after 100 timesteps.");
        System.exit(0);
    }
}
