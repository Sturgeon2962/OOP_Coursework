package cycling;

public class Rider {
    private String name;
    private int riderId;
    private int teamId;
    private int yearOfBirth;
    public static int nextRiderId = 0;

    public Rider(int teamId, String name, int YearOfBirth){
        this.name = name;
        this.teamId = teamId;
        this.yearOfBirth = yearOfBirth;
        riderId = nextRiderId;
        nextRiderId++;
    }

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
    
}
