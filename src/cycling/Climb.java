package cycling;

/**
 * A class to represent a climbing segment
 * 
 * @author
 * @version
 */
public class Climb extends Segment {
    private double avgGradient;
    private double length;

    /**
     * A constructor to create a new climb
     * 
     * @param location The distance into the race that the climb finishes
     * @param type The type of climb - {@link SegmentType#C1},
     * {@link SegmentType#C2}, {@link SegmentType#C3}, {@link SegmentType#C4} or
     * {@link SegmentType#HC}
     * @param avgGradient The average gradient of the climb
     * @param length The length of the climb (km)
     */
    Climb(double location, SegmentType type, double avgGradient, double length, int id) {
        super(location, type, id);
        this.setAvgGradient(avgGradient);
        this.setLength(length);
    }

    // Getters and Setters

    /**
     * The method gets the lenght of the climb
     * 
     * @return A double of the length of the climb
     */
    public double getLength() {
        return length;
    }

    /**
     * The method sets the length of the climb
     * 
     * @param length The length of the climb (km)
     */
    public void setLength(double length) {
        this.length = length;
    }

    /**
     * The method gets the average gradient of the climb
     * 
     * @return A double of the average gradient of the climb
     */
    public double getAvgGradient() {
        return avgGradient;
    }

    /**
     * The method sets the average gradient of the climb
     * 
     * @param avgGradient The average gradient of the climb
     */
    public void setAvgGradient(double avgGradient) {
        this.avgGradient = avgGradient;
    }

}
