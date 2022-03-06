package cycling;

/**
 * A class to represent an IntermediateSprint Segment of a race
 * 
 * @author
 * @version 1.0
 */
public class IntermediateSprint extends Segment {
    // Constructor
    /**
     * A class to represent an Intermediate sprint segment
     * 
     * @param location How far into the race this segment finishes
     */
    IntermediateSprint(double location, int id) {
        super(location, SegmentType.SPRINT, id);
    }
}
