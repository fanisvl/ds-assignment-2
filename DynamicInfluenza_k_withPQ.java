import java.nio.file.Paths;
import java.util.Scanner;

public class DynamicInfluenza_k_withPQ {
    public static void main(String[] args) {
        System.out.println("DynamicInfluenza_k_withPQ");
        
        // Create arrayList to store cities
        PQ<City> cities = new PQ<>();

        if (args.length != 2) {
            System.out.println("Χρήση: java DynamicInfluenza_k_withPQ <k> <input_file>");
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
                
                
                
                if (cities.size() < k) { cities.insert(city); } 
                
                else {
                    City MaxCity = cities.getMax();
                    
                    if (MaxCity.compareTo(city)>0){
                       
                        cities.remove(MaxCity);
                       
                        cities.insert(city);
                    }
                } 
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            System.exit(1);
        }
        
        System.out.println("The top k cities are: ");
        while(!cities.isEmpty()){
            System.out.println(cities.getmin());
        }
       
    }
}
