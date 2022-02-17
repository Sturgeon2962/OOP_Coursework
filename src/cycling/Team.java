package cycling;
import java.util.ArrayList;

public class Team {

    private String teamName;
    private ArrayList<Rider> teamMembers; 
    private String description;
    public static int nextTeamId;
    private int teamId;

    public Team (String name, String description){
        this.teamName = name;
        this.description = description;
        this.teamId = nextTeamId;
        nextTeamId++;
    }

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
}
