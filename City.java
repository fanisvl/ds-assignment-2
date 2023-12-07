import java.math.BigDecimal;
import java.math.RoundingMode;

public class City implements CityInterface, Comparable<City> {
    
    private int id;
    private static int[] usedIds = new int[1000];
    private static int usedIdsIndex = 0;
    private String name;
    private int population;
    private int influenzaCases;

    public City(int id, String name, int population, int influenzaCases) {
        setID(id);
        setName(name);
        setPopulation(population);
        setInfluenzaCases(influenzaCases);
    }
    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public int getInfluenzaCases() {
        return influenzaCases;
    }

    public void setID(int id) {
        if (idExists(id)) throw new IllegalArgumentException("id " + id + " already exists");
        if (id < 1 || id > 999) throw new IllegalArgumentException("id must be between 1 and 999 inclusive.");
        usedIds[usedIdsIndex++] = id;
        this.id = id;
    }

    public static boolean idExists(int id) {
        for (int usedId : usedIds) {
            if (usedId == id)  return true;
        }
        return false;
    }

    public void setName(String name) {
        if (name.length() > 50) throw new IllegalArgumentException("Name be less than 50 characters");
        this.name = name;
    }

    public void setPopulation(int population) {
        if (population < 0) throw new IllegalArgumentException("Population must be a positive number.");
        if (population > 10000000) throw new IllegalArgumentException("Population must be below 10.000.000");
        this.population = population;
    }

    public void setInfluenzaCases(int cases) {
        if (cases > population) throw new IllegalArgumentException("Influenza cases cannot be more than population.");
        this.influenzaCases = cases;
    }

    public double calculateDensity() {
        // Calculate cases per 50.000 residents
        double density = (double) influenzaCases / (population / 50000.0);
        BigDecimal bd = BigDecimal.valueOf(density);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    @Override
    public int compareTo(City o) {
        int densityComparison = Double.compare(this.calculateDensity(), o.calculateDensity());
        if (densityComparison == 0) {
            int nameComparison = this.getName().compareTo(o.getName());
            if (nameComparison == 0) {
                return Integer.compare(this.getID(), o.getID());
            }
            return nameComparison;
        }
        return densityComparison;
    }

    public String toString() {
        return this.getName();
    }
}
