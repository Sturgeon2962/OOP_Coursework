package cycling;
import java.util.ArrayList;

public class Team {
    //Non-Static Team Attributes
    private String teamName;
    private ArrayList<Rider> teamMembers; 
    private String description;
    private int teamId;

    //Static Team Attributes
    public static int nextTeamId = 0;

    //Constructor for a team
    public Team (String name, String description){
        teamName = name;
        this.description = description;
        teamId = nextTeamId;
        nextTeamId++;
        teamMembers = new ArrayList<Rider>();
    }


    //Getters and setters for all attibutes
    public String getTeamName(){
        return this.teamName;
    }

    public void setTeamName(String name){
        this.teamName = name;
    }

    public ArrayList<Rider> getTeamMembers(){
        return teamMembers;
    }

    public String getTeamDescription(){
        return this.description;
    }

    public void setTeamDescription(String description){
        this.description = description;
    }

    public void createRider(String name, int yearOfBirth){
        teamMembers.add(new Rider(teamId, name, yearOfBirth));
    }

    //All other methods
}