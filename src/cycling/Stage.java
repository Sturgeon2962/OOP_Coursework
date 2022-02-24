package cycling;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Stage {
    // Stage points - static attributes
    private final static int[] sprintFlatPoints = { 50, 30, 20, 18, 16, 14, 12, 10, 8, 7, 6, 5, 4, 3, 2 };
    private final static int[] sprintHillyPoints = { 30, 25, 22, 19, 17, 15, 13, 11, 9, 7, 6, 5, 4, 3, 2 };
    private final static int[] sprintMountainPoints = { 20, 17, 15, 13, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
    private final static int[] sprintTTPoints = { 20, 17, 15, 13, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
    private final static int[] sprintIntermediatePoints = { 20, 17, 15, 13, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
    private final static int[] mountainCat4Points = { 1 };
    private final static int[] mountainCat3Points = { 2, 1 };
    private final static int[] mountainCat2Points = { 5, 3, 2, 1 };
    private final static int[] mountainCat1Points = { 10, 8, 6, 4, 2, 1 };
    private final static int[] mountainHCPoints = { 20, 15, 12, 10, 8, 6, 4, 2 };

    // Non-static attributes
    private StageType category;
    private ArrayList<Segment> segments;
    private String name;
    private String description;
    private double length;
    private LocalDateTime startTime;

    // Contructor
    Stage(String stageName, String stageDescription, double stageLength, LocalDateTime stageStartTime, StageType type) {
        name = stageName;
        description = stageDescription;
        length = stageLength;
        startTime = stageStartTime;
        category = type;
        segments = new ArrayList<Segment>();
    }

    // Getters/Setters
    public static int[] getSprintFlat() {
        return sprintFlatPoints;
    }

    public double getLength() {
        return length;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public StageType getCategory() {
        return category;
    }

    public ArrayList<Segment> getSegments() {
        return segments;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public static int[] getSprintHilly() {
        return sprintHillyPoints;
    }

    public static int[] getSprintTT() {
        return sprintTTPoints;
    }

    public static int[] getSprintHigh() {
        return sprintMountainPoints;
    }

    public static int[] getSprintIntermediate() {
        return sprintIntermediatePoints;
    }

    public static int[] getMountainC4() {
        return mountainCat4Points;
    }

    /**
     * A getter for the mountainC3 points
     * 
     * @return Returns the array containing mountainCat3 points
     */ 
    public static int[] getMountainC3() {
        return mountainCat3Points;
    }

    /**
     * A getter for the mountainC2 points
     * 
     * @return Returns the array containing mountaincat2 points
     */
    public static int[] getMountainC2() {
        return mountainCat2Points;
    }

    /**
     * A getter for the mountainC1 points
     * 
     * @return Returns the array containing mountainCat1 points
     */
    public static int[] getMountainC1() {
        return mountainCat1Points;
    }

    /**
     * A getter for the MountainHC points
     * 
     * @return Returns the Array containing mountainHCpoints
     */
    public static int[] getMountainHC() {
        return mountainHCPoints;
    }

    // Add segment

}
