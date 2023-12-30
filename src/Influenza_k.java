import java.nio.file.Paths;
import java.util.Scanner;

public class Influenza_k {
    public static void main(String[] args) {
        System.out.println("Influenza_k");
        
        // Create arrayList to store cities
        MaxPQ<City> cities = new MaxPQ<>();

        if (args.length != 2) {
            System.out.println("Χρήση: java Influenza_k <k> <input_file>");
            System.exit(1);
        }

        int k = Integer.parseInt(args[0]);
        String filePath = (args[1]);

        // Read file
        try (Scanner fileReader = new Scanner(Paths.get(filePath))) {
            while (fileReader.hasNextLine()) {
                // Split line to city info.
                String line = fileReader.nextLine();
                String[] cityInfo = line.split(" ");

                // Get city info
                int id = Integer.parseInt(cityInfo[0]);
                String name = cityInfo[1];
                int population = Integer.parseInt(cityInfo[2]);
                int influenzaCases = Integer.parseInt(cityInfo[3]);

                // Create and save city
                City city = new City(id, name, population, influenzaCases);
                cities.insert(city);

            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.exit(1);
        }

        // Check if k is valid
        if (k > cities.getSize()) throw new IllegalArgumentException("Maximum valid k value is " + cities.getSize());

        // Get result
        cities.heapSort();
        System.out.println("The top k cities are: ");
        for (int i = 0; i < k; i++) {
            System.out.println(cities.get(i));
        }

    }
}
