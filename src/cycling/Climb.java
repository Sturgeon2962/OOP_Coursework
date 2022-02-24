package cycling;

public class Climb extends Segment {
    private double avgGradient;
    private double length;

    // Constructor
    Climb(double location, SegmentType type, double avgGradient, double length) {
        super(location, type);
        this.setAvgGradient(avgGradient);
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

    // Other shit
}
