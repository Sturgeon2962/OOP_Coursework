package cycling;

import java.util.ArrayList;
import java.util.Random;

/**
 * A class to represent a riders in a race
 * 
 * @author
 * @version 1.0
 */
public class Rider {
    // Non-static Rider Attributes
    private String name;
    private int riderId;
    private int teamId;
    private int yearOfBirth;

    // Static Rider Attributes
    public static final int MAXRIDERID = 2000000;
    public static ArrayList<Integer> usedId = new ArrayList<Integer>();

    /**
     * Constructor for a rider, This should mainly be called from the team method createRider {@link #createRider()}
     * 
     * @param teamId The Id of the team that this instance of the rider belongs to
     * @param name The name of the rider
     * @param yearOfBirth The year that the rider was born
     */
    public Rider(int teamId, String name, int yearOfBirth){
        setName(name);
        setTeamId(teamId);
        setYearOfBirth(yearOfBirth);
        setRiderId();
    }

    // Getters and setter for all attributes

    /**
     * A getter for the year of birth of the rider
     * 
     * @return Returns the integer of the year of birth of the rider
     */
    public int getYearOfBirth() {
        return yearOfBirth;
    }

    /**
     * A setter for the year of birth for the rider
     * 
     * @param yearOfBirth The year the rider was born
     */
    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    /**
     * A getter for the team Id
     * 
     * @return Returns the integer Id of the riders team
     */
    public int getTeamId() {
        return teamId;
    }

    /**
     * A setter for the riders Team Id
     * 
     * @param teamId The Id for the riders team
     */
    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    /**
     * A getter for the Rider's Id
     * 
     * @return The integer Id of the Rider
     */
    public int getRiderId() {
        return riderId;
    }

    public void setRiderId(){
        Random rand = new Random();
        boolean uniqueId = false;
        int newId = rand.nextInt(MAXRIDERID);
        
        if(usedId == null){
            riderId = newId;
            usedId.add(newId);   
        }else{
            while(uniqueId != true){
                newId = rand.nextInt(MAXRIDERID);
                uniqueId = true;
                for(int id : usedId ){
                    if(id == newId){
                        uniqueId = false;
                        break;
                    }
                }
            }
            riderId = newId;
            usedId.add(newId);
        }
    }


    /**
     * A getter for the Rider's Name
     * 
     * @return Returns the name of the rider
     */
    public String getName() {
        return name;
    }
    
    /**
     * A setter for the riders name
     * 
     * @param name The name of the rider
     */
    public void setName(String name){
        this.name = name;
    }
    // Methods bellow this point

}
