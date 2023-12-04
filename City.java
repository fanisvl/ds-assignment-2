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

    }

    public void setName(String name) {

    }

    public void setPopulation(int population) {

    }

    public void setInfluenzaCases(int InfluenzaCases) {

    }

    @Override
    public int compareTo(City o) {
        return 0;
    }
}
