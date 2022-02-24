package cycling;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * A class to represent a stage of a race
 * 
 * @author
 * @version 1.0
 */
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

    /**
     * Constructor to create a new stage
     * 
     * @param stageName The name of the race
     * @param stageDescription A breif description of the race
     * @param stageLength The length of the race (km)
     * @param stageStartTime The local start time of the race
     * @param type The type of the stage - {@link StageType#FLAT},
     * {@link StageType#MEDIUM_MOUNTAIN}, {@link StageType#HIGH_MOUNTAIN},
     * {@link StageType#TT}
     */
    Stage(String name, String description, double length, LocalDateTime startTime, StageType category) {
        setName(name);
        setDescription(description);
        setLength(length);
        setStartTime(startTime);
        setCategory(category);
        segments = new ArrayList<Segment>();
    }

    /**
     * The method sets the name of the stage
     * 
     * @param name The name of the stage
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * The method sets the description of the stage
     * 
     * @param description A breif description of the stage
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * The method sets the length of the stage
     * 
     * @param length The length of the stage (km)
     */
    public void setLength(double length) {
        this.length = length;
    }

    /**
     * The method sets the start time of the stage
     * 
     * @param startTime The start time of the race
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * The method sets the category of the stage
     * 
     * @param category The category of the stage - {@link StageType#FLAT},
     * {@link StageType#MEDIUM_MOUNTAIN}, {@link StageType#HIGH_MOUNTAIN},
     * {@link StageType#TT}
     */
    public void setCategory(StageType category) {
        this.category = category;
    }
    
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
