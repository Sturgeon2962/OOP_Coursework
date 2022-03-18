package cycling;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;


/**
 * A class to represent a stage of a race
 * 
 * @author
 * @version 1.0
 */
public class Stage implements Serializable {
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
    private int stageId;
    private boolean fullyCreated = false;

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
    Stage(String name, String description, double length, LocalDateTime startTime, StageType category, int id) {
        setName(name);
        setDescription(description);
        setLength(length);
        setStartTime(startTime);
        setCategory(category);
        segments = new ArrayList<Segment>();
        setStageId(id);
    }

    // Getters/Setters

    /**
     * A function to check weather a stage is complete or not
     *  
     * @return A boolean variable fullyCreated
     */
    public boolean isFullyCreated() {
        return fullyCreated;
    }

    /**
     * A Setter for fullyCreated
     * 
     * @param fullyCreated A boolean value
     */
    public void setFullyCreated(boolean fullyCreated) {
        this.fullyCreated = fullyCreated;
    }

    /**
     * A Getter for stage Id
     * 
     * @return StageId
     */
    public int getStageId() {
        return stageId;
    }

    /**
     * A Setter for Stage Id
     * @param id integer value
     */
    private void setStageId(int id){
        this.stageId = id;
    }

    /**
     * A Setter for the name of the stage
     * 
     * @param name The name of the stage
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * A Setter for the stage description
     * 
     * @param description A breif description of the stage
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * A Setter for the length of the stage
     * 
     * @param length The length of the stage (km)
     */
    public void setLength(double length) {
        this.length = length;
    }

    /**
     * A Setter for the start time of the stage
     * 
     * @param startTime The start time of the race
     */
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    /**
     * A Setter for the category of the stage
     * 
     * @param category The category of the stage - {@link StageType#FLAT},
     * {@link StageType#MEDIUM_MOUNTAIN}, {@link StageType#HIGH_MOUNTAIN},
     * {@link StageType#TT}
     */
    public void setCategory(StageType category) {
        this.category = category;
    }
    
    /**
     * A getter for the Flat sprin points
     * 
     * @return Returns an array of integers containing the flat sprint points
     */
    public static int[] getSprintFlat() {
        return sprintFlatPoints;
    }

    /**
     * A getter for the race length
     * 
     * @return Returns a double containing the length of the race
     */
    public double getLength() {
        return length;
    }

    /**
     * A getter for a description of the race
     * 
     * @return Returns a string containing a description of the race
     */
    public String getDescription() {
        return description;
    }

    /**
     * A getter for the race name
     * 
     * @return Returns a string containing the race name
     */
    public String getName() {
        return name;
    }

    /**
     * A getter fo the catagory of a race
     * 
     * @return  Returns the StageType of the race
     */
    public StageType getCategory() {
        return category;
    }

    /**
     * A getter for the segments in a race
     * 
     * @return Returns an ArrayList of segments of all the segments in a race
     */
    public ArrayList<Segment> getSegments() {
        return segments;
    }

    /**
     * A getter for the start time#
     * * 
     * @return Returns the localDateTime containing the start time
     */ 
    public LocalDateTime getStartTime() {
        return startTime;
    }

    /**
     * A getter for the hilly sprint points
     * 
     * @return Returns the array of integers containing hilly sprint points
     */ 
    public static int[] getSprintHilly() {
        return sprintHillyPoints;
    }

    /**
     * A getter for the Time trial sprints points
     * 
     * @return Returns the array of integers containing time trial sprint points
     */ 
    public static int[] getSprintTT() {
        return sprintTTPoints;
    }

    /**
     * A getter for the mountain sprint points
     * 
     * @return Returns the array of integers containing mountain sprint points
     */ 
    public static int[] getSprintHigh() {
        return sprintMountainPoints;
    }

    /**
     * A getter for the sprint intermediate points
     * 
     * @return Returns the array of integers containing sprint intermediate points
     */ 
    public static int[] getSprintIntermediate() {
        return sprintIntermediatePoints;
    }

    /**
     * A getter for the mountainC4 points
     * 
     * @return Returns the array of integers containing mountainCat4 points
     */ 
    public static int[] getMountainC4() {
        return mountainCat4Points;
    }

    /**
     * A getter for the mountainC3 points
     * 
     * @return Returns the array of integers containing mountainCat3 points
     */ 
    public static int[] getMountainC3() {
        return mountainCat3Points;
    }

    /**
     * A getter for the mountainC2 points
     * 
     * @return Returns the array of integers containing mountaincat2 points
     */
    public static int[] getMountainC2() {
        return mountainCat2Points;
    }

    /**
     * A getter for the mountainC1 points
     * 
     * @return Returns the array of integers containing mountainCat1 points
     */
    public static int[] getMountainC1() {
        return mountainCat1Points;
    }

    /**
     * A getter for the MountainHC points
     * 
     * @return Returns the Array of integers containing mountainHCpoints
     */
    public static int[] getMountainHC() {
        return mountainHCPoints;
    }

    /**
     * A Function to delete the Segment using the Id
     * 
     * @param segmentId The Id of the segment
     */
    public void removeSegmentById(int segmentId){
        Segment toRemove = null;
        for (Segment segment : this.getSegments()) {
            if (segment.getSegmentId() == segmentId) {
                toRemove = segment;
            }
        }
        if (toRemove != null) {
            this.getSegments().remove(toRemove);
        }
    }

    /**
     * A function to add a segment to a stage
     * 
     * @param segment The Segment to be added
     */
    public void addSegment(Segment segment){
        segments.add(segment);

        // Order segments by location from start
        Collections.sort(segments, Segment.compareByLocation);
    }

}
