import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.exit;

public class Influenza_k {
    public static void main(String[] args) {

        // Create arrayList to store cities
        MaxPQ<City> cities = new MaxPQ<>();

        // Get file name
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter daily report filename: ");
        String filePath = scanner.nextLine();

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
            System.out.println("File not found: " + filePath);
            System.exit(1);
        }

        // Check if k is valid
        System.out.print("k (max " + cities.getSize() + ") = ");
        int k = Integer.parseInt(scanner.nextLine());
        if (k > cities.getSize()) throw new IllegalArgumentException("Maximum valid k value is " + cities.getSize());

        // Get result
        cities.heapSort();
        System.out.println("The top k cities are: ");
        for (int i = 0; i < k; i++) {
            System.out.println(cities.get(i));
        }

    }
}
