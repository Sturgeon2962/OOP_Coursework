package cycling;
import java.util.ArrayList;

public class Race {
    // non-static attributes
    private String name;
    private String description;
    private ArrayList<Stage> stages;
    private int raceID;

    // static attributes
    public static int nextRaceID = 1;

    // Constructor
    public Race(String name, String descripion) {
        this.name = name;
        this.description = descripion;
        stages = new ArrayList<Stage>();
        raceID = nextRaceID++;
    }

    public String getName() {
        return name;
    }

    public int getRaceID() {
        return raceID;
    }

    public ArrayList<Stage> getStages() {
        return stages;
    }

    public String getDescription() {
        return description;
    }
}
