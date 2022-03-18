package cycling;
import java.io.Serializable;
import java.util.ArrayList;


/**
 * A class to create teams and allocate team members 
 * 
 * @author
 * @version 1.0
 */
public class Team implements Serializable {
    // Non-Static Team Attributes
    private String teamName;
    private ArrayList<Rider> teamMembers; 
    private String description;
    private int teamId;

    // Getter and setters

    /**
     * Constructor to create a new team.
     * 
     * @param name The name given to the team
     * @param description A brief description of the team
     */
    public Team (String name, String description, int id){
        setTeamName(name);
        setTeamDescription(description);
        setTeamId(id);
        teamMembers = new ArrayList<Rider>();
    }


    /**
     * Getter for the team name
     * 
     * @return Returns a string containing team name
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
    public Rider createRider(String name, int yearOfBirth, int id){
        Rider newRider = new Rider(teamId, name, yearOfBirth, id); 
        teamMembers.add(newRider);
        return newRider;
    }

    /**
     * Getter for the ID of the team
     * 
     * @return An int of the ID of the team
     */
    public int getId() {
        return this.teamId;
    }

    /**
     * Setter for the ID of the team
     * 
     * @param id The ID of the team
     */
    public void setTeamId(int id){
        this.teamId = id;
    }

    /**
     * A method to remove a rider from the team
     * 
     * @param riderId The ID of the targe rider to be removed
     */
    public void removeRider(int riderId) {
        for (int x = this.getTeamMembers().size()-1; x >= 0; x-- ){
            if (this.getTeamMembers().get(x).getRiderId() == riderId){
                teamMembers.remove(riderId);
                break;
            }
        }
    }

}
