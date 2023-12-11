import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * The {Application} class is the main class for the Airport Simulation.
 * It initializes the airport, sets up a scheduled executor for the simulation task,
 * and prints the state of the airport at each simulation step.
 *
 * @author Ronish Gautam
 * @version Airpot Simulation
 */
public class Application {
    private static final int SIMULATION_INTERVAL = 1; // 1 second

    /**
     * The main method that starts the Airport Simulation.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Airport Simulation!");

        System.out.print("Enter the number of runways: ");
        int numOfRunways = scanner.nextInt();

        Airport airport = new Airport(numOfRunways);
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        Runnable simulationTask = () -> {
            airport.updateQueues();
            printState(airport);
        };

        executorService.scheduleAtFixedRate(simulationTask, 0, SIMULATION_INTERVAL, TimeUnit.SECONDS);
    }

    /**
     * Prints the current state of the airport, including the approach queue,
     * ready-to-land queue, and planes on each runway.
     *
     * @param airport the airport to print the state of
     */
    private static void printState(Airport airport) {
        System.out.println("\nSimulation Steps: " + airport.getTimestepCount());

        // Print planes in the approach queue with their distances
        System.out.println("Plane Queue:");
        for (Plane plane : airport.getApproachQueue()) {
            System.out.println(plane + " - Distance from airport: " + plane.getDistance() +
                    (plane.isEmergency() ? " (Emergency)" : ""));
        }

        // Print planes in the ready-to-land queue
        System.out.println("Ready to Land Queue: " + airport.getReadyToLandQueue());

        // Print planes on each runway
        int runwayIndex = 1;
        for (Runway runway : airport.getRunways()) {
            System.out.println("Runway " + runwayIndex++ + ": " + runway.getPlanesOnRunway());
            System.out.println("---------------------------------------");
        }
    }
}
