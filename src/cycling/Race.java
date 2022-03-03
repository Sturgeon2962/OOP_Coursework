package cycling;
import java.util.ArrayList;
import java.util.Random;

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
    private static final int MAXRACEID = 2000000;
    public static ArrayList<Race> races = new ArrayList<Race>();
    

    /**
     * The constructor to create a race
     * 
     * @param name The name of the race
     * @param descripion A breif description of the race
     */
    public Race(String name, String descripion) {
        setName(name);
        setDescription(descripion);
        stages = new ArrayList<Stage>();
        setRaceId();
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

    private void setRaceId(){
        Random rand = new Random();
        boolean uniqueId = false;
        int newId = rand.nextInt(MAXRACEID);
        
        while(uniqueId != true){
            newId = rand.nextInt(MAXRACEID);
            uniqueId = true;
            for (Race race: races){
                if(race.getRaceID() == newId){
                    uniqueId = false;
                    break;
                }
            }
        }
        raceID = newId;
    }
    // Methods below this point
    
    public static void removeRace(Race raceToBeRemoved){
        Race.races.remove(raceToBeRemoved);
    }

    public void addStage(Stage newStage){
        getStages().add(newStage);
    }

    public void removeStage(Stage stage){
        getStages().remove(stage);
    }
}
