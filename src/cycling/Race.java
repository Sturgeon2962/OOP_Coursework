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

    public String getName() {
        return name;
    }

    public ArrayList<Stage> getStages() {
        return stages;
    }

    public String getDescription() {
        return description;
    }
}
