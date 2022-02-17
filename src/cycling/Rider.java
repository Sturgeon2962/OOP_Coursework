package cycling;

public class Rider {
    //Non-static Rider Attributes
    private String name;
    private int riderId;
    private int teamId;
    private int yearOfBirth;

    //Static Rider Attributes
    public static int nextRiderId = 0;

    //Constructor For a rider (called using a Team.java Method)
    public Rider(int teamId, String name, int yearOfBirth){
        this.name = name;
        this.teamId = teamId;
        this.yearOfBirth = yearOfBirth;
        riderId = nextRiderId;
        nextRiderId++;
    }

    //Getters and setter for all attributes
    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getRiderId() {
        return riderId;
    }

    public String getName() {
        return name;
    }
    
    //Methods bellow this point

}
