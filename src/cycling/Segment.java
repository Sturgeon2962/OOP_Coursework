package cycling;

public class Segment {
    // Non-Static Attributes
    private SegmentType type;
    private double location;
    private double avgGradient;
    private double length;
    
    // Constuctor for sprints
    public Segment(double location){
        this.setLocation(location);
        this.setType(SegmentType.SPRINT);
        setLength(-1.0);
        setAvgGradient(-1.0);
    }
    
    // Constructor for everything else (not Sprints)
    public Segment(double location, SegmentType type, double gradient, double length){
        this.setLocation(location);
        this.setType(type);
        setAvgGradient(gradient);
        this.setLength(length);
    }
    
    // Getters and Setters
    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getAvgGradient() {
        return avgGradient;
    }

    public void setAvgGradient(double avgGradient) {
        this.avgGradient = avgGradient;
    }

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