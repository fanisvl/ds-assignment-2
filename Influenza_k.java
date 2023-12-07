import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Influenza_k {
    public static void main(String[] args) {

        // Get file name
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter daily report filename: ");
        String filename = scanner.nextLine();

        // Create arrayList to store cities
        MaxPQ<City> cities = new MaxPQ<>();


        // Read file
        try (Scanner fileReader = new Scanner(Paths.get(filename))) {
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
            System.out.println("Error: " + e.getMessage());
        }
    }
}
