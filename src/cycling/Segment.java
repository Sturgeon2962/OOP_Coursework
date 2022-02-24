package cycling;

public abstract class Segment {
    // Non-Static Attributes
    private SegmentType type;
    private double location;
    
    // Constuctor for sprints
    public Segment(double location, SegmentType type) {
        this.setLocation(location);
        this.setType(SegmentType.SPRINT);
    }
    
    // Getters and Setters
    public double getLocation() {
        return location;
    }

    public void setLocation(double location) {
        this.location = location;
    }

    public SegmentType getType() {
        return type;
    }

    public void setType(SegmentType type) {
        this.type = type;
    }

    // Any other methods
}