package cycling;
import java.util.ArrayList;

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
    public static int nextTeamId = 1;
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
        teamName = name;
        this.description = description;
        teamId = nextTeamId++;
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

}
