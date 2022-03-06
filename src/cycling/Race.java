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

    /**
     * The constructor to create a race
     * 
     * @param name The name of the race
     * @param descripion A breif description of the race
     */
    public Race(String name, String descripion, int id) {
        setName(name);
        setDescription(descripion);
        stages = new ArrayList<Stage>();
        setRaceId(id);
    }

    /**
     * A method to set the name of a race
     * 
     * @param name The name of the race
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * A mtehod to set the description of the race
     * 
     * @param description A brief description of the race
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * The method gets the name of the race
     * 
     * @return A String of the name of the race
     */
    public String getName() {
        return name;
    }

    /**
     * The method gets the ID of the race
     * 
     * @return An Int of the ID of the race
     */
    public int getRaceID() {
        return raceID;
    }

    /**
     * The method gets the stages in the race
     * 
     * @return An ArrayList of stages in the race
     */
    public ArrayList<Stage> getStages() {
        return stages;
    }


    /**
     * The method gets the description of the race
     * 
     * @return A String of a breif description of the race
     */
    public String getDescription() {
        return description;
    }

    private void setRaceId(int id){
        this.raceID = id;
    }
    // Methods below this point

    public void addStage(Stage newStage){
        getStages().add(newStage);
    }

    public void removeStage(Stage stage){
        getStages().remove(stage);
    }
}
