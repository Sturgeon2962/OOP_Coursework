package cycling;
import java.util.ArrayList;

public class Race {
    // attributes
    private String name;
    private String description;
    private ArrayList<Stage> stages;

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
