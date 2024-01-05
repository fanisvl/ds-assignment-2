import java.nio.file.Paths;
import java.util.Scanner;

public class Dynamic_Median {
    public static void main(String[] args) {
        System.out.println("Dynamic_Median");

        // Create priority queues to store cities
        PQ<City> denPQ = new PQ<>();
        PQ<City> denTwoPQ = new PQ<>();

        // Initialize median city with a placeholder value
        City median = new City(99, "null", 0, 0);

        // Check if the correct number of command-line arguments is provided
        if (args.length != 1) {
            System.out.println("Usage: java Dynamic_Median <input_file>");
            System.exit(1);
        }

        // Get the file path from command-line arguments
        String filePath = args[0];

        // Read the file using a Scanner
        try (Scanner fileReader = new Scanner(Paths.get(filePath))) {
            // Process each line in the file
            while (fileReader.hasNextLine()) {
                // Split the line to extract city information
                String line = fileReader.nextLine();
                String[] cityInfo = line.split(" ");

                // Extract city information
                int id = Integer.parseInt(cityInfo[0]);
                String name = cityInfo[1];
                int population = Integer.parseInt(cityInfo[2]);
                int influenzaCases = Integer.parseInt(cityInfo[3]);

                // Create and save the city object
                City city = new City(id, name, population, influenzaCases);

                // Insert the city into the first priority queue
                denPQ.insert(city);

                // Transfer elements from the first priority queue to the second in sorted order
                while (!denPQ.isEmpty()) {
                    denTwoPQ.insert(denPQ.getmin());
                }

                // Get the size of the second priority queue
                int sizePQ = denTwoPQ.size();

                // Calculate the median based on the size of the second priority queue
                if (sizePQ == 1 || sizePQ == 2) {
                    median = denTwoPQ.getmin();
                    denPQ.insert(median);
                } else {
                    // Determine the number of iterations needed to find the median
                    int iterations = (sizePQ % 2 == 0) ? sizePQ / 2 : sizePQ / 2 + 1;

                    // Find the median and insert it into the first priority queue
                    for (int h = 0; h < iterations; h++) {
                        median = denTwoPQ.getmin();
                        denPQ.insert(median);
                    }
                }

                // Transfer elements from the second priority queue back to the first
                while (!denTwoPQ.isEmpty()) {
                    denPQ.insert(denTwoPQ.getmin());
                }

                 // Print the calculated density of the final median city
                if (denPQ.size() % 5 == 0) {
                    System.out.println(median.calculateDensity());
                }
            }
        } catch (Exception e) {
            // Handle any exceptions that may occur during file reading
            System.out.println("Error: " + e);
            System.exit(1);
        }

    }
}
