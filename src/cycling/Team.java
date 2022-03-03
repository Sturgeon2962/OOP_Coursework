package cycling;
import java.util.ArrayList;
import java.util.Random;

/**
 * A class to create teams and allocate team members 
 * 
 * @author
 * @version 1.0
 */
public class Team {
    // Non-Static Team Attributes
    private String teamName;
    private ArrayList<Rider> teamMembers; 
    private String description;
    private int teamId;

    // Static Team Attributes
    public static final int MAXTEAMID = 2000000;
    public static ArrayList<Team> teams = new ArrayList<Team>();
    public static ArrayList<Integer> usedId = new ArrayList<Integer>();
    /*
    Could be better to use random IDs
    Between 0 and Integer.maxValue or similar
    Means people can't malicously go through every ID
    */

    // Getter and setters

    /**
     * Constructor to create a new team.
     * 
     * @param name The name given to the team
     * @param description A brief description of the team
     */
    public Team (String name, String description){
        setTeamName(name);
        setTeamDescription(description);
        setTeamId();
        teamMembers = new ArrayList<Rider>();
    }


    /**
     * Getter for the team name
     * 
     * @return Returns the  string containing team name
     */
    public String getTeamName(){
        return this.teamName;
    }

    /**
     * Setter for team name
     * 
     * @param name The name given to the team
     */
    public void setTeamName(String name){
        this.teamName = name;
    }

    /**
     * Getter for team members
     * 
     * @return Returns a ArrayList of team members
     */
    public ArrayList<Rider> getTeamMembers(){
        return teamMembers;
    }

    /**
     * Getter for team description
     * 
     * @return Returns a string containing a brief description of the team 
     */
    public String getTeamDescription(){
        return this.description;
    }

    /**
     * Setter for description
     * 
     * @param description A brief description of the team
     */
    public void setTeamDescription(String description){
        this.description = description;
    }
    
    // All other methods

    /**
     * A method to create a new rider
     * 
     * @param name The name of the rider
     * @param yearOfBirth The year that the rider was born
     */
    public void createRider(String name, int yearOfBirth){
        teamMembers.add(new Rider(teamId, name, yearOfBirth));
    }

    public int getId() {
        return this.teamId;
    }

    public void setTeamId(){
        Random rand = new Random();
        boolean uniqueId = false;
        int newId = rand.nextInt(MAXTEAMID);

        if(usedId == null){
            teamId = newId;
            usedId.add(newId);   
        }else{
            while(uniqueId != true){
                newId = rand.nextInt(MAXTEAMID);
                uniqueId = true;
                for(int id : usedId ){
                    if(id == newId){
                        uniqueId = false;
                        break;
                    }
                }
            }
        }
        teamId = newId;
        usedId.add(newId);
    }

}
