package cycling;

/**
 * This abstract class is used to represent a segment within a stage.
 * 
 * @author
 * @version 1.0
 */
public abstract class Segment {
    // Non-Static Attributes
    private SegmentType type;
    private double location;
    
    /**
     * Constructor for a Segment.
     * 
     * @param location The km location where the segment finished within the stage.
     * @param type The type of segment being created - {@link SegmentType#C1},
     * {@link SegmentType#C2}, {@link SegmentType#C3}, {@link SegmentType#C4},
     * {@link SegmentType#HC} or {@link SegmentType#SPRINT}
     */
    public Segment(double location, SegmentType type) {
        this.setLocation(location);
        this.setType(type);
    }
    
    /**
     * The method gets the location of the segment.
     * 
     * @return A double of the km location where the segment finished within the stage.
     */
    public double getLocation() {
        return location;
    }

    /**
     * The method sets the location of the segment.
     * 
     * @param location The km location where the segment finished within the stage.
     */
    public void setLocation(double location) {
        this.location = location;
    }

    /**
     * The method gets the type of segment.
     * 
     * @return The type of the segment - {@link SegmentType#C1},
     * {@link SegmentType#C2}, {@link SegmentType#C3}, {@link SegmentType#C4},
     * {@link SegmentType#HC} or {@link SegmentType#SPRINT}
     */
    public SegmentType getType() {
        return type;
    }

    /**
     * The method sets the type of segment.
     * 
     * @param type The type of segment being created - {@link SegmentType#C1},
     * {@link SegmentType#C2}, {@link SegmentType#C3}, {@link SegmentType#C4},
     * {@link SegmentType#HC} or {@link SegmentType#SPRINT}
     */
    public void setType(SegmentType type) {
        this.type = type;
    }

    // Any other methods
}