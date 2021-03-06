package cycling;

import java.util.Comparator;
import java.io.Serializable;

/**
 * This abstract class is used to represent a segment within a stage.
 * 
 * @author
 * @version 1.0
 */
public abstract class Segment implements Serializable {
    // Non-Static Attributes
    private SegmentType type;
    private double location;
    private int segmentId;
    
    /**
     * Constructor for a Segment.
     * 
     * @param location The km location where the segment finished within the stage.
     * @param type The type of segment being created - {@link SegmentType#C1},
     * {@link SegmentType#C2}, {@link SegmentType#C3}, {@link SegmentType#C4},
     * {@link SegmentType#HC} or {@link SegmentType#SPRINT}
     */
    public Segment(double location, SegmentType type, int id) {
        this.setLocation(location);
        this.setType(type);
        setSegmentId(id);
    }
    
    /**
     * A method to get the ID of the segment
     * 
     * @return A int of the ID of the segment
     */
    public int getSegmentId() {
        return segmentId;
    }

    /**
     * A method to set the ID of the segment
     * 
     * @param id The ID of the segment
     */
    public void setSegmentId(int id) {
        this.segmentId = id;
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

    // Location comparator
    /**
     * A comparator, allowing segments to be order by there location along the stage
     */
    public static Comparator<Segment> compareByLocation = new Comparator<Segment>() {

        @Override
        public int compare(Segment segment1, Segment segment2) {
            return Double.compare(segment1.getLocation(), segment2.getLocation());
        }
        
    };
}