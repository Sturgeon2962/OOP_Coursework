package cycling;
import java.util.ArrayList;

/**
 * A class to represent a race containing stages and teams of riders.
 * 
 * @author
 * @version 1.0
 */
public class Race {
    // non-static attributes
    private String name;
    private String description;
    private ArrayList<Stage> stages;
    private int raceID;

    // static attributes
    public static int nextRaceID = 1;

    /**
     * The constructor to create a race
     * 
     * @param name The name of the race
     * @param descripion A breif description of the race
     */
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
