import java.math.BigDecimal;
import java.math.RoundingMode;

public class City implements CityInterface, Comparable<City> {
    
    private int id;
    private String name;
    private int population;
    private int influenzaCases;

    public City(int id, String name, int population, int influenzaCases) {
        this.id = id;
        this.name = name;
        this.population = population;
        this.influenzaCases = influenzaCases;
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

    public void setID(int ID) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setInfluenzaCases(int InfluenzaCases) {
        this.influenzaCases = influenzaCases;
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
        int densityComparison = Double.compare(o.calculateDensity(), this.calculateDensity());
        if (densityComparison == 0) {
            int nameComparison = o.getName().compareTo(this.getName());
            if (nameComparison == 0) {
                return Integer.compare(o.getID(), this.getID());
            }
            return nameComparison;
        }
        return densityComparison;
    }

    public String toString() {
        return this.getName();
    }
}
