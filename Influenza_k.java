import java.nio.file.Paths;
import java.util.Scanner;

public class Influenza_k {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter daily report filename: ");
        String filename = scanner.nextLine();

        try (Scanner fileReader = new Scanner(Paths.get(filename))) {
            while (fileReader.hasNextLine()) {
                String cityRow = fileReader.nextLine();
                System.out.println(cityRow);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
